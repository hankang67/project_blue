package com.sparta.projectblue.domain.payment.repository;

import com.sparta.projectblue.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
