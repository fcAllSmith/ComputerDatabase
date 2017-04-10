package src.main.java.com.excilys.computerdb.fconsigny.persistence.dao;

import java.util.List;

import src.main.java.com.excilys.computerdb.fconsigny.persistence.entity.EntityCompany;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao {

  public EntityCompany findById(Session session, final long id);

  public List<EntityCompany> findAll(Session session);

}
