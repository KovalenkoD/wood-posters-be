package com.woodposters.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.woodposters.entity.User;
@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	public User getActiveUser(String userName) {
		User activeUserInfo = new User();
		short enabled = 1;
		List<?> list = entityManager.createQuery("SELECT u FROM User u WHERE userName=? and enabled=?")
				.setParameter(1, userName).setParameter(2, enabled).getResultList();
		if(!list.isEmpty()) {
			activeUserInfo = (User)list.get(0);
		}
		return activeUserInfo;
	}
}