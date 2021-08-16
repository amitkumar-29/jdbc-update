package com.tcs.jbc.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JDBCDemo {
public	static Logger logger = LoggerFactory.getLogger(JDBCDemo.class);

public static void main(String[] args) {
	logger.trace("This is my logger handler");
	String DB_URL="jdbc:mysql://localhost/practice";
	String DB_USER="root";
	String DB_PASSWORD="Nuvelabs123$";
	try(Connection connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			Statement statement=connection.createStatement();){
	//	create();
		//retrieve(statement);
		//update(statement);
		//delete(statement);
		List<String> res= retrieveWithCondittion(statement,"A");
		System.out.println(res);
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
}
private static void retrieve(Statement statement) throws SQLException {
	ResultSet resultSet=statement.executeQuery("select * from regions");
	while(resultSet.next()) {
		logger.debug(null, resultSet.getInt(1));
		logger.debug(resultSet.getNString("REGION_NAME"));
	}
}
private static void create(Statement statement) throws SQLException {
	statement.execute("INSERT INTO REGIONS VALUES(4000,'INDIA')"); //insert

}
private static void update(Statement statement) throws SQLException {
	statement.execute("UPDATE REGIONS SET REGION_NAME='Africa' WHERE REGION_ID=1");
	statement.execute("UPDATE REGIONS SET REGION_NAME='America' WHERE REGION_ID=2");
}
private static void delete(Statement statement) throws SQLException {
	statement.execute("DELETE FROM REGIONS WHERE REGION_ID=4000");
}
private static List<String> retrieveWithCondittion(Statement statement, String str) throws SQLException {
	 final String Query="SELECT * FROM REGIONS WHERE REGION_NAME LIKE '"+str+"%'";
	ResultSet resultSet = statement.executeQuery(Query + " ORDER BY REGION_NAME DESC");

	List<String> ls = new ArrayList<>();
	while(resultSet.next()) {
		logger.debug("id={}" ,resultSet.getInt(1));
		logger.debug(resultSet.getNString("REGION_NAME"));
		ls.add(resultSet.getNString("REGION_NAME"));
	}
	logger.debug("");
	return ls;
}
}
