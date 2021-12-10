package za.co.digitalcowboy.event.consumer.domain.decline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDeclinedDto {

	public TransactionDeclinedDto() {
	}

	@JsonProperty("accountId")
	private String accountId;

	@JsonProperty("response")
	private Response response;

	@JsonProperty("transactionPayload")
	private TransactionPayload transactionPayload;

	@JsonProperty("txn")
	private String txn;

	public void setAccountId(String accountId){
		this.accountId = accountId;
	}

	public String getAccountId(){
		return accountId;
	}

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	public void setTransactionPayload(TransactionPayload transactionPayload){
		this.transactionPayload = transactionPayload;
	}

	public TransactionPayload getTransactionPayload(){
		return transactionPayload;
	}

	public void setTxn(String txn){
		this.txn = txn;
	}

	public String getTxn(){
		return txn;
	}

	@Override
 	public String toString(){
		return 
			"TransactionDeclinedDto{" + 
			"accountId = '" + accountId + '\'' + 
			",response = '" + response + '\'' + 
			",transactionPayload = '" + transactionPayload + '\'' + 
			",txn = '" + txn + '\'' + 
			"}";
		}
}