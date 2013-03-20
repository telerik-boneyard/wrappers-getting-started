package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import repositories.EmployeesRepository;

// the servlet answers on the /api/employees endpoint (i.e. localhost/api/employees) 
@WebServlet("/api/employees")
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// the data access class
    private EmployeesRepository _repository = null;
	
    // this method is called automatically by the servlet when it
    // starts up
    public void init() throws ServletException {
    		super.init();
    	
    		// send the path to the sqlite3 database to the repository
    		String path = this.getServletContext().getRealPath("data/sample.db");
    		
    		// create a new instance of the repository
    		_repository = new EmployeesRepository(path);
    }
    
    public Employees() {
        super();
        // TODO Auto-generated constructor stub
    }

    // this method responds to a GET request to /api/employees
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// parse the employeeid off of the request. set it to 0 if one isn't present
			int id = request.getParameter("EmployeeID") == null ? 0 : 
				Integer.parseInt(request.getParameter("EmployeeID"));
			
			// get a list of the employees from the database who report to the employee
			// with the id that was passed
			List<models.Employee> employees = _repository.listEmployees(id );
			
			// create an instance of the Gson JSON library
			Gson gson = new Gson();
			
			// set the response type to JSON
			response.setContentType("application/json"); 
			
			// write the list of employees to the repsonse using Gson to 
			// serialize them into a JSON string
			response.getWriter().write(gson.toJson(employees));
			
		} catch (Exception e) {
			
			// if anything goes wrong, just throw a generic error
			response.sendError(500);
			e.printStackTrace();
		
		}
	}
}
