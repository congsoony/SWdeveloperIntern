package nts.todo.api;

import java.io.IOException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import nts.todo.dao.TodoDao;
import nts.todo.dto.TodoDto;

@WebServlet("/type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringJson = request.getReader().lines().collect(Collectors.joining());// json형식으로 데이터를 가져옴
		ObjectMapper objectMapper = new ObjectMapper();
		TodoDao todoDao = new TodoDao();
		TodoDto todoDto_json = objectMapper.readValue(stringJson, TodoDto.class);// string형인 json을 알맞는 객체에 값을 넣어줌 없는 값은
																			// default임
		todoDao.updateTodo(todoDto_json);
	}

}
