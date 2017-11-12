package com.zipcodeapi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the zip response data for the zipcode api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
public class ZipResponse{

	@JsonProperty("zip_codes")
	private List<ZipCodesItem> zipCodes;

	/**
	 * Sets the local zipCodes variable
	 *
	 * @param zipCodes the value to set the local zipCodes variable
	 */
	public void setZipCodes(List<ZipCodesItem> zipCodes){
		this.zipCodes = zipCodes;
	}

	/**
	 * Gets the local zipCodes variable
	 *
	 * @return the local zipCodes variable
	 */
	public List<ZipCodesItem> getZipCodes(){
		return zipCodes;
	}

	/**
	 * Returns the local variables into a String value
	 *
	 * @return the String value of all local variables
	 */
	@Override
 	public String toString(){
		return 
			"ZipResponse{" + 
			"zip_codes = '" + zipCodes + '\'' + 
			"}";
		}
}