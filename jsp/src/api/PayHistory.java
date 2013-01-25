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

import repositories.PayHistoryRepository;

/**
 * Servlet implementation class PayHistory
 */
@WebServlet("/api/employee/payhistory")
public class PayHistory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private PayHistoryRepository _repository = null;
	private Gson _gson = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayHistory() {
        super();
        _repository = new PayHistoryRepository();
        _gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// read the employeeid from the url
			int id = Integer.parseInt(request.getParameter("employeeId"));
			
			// query the database for 
			List<models.PayHistory> payHistories = _repository.listPayHistory(id);			
		
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
							
			// print the content to the response
			response.getWriter().print(_gson.toJson(payHistories));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {

			// pull the parameters off of the HTTP request
			int employeeId = Integer.parseInt(request.getParameter("EmployeeID"));
			String rateChangeDate = request.getParameter("RateChangeDate");
			float rate = Float.parseFloat(request.getParameter("Rate"));
			int payFrequency = Integer.parseInt(request.getParameter("PayFrequency"));
			
			_repository.createPayHistory(employeeId, rateChangeDate, rate, payFrequency);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
