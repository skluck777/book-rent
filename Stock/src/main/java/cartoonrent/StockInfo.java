package cartoonrent;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="StockInfo_table")
public class StockInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long stockId;
    private Long orderId;
    private Long rentId;
    private Long stockAmt;

    // 엔티티 저장 후 발동
    @PostPersist
    public void onPostPersist(){
        if(this.stockAmt == -1){
            StockReduced stockReduced = new StockReduced();
            BeanUtils.copyProperties(this, stockReduced);
            stockReduced.publishAfterCommit();
        }else if(this.stockAmt == 1){
            StockIncreased stockIncreased = new StockIncreased();
            BeanUtils.copyProperties(this, stockIncreased);
            stockIncreased.publishAfterCommit();
        }
    }

     public Long getStockId() {
        return stockId;
    }
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getRentId() {
        return rentId;
    }
    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }
    public Long getStockAmt() {
        return stockAmt;
    }
    public void setStockAmt(Long stockAmt) {
        this.stockAmt = stockAmt;
    }
}
