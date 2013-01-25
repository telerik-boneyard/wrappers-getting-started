package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/api/employees")
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employees() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// create a JSON object to send back
		JSONObject obj = new JSONObject();
		JSONArray data = new JSONArray();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/adventureworks", "root", "root");
			PreparedStatement stmt = getEmployeesStmt(request.getParameter("id"), conn);
						
			ResultSet rs = stmt.executeQuery();
					
			while(rs.next()) {
						
				JSONObject person = new JSONObject();
						
				person.put("id", rs.getInt("EmployeeID"));
				person.put("managerId", rs.getString("ManagerID"));
				person.put("name", rs.getString("Name"));
				person.put("hasChildren", rs.getInt("DirectReports") > 0);
				
				data.add(person);
			}
			
			rs.close();
			stmt.close();
			
			obj.put("msg", "ok");
			obj.put("data", data);
			
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				obj.put("msg", "sad trombone...");
			}
			
			// set the content type we are sending back as JSON
			response.setContentType("application/json"); 
			
			// print the content to the response
			response.getWriter().print(obj);
		}

	protected PreparedStatement getEmployeesStmt (String managerId, Connection conn) {
		
		PreparedStatement stmt = null;
		
		try {
			
			String query = "SELECT e.EmployeeID, e.ManagerID, CONCAT(c.FirstName, ' ', c.LastName) AS Name, " +
						   "(SELECT COUNT(*) FROM employee WHERE ManagerID = e.EmployeeID) AS DirectReports " +
	 					   "From employee e " +
	 					   "JOIN contact c ON e.ContactID = c.ContactID ";
			
			if (managerId != null) {
				query += "WHERE ManagerID = ?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(managerId));
			}
			else {
				query += "WHERE ManagerID IS NULL";
				stmt = conn.prepareStatement(query);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stmt;
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
