package sharedmobility;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="StockInfo_table")
public class StockInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;
    private String orderId;
    private String rentId;

    @PostPersist
    public void onPostPersist(){
        ReducedStock reducedStock = new ReducedStock();
        BeanUtils.copyProperties(this, reducedStock);
        reducedStock.publishAfterCommit();

        StockIncreased stockIncreased = new StockIncreased();
        BeanUtils.copyProperties(this, stockIncreased);
        stockIncreased.publishAfterCommit();

    }

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
    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }




}
