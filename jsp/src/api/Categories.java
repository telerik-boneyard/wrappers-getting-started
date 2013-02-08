package api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repositories.CategoryRepository;

import com.google.gson.Gson;

/**
 * Servlet implementation class Categories
 */
@WebServlet("/api/categories")
public class Categories extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private repositories.CategoryRepository _repository = null;
    private Gson _gson = new Gson();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categories() {
        super();
    }
    
    public void init() throws ServletException {
    	super.init();
    	_repository = new CategoryRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<models.Category> categories = new ArrayList<models.Category>();
		
		try {
			
			categories = _repository.listCategories();
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
						
			// print the content to the response
			response.getWriter().print(_gson.toJson(categories));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

}
