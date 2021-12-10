//package za.co.digitalcowboy.kinesis.consumer.messaging;
//
//import com.google.gson.Gson;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import za.co.digitalcowboy.kinesis.consumer.domain.User;
//import za.co.digitalcowboy.kinesis.consumer.domain.decline.TransactionDeclinedDto;
//
//@Component
//public class Processor {
//
//    @StreamListener(Streams.MASTER_USER_UPDATED_CONSUME_CHANNEL)
//    @Transactional
//    public void listenForMasterUserUpdates(@Payload User user) {
//
//        try {
//
//            Gson gson = new Gson();
//            System.out.println("Listend from Kinesis" + gson.toJson(user));
//
//        } catch (Exception e) {
//            System.out.println("Error " + e.getMessage());
//        }
//    }
//
//
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
