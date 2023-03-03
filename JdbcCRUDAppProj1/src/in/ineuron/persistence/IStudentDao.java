package in.ineuron.persistence;

import in.ineuron.dto.Student;

public interface IStudentDao {

	public String addStudent(String name,Integer age,String address);
	
	public Student searchStudent(Integer id);
	
	public String updateStudent(Student student);
	
	public String deleteStudent(Integer id);
}
