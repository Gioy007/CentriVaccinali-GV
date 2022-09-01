package com.dg.Cittadino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

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
	private static String selectedCV;
	private static String idutente;
	private static String scelta = "";
	private static boolean connOK=false;
	
	public static String getScelta() {
		return scelta;
	}
	public void avviaSocket() {
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			out = new PrintStream( socket.getOutputStream() );
			in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			connOK=true;
		} catch (Exception e) {		
			connOK=false;
		}
	}
	
    public Cittadino() {
    	avviaSocket();
        if(connOK) {
        	initComponents();
        }
        else {
        	JOptionPane.showMessageDialog(null, "Server non trovato, attendo server");
        	
        	while(!connOK) {
        		avviaSocket();
        		System.out.println("Cerco");
        		
        	}
        	JOptionPane.showMessageDialog(null, "Server trovato");
        	initComponents();
        }
    }     
                
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        operatoriLoginButton = new javax.swing.JButton();
        ricercaButton = new javax.swing.JButton();
        nomeTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comuneTextField = new javax.swing.JTextField();
        tipologiaComboBox = new javax.swing.JComboBox<>();
        listaComboBox = new javax.swing.JComboBox<>();
        infoButton = new javax.swing.JButton();
        sintomiAvversiButton = new javax.swing.JButton();
        segnalaEventoButton = new javax.swing.JButton();
        prenotaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Home Cittadino");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        operatoriLoginButton.setText("Login per Operatori");
        operatoriLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operatoriLoginButtonActionPerformed(evt);
            }
        });

        ricercaButton.setText("Avvia ricerca");
        ricercaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ricercaButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Cerca Centro Vaccinale");

        jLabel3.setText("Nome");

        jLabel4.setText("Comune");

        jLabel5.setText("Tipologia");

        tipologiaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Azienda", "Hub", "Ospedale" }));

        infoButton.setText("Informazioni Centro Vaccinale");
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        sintomiAvversiButton.setText("Sintomi avversi");
        sintomiAvversiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sintomiAvversiButtonActionPerformed(evt);
            }
        });

        segnalaEventoButton.setText("Segnala evento avverso nel Centro");
        segnalaEventoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segnalaEventoButtonActionPerformed(evt);
            }
        });

        prenotaButton.setText("Prenota Centro Selezionato");
        prenotaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenotaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(operatoriLoginButton)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ricercaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tipologiaComboBox, 0, 114, Short.MAX_VALUE)
                                    .addComponent(comuneTextField)
                                    .addComponent(nomeTextField))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listaComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(infoButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sintomiAvversiButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(segnalaEventoButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(prenotaButton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comuneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(infoButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tipologiaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sintomiAvversiButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ricercaButton)
                    .addComponent(segnalaEventoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prenotaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(operatoriLoginButton)
                .addContainerGap())
        );

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
                        .addContainerGap(344, Short.MAX_VALUE))))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                      

    private void operatoriLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        scelta = "operatore";

        Login r=new Login();
        r.setVisible(true);
    }                                                    

    /*
     * Il metodo va a ricercare i centri vaccinali consoni ai parametri di ricerca
     * aggiungendo i risultati nel combobox
     */
    private void ricercaButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
       String nome = nomeTextField.getText().toLowerCase();
        String comune = comuneTextField.getText().toLowerCase();
        String tipologia="";
        
        try {
        	tipologia = (String)tipologiaComboBox.getSelectedItem();
            tipologia = tipologia.toLowerCase();
        }catch(Exception e) {
        	
        }
        String request="";
        
        if(!nome.isBlank()) {
        	request = "cercaNome;"+nome;
        	out.println(request);
        	
        }
        else {
        	if(!tipologia.equals("")) {
        		if(!comune.isBlank()) {
                	if(!tipologia.isBlank() ) {
                		request = "cercaComune;"+comune+";"+tipologia;
                    	out.println(request);
                	}                	
                }else {
                	request = "cercaTipologia;"+tipologia;
                	out.println(request);
                }
        	}
        	else
        		JOptionPane.showMessageDialog(jPanel2, "Si prega di selezionare una tipologia");
        }
        
    	listaComboBox.removeAllItems();
        
        //riempi il combo solo se ha fatto la ricerca
        if(!request.equals("")) {
        	try {
                String response= in.readLine();
            	System.out.println(response);
        		String[] risposta =response.split(";");
        		
        		for(String r : risposta) {
        			listaComboBox.addItem(r);
        		}
            }catch(Exception e) {
            	
            }
        }
    }                                             

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	if(listaComboBox.getSelectedIndex()>-1) {
    		
    		selectedCV = (String)listaComboBox.getSelectedItem();
    	
    		InfoCentro r=new InfoCentro();
    		r.setVisible(true);
    	}
    	else {
    		JOptionPane.showMessageDialog(jPanel2, "Si prega di selezionare un Centro Vaccinale");
    	}
    }                                          

    private void sintomiAvversiButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        /*
        * Visualizza un dialog con all'interno il numero di eventi avversi e la media della severita
        */
    	if(listaComboBox.getSelectedIndex()>-1) {
    		selectedCV = (String)listaComboBox.getSelectedItem();
    		SintomiAvversi r=new SintomiAvversi();
    		r.setVisible(true);
    	}
    	else {
    		JOptionPane.showMessageDialog(jPanel2, "Si prega di selezionare un Centro Vaccinale");
    	}
    }                                                    

    private void segnalaEventoButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        String s=(String)listaComboBox.getSelectedItem();
    	    	
    	if(s!=null) {
            setSelectedCV(s);
            scelta = "sintomi";

            Login r = new Login();
            r.setVisible(true);
    	}else {
            JOptionPane.showMessageDialog(jPanel2, "Si prega di selezionare un Centro Vaccinale");
    	}
    }                                                   

     /*
     * Porta alla schermata di login per poi potersi prenotare per una nuova dose
     */
    private void prenotaButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        String s=(String)listaComboBox.getSelectedItem();
    	
    	if(s!=null) {
            setSelectedCV(s);        	
            scelta = "prenota";

            Login r=new Login();
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
	
    public static void main(String args[]) {
		
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
		return selectedCV;
	}
	public static void setSelectedCV(String selectedCV) {
		Cittadino.selectedCV = selectedCV;
	}

    private javax.swing.JTextField comuneTextField;
    private javax.swing.JButton infoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> listaComboBox;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JButton operatoriLoginButton;
    private javax.swing.JButton prenotaButton;
    private javax.swing.JButton ricercaButton;
    private javax.swing.JButton segnalaEventoButton;
    private javax.swing.JButton sintomiAvversiButton;
    private javax.swing.JComboBox<String> tipologiaComboBox;        
}
