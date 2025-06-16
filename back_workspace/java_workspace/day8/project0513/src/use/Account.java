/* 객체지향 언어의 장점
1) 캡슐화
2) 상속
3) 추상화
*/
package use;
public class Account {
	
	private String num="110-409-513991";
	private String bank="신한은행";
	private String owner="HJ";
	private int balance=500000;
	
	//클래스 작성 시 데이터를 보호하고, 이 보호된 데이터를 외부에서 사용하게 해주려면
	//공개된 메서드를 제공해주어야하는데, 
	//이러한 클래스 정의기법을 가리켜 은닉화,캡슐화라고 함 (=encapsulation)
	//리모컨(은닉화)과 버튼 (메서드)
	
	
	
	//데이터에 대해 읽기,쓰기가 가능하도록 메서드 정의
	public int getBalance(){ //getter 메서드 (get+멤버변수 조합)
		return balance;
	}
	public String getBank(){
		return bank;
	}
	public String getOwner(){
		return owner;
	}
	public String getNum(){
		return num;
	}
	
	public void setBalance(int balance){ //setter 메서드 (set+멤버변수 조합)
		this.balance=balance;
	}
	public void setBank(String bank){
		this.bank=bank;
	}
	public void setOwner(String owner){
		this.owner=owner;
	}
	public void setNum(String num){
		this.num=num;
	}
	//메서드 접근은 우리가 제어할 수 있음(조건줘서 제어 가능)
	
}