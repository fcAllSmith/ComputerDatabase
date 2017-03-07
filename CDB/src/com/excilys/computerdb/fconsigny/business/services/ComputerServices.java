package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;

import java.sql.SQLException;
import java.util.List;

public class ComputerServices {
	
	public ComputerServices() throws SQLException{
		super();
	}
	
	private ComputerDao cDao = ComputerFactory.getComputerDao();

	public ComputerDto getUniqueComputer(long id) {
		return ComputerDtoMapper.transformToDto(cDao.findById(id));
	}

	public List<ComputerDto> getAllComputers(){
		return ComputerDtoMapper.transformListToDto(cDao.findAll());
	}

	public List<ComputerDto> getAllComputersWithLimiter(int limit, int offset){
		return ComputerDtoMapper.transformListToDto(cDao.findAllWithLimiter(limit, offset));
	}

	public ComputerDto saveComputer(ComputerDto entity){
		ComputerDto savedComputer = null; 

		if (cDao.findById(entity.getId()) == null) {
			//TODO : CREATE COMPUTER
			//savedComputer = cDao.makePersistent(entity);
		} else {
			//TODO upload computer
		}
		return savedComputer;
	}

}
