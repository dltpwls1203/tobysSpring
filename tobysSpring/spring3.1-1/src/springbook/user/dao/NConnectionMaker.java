package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {
	
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
				// D사 DB connection 코드
				System.out.println("N사 DB connection 코드 : NConnectionMaker");
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "spring", "book");
				return c;
	}
	
}
