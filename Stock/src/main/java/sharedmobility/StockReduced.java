package sharedmobility;

public class StockReduced extends AbstractEvent {

    private Long stockId;
    private Long stockAmt;

    public StockReduced(){
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
