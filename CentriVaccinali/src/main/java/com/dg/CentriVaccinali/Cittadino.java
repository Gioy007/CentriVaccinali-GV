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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Cittadino extends JFrame {

	private JPanel cittadino;
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	static Socket socket;
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
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
		} catch (Exception e) {

		}
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
		
		final JComboBox cvaccinale = new JComboBox();
		sl_login.putConstraint(SpringLayout.EAST, cvaccinale, -52, SpringLayout.EAST, cittadino);
		try {
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
		
		final JLabel jindirizzo = new JLabel("N/D");
		sl_login.putConstraint(SpringLayout.WEST, jindirizzo, 6, SpringLayout.EAST, lblNewLabel_3);
		sl_login.putConstraint(SpringLayout.SOUTH, jindirizzo, 0, SpringLayout.SOUTH, lblNewLabel_3);
		cittadino.add(jindirizzo);
		
		final JLabel jtipologia = new JLabel("N/D");
		sl_login.putConstraint(SpringLayout.NORTH, jtipologia, 6, SpringLayout.SOUTH, lblNewLabel_3);
		sl_login.putConstraint(SpringLayout.WEST, jtipologia, 0, SpringLayout.WEST, jindirizzo);
		cittadino.add(jtipologia);
		
		final JLabel jnome = new JLabel("N/D");
		sl_login.putConstraint(SpringLayout.WEST, jnome, 0, SpringLayout.WEST, jindirizzo);
		sl_login.putConstraint(SpringLayout.SOUTH, jnome, 0, SpringLayout.SOUTH, lblNewLabel_2);
		cittadino.add(jnome);
		
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			
					PrintStream out = new PrintStream( socket.getOutputStream() );
					BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
					String request="cercaInfo;"+cvaccinale.getSelectedItem();
					

					out.println(request);
					System.out.println("mandato");
					String response= in.readLine();
					String[] risposta =response.split(";");					
					
					jnome.setText(risposta[0]);
					jindirizzo.setText(risposta[1]);
					jtipologia.setText(risposta[2]);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		sl_login.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, cvaccinale);
		sl_login.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, cvaccinale);
		cittadino.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login r=new Login(socket);
				r.setVisible(true);
			}
		});
		sl_login.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -10, SpringLayout.SOUTH, cittadino);
		sl_login.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, cvaccinale);
		cittadino.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Registrati");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Registrati r=new Registrati(socket);
				r.setVisible(true);
				
			}
		});
		sl_login.putConstraint(SpringLayout.SOUTH, btnNewButton_2, 0, SpringLayout.SOUTH, btnNewButton_1);
		sl_login.putConstraint(SpringLayout.EAST, btnNewButton_2, -6, SpringLayout.WEST, btnNewButton_1);
		cittadino.add(btnNewButton_2);

	}
}
