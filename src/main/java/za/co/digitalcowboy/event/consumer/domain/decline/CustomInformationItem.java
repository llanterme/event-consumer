package za.co.digitalcowboy.event.consumer.domain.decline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomInformationItem{

	@JsonProperty("customFieldSetGroupIndex")
	private Object customFieldSetGroupIndex;

	@JsonProperty("customFieldID")
	private String customFieldID;

	@JsonProperty("value")
	private String value;

	public void setCustomFieldSetGroupIndex(Object customFieldSetGroupIndex){
		this.customFieldSetGroupIndex = customFieldSetGroupIndex;
	}

	public Object getCustomFieldSetGroupIndex(){
		return customFieldSetGroupIndex;
	}

	public void setCustomFieldID(String customFieldID){
		this.customFieldID = customFieldID;
	}

	public String getCustomFieldID(){
		return customFieldID;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"CustomInformationItem{" + 
			"customFieldSetGroupIndex = '" + customFieldSetGroupIndex + '\'' + 
			",customFieldID = '" + customFieldID + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}