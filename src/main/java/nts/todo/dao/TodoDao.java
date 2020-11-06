package nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import nts.todo.dto.TodoDto;

public class TodoDao {
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user21";
	private static final String DB_USER = "user21";
	private static final String DB_PASSWORD = "user21";

	public int addTodo(TodoDto todo) {
		int insertCount = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "insert into todo(title, name, sequence) values(?,?,?)";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			insertCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<TodoDto>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "select id, title, name, sequence, type, date_format(regdate,'%Y.%m.%d') from todo;";
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql)) {
				while (rs.next()) {
					long id = rs.getLong("id");
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					String regdate = rs.getString("date_format(regdate,'%Y.%m.%d')");
					TodoDto dto = new TodoDto(id, name, regdate, sequence, title, type);
					list.add(dto);
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateTodo(TodoDto todo) {
		int updateCount = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "update todo set type = ? where id = ?;";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, todo.getType());
			ps.setLong(2, todo.getId());
			updateCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return updateCount;
	}
	
}
