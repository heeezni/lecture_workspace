//현실의 단어를 정의한다
class Word{
    constructor(container,x,y,text,color){
        this.container=container;
        this.span=document.createElement("span");
        //div는 block이라 글자만 래핑x
        //span은 inline이므로 너비가 컨텐츠만큼 확보 (wrapping)
        //단어 감싸고 inline-block주자
        this.x=x;
        this.y=y;
        this.text=text; //이 span이 포함하게 될 단어
        this.color=color; //글씨 색상

        //sytle & 조립
        this.span.style.display="inline-block";
        this.span.style.position="absolute";
        this.span.style.left=this.x+"px";
        this.span.style.top=this.y+"px";
        this.span.innerText=this.text;
        this.span.style.color=this.color;

        this.container.appendChild(this.span);
    }
    //물리량 변화
    tick(){
        this.y+=5;
    }
    //그래픽 처리 (한발 씩 내려오기)
    render(){
        this.span.style.top=this.y+"px";
    }
}