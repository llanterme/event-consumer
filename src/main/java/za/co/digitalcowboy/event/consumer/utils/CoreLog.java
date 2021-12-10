package za.co.digitalcowboy.event.consumer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CoreLog {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private long startTime = System.nanoTime();

    public CoreLog() {
    }

    public String start() {
        return this.start((String)null, (Object)null);
    }

    public String start(String description, Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("corelog started ");
        this.appendObjectToStringBuilder(stringBuilder, description, object);
        return stringBuilder.toString();
    }

    public String info(String message) {
        long infoTime = System.nanoTime();
        long infoDuration = infoTime - this.startTime;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("corelog info at ");
        stringBuilder.append((double)infoDuration / 1000000.0D);
        stringBuilder.append(" milliseconds ");
        stringBuilder.append(message);
        return stringBuilder.toString();
    }

    public String info(String description, Object object) {
        long infoTime = System.nanoTime();
        long infoDuration = infoTime - this.startTime;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("corelog info at ");
        stringBuilder.append((double)infoDuration / 1000000.0D);
        stringBuilder.append(" milliseconds ");
        this.appendObjectToStringBuilder(stringBuilder, description, object);
        return stringBuilder.toString();
    }

    public String error(String description, Object object) {
        long errorTime = System.nanoTime();
        long errorDuration = errorTime - this.startTime;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("corelog error at ");
        stringBuilder.append((double)errorDuration / 1000000.0D);
        stringBuilder.append(" milliseconds ");
        this.appendObjectToStringBuilder(stringBuilder, description, object);
        return stringBuilder.toString();
    }

    public String error(String description) {
        long errorTime = System.nanoTime();
        long errorDuration = errorTime - this.startTime;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("corelog error at ");
        stringBuilder.append((double)errorDuration / 1000000.0D);
        stringBuilder.append(" milliseconds ");
        stringBuilder.append(description);
        return stringBuilder.toString();
    }

    public String warn(String warning) {
        long warnTime = System.nanoTime();
        long warnDuration = warnTime - this.startTime;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("corelog warn at ");
        stringBuilder.append((double)warnDuration / 1000000.0D);
        stringBuilder.append(" milliseconds ");
        stringBuilder.append(warning);
        return stringBuilder.toString();
    }

    public String end() {
        return this.end((String)null, (Object)null);
    }

    public String end(String description) {
        return this.end(description, (Object)null);
    }

    public String end(String description, Object object) {
        long endTime = System.nanoTime();
        long duration = endTime - this.startTime;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("corelog ended in ");
        stringBuilder.append((double)duration / 1000000.0D);
        stringBuilder.append(" milliseconds ");
        this.appendObjectToStringBuilder(stringBuilder, description, object);
        return stringBuilder.toString();
    }

    private void appendObjectToStringBuilder(StringBuilder stringBuilder, String description, Object object) {
        if (object != null) {
            stringBuilder.append("with ");
        }

        if (description != null) {
            stringBuilder.append(description);
        }

        if (description != null && object != null) {
            stringBuilder.append(" = ");
        }

        String jsonString = "";
        if (object != null) {
            try {
                jsonString = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException var6) {
                var6.printStackTrace();
            }

            jsonString = jsonString.substring(0, Math.min(9000, jsonString.length()));
            stringBuilder.append(jsonString);
        }

    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


}
