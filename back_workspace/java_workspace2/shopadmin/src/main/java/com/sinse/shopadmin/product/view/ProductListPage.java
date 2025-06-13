package com.sinse.shopadmin.product.view;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.view.Page;

public class ProductListPage extends Page{
	JTable table;
	JScrollPane scroll;
	JButton bt_regist; 
	public ProductListPage(AppMain appmain) {
		super(appmain);
	}

}
