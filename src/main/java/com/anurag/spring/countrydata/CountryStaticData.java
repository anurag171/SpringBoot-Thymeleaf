package com.anurag.spring.countrydata;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Document(collection = "countrystaticdata")
public class CountryStaticData {
	
	@Size(max = 2,message = "cannot be greater than 2")
	@Id
	private String code;
	private String name;
	private String zoneId;
	private String zone;
	private String startDateTime;
	private String endDateTime;
	private String nextBusinessDate;
	private String isBusinessDate;
	private String throttlingAmount;
	private String currency;
	private String zoneOffSet;
	private String timeZone;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the zoneId
	 */
	public String getZoneId() {
		return zoneId;
	}
	/**
	 * @param zoneId the zoneId to set
	 */
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}
	/**
	 * @return the startDateTime
	 */
	public String getStartDateTime() {
		return startDateTime;
	}
	/**
	 * @param startDateTime the startDateTime to set
	 */
	public void setStartDateTime(String startDateTime) {
		if(!StringUtils.isEmpty(startDateTime))
			this.startDateTime = startDateTime;
		else
			this.startDateTime=DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now(ZoneId.systemDefault()));
	}
	/**
	 * @return the endDateTime
	 */
	public String getEndDateTime() {
		return endDateTime;
	}
	/**
	 * @param endDateTime the endDateTime to set
	 */
	public void setEndDateTime(String endDateTime) {
		if(!StringUtils.isEmpty(endDateTime))
			this.endDateTime = endDateTime;
		else
			this.endDateTime=DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now(ZoneId.systemDefault()));
		
	}
	/**
	 * @return the nextBusinessDate
	 */
	public String getNextBusinessDate() {
		return nextBusinessDate;
	}
	/**
	 * @param nextBusinessDate the nextBusinessDate to set
	 */
	public void setNextBusinessDate(String nextBusinessDate) {
		if(!StringUtils.isEmpty(nextBusinessDate))
			this.nextBusinessDate = nextBusinessDate;
		else
			this.nextBusinessDate=DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now(ZoneId.systemDefault()).plusDays(1));
	}
	/**
	 * @return the isBusinessDate
	 */
	public String getIsBusinessDate() {
		return isBusinessDate;
	}
	/**
	 * @param isBusinessDate the isBusinessDate to set
	 */
	public void setIsBusinessDate(String isBusinessDate) {
		this.isBusinessDate = isBusinessDate;
	}
	public String getThrottlingAmount() {
		return throttlingAmount;
	}
	public void setThrottlingAmount(String throttlingAmount) {
		this.throttlingAmount = throttlingAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getZoneOffSet() {
		return zoneOffSet;
	}
	public void setZoneOffSet(String zoneOffSet) {
		this.zoneOffSet = zoneOffSet;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}
