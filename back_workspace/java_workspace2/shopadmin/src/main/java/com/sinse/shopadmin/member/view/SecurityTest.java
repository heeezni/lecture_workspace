package com.sinse.shopadmin.member.view;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityTest {

	public static void main(String[] args) {
		//javaSE는 이미 암호화 알고리즘 함수를 보유하고 있다.
		String pass="minzino";
		
		try {
			//1. SHA-256 알고리즘 객체 생성
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			
			//2. 입력문자열을 바이트배열로 쪼개기
			byte[] hash=md.digest(pass.getBytes("UTF-8")); //알맞은 인코딩으로 쪼개짐
			//System.out.println(hash.length); 32출력. 32개의 바이트로 쪼개짐
			
			//3. 잘게 쪼개진 바이트를 16진수 문자열로 변환
			StringBuffer sb=new StringBuffer(); //String의 불변적 특징을 해결한 객체
			
			for(int i=0; i<hash.length; i++) {
				/*byte 데이터를 16진수로 변경하는 과정에서, byte값 안에 음수가 존재할 경우
				 * byte 데이터형이 int형으로 변경되면서 부호 비트가 자동으로 붙게되는데
				 * 이 부호 비트는 암호화에 사용되지 않으므로, 제거해야함
				 * 이때 제거하는 연산은 16진수 0xff와 &비트 연산자 중 and 연산자를 이용한다
				 * 참고) 바이트 데이터가 int형으로 변경되는 이유는 java언어에서 왼쪽은 비트형 오른쪽은 바이트형*/
				
				String hex=Integer.toHexString(0xff & hash[i]); 
				//System.out.println(hex); //음수값 때문에 쓸데없이 길게나오니까 0xff와 비트연산자 &로 음수
				if(hex.length()<2) sb.append("0"); //하나짜리는 앞에 0을 추가하겠다 (64자 맞춰주기위해)
				sb.append(hex); //String 누적
			}
			System.out.println(sb.toString()); //sb는 스트링 자료형 아니니까 toString메서드 사용
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

}
