package tracker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import tracker.Diesel;
import tracker.Electric;
import tracker.Gas;
import tracker.Job;
import tracker.Vehicle;

//location homepage.jsp
//Submit data from 'Add A Customer' form

@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FormServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get session variable
		HttpSession session = request.getSession();
		ArrayList queue = (ArrayList)session.getAttribute("VehicleQueue");
		
		if (queue == null) {
			
			queue = new ArrayList();

		}

		//read form fields
		String customerName = request.getParameter("customer");
		String vehicleMake = request.getParameter("make");
		String vehicleModel = request.getParameter("model");
		String vehicleYear = request.getParameter("year");
		String vehicleOdometer = request.getParameter("odometer");
		String vehicleType = request.getParameter("type");
		
		//create JSON object to return to Ajax for display
        JSONObject dataObj = new JSONObject();
        dataObj.put("customer", customerName);
        dataObj.put("make", vehicleMake);
        dataObj.put("model", vehicleModel);
        dataObj.put("year", vehicleYear);
        dataObj.put("odometer", vehicleOdometer);
        dataObj.put("type", vehicleType);
		
		//create object based on vehicle type
        //add object to queue ArrayList
        //add object id to JSON object for display
		if(vehicleType.equals("Electric")){
			
			Electric electricObj = new Electric(customerName, vehicleMake, vehicleModel, vehicleYear, vehicleOdometer, vehicleType);

			queue.add(0, electricObj);

			dataObj.put("id", electricObj.getId());

		} else if(vehicleType.equals("Gas")){
			
			Gas gasObj = new Gas(customerName, vehicleMake, vehicleModel, vehicleYear, vehicleOdometer, vehicleType);

			queue.add(0, gasObj);
			
			dataObj.put("id", gasObj.getId());
			
		} else if(vehicleType.equals("Diesel")){
			
			Diesel dieselObj = new Diesel(customerName, vehicleMake, vehicleModel, vehicleYear, vehicleOdometer, vehicleType);

			queue.add(0, dieselObj);

			dataObj.put("id", dieselObj.getId());
			
		} else {
			
			System.out.println("Error");
		}
		
		session.setAttribute("VehicleQueue", queue);

        PrintWriter out = response.getWriter();
        
        out.println(dataObj.toString());

        out.close();
    
		
	}

}
