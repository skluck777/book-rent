package sharedmobility;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="RentInfo_table")
public class RentInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long rentId;
    private Long orderId;
    private String rentDate;
    private String returnDate;
    private String approveDate;
    private String rentStatus;

    @PostUpdate
    public void onPostUpdate(){
        if(this.rentStatus.equals("RETURN")){
            Returned returned = new Returned();
            BeanUtils.copyProperties(this, returned);
            returned.publishAfterCommit();
        }
        if(this.rentStatus.equals("RENT")){
            Rented rented = new Rented();
            BeanUtils.copyProperties(this, rented);
            rented.publishAfterCommit();
        }
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
    public String getRentDate() {
        return rentDate;
    }
    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }
    public String getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRentStatus() {
        return rentStatus;
    }
    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }
    public String getApproveDate() {
        return approveDate;
    }
    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }


}
