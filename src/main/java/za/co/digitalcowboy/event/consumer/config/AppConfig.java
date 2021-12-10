package za.co.digitalcowboy.event.consumer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("za.co.digitalcowboy.kinesis.consumer")
public class AppConfig {
}
