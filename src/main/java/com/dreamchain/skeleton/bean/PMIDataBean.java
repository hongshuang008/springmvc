package com.dreamchain.skeleton.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.dreamchain.skeleton.model.PMIData;


public class PMIDataBean{

	private long phone;
	
	private String province;

	private String city;
	
	private String district;
	
	/**
	 * 温度
	 */
	private String temp;
	
	/**
	 * 湿度
	 */
	private String humidity;
	
	private String pm1;
	
	private String pm2_5;
	
	private String pm10;
	
	private String voc;
	
	private String createTime;
	
	
	public PMIData bean2model() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PMIData data = new PMIData();
		if(createTime!=null&&!createTime.isEmpty()){
			data.setCreateTime(dateFormat.parse(createTime));
		}
		data.setCity(city);
		data.setDistrict(district);
		data.setHumidity(humidity);
		data.setPhone(phone);
		data.setPm1(pm1);
		data.setPm10(pm10);
		data.setPm2_5(pm2_5);
		data.setProvince(province);
		data.setTemp(temp);
		data.setVoc(voc);
		return data;
	}
	
	
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
