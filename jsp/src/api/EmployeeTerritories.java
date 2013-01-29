package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.EmployeeTerritory;

import com.google.gson.Gson;

import repositories.EmployeeTerritoryRepository;

/**
 * Servlet implementation class EmployeeTerritories
 */
@WebServlet("/api/employees/territories")
public class EmployeeTerritories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EmployeeTerritoryRepository _repository = null;
	private Gson _gson = new Gson();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeTerritories() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	super.init();
    	_repository = new EmployeeTerritoryRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// get the employeeid off the request
			int employeeId = Integer.parseInt(request.getParameter("EmployeeID"));
			
			// get a list of territories for this employee
			List<EmployeeTerritory> employeeTerritories = _repository.listEmployeeTerritories(employeeId);
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
						
			// print the content to the response
			response.getWriter().print(_gson.toJson(employeeTerritories));
			
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

}
