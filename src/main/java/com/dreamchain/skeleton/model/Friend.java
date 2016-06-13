package com.dreamchain.skeleton.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Friend implements Serializable {

	private static final long serialVersionUID = 6675030823180035045L;
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private long userId;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})         
	@JoinColumn(name="friendid") 
	private Person friend;

	public Friend(long userId,Person friend){
		this.setUserId(userId);
		this.setFriend(friend);
	}
	public Friend(){
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Person getFriend() {
		return friend;
	}

	public void setFriend(Person friend) {
		this.friend = friend;
	}
	
	
	
}
