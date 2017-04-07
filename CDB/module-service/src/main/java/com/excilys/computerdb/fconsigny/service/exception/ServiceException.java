package src.main.java.com.excilys.computerdb.fconsigny.service.exception;

public class ServiceException extends Exception{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ServiceException(String message){
    super(" Services are unvailable please come back later " + message);
  }
}
