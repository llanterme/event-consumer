package za.co.digitalcowboy.event.consumer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Version {

    private String message;
    private String serviceVersion;
}
