package nts.todo.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nts.todo.dao.TodoDao;
import nts.todo.dto.TodoDto;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		TodoDao dao = new TodoDao();
		TodoDto dto = new TodoDto();
		dto.setTitle(req.getParameter("title"));
		dto.setName(req.getParameter("name"));
		dto.setSequence(req.getParameter("sequence").charAt(0)-'0');
		dao.addTodo(dto);
		resp.sendRedirect("main");
	}
}
