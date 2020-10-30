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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoDao dao=new TodoDao();
		List<TodoDto> list=dao.getTodos();
		List<TodoDto> todolist=new ArrayList<>();
		List<TodoDto> doinglist=new ArrayList<>();
		List<TodoDto> donelist=new ArrayList<>();
		for(TodoDto item:list) {
			if(item.getType()=="TODO")
				todolist.add(item);
			else if(item.getType()=="DOING")
				doinglist.add(item);
			else
				donelist.add(item);
		}
		request.setAttribute("todolist",todolist);
		request.setAttribute("doinglist",doinglist);
		request.setAttribute("donelist",donelist);
		
		RequestDispatcher requestDispatcher= request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
