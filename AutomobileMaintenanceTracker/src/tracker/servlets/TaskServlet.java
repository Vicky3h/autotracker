package tracker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tracker.Job;
import tracker.Vehicle;

//location taskpage.jsp
//Display contents on task page

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TaskServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get form fields
		String code = (String) request.getParameter("code");

		//get session variable
		HttpSession session = request.getSession();
		ArrayList queue = (ArrayList) session.getAttribute("VehicleQueue");

		if (queue == null || code == null) {

			response.sendRedirect(request.getServletPath() + "/");
			return;

		} else if (queue != null) {

			//iterate through queue ArrayList and get tasks
			Iterator<Vehicle> iter = queue.iterator();
			while (iter.hasNext()) {
				Vehicle vehicleDetails = iter.next();
				request.setAttribute("currentVehicle", vehicleDetails);
				if (vehicleDetails.getId().equals(code)) {
					ArrayList<Job> tasks = vehicleDetails.getTasks();
					request.setAttribute("tasks", tasks);
					break;
				}

			}

		}

		//forward to /WEB-INF/taskpage.jsp
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/taskpage.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
