package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
	
//	@Bean
//	public UserDao userDao() {
//		UserDao userDao = new UserDao(connectionMaker());
//		return userDao;
//	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return  new CountingConectionMaker(realConnectionMaker());
	}
	
	@Bean
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
	
}
