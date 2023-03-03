package in.ineuron.daofactory;

import in.ineuron.persistence.IStudentDao;
import in.ineuron.persistence.StudentDaoImpl;

public class StudentDaoFactory {

	private StudentDaoFactory() {};
	static IStudentDao studentDao=null;
	
	public static IStudentDao getStudentDao() {
		if(studentDao==null) {//singleton object creation
		studentDao=new StudentDaoImpl();
		}
		return studentDao;
	}
}
