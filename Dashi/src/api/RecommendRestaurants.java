package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.MongoDBConnection;
import db.MySQLDBConnection;

/**
 * Servlet implementation class RecommendRestaurants
 */
@WebServlet("/recommendation")
public class RecommendRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBConnection connection = new MySQLDBConnection();
	//DBConnection connection = new MongoDBConnection();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendRestaurants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

/*JSONArray array = null;
		
		if (request.getParameterMap().containsKey("user_id")) {
			String userId = request.getParameter("user_id");
			array = connection.recommendRestaurants(userId);
		}
		RpcParser.writeOutput(response, array);*/

		JSONArray array = new JSONArray();
		try {
			if (request.getParameterMap().containsKey("user_id"))
					 {
				String userId = request.getParameter("user_id");
				
				// return some fake restaurants
				JSONObject one = new JSONObject();
				one.put("name", "panda express");
				one.put("location", "downtown");
				one.put("country", "united states");
				array.put(one);
				JSONObject two = new JSONObject();
				two.put("name", "hongkong express");
				two.put("location", "uptown");
				two.put("country", "united states");
				array.put(two);
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RpcParser.writeOutput(response, array);
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String username = "";
		PrintWriter out = response.getWriter();
		if(request.getParameter("username") != null) {
			username = request.getParameter("username");
			out.print("Hello " + username);
		}
		out.flush();
		out.close();
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			JSONObject input = RpcParser.parseInput(request);
		
			if (input.has("user_id") ) {
				/*String userId = (String) input.get("name");
				JSONArray array = (JSONArray) input.get("visited");
				List<String> visitedRestaurants = new ArrayList<>();
				for (int i = 0; i < array.length(); i++) {
					String businessId = (String) array.get(i);
					visitedRestaurants.add(businessId);
				}*/
				RpcParser.writeOutput(response,
						new JSONObject().put("status", "OK"));
			} else {
				RpcParser.writeOutput(response,
						new JSONObject().put("status", "InvalidParameter"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//doGet(request, response);
	}

}
