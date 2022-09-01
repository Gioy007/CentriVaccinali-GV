package com.dg.Cittadino;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SintomiAvversi extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel mediaSintomiGenerale;
	private int n=0;
	private int m=2;
	private String[] columnNames = { "Evento", "Severita' media", "Numero casi"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SintomiAvversi frame = new SintomiAvversi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SintomiAvversi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel nomeCentro = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.WEST, nomeCentro, 10, SpringLayout.WEST, contentPane);
		nomeCentro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(nomeCentro);
		
		
		
        
		//create table model with data
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override 
            public int getColumnCount() { 
                return columnNames.length; 
            } 

            @Override 
            public String getColumnName(int index) { 
                return columnNames[index]; 
            }
        };
        
        nomeCentro.setText(Cittadino.getSelectedCV());
        Cittadino.getOut().println("sintomiAvversiAVG;"+Cittadino.getSelectedCV());
        
        
        try {
			String[] response= Cittadino.getIn().readLine().split(";");

			for(int i=0;i<response.length;i++) {
				model.addRow(new Object[]{response[i], response[++i], response[++i]});
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
        
        
		
		table = new JTable(model);
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, table, 41, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, table, -38, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, nomeCentro, -6, SpringLayout.NORTH, table);
		sl_contentPane.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, table, 383, SpringLayout.WEST, contentPane);
		contentPane.add(table);
		
		lblNewLabel = new JLabel("Media generale della severita dei sintomi: ");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblNewLabel);
		
		
		Cittadino.getOut().println("numeroMedia;"+Cittadino.getSelectedCV());		
		try {
			String[] response= Cittadino.getIn().readLine().split(";");
			System.out.println(response[1]);
			Float f= (float) (Math.round(Float.parseFloat(response[1])*1000d)/1000d);
			mediaSintomiGenerale = new JLabel(f.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		sl_contentPane.putConstraint(SpringLayout.NORTH, mediaSintomiGenerale, 0, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, mediaSintomiGenerale, 6, SpringLayout.EAST, lblNewLabel);
		contentPane.add(mediaSintomiGenerale);
		
		JButton backButton = new JButton("<--");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, backButton, 6, SpringLayout.SOUTH, table);
		sl_contentPane.putConstraint(SpringLayout.EAST, backButton, 0, SpringLayout.EAST, table);
		contentPane.add(backButton);

	}
}
