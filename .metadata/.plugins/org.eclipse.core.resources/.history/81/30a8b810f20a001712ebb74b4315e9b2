package com.excilys.computerdb.fconsigny.presentation.view.components;

import java.util.List;
import java.util.Map;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.dto.IDto;

public class Page implements IPage<IDto> {

	private int offset; 
	private int limit = 0; 
	private int maxPage;
	private int cursor;
	private List<IDto> arrayList; 
	
	public Page(int offset, int limit,int maxResult){
		this.offset = offset; 
		this.limit = limit;
		
		resizePage(maxResult);
	}
	
	public boolean swapPageLeft(){
		if( this.cursor < 0){
			this.cursor--; 
			return true;
		}
		return false; 
	}
	
	public boolean swapPageRight(){
		if(this.cursor < maxPage){
			this.cursor++; 
			return true;
		}
		return false;  
	}

	public void resizePage(int maxResult){
		this.maxPage = (maxResult) / this.offset ; 
	}

	@Override
	public IDto getItemByPosition(int index) {
		return arrayList.get(index);
	}
	
}
