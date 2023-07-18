package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest_2 {

	@Test
	public void addAndGet() throws SQLException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
//		User user = new User();
//		user.setId("gyumee");
//		user.setName("박성철");
//		user.setPassword("springno1");
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
		
//		User user2 = dao.get(user.getId());
//		assertThat(user2.getName(), is(user.getName()));
//		assertThat(user2.getPassword(), is(user.getPassword()));
//		if(!user.getName().equals(user2.getName())) {
//			System.out.println("테스트 실패 (name)");
//		} else if(!user.getPassword().equals(user2.getPassword())) {
//			System.out.println("테스트 실패 (password)");
//		}else {
//			System.out.println("조회 테스트 성공");
//		}
	}
	
	@Test
	public void count() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		User user3 = new User("bumjin", "박범진", "springno3");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
}
