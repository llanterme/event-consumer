package za.co.digitalcowboy.event.consumer.domain.decline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionPayload{

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("notes")
	private String notes;

	@JsonProperty("method")
	private String method;

	@JsonProperty("customInformation")
	private List<CustomInformationItem> customInformation;

	@JsonProperty("type")
	private String type;

	@JsonProperty("currencyCode")
	private String currencyCode;

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setCustomInformation(List<CustomInformationItem> customInformation){
		this.customInformation = customInformation;
	}

	public List<CustomInformationItem> getCustomInformation(){
		return customInformation;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	@Override
 	public String toString(){
		return 
			"TransactionPayload{" + 
			"amount = '" + amount + '\'' + 
			",notes = '" + notes + '\'' + 
			",method = '" + method + '\'' + 
			",customInformation = '" + customInformation + '\'' + 
			",type = '" + type + '\'' + 
			",currencyCode = '" + currencyCode + '\'' + 
			"}";
		}
}