class DataType{
	
	public static void main(String[] args){
		//자료형 분류하는 이유는 메모리에 생성되는 용량의 크기를 결정짓기 위함
		/*
		정수: byte(1) < short(2) < int(4) < long(8)
		실수:							 float(4) < double(8)
		문자:				  char(2)
		*/
		//Java는 전세계 주요국가의 문자를 수용할 수 있으므로,
		//영미권 위주의 아스키코드보다 확장된 유니코드 기반
		
		//'int보다 작은' 자료형을 연산에 대입하면 자동으로 int로 형변환 
		//Java 컴파일러는 32-bit(int형)에 최적화
		//long은 제외 (int 보다 큼)
		//64bit 기반의 컴퓨터여도 그건 OS관련 문제. Java는 32bit 최적화
		
		short s=7;
		byte b=9;
		long k=10;
		
		int x=s+b;
		long y=k+s;
		
		// short a=3;
		// short b=9;
		// short c=short(a+b); 강제 형변환
		// int c=a+b
		
		
	}

}