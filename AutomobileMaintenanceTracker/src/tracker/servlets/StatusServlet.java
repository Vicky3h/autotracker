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

import tracker.Job;
import tracker.Vehicle;

//location taskpage.jsp
//Update status of tasks

@WebServlet("/StatusServlet")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StatusServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get form fields
		String vehicleId = request.getParameter("id");
		String item = request.getParameter("item");
		String status = request.getParameter("status");
		
		//get session variable
		HttpSession session = request.getSession();
		ArrayList queue = (ArrayList)session.getAttribute("VehicleQueue");
		
		if (queue == null) {
			
            response.sendRedirect(request.getServletPath() + "/");
            return;

		} else {
			
			//iterate through vehicle ArrayList and update status
			Iterator<Vehicle> iter = queue.iterator();
			while(iter.hasNext()){
			    Vehicle vehicleDetails = iter.next();
				request.setAttribute("currentVehicle", vehicleDetails);
			    if (vehicleDetails.getId().equals(vehicleId)) {
					for (Job object:  vehicleDetails.getTasks()) {
						if (object.getItem().equals(item)) {
							object.setStatus(status);
							break;
						}
					}
			        break;
			    }
			    
			}
		}

		
        PrintWriter out = response.getWriter();
        
        out.println("Success");

        out.close();
	}

}
