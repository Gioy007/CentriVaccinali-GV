package com.dg.Cittadino;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/*
 * Classe per autenticarsi al portale e per poter effettuare la registrazione
 *  di un evento avverso o per prenotare una dose
 * 
 * @author Giacomelli Davide 741844
 * @author Gioele Vicini 747818
 */
public class Login extends JFrame {

	private JPanel login;
	private JPasswordField jpsw;
	private JTextField jemail;

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 274, 306);
		login = new JPanel();
		login.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(login);
		SpringLayout sl_login = new SpringLayout();
		login.setLayout(sl_login);

		JLabel lblNewLabel = new JLabel("Login");
		sl_login.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, login);
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, login);
		sl_login.putConstraint(SpringLayout.EAST, lblNewLabel, -147, SpringLayout.EAST, login);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		login.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("E-mail");
		sl_login.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 19, SpringLayout.SOUTH, lblNewLabel);
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		login.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		sl_login.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 23, SpringLayout.SOUTH, lblNewLabel_1);
		sl_login.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		sl_login.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -140, SpringLayout.SOUTH, login);
		login.add(lblNewLabel_2);

		jpsw = new JPasswordField();
		sl_login.putConstraint(SpringLayout.NORTH, jpsw, -3, SpringLayout.NORTH, lblNewLabel_2);
		sl_login.putConstraint(SpringLayout.WEST, jpsw, 49, SpringLayout.EAST, lblNewLabel_2);
		sl_login.putConstraint(SpringLayout.EAST, jpsw, -18, SpringLayout.EAST, login);
		login.add(jpsw);

		jemail = new JTextField();
		sl_login.putConstraint(SpringLayout.WEST, jemail, 72, SpringLayout.EAST, lblNewLabel_1);
		sl_login.putConstraint(SpringLayout.SOUTH, jemail, -12, SpringLayout.NORTH, jpsw);
		sl_login.putConstraint(SpringLayout.EAST, jemail, 0, SpringLayout.EAST, jpsw);
		login.add(jemail);
		jemail.setColumns(10);

		jpsw.setText("c");
		jemail.setText("c");

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			/*
			 * Viene invocato quando si clicca sul bottone Login, verifica che le
			 * credenziali siano corrette e reidirizza l'utente alla finetra desiderata
			 */
			public void actionPerformed(ActionEvent e) {
				String email = jemail.getText();
				String psw = String.valueOf(jpsw.getPassword());

				if (!email.equals("") && !psw.equals("")) {
					try {
						String request = "login;" + email + ";" + psw;
						Cittadino.getOut().println(request);

						String[] risposta = Cittadino.getIn().readLine().split(";");
						Cittadino.setIdutente(risposta[1]);

						if (risposta[0].equals("t")) {

							setVisible(false);
							OperatoreVaccinale s = new OperatoreVaccinale();
							s.setVisible(true);

						}

						if (risposta[0].equals("f")) {

							if (Cittadino.getScelta().equals("operatore"))
								JOptionPane.showMessageDialog(login, "L'utente scelto non e' admin");

							if (Cittadino.getScelta().equals("prenota")) {
								setVisible(false);
								Prenota p = new Prenota();
								p.setVisible(true);
								setVisible(false);
							}
							if (Cittadino.getScelta().equals("sintomi")) {
								Cittadino.getOut().println("vaccini;" + Cittadino.getIdutente());
								String s = Cittadino.getIn().readLine();
								if (!s.equals("NO")) {
									setVisible(false);
									Sintomi sin = new Sintomi();
									sin.setVisible(true);
								} else {
									JOptionPane.showMessageDialog(null,
											"Non puoi segnalare un evento avverso senza aver fatto il vaccino");
									setVisible(false);
									Cittadino c = new Cittadino();
									c.setVisible(true);

								}
							}
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(login, "Nome utente o password mancanti");
			}
		});
		sl_login.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, login);
		sl_login.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, login);
		login.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Registrati");
		btnNewButton_1.addActionListener(new ActionListener() {
			/*
			 * Serve per fare in modo che un utente si possa registrare al portale e poter
			 * effettuare il vaccino. Porta alla schermata di registrazione
			 */
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Registrati r = new Registrati();
				r.setVisible(true);

			}
		});
		sl_login.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		sl_login.putConstraint(SpringLayout.EAST, btnNewButton_1, -6, SpringLayout.WEST, btnNewButton);
		login.add(btnNewButton_1);
	}
}