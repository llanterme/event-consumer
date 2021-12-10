package za.co.digitalcowboy.event.consumer;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.var;
import za.co.digitalcowboy.event.consumer.config.AWSConfig;

import java.util.ArrayList;
import java.util.List;

public class Runner {



    public static void main(String[] args) {

        try {
            List<S3ObjectSummary> result = execute("event.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<S3ObjectSummary> execute(String key) {

        AWSConfig awsConfig = new AWSConfig();
        AmazonS3 amazonS3 = awsConfig.getAmazonS3Client();
        var keyLower = key.toLowerCase();
        var keys = new ArrayList<S3ObjectSummary>();
        ListObjectsV2Request request = new ListObjectsV2Request().withBucketName("digital-cowboy-s3");
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
            } while (result.isTruncated());
        } catch (AmazonS3Exception e) {
            // LOG.error(e.getAdditionalDetails().entrySet().toString());
            throw e;
        }
        return keys;
    }
}
