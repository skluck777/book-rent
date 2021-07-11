package sharedmobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class OrderInfoController {

    @Autowired
    OrderInfoRepository orderInfoRepository;

    // 사용 주문
    @PostMapping(value = "/order")
    public OrderInfo useOrder(@RequestBody Map<String, String> param) {

        OrderInfo orderinfo = new OrderInfo();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String today =  sdf.format(timestamp);
        
        Long customerId = Long.parseLong(param.get("customerId"));
        Long time = Long.parseLong(param.get("time") == null ? "1" : param.get("time"));

        orderinfo.setOrderDate(today);
        orderinfo.setOrderStatus("USE");
        orderinfo.setCustomerId(customerId);
        orderinfo.setTime(time);
        orderinfo.setPrice(time*1000);   // 시간 당 천원

        // TODO : 렌트 가능한 지 확인 필요
        System.out.println("Stock 호출해서 잔여 재고가 있는 지 확인 필요");

        try {
            orderinfo = orderInfoRepository.save(orderinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderinfo;
    }

    // 주문 취소
    @PatchMapping(value = "/order/cancel/{id}")
    public OrderInfo cancelOrder(@PathVariable String id) {
        return this.updateOrder(id, "CANCEL");
    }

    // 반납 주문
    @PatchMapping(value = "/order/return/{id}")
    public OrderInfo returnOrder(@PathVariable String id) {
        return this.updateOrder(id, "RETURN");
    }

    // 주문 상태 변경
    @PatchMapping(value = "/order/update/{id}")
    public OrderInfo updateOrder(@PathVariable String id, @PathVariable String status) {
        OrderInfo orderInfo = null;
        List<OrderInfo> paymentList = orderInfoRepository.findByOrderId(Long.parseLong(id));

        for (OrderInfo o : paymentList) {
            o.setOrderStatus(status);

            orderInfo = orderInfoRepository.save(o);    // 저장
        }

        return orderInfo;
    }

    // 주문 확인
    @GetMapping(value = "/order/{id}")
    public OrderInfo getOrderById(@PathVariable String id) {

        Optional<OrderInfo> opt = orderInfoRepository.findById(Long.parseLong(id));
        OrderInfo orderInfo = null;

        if (opt.isPresent())
            orderInfo = opt.get();

        return orderInfo;
    }

    // 전체 주문 리스트 확인
    @GetMapping(value = "/order")
    public Iterable<OrderInfo> getOrderList() {

        Iterable<OrderInfo> iter = orderInfoRepository.findAll();

        return iter;
    }
}