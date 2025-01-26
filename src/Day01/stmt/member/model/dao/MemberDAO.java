package Day01.stmt.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Day01.stmt.member.model.vo.Member;

public class MemberDAO {
	
	/*
	 * 1.드라이버등록!
	 * 2.DBMS 연결 생성
	 * 3.statement 생성
	 * 4.쿼리문 전송
	 * 5. 결과값받기
	 * 6.자원해제
	 */
	
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";

	//회원전체조회!controller에서 넘어옴!
	public List<Member> selectList() {
		// TODO Auto-generated method stub
		String query="SELECT * FROM MEMBER_TBL";
		//최종적으로 리턴해줘야하는 객체!!
		List<Member> mList = new ArrayList<Member>();
		
		try {
			Class.forName(DRIVER_NAME); //트라이캐치해주고상수화!
			Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD); //2번째캐치!
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			//후처리
			//while 문을 돌면서 resultset의 모든 레코드가
			//mList로 변한된다!
			
			while(rset.next()) {
				System.out.println("ID :" + rset.getString("MEMBER_ID"));
				//rset 객체를 추가해줄수 없으므로 member 객체를 추가
				// 그런데 Member member = new Member(); 는 아무데이터 없이
				//객체 생성된 상태이고 Member 클래스는 아직 작성하지 않음
				// rsetTomember: rset 객체에 있는 필드값을 Member로 converting 필요
				//생성자 안만듬!!만들고 오겠음!!오키!!
				String memberId = rset.getString("MEMBER_ID"); 
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("GENDER").charAt(0);//CHAR를 쓸경우 이코드로!!
				//String gender = rset.getString("GENDER");
				int age = rset.getInt("AGE");
				String email = rset.getString("EMAIL");
				String  phone = rset.getString("PHONE") ;
				String address = rset.getString("ADDRESS");
				String hobby = rset.getString("HOBBY");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
				mList.add(member);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//모든정보를 담은 mList를 리턴!!!
		return mList;
	}
	//1. 회원가입 Controller에서 넘어옴!!
	public int insertMember(Member member) { //int로바꿔줌!
		// TODO Auto-generated method stub
		//DML JDBC 
				String s ="INSERT INTO MEMBER_TBL VALUES('','','','', ,'','','','',DEFAULT)";
//				String query = "INSERT INTO MEMBER_TBL VALUES('"
//				+member.getMemberId()+"','"
//				+member.getMemberPwd()+"','"
//				+member.getMemberName()+"','"
//				+member.getGender()+"',"
//				+member.getAge()+" ,'"
//				+member.getEmail()+"','"
//				+member.getPhone()+"','"
//				+member.getAddress()+"','"
//				+member.getHobby()+"',DEFAULT)"; //무서운코드!!!
				//홋따옴표 ''안에 ""쌍따옴표 넣고 '""' 거기안에 ++ 넣어주고
				// '"++"' 그다음 member.get 시작!!
				//~~VALUES('','','','', ,'','','','',DEFAULT)";
				//~~VALUES('"+memberId()+"','','','', ,'','','','',DEFAULT)";
				//길기때문에 저렇게 아래로 내렸음!!!
				//아래코드는 안내리고 풀 코드!!
				String query = "INSERT INTO MEMBER_TBL VALUES('"+member.getMemberId()+"','"+member.getMemberPwd()+"','"+member.getMemberName()+"','"+member.getGender()+"', "+member.getAge()+",'"+member.getEmail()+"','"+member.getPhone()+"','"+member.getAddress()+"','"+member.getHobby()+"',DEFAULT)";
		int result = 0; //지역변수를 사용하기때문에!!
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query); //int result니깐 위에 void를 int로바꿔줌!
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	//3. 회원검색(아이디) Controller에서 넘어옴!
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
		//아래 코드처럼 쿼리문쓰면 고정이되니깐 변수로 바꿔준다!!
//		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = 'user01'";
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME,PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			//rset을 그대로 사용할수 없어서
			//member로 변환해주는 작업을 해야함(rsetTomember()
			while(rset.next()) {
				memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("GENDER").charAt(0);
				int age = rset.getInt("AGE");
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				String address = rset.getString("ADDRESS");
				String hobby = rset.getString("HOBBY");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
				
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

}
