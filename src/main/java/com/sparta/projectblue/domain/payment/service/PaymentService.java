package com.sparta.projectblue.domain.payment.service;

import com.sparta.projectblue.domain.common.enums.PaymentStatus;
import com.sparta.projectblue.domain.common.exception.PaymentException;
import com.sparta.projectblue.domain.payment.dto.PaymentResponseDto;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PerformanceRepository performanceRepository;
    private final UserRepository userRepository;

    private static final String TOSS_BASIC_URL = "https://api.tosspayments.com/v1/payments/";
    private static final String WIDGET_SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

    @Transactional
    public JSONObject confirmPayment(String jsonBody) throws Exception {
        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;
        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };

        if(!verifyPayment(orderId, Long.parseLong(amount))) {
            throw new PaymentException("주문ID에 대한 가격이 상이합니다.");
        }

        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        String authorizations = secretKeyEncoder();

        // 결제 승인 API를 호출하세요.
        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        // @docs https://docs.tosspayments.com/guides/v2/payment-widget/integration#3-결제-승인하기
        URL url = new URL(TOSS_BASIC_URL + "confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // TODO: 결제 성공 및 실패 비즈니스 로직을 구현하세요.
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();

        if(!verifyPayment(orderId, Long.parseLong(amount))) {
            // 결제 취소 부탁드립니다
            cancelPayment(paymentKey, "wrong orderId!!!!!!!!!");
            throw new PaymentException("주문ID에 대한 가격이 상이합니다.");
        } // 별 필요 없을듯

        if (isSuccess) {
            // 결제 승인 후 처리
            savePayment(jsonObject);
        }
        
        return jsonObject;
    }

    @Transactional
    public void savePayment(JSONObject jsonObject) {
        String orderId = (String) jsonObject.get("orderId");

        Long reservationId = Long.parseLong(orderId.substring(23));

        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() ->
                new IllegalArgumentException("예매 정보를 찾을 수 없습니다"));

        OffsetDateTime approvedAt = OffsetDateTime.parse((String) jsonObject.get("approvedAt"));

        Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(()->
                new IllegalArgumentException("결제 정보를 찾을 수 없습니다"));

        payment.addPaymentInfo(
                (String) jsonObject.get("paymentKey"),
                (String) jsonObject.get("type"),
                (String) jsonObject.get("method"),
                (Long) jsonObject.get("suppliedAmount"),
                (Long) jsonObject.get("vat"),
                approvedAt.toLocalDateTime()
        );
        // 실패된 결제는 유지 status값 추가

        reservation.addPaymentId(payment.getId());

        reservation.resCompleted();
    }

    @Transactional
    public String cancelPayment(String paymentKey, String cancelReason) throws Exception {
        // 취소 API 호출 URL
        URL url = new URL(TOSS_BASIC_URL + paymentKey + "/cancel");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", secretKeyEncoder());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // 취소 요청 데이터 설정
        JSONObject obj = new JSONObject();
        obj.put("cancelReason", cancelReason);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

//        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
//        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        if (isSuccess) {
            // payment 상태 취소 변경
            Payment payment = paymentRepository.findByPaymentKey(paymentKey)
                    .orElseThrow(() -> new IllegalArgumentException("결제 정보를 찾을 수 없습니다."));

            payment.canceled();
        }

        return String.valueOf(obj);
    }

    @Transactional
    public PaymentResponseDto setValue(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() ->
                new IllegalArgumentException("예약 내역이 없습니다."));

        Performance performance = performanceRepository.findById(reservation.getPerformanceId()).orElseThrow(() ->
                new IllegalArgumentException("해당 공연이 존재하지 않습니다."));

        User user = userRepository.findById(reservation.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String orderId = "blueRes_" + timestamp + "_" + reservationId;

        Payment payment = new Payment(
                user.getId(),
                reservation.getId(),
                performance.getId(),
                reservation.getPrice(),
                orderId
        );

        paymentRepository.save(payment);

        return PaymentResponseDto.builder()
                .orderId(orderId)
                .orderName(performance.getTitle())
                .amount(reservation.getPrice())
                .customerEmail(user.getEmail())
                .customerName(user.getName())
                .build();
    }

    public String secretKeyEncoder() {
        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        // @docs https://docs.tosspayments.com/reference/using-api/authorization#%EC%9D%B8%EC%A6%9D
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((WIDGET_SECRET_KEY + ":").getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedBytes);
    }

    private boolean verifyPayment(String orderId, Long amount) {
        Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(() ->
                new IllegalArgumentException("결제 내역이 존재하지 않습니다."));

        return Objects.equals(amount, payment.getAmountTotal());
    }
}
