package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {

	Connection connection=null;
	PreparedStatement pstmt=null;
	ResultSet resultSet=null;
	Scanner scan=null;
	@Override
	public String addStudent(Student std) {
		// TODO Auto-generated method stub
		String sqlInsertQuery = "insert into student(`name`,`age`,`address`)values(?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {

				pstmt.setString(1, std.getName());
				pstmt.setInt(2, std.getAge());
				pstmt.setString(3, std.getAddress());

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}
    @Override
	public Student searchStudent(Integer id) {
		// TODO Auto-generated method stub
    	String sqlsearchQuery="select id,name,age,address from student where id=?";
    	Student student=null;
    	try {
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null) {
				pstmt=connection.prepareStatement(sqlsearchQuery);
			}
			if(pstmt!=null) {
				pstmt.setInt(1,id);
			}
			if(pstmt!=null) {
				resultSet=pstmt.executeQuery();
			}
			if(resultSet!=null) {
				
				if(resultSet.next()) {
					student=new Student();
					student.setId(resultSet.getInt(1));
					student.setName(resultSet.getString(2));
					student.setAge(resultSet.getInt(3));
					student.setAddress(resultSet.getString(4));
					return student;
				}
			}
		} catch (SQLException | IOException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateStudent(Student student) {
		String sqlUpdateQuery = "update student set name=?,age=?,address=? where id=?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);

			if (pstmt != null) {

				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getAge());
				pstmt.setString(3, student.getAddress());
				pstmt.setInt(4, student.getId());

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failure";
	}

	

	@Override
	public String deleteStudent(Integer id) {
		// TODO Auto-generated method stubtry {
		try {
		connection=JdbcUtil.getJdbcConnection();
		String sqlInsertQuery="delete from student where id=?";
		if(connection!=null) {
			pstmt=connection.prepareStatement(sqlInsertQuery);
		}
		scan=new Scanner(System.in);
		if(pstmt!=null) {
			pstmt.setInt(1, id);
			
			int rowDeleted=pstmt.executeUpdate();
			if(rowDeleted==1) {
				return "success";
			}else {
				return "not found";
			}
	} }catch (SQLException se) {
		// TODO Auto-generated catch block
		se.printStackTrace();
	} catch (IOException ie) {
		// TODO Auto-generated catch block
		ie.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "Failed";
}
}