package sharedmobility;

import sharedmobility.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentInfoRepository paymentInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCanceled_PaymentCancel(@Payload Canceled canceled){

        if(!canceled.validate()) return;

        System.out.println("\n\n##### listener PaymentCancel : " + canceled.toJson() + "\n\n");



        // Sample Logic //
        // PaymentInfo paymentInfo = new PaymentInfo();
        // paymentInfoRepository.save(paymentInfo);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
