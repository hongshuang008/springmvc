package com.dreamchain.skeleton.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class PMIData implements Serializable {

	private static final long serialVersionUID = -4060739788760795254L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private long phone;
	
	private String province;

	private String city;
	
	private String district;
	
	
	/**
	 * 温度
	 */
	@NotNull
	private String temp;
	
	/**
	 * 湿度
	 */
	@NotNull
	private String humidity;
	
	@NotNull
	private String pm1;
	
	@NotNull
	private String pm2_5;
	
	@NotNull
	private String pm10;
	
	@NotNull
	private String voc;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	
	
	
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPm1() {
		return pm1;
	}
	public void setPm1(String pm1) {
		this.pm1 = pm1;
	}
	public String getPm2_5() {
		return pm2_5;
	}
	public void setPm2_5(String pm2_5) {
		this.pm2_5 = pm2_5;
	}
	public String getPm10() {
		return pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	public String getVoc() {
		return voc;
	}
	public void setVoc(String voc) {
		this.voc = voc;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	
	
}
