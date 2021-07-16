package cartoonrent;

import cartoonrent.config.kafka.KafkaProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired RentInfoRepository rentInfoRepository;

    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_Approve(@Payload PaymentApproved paymentApproved){
        // 유휴 킥보드에 접근하여 해당 Order ID 의 렌트승인 상태로 변경
        // 렌트 승인 상태인 Order Id 는 기기 접근 시 승인 처리됨.
        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener Approve : " + paymentApproved.toJson() + "\n\n");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String today =  sdf.format(timestamp);

        // 결제 승인 시, 렌트 가능한 상태로 변경
        RentInfo rentInfo = new RentInfo(); // 신규 생성
        rentInfo.setOrderId(paymentApproved.getOrderId());  // orderId 저장
        rentInfo.setRentStatus("APPROVE");  // 렌트 상태 저장
        rentInfo.setApproveDate(today);  // 승인 날짜

        rentInfoRepository.save(rentInfo);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_Return(@Payload OrderReturned orderReturned){

        if(!orderReturned.validate()) return;

        System.out.println("\n\n##### listener Return : " + orderReturned.toJson() + "\n\n");

        // 반납 수신 시, 반납 상태로 변경
        RentInfo rentInfo = new RentInfo();
        List<RentInfo> rentList = rentInfoRepository.findByOrderId(orderReturned.getOrderId());

        for (RentInfo o : rentList) {
            rentInfo = o;
        }

        if(rentInfo != null && !"RETURN".equals(rentInfo.getRentStatus())){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            rentInfo.setReturnDate(sdf.format(timestamp));
            rentInfo.setRentStatus("RETURN");
            rentInfoRepository.save(rentInfo);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
