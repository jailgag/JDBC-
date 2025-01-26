package Day01.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import Day01.stmt.member.controller.MemberController;
import Day01.stmt.member.model.vo.Member;

public class MemberView {
	
	//선언만 한상태!!
	private MemberController manage; //1.선언!
	
	//초기화를 통해 객체를 만들어줌!!
	public MemberView() {  //2.이거 정말 많이 누락하니!첫시작할때 꼭적어주기!!안적어주면 널포인트 익셉션발생!!
		manage = new MemberController();
	}
	
	
	private static final String BUY_MSG = "프로그램이 종료되었습니다"; //상수화만들기!!
	private static final String SUCCESS_MSG = "[서비스성공]";
	private static final String FAIL_MSG = "[서비스 실패]";
	private static final String NO_DATA_FOUND = "일치하는 데이터가 존재하지 않습니다";
	public void startProgram() {
		// TODO Auto-generated method stub
		finish:
		while(true) {
			int menu = this.printMenu();
			switch(menu) {
			case 1: 
				//회원가입//등록이 안됨!!입력은 되나
				//취미까지 입력하면 넘어가질 않는다.
				//깃허브에 일단 올릴려고 했으나 
				//올려지지 않는다..무엇이 문제일까...
				Member member = this.inputMember();
				int result = manage.registerMember(member);
				if(result > 0) { //성공여부체크!
					//성공
					this.printMessage(SUCCESS_MSG);
				}else {
					//실패
					this.printMessage(FAIL_MSG);
				}
				break;
			case 2: 
				//2. 회원전체 조회!!
				//이것도 에러떠서 전체 조회가 안됨..
				// Cannot invoke "String.charAt(int)" because the return value of "java.sql.ResultSet.getString(String)" is null
				//에러뜸!!
				//int 
				List<Member> mList = manage.showMemberList();
				this.printAllMembers(mList); 
				break;
			case 3: 
				//회원검색(아이디)
				String memberId = this.inputMemberId();
				member = manage.findOneById(memberId);
				if(member != null) {
					this.printOne(member);
				}else {
					this.printMessage(NO_DATA_FOUND);
				}
				break;
			case 0: 
				this.printMessage(BUY_MSG);
				break finish;
			}
		}
		
	}
	//3.회원검색(아이디)
	private void printOne(Member member) {
		// TODO Auto-generated method stub
		//아래 회원전제조회에서 복붙해서 검색한회원정보만 바꿈!!!
		System.out.println("=====검색한 회원정보=====");
		System.out.println("아이디\t\t이름\t\t이메일\t\t전화번호\t\t주소");
		System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
	}
	//3.회원검색(아이디)
	private String inputMemberId() {
		// TODO Auto-generated method stu
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}
	//1회원가입!!
	private Member inputMember() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("=====회원 정보입력=====");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
//		String address = sc.next();
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		//끝에 null값이 보기 싫으다 하면 다시 적고 생성자 생성해주면됨!!
		//Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, null);
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby);
		return member;
	}
	// 2.회원전체 조회!!
	private void printAllMembers(List<Member> mList) {
		// TODO Auto-generated method stub
		System.out.println("=====회원 전제 정보=====");
		System.out.println("아이디\t\t이름\t\t이메일\t\t전화번호\t\t주소");
		for(Member member : mList) {
			//아래코드 연습코드!!
			System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
			
		}
		
	}
	//0프로그램종료!
	private void printMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
		
	}
	//첫보여주는 화면!!
	private int printMenu() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("====회원관리 프로그램=====");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원전체 조회");
		System.out.println("3. 회원검색(아이디)");
		System.out.println("0.프로그램 종료");
		System.out.print("메뉴선택 >>> ");
		int choice = sc.nextInt();
		return choice;
	}

}
