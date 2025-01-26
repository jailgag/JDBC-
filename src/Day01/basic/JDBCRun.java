package Day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun { 
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; //DBMS연결
	private static final String NAME = "student";
	private static final String PASSWORD = "student";





	public static void main(String[] args) {
		String query ="INSERT INTO STUDENT_TBL VALUES('user02','pass02','이용자','F',20,"
				+"'user02@ols.com','01098765432','서울시 서대문구','독서,달리기',DEFAULT)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 등록
			Connection conn = DriverManager.getConnection(URL,NAME, PASSWORD); //DBMS연결
			//statment생성!
			Statement stmt = conn.createStatement();
			//쿼리문이 select이면 excuteQeury~!!!!(dql)
			//INSERT INTO~~이면(DML) excuteUpdate~~
			int result = stmt.executeUpdate(query);
			if(result >0) {
				System.out.println("데이터가 저장되었습니다");
			}else {
				System.out.println("데이터가 저장되지 않았습니다");
			}
			//자원해제!
			conn.close();
			stmt.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void dqlJDBC() { //첫번째 실습코드
		//1.드라이버 등록
		try {
			//1.드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.dbms 연결
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			//3.statement 생성
			Statement stmt = conn.createStatement();
			//4.sql 전송및 결과받기
			String query ="SELECT *FROM STUDENT_TBL";
			//쿼리문이 select이면 excuteQeury~!!!!(dql)
			//INSERT INTO~~이면 excuteUpdate~~
			ResultSet rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				System.out.println("ID :"+ rset.getString("STUDENT_ID"));
				System.out.println("NAME :"+rset.getString("STUDENT_NAME"));
				System.out.println("AGE :"+ rset.getInt("AGE"));
				System.out.println("DATE :"+rset.getDate("ENROLL_DATE"));
			}
			//6자원해제
			rset.close();
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
