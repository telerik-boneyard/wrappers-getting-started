package api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import repositories.AddressRepository;
import models.Address;

/**
 * Servlet implementation class Addresses
 */
@WebServlet("/api/employee/addresses")
public class Addresses extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AddressRepository _repository = null;    
	private Gson _gson = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addresses() {
        super();
        
        _repository = new AddressRepository();
        _gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// get the parameter off the request
			int employeeID = Integer.parseInt(request.getParameter("employeeId"));
			
			List<models.Address> addresses = _repository.listAddresses(employeeID);
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
			
			// print the content to the response
			response.getWriter().print(_gson.toJson(addresses));
		} catch (SQLException e) {
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
