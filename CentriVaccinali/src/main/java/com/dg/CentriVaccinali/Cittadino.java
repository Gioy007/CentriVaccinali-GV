package com.dg.CentriVaccinali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Cittadino extends javax.swing.JFrame {
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	private static Socket socket;
	private static PrintStream out;
	private static BufferedReader in;
	private static String idutente;
	private static String selectedCV;
	
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
        jScrollPane1 = new javax.swing.JScrollPane();
        centriList = new javax.swing.JList<>();
        ricercaButton = new javax.swing.JButton();
        prenotaButton = new javax.swing.JButton();
        eventoAvversoButton = new javax.swing.JButton();
        operatoriLoginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Home cittadino");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setText("Cerca Centro Vaccinale");

        jLabel3.setText("Nome:");

        jLabel4.setText("Comune:");

        jLabel5.setText("Tipologia:");

        tipologiaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aziendale", "Hub", "Ospedaliero"}));

        jScrollPane1.setViewportView(centriList);

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
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenotaButtonActionPerformed(evt);
            }
        });

        eventoAvversoButton.setText("Segnala evento avverso nel Centro");
        eventoAvversoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventoAvversoButtonActionPerformed(evt);
            }
        });

        operatoriLoginButton.setText("Login per Operatori");
        operatoriLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operatoriLoginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tipologiaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(ricercaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comuneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eventoAvversoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(prenotaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(operatoriLoginButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comuneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tipologiaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ricercaButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prenotaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventoAvversoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
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

    private void ricercaButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                              
        String nome = nomeTextField.getText();
        String comune = comuneTextField.getText();
        String tipologia = (String)tipologiaComboBox.getSelectedItem();
        String request="";
        if(!nome.isBlank()) {
        	request = "cercaNome;"+nome+";"+tipologia;
        	out.println(request);
        	
        	String response= in.readLine();
			String[] risposta =response.split(";");
			
			for(String r : risposta) {
				centriList.add(jScrollPane1, r);
			}
			
        }
        else if(!comune.isBlank()) {
        	request = "cercaComune;"+comune+";"+tipologia;
        	out.println(request);
        	
        	String response= in.readLine();
			String[] risposta =response.split(";");
			
			for(String r : risposta) {
				centriList.add(jScrollPane1, r);
			}
			
        }else {
        	request = "cercaTipologia;"+tipologia;
        	out.println(request);
        	
        	String response= in.readLine();
			String[] risposta =response.split(";");
			
			for(String r : risposta) {
				centriList.add(jScrollPane1, r);
			}
			
        }
    }                                             

    private void operatoriLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
    	setVisible(false);
		Login r=new Login();
		r.setVisible(true);
    }                                                    

    private void prenotaButtonActionPerformed(java.awt.event.ActionEvent evt) {    
    	selectedCV = centriList.getSelectedValue();
    	if(selectedCV.isEmpty()) {
    		setVisible(false);
    		Login r=new Login();
    		r.setVisible(true);
    	}else {
    		JOptionPane.showMessageDialog(jPanel2, "Si prega di selezionare un Centro Vaccinale");
    	}
    	
    }                                             

    private void eventoAvversoButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }    
    
    public static Socket getSocket() {
		return socket;
	}
	
	public static PrintStream getOut() {
		return out;
	}
	
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
				Login r=new Login();
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
				Registrati r=new Registrati();
				r.setVisible(true);
				
			}
		});
		sl_login.putConstraint(SpringLayout.SOUTH, btnNewButton_2, 0, SpringLayout.SOUTH, btnNewButton_1);
		sl_login.putConstraint(SpringLayout.EAST, btnNewButton_2, -6, SpringLayout.WEST, btnNewButton_1);
		cittadino.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Sintomi");
		sl_login.putConstraint(SpringLayout.NORTH, btnNewButton_3, 0, SpringLayout.NORTH, btnNewButton_1);
		sl_login.putConstraint(SpringLayout.WEST, btnNewButton_3, 0, SpringLayout.WEST, lblNewLabel);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cittadino.add(btnNewButton_3);

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
}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cittadino().setVisible(true);
            }
        });
    }
                  
    private javax.swing.JList<String> centriList;
    private javax.swing.JTextField comuneTextField;
    private javax.swing.JButton eventoAvversoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JButton operatoriLoginButton;
    private javax.swing.JButton prenotaButton;
    private javax.swing.JButton ricercaButton;
    private javax.swing.JComboBox<String> tipologiaComboBox;          
}
