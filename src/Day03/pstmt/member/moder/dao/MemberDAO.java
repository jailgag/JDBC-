package Day03.pstmt.member.moder.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Day03.pstmt.member.moder.vo.Member;



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
		//**위에코드 day02에서 복붙했는데 지금보니GENDER 빼먹었음....그래서GENDER는 빼고 실습!!
		
		//		query = "INSERT INTO MEMBER_TBL VALUES('','','',)";
		//01/19 22:41 finally 작업!!
		//01/27 pstmt 03실습!!
		//아래코드 시작!!*1번!!
		query ="INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, AGE) VALUES(?,?,?,?)";
		
		int result = 0; 
		Connection conn= null;
		Statement stmt=null; 
		//*2.
		PreparedStatement pstmt = null; //*2.작성!
		
		try {
			conn = this.getConnection(); //줄어둔코드!!
			stmt = conn.createStatement();
			
			//**3
			pstmt = conn.prepareStatement(query);//**3적어주고 위치홀더셋팅!!
			pstmt.setString(1, member.getMemberId()); //**4 위치홀더셋팅!!
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(4, member.getAge());
			//**위에 위치홀더가 순서가바뀌어도 상관없다!!
			//**중요한건1.번이라는 자리에 ID만 있으면된다!!
			//**나머진 상관없다!!!!
			
			//result = stmt.executeUpdate(query);**5.아래코드 적어주고 이코드는 주석!!
			
			result = pstmt.executeUpdate();//**5.쿼리문실행문!!하고 위코드 주석!!
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { //예외가 발생하던 안하던 실행문을 동작시켜줌!!
			try {
				stmt.close(); //트라이캐치해주고 나머지안으로!!
				pstmt.close(); //*6. 자원해제적어줌!!
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
		
		
		//***이것도 pstmt로 바꿔줌??강사님 깃허브보고 셋팅!!
		//List는 안건드리신거같음!!
		
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
		/*
		 * 1. 쿼리문에 위치홀더(?) 
		 * 2. PreparedStatement 생성
		 * 3.위치홀더에 값 셋팅
		 * 4. 쿼리문 실행(query)문 전달x
		 */
//		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='user11'";
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='"+memberId+"'";
		//위코드대신~아래코드 물음표로 대체!!!
		query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?"; //*1.
		Member member = null;
		Connection conn =null;
		Statement stmt =null;
		PreparedStatement pstmt = null; //*2.이거 선언을하고!
		ResultSet rset=null;
		try {
			conn = this.getConnection();//3번캐치!
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);//*3.쿼리문동작!코드작성
			
			pstmt.setString(1, memberId);//*4.위치홀더가1개니깐(ID) 1개만작성!!쿼리문 실행준비완료!!
			
			//rset = stmt.executeQuery(query); ****7.<--이코드있으면
			//ORA-01008: 일부 변수가 바인드되지 않았습니다.그래서 주석처리!!
			
			
			rset = pstmt.executeQuery();//5* 이코드 작성!!!쿼리가 없다!!!
			
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
				pstmt.close();//*6.pstmt로 닫아주기!!
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
		//**1.query문 재 작성 아래코드
		query ="DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = ? ";
		
		Connection conn =null;
		Statement stmt=null;
		
		//**2 PreparedStatement작성!!
		PreparedStatement pstmt = null; //**2작성후!!
		
		try {
			conn = this.getConnection(); //3캐치!
			stmt = conn.createStatement();
			
			//**3 pstmt 작성
			pstmt = conn.prepareStatement(query);//**3작성후위치홀더셋팅!!
			//**4.위치홀더 작성
			pstmt.setString(1, memberId); //조금 다르다??get인데 아님!!위치홀더셋팅후!!
			
			//result =stmt.executeUpdate(query);**5작성후지움!!(주석처리!!)
			
			//**5.위치홀더 작성후 result 위에코드 지우고(주석처리함)셋팅
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				pstmt.close();//**6. 자원닫아줌!!
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
		
		//**1.query 문 재작성!
		//update니깐DML!!
		//**원래는 위에 코드 String query =~~는 지워야함!!
		query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ? ,EMAIL = ? , PHONE = ? , ADDRESS = ? , HOBBY = ? WHERE MEMBER_ID = ?";
		
		Connection conn = null;
		Statement stmt = null;
		//**2. pstmt 작성 아래코드
		PreparedStatement pstmt = null; //*2작성후!
		
		try {
			conn= this.getConnection();
			stmt = conn.createStatement();//연결
			
			//**3. 아래코드 작성!!
			pstmt = conn.prepareStatement(query);//**3 작성후!!
			
			//**4.위치홀더 셋팅!!아래코드!!
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getMemberId());
			
			//쿼리문 실행코드 누락주의!
			//result =stmt.executeUpdate(query);
			
			//**5. 쿼리문 실행코드 작성!!위result = stmt~는지우기!!주석처리!
			result = pstmt.executeUpdate();//**5 작성후!!!
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
				pstmt.close();//***6.자원해제!!!
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
