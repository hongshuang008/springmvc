package com.dreamchain.skeleton.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table
public class Person implements Serializable {

	private static final long serialVersionUID = 524241311458949200L;
	@Id
	private long phone;
	@NotEmpty
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像url
	 */
	private String head_portrait;
	/**
	 * 问题1：本人名称
	 */
	@NotEmpty
	private String key1;
	/**
	 * 问题2：出生年月日
	 */
	@NotEmpty
	private String key2;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER ,cascade=CascadeType.ALL,mappedBy="userId")    
	private Set<Friend> friends; 
	
	
	
	
	
	
	
	
	
	public Set<Friend> getFriends() {
		return friends;
	}
	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getHead_portrait() {
		return head_portrait;
	}
	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	
	
}
