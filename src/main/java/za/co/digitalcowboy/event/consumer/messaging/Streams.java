//package za.co.digitalcowboy.kinesis.consumer.messaging;
//
//
//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.messaging.SubscribableChannel;
//
//public interface Streams {
//
//    String MASTER_USER_UPDATED_CONSUME_CHANNEL = "master-user-updated-channel";
//
//    @Input(MASTER_USER_UPDATED_CONSUME_CHANNEL)
//    SubscribableChannel getMasterUserUpdates();
//
//
//
//}