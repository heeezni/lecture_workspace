class GameObject{
    constructor(container,x,y,width,height,velX,velY,bg,border,borderColor){
        this.container=container;
        this.div=document.createElement("div"); 
                //게임완성 후, div를 버리지 말고, 그대로 유지하되, 이미지를 배경처리
                //이미지로 개발하지 못하는 이유 : 이미지는 부모 기능이 없음
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.velX=velX;
        this.velY=velY;
        this.bg=bg;
        this.border=border;
        this.borderColor=borderColor;

        //style 및 조립
        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";
        this.div.style.boxSizing="border-box"

        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";
        this.div.style.background=this.bg;
        this.div.style.border=`${this.border}px solid ${this.borderColor}`;

        this.container.appendChild(this.div);
    }

    //object의 변화게 될 물리량 계산 update(),tick()..
    tick(){}
    //변화된 물리량을 화면에 반영하는 그래픽 작업 render(), paint()
    render(){}

}