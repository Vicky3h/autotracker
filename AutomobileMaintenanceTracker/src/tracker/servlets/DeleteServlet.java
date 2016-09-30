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

import tracker.Vehicle;

//location homepage.jsp
//delete customer

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get request parameters
		String vehicleId = request.getParameter("id");
		
		//get session variable
		HttpSession session = request.getSession();
		ArrayList queue = (ArrayList)session.getAttribute("VehicleQueue");
		
		//if session variable is null, redirect to home page
		//otherwise iterate through the ArrayList and remove relevant object
		if (queue == null) {
			
            response.sendRedirect(request.getServletPath() + "/");
            return;

		} else {
			
			Iterator<Vehicle> iter = queue.iterator();
			while(iter.hasNext()){
			    Vehicle vehicleDetails = iter.next();
			    if (vehicleDetails.getId().equals(vehicleId)) {
			    	iter.remove();
			        break;
			    }
			    
			}
			
	        PrintWriter out = response.getWriter();
	        
	        out.println(vehicleId);

	        out.close();

		}

		

	}

}
