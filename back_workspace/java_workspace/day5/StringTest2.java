class StringTest2{
	public static void main(String[] args){
		// String 불변의 특징
		// 즉 변경될 수 없다 (immutable : 수정불가하다)
		
		String x="korea";
		x="korea fighting"; //이 클래스에서 만든 String 객체는 2개 (대체 된 게 아님)
		
		System.out.println(x);

	}
}
