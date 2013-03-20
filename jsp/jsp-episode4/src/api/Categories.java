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

// the servlet answers on the /api/categories endpoint (i.e. localhost/api/categories)
@WebServlet("/api/categories")
public class Categories extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// the data access class
    private repositories.CategoriesRepository _repository = null;   
	
    // this method is called by the servlet automatically when it starts up
    public void init() throws ServletException {
    		super.init();
    		
    		// get the physical path to the sqlite3 database
    		String path = this.getServletContext().getRealPath("data/sample.db");
    		
    		// create a new instance of the repository for categories
    		_repository = new repositories.CategoriesRepository(path);
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categories() {
        super();
        // TODO Auto-generated constructor stub
    }

	// this method response to any requests to this endpoint that are a GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// get a list of all the categories in the database
			List<models.Category> categories = _repository.listCategories();
			
			// create a new Gson serializer
			Gson gson = new Gson();
			
			// set the content type to JSON
			response.setContentType("application/json");
			
			// write the categories to the response and serliaze them as JSON
			response.getWriter().write(gson.toJson(categories));
		} catch (SQLException e) {
			// throw a generic error on failure
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
