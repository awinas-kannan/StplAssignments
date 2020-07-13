package com.project.awinas;




import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RankController {

	@RequestMapping("/rank")
	public StudentList rank(@RequestBody int rank)  {

		StudentDao dao = new StudentDao();
		StudentList rankList =new StudentList();
		List<StudentModel> rankresult ;

		rankresult = dao.getStudentRank(rank);
		rankList.setStudentList(rankresult);
		return rankList;
	}

}
