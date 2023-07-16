package io.axoniq.paymentsservice;

import io.axoniq.core.config.AxonConfig;
import io.axoniq.paymentsservice.command.interceptors.ProcessPaymentCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({ AxonConfig.class })
public class PaymentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsServiceApplication.class, args);
    }

    // register ProcessPaymentCommand Message Interceptor
    @Autowired
    public void registerProcessPaymentCommandMessageInterceptor(ApplicationContext context, CommandBus commandBus) {

        commandBus.registerDispatchInterceptor(context.getBean(ProcessPaymentCommandInterceptor.class));
    }

}
