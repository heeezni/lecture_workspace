// 메서드의 개념: 클래스에 소속된 함수를 가리켜 메서드라고 한다
class MethodTest{
	
	//접근제한자
	//public: 외부의 있는 모든 코드가 이 메서드를 접근할 수 있음
	
	//protected: 같은 패키지(디렉토리) 및 상속관계에 있는 객체들이라면 접근가능
	
	//default: default는 명시하는 게 아니라, 아무것도 작성하지 않아아 함
	int x; //default 접근 제한자 (자식이라도 같은 패키지가 아니면 접근 금지)

	//private: 같은 멤버끼리만 접근 가능
	
	
	//반환형이 없는 메서드
	public void getName();

}

