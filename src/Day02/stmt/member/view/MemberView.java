package Day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import Day02.stmt.member.controller.MemberController;
import Day02.stmt.member.model.vo.Member;

public class MemberView {
	
	private static final String BYE_MSG = "프로그램이 종료되었습니다";
	private static final String SUCCES_MSG = "[서비스성공]";
	private static final String FAIL_MSG = "[서비스실패]";
	private static final String NO_DATA_FOUND = "데이터가 존재하지않습니다";
	private Scanner sc;//스캐너 한번만쓰기!
	private MemberController manage; //임포트
	
	public MemberView() {
		sc = new Scanner(System.in);
		manage = new MemberController();
	}
	
	//첫스타트
	public void startProgram() {
		// TODO Auto-generated method stub
		finish:
		while(true) {
			int menu = this.showMainMenu();
			switch(menu) {
			case 1: 
				//회원가입
				Member member = this.inputMember();
				int result = manage.insertMember(member);
				if(result >0) {
					//성공
					this.showMessage(SUCCES_MSG);
				}else {
					//실패
					this.showMessage(FAIL_MSG);
				}
				break;
			case 2: 
				//2회원정보 수정
				//아이디,비번,이름,성별,나이,이메일,폰,주소,취미
				//->비번,이메일,폰,주소,취미
				//UPDATE MEMBER_TBL SET MEMBER_PWD = 'pass01',EMAIL ='khuser01@va.com'
				//,PHONE = '01012345678',ADDRESS ='경기도 남양주시',HOBBY = '코딩,수영'
				//WHERE MEMBER_ID ='user01';
				//데이터가 있을경우수정, 없으면 no_data_found 출력
				String memberId = this.inputMemberId();
				member = manage.selectOneById(memberId);
				if(member != null) {
					//정보수정
					member = this.modifyMember(memberId);
					result = manage.updateMember(member);
					if(result >0) {
						this.showMessage(SUCCES_MSG);
					}else {
						this.showMessage(FAIL_MSG);
					}
				}else {
					this.showMessage(NO_DATA_FOUND);
				}
				break;
			case 3: 
				//3.회원탈퇴
				//DELETE FROM MEMBER_TBL WHERE MEMBER_ID ='user11';
				memberId = this.inputMemberId(); //String지워줌!
				result = manage.deleteMember(memberId);
				if(result >0) {
					this.showMessage(SUCCES_MSG);
				}else {
					this.showMessage(FAIL_MSG);
				}
				break;
			case 4: 
				//4회원정보조회
				List<Member> mList =manage.selectList();
				this.ViewAllMembets(mList);
				break;
			case 5: 
				//5회원 검색
				//SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='memberId';
				// SELECT 를 실행한 결과는 딱 1개만 나옴 List<Member> 가아닌
				//Member로 받아야함.
				memberId = this.inputMemberId();
				member = manage.selectOneById(memberId);
				if(member != null) {
					this.ViewOneMember(member);
				}else {
					this.showMessage(NO_DATA_FOUND);
				}
				break;
			case 0: 
				this.showMessage(BYE_MSG);
				break finish;
			default : break;
			}
		}
		
	}
	//2회원정보수정
	private Member modifyMember(String memberId) {
		// TODO Auto-generated method stub
		System.out.println("=====수정할 정보 입력=====");
		//>비번,이메일,폰,주소,취미 +id필요!
		System.out.print("비번 :");
		String memberPwd = sc.next();
		System.out.print("이메일 :");
		String email = sc.next();
		System.out.print("전화번호 :");
		String phone = sc.next();
		System.out.print("주소 :");
		String address = sc.next();
		System.out.print("취미 :");
		String hobby = sc.next();
		//입력한 데이터를 객체 초기화함. 객체 생성됨
		Member member= new Member(memberId, memberPwd, email, phone, address, hobby);
		//호출한 곳에서 쓸수 있도록 member리턴함
		return member; //생성자 만들기!!
	}

	//5.회원검색
	private void ViewOneMember(Member member) {
		// TODO Auto-generated method stub //복붙해줌!!
		System.out.println("===== 회원검색 결과=====");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getGender()+"\t\t"+member.getAge()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
	}

	//5회원 검색
	private String inputMemberId() {
		// TODO Auto-generated method stub
		System.out.print("아이디 입력 :");
		String memberId = sc.next();
		return memberId;
	}

	//4.회원정보조회 
	private void ViewAllMembets(List<Member> mList) {
		// TODO Auto-generated method stub
		System.out.println("===== 회원정보 출력=====");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		for(Member member : mList) {
			System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getGender()+"\t\t"+member.getAge()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());

		}
	}

	//회원가입성공메세지!!!+bye 메세지
	private void showMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
		
	}

	//1회원가입
	private Member inputMember() {
		// TODO Auto-generated method stub
		System.out.println("=====회원 정보 입력=====");
		System.out.print("아이디 :");
		String memberId = sc.next();
		System.out.print("비밀번호 :");
		String memberPwd =sc.next();
		System.out.print("이름 :");
		String memberName = sc.next();
		System.out.print("나이 :");
		int age = sc.nextInt();
		Member member = new Member(memberId,memberPwd,memberName,age);
		return member;
	}
	//첫스타트 화면!
	private int showMainMenu() {
		// TODO Auto-generated method stub
		System.out.println("===== 회원관리 프로그램=====");
		System.out.println("1.회원가입");
		System.out.println("2.회원정보수정");
		System.out.println("3.회원탈퇴");
		System.out.println("4.회원 정보조회(ALL)");
		System.out.println("5.회원 검색(아이디)");
		System.out.println("0.프로그램 종료");
		System.out.print("메뉴 선택 :");
		int choice = sc.nextInt();
		return choice;
	}

}
