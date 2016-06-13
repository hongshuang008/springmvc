package com.dreamchain.skeleton.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dreamchain.skeleton.base.BaseService;
import com.dreamchain.skeleton.base.baseInterface.DaoInterface;
import com.dreamchain.skeleton.dao.impl.FriendDaoImpl;
import com.dreamchain.skeleton.dao.impl.PMIDaoImpl;
import com.dreamchain.skeleton.dao.impl.PersonDaoImpl;
import com.dreamchain.skeleton.model.Friend;
import com.dreamchain.skeleton.model.PMIData;
import com.dreamchain.skeleton.model.Person;
import com.dreamchain.skeleton.util.commonUtils;

@Service
public class PersonServiceImpl extends BaseService<Person,Long>{

	@Autowired
	private PersonDaoImpl personDao;
	@Autowired
	private FriendDaoImpl friendDao;
	@Autowired
	public PersonServiceImpl(PersonDaoImpl dao) {
		super(dao);
	}

	@Transactional
	public void updatePassword(long phone, String password, String key1, String key2) throws Exception {
		Person person = personDao.findById(phone);
		if(person==null){
			throw new Exception("用户不存在");
		}
		if(!person.getKey1().equals(key1)){
			throw new Exception("key1错误");
		}
		if(!person.getKey2().equals(key2)){
			throw new Exception("key2错误");
		}
		person.setPassword(password);
		personDao.update(person);
	}
	
	public Map login(long phone, String password) throws Exception {
		Person person = personDao.findById(phone);
		if(person==null){
			throw new Exception("账号或密码错误");
		}
		if(!person.getPassword().equals(password)){
			throw new Exception("账号或密码错误");
		}
		Map result = new HashMap<String, Object>();
		result.put("nickname", person.getNickname());
		result.put("head_portrait", person.getHead_portrait());
		return result;
	}
	
	/**
	 * 添加好友
	 * @param phone
	 * @return 
	 * @throws Exception 
	 */
	@Transactional
	public Map addFriend(long userphone,long friendPhone) throws Exception {
		Person user =  findById(userphone);
		Person friend =  findById(friendPhone);
		
		if(user==null){
			throw new Exception("用户不存在");
		}
		if(friend==null){
			throw new Exception("好友用户不存在");
		}
		
		Map param = new HashMap<String,Object>();
		param.put("userId", user.getPhone());
		param.put("friend", friend);
		List<Friend> friends = friendDao.findByProperties(param);
		
		if(friends.size()>0){
			throw new Exception("该好友已存在，请勿重复添加");
		}
		
		Friend f = new Friend(userphone,friend);
		
		friendDao.save(f);
		
		Map result = new HashMap<String, Object>();
		result.put("nickname", friend.getNickname());
		result.put("head_portrait", friend.getHead_portrait());
		
		return result;
	}
	@Transactional
	public void delFriend(long userPhone, long friendPhone) throws Exception {
		Person user =  findById(userPhone);
		Person friend =  findById(friendPhone);
		
		if(user==null){
			throw new Exception("用户不存在");
		}
		if(friend==null){
			throw new Exception("好友用户不存在");
		}
		Map param = new HashMap<String,Object>();
		param.put("userId", user.getPhone());
		param.put("friend", friend);
		List<Friend> friends = friendDao.findByProperties(param);
		for (Friend f : friends) {
			//解决关联关系后，再删除
			user.getFriends().remove(f); //在所关联的一方的set中移走当前要删除的对象
			f.setFriend(null);//设置所对应的一方为空，解除它们之间的关系
			friendDao.delete(f);
		}
	}
	
}
