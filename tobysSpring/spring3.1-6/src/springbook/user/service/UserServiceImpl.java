package springbook.user.service;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.util.List;

import org.springframework.mail.MailSender;

public class UserServiceImpl implements UserService{
	UserDao userDao;
	MailSender mailSender;
	
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void add(User user) {
		if(user.getLevel() == null ) user.setLevel(Level.BASIC);
		userDao.add(user);
	}

	@Override
	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		for(User user : users) {
			if (canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}
	
	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}
	
	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		switch(currentLevel) {
			case BASIC : return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER : return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
			case GOLD : return false;
			default : throw new IllegalArgumentException("Unknown Level: "+currentLevel);
		}
	}

}
