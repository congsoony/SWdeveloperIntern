package nts.todo.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nts.todo.dao.TodoDao;
import nts.todo.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TodoDao todoDao = new TodoDao();
		List<TodoDto> list = todoDao.getTodos();
		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();
		
		for (TodoDto item : list) {
			if (item.getType().equals("TODO")) {
				todoList.add(item);
			}else if (item.getType().equals("DOING")) {
				doingList.add(item);
			}else {
				doneList.add(item);
			}
		}
		
		request.setAttribute("todolist", todoList);
		request.setAttribute("doinglist", doingList);
		request.setAttribute("donelist", doneList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);

	}
}
