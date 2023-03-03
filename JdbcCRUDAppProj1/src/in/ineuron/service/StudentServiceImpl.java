package in.ineuron.service;

import in.ineuron.daofactory.StudentDaoFactory;
import in.ineuron.dto.Student;
import in.ineuron.persistence.IStudentDao;
import in.ineuron.serviceFactory.StudentServiceFactory;

public class StudentServiceImpl implements IStudentService {

	IStudentDao studentdao;
	@Override
	public String addStudent(String name, Integer age, String address) {
		// TODO Auto-generated method stub
		studentdao=StudentDaoFactory.getStudentDao();
		return studentdao.addStudent(name, age, address);
	}

	@Override
	public Student searchStudent(Integer id) {
		// TODO Auto-generated method stub
		studentdao=StudentDaoFactory.getStudentDao();
		return studentdao.searchStudent(id);
	}

	@Override
	public String updateStudent(Student student) {
		// TODO Auto-generated method stub
		studentdao=StudentDaoFactory.getStudentDao();
		return studentdao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		studentdao=StudentDaoFactory.getStudentDao();
		return studentdao.deleteStudent(id);
	}

}
