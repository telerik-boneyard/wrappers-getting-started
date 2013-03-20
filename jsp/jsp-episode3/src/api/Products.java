package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.DataSourceResult;

import com.google.gson.Gson;

import repositories.ProductsRepository;

// responds to requests made to the /api/products endpoint (e.g. localhost/api/product)
@WebServlet("/api/products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// class level instance of the products data access class
	private repositories.ProductsRepository _repository = null;
    
	// this method is called by the servlet automatically when
	// it starts up
    public void init() throws ServletException {
    		super.init();
    		
    		// ask the server for the full path to the sqlite3 database
    		String path = this.getServletContext().getRealPath("data/sample.db");
    		
    		// pass the path to the database to the repository
    		_repository = new ProductsRepository(path);
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Products() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			// get the take and skip parameters that the grid sends off of the request, 
			// setting some default values
			int take = request.getParameter("take") == null ? 5 :
				Integer.parseInt(request.getParameter("take"));
			int skip = request.getParameter("skip") == null ? 0 :
				Integer.parseInt(request.getParameter("skip"));
			
			// create a new DataSourceResult object to send back as the result of this method
			models.DataSourceResult result = new DataSourceResult();
			
			// set the data property to the list of products
			result.setData(_repository.listProducts(skip, take));
			// set the total to the count
			result.setTotal(_repository.getCount());
			
			// create a new Gson object for serializing JSON
			Gson gson = new Gson();
			
			// set the response type to JSON
			response.setContentType("application/json");
			
			// write the DataSourceResult to the response and serialize it
			// to JSON with Gson
			response.getWriter().write(gson.toJson(result));
			
		} catch (Exception e) {
			// if anything goes wrong, send a generic error
			response.sendError(500);
			e.printStackTrace();
		}
	}

}
