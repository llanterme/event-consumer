package za.co.digitalcowboy.event.consumer.utils;

import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class ConversionUtil {

    private static <T> T loadFileFromString(String fileName, Class<T> fileType) {
        ObjectMapper mapper = Utils.getObjectMapper();
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            return mapper.readValue(fileName.getBytes(), fileType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getFileJson(S3Object s3Object, Class<T> fileType) {
        CoreLog coreLog = new CoreLog();
        coreLog.start("getGlFileJson", s3Object.getKey());
        InputStream inputStream = s3Object.getObjectContent();
        String jsonMapMessage =
                new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        log.info(coreLog.info(jsonMapMessage+"jsonMapMessage"));

        return loadFileFromString(jsonMapMessage, fileType);
    }
}
