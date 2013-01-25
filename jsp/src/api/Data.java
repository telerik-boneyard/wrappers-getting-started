package api;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Data
 */
@WebServlet(description = "A simple class to return data as JSON", urlPatterns = { "/api/productcategories" })
public class Data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Data() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// create a JSON object to send back
		JSONObject obj = new JSONObject();
		JSONArray data = new JSONArray();
		
		// query the database for some data
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/adventureworks", "root", "root");
			Statement categories_stmt = conn.createStatement();
			ResultSet categories = categories_stmt.executeQuery("SELECT ProductCategoryID, Name FROM productcategory");
			
			while(categories.next()) {
				
				JSONObject category = new JSONObject();
				
				int productCategoryId = categories.getInt("ProductCategoryID");
				String name = categories.getString("name");
				
				category.put("id",  productCategoryId);
				category.put("name", name);
				
				PreparedStatement subcategories_stmt = conn.prepareStatement("SELECT ProductSubcategoryID, Name FROM productsubcategory WHERE ProductCategoryID = ?");
				subcategories_stmt.setInt(1, productCategoryId);
				
				ResultSet subcategories = subcategories_stmt.executeQuery();
				
				boolean hasChildren = subcategories.getFetchSize() > 0;
				
				category.put("hasChildren", true);
				
//				JSONArray children = new JSONArray();
//				
//				
//				while(subcategories.next()) {
//					
//					JSONObject subcategory = new JSONObject();
//					
//					subcategory.put("id", subcategories.getInt("ProductSubcategoryID"));
//					subcategory.put("name", subcategories.getString("Name"));
//					
//					children.add(subcategory);
//				}
//				
//				category.put("children", children);
//				
				data.add(category);
//				
//				subcategories.close();
//				subcategories_stmt.close();
			}
			
			obj.put("msg", "ok");
			obj.put("data", data);
			
			categories.close();
			categories_stmt.close();
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obj.put("msg", "wah wah...");
		}
		
		// set the content type we are sending back as JSON
		response.setContentType("application/json"); 
		
		// print the content to the response
		response.getWriter().print(obj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
