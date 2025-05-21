class Brick extends GameObject{
    constructor(container,x,y,width,height,velX,velY,bg,border,borderColor){
        super(container,x,y,width,height,velX,velY,bg,border,borderColor); 
        //Js는 무조건 super() 직접호출!
    }
}