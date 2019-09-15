package br.barretuino.visual;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.barretuino.repository.ConnectionFactory;

/**
 * Servlet implementation class svlGrafico
 * http://localhost:8080/MedidorLuminosidadeWeb/svlGrafico?intervalo=10&tempo=5
 */
@WebServlet("/svlGrafico")
public class svlGrafico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public svlGrafico() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// acerta tipo MIME para a resposta
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();  

		out.println("<html>");
		out.println("<head>");
		out.println("<script type='text/javascript'");
		out.println("src=\"https://www.google.com/jsapi?autoload={");
		out.println("'modules':[{");
		out.println("'name':'visualization',");
		out.println("'version':'1',");
		out.println("'packages':['corechart']");
		out.println("}]");
		out.println("}\"></script>");
		out.println("<script type='text/javascript'>");
		out.println("google.setOnLoadCallback(drawChart);");

		out.println("function drawChart() {");
		out.println("var data = google.visualization.arrayToDataTable([");
		out.println( "['Periodo', 'Medida'],");
		
		//Consulta Medidas persistidas
		ResultSet rs, rsAcumulado;
		String medida, data, hora;

		//definição do tempo de consulta
		int intervalo = 60;
		if (!request.getParameter("intervalo").equals(""))
			intervalo = Integer.parseInt(request.getParameter("intervalo"));
		
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement stmt = conexao.createStatement();
				
			//Medidas para obtenção do gráfico
			String sql = "SELECT * FROM `PJGERENCIA`.`MEDIDAS` WHERE DATA BETWEEN DATE_SUB(NOW(), INTERVAL " + intervalo + " DAY) AND NOW()";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				out.println("['" + rs.getString(2) + "'," + rs.getFloat(3) + "],");
			}
			stmt.close();
			conexao.close();
		} catch (SQLException sqle) {			
			out.println("Falha ao Conectar-se ao SGBD<br>" + sqle);
			sqle.printStackTrace();
		}
		
		out.println("]);");

		out.println("var options = {");
		out.println("title: '',");
		out.println("curveType: 'function',");
		out.println("  legend: { position: 'bottom' }");
		out.println("};");

		out.println("var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));");

		out.println("chart.draw(data, options);");
		out.println("}");
		out.println("</script>");
		
		//Agendamento do Reflesh dos dados de Sistema  
		int tempo = 10;
		if(!request.getParameter("tempo").equals(""))
			out.println("<meta http-equiv='refresh' content='" + request.getParameter("tempo") + "'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center><h1>Sistema de Gerência</h1>");

		//Medidas do Acumulador
		String sql = "SELECT SUM(VALOR)/COUNT(VALOR) AS 'MEDIDA' FROM `PJGERENCIA`.`MEDIDAS` WHERE DATA BETWEEN DATE_SUB(NOW(), INTERVAL " + intervalo + " DAY) AND NOW()";
		
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement stmt = conexao.createStatement();
			
			//Inteiros
			DecimalFormat df = new DecimalFormat("#,##0");
			
			rsAcumulado = stmt.executeQuery(sql);
			while (rsAcumulado.next()) {				
				out.println("<h2>Média: " + df.format(rsAcumulado.getDouble(1)) + " </h2>");
			}
			stmt.close();
			conexao.close();
		}catch (SQLException sqle) {			
			out.println("Falha ao Conectar-se ao SGBD<br>" + sqle);
			sqle.printStackTrace();
		}
		
		out.println("<div id='curve_chart' style='width: 900px; height: 500px'></div>");
		out.println("</center></body>");
		out.println("</html>");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}