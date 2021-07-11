package sharedmobility;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="PaymentInfo_table")
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long payId;
    private Long orderId;
    private Long price;
    private String payDate;
    private String payStatus;
    private String payCancelDate;
    private Long customerId;

    @PostPersist
    public void onPostPersist(){
        // 결제 완료 후 KAFKA 전송
        if(this.payStatus == "PAIED"){
            PaymentApproved paymentApproved = new PaymentApproved();
            BeanUtils.copyProperties(this, paymentApproved);
            paymentApproved.publishAfterCommit();
        }

    }
    @PostUpdate
    public void onPostUpdate(){
        if(this.payStatus == "CANCEL"){
        PaymentCanceled paymentCanceled = new PaymentCanceled();
        BeanUtils.copyProperties(this, paymentCanceled);
        paymentCanceled.publishAfterCommit();
        }
    }

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
