package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Products
 */
@WebServlet("/api/products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private repositories.ProductsRepository _repository;   
    private Gson _gson = new Gson();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Products() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	super.init();
    	_repository = new repositories.ProductsRepository(this.getServletContext().getRealPath("data/sample.db"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			models.DataSourceResult result = new models.DataSourceResult();
			
			int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
			int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			
			result.setTotal(_repository.getProductCount());
			result.setData(_repository.listProducts(take, skip));
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
									
			// print the content to the response
			response.getWriter().print(_gson.toJson(result));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// set the content type we are sending back as JSON
			response.setContentType("application/json");
			
	    	int productId = request.getParameter("ProductID") == "" ? 0 : Integer.parseInt(request.getParameter("ProductID"));
	
	    	// get the type of operation
	    	String type = request.getQueryString().trim();
	    	
	    	// parse the rest of the request into an employee object
	    	models.Product product = parseRequest(request);
	    	
	    	if (type.equals("update")) {
	    		// add the product id onto the model and update the product
		    	product.setProductID(productId);
		    	product = _repository.doUpdateProduct(product);
	    	
		    	response.getWriter().print(_gson.toJson(product));
	    	}
		    if (type.equals("create")) {
		    	//create the product
	    		productId = _repository.doCreateProduct(product);
	    		response.getWriter().print(_gson.toJson(productId));
		    }
		    if (type.equals("delete")) {
		    	// delete the product
		    	System.out.println(type);
		    	_repository.doDeleteProduct(productId);
		    }
	    	
		}
		catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}

	}

	private models.Product parseRequest(HttpServletRequest request) {

		models.Product product = new models.Product();

		// VALIDATION SHOULD BE DONE HERE AS WELL
		
		// parse the rest of the information off of the request
		product.setSupplier(new models.Supplier(Integer.parseInt(request.getParameter("Supplier[SupplierID]")), 
				request.getParameter("Supplier[SupplierName]")));
		product.setCategory(new models.Category(Integer.parseInt(request.getParameter("Category[CategoryID]")), 
				request.getParameter("Category[Categoryname]")));
		product.setUnitPrice(Float.parseFloat(request.getParameter("UnitPrice")));
		product.setUnitsInStock(Integer.parseInt(request.getParameter("UnitsInStock")));
		product.setDiscontinued(Boolean.parseBoolean(request.getParameter("Discontinued")));
		product.setProductName(request.getParameter("ProductName"));

		return product;
	}

}
