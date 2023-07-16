package io.axoniq.paymentsservice.command.interceptors;

import io.axoniq.core.commands.ProcessPaymentCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Component
public class ProcessPaymentCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    @NotNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NotNull List<? extends CommandMessage<?>> messages) {
        return (index, commandMessage) -> {

            ProcessPaymentCommand processPaymentCommand = (ProcessPaymentCommand) commandMessage.getPayload();

            log.info("ProcessPaymentCommandInterceptor intercepted command: {}", commandMessage);

            if (ProcessPaymentCommand.class.equals(commandMessage.getPayload().getClass())) {
                if (processPaymentCommand.getPaymentId() == null) {
                    throw new IllegalArgumentException("PaymentId is required");
                }

                if (processPaymentCommand.getOrderId() == null) {
                    throw new IllegalArgumentException("OrderId is required");
                }
            }

            return commandMessage;
        };
    }
}
