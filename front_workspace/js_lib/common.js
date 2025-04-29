//랜덤한 값 구하기
function getRandom(max){
    return parseInt(Math.random()*(max+1));
    // 원하는 정수를 반환받기 위해서는 max의 값을 호출 시 결정하자
    // ex) getRandom(10)을 넘기면 0~10 반환
    // ex) getRandom(9)을 넘기면 0~9 반환
}