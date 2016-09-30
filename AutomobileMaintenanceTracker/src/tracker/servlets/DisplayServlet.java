package tracker.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tracker.Diesel;
import tracker.Electric;
import tracker.Gas;
import tracker.Vehicle;

//location homepage.jsp
//display contents on homepage

@WebServlet(urlPatterns={"/home"})
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisplayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies.

		//get session variable
		HttpSession session = request.getSession();
		ArrayList<Vehicle> queue = (ArrayList<Vehicle>)session.getAttribute("VehicleQueue");
		
		//if queue is null then fill ArrayList with example data
		if (queue == null) {
			
			queue = new ArrayList<Vehicle>();

			queue.add(new Gas("John Smith", "Toyota", "Corolla", "2010", "945253", "Gas"));
			queue.add(new Diesel("Alyssa West", "Jeep", "Grand Cherokee", "2008", "104505", "Diesel"));
			queue.add(new Electric("Nathan Scholls", "Nissan", "Leaf", "2014", "42012", "Electric"));
			queue.add(new Electric("Cameron Bates", "Ford", "Focus", "2005", "141420", "Electric"));
			queue.add(new Gas("Toby Miles", "Honda", "Accord", "2009", "114652", "Gas"));
			queue.add(new Gas("Shayna Lane", "Volkswagen", "Jetta", "2015", "57568", "Gas"));	
			
			
		    session.setAttribute("VehicleQueue", queue);

		}

	    request.setAttribute("list", queue);
	    
	    //forward to /WEB-INF/homepage.jsp
	    request.getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
