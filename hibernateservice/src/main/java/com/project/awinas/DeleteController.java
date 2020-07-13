package com.project.awinas;









import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeleteController {
	@RequestMapping("/delete")
	public String delete(@RequestBody int id) {

		StudentDao dao = new StudentDao();

		

		return dao.deleteStudentDetail(id);

		
	}

}
