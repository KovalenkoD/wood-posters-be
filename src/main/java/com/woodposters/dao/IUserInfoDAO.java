package com.woodposters.dao;
import com.woodposters.entity.User;
public interface IUserInfoDAO {
	User getActiveUser(String userName);
}