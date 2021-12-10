//package za.co.digitalcowboy.kinesis.consumer.messaging;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.dozer.DozerBeanMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Processor;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.messaging.Message;
//import za.co.digitalcowboy.kinesis.consumer.domain.BluffHistory;
//import za.co.digitalcowboy.kinesis.consumer.domain.User;
//import za.co.digitalcowboy.kinesis.consumer.entity.UserEntity;
//import za.co.digitalcowboy.kinesis.consumer.repository.UserRepository;
//import za.co.digitalcowboy.kinesis.consumer.utils.CoreLog;
//
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@EnableBinding(Sink.class)
//@Slf4j
//public class KinesisConsumerApplication {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @StreamListener(Processor.INPUT)
//    public void input(String message) {
//
//        CoreLog loggerUtil = new CoreLog();
//
//        Gson gson = new Gson();
//        User user = gson.fromJson(message, User.class);
//        user.setDateRegistered(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//
//        log.info(loggerUtil.start("add user to mds ", gson.toJson(user)));
//
//        userRepository.save(new DozerBeanMapper().map(user, UserEntity.class));
//
//    }
//
//    @StreamListener("errorChannel")
//    public void error(Message<?> message) {
//        System.out.println("Handling ERROR: " + message);
//
//    }
//
//
//
//}
