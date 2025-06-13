package com.sinse.shopadmin.product.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;

public class ProductListPage extends Page{
	JTable table;
	JScrollPane scroll;
	
	JPanel p_content;
	JButton bt_regist; 
	ProductModel productModel=new ProductModel();
	
	
	public ProductListPage(AppMain appmain) {
		super(appmain);
		
		table=new JTable(productModel=new ProductModel());
		scroll=new JScrollPane(table);
		p_content = new JPanel();
		bt_regist = new JButton("등록");
		
		scroll.setPreferredSize(new Dimension((Config.ADMINMAIN_WIDTH-Config.SIDE_WIDTH-30), 400));

		add(scroll);
	}

}
