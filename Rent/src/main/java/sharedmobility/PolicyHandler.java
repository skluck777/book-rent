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
    @Autowired RentInfoRepository rentInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_Return(@Payload Returned returned){

        if(!returned.validate()) return;

        System.out.println("\n\n##### listener Return : " + returned.toJson() + "\n\n");



        // Sample Logic //
        // RentInfo rentInfo = new RentInfo();
        // rentInfoRepository.save(rentInfo);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_Approve(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener Approve : " + paymentApproved.toJson() + "\n\n");



        // Sample Logic //
        // RentInfo rentInfo = new RentInfo();
        // rentInfoRepository.save(rentInfo);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
