// 현실의 총알을 정의한다
// java, c#, Python... 모든 클래스 안에는 본질적으로 단 2가지 작성
// 속성(상태)과 메서드(동작)

class Bullet{ // 파일명과 클래스명 동일하게 해주기

    //아래의 생성자 메서드는 new 연산자 뒤에서 호출됨
    // new Bullet()이런식으로
    constructor(bg,y){ // 총알이 보유할 변수(속성 property) 선언
        this.div=document.createElement("div");
        this.x=0; // 총알의 x축 위치
        this.y=y;
        this.velX=10; // 총알의 속도
        this.bg=bg; // 매개변수로 넘어온 데이터를 클래스 변수에 보관해놓기 (필수는 x)

        //총알이 어떤 모습으로 보여질 지 개성 결정 (css-style)
        this.div.style.width= 20+"px";
        this.div.style.height= 20+"px";
        this.div.style.borderRadius= 50+"%";
        this.div.style.background=this.bg; // 색 각기 다르게 하고 싶으면 매개변수 사용
        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px"; // 위치 다르게 하고 싶으면 매개변수 사용

        document.body.appendChild(this.div);
        // console.log("저 방금 태어났어요");
    }

    move(){ // 총알의 상태를 변경시키기 위한 (움직임을 표현한) 메서드 정의
        this.x+=this.velX; //등속도 운동
        this.div.style.left=this.x+"px";
    }
}