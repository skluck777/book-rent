
package sharedmobility;

public class Canceled extends AbstractEvent {

    private Long id;
    private String status;
    private String cancelDate;
    private String customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderState() {
        return status;
    }

    public void setOrderState(String status) {
        this.status = status;
    }
    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

