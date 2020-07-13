package com.project.awinas;






import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UpdateController {

	@RequestMapping("/update")
	public String add(@RequestBody StudentModel asm)   
	{
	StudentDao dao =new StudentDao();

	String result ="STUDENT UPDATE SUCCESSFUL";
	
	dao.updateStudentDetail(asm);
 
	
	
	
	return result;
	}

}
