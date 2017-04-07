package src.main.java.com.excilys.computerdb.fconsigny.cli.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({
  "src.main.java.com.excilys.computerdb.fconsigny"})
public class ApplicationConfig {    
    
  
}