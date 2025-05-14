class Bullet extends GameObject{
    constructor(container,src,x,y,width,height,velX,velY){
        super(container,src,x,y,width,height,velX,velY);
    }
    //제거 메서드 (화면상제거+총알배열제거)
    //배열 제거 안하면 계속 총알이 있는 줄 안다
    removeObject(array,target){ //총알 배열도 제거하고, 적군 배열도 제거할 수 있게 매개변수로 받을 준비
        //1)화면에서 제거
        this.container.removeChild(array[array.indexOf(target)].img); //bullet(메모리 덩어리)의 img(우리가 시각적으로 보고있는것) 제거해야함
        //this는 나의 인스턴스를 가리킴

        //2)총알이 들어있던 배열에서도 제거
        //splice
        array.splice(array.indexOf(target),1);
    }
    tick(){
        this.x+=this.velX;
    }
    render(){
        //총알이 한 걸음씩 나아갈 때마다, 총알과 적군과의 충돌을 체크해서 제거처리
        for(let i=0; i<enemyArray.length; i++){
            let enemy= enemyArray[i];//적군 한 마리 꺼내기

            if(collisionCheck(this.img, enemy.img)){
                //충돌했다면
                this.removeObject(enemyArray, enemy); //나 죽고
                this.removeObject(bulletArray, this); //너 죽고
                setScore(10); //10점 증가
            }
        }

        //적군에 충돌하지 않은 총알은 자동 제거 (1400px넘어설 때 제거처리)
        if(this.x>1400){
            //화면에서 제거
            //배열에서 제거
            this.removeObject(bulletArray, this);
        }
        this.img.style.left=this.x+"px";
    }
}