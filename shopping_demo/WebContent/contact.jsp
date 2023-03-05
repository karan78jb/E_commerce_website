<%-- 
    Document   : contact
    Created on : Oct 23, 2022, 5:00:54 PM
    Author     : LENOVO
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.shashi.utility.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
      String name = request.getParameter("name");
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");
//        out.println(name+""+email+""+comments);
        
        Connection con=null;
        try{
       // Class.forName("com.mysql.jdbc.Driver");
       Class.forName("com.mysql.cj.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","1234");
       System.out.println(con);
       System.out.println("Database connection Success ");
        }catch(ClassNotFoundException | SQLException ex){
           // Logger.getLogger(databases.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
        try (PreparedStatement stmt = con.prepareStatement("INSERT INTO SHOPPING.CONTACT VALUES(?,?,?)")) {
               boolean t=false;
                
               stmt.setString(1,name);
                stmt.setString(2,email);
                stmt.setString(3,comments);
                
                
               boolean execute = stmt.execute();
                System.out.println("Insertion completed");
//                out.print("<h2>Successfully added..</h2>");
            
          
                stmt.close();
                 
            }
            catch (SQLException ex) {
           
       }
       
     
      response.sendRedirect("index.jsp?name='+name+'");
        %>
        
        
    </body>
</html>
