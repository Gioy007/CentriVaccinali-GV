package com.dg.CentriVaccinali;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;

public class Registrati extends JFrame {

	private JPanel registrati;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;
	private JTextField jnome;
	private JTextField jcognome;
	private JTextField jcf;
	private JTextField jemail;
	private JTextField jpsw;
	private JTextField jrpsw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrati frame = new Registrati();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private boolean registraCittadino(String nome,String cognome,String cf,String email,String psw) throws SQLException {
		
		String url = "jdbc:postgresql://localhost:5432/CentriVaccinali";
        String username = "eclipse";
        String password = "1234";

        try {
        	
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            
            String query = "INSERT INTO utenti (nome, cognome, cf, password, email)"
            			+ "VALUES ('"+nome+"', '"+cognome+"', '"+cf+"', '"+email+"', '"+psw+"');";

            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Registrazione avvenuta");
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
		
		return true;
	}
	
	public Registrati() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 429);
		registrati = new JPanel();
		registrati.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(registrati);
		
		JLabel lblNewLabel = new JLabel("Registrazione");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		lblNewLabel_1 = new JLabel("Nome");
		
		lblNewLabel_2 = new JLabel("Cognome");
		
		lblNewLabel_3 = new JLabel("CF");
		
		lblNewLabel_4 = new JLabel("E-mail");
		
		lblNewLabel_5 = new JLabel("Password");
		
		btnNewButton = new JButton("Registrati");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome= jnome.getText();
				String cognome= jcognome.getText();
				String cf= jcf.getText();
				String email= jemail.getText();
				String psw= jpsw.getText();
				String rpsw= jrpsw.getText();
				
				if(psw.equals(rpsw)&& !psw.equals("")) {
					
					try {
						registraCittadino(nome, cognome, cf, email, psw);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}
					
					setVisible(false);
					Login l=new Login();
					l.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Password diverse");
				}
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("Ripeti password");
		SpringLayout sl_registrati = new SpringLayout();
		sl_registrati.putConstraint(SpringLayout.WEST, lblNewLabel_6, 0, SpringLayout.WEST, registrati);
		sl_registrati.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0, SpringLayout.WEST, lblNewLabel_2);
		sl_registrati.putConstraint(SpringLayout.EAST, lblNewLabel_5, -497, SpringLayout.EAST, registrati);
		sl_registrati.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel_2);
		sl_registrati.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 12, SpringLayout.SOUTH, lblNewLabel_1);
		sl_registrati.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -12, SpringLayout.NORTH, lblNewLabel_3);
		sl_registrati.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel_2);
		sl_registrati.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, registrati);
		sl_registrati.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, registrati);
		sl_registrati.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, registrati);
		sl_registrati.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, registrati);
		sl_registrati.putConstraint(SpringLayout.SOUTH, lblNewLabel, 53, SpringLayout.NORTH, registrati);
		registrati.setLayout(sl_registrati);
		
		jnome = new JTextField();
		sl_registrati.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, jnome);
		sl_registrati.putConstraint(SpringLayout.WEST, jnome, 60, SpringLayout.EAST, lblNewLabel_1);
		sl_registrati.putConstraint(SpringLayout.EAST, jnome, -290, SpringLayout.EAST, registrati);
		sl_registrati.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 3, SpringLayout.NORTH, jnome);
		sl_registrati.putConstraint(SpringLayout.NORTH, jnome, 6, SpringLayout.SOUTH, lblNewLabel);
		jnome.setColumns(10);
		registrati.add(jnome);
		registrati.add(lblNewLabel_2);
		registrati.add(lblNewLabel_3);
		registrati.add(lblNewLabel_4);
		registrati.add(lblNewLabel_5);
		registrati.add(lblNewLabel);
		registrati.add(lblNewLabel_1);
		registrati.add(btnNewButton);
		registrati.add(lblNewLabel_6);
		
		jcognome = new JTextField();
		sl_registrati.putConstraint(SpringLayout.NORTH, jcognome, 6, SpringLayout.SOUTH, jnome);
		sl_registrati.putConstraint(SpringLayout.WEST, jcognome, 0, SpringLayout.WEST, jnome);
		sl_registrati.putConstraint(SpringLayout.EAST, jcognome, 0, SpringLayout.EAST, jnome);
		registrati.add(jcognome);
		jcognome.setColumns(10);
		
		jcf = new JTextField();
		sl_registrati.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 3, SpringLayout.NORTH, jcf);
		sl_registrati.putConstraint(SpringLayout.NORTH, jcf, 6, SpringLayout.SOUTH, jcognome);
		sl_registrati.putConstraint(SpringLayout.WEST, jcf, 0, SpringLayout.WEST, jnome);
		sl_registrati.putConstraint(SpringLayout.EAST, jcf, 166, SpringLayout.WEST, jnome);
		registrati.add(jcf);
		jcf.setColumns(10);
		
		jemail = new JTextField();
		sl_registrati.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 3, SpringLayout.NORTH, jemail);
		sl_registrati.putConstraint(SpringLayout.NORTH, jemail, 6, SpringLayout.SOUTH, jcf);
		sl_registrati.putConstraint(SpringLayout.WEST, jemail, 87, SpringLayout.WEST, registrati);
		sl_registrati.putConstraint(SpringLayout.EAST, jemail, 0, SpringLayout.EAST, jnome);
		registrati.add(jemail);
		jemail.setColumns(10);
		
		jpsw = new JTextField();
		sl_registrati.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 3, SpringLayout.NORTH, jpsw);
		sl_registrati.putConstraint(SpringLayout.NORTH, jpsw, 6, SpringLayout.SOUTH, jemail);
		sl_registrati.putConstraint(SpringLayout.WEST, jpsw, 0, SpringLayout.WEST, jnome);
		sl_registrati.putConstraint(SpringLayout.EAST, jpsw, 253, SpringLayout.WEST, registrati);
		registrati.add(jpsw);
		jpsw.setColumns(10);
		
		jrpsw = new JTextField();
		sl_registrati.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, jrpsw);
		sl_registrati.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 3, SpringLayout.NORTH, jrpsw);
		sl_registrati.putConstraint(SpringLayout.NORTH, jrpsw, 6, SpringLayout.SOUTH, jpsw);
		sl_registrati.putConstraint(SpringLayout.WEST, jrpsw, 0, SpringLayout.WEST, jnome);
		sl_registrati.putConstraint(SpringLayout.EAST, jrpsw, -290, SpringLayout.EAST, registrati);
		registrati.add(jrpsw);
		jrpsw.setColumns(10);
	}
}
