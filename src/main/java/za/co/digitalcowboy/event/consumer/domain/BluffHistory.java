package za.co.digitalcowboy.event.consumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class BluffHistory {

    private int bluffHistoryId;
    private int userId;
    private String angryScore;
    private String joyScore;
    private String sorrowScore;
    private String surprisedScore;
    private String dateAdded;
    private String totalWeighting;
    private String imageUrl;
}
