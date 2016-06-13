package com.dreamchain.skeleton.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dreamchain.skeleton.dao.impl.PersonDaoImpl;
import com.dreamchain.skeleton.service.impl.PersonServiceImpl;

public class PMIControllerTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		try {
			PersonDaoImpl dao = new PersonDaoImpl();
			PersonServiceImpl personServiceImpl = new PersonServiceImpl(dao);
			personServiceImpl.updatePassword(13267957035l, "0000", "吴洪双", "20151225");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
