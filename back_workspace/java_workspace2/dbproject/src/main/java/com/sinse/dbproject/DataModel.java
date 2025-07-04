package com.sinse.dbproject;

import javax.swing.table.AbstractTableModel;

/*JTable은 실제 데이터를 가진 주체가 아니고, 단지 사용자들이 보게되는 UI에 지나지 않으므로 (껍데기)
 * JTable에 보여질 데이터는 모델에 의존하게 됨
 * 이와 같이 디자인 영역 (View)과 데이터 및 그 처리 로직 (Model)을 분리시켜 개발하는 방법을 
 * MVC 개발 방법론 / 패턴이라고 한다*/

public class DataModel extends AbstractTableModel {
	String[][] data; //데이터를 표현하는 이차원 배열
	String[] title; //컬럼을 표현하는 일차원 배열 선언하기

	//층수를 반환하여 JTable이 참조할 수 있도록 제공되는 메서드 
	@Override
	public int getRowCount() {
		return data.length;
	}

	//호수를 반환하여 JTable이 참조할 수 있도록 제공되는 메서드
	@Override
	public int getColumnCount() {
		return title.length;
	}
	
	public String getColumnName(int column) {
		return title[column];
	}

	//JTable에 의해 row * col 만큼 아래의 메서드 호출됨
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
