package io.axoniq.paymentsservice.query.handler;

import io.axoniq.core.events.PaymentProcessedEvent;
import io.axoniq.paymentsservice.coreapi.data.domain.PaymentEntity;
import io.axoniq.paymentsservice.coreapi.data.domain.interfaces.PaymentsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventsHandler {

    private final PaymentsRepository paymentsRepository;

    public PaymentEventsHandler(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(event, paymentEntity);

        try {
            paymentsRepository.save(paymentEntity);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
