package org.soen387.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.domain.User;
import org.soen387.services.UserService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class AdminUsersServlet
 */
@WebServlet("/admin/users/*")
public class AdminUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> users = UserService.getInstance().getAllUsers();
		if (users != null) {
			request.setAttribute("users", users);
			request.getServletContext().getRequestDispatcher("/admin/users.jsp").forward(request, response);
		} else {
			System.out.println("Users not found");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(request.getReader());
        int locked = obj.get("locked").getAsInt();
        System.out.println("locked:" + locked);
        int userId = Integer.parseInt(request.getPathInfo().substring(1));
        System.out.println("id:" + userId);
        User user = UserService.getInstance().updateUser(userId, locked); //TODO - Generic REST PUT
        response.setStatus(HttpServletResponse.SC_OK);
	}

}
