
package sharedmobility;

public class PaymentApproved extends AbstractEvent {

    private String id;
    private String orderId;
    private Long price;
    private String status;
    private String payDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public String getPaymentState() {
        return status;
    }

    public void setPaymentState(String status) {
        this.status = status;
    }
    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}

