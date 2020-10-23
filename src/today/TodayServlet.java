package today;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		out.println("<html>");
		out.println("<head><title>현재시간</title>");
		out.println("<link rel=\"stylesheet\" href=\"css/today.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"layer\">");
		out.println("<p class=\"p\"><a href=\"index.html\">메인화면</a></p>");
		out.println("<span class=\"content\">현재시간: ");
		out.println(currentTime.format(formatter));
		out.println("</span>");
		out.println("<span class=\"blank\"</span>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
