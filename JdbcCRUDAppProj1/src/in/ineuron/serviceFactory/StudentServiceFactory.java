package in.ineuron.serviceFactory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;

public class StudentServiceFactory {

	private StudentServiceFactory() {}
	
	public static IStudentService studentService=null;
	public static IStudentService getStudentService() {
		if(studentService==null) {//singleton object creation
		studentService=new StudentServiceImpl();
		}
		return studentService;
	}
	
}
