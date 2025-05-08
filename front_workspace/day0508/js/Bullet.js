// 총알을 정의한다
class Bullet{
    constructor(container,x,y,width,height,velX,velY,bg){
        this.container=container;
        this.div=document.createElement("div");
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.velX=velX;
        this.velY=velY; //총알이 대각선으로 날아갈 수도 있으니까
        this.bg=bg
        // style
        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";

        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";

        this.div.style.background=this.bg;
        this.div.style.borderRadius=50+"%"

        // 총알의 테두리에 블러효과 주기
        this.div.style.filter="blur(2px)"; //퍼짐 정도를 숫자로 표현

        this.container.appendChild(this.div);
    }

    shoot(){ //총알이 날아가는 기능 정의

        //만일 총알이 적군 등에 맞지 않고 화면 밖으로 나가는 경우,
        //메모리 관리를 위해 제거 (화면제거+배열제거)
        if(this.x>=1500){ //경계를 넘어서는 순간부터는...
            this.container.removeChild(this.div); //내가 총알 , 화면에서 제거
            
            //전역변수 접근 가능
            //현재 총알인 내가, 배열 몇번 째에 위치해 있는지 배열한테 물어봐서
            // 그 위치를 알아내자
            let index=bulletArray.indexOf(this)
            //this는 현재 bullet객체를 가리킨다
            bulletArray.splice(index,1);

            console.log("현재 존재하는 총알 수는", bulletArray.length);
        }

        this.x+=this.velX
        this.y+=this.velY

        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";
    }

}