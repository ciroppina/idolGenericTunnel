package it.ciroppina.idol.generic.tunnel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;

import com.autonomy.aci.client.services.AciErrorException;

public class ErrorResponse extends Hit implements Serializable {

	private static final long serialVersionUID = -480502218061470396L;
	
	private HashMap<String, String> dreFields;
	/**
	 * Getters
	 */
	public String getDreField(String key) {
		return dreFields.get(key);
	}
	public List<String> getFieldsList() {
		return new ArrayList<String>(dreFields.keySet());
	}
	
	/**
	 * Constructor
	 * @param dreFields
	 */
	public ErrorResponse(HashMap<String, String> dreFields) {
		super();
		this.dreFields = dreFields == null ? new HashMap<String, String>() : dreFields;
	}
	/**
	 * Constructor
	 * @param dreFields
	 */
	public ErrorResponse() {
		super();
		this.dreFields = new HashMap<String, String>();
	}

	public ErrorResponse(Document d) {
		super();
		this.dreFields = new HashMap<String, String>();
		
		dreFields.put("action", d.getElementsByTagName("action").item(0).getTextContent() );
		dreFields.put("response", d.getElementsByTagName("response").item(0).getTextContent() );
		dreFields.put("errorid", d.getElementsByTagName("errorid").item(0).getTextContent() );
		dreFields.put("rawerrorid", d.getElementsByTagName("rawerrorid").item(0).getTextContent() );
		dreFields.put("errorstring", d.getElementsByTagName("errorstring").item(0).getTextContent() );
		dreFields.put("errordescription", d.getElementsByTagName("errordescription").item(0).getTextContent() );
		dreFields.put("errortime", d.getElementsByTagName("errortime").item(0).getTextContent() );
		dreFields.put("errorcode", d.getElementsByTagName("errorcode").item(0).getTextContent() );
	}
	
	public String getResponse() {
		return "ERROR";
	}
	
	public String getAction() {
		return this.dreFields.get("action") == null ? "n.d." : this.dreFields.get("action");
	}

	public String getErrorId() {
		return this.dreFields.get("errorid") == null ? "n.d." : this.dreFields.get("errorid");
	}

	public String getRawErrorId() {
		return this.dreFields.get("rawerrorid") == null ? "n.d." : this.dreFields.get("rawerrorid");
	}

	public String getErrorString() {
		return this.dreFields.get("errorstring") == null ? "n.d." : this.dreFields.get("errorstring");
	}

	public String getErrorDescription() {
		return this.dreFields.get("errordescription") == null ? "n.d." : this.dreFields.get("errordescription");
	}

	public String getErrorCode() {
		return this.dreFields.get("errorcode") == null ? "n.d." : this.dreFields.get("errorcode");
	}

	public String getErrorTime() {
		return this.dreFields.get("errortime") == null ? "n.d." : this.dreFields.get("errortime");
	}
	
	public void add(String tag, String value) {
		this.dreFields.put(tag, value);
	}

	/**
	 * Utility: get the error in <autnresponse> XML format
	 */
	public String autnResponse (AciErrorException ce) {
		String result = "<autnresponse><action>"+ dreFields.get("action") +"</action>"
				+ "<response>ERROR</response>"
				+ "<responsedata><error>"
				+ "<errorid>" + ce.getErrorId() +"</errorid>"
				+ "<rawerrorid>" + ce.getRawErrorId() +"</rawerrorid>"
				+ "<errorstring>" + ce.getErrorString() +"</errorstring>"
				+ "<errordescription>" + ce.getErrorDescription() +"</errordescription>"
				+ "<errorcode>" + ce.getErrorCode() +"</errorcode>"
				+ "<errortime>" + ce.getErrorTime() +"</errortime>"
				+ "</error></responsedata></autnresponse>";
		return result;
	}

}
