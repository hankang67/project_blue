package com.sparta.projectblue.domain.payment.service;

import com.sparta.projectblue.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    PaymentRepository paymentRepository;
}
