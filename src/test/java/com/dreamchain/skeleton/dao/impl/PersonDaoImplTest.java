package com.dreamchain.skeleton.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dreamchain.skeleton.model.Friend;
import com.dreamchain.skeleton.model.Person;
@Transactional
public class PersonDaoImplTest  extends DaoTest {
	@Autowired FriendDaoImpl friendDaoImpl;
	@Autowired PersonDaoImpl personDaoImpl;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testsaveFriend() {
		long personid = 13267957031l;
		long friendid = 13267957032l;
		Person friend = personDaoImpl.findById(friendid);
		Friend f = new Friend(); 
		f.setUserId(personid);
		f.setFriend(friend);
		long id = friendDaoImpl.save(f);
		Friend  r = friendDaoImpl.findById(id);
		System.out.println(r.getFriend());
	}
	
	@Test
	public void getFriend() {
		int count= personDaoImpl.count();
		System.out.println(count);
		List<Person> ps = personDaoImpl.findAll(count-1, 1);
		System.out.println(ps.get(0));
		
		
	}
	
	
	
	@Test
	public void testDeleteID() {
		long a = 1;
		personDaoImpl.delete(a);
	}

	@Test
	public void testFindByIdID() {
		long a = 1;
		Person p=personDaoImpl.findById(a);
		System.out.println(p.getNickname());
	}

}
