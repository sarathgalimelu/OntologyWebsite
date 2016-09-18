import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.tdb.TDBFactory;

public class PeriodicTable extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try {

        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>TDB Query Servlet</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY ALIGN=\"CENTER\" BGCOLOR=\"#FFFFFF\" >");
        out.println("<h3>Periodic Table </h3>");
		out.println("<table border=\"1\"  align=\"center\"><tr>");
		out.println("<td><a href=\"http://tinman.cs.gsu.edu:8080/vgalimelu1/servlet/ElementList\">Elements List</a></td>");
		out.println("<td><a href=\"http://tinman.cs.gsu.edu:8080/vgalimelu1/servlet/GroupList\">Groups</a></td>");
		out.println("<td><a href=\"http://tinman.cs.gsu.edu:8080/vgalimelu1/servlet/BlockList\">Blocks</a></td>");
		out.println("<td><a href=\"http://tinman.cs.gsu.edu:8080/vgalimelu1/servlet/ClassificationList\">Classification</a></td>");
		out.println("<td><a href=\"http://tinman.cs.gsu.edu:8080/vgalimelu1/servlet/PeriodList\">Periods</a></td>");
		out.println("<td><a href=\"http://tinman.cs.gsu.edu:8080/vgalimelu1/servlet/StateList\">Standard States</a></td></tr>");

		
        out.println("</BODY>");
        out.println("</HTML>");

      }catch (Exception e) {
         e.printStackTrace();
        } 
  }

}
