package za.co.digitalcowboy.event.consumer.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
	ignoreUnknown = true
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister{
	private String emailAddress;
	private String name;
	private String socialNetwork;
	private String dateRegistered;


}
