package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CustomerResponse;
import models.Employee;

import com.google.gson.Gson;

import repositories.CustomerRepository;

/**
 * Servlet implementation class Customers
 */
@WebServlet("/api/customers")
public class Customers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private repositories.CustomerRepository _customerRepository; 
	private Gson _gson = new Gson();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customers() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	super.init();
 
    	_customerRepository = new CustomerRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		models.CustomerResponse customers = new CustomerResponse();
		
		try {
			
			int employeeId = request.getParameter("EmployeeID") == null ? 0 : Integer.parseInt(request.getParameter("EmployeeID"));
			int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
			int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			
			customers.setCount(_customerRepository.getCustomerCount(employeeId));
			customers.setData(_customerRepository.listCustomers(employeeId, take, skip));
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
						
			// print the content to the response
			response.getWriter().print(_gson.toJson(customers));
			
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
