package in.ineuron.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {
	private JdbcUtil() {
	}

	public static Connection getJdbcConnection() throws SQLException, IOException,Exception {

		// Take the data from properties file
		HikariConfig config=new HikariConfig("F:\\Java_Applications\\JdbcCRUDApp\\src\\db.properties");
		HikariDataSource dataSource=new HikariDataSource(config);
		return dataSource.getConnection();
	}
}