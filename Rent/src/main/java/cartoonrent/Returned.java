package cartoonrent;

public class Returned extends AbstractEvent {

    private Long rentId;
    private Long orderId;
    private String returnDate;
    private String rentStatus;

    public Returned(){
        super();
    }

    public Long getRentId() {
        return rentId;
    }
    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getRentStatus() {
        return rentStatus;
    }
    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }
    public String getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
