package com.kiosk.app.model;

import com.kiosk.app.enums.PaymentMethod;
import com.kiosk.app.enums.PaymentStatus;
import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private BigDecimal totalPrice;
    private String couponStatus;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private Date completedAt;
    private Date failureAt;
    private Date createdAt;

    public Payment(BigDecimal totalPrice, PaymentMethod paymentMethod) {
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.PENDING;
        this.createdAt = new Date();
    }

    public void completePayment() {
        this.status = PaymentStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public void failPayment(String reason) {
        this.status = PaymentStatus.FAILED;
        this.failureAt = new Date();
    }

    public String getReceipt() {
        if (status != PaymentStatus.COMPLETED) {
            return null;
        }
        // TODO: Implement receipt generation
        return "Receipt for payment: " + totalPrice;
    }

    public void addCoupon(String couponCode) {
        this.couponStatus = "APPLIED";
        // TODO: Implement coupon logic
    }

    // Getters and Setters
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getCouponStatus() { return couponStatus; }
    public void setCouponStatus(String couponStatus) { this.couponStatus = couponStatus; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public Date getCompletedAt() { return completedAt; }
    public Date getFailureAt() { return failureAt; }
    public Date getCreatedAt() { return createdAt; }
} 