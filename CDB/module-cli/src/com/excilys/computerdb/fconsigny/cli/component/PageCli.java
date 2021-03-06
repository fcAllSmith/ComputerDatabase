package com.excilys.computerdb.fconsigny.cli.component;

import com.excilys.computerdb.fconsigny.binding.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.core.components.IPage;

public class PageCli implements IPage<ComputerDto> {

  // Initialize page to 0 by default
  private int currentPage = 0;
  // Initialize page to 0 by default
  private int offset = 0;
  // Limit can't be get outside the class.
  private int limit;
  private int maxPage;

  /**
   * By default, the view has none offset and the cursor is set to first page.
   * 
   * @param limit
   *          : we initialize the page with a number of rows to look for.
   */
  public PageCli(final int limit) {
    this.limit = limit;
  }

  /**
   * Change the limit of rows to look for.
   * 
   * @param limit.
   */
  public void setLimit(final int limit) {
    this.limit = limit;
  }

  /**
   * Navigation can be made only if the maxPage has been set.
   * 
   * @param maxPage.
   */
  public void setMaxPage(final int maxPage) {
    this.maxPage = maxPage;
  }

  /**
   * Change the cursor if a nextPage exist.
   * 
   * @return false if no page exist.
   */
  public boolean nextPage() {
    if (this.currentPage < maxPage) {
      this.currentPage++;
      this.offset = offset + limit;
      return true;
    }

    return false;
  }

  /**
   * Change the cursor if a previousPage exist.
   * 
   * @return
   */
  public boolean previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.offset = offset - limit; 
      return true;
    }
    return false;
  }

  @Override
  public int getOffset() {
    // TODO Auto-generated method stub
    return this.offset;
  }

  @Override
  public int getLimite() {
    // TODO Auto-generated method stub
    return this.limit;
  }

  @Override
  public int getCurrentPage() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setMaxCount(int nbrMax) {
    System.out.println(nbrMax);
    this.maxPage = (int)(nbrMax/limit);
    System.out.println(" " + this.maxPage);
  }
}
