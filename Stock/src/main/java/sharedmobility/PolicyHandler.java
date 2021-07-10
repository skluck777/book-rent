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
    @Autowired StockInfoRepository stockInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRented_StockChange(@Payload Rented rented){

        if(!rented.validate()) return;

        System.out.println("\n\n##### listener StockChange : " + rented.toJson() + "\n\n");



        // Sample Logic //
        // StockInfo stockInfo = new StockInfo();
        // stockInfoRepository.save(stockInfo);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_StockChange(@Payload Returned returned){

        if(!returned.validate()) return;

        System.out.println("\n\n##### listener StockChange : " + returned.toJson() + "\n\n");



        // Sample Logic //
        // StockInfo stockInfo = new StockInfo();
        // stockInfoRepository.save(stockInfo);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
