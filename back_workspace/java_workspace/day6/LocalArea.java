class LocalArea{
	public LocalArea(){ // 생성자: 자바의 생성자는 클래스명과 완전 동일해야함
		int k=9;
	}
	
	{
		// 인스턴스 초기화 블럭
		// 해당 클래스로부터 인스턴스를 생성할 때마다 실행되는 영역을 말함
		// new LocalArea()때 이 영역을 건드리게 됨
		// 개발 시 거의 보기 힘들다. 생성자가 이미 객체의 변수들을 초기화 하는 역할을 함
		// 거의 다 생성자로 초기화함
		System.out.println("인스턴스 생성했지?");
		k=7;
	}
	
	public static void main(String[] args){
		new LocalArea();
		//int x=8; //already difined
		{
			int x=5;	
		}
		//x=7; //cannot find symbol (int x=5; 이미 죽음)
	}
}