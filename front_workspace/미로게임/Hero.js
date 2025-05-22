class Hero extends GameObject{
    constructor(container,x,y,width,height,velX,velY,bg,border,borderColor){
        super(container,x,y,width,height,velX,velY,bg,border,borderColor);

        //4개의 센서를 보유하자
        this.upSensor= new UpSensor(this.div, this, 3, 0, this.width-6, 3, "blue", 0, "");
        this.rightSensor= new RightSensor(this.div, this, this.width-3, 3, 3, this.height-6, "blue", 0, "");
        this.downSensor= new DownSensor(this.div, this, 3, this.height-3, this.width-6, 3, "blue", 0, "");
        this.leftSensor= new LeftSensor(this.div, this, 0, 3, 3, this.height-6, "blue", 0, "");
    }
    showHideSensor(flag){
        this.upSensor.setVisible(flag);
        this.rightSensor.setVisible(flag);
        this.downSensor.setVisible(flag);
        this.leftSensor.setVisible(flag);
    }

    //부모의 메서드 오버라이딩
    tick(){
        this.x+=this.velX;
        this.y+=this.velY;
    }
    render(){
        //현재 화면에 등장한 벽돌과 나와의 교차여부 체크(collision check)
        /*for(let i=0; i<brickArray.length; i++){
            for(let j=0; j<brickArray[i].length; j++){
                if(brickArray[i][j] !==0){ //벽돌 객체인지 확인 (0이면 스킵)
                    if(collisionCheck(this.div, brickArray[i][j].div)){
                         this.div.style.background="blue";
                    }
                }
            }
        }*/
        //센서는 현재 자신의 값에 주인공의 위치값을 더한 값으로 충돌 계산이 이루어져야 함
        this.upSensor.setFakeValue(3,0);
        this.rightSensor.setFakeValue(this.width-3,3);
        this.downSensor.setFakeValue(3,this.height-3);
        this.leftSensor.setFakeValue(0,3);

        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";
    }
}
/*
센서 만들기
윗 센서: 벽돌의 y값 + 벽들의 높이
오른쪽 센서: 벽돌의 왼쪽+주인공 너비
왼쪽 센서 : 벽돌의 오른쪽+벽돌의 너비
아래쪽 센서 : 벽돌의 y값 + 주인공의 높이
*/