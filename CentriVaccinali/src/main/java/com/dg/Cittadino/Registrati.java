package com.dg.Cittadino;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;

/*
 * Classe per registrare cittadino al portale
 * 
 * @author Giacomelli Davide 741844
 * @author Gioele Vicini 747818
 */
public class Registrati extends javax.swing.JFrame {

    /**
     * Creates new form Registrati
     */
    public Registrati() {
        initComponents();
    }

    /*
	 * Metodo usato per inviare una richiesta al server di registrazione con risposta
	 */
	private boolean registraCittadino(String nome,String cognome,String cf,String email,String psw) throws SQLException {
        try {        	
			String outString="registraCitt;"+nome+";"+cognome+";"+cf+";"+psw+";"+email;
			Cittadino.getOut().println(outString);			

            if(Cittadino.getIn().readLine().equals("OK")) {
            	JOptionPane.showMessageDialog(null, "Registrazione avvenuta");
            }else if(Cittadino.getIn().readLine().equals("OK")) {
            	JOptionPane.showMessageDialog(null, "Esiste già un utente con questa email o codice fiscale");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
		
		return true;
	}
	
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        emailTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        registrationButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        repeatPasswordField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        cognomeTextField = new javax.swing.JTextField();
        cfTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Registrazione");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setText("E-Mail");

        jLabel3.setText("Password");

        backButton.setText("<-");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        registrationButton.setText("Registrati");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Ripeti password");

        jLabel5.setText("Nome");

        jLabel6.setText("Cognome");

        jLabel7.setText("Codice Fiscale");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registrationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(cognomeTextField)
                            .addComponent(repeatPasswordField)
                            .addComponent(passwordField)
                            .addComponent(emailTextField)
                            .addComponent(cfTextField))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cognomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(cfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(repeatPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(registrationButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                       

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	setVisible(false);
        Login log = new Login();
        log.setVisible(true);
    }                                          

    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        String nome= nomeTextField.getText();
		String cognome= cognomeTextField.getText();
		String cf= cfTextField.getText();
		String email= emailTextField.getText();
		String psw= String.valueOf(passwordField.getPassword());
		String rpsw= String.valueOf(repeatPasswordField.getPassword());
		
		if(!nome.equals("") && !cognome.equals("") && !cf.equals("") && !email.equals("") && !psw.equals("") && !rpsw.equals("")) {
			if(passwordValidity(psw, rpsw)) {
				if(chkCF(cf)) {
					if(chkMail(email)) {
						
						
						try {
							registraCittadino(nome, cognome, cf, email, psw);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						setVisible(false);
						Login l=new Login();
						l.setVisible(true);
						
					}
					else {
						JOptionPane.showMessageDialog(jPanel2, "L'e-mail inserita non è valida");
					}
				}
				else {
					JOptionPane.showMessageDialog(jPanel2, "Il codice fiscale inserito non è della lunghezza richiesta");
				}
			}
			else {
				JOptionPane.showMessageDialog(jPanel2, "La password inserita non è valida (lunga almeno 8 caratteri e con un numero) oppure è stata ripetuta in maniera errata");
			}
		}
		else JOptionPane.showMessageDialog(jPanel2, "Si prega di inserire tutti i dati");
    }                                                  

    private boolean chkMail(String email) {
		if(email.contains("@")) {
			int posChiocciola = email.indexOf("@");
			String postChiocciola = email.substring(posChiocciola+1);
			String preChiocciola = email.substring(0, posChiocciola);
			if(!preChiocciola.equals("")) {
				if(postChiocciola.contains(".")) {
					int posPunto = postChiocciola.indexOf(".");
					String postPunto = postChiocciola.substring(posPunto+1);
					String prePunto = postChiocciola.substring(0, posPunto);
					if(!prePunto.equals("")) {
						if(!postPunto.equals("")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
    
    private boolean chkCF(String cf) {
		if(cf.length() == 16) {
			return true;
		}
		return false;
	}
    
    private boolean passwordValidity(String psw, String r) {
		if(psw.length()>=8) {
			if(psw.contains("1") || psw.contains("2") || psw.contains("3") || psw.contains("4") || psw.contains("5") ||
					psw.contains("6") || psw.contains("7") || psw.contains("8") || psw.contains("9") || psw.contains("0")) {
				if(psw.equals(r))
					return true;
			}
		}
		return false;
	}
    public static void main(String args[]) {
		
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registrati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrati().setVisible(true);
            }
        });
    }
                 
    private javax.swing.JButton backButton;
    private javax.swing.JTextField cfTextField;
    private javax.swing.JTextField cognomeTextField;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton registrationButton;
    private javax.swing.JPasswordField repeatPasswordField;
	
}
