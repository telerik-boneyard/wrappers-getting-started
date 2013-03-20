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

// this servlet responds to any requests made to the api/suppliers endpoint (i.e. localhost/api/suppliers)
@WebServlet("/api/suppliers")
public class Suppliers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// the data access class
    private repositories.SuppliersRepository _repository;
	
    // this method is called automatically when the servlet starts up
	public void init() throws ServletException {
		super.init();
		
		// get the physical path to the sqlite3 database
		String path = this.getServletContext().getRealPath("data/sample.db");
		_repository = new repositories.SuppliersRepository(path);
	}
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Suppliers() {
        super();
        // TODO Auto-generated constructor stub
    }

	// this method is called when a GET request is made
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// get the suppliers from the database
			List<models.Supplier> suppliers = _repository.listSuppliers();
			
			// create a new gson object for serializing to json
			Gson gson = new Gson();
			
			// set the response as JSON
			response.setContentType("application/json");
			
			// write the suppliers to the repsonse and serialize them as a JSON string
			response.getWriter().write(gson.toJson(suppliers));
		} catch (SQLException e) {
			// if anything goes wrong, throw a generic error
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

}
