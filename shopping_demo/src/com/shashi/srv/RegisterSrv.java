package com.shashi.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.UserBean;
import com.shashi.dao.UserDao;
import com.shashi.dao.UserDaoImpl;
import com.shashi.utility.MailMessage;

/**
 * Servlet implementation class RegisterSrv
 */
@WebServlet("/RegisterSrv")
public class RegisterSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RegisterSrv() {
        super();
       
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                MailMessage ml=new MailMessage();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String userName = request.getParameter("username");
		String mobileNo = request.getParameter("mobile");
		String emailId = request.getParameter("email");
		String address = request.getParameter("address");
		int pinCode = Integer.parseInt(request.getParameter("pincode"));
		String password = request.getParameter("password");
		
		UserBean user = new UserBean(userName,mobileNo,emailId,address,pinCode,password);
		
		UserDaoImpl dao = new UserDaoImpl();
		
		String status = dao.registerUser(user);
		ml.registrationSuccess(emailId,userName);
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		
		request.setAttribute("message", status);
		
		rd.forward(request, response);
	}

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

}
