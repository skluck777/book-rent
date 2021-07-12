package sharedmobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sharedmobility.external.PaymentInfo;
import sharedmobility.external.PaymentInfoService;

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
        
        Long orderId = Long.parseLong(param.get("orderId"));                                // API 호출 시 필수 입력 값
        Long customerId = Long.parseLong(param.get("customerId"));                          // API 호출 시 필수 입력 값
        Long time = Long.parseLong(param.get("time") == null ? "1" : param.get("time"));    // time은 옵션 값임 줘도되고 안줘도되고

        orderinfo.setOrderId(orderId);          // OrderId 입력
        orderinfo.setOrderDate(today);          // 주문 시간 입력
        orderinfo.setOrderStatus("USE");        // 주문 상태 변경
        orderinfo.setCustomerId(customerId);    // 고객ID 입력
        orderinfo.setTime(time);                // 이용 시간 입력
        orderinfo.setPrice(time*1000);          // 시간 당 천원 입력
        
        // 결제 진행
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(orderId);
        paymentInfo.setPrice(time*1000);
        paymentInfo.setCustomerId(customerId);

        OrderApplication.applicationContext.getBean(PaymentInfoService.class)
            .pay(paymentInfo);

        try {
            orderinfo = orderInfoRepository.save(orderinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderinfo;
    }

    // 주문 취소
    @PutMapping(value = "/order/cancel/{id}")
    public OrderInfo cancelOrder(@PathVariable String id) {
        return this.updateOrder(id, "CANCEL");
    }

    // 반납 주문
    @PutMapping(value = "/order/return/{id}")
    public OrderInfo returnOrder(@PathVariable String id) {
        return this.updateOrder(id, "RETURN");
    }

    // 주문 상태 변경
    @PatchMapping(value = "/order/update/{id}")
    public OrderInfo updateOrder(@PathVariable String id, @PathVariable String status) {
        OrderInfo orderInfo = null;
        boolean isFind = false;

        List<OrderInfo> paymentList = orderInfoRepository.findByOrderId(Long.parseLong(id));

        for (OrderInfo o : paymentList) {
            if("USE".equals(o.getOrderStatus())){
                o.setOrderStatus(status);

                orderInfo = orderInfoRepository.save(o);    // 저장
                isFind = true;
            }
        }

        return isFind ? orderInfo : null;
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