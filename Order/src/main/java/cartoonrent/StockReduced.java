
package cartoonrent;

public class StockReduced extends AbstractEvent {

    private Long stockId;
    private Long orderId;
    private Long rentId;
    private Long stockAmt;

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

