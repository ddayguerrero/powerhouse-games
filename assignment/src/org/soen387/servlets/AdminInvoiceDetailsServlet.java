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

import org.soen387.domain.InvoiceDetails;
import org.soen387.services.AdminService;
import org.soen387.util.URLEncodedUtils;

import com.google.gson.Gson;

/**
 * Servlet implementation class AdminInvoiceDetailsServlet
 */
@WebServlet("/admin/details/*")
public class AdminInvoiceDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInvoiceDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		URL fullUrl = new URL(URLEncodedUtils.getFullURL(request));
	    Map<String, List<String>> queryParams = URLEncodedUtils.splitQuery(fullUrl);
	    System.out.println("invoice details parameters" + queryParams.get("invoice"));
	    int invoiceId = Integer.parseInt(queryParams.get("invoice").get(0));
	    
	    ArrayList<InvoiceDetails> invoices = AdminService.getInstance().getOrderDetails(invoiceId);
		System.out.println("INVOICE DETAILS: " + invoices.size());
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		Gson gson = new Gson();
		String json = gson.toJson(invoices);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
