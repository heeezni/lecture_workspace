interface Foo {} 
class Alpha implements Foo {} 
class Beta extends Alpha {} 
class Delta extends Beta {     
	public static void main( String[] args ) { 
		Beta x = new Beta(); 
16.		//insert code here 16     
	} 
}
Which code, inserted at line 16, will cause a java.lang.ClassCastException?  

A. Alpha a = x; 
B. Foo f = (Delta)x; //컴파일은 가능, 실행 불가능 Delta객체가 없음
C. Foo f = (Alpha)x; 
D. Beta b = (Beta)(Alpha)x;
--------------------------------------------
class Foo {     
	public int a = 3;     
	public void addFive() { a += 5; System.out.print("f "); } 
} 

class Bar extends Foo { 
	public int a = 8;    
	
	public void addFive() { 
		this.a += 5; System.out.print("b " ); 
	} 
}

Invoked with:  
Foo f = new Bar(); f.addFive(); System.out.println(f.a);  

What is the result?  

A. b 3 

자료형은 서랍장 개념
메서드는 this 바인딩으로 찾기!

--------------------------------------------------

class ClassA {}
class ClassB extends ClassA {} 
class ClassC extends ClassA {} 
and: 
ClassA p0 = new ClassA(); 
ClassB p1 = new ClassB(); 
ClassC p2 = new ClassC(); 
ClassA p3 = new ClassB(); 
ClassA p4 = new ClassC();  

Which three are valid? (Choose three.)  

자료형임
A. p0(A) = p1(B); //가능 
B. p1(B) = p2(C); 
C. p2(C) = p4(A); 
D. p2(C) = (ClassC)p1(A); 
E. p1(B) = (ClassB)p3(A); //다운캐스팅 가능 
F. p2(C)= (ClassC)p4(A); //다운캐스팅 가능

자식이 부모의 메서드를 오버라이딩 할 때는 
부모의 접근 제한자보다 강력하게 할 수 없다! 그 이하로 (public, protected, default, private 순)
---------------------------------------------------------------

super( ) 와 this( ) 양자택일

1. class X { 
2.     X() { System.out.print(1); } 
3.     X(int x) { 
4.         this(); System.out.print(2); 
5.     } 
6. } 
7. public class Y extends X { 
8.     Y() { super(6); System.out.print(3); } 
9.     Y(int y) {
10.         this(); System.out.println(4); 
11.     } 
12.     public static void main(String[] a) { new Y(5); } 
13. } 
//1234

생성자 호출 흐름
new Y(5) → Y(int y)
   ↓
this();            // → Y() 호출
   ↓
Y() {
    super(6);      // → X(int) 호출
        ↓
        this();    // → X() 호출
            → print(1)
        → print(2)
    → print(3)
}
→ print(4)

자바 생성자에서는 this() 또는 super() 중 하나만 호출할 수 있고,
둘 다 생략하면 super()가 자동으로 붙습니다.

자동 생성되는 super();는 매개변수가 없는 기본 생성자
----------------------------------------------------------------------------
