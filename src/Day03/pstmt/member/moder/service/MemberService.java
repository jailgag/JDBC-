package Day03.pstmt.member.moder.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Day03.pstmt.member.common.JDBCTemplate;
import Day03.pstmt.member.moder.dao.MemberDAO;
//import Day03.pstmt.member.common.JDBCTemplate;
//import Day03.pstmt.member.moder.dao.MemberDAO;
import Day03.pstmt.member.moder.vo.Member;

public class MemberService {
		
	//여기까지 서비스 완료!!!17:~~
	//아래 코드 1~5 누락주의!!!
	//*클래스로 변경후 아래 jdbcTemplate 파란색 노란색에러!
	//에러:The value of the field MemberService.jdbcTemplate is not used
	private JDBCTemplate jdbcTemplate; //1.
	
	//mDAO 에러?The value of the field MemberService.mDAO is not used//철자mDAO 라고 적어서 에러!!
	private MemberDAO mDao;//2.
	
	
	//*Singleton 적용후 아래 new JDBCTemplte 에러:The constructor Singleton() is not visible
	//뜸!!!!이유는 JDBCTemplte에서 JDBCTemplte가 private니깐!!
	//그래서 jdbcTemplate = new JDBCTemplate(); 코드는 주석처리!
	//새로 작성!!후 에러사라짐!!!
	public MemberService() { //3.
		//jdbcTemplate = new JDBCTemplate(); //4.객체생성!
		//아래코드jdbcTemplate = JDBCTemplate.getjdbcTemplate();
		//로 변경!!!
		
		
		//jdbcTemplate = JDBCTemplate.getjdbcTemplate();
		//위에코드는 원래 코드 아래코드는 바뀐코드!!
		//JDBCTemplate에서 바꿔주고 밑에코드도 바꿔줌!!
		jdbcTemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO(); //5.
	}
	
	
	//private JDBCTemplate jdbcTemplate;//1.
	//private MemberDAO mDao;//2.
	
	//public MemberService() { //3
		//jdbcTemplate =  new JDBCTemplate(); //4
		//mDao = new MemberDAO(); //5.!!
		//1.~5까지 누락주의!!!
	//**위에코드는 다시 시작했기때문에 주석처리후 다시 시작!!
	//아래코드가 원랜실습코드 public void insert~~였는데 
	//Controller 에 insertMember가 int형이였기때문에 void를 int로 변경!!
	
	//***######service는 연결을 전달하는 역할!!*****######
	//회원가입!!
	public int insertMember(Member member) { //서라운드트라이캐치!
		int result =0;
		Connection conn = null;
		try {
			
			//**JDBCTemplate에서 public static Connection getConnection() throw
			//부분을 싱글톤 패턴적용후 여기로 넘어왔는데 아래코드
			//conn = jdbcTemplate.getConnection(); 노란줄 에러
			//The static method getConnection() from the type JDBCTemplate should be accessed in a static way
			//conn = JDBCTemplate.getConnection();에서
			//JDBCTemplate~부분을 콘스로 빨간색으로 임포트?해서바꿔줌....
			//어떻게 바꿨지??...
			//클래스로 바꿔준다!!의미어렵네....
			//static 으로 변경해줬기때문에 ..정리 안됨....
			//기본기부터 해야 알듯....지금은패스!!!
			
			//***********나의팁?????위에 에러코드로 
			//from the type JDBCTemplate should be accessed in a static way
			//로 찾아볼것!!!!
			
			//파란색을 빨간색으로 바꿔줌!!!
			//나머지도 바꿔주기 시작!!!여긴처음이니 1.번!!!
			//전체 바꿔주고 실행해서 5번 실행하니!!
			//Exception in thread "main" java.util.InputMismatchException
			//에러뜸...다름...
			//다시 Run에서 실행하니 잘됨....????
			
			//다시 파란색으로 바꿔주는 작업......이번엔 콘스로 선택!!수정완료!!
			conn = jdbcTemplate.getConnection();
			//(conn,member) 2개를 넘겨줘여한다?
			//2.insertMember 에러:The method insertMember(Member) in the type MemberDAO is not applicable for the arguments (Connection, Member)
			//MemberDAO 에 insertMember 가 conn을 못받고 있기때문에 MemeberDAO로 이동해서
			//(Connection conn, ~~추가해주러감!!)
			//그럼 아래 코드 에러 사라짐!!!
			result = mDao.insertMember(conn,member); //mDao cannot be resolved (mDAO없어서만듬!)
			//JDBCTemplate에서 ClassNotFoundException
			//지우고 여기로 와서 밑에 코드 ClassNotFoundException
			//지워줌!!난 주석처리!!!다음건 삭제로 함!!!수정완료!!
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//자원해제!!
		}
		return result;
	}

	//4.회원정보조회!!
	public List<Member> selectList() {
		// TODO Auto-generated method stub
		Connection conn = null;
		List<Member> mList = null;
		
		try {
			//**2.클래스로 바꿔주기!!이번엔 클릭해서바꿔줌!!
			//chanqe access to satatic using'~~나온걸로 클릭해서 바꿔줌!
			
			//***1.1다시 JDBCTemplate에서 수정후 아래코드 에러
			//Cannot make a static reference to the non-static method getConnection() from the type JDBCTemplate
			//뜸!!!!다시 파란색으로 고치는거 수정하기!!1.2!!
			//1.2 빨간색을 다시 파란색으로 바꾸는데 클릭으로는안되어
			//콘스누르면 이상하게 빨간색 jdbcTemplate:JDBCTemplate-MemberService
			//나온걸 선택하면 파란색으로 바뀜!!!나머진 어떨까?? 1.2 수정완료!!!나머지바꾸기!!
			conn = jdbcTemplate.getConnection();//연결!!
			mList = mDao.selectList(conn); //전달!!
		//ClassNotFoundException삭제 완료!!!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mList;
	}

	//5.회원검색!!
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Member member = null;
		
		try {
			//**3.클래스로변경완료!!
			//다시 파란색으로 변경!!수정완료!!
			conn = jdbcTemplate.getConnection(); //1.연결!!
			member = mDao.selectOneById(conn,memberId);
		//ClassNotFoundException삭제완료!!!!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return member;
	}

	//3.회원탈퇴!!!
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		int result =0;
		Connection conn = null;
		
		try {
			//*4.클래스로 변경완료!!
			//다시 파란색으로 수정완료!!ㄴ
			conn = jdbcTemplate.getConnection(); //1연결
			//위에서 한것처럼 에러뜨면: The method deleteMember(String) in the type MemberDAO is not applicable for the arguments (Connection, String)
			//memberDAO에 가서 Connection conn 추가!!!
			result = mDao.deleteMember(conn,memberId);//2.전달
		//ClassNotFoundException삭제완료!!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	//2.회원정보수정!!
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		int result =0;
		Connection conn = null;
		
		try {
			//**5.클래스로 변경완료!!
			//다시 파란색으로 변경완료!!
			conn = jdbcTemplate.getConnection();//1.연결
			result = mDao.updateMember(conn,member); //2.전달
		//ClassNotFoundExceptiont삭제완료!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
