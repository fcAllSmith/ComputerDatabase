package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.business.model.Company;

@Repository
public interface CompanyDao {

  public Company findById(JdbcTemplate jdbcTemplate, final long id);

  public List<Company> findAll(JdbcTemplate jdbcTemplate);

}
