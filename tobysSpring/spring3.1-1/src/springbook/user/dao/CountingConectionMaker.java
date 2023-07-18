package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;


public class CountingConectionMaker implements ConnectionMaker	{
	int counter = 0;
	private ConnectionMaker realConnectionMaker;
	
	public CountingConectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		return realConnectionMaker.makeConnection();
	}
	
	public int getCounter() {
		return this.counter;
	}

}
