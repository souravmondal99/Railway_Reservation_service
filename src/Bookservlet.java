import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class Bookservlet
 */
@WebServlet("/Bookservlet")
public class Bookservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String uname=request.getParameter("name");
		String uage=request.getParameter("age");
		String jfrom=request.getParameter("departure");
		String jto=request.getParameter("destination");
		String jclass=request.getParameter("coach");
		String jdate=request.getParameter("date");
		String usern=request.getParameter("usern");
		String trnm=request.getParameter("trnm");
		//out.println(uname+uage+jfrom+jto+jclass+jdate+trnm);
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","MYDB","admin");
				if(con==null)
					out.println("connect not created");
				else
				{
					System.out.println("connection created");
					PreparedStatement ps=con.prepareStatement("INSERT INTO RRS_Book VALUES (?,?,?,?,?,?,?,?)");
					ps.setString(1, uname);
					ps.setString(2, uage);
					ps.setString(3, jfrom);
					ps.setString(4, jto);
					ps.setString(5, jclass);
					ps.setString(6, jdate);
					ps.setString(7, trnm);
					ps.setString(8, usern);
					
					int x=ps.executeUpdate();
					if(x>0)
						out.println("");
					else
						out.println("no record inserted!");
					response.setContentType("text/html");
					//PrintWriter out=response.getWriter();
					out.println("<html><head>\r\n" + 
							"	<meta charset=\"utf-8\">\r\n" + 
							"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
							"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
							"	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\r\n" + 
							"\r\n" + 
							"	<title>Online Booking Form</title>\r\n" + 
							"\r\n" + 
							"	<!-- Google font -->\r\n" + 
							"	<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\">\r\n" + 
							"\r\n" + 
							"	<!-- Bootstrap -->\r\n" + 
							"	<link type=\"text/css\" rel=\"stylesheet\" href=\"css/bootstrap.min.css\" />\r\n" + 
							"\r\n" + 
							"	<!-- Custom stlylesheet -->\r\n" + 
							"	<link type=\"text/css\" rel=\"stylesheet\" href=\"css/b_style.css\" />\r\n" + 
							"\r\n" + 
							"	<script type=\"text/javascript\" src=\"js/jquery-1.10.2.min.js\"></script>\r\n" + 
							"    <script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>\r\n" + 
							"\r\n" + 
							"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
							"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n" + 
							"\r\n" + 
							" </head>\r\n" + 
							" <body style=\"background-color: rgb(58, 145, 148);\"><div class=\"container\">\r\n" + 
							"		<div class=\"page-header\">\r\n" + 
							"		   <center><h1>Payment Form </h1></center>\r\n" + 
							"		</div>\r\n" + 
							"	   \r\n" + 
							"		\r\n" + 
							"	   \r\n" + 
							"		<div class=\"container\">\r\n" + 
							"		   <div class=\"row\">\r\n" + 
							"			   <div class=\"col-xs-12 col-md-4 col-md-offset-4\">\r\n" + 
							"				   <div class=\"panel panel-default\">\r\n" + 
							"					   <div class=\"panel-heading\">\r\n" + 
							"						   <div class=\"row\">\r\n" + 
							"							   <h3 class=\"text-center\">Payment Details</h3>\r\n" + 
							"							   <img class=\"img-responsive cc-img\" src=\"http://www.prepbootstrap.com/Content/images/shared/misc/creditcardicons.png\">\r\n" + 
							"						   </div>\r\n" + 
							"					   </div>\r\n" + 
							"					   <div class=\"panel-body\">\r\n" + 
							"						   <form role=\"form\">\r\n" + 
							"							   <div class=\"row\">\r\n" + 
							"								   <div class=\"col-xs-12\">\r\n" + 
							"									   <div class=\"form-group\">\r\n" + 
							"										   <label>CARD NUMBER</label>\r\n" + 
							"										   <div class=\"input-group\">\r\n" + 
							"											   <input type=\"tel\" class=\"form-control\" placeholder=\"Valid Card Number\" />\r\n" + 
							"											   <span class=\"input-group-addon\"><span class=\"fa fa-credit-card\"></span></span>\r\n" + 
							"										   </div>\r\n" + 
							"									   </div>\r\n" + 
							"								   </div>\r\n" + 
							"							   </div>\r\n" + 
							"							   <div class=\"row\">\r\n" + 
							"								   <div class=\"col-xs-7 col-md-7\">\r\n" + 
							"									   <div class=\"form-group\">\r\n" + 
							"										   <label><span class=\"hidden-xs\">EXPIRATION</span><span class=\"visible-xs-inline\">EXP</span> DATE</label>\r\n" + 
							"										   <input type=\"tel\" class=\"form-control\" placeholder=\"MM / YY\" />\r\n" + 
							"									   </div>\r\n" + 
							"								   </div>\r\n" + 
							"								   <div class=\"col-xs-5 col-md-5 pull-right\">\r\n" + 
							"									   <div class=\"form-group\">\r\n" + 
							"										   <label>CV CODE</label>\r\n" + 
							"										   <input type=\"tel\" class=\"form-control\" placeholder=\"CVC\" />\r\n" + 
							"									   </div>\r\n" + 
							"								   </div>\r\n" + 
							"							   </div>\r\n" + 
							"							   <div class=\"row\">\r\n" + 
							"								   <div class=\"col-xs-12\">\r\n" + 
							"									   <div class=\"form-group\">\r\n" + 
							"										   <label>CARD OWNER</label>\r\n" + 
							"										   <input type=\"text\" class=\"form-control\" placeholder=\"Card Owner Names\" />\r\n" + 
							"									   </div>\r\n" + 
							"								   </div>\r\n" + 
							"							   </div>\r\n" + 
							"						   </form>\r\n" + 
							"					   </div>\r\n" + 
							"					   <div class=\"panel-footer\">\r\n" + 
							"						   <div class=\"row\">\r\n" + 
							"							   <div class=\"col-xs-12\">\r\n" + 
							"                                  <form action='pnr1.html' method='post'><input type='submit'  value='Pay & Check PNR'></form><form align=\"center\" action='login.html'>\r\n" + 
							"<input class=\"button1\" type=submit  value=\"LOG OUT\">"+
							"							   </div>\r\n" + 
							"						   </div>\r\n" + 
							"					   </div>\r\n" + 
							"				   </div>\r\n" + 
							"			   </div>\r\n" + 
							"		   </div>\r\n" + 
							"		</div>\r\n" + 
							"	   \r\n" + 
							"		 <style>\r\n" + 
							"		   .cc-img {\r\n" + 
							"			   margin: 0 auto;\r\n" + 
							"		   }\r\n" + 
							"		</style>\r\n" + 
							"	   \r\n" + 
							"	   \r\n" + 
							"	</div></body></html>");
				}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
