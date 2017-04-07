package src.main.java.com.excilys.computerdb.fconsigny.cli.dto;

public class CompanyDto {

  private long id;
  private String name = "";

  public CompanyDto() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return " ID : " + this.id + " Company Name : " + this.name;
  }

  @Override
  public boolean equals(Object obj) {
    // TODO : redefine this method
    return true;
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return super.hashCode();
  }

}
