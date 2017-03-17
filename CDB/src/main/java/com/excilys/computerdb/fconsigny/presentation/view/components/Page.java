package com.excilys.computerdb.fconsigny.presentation.view.components;

public class Page {

	//Initialize page to 0 by default
	private int currentPage = 0 ;
	private int offset = 0; 
	private int maxPage;
	private int limit; 
	
	public Page(int limit){
		this.limit = limit; 
	}
	
	public void setLimit(int limit){
		this.limit = limit; 
	}
	
	public void setMaxPage(int maxPage){
		this.maxPage = maxPage;
	}
	
	public boolean nextPage() {
		if(this.currentPage < maxPage){
			this.currentPage++;
			return true;
		}

		return false;
	}

	public boolean previousPage(){
		if(this.currentPage > 0){
			this.currentPage--;
			return true;
		}
		return false; 
	}
}
