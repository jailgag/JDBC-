package Day01.stmt.member.controller;

import java.util.List;

import Day01.stmt.member.model.dao.MemberDAO;
import Day01.stmt.member.model.vo.Member;

public class MemberController {
	//1.마찬가지로 선언해주고
	private MemberDAO mDao;
	
	//2.초기화를 통해 객체 생성!! 암기!!
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	
	
	//view에서 넘어옴!2.회원전체조회!!
	public List<Member> showMemberList() {
		// TODO Auto-generated method stub
		//내가 원하는건 List<Member> 값이고
		//MemberDAO가 가져다 준다!
		
		List<Member> mList = mDao.selectList();
		//MemberDAO가 잘 수행되었다면 mList 에
		//데이터가 있을것이고 콘트롤러에서
		//가져다 쓸수 있도록 리턴을 해줘야함!!
		return mList;
	}


	//1.회원가입!!view에서 넘어옴!
	public int registerMember(Member member) {
		// TODO Auto-generated method stub
		int result = mDao.insertMember(member);
		return result;
	}


	//3.회원검색(아이디)view에서 넘어옴!!
	public Member findOneById(String memberId) {
		// TODO Auto-generated method stub
		Member member = mDao.selectOneById(memberId);
		return member;
	}

}
