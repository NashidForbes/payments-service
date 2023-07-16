package io.axoniq.paymentsservice.command.aggregate;

import io.axoniq.core.commands.ProcessPaymentCommand;
import io.axoniq.core.events.PaymentProcessedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PaymentAggregate {
    @AggregateIdentifier
    private  String paymentId;
    private  String orderId;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {
        PaymentProcessedEvent paymentProcessedEvent = PaymentProcessedEvent.builder()
                .paymentId(processPaymentCommand.getPaymentId())
                .orderId(processPaymentCommand.getOrderId())
                .build();

        AggregateLifecycle.apply(paymentProcessedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event){
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }
}
