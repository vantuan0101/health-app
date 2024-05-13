package com.vantuan.authmanagement.repository;

import com.vantuan.authmanagement.criteria.UserCriteria;
import com.vantuan.authmanagement.model.entity.User;
import com.vantuan.framework.crud.respository.BaseDAO;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends BaseDAO<User, Long, UserCriteria> {

}
