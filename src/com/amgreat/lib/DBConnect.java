package com.amgreat.lib;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.math.BigDecimal;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amgreat.vo.RequestVO;
import com.amgreat.vo.VO;

@Component
public class DBConnect {
	
	Logger logger = LoggerFactory.getLogger(DBConnect.class);	
	
	@Value("${db.jbcurl}")
	private String jdbcURL;
	
	@Value("${db.userid1}")
	private String dbUserId;
	
	@Value("${db.password1}")
	private String dbPassword;
	
	private Connection conn;
	
//	public Connection connect(){
//		try {
//			conn = DriverManager.getConnection( "jdbc:mariadb://localhost:3306/appdb","app", "app" );
//			if (conn != null) {
//				System.out.println("connection established!");
//			}
//		} catch(Exception e){
//			logger.error("[DBConnect.connect]:" + e.getMessage());
//		}
//		return (conn!=null ? conn:null);
//	}
	@Bean
	public Connection connect(){
		try {
				System.out.println(jdbcURL + "-" + dbUserId + "-" + dbPassword);
			
			  Class.forName("org.postgresql.Driver");			  
			  
			  conn = DriverManager.getConnection( jdbcURL, dbUserId, dbPassword );
			  
			if (conn != null) {
				System.out.println("connection established!");
			}
		} catch(Exception e){
			logger.error("[DBConnect.connect]:" + e.getMessage());
		}
		return (conn!=null ? conn:null);
	}
	
	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch(Exception e){
			logger.error("[DBConnect.closeConnection]:" + e.getMessage());
		}
	}
	
	private String sqlString(VO vo) {
		try {		
			return ((RequestVO) vo).getCmdString();
		} catch(Exception e){
			logger.error("[DBConnect.sqlString]:" + e.getMessage());
		}
		return null;
	}
	
	private PreparedStatement setParamString(PreparedStatement stmt, VO vo) {
		String str = "";
		try {
			RequestVO rvo = (RequestVO) vo; 
			
			if( rvo != null && rvo.getVal() != null) {
				
				int key = 0; RequestVO rv = rvo;
				
				while ( rv != null && rv.getType() != null) {
					
					String val = rv.getVal();
					String type = rv.getType();
					
					key++;
					
					System.out.println(" before setVal["+key+"] = " + val + ", type = " + type);
					
					switch (type) {
						case "str":
							stmt.setString(key, val);break;
						case "text":
							stmt.setString(key, val);break;	
						case "smallint":
							stmt.setInt(key, Integer.parseInt(val)); break;
						case "bigint":
							stmt.setInt(key, Integer.parseInt(val)); break;
						case "int":
							stmt.setInt(key, Integer.parseInt(val)); break;
						case "date": 
							{
								SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
								String dateInString = (val == null || val.trim().equals("") ? "01-01-1900" : val );
								java.util.Date date = formatter.parse(dateInString);
								stmt.setDate(key, new Date(date.getTime()));
								break;
							}
						case "double":
							stmt.setDouble(key, Double.parseDouble(val)); break;
						case "float":
							stmt.setFloat(key, Float.parseFloat(val)); break;
						case "decimal":
						{; 
							stmt.setBigDecimal(key, new BigDecimal(val));break;
						}	
					}
					
					System.out.println(" after setVal["+key+"] = " + val + ", type = " + type);
					
					rv = rv.getNext();
				}
			} 
			return stmt;
		} catch(Exception e){
			logger.error("[DBConnect.setParamString]:" + e.getMessage());
			return null;
		}
	}
	
	public Connection getConn() { return conn; }

	public void setConn(Connection conn) { this.conn = conn; }

	public boolean executeUpdate(VO vo, Connection con) {
		
		Boolean bool = false; String sql_str = "";
		
		if ( (sql_str = sqlString(vo)) == null ) return false;
		
		try {
			System.out.println("prepareStatement sql_str = " + sql_str);
			
			PreparedStatement stmt = con.prepareStatement(sql_str); 
			
			stmt = setParamString(stmt, vo);
			
			int res = stmt.executeUpdate();
			
			System.out.println("[DBConnect.executeUpdate Normal] " + sql_str);
			
		} catch(Exception e){
			logger.error("[DBConnect.executeUpdate]:" + e.getMessage());
			return false;
		} 
		return bool;
	}
	
	public ResultSet executeQuery(VO vo, Connection con) {
		
		String sql_str = "";
		
		if ( (sql_str = sqlString(vo)) == null ) return null;
		
		try {
			System.out.println("prepareStatement sql_str = " + sql_str);
			
			PreparedStatement stmt = con.prepareStatement( sql_str );
			
			stmt = setParamString(stmt, ((RequestVO)vo).getFilter());
			
			return stmt.executeQuery();
			
		} catch(Exception e){
			logger.error("[DBConnect.executeQuery]:" + e.getMessage());
		}
		return null;
	}
}