//ES6부터는 클래스뿐 아니라 상속까지도 Java언어와 흡사하게 지원
class Duck extends Bird{
    /*js와 java는 둘다 상속관계에서 자식의 인스턴스가 초기화 되기 전에 
    부모의 인스턴스 초기화가 앞서야 함은 동일하지만
    js는 개발자가 자식의 클래스에서 생성자를 정의만 해도 무조건 부모의 생성자 호출을 의무하길 명시*/
    constructor(color,age){
        super(color,age); //부모님아 먼저 초기화되세요~
                //super()는 부모의 constructor()를 의미
        //this.color=color; 
        //this.age=age; 부모클래스에서 이미 처리했으면 쓸 필요 없음
    }
    fly(){
        console.log("오리가 날아요");
    }
}