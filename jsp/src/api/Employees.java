package api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import repositories.EmployeeRepository;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/api/employees")
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private repositories.EmployeeRepository _repository = null; 
	private Gson _gson = new Gson();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employees() {    	
    	super();
    }
    
    public void init() throws ServletException {
    	super.init();
    	
    	_repository = new EmployeeRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// get the managerId off of the request
			int managerId = request.getParameter("EmployeeID") == null ? 0 : Integer.parseInt(request.getParameter("EmployeeID"));
			
			List<Employee> employees = _repository.listEmployees(managerId);
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
						
			// print the content to the response
			response.getWriter().print(_gson.toJson(employees));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
