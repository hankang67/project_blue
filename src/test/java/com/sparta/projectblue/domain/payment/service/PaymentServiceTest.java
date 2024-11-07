package com.sparta.projectblue.domain.payment.service;

import com.sparta.projectblue.domain.common.enums.PaymentStatus;
import com.sparta.projectblue.domain.common.enums.ReservationStatus;
import com.sparta.projectblue.domain.coupon.service.CouponService;
import com.sparta.projectblue.domain.payment.entity.Payment;
import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.reservation.entity.Reservation;
import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.user.repository.UserRepository;
import okhttp3.mockwebserver.MockResponse;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import okhttp3.mockwebserver.MockWebServer;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private PerformanceRepository performanceRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CouponService couponService;

    @InjectMocks
    private PaymentService paymentService;

    private MockWebServer mockWebServer;

    @BeforeEach
    void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // 결제 서비스의 URL을 MockWebServer 주소로 변경
        ReflectionTestUtils.setField(paymentService, "TOSS_BASIC_URL", mockWebServer.url("/v1/payments/").toString());
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Nested
    class ConfirmPaymentTest {

        @Test
        void 결제_승인_정상_동작() throws Exception {

            // given
            String orderId = "blueRes_20241107040730_64";

            JSONObject requestData = new JSONObject();
            requestData.put("paymentKey", "testPaymentKey");
            requestData.put("orderId", orderId);
            requestData.put("amount", "10000");

            String jsonBody = requestData.toString();

            Payment payment = new Payment(1L, 1L, 1L, 10000L, 0L, "orderId123");
            ReflectionTestUtils.setField(payment, "status", PaymentStatus.READY);

            given(paymentRepository.findByOrderId(anyString())).willReturn(Optional.of(payment));

            given(paymentRepository.findByReservationIdAndStatus(anyLong(), any(PaymentStatus.class))).willReturn(Optional.empty());

            JSONObject responseJson = new JSONObject();
            responseJson.put("approvedAt", "2024-11-06T15:23:01Z");
            responseJson.put("orderId", orderId);
            responseJson.put("totalAmount", 10000L);

            mockWebServer.enqueue(new MockResponse()
                    .setResponseCode(200)
                    .setBody(responseJson.toString())
                    .addHeader("Content-Type", "application/json"));

            Reservation reservation = new Reservation(1L, 1L, 1L, ReservationStatus.PENDING, 15000L);

            given(reservationRepository.findById(anyLong())).willReturn(Optional.of(reservation));

            // when
            JSONObject response = paymentService.confirmPayment(jsonBody);

            // then
            assertEquals(response.get("orderId"), orderId);
            assertEquals(response.get("totalAmount"), 10000L);

            assertEquals(reservation.getStatus(), ReservationStatus.COMPLETED);
        }


    }
}
