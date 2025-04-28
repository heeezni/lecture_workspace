//랜덤한 값 구하기
function getRandom(n){
    return parseInt(Math.random()*(n+1));
    // 원하는 정수를 반환받기 위해서는 n의 값을 호출 시 결정하자
    // ex) getRandom(10)을 넘기면 0~10 반환
    // ex) getRandom(9)을 넘기면 0~9 반환
}