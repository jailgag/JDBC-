package Day02.stmt.member.controller;

import java.util.List;

import Day02.stmt.member.model.dao.MemberDAO;
import Day02.stmt.member.model.vo.Member;

public class MemberController {
		
	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	//view에서 넘어옴!1. 회원가입
	public int insertMember(Member member) { //()안에 입력..
		// TODO Auto-generated method stub
		int result = mDao.insertMember(member);
		return result;
	}

	//4.회원정보 조회
	public List<Member> selectList() {
		// TODO Auto-generated method stub
		List<Member> mList = mDao.selectList();
		return mList;
	}
	//5.회원검색
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
		//SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='memberId';
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	//3/회원탈퇴
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		int result = mDao.deleteMember(memberId);
		return result;
	}
	//2.회원정보 수정
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		int result = mDao.updateMember(member);
		return result;
	}

}
