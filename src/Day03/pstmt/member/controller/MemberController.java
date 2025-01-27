package Day03.pstmt.member.controller;

import java.util.List;

import Day03.pstmt.member.moder.dao.MemberDAO;
import Day03.pstmt.member.moder.service.MemberService;
import Day03.pstmt.member.moder.vo.Member;

public class MemberController {
	
	//01/27 15:51수정!! controllet 수정끝!!!
	//private MemberDAO mDao; //dao대신 Service 추가
	
	private MemberService mService; //1.**추가!!
	
	public MemberController() {
	//	mDao = new MemberDAO(); //DAO대신 Service추가!!!
		//2.**추가!! mService로 바꿔줬기 때문에
		//*****3.아래 코드 dao는  주석처리하고 새로 추가해줌!!
		mService = new MemberService();
	}
	
	//view에서 넘어옴!1. 회원가입
	public int insertMember(Member member) { //()안에 입력..
		// TODO Auto-generated method stub
		//int result = mDao.insertMember(member); *3 수정!!
		int result = mService.insertMember(member);
		return result;
	}

	//4.회원정보 조회
	public List<Member> selectList() {
		// TODO Auto-generated method stub
//		List<Member> mList = mDao.selectList(); ***3!!!!
		List<Member> mList = mService.selectList(); ///**메소드 만들어주기!!
		return mList;
	}
	//5.회원검색
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
		//SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='memberId';
		//Member member = mDao.selectOneById(memberId);**3!!!
		Member member = mService.selectOneById(memberId);
		return member;
	}
	//3/회원탈퇴
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		//int result = mDao.deleteMember(memberId);
		int result = mService.deleteMember(memberId); //3.****
		return result;
	}
	//2.회원정보 수정
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		//int result = mDao.updateMember(member);//3.**
		int result = mService.updateMember(member);//3.**
		return result;
	}

}
