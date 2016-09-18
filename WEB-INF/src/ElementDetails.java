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

public class ElementDetails extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
	String value = (String) request.getParameter("val");
    try {
	  //PREFIX table: <http://www.daml.org/2003/01/periodictable/PeriodicTable#>  ;
	  String prefix1 = "PREFIX table: <http://www.daml.org/2003/01/periodictable/PeriodicTable#>  ";
      String prefix2 = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ";
      String prefix3 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
      String prefix = prefix1;
      String queryString = prefix+ "SELECT ?name ?symbol ?number ?standardState ?color ?atomicWeight ?group ?period ?block ?classification ?casRegistryID  { ?element table:name ?name . ?element table:symbol ?symbol. ?element table:atomicNumber ?number. ?element table:standardState ?standardState.?element table:color ?color. ?element table:atomicWeight ?atomicWeight. ?element table:period ?period. ?element table:group ?group. ?element table:block ?block. ?element table:classification ?classification. ?element table:casRegistryID ?casRegistryID. FILTER regex(?name, \""+value+"\", \"i\") } ORDER BY ?number";
	  
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
        out.println("<h3>Elements Details</h3>");
        while(rs.hasNext()){
          QuerySolution qs = rs.nextSolution();
          String name = qs.getLiteral("?name").toString();
		  name=name.substring(0,name.indexOf("^"));
		  
		  String symbol = qs.getLiteral("?symbol").toString();
		  symbol=symbol.substring(0,symbol.indexOf("^"));
		  
          String number = qs.getLiteral("?number").toString();
		  number=number.substring(0,number.indexOf("^"));
		  
		  String color = qs.getLiteral("?color").toString();
		  color=color.substring(0,color.indexOf("^"));
		  
		  String atomicWeight = qs.getLiteral("?atomicWeight").toString();
		  atomicWeight=atomicWeight.substring(0,atomicWeight.indexOf("^"));
		  
		  String group=qs.getResource("?group").toString();
		  group=group.substring(group.lastIndexOf('#')+1,group.length());
		  
		  String period=qs.getResource("?period").toString();
		  period=period.substring(period.lastIndexOf('#')+1,period.length());
		  
		  String block=qs.getResource("?block").toString();
		  block=block.substring(block.lastIndexOf('#')+1,block.length());
		  
		  String standardState=qs.getResource("?standardState").toString();
		  standardState=standardState.substring(standardState.lastIndexOf('#')+1,standardState.length());
		  
		   String classification=qs.getResource("?classification").toString();
		  classification=classification.substring(classification.lastIndexOf('#')+1,classification.length());
		  
		  String casRegistryID = qs.getLiteral("?casRegistryID").toString();
		  casRegistryID=casRegistryID.substring(0,casRegistryID.indexOf("^"));
	
		  out.println("<table  border=\"1\" align=\"center\">");
          out.println("<tr><td><P>Name of the Element:</td><td><b>"+name+"</b></td></tr></P>");
		  out.println("<tr><td><P>Symbol:</td><td>"+symbol+"</td></tr></P>");
		  out.println("<tr><td><P>Element Number:</td><td>"+number+"</td></tr></P>");
		  out.println("<tr><td><P>Element Color:</td><td>"+color+"</td></tr></P>");
		  out.println("<tr><td><P>Standard State:</td><td>"+standardState+"</td></tr></P>");
		  out.println("<tr><td><P>Atomic Weight:</td><td>"+atomicWeight+"</td></tr></P>");
		  out.println("<tr><td><P>Element Group:</td><td>"+group+"</td></tr></P>");
		  out.println("<tr><td><P>Element Period :</td><td>"+period+"</td></tr></P>");
		  out.println("<tr><td><P>Element Block:</td><td>"+block+"</td></tr></P>");
		  out.println("<tr><td><P>Classification:</td><td>"+classification+"</td></tr></P>");
		  out.println("<tr><td><P>casRegistryID:</td><td>"+casRegistryID+"</td></tr></P>");
		  
		  out.println("</table>");
        }
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
