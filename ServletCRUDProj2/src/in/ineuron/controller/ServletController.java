package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		IStudentService stdService = StudentServiceFactory.getStudentService();

		System.out.println("Request URI :: " + request.getRequestURI());
		System.out.println("Path info   :: " + request.getPathInfo());

		if (request.getRequestURI().endsWith("addform")) {
			
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddr = request.getParameter("saddr");

			Student student = new Student();
			student.setName(sname);
			student.setAge(Integer.parseInt(sage));
			student.setAddress(saddr);

			String status = stdService.addStudent(student);

			RequestDispatcher requestDispatcher=null;
			if (status.equals("success")) {
				requestDispatcher = request.getRequestDispatcher("../success.html");
				requestDispatcher.forward(request, response);
				
			} else {
				requestDispatcher = request.getRequestDispatcher("../failure.html");
				requestDispatcher.forward(request, response);
			}
			
		}
		
		if(request.getRequestURI().endsWith("searchform")) {
			String id = request.getParameter("sid");
			
			Student std=stdService.searchStudent(Integer.parseInt(id));
			PrintWriter out = response.getWriter();
			if (std != null) {
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>ID</th><td>" + std.getId() + "</td></tr>");
				out.println("<tr><th>NAME</th><td>" + std.getName() + "</td></tr>");
				out.println("<tr><th>AGE</th><td>" + std.getAge() + "</td></tr>");
				out.println("<tr><th>ADDRESS</th><td>" + std.getAddress() + "</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			} else {
				out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + id
						+ "</h1>");
			}
			out.close();
		}
		
		if(request.getRequestURI().endsWith("deleteform")) {
			String id = request.getParameter("sid");
			String status = stdService.deleteStudent(Integer.parseInt(id));
			
			RequestDispatcher rd=null;
				if (status.equals("success")) {
					rd=request.getRequestDispatcher("../deletesuccess.html");
					rd.forward(request, response);
				} else if (status.equals("failure")) {
					rd=request.getRequestDispatcher("../deletefailure.html");
					rd.forward(request, response);
				} else {
					rd=request.getRequestDispatcher("../deletenotfound.html");
					rd.forward(request, response);
				}
				
			
		}
		
		if (request.getRequestURI().endsWith("editform")) {
			String sid = request.getParameter("sid");

			Student student = stdService.searchStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();
			if (student != null) {
				// display student records as a form data so it is editable
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>" + student.getId() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='" + student.getId() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + student.getName()
						+ "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='" + student.getAge()
						+ "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='" + student.getAddress()
						+ "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
				// display not found message
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " + sid
						+ "</h1>");
				out.println("</body>");
			}
			out.close();
		}
		
		if (request.getRequestURI().endsWith("updateRecord")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddr = request.getParameter("saddr");

			Student student = new Student();
			student.setId(Integer.parseInt(sid));
			student.setAddress(saddr);
			student.setName(sname);
			student.setAge(Integer.parseInt(sage));

			String status = stdService.updateStudent(student);

			RequestDispatcher rd=null;
			if (status.equals("success")) {
				rd=request.getRequestDispatcher("../../updatesuccess.html");
				rd.forward(request, response);
			} else {
				rd=request.getRequestDispatcher("../../updatefailure.html");
				rd.forward(request, response);
			}
		}
	}
}
