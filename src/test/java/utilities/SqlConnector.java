package utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SqlConnector {
    private static final String userName = ReadConfigFiles.getPropertyValues("DbUser");
    private static final String password = ReadConfigFiles.getPropertyValues("DbPassword");
    private static final String databaseName = ReadConfigFiles.getPropertyValues("DbName");
   // private static final String url = "jdbc:postgresql://localhost:5432/" + databaseName + "";
    private static final String url = String.format("jdbc:postgresql://localhost:5432/%s", databaseName);

    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);

    /**
     * Connect at the postgresSQL database
     * @return a connection object
     */

    private static Connection connect(){
       Connection conn = null;
       try {
           conn = DriverManager.getConnection(url, userName, password);
           LOGGER.info("Successfully Connected to the Database");


       }catch (SQLException e){
           LOGGER.error("Sql Connection Exception: " + e.getMessage());

       }
       return conn;
    }

    /**
     * Reading Data from the Database
     * @param sqlQuery is the method parameter to pass the SQL Query
     * @return a ResultSet Object
     * @throws SQLException
     */

    public static ResultSet readData(String sqlQuery) throws SQLException {
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = connect();
            Statement stmt = conn.createStatement();
          rs = stmt.executeQuery(sqlQuery);
        }catch (SQLException e){
            LOGGER.error("SQL ResultSet Exception: " + e.getMessage());
        }finally {
            conn.close();
        }
        return rs;
    }

}
