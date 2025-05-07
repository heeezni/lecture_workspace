//이퀄라이저에 들어가는 현실의 사각형 막대 정의
class Rect{
    constructor(container,x,y,width,height,bg){
        this.container=container;
        this.div=document.createElement("div");
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.bg=bg;
        this.a=0.1;
        this.targetH=parseFloat(Math.random()*(300+1));

        // style
        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px"

        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";

        this.div.style.background=this.bg;
        
        this.container.appendChild(this.div); //부모에 부착
        this.move(); //태어나자마자 한 번은 호출해줘야 무한루프 가동
    }

    move(){
        height=parseInt(Math.random()*(300+1));
        //console.log("move()...")
        //여기서 막대의 크기를 감속도 공식을 적용하여 움직여보자
        //나의 높이=현재 나의 높이+ a*(목표지점의 높이-현재 나의 높이)
        this.div.style.height=parseFloat(this.div.style.height)+this.a*(this.targetH-parseFloat(this.div.style.height));

        setTimeout(()=>{ //화살표함수
            this.move();
        },10);
        //setTimeout("this.move()"); 안먹힘
    }
}