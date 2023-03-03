package in.ineuron.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("ENTER UR CHOICE, PRESS[1/2/3/4/5]::  ");
			String option = br.readLine();

			switch (option) {
			case "1":
				insertStudent();
				break;
			case "2":
				searchStudent();
				break;
			case "3":
				updateStudent();
				break;
			case "4":
				deleteStudent();
				break;
			case "5":
				System.out.println("******* Thanks for using the application *****");
				System.exit(0);
			default:
				System.out.println("Invalid option plz try agin with valid options....");
				break;
			}

		}

	}

	public static void updateStudent() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the student id to be updated :");
		String sid=br.readLine();
		
		IStudentService studentService=StudentServiceFactory.getStudentService();
		Student student=studentService.searchStudent(Integer.parseInt(sid));
		if(student!=null) {
			Student newStudent=new Student();
			System.out.println("Student id :"+student.getId());
			newStudent.setId(student.getId());
			System.out.println("Student oldName :"+student.getName()+" Enter new Name :");
			String newName=br.readLine();
			if(newName.equals("")||newName=="") {
				newStudent.setName(student.getName());
			}else {
				newStudent.setName(newName);
			}
			
			System.out.println("Student oldAge :"+student.getAge()+" Enter new age :");
			String newage=br.readLine();
			if(newage.equals("")||newage=="") {
				newStudent.setAge(student.getAge());
			}else {
				newStudent.setAge(Integer.parseInt(newage));
			}
			
			System.out.println("Student oldAddress :"+student.getAddress()+" Enter new Address :");
			String newAddress=br.readLine();
			if(newAddress.equals("")||newAddress=="") {
				newStudent.setAddress(student.getAddress());
			}else {
				newStudent.setAddress(newAddress);
			}
			String status=studentService.updateStudent(newStudent);
			if(status.equalsIgnoreCase("success"))
				System.out.println("record updated succesfully");
			else
				System.out.println("record updation failed");
			
		}else {
			System.out.println("student record not available");
		}
	}

	public static void deleteStudent() {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Student id");
		int id=scan.nextInt();
		IStudentService studentService=StudentServiceFactory.getStudentService();
		String msg=studentService.deleteStudent(id);
		if(msg.equalsIgnoreCase("success")) {
			System.out.println("record deleted succesfully");
		}
		else if(msg.equalsIgnoreCase("not Found"))
			System.out.println("record not available for id "+id);
	}
	
	public static void searchStudent() {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Student id");
		int id=scan.nextInt();
		IStudentService studentService=StudentServiceFactory.getStudentService();
		Student std=studentService.searchStudent(id);
		if(std!=null) {
			System.out.println("id\tname\tage\taddress");
			System.out.println(std.getId()+"\t"+std.getName()+"\t"+std.getAge()+"\t"+std.getAddress());
		}
		else
			System.out.println("record not found for id "+id);
	}

	private static void insertStudent() {
		IStudentService studentService=StudentServiceFactory.getStudentService();
		Scanner scan=new Scanner(System.in);
		System.out.println("enter the name");
		String sname=scan.next();
		
		System.out.println("enter the age");
		int sage=scan.nextInt();
		
		System.out.println("enter the address");
		String sadd=scan.next();
		
		String msg=studentService.addStudent(sname, sage, sadd);
		if(msg.equalsIgnoreCase("success"))
			System.out.println("record inserted succesfully");
		else
			System.out.println("record insertion failed");
	}
}
