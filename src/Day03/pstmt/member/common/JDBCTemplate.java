package Day03.pstmt.member.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";
	
	/*
	 * Connection에 싱글톤을 적용하지 않은이유!!
	 * Connection 싱글톤을 적용하려는 이유는 Connection Pool(연결을 생성해서 Pool에넣고
	 * 재사용할수 있는 기술) 을 사용하려고 하는것이지만 싱글톤을
	 * 적용하고 Connection Pool이
	 * 동작하는 코드는 없기때문에 적용하지 않음!!!!!
	 * 
	 */

	//여기다가 먼저 스타트!!!할걸!!다시 시작!!01/27 16:50 
	//만들고 Service 만들기!!오키!!
	
	
	//Singleton 만들고 Run에서 코드작성후 여기로 넘어왔음!!
	//아래코드 1~3까지 Singleton 에서 적은 코드랑 비슷하다!!
	private static JDBCTemplate instance;//2. 코드작성후!
	
	//4.코드작성후!(클래스가 아니니깐 생성자 만들필요없다!!)
	//코드 작성후 맨아래 public Connection getConnection() throws~
	//로 이동해서 static 추가!!5.!!!
	
	//아래 코드는 싱글톤패턴적용필요가 없어서 지운다!!!
	//MemberService에서 클래스로 JDBCTemplate.getConnection();
	//바꾼 후 에러 나서 아래 코드는 지운다!!주석처리!!
	//지우고나서 맨아래 
	//public static Connection getConnection() thro~~~
	//로 이동!! ***!!!!!1-1
	
	//private static Connection conn;(지우는코드이나 주석처리함!!)
	
	//1.생성자 만들고! public으로 만들었으나 2.코드작성후 private로변경!
	private JDBCTemplate() {
		//아래에서 여기로 이동했음!!!Class.forName(DRIVER_NAME);
		//생성자안에서 드라이버등록을해준다!!!
		//그러면 아래
		//public Connection getConnection() throws ClassNotFoundException, 
		//에서 ClassNotFoundException?안해도된다??다시 아래로 이동!!
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//3.아래코드 작성
	//public static JDBCTemplate getjdbcTemplate() {
	//원래코드는 위코드인데 아래코드로 수정함...
	//그리고 Service로 이동해서 똑같이 바꾸러감!!
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	//**정리!!Connection getConnection 에도 싱글톤패턴적용함!!!
	
	//5.이동후 아래 코드 변경!! 변경전 코드는 생략해서 적음..
	//변경전public Connection getConnection(
	//변경후~public static Connection getConnection(
	//static 이 추가됨!!추가하고 6.으로 이동!!
	
	//1.2~ (1.1)수정후 아래 코드 static 다시 지움....이번엔
	//주석처리 안하고 지움!!!1.2가 끝나면1.3으로 이동!!
	
	//아래 ClassNotFoundException,지움!!왜?
	//private JDBCTemplate() {~~생성자 안에서
	//(ClassNotFoundException 을 쓰고 있기때문에 아래에서 지움!!
	//그럼 다시 memberService로 이동해서 
	//ClassNotFoundException 다 지우로 이동!!
	public Connection getConnection() throws  SQLException {
		
		//1.3이동하고 아래코드 작성!!!
		Connection conn = null; //1.3추가코드완료!!
		
		//6.아래 코드 작성후!
		//Class.forName(DRIVER_NAME);
		//Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		//코드를 if문 안으로 넣어줌!!!7.으로 이동!!해서 
//		if(conn == null) {
//			
//		}
	//============================================	
		
		if(conn == null) {
			//아래코드 남겨둠!!주석처리!!
			//복붙해서 위로 올라감!!!!
			
			//Class.forName(DRIVER_NAME);
			
			//아래코드 conn에서 에러:The value of the local variable conn is not used
			//노란에러임!! 노란에러에서 Connection conn =
			//에서 Connection 빼주니 사라짐!!
			//7. 아래코드에서 Connection conn = DriverM~
			//Connection빼줌!!!!빼주기전 코드는 주석처리해줌!!
//			Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			}
		return conn;
	}
}
