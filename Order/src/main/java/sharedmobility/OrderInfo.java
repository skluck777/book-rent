package sharedmobility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import sharedmobility.external.PaymentInfo;
import sharedmobility.external.PaymentInfoService;

@Entity
@Table(name="OrderInfo_table")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long customerId;
    private String orderStatus;
    private String orderDate;
    private String cancelDate;
    private String returnDate;
    private Long time;
    private Long price;

    // 해당 엔티티 저장 후
    @PostPersist
    public void onPostPersist(){

        // 사용 주문 들어왔을 경우
        if("USE".equals(this.orderStatus)){
            /*
                Kafka 송출
            */
            Ordered ordered = new Ordered();
            BeanUtils.copyProperties(this, ordered);
            ordered.publish();   // ordered 카프카 송출

            // 결제 진행
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setOrderId(this.orderId);
            paymentInfo.setPrice(this.price);
            paymentInfo.setCustomerId(this.customerId);

            OrderApplication.applicationContext.getBean(PaymentInfoService.class)
                .pay(paymentInfo);
        }
    }

    // 해당 엔티티 업데이트 후
    @PostUpdate
    public void onPostUpdate(){
        if("CANCEL".equals(this.orderStatus)){
            // 주문 상태가 canceled 일 때
            Canceled canceled = new Canceled();
            BeanUtils.copyProperties(this, canceled);
            canceled.publishAfterCommit();
        }else if("RETURN".equals(this.orderStatus)){
            // 주문 상태가 returned 일 때
            OrderReturned returned = new OrderReturned();
            BeanUtils.copyProperties(this, returned);
            returned.publishAfterCommit();
        }
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    } 
}
