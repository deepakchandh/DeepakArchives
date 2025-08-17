package com.java.gptcodes;

import java.util.*;
// 1. Interface Segregation & Abstraction: Clean interfaces for payment and notifications

interface PaymentStrategy {
    boolean pay(double amount);
}

interface Notifier {
    void notifyUser(String message);
}

// 2. Open/Closed, Liskov Substitution: Flexible payment implementations

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {
        // In real scenarios, make payment gateway call
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
        return true;
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal: " + email);
        return true;
    }
}

// 3. Observer Pattern Implementation: Decouple communication

interface OrderObserver {
    void onOrderStatusChanged(Order order);
}

class NotificationService implements OrderObserver {
    private Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void onOrderStatusChanged(Order order) {
        notifier.notifyUser("Order status changed to: " + order.getStatus());
    }
}

class EmailNotifier implements Notifier {
    private String email;

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("Email sent to " + email + ": " + message);
    }
}

// 4. Models and Core Business Logic (SRP: Each class has a single responsibility)

enum OrderStatus {
    CREATED, PAID, SHIPPED, CANCELLED
}

class Order {
    private static int counter = 1;
    private final int orderId;
    private final double totalAmount;
    private OrderStatus status;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(double totalAmount) {
        this.orderId = counter++;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.CREATED;
    }

    public int getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus status) {
        this.status = status;
        notifyObservers();
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.onOrderStatusChanged(this);
        }
    }
}

// 5. Factory Pattern for Payment creation (encapsulates instantiation logic)

class PaymentFactory {
    public static PaymentStrategy getPaymentStrategy(String type, String identifier) {
        switch (type.toLowerCase()) {
            case "creditcard":
                return new CreditCardPayment(identifier);
            case "paypal":
                return new PayPalPayment(identifier);
            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}

// 6. Order Processor (Dependency Inversion: high-level depends on abstraction)

class OrderProcessor {
    private final PaymentStrategy paymentStrategy;
    private final Order order;

    public OrderProcessor(PaymentStrategy paymentStrategy, Order order) {
        this.paymentStrategy = paymentStrategy;
        this.order = order;
    }

    public void processOrder() {
        if (paymentStrategy.pay(order.getTotalAmount())) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.CANCELLED);
        }
    }
}

// 7. Main class tying everything together

public class OrderProcessingSystem {
    public static void main(String[] args) {
        // Create an order
        Order order = new Order(499.99);

        // Observer/Notification setup
        Notifier notifier = new EmailNotifier("customer@example.com");
        OrderObserver notificationService = new NotificationService(notifier);
        order.addObserver(notificationService);

        // Factory: Get a payment strategy
        PaymentStrategy payment = PaymentFactory.getPaymentStrategy("creditcard", "1234-5678-9876-4321");

        // SRP & Dependency Injection: Order processing
        OrderProcessor processor = new OrderProcessor(payment, order);
        processor.processOrder();

        // Output order status
        System.out.println("Final Order Status: " + order.getStatus());
    }
}

