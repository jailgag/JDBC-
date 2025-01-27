package Day03.pstmt.member.run;

import Day03.pstmt.member.common.Singleton;
import Day03.pstmt.member.view.MemberView;

public class Run {
	public static void main(String[] args) {
		MemberView view = new MemberView();
		view.startProgram();
		//1.Singleton 객체만들기!
		//The constructor Singleton() is not visible 
		//위 에러코드는 아무나 접근못하게 private 으로 했기때문에!!
		//다른코드로 씀!! 밑에코드는 주석처리함!!새로 작성!!
		//Singleton singleton = new Singleton();
		//getInstance로 객체로 만들면!
		Singleton singleton = Singleton.getInstance(); 
	}
}
