package com.project.awinas;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



import com.project.awinas.model.LoginModel;

public class LoginDao {
	public static final String EXP = "THE EXCP IS  ";
	private static final InputStream IS = StudentDao.class.getClassLoader()
			.getResourceAsStream("dbpassword.properties");
	
	private static final Properties PRO = new Properties();
	
	
	public static final String URL = "jdbc:sqlserver://MSSQL-DEV:1433;databaseName=BPIGTN_TRAINEE";
	public static final String UNAME = "Awinas";
	public static final String PASS = "PASS";

	private PreparedStatement checklogin;
	private ResultSet rst;
	
	public LoginDao()
	{
		//loginDao
	}
	
	Connection connect() throws IOException, SQLException{
		PRO.load(IS);
		return DriverManager.getConnection(URL,UNAME,PRO.getProperty(PASS));
			
}
	
	
	  String getCredentialResult(LoginModel lm) throws IOException, SQLException
	{
		String loginquery ="select PASSWORD from logintable where USERID=? ";
		
		String loginresult = "UNSUCCESSFUL";
		Connection connect =connect();
		try {
		 checklogin = connect.prepareStatement(loginquery);
					
				checklogin.setInt(1, lm.getUserid());
				 rst = checklogin.executeQuery();

					
					rst.next(); 
					
					if(	rst.getString(1).equals(lm.getPwd()))
					{
					loginresult= "SUCCESSFUL";
					}
						
				

						
		} 
		finally
		{
		rst.close();
		checklogin.close();
		connect.close();
		}
		
		return loginresult;
	}
	
	
}
