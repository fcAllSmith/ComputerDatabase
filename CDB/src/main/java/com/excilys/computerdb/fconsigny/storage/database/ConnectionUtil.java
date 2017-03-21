package com.excilys.computerdb.fconsigny.storage.database;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class ConnectionUtil {

  private static Logger logger = Logger.getLogger(ConnectionUtil.class);

  public static void closeQuietly(Connection conn) {
    try {
      conn.close();
    } catch (Exception e) {
      logger.error(e);
    }
  }

  public static void rollbackQuietly(Connection conn) {
    try {
      conn.rollback();
    } catch (Exception e) {
      logger.error(e);
    }
  }

}
