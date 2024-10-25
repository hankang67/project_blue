package com.sparta.projectblue.domain.payment.controller;

import com.sparta.projectblue.domain.payment.dto.PaymentResponseDto;
import com.sparta.projectblue.domain.payment.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {
        return ResponseEntity.ok(paymentService.confirmPayment(jsonBody));
    }

    @GetMapping("/payments/{reservationId}")
    public String payments(Model model, @PathVariable Long reservationId) {
        PaymentResponseDto responseDto = paymentService.setValue(reservationId);
        model.addAttribute("paymentResponse", responseDto);
        return "/checkout";
    }

    /**
     * 인증성공처리
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/payments/success")
    public String paymentRequest(HttpServletRequest request, Model model) throws Exception {
        return "/success";
    }

    /**
     * 인증실패처리
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/fail")
    public String failPayment(HttpServletRequest request, Model model) throws Exception {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "/fail";
    }

//    @PostMapping("/cancel")
//    public ResponseEntity<String> cancelPayment(
//            @RequestParam String paymentKey,
//            @RequestParam String cancelReason) throws Exception {
//        String response = paymentService.cancelPayment(paymentKey, cancelReason);
//        return ResponseEntity.ok(response);
//    }
}
