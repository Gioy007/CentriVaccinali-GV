package com.dg.CentriVaccinali;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

/**
 * Classe che si occupa del primo test di connessione al DB e di gestire le connessioni al server
 * 
 * @author Giacomelli Davide 741844
 * @author Gioele Vicini 747818
 */
public class ServerCV extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;

	private static final int PORT = 9090; //porta utilizzata dal server per accettare le connessioni
	
	/*
	 * dovranno essere di input
	 */
	private static String url = ""; //jdbc:postgresql://localhost:5432/CentriVaccinali
	private static String username = ""; //eclipse
	private static String password = ""; //1234
	
	/*
	 * numero massimo di thread gestibili dal server(circa tutta la popolazione italiana)
	 */
	private static ExecutorService pool = Executors.newFixedThreadPool(60000000);

	/*
	 * lista dei client connessi
	 */
	private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	
	private Connection conn;

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

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(pswLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(urlDBLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(adminLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(urlTextField)
                            .addComponent(passwordField)
                            .addComponent(adminTextField, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(label)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addGap(12, 12, 12)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(urlDBLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(urlTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(adminLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(pswLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                        .addGap(136, 136, 136))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }    
    /**
     * Metodo che si occupa di testare le credenziali per la connessione al db
     * 
     * @param evt click sul pulsante per connettersi al server
     * @throws SQLException eccezzione che entra in causa nel caso le credenziali siano sbagliate
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {  
        url = "jdbc:postgresql://"+urlTextField.getText()+":5432/CentriVaccinali";
        username = adminTextField.getText();
        password = passwordField.getText();
        
        System.out.println("Controllo credenziali...");
        
        if(controlloCredenziali()) {
        	urlTextField.setText("");
        	adminTextField.setText("");
        	passwordField.setText("");
        	JOptionPane.showMessageDialog(jPanel, "Credenziali corrette, rimango in attesa di connessioni...");
        }else {
        	JOptionPane.showMessageDialog(jPanel, "Credenziali non corrette, riprovare");
        }
    }                                        

    /**
     * effettivo controllo delle credenziali precedentemente ottenute dai textField
     * @return true se i valori sono giusti(testanto attivamente la connessione), false altrimenti
     */
    private boolean controlloCredenziali() {
    	try {
        	conn = DriverManager.getConnection(url, username, password);   
        	conn.close();
        	return true;
        }catch(Exception e) {
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
		try {
			ServerSocket listener = new ServerSocket(PORT);
			while (true) {
				Socket client = listener.accept();
				ClientHandler clientThread = new ClientHandler(client, url, username, password);
				clients.add(clientThread);
				//clientThread.run();
				pool.execute(clientThread);
			}
		}catch (Exception e) {
			System.out.print(e);
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
    private javax.swing.JTextField urlTextField;       
}