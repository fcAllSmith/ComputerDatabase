package com.excilys.computerdb.fconsigny.presentation.component;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageServlet extends SimpleTagSupport {

  private String uri;
  private int currentPage;
  private int totalPages; 
  private int maxLinks =10;

  private Writer getWriter(){
    JspWriter out = getJspContext().getOut();
    return out;
  }
  
  @Override 
  public void doTag() throws JspException {
    Writer out = getWriter();
    int pageStart = Math.max(currentPage - maxLinks / 2, 1);
    int pageEnd = pageStart + maxLinks; 
    
    if(pageEnd > totalPages + 1){
      if(pageStart < 1){
        pageStart = 1;
      }
      pageEnd = totalPages + 1;
    }
    
  }

}