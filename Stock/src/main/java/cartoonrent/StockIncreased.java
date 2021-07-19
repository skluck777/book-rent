package cartoonrent;

public class StockIncreased extends AbstractEvent {

    private Long stockId;
    private Long stockAmt;
    private Long orderId;

    public StockIncreased(){
        super();
    }

    public Long getStockId() {
        return stockId;
    }
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
    public Long getStockAmt() {
        return stockAmt;
    }
    public void setStockAmt(Long stockAmt) {
        this.stockAmt = stockAmt;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
