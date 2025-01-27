package Day03.pstmt.member.common;

public class Singleton {
	/*
	 * 싱글톤 - 클래스의 인스턴스가 하나만 생성되도록보장하는
	 * 디자인 패턴
	 * 데이터 연결 작업은 부하가 큰 작업이고
	 * 반복될수도록 데이터 베이스에 무리가 될수 있음
	 * -> 데이터 베이스 연결은 비용이 많이 드는 작업
	 * ,그리고 많은 메모리와 시스템 리소스가 필요함
	 * 그런데 이런 작업을 싱글톤을 관리하면 
	 * 한번만 연결 객체를 생성하여 재사용 할수 있음
	 * ->성능 향상, 새로운 연결을 생생할때 마다 발생하는
	 * 비용 감소, DB 부하감소
	 */
	
	//4.번까지 적고 Run으로 이동해서 코드작성!!
	
	//1.static 이면서 Singleton 타입인 멤버변수 필요
	private static Singleton instance;
	
	
	//4.생성자!! private 접근제한자인 생성자 생성!!
	private Singleton() {}
	
	
	//2. static 이면서 public이고 리턴타입이 Singleton 메소드 필요
	public static Singleton getInstance() {
		//3. if문으로 멤버변수 널 체크후 null이면 객체생성
		//null이 아니면 그대로 리턴!!
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
	
}
