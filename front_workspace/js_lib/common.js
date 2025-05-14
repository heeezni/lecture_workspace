//랜덤한 값 구하기
function getRandom(max){
    return parseInt(Math.random()*(max+1));
}
// 원하는 정수를 반환받기 위해서는 max의 값을 호출 시 결정하자
// ex) getRandom(10)을 넘기면 0~10 반환
// ex) getRandom(9)을 넘기면 0~9 반환
/*-----------------------------------------------------------------*/
    
//한 자리 수 숫자 n 앞에 0을 붙여주기
function zeroString(n){
    let str=n;
    if(n>=0 && n<10)str="0"+n;
    return str;
} 
/*-----------------------------------------------------------------*/

// 해당 월의 '시작 요일' 구하기
// API 사용 예) 2025년 5월 → getStartDay(2025,4)
function getStartDay(yy,mm){
    let d = new Date(yy,mm,1); // 1일
    return d.getDay(); // 요일을 반환  
}
/*-----------------------------------------------------------------*/
// 영어 또는 한국어로 요일을 변환하여 반환하기
// API 사용 예) convertDay(2,"eng")
function convertDay(n,lang){
    let korArray=["일요일","월요일","화요일","수요일","목요일","금요일","토요일"];
    let engArray=["Sun","Mon","Tue","Wed","Thur","Fri","Sat"];

    //어떤 요일을 선책할 지 결정
    let day=(lang=="kor")? korArray[n]:engArray[n];
    return day;
} 
/*-----------------------------------------------------------------*/
// 해당 월의 "마지막 날" 구하기 (= 다음 달의 시작 일 하루 전)
// API 사용 예) getLastDate(원하는 연도, 원하는 월-1)
function getLastDate(yy,mm){
    let d = new Date(yy,mm+1,0); 
    return d.getDate();
}
/*-----------------------------------------------------------------*/
// 충돌체크함수
// API 사용 예)
function collisionCheck(me,target){
    //나에 대한 수치 계산
    const me_x= parseInt(me.style.left);
    const me_y= parseInt(me.style.top);
    const me_width=parseInt(me.style.width);
    const me_height=parseInt(me.style.height);

    //조사대상 상대방
    const target_x= parseInt(target.style.left);
    const target_y= parseInt(target.style.top);
    const target_width=parseInt(target.style.width);
    const target_height=parseInt(target.style.height);

    return!(
    me_x+me_width < target_x || //me의 오른쪽이 타겟의 왼쪽보다 왼쪽에 있으면
    me_x > target_x+target_width || //me의 왼쪽이 타겟의 오른쪽보다 더 오른쪽이면 
    me_y+me_height < target_y || // me의 아래쪽이 타겟의 위보다 위에 있으면
    me_y > target_y+target_height // me의 아래쪽이 타겟의 아래보다 아래에 있으면
    )
}
/*-----------------------------------------------------------------*/
