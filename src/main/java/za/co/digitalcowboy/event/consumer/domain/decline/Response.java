package za.co.digitalcowboy.event.consumer.domain.decline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response{

	@JsonProperty("returnCode")
	private int returnCode;

	@JsonProperty("transactionIds")
	private Object transactionIds;

	@JsonProperty("balance")
	private Object balance;

	@JsonProperty("returnStatus")
	private String returnStatus;

	public void setReturnCode(int returnCode){
		this.returnCode = returnCode;
	}

	public int getReturnCode(){
		return returnCode;
	}

	public void setTransactionIds(Object transactionIds){
		this.transactionIds = transactionIds;
	}

	public Object getTransactionIds(){
		return transactionIds;
	}

	public void setBalance(Object balance){
		this.balance = balance;
	}

	public Object getBalance(){
		return balance;
	}

	public void setReturnStatus(String returnStatus){
		this.returnStatus = returnStatus;
	}

	public String getReturnStatus(){
		return returnStatus;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"returnCode = '" + returnCode + '\'' + 
			",transactionIds = '" + transactionIds + '\'' + 
			",balance = '" + balance + '\'' + 
			",returnStatus = '" + returnStatus + '\'' + 
			"}";
		}
}