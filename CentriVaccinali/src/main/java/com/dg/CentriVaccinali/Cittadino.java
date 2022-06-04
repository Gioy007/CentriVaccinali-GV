package com.dg.CentriVaccinali;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Cittadino extends JFrame {

	private JPanel cittadino;
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cittadino frame = new Cittadino();
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
	public Cittadino() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 381);
		cittadino = new JPanel();
		cittadino.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cittadino);
		SpringLayout sl_login = new SpringLayout();
		cittadino.setLayout(sl_login);
		
		JLabel lblNewLabel = new JLabel("Menu Cittadino");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sl_login.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, cittadino);
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, cittadino);
		cittadino.add(lblNewLabel);
		
		JComboBox cvaccinale = new JComboBox();
		sl_login.putConstraint(SpringLayout.EAST, cvaccinale, -52, SpringLayout.EAST, cittadino);
		try {
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			PrintStream out = new PrintStream( socket.getOutputStream() );
			BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

			
			out.println("centriDisp");
			
			String response= in.readLine();
			String[] risposta =response.split(";");					
			
			for(int i=0;i<risposta.length;i++) {
				cvaccinale.addItem(risposta[i]);
			}

			cvaccinale.setSelectedIndex(-1);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cittadino.add(cvaccinale);
		
		JButton cerca = new JButton("Cerca");
		cerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_login.putConstraint(SpringLayout.NORTH, cerca, 6, SpringLayout.SOUTH, cvaccinale);
		sl_login.putConstraint(SpringLayout.EAST, cerca, 0, SpringLayout.EAST, cvaccinale);
		cittadino.add(cerca);
		
		JLabel lblNewLabel_1 = new JLabel("Centro vaccinale da consultare");
		sl_login.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 19, SpringLayout.SOUTH, lblNewLabel);
		sl_login.putConstraint(SpringLayout.NORTH, cvaccinale, -4, SpringLayout.NORTH, lblNewLabel_1);
		sl_login.putConstraint(SpringLayout.WEST, cvaccinale, 6, SpringLayout.EAST, lblNewLabel_1);
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		cittadino.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nome:");
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		sl_login.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -157, SpringLayout.SOUTH, cittadino);
		cittadino.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Indirizzo:");
		sl_login.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 6, SpringLayout.SOUTH, lblNewLabel_2);
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		cittadino.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tipologia:");
		sl_login.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 6, SpringLayout.SOUTH, lblNewLabel_3);
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel);
		cittadino.add(lblNewLabel_4);
		
		JLabel jindirizzo = new JLabel("N/D");
		sl_login.putConstraint(SpringLayout.WEST, jindirizzo, 6, SpringLayout.EAST, lblNewLabel_3);
		sl_login.putConstraint(SpringLayout.SOUTH, jindirizzo, 0, SpringLayout.SOUTH, lblNewLabel_3);
		cittadino.add(jindirizzo);
		
		JLabel jtipologia = new JLabel("N/D");
		sl_login.putConstraint(SpringLayout.NORTH, jtipologia, 6, SpringLayout.SOUTH, lblNewLabel_3);
		sl_login.putConstraint(SpringLayout.WEST, jtipologia, 0, SpringLayout.WEST, jindirizzo);
		cittadino.add(jtipologia);
		
		JLabel jnome = new JLabel("N/D");
		sl_login.putConstraint(SpringLayout.WEST, jnome, 0, SpringLayout.WEST, jindirizzo);
		sl_login.putConstraint(SpringLayout.SOUTH, jnome, 0, SpringLayout.SOUTH, lblNewLabel_2);
		cittadino.add(jnome);

	}
}
