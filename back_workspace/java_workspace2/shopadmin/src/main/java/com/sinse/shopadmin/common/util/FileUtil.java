package com.sinse.shopadmin.common.util;

import java.io.File;

//애플리케이션 전반에 걸쳐 유용하게 사용할 공통코드 中 파일 관련된 기능

public class FileUtil {
	
	/*-------------------------------------------------------
	 * 중복되지 않는 이름으로 새로운 파일 생성하기
	 * targetDir: 파일이 생성될 디렉토리
	 * ------------------------------------------------------*/ 
	public static File createFile(String targetDir, String ext) { //디렉토리 + 확장자 넘겨받기
		
		long time=System.currentTimeMillis(); //현재 시간 반환
		String filename=targetDir+File.separator+time+"."+ext;
		return  new File(filename);
	}
	
	/*-------------------------------------------------------
	 * 파일 확장자 구하기
	 * ------------------------------------------------------*/ 
	public static String getExt(String path) {		
		return path.substring(path.lastIndexOf(".")+1, path.length());
	}
	

}
