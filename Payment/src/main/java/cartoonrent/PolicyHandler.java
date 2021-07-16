package cartoonrent;

import cartoonrent.config.kafka.KafkaProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentInfoRepository paymentInfoRepository;

    // 주문취소(Canceled) 이벤트 수신
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCanceled_PaymentCancel(@Payload Canceled canceled){

        if(!canceled.validate()) return;

        System.out.println("\n\n##### listener PaymentCancel : " + canceled.toJson() + "\n\n");

        // 오더 취소 시
        PaymentInfo paymentInfo = paymentInfoRepository.findById(canceled.getOrderId()).get();
        if(paymentInfo != null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            paymentInfo.setPayCancelDate(sdf.format(timestamp));
            paymentInfo.setPayStatus("CANCEL");
            paymentInfoRepository.save(paymentInfo);
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
