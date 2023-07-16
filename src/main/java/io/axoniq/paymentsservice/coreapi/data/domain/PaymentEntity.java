package io.axoniq.paymentsservice.coreapi.data.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    private String paymentId;
    @Column
    public String orderId;

}
