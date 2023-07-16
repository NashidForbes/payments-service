package io.axoniq.paymentsservice.coreapi.data.domain.interfaces;

import io.axoniq.paymentsservice.coreapi.data.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<PaymentEntity, String> {
}
