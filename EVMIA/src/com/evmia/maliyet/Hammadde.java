package com.evmia.maliyet;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hammadde extends JFrame{
	private static final String SQL_HAMMADDE_INSERT = "INSERT INTO Hammadde (hammaddeAdi, olcuBirimi, birimFiyati) VALUES (?,?,?)";
	private static final String COLUMNS[] = { "hammaddeAdi", "olcuBirimi", "birimFiyati" };
	
	public Hammadde(JFrame mainFrame) {
		this.setSize(400, 516);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		Object data[][] = hammaddeArrayGetir();
		table = new JTable(data, COLUMNS);
		table.setBounds(10, 11, 363, 405);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		getContentPane().add(table);
		
		hammaddeAdi = new JTextField();
		hammaddeAdi.setBounds(10, 427, 121, 20);
		getContentPane().add(hammaddeAdi);
		hammaddeAdi.setColumns(10);
		
		olcuBirimi = new JTextField();
		olcuBirimi.setBounds(131, 427, 121, 20);
		getContentPane().add(olcuBirimi);
		olcuBirimi.setColumns(10);
		
		birimFiyati = new JTextField();
		birimFiyati.setBounds(252, 427, 121, 20);
		getContentPane().add(birimFiyati);
		birimFiyati.setColumns(10);
		
		JButton btnNewButton = new JButton("Kaydet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = DatabaseConnector.connect();
				try {
					String birimFiyatiStr;
					if(birimFiyati.getText().contains(",")) {
						birimFiyatiStr = birimFiyati.getText().replace(",", ".");
					} else {
						birimFiyatiStr = birimFiyati.getText();
					}
					PreparedStatement preparedStatement = con.prepareStatement(SQL_HAMMADDE_INSERT);
			        preparedStatement.setString(1, hammaddeAdi.getText());
			        preparedStatement.setString(2, olcuBirimi.getText());
			        preparedStatement.setBigDecimal(3, new BigDecimal(birimFiyatiStr));
			        preparedStatement.execute();
			        
			        Object data[][] = hammaddeArrayGetir();
			        TableModel model = new DefaultTableModel(data, COLUMNS);
			        table.setModel(model);
			        clear();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						con.close();
					} catch (SQLException e1) {
						//
					}

				}

			}
		});
		btnNewButton.setBounds(131, 450, 123, 23);
		getContentPane().add(btnNewButton);
		
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        mainFrame.setVisible(true);
		    }
		});
	}
	
	private Object [][] hammaddeArrayGetir(){
		List<Object[]> arrList = new ArrayList<Object[]>();
		Connection con = DatabaseConnector.connect();
		Statement stt = null;
		try {
			stt = con.createStatement();
			ResultSet rs = stt.executeQuery("SELECT hammaddeAdi, olcuBirimi, birimFiyati from Hammadde");
			while (rs.next()) {
				Object[] arr = new Object[3];
				arr[0] = rs.getString("hammaddeAdi");
				arr[1] = rs.getString("olcuBirimi");
				arr[2] = rs.getString("birimFiyati");
				arrList.add(arr);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				con.close();
				stt.close();
			} catch (SQLException e1) {
				//
			}
			
		}
		Object data[][] = new Object[arrList.size()][]; 
		for(Object[] arr:arrList) {
			data[arrList.indexOf(arr)] = arr;
		}
		return data;
	}
	
	private void clear() {
		hammaddeAdi.setText(null);
		olcuBirimi.setText(null);
		birimFiyati.setText(null);
	}
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField hammaddeAdi;
	private JTextField olcuBirimi;
	private JTextField birimFiyati;
}
