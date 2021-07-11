package sharedmobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

 @RestController
 public class PaymentInfoController {

    @Autowired
    PaymentInfoRepository paymentInfoRepository;

   // 결제
   @PostMapping(value = "/payment")
   public boolean pay(@RequestBody PaymentInfo param) {

       PaymentInfo paymentInfo = new PaymentInfo();
       boolean result = false;

       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
       String today =  sdf.format(timestamp);

       paymentInfo.setPayDate(today);
       paymentInfo.setPayStatus("PAIED");
       paymentInfo.setCustomerId(param.getCustomerId());
       paymentInfo.setPrice(param.getPrice());
       paymentInfo.setOrderId(param.getOrderId());

       try {
        paymentInfo = paymentInfoRepository.save(paymentInfo);
           result = true;
       } catch (Exception e) {
           e.printStackTrace();
       }

       return result;
   }
   
       // 결제 취소
       @PatchMapping(value = "/payment/cancel/{id}")
       public PaymentInfo cancelPayment(@PathVariable String id) {
           return this.updatePayment(id, "CANCEL");
       }

       // 결제 상태 변경
       @PatchMapping(value = "/payment/update/{payId}")
       public PaymentInfo updatePayment(@PathVariable String id, @PathVariable String status) {
           PaymentInfo PaymentInfo = null;
           List<PaymentInfo> paymentList = paymentInfoRepository.findByPayId(Long.parseLong(id));
   
           for (PaymentInfo o : paymentList) {
               o.setPayStatus(status);
   
               PaymentInfo = paymentInfoRepository.save(o);    // 저장
           }
   
           return PaymentInfo;
       }
   
       // 결제 확인
       @GetMapping(value = "/payment/{id}")
       public PaymentInfo getPaymentById(@PathVariable String id) {
   
           Optional<PaymentInfo> opt = paymentInfoRepository.findById(Long.parseLong(id));
           PaymentInfo PaymentInfo = null;
   
           if (opt.isPresent())
               PaymentInfo = opt.get();
   
           return PaymentInfo;
       }
   
       // 전체 결제 리스트 확인
       @GetMapping(value = "/payment")
       public Iterable<PaymentInfo> getPaymentList() {
   
           Iterable<PaymentInfo> iter = paymentInfoRepository.findAll();
   
           return iter;
       }
 }
