package com.sparta.projectblue.domain.payment.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sparta.projectblue.domain.payment.dto.PaymentResponseDto;
import com.sparta.projectblue.domain.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody)
            throws Exception {

        return ResponseEntity.ok(paymentService.confirmPayment(jsonBody));
    }

    @GetMapping("/payments/{reservationId}")
    public String payments(Model model, @PathVariable Long reservationId) {

        PaymentResponseDto responseDto = paymentService.setValue(reservationId);

        model.addAttribute("paymentResponse", responseDto);

        return "/checkout";
    }

    @GetMapping("/payments/success")
    public String paymentRequest(HttpServletRequest request, Model model) throws Exception {

        return "/success";
    }

    @GetMapping("/fail")
    public String failPayment(HttpServletRequest request, Model model) throws Exception {

        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "/fail";
    }
}
