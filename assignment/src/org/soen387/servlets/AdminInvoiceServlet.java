package org.soen387.servlets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.datasource.mappers.AdminMapper;
import org.soen387.datasource.mappers.UserMapper;
import org.soen387.domain.Invoice;
import org.soen387.domain.User;
import org.soen387.util.URLEncodedUtils;

/**
 * Servlet implementation class AdminInvoiceServlet
 */
@WebServlet("/admin/invoice/*")
public class AdminInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    URL fullUrl = new URL(URLEncodedUtils.getFullURL(request));
	    Map<String, List<String>> queryParams = URLEncodedUtils.splitQuery(fullUrl);
	    
		System.out.println("invoice parameters" + queryParams.get("user"));
		int userId = Integer.parseInt(queryParams.get("user").get(0));
		User user = UserMapper.getInstance().fetchUser(userId);
		
		if (user != null) {
			ArrayList<Invoice> invoices = AdminMapper.getInstance().getUserOrders(user);
			request.setAttribute("user", user);
			request.setAttribute("invoices", invoices);
			request.getServletContext().getRequestDispatcher("/admin/invoice.jsp").forward(request, response);
		} else {
			System.out.println("User not found");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
