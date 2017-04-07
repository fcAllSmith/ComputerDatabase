package src.main.java.com.excilys.computerdb.fconsigny.cli.component;

public interface IPage <T> {

  public int getOffset();
  
  public int getLimite();
  
  public int getCurrentPage();
  
  public void setMaxCount(int nbrMax);
}
