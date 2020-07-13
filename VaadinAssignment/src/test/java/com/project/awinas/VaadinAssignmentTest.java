package com.project.awinas;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class VaadinAssignmentTest {
	public  final String NAME = "RAM";
    public  final String NAME_2 = "RAMRAM";
    
    public  final int ID = 44;
    public  final int MARK1 = 50;
    public  final int MARK2 = 50;
    public  final int MARK3 = 50;
    public  final int TOTAL = 150;
    
    public  final String NAME_1 = "JANANI";
    public  final int ID_1 = 33;
    public  final int MARK1_1 = 50;
    public  final int MARK2_1 = 45;
    public  final int MARK3_1 = 50;
    public  final int TOTAL_1 = 145;
    
    public  final int RANK = 1;
    public  final int RANK_1 = 15;
    public  final int ID_2 = 333; 
    
    public static final String EXPECTED_ADD = "SUCCESSFUL";
    //public  final StudentModel EXPECTED_DISPLAY;
    public  final String EXPECTED_DELETE = "REMOVED";
    public  final String EXPECTED_DELETES = "noid";
    public  final String EXPECTED_DISPLAYS ="NO DETAILS FOUND";
    public  final String EXPECTED_RANK ="NO ONE";

    
    StudentDao dao=new StudentDao();
    StudentModel asm=new StudentModel();
    StudentModel asm1=new StudentModel();
    
    StudentModel asmR=new StudentModel();
    
    
    LoginDao ldao=new LoginDao();
    LoginModel lm=new LoginModel();
     
    @Before
    public void NormalTest() throws SQLException
    {
    	try (Connection connect = DriverManager.getConnection("jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_TRAINEE", "Awinas","^D$b2K5!3"); Statement selectst = connect.createStatement()) {

            String deletequery = "delete from student";
            selectst.executeUpdate(deletequery);
           
        }
    

    	asm.setId(ID);
    	asm.setName(NAME);
    	asm.setMark1(MARK1);
    	asm.setMark2(MARK2);
    	asm.setMark3(MARK3);
    	asm.setTotal(TOTAL);
    	
    	asm1.setId(ID_1);
    	asm1.setName(NAME_1);
    	asm1.setMark1(MARK1_1);
    	asm1.setMark2(MARK2_1);
    	asm1.setMark3(MARK3_1);
    	asm1.setTotal(TOTAL_1);
    	
    	asmR.setId(ID_1);
    	asmR.setName(NAME);
    	asmR.setMark1(MARK1);
    	asmR.setMark2(MARK2);
    	asmR.setMark3(MARK3);
    	asmR.setTotal(TOTAL);
    
    }
    
    @Test
    public void LoginPassTest() throws SQLException, ClassNotFoundException, IOException {
        lm.setUserid(1);
    	lm.setPwd("awi");
      String ACTUAL= ldao.getCredentialResult(lm);
        assertEquals(ACTUAL, "SUCCESSFUL");
    }
    
    @Test
    public void LoginFailTest() throws SQLException, ClassNotFoundException, IOException {
        lm.setUserid(1);
    	lm.setPwd("awin");
      String ACTUAL= ldao.getCredentialResult(lm);
        assertEquals(ACTUAL, "UNSUCCESSFUL");
    }
    
   
    @Test
    public void DisplayStudentsDetsilsTest() throws SQLException, ClassNotFoundException, IOException {
        
        dao.addStudentDetail(asm);
        dao.addStudentDetail(asm1);
        StudentModel ACTUAL = dao.getStudentDetail(ID);
        assertEquals(ACTUAL.getName(), asm.getName());
    }
    
  
    
    @Test
    public void DeleteStudentsDetsilsTest() throws SQLException, ClassNotFoundException, IOException {
        
        dao.addStudentDetail(asm);
        dao.addStudentDetail(asm1);
        String ACTUAL = dao.deleteStudentDetail(ID);
        assertEquals(ACTUAL, EXPECTED_DELETE);
    }
    
    @Test
    public void DeleteStudentsDetsilsFailTest() throws SQLException, ClassNotFoundException, IOException {
        
        dao.addStudentDetail(asm);
        dao.addStudentDetail(asm1);
        String ACTUAL = dao.deleteStudentDetail(ID_2);
        assertEquals(ACTUAL, EXPECTED_DELETES);
    }
    

   
    @Test
    public void TwoRankStudentsDetsilsTest() throws SQLException, ClassNotFoundException, IOException {
        
        dao.addStudentDetail(asm);
        dao.addStudentDetail(asmR);
        List<StudentModel> ACTUAL = dao.getStudentRank(RANK);
        assertEquals(ACTUAL.get(0).getId(), asm.getId());
    }
    
    
    @Test
    public void UpdateStudentsDetsilsTest() throws SQLException, ClassNotFoundException, IOException {
        
        dao.addStudentDetail(asm);
        dao.addStudentDetail(asmR);
        String ACTUAL = dao.updateStudent(asm);
        assertEquals(ACTUAL,"UPDATED");
    }
}
