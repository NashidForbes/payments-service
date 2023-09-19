package io.axoniq.paymentsservice.query.handler;

import io.axoniq.core.events.PaymentProcessedEvent;
import io.axoniq.paymentsservice.coreapi.data.domain.PaymentEntity;
import io.axoniq.paymentsservice.coreapi.data.domain.interfaces.PaymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class PaymentEventsHandler {

    private final PaymentsRepository paymentsRepository;

    public PaymentEventsHandler(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(event, paymentEntity);

        if(event.getPaymentId().isBlank()) {
            paymentEntity.setPaymentId(UUID.randomUUID().toString());
            log.info("No payment id provided by PaymentProcessedEvent event field paymentId, generating a new one");
        }

        try {
            paymentsRepository.save(paymentEntity);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
