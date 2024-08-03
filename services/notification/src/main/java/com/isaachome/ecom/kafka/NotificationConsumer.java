package com.isaachome.ecom.kafka;

import com.isaachome.ecom.kafka.order.OrderConfirmation;
import com.isaachome.ecom.kafka.payment.PaymentConfirmation;
import com.isaachome.ecom.notification.Notification;
import com.isaachome.ecom.notification.NotificationRepository;
import com.isaachome.ecom.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationConsumer {
    private final NotificationRepository repository;
//    private final EmailService service;

    @KafkaListener(topics = "payment-topic")
    public  void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Consuming the message from Payment-topic TOPIC : {}", paymentConfirmation);
        var notification = Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();
        repository.save(notification);

        // todo sendout mail
    }

    @KafkaListener(topics = "order-topic")
    public  void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Consuming the message from Order-topic TOPIC : {}", orderConfirmation);
        var notification = Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();
        repository.save(notification);

        // todo sendout mail
    }

}
