package com.dg.Cittadino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/*
 * Classe principale del cittadino, è la prima schermata che si apre.
 * Da qua potrà andare a compiere tutte le azioni permesse per un cittadino,
 * invece se ci si vuole compiere azioni da operatore vaccinale bisognera prima autenticarsi come superuser
 * 
 * @author Giacomelli Davide 741844
 * @author Gioele Vicini 747818
 */
public class Cittadino extends javax.swing.JFrame {
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	private static Socket socket;
	private static PrintStream out;
	private static BufferedReader in;
	private static String selectedCV = "";
	private static String idutente;
	private static String scelta = "";
	
	public static String getScelta() {
		return scelta;
	}
	
    public Cittadino() {
        initComponents();
    }                      
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        comuneTextField = new javax.swing.JTextField();
        tipologiaComboBox = new javax.swing.JComboBox<>();
        ricercaButton = new javax.swing.JButton();
        prenotaButton = new javax.swing.JButton();
        eventoAvversoButton = new javax.swing.JButton();
        operatoriLoginButton = new javax.swing.JButton();
        listaComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Home cittadino");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setText("Cerca Centro Vaccinale");

        jLabel3.setText("Nome:");

        jLabel4.setText("Comune:");

        jLabel5.setText("Tipologia:");

        tipologiaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Azienda", "Hub", "Ospedale"}));

        ricercaButton.setText("Avvia ricerca");
        ricercaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					ricercaButtonActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        prenotaButton.setText("Prenota Centro Selezionato");        
        prenotaButton.addActionListener(new java.awt.event.ActionListener() {  
        	/*
        	 * Il metodo viene invocato quando si vuole prenotare un vaccino nel centro selezionato
        	 */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenotaButtonActionPerformed(evt);
            }
        });

        eventoAvversoButton.setText("Segnala evento avverso nel Centro");
        eventoAvversoButton.addActionListener(new java.awt.event.ActionListener() {
        	/*
        	 * Il metodo viene invocato quando si vuole registrare un evento avverso nel centro selezionato
        	 */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventoAvversoButtonActionPerformed(evt);
            }
        });

        operatoriLoginButton.setText("Login per Operatori");
        operatoriLoginButton.addActionListener(new java.awt.event.ActionListener() {
        	/*
        	 * Il metodo viene invocato quando si vuole accedere da operatori vaccinali
        	 */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operatoriLoginButtonActionPerformed(evt);
            }
        });
        
        JButton btnNewButton = new JButton("Sintomi avversi");
        btnNewButton.addActionListener(new ActionListener() {
        	/*
        	 * Visualizza un messageDialog con all'interno il numero di eventi avversi e la media della severita
        	 */
        	public void actionPerformed(ActionEvent e) {
        		selectedCV = (String)listaComboBox.getSelectedItem();
        		out.println("numeroMedia;"+selectedCV);
        		
        		try {
					String[] response= in.readLine().split(";");
					
					JOptionPane.showMessageDialog(null, "Numero di eventi avversi nel centro: "+response[0]+"\nMedia di severita dei casi: "+response[1]);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        		
        		
        	}
        });
        


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel2Layout.createSequentialGroup()
        							.addComponent(jLabel5)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(tipologiaComboBox, 0, 115, Short.MAX_VALUE))
        						.addComponent(ricercaButton, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        						.addGroup(jPanel2Layout.createSequentialGroup()
        							.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(jPanel2Layout.createSequentialGroup()
        									.addComponent(jLabel4)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(comuneTextField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
        								.addGroup(jPanel2Layout.createSequentialGroup()
        									.addComponent(jLabel3)
        									.addGap(18)
        									.addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
        								.addComponent(jLabel2))
        							.addGap(0, 0, Short.MAX_VALUE)))
        					.addGap(12)
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(eventoAvversoButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(prenotaButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(listaComboBox, Alignment.TRAILING, 0, 196, Short.MAX_VALUE))))
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addComponent(operatoriLoginButton)
        					.addGap(0, 255, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(18)
        			.addComponent(jLabel2)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(listaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel4)
        				.addComponent(comuneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewButton))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel5)
        				.addComponent(tipologiaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(ricercaButton)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(prenotaButton)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(eventoAvversoButton)
        			.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
        			.addComponent(operatoriLoginButton)
        			.addContainerGap())
        );
        jPanel2.setLayout(jPanel2Layout);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                  

    /*
     * Il metodo va a ricercare i centri vaccinali consoni ai parametri di ricerca
     * aggiungendo i risultati nel combobox
     */
    private void ricercaButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {   
    	
    	
        String nome = nomeTextField.getText().toLowerCase();
        String comune = comuneTextField.getText().toLowerCase();
        String tipologia = (String)tipologiaComboBox.getSelectedItem();
        tipologia = tipologia.toLowerCase();
        String request="";
        
        if(!nome.isBlank()) {
        	request = "cercaNome;"+nome;
        	out.println(request);
        	
        }
        else if(!comune.isBlank()) {
        	request = "cercaComune;"+comune+";"+tipologia;
        	out.println(request);
        	
        }else {
        	request = "cercaTipologia;"+tipologia;
        	out.println(request);
        }
        
        listaComboBox.removeAllItems();
        String response= in.readLine();
    	System.out.println(response);
		String[] risposta =response.split(";");
		
		for(String r : risposta) {
			listaComboBox.addItem(r);
		}
		
    }                                             
    
    /*
     * Porta alla schermata di login per autenticarsi come operatori
     */
    private void operatoriLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
    	setVisible(false);
		Login r=new Login();
		r.setVisible(true);
    }                                                    
    
    /*
     * Porta alla schermata di login per poi potersi prenotare per una nuova dose
     */
    private void prenotaButtonActionPerformed(java.awt.event.ActionEvent evt) {  
    	selectedCV = (String)listaComboBox.getSelectedItem();
    	scelta = "prenota";
    	
    	if(!selectedCV.equals("")) {
    		setVisible(false);
    		Login r=new Login();
    		r.setVisible(true);
    	}else {
    		JOptionPane.showMessageDialog(jPanel2, "Si prega di selezionare un Centro Vaccinale");
    	}
    	
    }                                             

    /*
     * Porta alla schermata di login per poi potersi registrare un nuovo evento avverso
     */
    private void eventoAvversoButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    	selectedCV = (String)listaComboBox.getSelectedItem();
    	scelta = "sintomi";
    	
    	if(!selectedCV.equals("")) {
    		setVisible(false);
    		Login r = new Login();
    		r.setVisible(true);
    	}else {
    		JOptionPane.showMessageDialog(jPanel2, "Si prega di selezionare un Centro Vaccinale");
    	}
    }    
    
    /*
     * Get della variabile socket
     */
    public static Socket getSocket() {
		return socket;
	}
    
	/*
	 *	Getter del printStream necessario alla scrittura dei dati 
	 */
	public static PrintStream getOut() {
		return out;
	}
	
	/*
	 * Getter del bufferReader necessario alla lettura dei dati 
	 */
	public static BufferedReader getIn() {
		return in;
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			out = new PrintStream( socket.getOutputStream() );
			in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cittadino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cittadino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cittadino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cittadino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cittadino().setVisible(true);
            }
        });
    }
                  
    public static String getIdutente() {
		return idutente;
	}
	public static void setIdutente(String idutente) {
		Cittadino.idutente = idutente;
	}
	
	public static String getSelectedCV() {
		return idutente;
	}
	public static void setSelectedCV(String selectedCV) {
		Cittadino.selectedCV = selectedCV;
	}
    private javax.swing.JTextField comuneTextField;
    private javax.swing.JButton eventoAvversoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JButton operatoriLoginButton;
    private javax.swing.JButton prenotaButton;
    private javax.swing.JButton ricercaButton;
    private javax.swing.JComboBox<String> tipologiaComboBox;  
    private javax.swing.JComboBox<String> listaComboBox;
}
