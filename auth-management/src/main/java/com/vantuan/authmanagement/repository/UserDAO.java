package com.vantuan.authmanagement.repository;

import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.model.entity.User;
import org.springframework.stereotype.Component;
import com.vantuan.crud.respository.BaseDAO;

@Component
public class UserDAO extends BaseDAO<User, Long, UserCriteria> {

}
