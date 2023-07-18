package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao  {

	protected Connection getConnection() throws ClassNotFoundException, SQLException {
		// N사 DB connection 코드
		System.out.println("N사 DB connection 코드");
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "spring", "book");
		return c;
	}
}
