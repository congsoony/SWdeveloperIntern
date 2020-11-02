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

@WebServlet("/type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputData = request.getReader().lines().collect(Collectors.joining());
		
		System.out.println(inputData);
	}

}
