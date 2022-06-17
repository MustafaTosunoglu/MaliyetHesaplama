package com.evmia.maliyet;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Main {
	private static JTable table;
	static JFrame mainFrame = null;

	public static void main(String[] args) {
		mainFrame = new JFrame();
		mainFrame.setSize(400, 500);// 400 width and 500 height
		mainFrame.getContentPane().setLayout(null);// using no layout managers
		mainFrame.setLocationRelativeTo(null);


		String data[][] = { { "101", "Amit", "670000" }, { "102", "Jai", "780000" }, { "101", "Sachin", "700000" }, { "1010", "Sachin", "700000" } };
		String column[] = { "ID", "NAME", "SALARY" };
		table = new JTable(data, column);
		table.setBounds(10, 11, 364, 405);
		table.setEnabled(false);
		mainFrame.setVisible(true);// making the frame visible
		JButton btnNewButton = new JButton("\u00DCr\u00FCn Ekle");
		btnNewButton.setBounds(58, 427, 136, 23);
		mainFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hammadde Ekle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				new Hammadde(mainFrame).setVisible(true);
			}
		});
		btnNewButton_1.setBounds(197, 427, 136, 23);
		mainFrame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(10, 11);
		scrollPane.setSize(364, 405);
		mainFrame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
	}
}
