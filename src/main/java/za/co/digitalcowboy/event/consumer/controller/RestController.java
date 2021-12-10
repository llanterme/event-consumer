package za.co.digitalcowboy.event.consumer.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.prowidesoftware.swift.model.SwiftBlock3;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.io.IOUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.*;
import za.co.digitalcowboy.event.consumer.config.AWSConfig;
import za.co.digitalcowboy.event.consumer.domain.User;
import za.co.digitalcowboy.event.consumer.domain.UserRegister;
import za.co.digitalcowboy.event.consumer.utils.ConversionUtil;
import za.co.digitalcowboy.event.consumer.utils.CoreLog;
import za.co.digitalcowboy.event.consumer.utils.Utils;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {

    private AWSConfig awsConfig;

    @Value("${s3.bucketContextUrl}")
    private String bucketUrlContext;

    @Value("${cloud.aws.region.static}")
    private String region;


    private static final String QUEUE = "digital-cowboy-sqs-queue";
    private static final String CITY_QUEUE = "sqs-city-queue";
    private static final String BUCKET_NAME = "digital-cowboy-s3";

    @Autowired
    public RestController(AWSConfig awsConfig) {
        this.awsConfig = awsConfig;
    }


    @RequestMapping(path = "/health", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHealthz() {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity hello() {

        return ResponseEntity.status(HttpStatus.OK).body("Hello");
    }

    @PostMapping(value = "/receive-register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SqsListener(value = CITY_QUEUE, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    @ResponseStatus(HttpStatus.CREATED)
    public void receiveNewUser(@Headers Map<String, String> headers, String message) throws Exception {
        CoreLog coreLog = new CoreLog();
        log.info(coreLog.info("Received SQS message Metadata", message));

        final DozerBeanMapper mapper = new DozerBeanMapper();
        final User user = mapper.map(headers, User.class);

        publishToRegisterSns(user);


    }


    @PostMapping(value = "/receive", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SqsListener(value = QUEUE, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    @ResponseStatus(HttpStatus.CREATED)
    public void receiveSettlements(@Valid String message, @Header("MessageId") String messageId) throws Exception {
        CoreLog coreLog = new CoreLog();
        log.info(coreLog.start("Received message with message ID", messageId));
        log.info(coreLog.info("Received SQS message Metadata", message));
        S3Object s3Object = getS3ObjectFromSqsMessage(message);
        String s3FileName = s3Object.getKey();
        log.info(coreLog.start("Got file name", s3FileName));

        UserRegister userRegister = ConversionUtil.getFileJson(s3Object, UserRegister.class);
        log.info(coreLog.start("SQS Deserialized Object", userRegister.getEmailAddress()));

        Gson gson = new Gson();
        publishToSns(gson.toJson(userRegister));

    }


    @GetMapping(value = "/searchbucket/{fileName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> searchBucket(@PathVariable String fileName) throws Exception {

        S3Object fullObject = getFulls3Object(fileName);
       String uetr = getUetr(fullObject);

        return ResponseEntity.status(HttpStatus.OK).body(uetr);
    }

    public S3Object getFulls3Object(String fileName) {

        AmazonS3 amazonS3 = awsConfig.getAmazonS3Client();
        var keyLower = fileName.toLowerCase();
        var keys = new ArrayList<S3ObjectSummary>();
        ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(BUCKET_NAME);
        ListObjectsV2Result result;
        try {
            do {
                result = amazonS3.listObjectsV2(request);
                result.getObjectSummaries()
                        .stream()
                        .filter(obj -> obj.getKey().toLowerCase().contains(keyLower))
                        .forEach(keys::add);
                var token = result.getContinuationToken();
                request.setContinuationToken(token);

                S3Object fullObject = amazonS3.getObject(new GetObjectRequest("digital-cowboy-s3", keys.get(0).getKey()));

                return fullObject;
            } while (result.isTruncated());
        } catch (AmazonS3Exception e) {
            System.out.println((e.getAdditionalDetails().entrySet().toString()));
            throw e;
        }

    }

    public S3Object getS3ObjectFromSqsMessage(String message) throws IOException {
        CoreLog coreLog = new CoreLog();
        log.info(coreLog.start("getS3Details", message));
        ObjectMapper objectMapper = Utils.getObjectMapper();
        JsonNode rootNode = objectMapper.readTree(message.toString());
        String bucketName = rootNode.path("Records").get(0).path("s3").path("bucket").path("name").asText();
        String fileName = rootNode.path("Records").get(0).path("s3").path("object").path("key").asText();
        log.info(coreLog.info("bucknetName", bucketName));
        log.info(coreLog.info("fileName", fileName));
        AmazonS3 amazonS3 = awsConfig.getAmazonS3Client();
      //  log.info(coreLog.info("s3object Metadata", amazonS3));
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        log.info(coreLog.info("s3object content", s3Object.getObjectContent().toString()));
        return s3Object;
    }


    private void publishToRegisterSns(User user) {

        try {
            ObjectMapper objectMapper = Utils.getObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(user);
            PublishResult result = awsConfig.getAmazonSNSClient().publish("arn:aws:sns:us-east-2:634182598822:sns-user-register", jsonStr);
            System.out.printf(result.getMessageId());

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }

    private void publishToSns(String jsonFileString) {

        try {
            ObjectMapper objectMapper = Utils.getObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(jsonFileString);
            PublishResult result = awsConfig.getAmazonSNSClient().publish("arn:aws:sns:us-east-2:634182598822:digital-cowboy-sms-topic", jsonStr);
            System.out.printf(result.getMessageId());

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }

    private String getUetr(S3Object fullObject) throws Exception {
        try {

            String mt103String = IOUtils.toString(fullObject.getObjectContent());

            MT103 mt = MT103.parse(mt103String);
            
            SwiftMessage swiftMessage = mt.getSwiftMessage();

            swiftMessage = SwiftMessage.parse(swiftMessage.getUnparsedTexts().getAsFINString());

            SwiftBlock3 swiftBlock3 = swiftMessage.getBlock3();
            com.prowidesoftware.swift.model.Tag tag4 = swiftBlock3.getTag(4);
            Field tag4Field = tag4.asField();
            String uetr = tag4Field.getComponent(1);

            return uetr;


        } catch (Exception e) {
            throw new Exception("unable to get uetr");
        }

    }
}
