package Day02.stmt.member.model.dao;

import java.sql.*; //* 추가!!
import java.util.ArrayList;
import java.util.List;

import Day02.stmt.member.model.vo.Member;

public class MemberDAO {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";

	/*
	 * 여기서 JDBC 코딩
	 * 1.드라이버 등로
	 * 2.DBMS 생성
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기
	 * 6. 자원해제
	 */
	
	//1.회원가입 Controller에서 넘어옴!
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO MEMBER_TBL(MEMBER_ID,MEMBER_PWD, MEMBER_NAME,AGE)" 
				+"VALUES('"+member.getMemberId()+"','"+member.getMemberPwd()+"','"+member.getMemberName()+"',"+member.getAge()+")";
//		query = "INSERT INTO MEMBER_TBL VALUES('','','',)";
		//01/19 22:41 finally 작업!!
		int result = 0; 
		Connection conn= null;
		Statement stmt=null; 
		try {
			conn = this.getConnection(); //줄어둔코드!!
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { //예외가 발생하던 안하던 실행문을 동작시켜줌!!
			try {
				stmt.close(); //트라이캐치해주고 나머지안으로!!
				conn.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	//4.회원정보조회(3번째 finally)
	public List<Member> selectList() {
		// TODO Auto-generated method stub
		//모듈화!!
		String query = "SELECT * FROM MEMBER_TBL"; 
		List<Member> mList = new ArrayList<Member>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rset=null;
		try {
			conn = this.getConnection(); //세번째서라운드캐치!
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			//rset에 테이블 형태로 데이터가 있으나 그대로 사용못함
			// rset member 에 담아주느느 코드를 작성해줘야한다
			// 그럴때 사용하는 rset 의 메소드가 next(),getString(),....이있음
			while(rset.next()) {
				//옮겨준 작업하면 다 지우고 Member member만 남겨둠!!그리고 밑에 코드작성!
				Member member = this.rsetToMember(rset);
				//while 문이 동작하면서 모든 레코드에 대한 정보를
				//mList에 담을수 있도록 add해줌!
				mList.add(member);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//리턴을 null로 하면 nullpointException 발생
		//mList로 리턴해 주어야함!
		return mList;
	}
	//5.회원검색(올려줌)(4번째finally)
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
//		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='user11'";
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='"+memberId+"'";
		Member member = null;
		Connection conn =null;
		Statement stmt =null;
		ResultSet rset=null;
		try {
			conn = this.getConnection();//3번캐치!
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			//옮겨준 작업하면 밑에 코드 적고 Member member = this~안으로 옮겨줌!
			if(rset.next()) {
				member = this.rsetToMember(rset);
				//rest은 Member로 변환되어야함(RsetToMember()
				
			}
			//자원해제
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return member;
		
	}
	//3.회원탈퇴(올려줌)(2번째finally)
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		int result =0;
		//아래 코드는 대소문자 구문 없이 삭제하는 쿼리문!
		String query = "DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		//String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID ='"+memberId+"'";
		Connection conn =null;
		Statement stmt=null;
		try {
			conn = this.getConnection(); //3캐치!
			stmt = conn.createStatement();
			result =stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	//2.회원정보수정(올려줌)
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		int result =0;
		//아래쿼리문 작성 잘하기!!
		//아래 쿼리문 시작 연습코드!!
		//query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '', EMAIL ='', PHONE = '', ADDRESS = '', HOBBY = '' WHERE MEMBER_ID = ''";
//		String query = "UPDATE MEMBER_TBL SET MEMBERPWD ='"+member.getMemberPwd()+"',EMAIL ='"+member.getEmail()+"',PHONE ='"+member.getPhone()+"', ADDRESS='"+member.getAddress()+"',HOBBY ='"+member.getHobby()+"' WHERE MEMBER_ID ='"+member.getMemberId()+"' ";
		//쿼리문 일단 강사님꺼 빌림...
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '"+member.getMemberPwd()
		+"', EMAIL = '"+member.getEmail()
		+"', PHONE = '"+member.getPhone()
		+"', ADDRESS = '"+member.getAddress()
		+"', HOBBY = '"+member.getHobby()
		+"' WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn= this.getConnection();
			stmt = conn.createStatement();//연결
			//쿼리문 실행코드 누락주의!
			result =stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//자원해제
				stmt.close();
				conn.close(); //3번째캐치!
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	//rest 변환!!복사하고!!add스로우해줌!!
	private Member rsetToMember(ResultSet rset) throws SQLException {
		// TODO Auto-generated method stub
		String memberId = rset.getString("MEMBER_ID");
		String memberPwd = rset.getString("MEMBER_PWD");
		String memberName = rset.getString("MEMBER_NAME");  //오타!!!
		String gender = rset.getString("GENDER");
		int age = rset.getInt("AGE");
		String email = rset.getString("EMAIL");
		String phone = rset.getString("PHONE");
		String address = rset.getString("ADDRESS");
		String hobby = rset.getString("HOBBY");
		Date enrollDate = rset.getDate("ENROLL_DATE");
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);//생성자 만들고 시작!!
		return member;
	}
	//Connection 모듈화!!
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub//예외던지기캐치던지기!ADD스로우!
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		return conn;
	}

}
