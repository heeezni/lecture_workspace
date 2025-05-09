//랜덤한 값 구하기
function getRandom(max){
    return parseInt(Math.random()*(max));
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
