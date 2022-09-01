package com.dg.CentriVaccinali;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Classe che si occupa del primo test di connessione al DB e di gestire le
 * connessioni al server
 * 
 * @author Giacomelli Davide 741844
 * @author Gioele Vicini 747818
 */
public class ServerCV extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private static final int PORT = 9090; // porta utilizzata dal server per accettare le connessioni

	/*
	 * dovranno essere di input
	 */
	private static String url = ""; // jdbc:postgresql://localhost:5432/CentriVaccinali
	private static String username = ""; // eclipse
	private static String password = ""; // 1234

	/*
	 * numero massimo di thread gestibili dal server(circa tutta la popolazione
	 * italiana)
	 */
	private static ExecutorService pool = Executors.newFixedThreadPool(60000000);

	/*
	 * lista dei client connessi
	 */
	private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

	private Connection conn;
	private static Boolean statoDB = false;

	/**
	 * Costruttore della classe che chiama il metodo per disegnare la GUI
	 */
	public ServerCV() {
		initComponents();
	}

	/**
	 * Metodo per la creazione dell'interfaccia grafica
	 */
	private void initComponents() {

		jPanel = new javax.swing.JPanel();
		urlDBLabel = new javax.swing.JLabel();
		adminLabel = new javax.swing.JLabel();
		pswLabel = new javax.swing.JLabel();
		urlTextField = new javax.swing.JTextField();
		urlTextField.setText("localhost");
		adminTextField = new javax.swing.JTextField();
		adminTextField.setText("eclipse");
		passwordField = new javax.swing.JPasswordField();
		jButton1 = new javax.swing.JButton();
		label = new javax.swing.JLabel();
		statoLabel = new JLabel("New label");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		urlDBLabel.setText("URL");

		adminLabel.setText("Admin username");

		pswLabel.setText("Password");
		passwordField.setText("1234");

		jButton1.setText("Connettiti");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton1ActionPerformed(evt);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		label.setText("Server DataBase Connector");
		
		lblNewLabel = new JLabel("Stato:");

		javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
		jPanelLayout.setHorizontalGroup(
			jPanelLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanelLayout.createSequentialGroup()
							.addGap(175)
							.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(jPanelLayout.createSequentialGroup()
							.addGroup(jPanelLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanelLayout.createSequentialGroup()
									.addComponent(pswLabel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(jPanelLayout.createSequentialGroup()
									.addComponent(urlDBLabel, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
									.addGap(40))
								.addGroup(jPanelLayout.createSequentialGroup()
									.addComponent(adminLabel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(jPanelLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(urlTextField, 110, 110, 110)
								.addComponent(passwordField, 110, 110, 110)
								.addComponent(adminTextField, Alignment.TRAILING, 110, 110, 110)))
						.addGroup(jPanelLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(0, 120, Short.MAX_VALUE))
						.addGroup(jPanelLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(statoLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		jPanelLayout.setVerticalGroup(
			jPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(12)
					.addGroup(jPanelLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(urlDBLabel, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(urlTextField, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(jPanelLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(adminTextField, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(adminLabel, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(jPanelLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pswLabel, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addGroup(jPanelLayout.createSequentialGroup()
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)))
					.addGap(18)
					.addGroup(jPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(statoLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		jPanel.setLayout(jPanelLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		pack();
	}

	/**
	 * Metodo che si occupa di testare le credenziali per la connessione al db
	 * 
	 * @param evt click sul pulsante per connettersi al server
	 * @throws SQLException eccezzione che entra in causa nel caso le credenziali
	 *                      siano sbagliate
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		url = "jdbc:postgresql://" + urlTextField.getText() + ":5432/CentriVaccinali";
		username = adminTextField.getText();
		password = String.valueOf(passwordField.getPassword());

		System.out.println("Controllo credenziali...");

		if (controlloCredenziali()) {
			urlTextField.setText("");
			adminTextField.setText("");
			passwordField.setText("");
			statoLabel.setText("connesso");
			statoLabel.setForeground(Color.GREEN);
			statoDB = true;
		} else {
			JOptionPane.showMessageDialog(jPanel, "Credenziali non corrette, riprovare");
		}
	}

	/**
	 * effettivo controllo delle credenziali precedentemente ottenute dai textField
	 * 
	 * @return true se i valori sono giusti(testanto attivamente la connessione),
	 *         false altrimenti
	 */
	private boolean controlloCredenziali() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			conn.close();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ServerCV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ServerCV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ServerCV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ServerCV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		new ServerCV().setVisible(true);
		
		
		statoLabel.setText("non connesso al db");
		statoLabel.setForeground(Color.RED);
		while(!statoDB) {
			System.out.println("Attendo db");
		}
		
		while (true) {
				try {
					ServerSocket listener = new ServerSocket(PORT);
					System.out.println("Attendo connessioni");
					Socket client = listener.accept();
					ClientHandler clientThread = new ClientHandler(client, url, username, password);
					clients.add(clientThread);
					// clientThread.run();
					pool.execute(clientThread);

				} catch (Exception e) {
					System.out.print(e);
				}
			
		}

	}

	private javax.swing.JLabel adminLabel;
	private javax.swing.JTextField adminTextField;
	private javax.swing.JButton jButton1;
	private javax.swing.JPanel jPanel;
	private javax.swing.JLabel label;
	private javax.swing.JPasswordField passwordField;
	private javax.swing.JLabel pswLabel;
	private javax.swing.JLabel urlDBLabel;
	private static javax.swing.JLabel statoLabel;
	private javax.swing.JTextField urlTextField;
	private JLabel lblNewLabel;
}