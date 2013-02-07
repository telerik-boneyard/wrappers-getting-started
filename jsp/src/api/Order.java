package api;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repositories.OrderRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Order
 */
@WebServlet("/api/orders")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private repositories.OrderRepository _repository = null; 
	private Gson _gson = new Gson();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	super.init();
    	
    	_repository = new OrderRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			String customerId = request.getParameter("CustomerID");
			
			List<models.Order> orders = _repository.listOrders(customerId);
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
						
			// print the content to the response
			response.getWriter().print(_gson.toJson(orders));
			
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
