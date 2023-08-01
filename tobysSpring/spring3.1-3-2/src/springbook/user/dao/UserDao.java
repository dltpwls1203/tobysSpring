package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;


import springbook.user.domain.User;

public class UserDao {
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
		
		this.dataSource = dataSource;
	}
	

	public void add(User user) throws SQLException {
		StatementStrategy st = new AddStatement(user);
		this.jdbcContext.workWithStatementStrategy(st);
	}

	public User get(String id) throws SQLException {
		Connection c = this.dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		rs.close();
		ps.close();
		c.close();

		if (user == null)
			throw new EmptyResultDataAccessException(1);

		return user;
	}
	
	public void deleteAll() throws SQLException {
		this.jdbcContext.executeSql("delete from users");
//		StatementStrategy st = new DeleteAllStatement();
//		jdbcContextwithStatementStrategy(
//				new StatementStrategy() {
//					
//					@Override
//					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//						return c.prepareStatement("delete from users");
//					}
//				}
//				);
	}
	
	
//	public void deleteAll() throws SQLException {
//		Connection c = null;
//		PreparedStatement ps = null;
//
//		try {
//			c = dataSource.getConnection();
//			
//			// ps = c.prepareStatement("delete from users"); // 변하는 부분
//			//	 ps = makeStatement(c);
//			StatementStrategy strategy = new DeleteAllStatement();
//			ps = strategy.makePreparedStatement(c);
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			throw e;
//		} finally {
//			if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
//			if (c != null) { try { c.close(); } catch (SQLException e) {} }
//		}
//
//		ps.close();
//		c.close();
//	}

	public int getCount() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = this.dataSource.getConnection();
			ps = c.prepareStatement("select count(*) from users");

			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			throw e;
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}

			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}

		}
	}

	public void jdbcContextwithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = dataSource.getConnection();
			
			ps = stmt.makePreparedStatement(c);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
			if (c != null) { try { c.close(); } catch (SQLException e) {} }
		}

		ps.close();
		c.close();
	}
	
//	private PreparedStatement makeStatement(Connection c) throws SQLException {
//		PreparedStatement ps;
//		ps = c.prepareStatement("delete from users");
//		return ps;
//	}
	
}
