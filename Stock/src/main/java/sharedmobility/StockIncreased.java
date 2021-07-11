package sharedmobility;

public class StockIncreased extends AbstractEvent {

    private Long stockId;
    private Long stockAmt;

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
}
