package nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nts.todo.dto.TodoDto;

public class TodoDao {
	private static String dburl = "jdbc:mysql://10.113.116.52:13306/user21";
	private static String dbUser = "user21";
	private static String dbpasswd = "user21";
	
	public int addTodo(TodoDto todo) {
		int insertCount = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "insert into todo(title, name, sequence) values(?,?,?)";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
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
	public List<TodoDto> getTodos(){
		List<TodoDto> list=new ArrayList<TodoDto>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="select id, title, name, sequence, type, regdate from todo";
		try(Connection conn=DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			try(ResultSet rs=ps.executeQuery(sql)){
				while(rs.next()) {
					long id=rs.getLong("id");
					String title=rs.getString("title");
					String name=rs.getString("name");
					int sequence =rs.getInt("sequence");
					String type=rs.getString("type");
					String regdate=rs.getString("regdate");
					regdate=DateFormat(regdate);
					TodoDto dto=new TodoDto(id, name, regdate, sequence, title, type);
					list.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public String DateFormat(String regdate) {
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat afterFormat=new SimpleDateFormat("yyyy.MM.dd");
		Date tempDate=null;
		try {
			tempDate=beforeFormat.parse(regdate);
			regdate=afterFormat.format(tempDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return regdate;
	}
	public int updateTodo(TodoDto todo) {
		int updateCount=0;
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql="update todo set type = ? where id = ?;";
			ps=conn.prepareStatement(sql);
			ps.setString(1,todo.getType());
			ps.setLong(2,todo.getId());
			updateCount=ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				}catch(Exception ex) {}
			}
			if(conn!=null) {
				try {
					conn.close();
				}catch(Exception ex) {}
			}
		}
		return updateCount;
	}
}
