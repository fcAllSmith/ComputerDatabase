package com.excilys.computerdb.fconsigny.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.persistence.entity.EntityComputer;
import com.excilys.computerdb.fconsigny.persistence.entity.EntityCompany;

import com.excilys.computerdb.fconsigny.core.model.Computer;

@Repository
public class ComputerDaoImpl implements ComputerDao {

	private static final String QUERY_SELECT_ALL = "from EntityComputer as computer left join EntityCompany as company on computer.company_id = company.id";
	private static final String	QUERY_SELECT_BY_ID = "from EntityComputer as computer left join EntityCompany as company on computer.company_id = company.id where computer.id = :id";
	private static final String QUERY_DELETE = "DELETE FROM EntityComputer WHERE id = :id";
	private static final String QUERY_GET_COUNT = "SELECT COUNT(entityComputer) from EntityComputer as entityComputer";
	private static final String QUERY_SELECT_WITH_LIMITER_AND_FILTER = "from EntityComputer as computer left join EntityCompany as company on computer.company_id = company.id where computer.name like :searchField group by computer.id order by computer.name asc";
	private static final String QUERY_SELECT_WITH_LIMITER = "from EntityComputer as computer left join EntityCompany as company on computer.company_id = company.id group by computer.id order by computer.name asc";
	private static final String QUERY_ADD = "INSERT INTO computer (name,introduced,discontinued,company_id)  VALUES (?,?,?,?)";
	private static final String QUERY_UPDATE = "update EntityComputer set name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :company_id where id = :id";

	@Override
	public int getCount(Session session) {
		Query query = session.createQuery(QUERY_GET_COUNT);
		long sizeMax = (long)query.getSingleResult();
		System.out.println("SIZE MAX");

		return Math.toIntExact(sizeMax);
	}

	@Override
	public Computer findById(Session session, final long id){
		List<Object[]> query = session.createQuery(QUERY_SELECT_BY_ID)
				.setParameter("id", ((Number) id).intValue() ).list(); 

		Computer computer = null;
		for (Object[] res : query){
			computer = fillComputer(res);
		}
		return computer;

	}

	@Override
	public List<Computer> findAll(Session session) {

		List<Object[]> result = session.createQuery(QUERY_SELECT_ALL).list();
		List<Computer> computerList = new ArrayList<Computer>();

		for (Object[] res : result ){
			computerList.add(fillComputer(res));
		}

		return computerList;
	}

	@Override
	public List<Computer> findAllWithLimiter(Session session, String name, final int limit, final int offset) {

		List<Object[]> result ;

		if (name == null) {
			result = session.createQuery(QUERY_SELECT_WITH_LIMITER)
					.setFirstResult(offset).setMaxResults(limit).list(); 

		} else {
			result = session.createQuery(QUERY_SELECT_WITH_LIMITER_AND_FILTER)
					.setParameter("searchField",'%'+name+'%')
					.setFirstResult(offset).setMaxResults(limit).list(); 
		}

		List<Computer> computerList = new ArrayList<Computer>();
		for(Object[] res : result){
			computerList.add(fillComputer(res));
		}
		return computerList;
	}

	@Override
	public boolean deleteComputer(Session session ,long id) {
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(QUERY_DELETE)
				.setParameter("id", ((Number) id).intValue());

		int result = query.executeUpdate();
		tx.commit();

		return (result > 0);
	}

	@Override
	public boolean addComputer(Session session, Computer computer) {
		return false;
	}

	@Override
	public boolean updateComputer(Session session,Computer computer) {
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery(QUERY_UPDATE);
		query.setParameter("name", computer.getName());
		query.setParameter("introduced", computer.getIntroduced());
		query.setParameter("discontinued", computer.getDiscontinued());
		query.setParameter("company_id", ((Number) computer.getCompany().getId()).intValue());
		query.setParameter("id", ((Number) computer.getId()).intValue());

		int result = query.executeUpdate();
		tx.commit();

		return (result > 0); 
	}

	private Computer fillComputer(Object[] res){
		EntityComputer entityComputer = (EntityComputer) res[0];
		Computer computer;

		if(res[1] != null) {
			EntityCompany entityCompany = (EntityCompany) res[1];
			computer = new Computer( 
					entityComputer.getId(),
					entityComputer.getName(),
					entityComputer.getIntroduced(),
					entityComputer.getDiscontinued(),
					entityCompany.getId(),
					entityCompany.getName()
					);

		} else {
			computer = new Computer(entityComputer.getId());
			computer.setName(entityComputer.getName());
			computer.setIntroduced(entityComputer.getIntroduced());
			computer.setDiscontinued(entityComputer.getDiscontinued());
		}

		return computer;
	}
}
