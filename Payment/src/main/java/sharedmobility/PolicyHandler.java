package sharedmobility;

import sharedmobility.config.kafka.KafkaProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        paymentInfo.setPayDate(sdf.format(timestamp));
        paymentInfo.setPayStatus("payCanceled");
        paymentInfoRepository.save(paymentInfo);
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
