package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.CompanyFactory;
import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.storage.datasource.JdbcDataSource;
import com.excilys.computerdb.fconsigny.storage.entity.EntityCompany;
import com.excilys.computerdb.fconsigny.utils.hibernate.HibernateUtil;

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
