class StringTest{
	
	public static void main(String[] args){
		//모든 프로그래밍 언어에서 개발자가 사용할 수 있는 기본 자료형은
		//문자, 숫자, 논리값이지만, 이 중 문자는 문자1개를 말하므로
		//우리의 일상에서 사용하는 문자열을 표현하려면, 개발자가 배열을 사용해야함
		//하지만 개발이 너무 불편하므로, 현대적 프로그래밍 언어에서는 내부적으로는 배열로 처리되지만
		//개발자들을 위해서 문자열을 객체자료형으로 지원해줌
		
		
		//new를 명시하지 않아도, 내부적으로 문자열 객체를 생성시키는 생성법을
		//암시적,묵시적 (implicit)생성법이라함
		String s1="korea"; //사실은 문자 '배열' char[]
		String s2="korea";
		System.out.println(s1==s2); //True ("korea"는 주소값이 상수풀로 들어감, 상수풀은 중복체크기능O)
		
		//new 연산자를 이용하여 객체의 생성법을 그대로 따르는 문자열 생성법을
		//명시적 (explicit)생성법이라함 (특별한 일 없으면 거의 쓸 일없음)
		String x1=new String("apple");
		String x2=new String("apple");
		System.out.println(x1==x2); //False (서로 다른 객체의 주소값 비교이므로)
	}

}
