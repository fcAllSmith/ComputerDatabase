package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.storage.entity.EntityCompany;

@Repository
public interface CompanyDao {

  public EntityCompany findById(Session session, final long id);

  public List<EntityCompany> findAll(Session session);

}
