package com.excilys.computerdb.fconsigny.service.services;

import com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import com.excilys.computerdb.fconsigny.service.factory.CompanyFactory;

import com.excilys.computerdb.fconsigny.core.model.Company;
import com.excilys.computerdb.fconsigny.persistence.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.persistence.entity.EntityCompany;

import com.excilys.computerdb.fconsigny.service.hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServices implements ICompanyServices {

  private final CompanyDao companyDao = CompanyFactory.getCompanyDao() ;
      
  public CompanyServices(){

  }

  public Company getUniqueCompany(final long id) throws ServiceException {
    Session session = HibernateUtil.getSessionFactory().openSession();
    EntityCompany entiyCompany =  companyDao.findById(session, id);
    return new Company((long)entiyCompany.getId(),entiyCompany.getName());
  }

  public List<Company> getAllCompanies() throws ServiceException {
    
    List<Company> companyList = new ArrayList<Company>(); 
    Session session =  HibernateUtil.getSessionFactory().openSession();
    List<EntityCompany> entityCompany =  companyDao.findAll(session);
    
    for(EntityCompany entity : entityCompany){
      System.out.println(" ID : " + entity.getId() + " - " + " name : " + " " + entity.getName());
      companyList.add(new Company((long)entity.getId(),entity.getName()));
    }
    
    return companyList;
  }
  
}
