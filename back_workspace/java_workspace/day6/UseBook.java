class  Book{
	String title="해리포터";
	int page=300;

	public int getPage(){
		return page;
	}
	public void setPage(int p){
		page=p;
	}
}


class UseBook{
	public static void main(String[] args){

		Book  b=new Book();
		b.setPage(150);
		int page=b.getPage();
		System.out.println("페이지 수는 "+page); //150
	}
}
