package com.project.awinas;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class StudentDao  {

	public static final String EXP = "THE EXCP IS  ";
	private static final InputStream IS = StudentDao.class.getClassLoader()
			.getResourceAsStream("dbpassword.properties");
	private static final Properties PRO = new Properties();
	
	public static final String URL = "jdbc:sqlserver://MSSQL-DEV:1433;databaseName=BPIGTN_TRAINEE";
	public static final String UNAME = "Awinas";
	public static final String PASS = "PASS";
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final String STR="NO DETAILS FOUND";

	private PreparedStatement st ;
	private PreparedStatement selectrank;
	private PreparedStatement updatest;

	private PreparedStatement selectst;
	private ResultSet result;

	private PreparedStatement deletest;

	private ResultSet rstr;
	private PreparedStatement returnstr;

	private PreparedStatement upst;
	public StudentDao()
	{
		//StudentDao
	}
	
	Connection connect() throws IOException, SQLException{
		PRO.load(IS);
		
		return DriverManager.getConnection(URL,UNAME,PRO.getProperty(PASS));
			
}

	
	
	 void addStudentDetail(StudentModel asm) throws  IOException, SQLException
	{
		
		 String query="insert into student(ID,NAME,MARK1,MARK2,MARK3,TOTAL)values (?,?,?,?,?,?)";
		 Connection connect=connect();
          try
          {
                st=connect.prepareStatement(query);
          		
          	
                st.setInt(1,asm.getId());
                st.setString(TWO,asm.getName());
                st.setInt(THREE, asm.getMark1());
                st.setInt(FOUR, asm.getMark2());
                st.setInt(FIVE,asm.getMark3());
                st.setInt(SIX, asm.getTotal());
               
              st.executeUpdate();
              calcrank(connect);
              
              
          } 
          finally
          {
	   
	   st.close();
	   connect.close();
          }
         
         
	}
	
	
	
	public  void calcrank(Connection connect) throws SQLException
	{
		String updatestring="update  student set RANK=? where ID=?";
		String ranksortquery = "select id,total from student order by total desc";
		 
		
		
		try
		{
		
                 selectrank = connect.prepareStatement(ranksortquery);
                updatest = connect.prepareStatement(updatestring);
				ResultSet rs= selectrank.executeQuery();
                
			
				setrank(rs, updatest);
				
                
			
		}
		finally
		{
			selectrank.close();
			updatest.close();
		}
        
		
	}
	
	public  void setrank(ResultSet rs , PreparedStatement updatest) throws SQLException
	{
		int rank = 1;
		int before;
		
		rs.next();
        
        before = rs.getInt(TWO	);
        updatest.setInt(1, rank);
        updatest.setInt(TWO,rs.getInt(1) );
        updatest.executeUpdate();
        while (rs.next()) {
            if (rs.getInt(TWO) != before) {
                before = rs.getInt(TWO);
                updatest.setInt(1, rank+1);
                updatest.setInt(TWO,rs.getInt(1) );
                updatest.executeUpdate();            
                rank++;
            }
            else {
                before = rs.getInt(TWO);
                updatest.setInt(1, rank);
                updatest.setInt(TWO,rs.getInt(1) );
                updatest.executeUpdate();
            }
            
        }
		
	}

	
	
	 StudentModel getStudentDetail(int id) throws  IOException, SQLException {

		StudentModel dsm = new StudentModel();
		Connection connect=connect();
		String selectquery = "select * from student where ID =?";
		try {
			
				 selectst = connect.prepareStatement(selectquery);
				selectst.setInt(1, id);

					result = selectst.executeQuery();
					result.next();
					dsm.setId(result.getInt(1));
					dsm.setName(result.getString(TWO));
					dsm.setMark1(result.getInt(THREE));
					dsm.setMark2(result.getInt(FOUR));

					dsm.setMark3(result.getInt(FIVE));
					dsm.setTotal(result.getInt(SIX));
					dsm.setRank(result.getInt(SEVEN));

				}
		finally
        {
        	result.close();
        	selectst.close();
        	connect.close();
        	
        }
        
		return dsm;

	}
	
	 String deleteStudentDetail(int id) throws  IOException, SQLException
	{
		 int count=0;
         String deletequery = "delete from student where ID =?";
         String displaystring=STR;
         Connection connect=connect();
         try
         {
             	
          deletest=connect.prepareStatement(deletequery);
         		
         	deletest.setInt(1, id);	
         	count=deletest.executeUpdate();
          if(count!=0)
          {
             displaystring="REMOVED";
          } 
          else
          {
        	  displaystring="noid";
          }
         }
         finally
         {
         deletest.close();	
         connect.close();
         }
         
     return displaystring; 
         
    
    
	}

	  List<StudentModel> getStudentRank(int rank) throws  IOException, SQLException {

		
		
		List<StudentModel> daoL;
		
		String selectstring = "select * from student where RANK=?";
		Connection connect=connect();
		try
		{
					 returnstr = connect.prepareStatement(selectstring);

				returnstr.setInt(1, rank);
				 rstr = returnstr.executeQuery();

					daoL = getrank(rstr);
					
		}
			
		finally
        {
        	
        	rstr.close();
        	returnstr.close();
        	connect.close();
        }
		return daoL;

	}

	public  List<StudentModel> getrank(ResultSet rst) throws SQLException {
		
		
		List<StudentModel> daoSL=new ArrayList<>();
	
		
			while (rst.next()) 
			{	
				StudentModel send = new StudentModel();
				send.setId(rst.getInt(1));
				send.setName(rst.getString(TWO));
				send.setMark1(rst.getInt(THREE));
				send.setMark2(rst.getInt(FOUR));
				send.setMark3(rst.getInt(FIVE));
				send.setTotal(rst.getInt(SIX));
				send.setRank(rst.getInt(SEVEN));
				daoSL.add(send);
			}
		
			return daoSL;
	}
	
	
	 String updateStudent(StudentModel usm) throws  IOException, SQLException
	{
		String updateresult="UNSUCCESSFUL";
		String updateQuery ="update Student SET NAME=?,MARK1=?,MARK2=?,MARK3=?,TOTAL=?  where ID=?";
		Connection connect=connect();
		try
		{
            upst = connect.prepareStatement(updateQuery);
		
			upst.setString(1, usm.getName());
			upst.setInt(TWO, usm.getMark1());
			upst.setInt(THREE, usm.getMark2());
			upst.setInt(FOUR, usm.getMark3());
			upst.setInt(FIVE, usm.getTotal());
			upst.setInt(SIX, usm.getId());
			upst.executeUpdate();
			calcrank(connect);
			updateresult="UPDATED";
		
		}
		finally
		{
			upst.close();
			connect.close();
		}
		
        
		
		
		return updateresult;
	}

}
