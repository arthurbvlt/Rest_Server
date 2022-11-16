package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DaoFactory {

	private static final Logger LOG = LogManager.getLogger(DaoFactory.class);

	private String url;
	private String username;
	private String password;
	private static final ResourceBundle DB_CONF = ResourceBundle.getBundle("DB");

	DaoFactory() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOG.fatal(e);
		}
		this.url = DB_CONF.getString("URL_DB");
		this.username = DB_CONF.getString("USERNAME_DB");
		this.password = DB_CONF.getString("PASSWORD_DB");
	}

	/**
	 * Create an instance of DaoFactory to handle MariaDB queries
	 * @return A DaoFactory instance
	 */
	public static DaoFactory getInstance() {
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			LOG.fatal(e);
//		}
		return new DaoFactory();
	}

	/**
	 * Create a connection to the database
	 * @return The new connection
	 */
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			LOG.fatal(e);
		}
		return connection;
	}

	/**
	 * @param connection
	 * @param statement
	 */
	public void closeStatement(Connection connection, Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOG.fatal(e);
		}
	}
}
