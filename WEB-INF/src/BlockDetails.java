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

public class BlockDetails extends HttpServlet {




  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
String value= (String)request.getParameter("val");


    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
//out.println("value"+value);
    try {
	  //PREFIX table: <http://www.daml.org/2003/01/periodictable/PeriodicTable#>  ;
	  String prefix1 = "PREFIX table: <http://www.daml.org/2003/01/periodictable/PeriodicTable#>  ";
      String prefix2 = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ";
      String prefix3 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
      String prefix = prefix1;
      String queryString = prefix+ "SELECT DISTINCT ?name  {?element table:name ?name. ?element table:atomicNumber ?number. ?element table:block table:"+value+".} ORDER BY ?number";
	  
	  String directory = "/student/vgalimelu1/PERIODIC" ;
      Dataset ds = TDBFactory.createDataset(directory) ;
           
      Query query = QueryFactory.create(queryString);
      QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
      try {
        ResultSet rs = qe.execSelect() ;
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>TDB Query Servlet</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY ALIGN=\"CENTER\" BGCOLOR=\"#FFFFFF\" >");
        out.println("<h3>Group Elements</h3>");
		out.println("<table border=\"1\" align=\"center\">");
        while(rs.hasNext()){
          QuerySolution qs = rs.nextSolution();
          String name = qs.getLiteral("?name").toString();
		
		  name=name.substring(0,name.indexOf("^"));
		 
		 
          out.println("<P><tr><td><a href=\"http://tinman.cs.gsu.edu:8080/vgalimelu1/servlet/ElementDetails?val="+name+"\">"+name+"</a></td></tr></P>");
		  
        }
		out.println("</table>");
        out.println("</BODY>");
        out.println("</HTML>");
      } finally { 
          qe.close() ; 
        }
      }catch (Exception e) {
         e.printStackTrace();
        } 
  }

}
