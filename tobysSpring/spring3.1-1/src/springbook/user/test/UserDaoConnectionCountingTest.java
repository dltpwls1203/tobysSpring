package springbook.user.test;

import java.sql.SQLException;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.dao.CountingConectionMaker;
import springbook.user.dao.CountingDaoFactory;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoConnectionCountingTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		//
		// DAO 사용 코드
		//
		CountingConectionMaker ccm = context.getBean("connectionMaker", CountingConectionMaker.class);
		System.out.println("Connection counter : " +ccm.getCounter());
		
	}

}
