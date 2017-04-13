package com.excilys.computerdb.fconsigny.persistence.dao;

import org.hibernate.Session;

@Repository
public class UserDaoImpl {
	
	public User findByUserName(Session session , String username) {

        List<User> users = new ArrayList<User>();

        users = session.createQuery("from User where username=?")
        		.setParameter(0, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
