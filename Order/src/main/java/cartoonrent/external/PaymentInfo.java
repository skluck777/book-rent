package cartoonrent.external;

public class PaymentInfo {

    private Long payId;
    private Long orderId;
    private Long price;
    private String payDate;
    private String payStatus;
    private String payCancelDate;
    private Long customerId;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
    public String getPayCancelDate() {
        return payCancelDate;
    }

    public void setPayCancelDate(String payCancelDate) {
        this.payCancelDate = payCancelDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
