package com.dg.Cittadino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

/**
 * Classe che si occupa di mandare al server le informazioni per un nuovo centro vaccinale e un nuovo vaccinato
 * 
 * @author Giacomelli Davide 781844
 */
public class OperatoreVaccinale extends javax.swing.JFrame {
	
	/**
	 * Costruttore della classe che chiama il metodo per disegnare la GUI
	 */
    public OperatoreVaccinale() {
        initComponents();
    } 

    /**
     * Metodo per la creazione dell'interfaccia grafica
     */
    private void initComponents() {
        jPanel = new javax.swing.JPanel();
        jLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nomeCVLabel = new javax.swing.JLabel();
        comuneLabel = new javax.swing.JLabel();
        provinciaLabel = new javax.swing.JLabel();
        CAPLabel = new javax.swing.JLabel();
        indirizzoLabel = new javax.swing.JLabel();
        nCivicoLabel = new javax.swing.JLabel();
        tipologiaLabel = new javax.swing.JLabel();
        registraCVButton = new javax.swing.JButton();
        nomeTextField = new javax.swing.JTextField();
        comuneTextField = new javax.swing.JTextField();
        CAPTextField = new javax.swing.JTextField();
        provinciaTextField = new javax.swing.JTextField();
        indirizzoTextField = new javax.swing.JTextField();
        nCivicoTextField = new javax.swing.JTextField();
        tipologiaComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        cfLabel = new javax.swing.JLabel();
        cfTextField = new javax.swing.JTextField();
        dataTextField = new javax.swing.JTextField();
        dataLabel = new javax.swing.JLabel();
        tipoVaccinoLabel = new javax.swing.JLabel();
        tipoVaccinoComboBox = new javax.swing.JComboBox<>();
        registaVaccinatoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel.setText("Operatore Vaccinale");

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        nomeCVLabel.setText("Nome");

        comuneLabel.setText("Comune");

        provinciaLabel.setText("Provincia(Sigla)");

        CAPLabel.setText("CAP");

        indirizzoLabel.setText("Indirizzo");

        nCivicoLabel.setText("N° civico");

        tipologiaLabel.setText("Tipologia");

        registraCVButton.setText("Registra Centro Vaccinale");
		
        registraCVButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registraCVButtonActionPerformed(evt);
            }
        });

        tipologiaComboBox.setModel(new DefaultComboBoxModel(new String[] {"azienda", "hub", "ospedale"}));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registraCVButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CAPLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(88, 88, 88))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(indirizzoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nCivicoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comuneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nomeCVLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(22, 22, 22))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tipologiaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(61, 61, 61))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(provinciaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tipologiaComboBox, 0, 207, Short.MAX_VALUE)
                            .addComponent(nomeTextField)
                            .addComponent(comuneTextField)
                            .addComponent(nCivicoTextField)
                            .addComponent(CAPTextField)
                            .addComponent(provinciaTextField)
                            .addComponent(indirizzoTextField))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeCVLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comuneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(CAPLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(provinciaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comuneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CAPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(provinciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(indirizzoTextField)
                    .addComponent(indirizzoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nCivicoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nCivicoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipologiaComboBox)
                    .addComponent(tipologiaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addComponent(registraCVButton)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        cfLabel.setText("Codice Fiscale Vaccinato");

        dataLabel.setText("Data vaccino (gg/mm/aaaa)");

        tipoVaccinoLabel.setText("Tipologia");

        tipoVaccinoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AstraZeneca", "Moderna", "J&J", "Pfizer" }));

        registaVaccinatoButton.setText("Registra Vaccinato");
        registaVaccinatoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registaVaccinatoButtonActionPerformed(evt);
            }
        });
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("ID Vaccino");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(registaVaccinatoButton, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(cfLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        						.addComponent(dataLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(dataTextField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        						.addComponent(cfTextField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(tipoVaccinoLabel, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        						.addComponent(lblNewLabel))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(textField)
        						.addComponent(tipoVaccinoComboBox, 0, 129, Short.MAX_VALUE))))
        			.addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addComponent(cfLabel, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
        					.addGap(5)
        					.addComponent(dataLabel, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addComponent(cfTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(3)
        					.addComponent(dataTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(tipoVaccinoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(tipoVaccinoComboBox, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel))
        			.addGap(103)
        			.addComponent(registaVaccinatoButton)
        			.addContainerGap())
        );
        jPanel2.setLayout(jPanel2Layout);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(334, 334, 334))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
     * metodo per mandare al server le informazioni utili per registrare un nuovo vaccinato
     * 
     * @param evt click sul relativo pulsante
     */
    private void registaVaccinatoButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	String cf = cfTextField.getText().toLowerCase();	//1
    	String data = dataTextField.getText().toLowerCase();//2
    	String tipologia = (String)tipoVaccinoComboBox.getSelectedItem();//3
    	String ID = textField.getText();//4
    	tipologia = tipologia.toLowerCase();
    	
    	String request = "nuovoVaccinato;"+cf+";"+data+";"+tipologia+";"+ID+Cittadino.getSelectedCV();
    	Cittadino.getOut().println(request);
    	
    	
        try {
			if(Cittadino.getIn().readLine().equals("OK")) {
				JOptionPane.showMessageDialog(jPanel, "Nuovo vaccinato registrato correttamente");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }                                                      

    /**
     * metodo per mandare al server le informazioni utili per registrare un nuovo centro vaccinale
     * 
     * @param evt evt click sul relativo pulsante
     */
    private void registraCVButtonActionPerformed(java.awt.event.ActionEvent evt) { 
    	String nome = nomeTextField.getText().toLowerCase();	//1
    	String comune = comuneTextField.getText().toLowerCase();//2
    	String CAP = CAPTextField.getText();	//3
    	String provincia = provinciaTextField.getText().toLowerCase();//4
    	String indirizzo = indirizzoTextField.getText().toLowerCase();//5
    	String nCivico = nCivicoTextField.getText();//6
    	String tipologia = ""+tipologiaComboBox.getSelectedItem();//7
    	tipologia = tipologia.toLowerCase();
    	
    	String request = "nuovoCentroVaccinale;"+nome+";"+comune+";"+CAP+";"+provincia
    			+";"+indirizzo+";"+nCivico+";"+tipologia;
    	Cittadino.getOut().println(request);
    	
        try {
			if(Cittadino.getIn().readLine().equals("OK")) {
				JOptionPane.showMessageDialog(jPanel, "Centro registrato correttamente");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }           
              
    private javax.swing.JLabel CAPLabel;
    private javax.swing.JTextField CAPTextField;
    private javax.swing.JLabel cfLabel;
    private javax.swing.JTextField cfTextField;
    private javax.swing.JLabel comuneLabel;
    private javax.swing.JTextField comuneTextField;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JTextField dataTextField;
    private javax.swing.JLabel indirizzoLabel;
    private javax.swing.JTextField indirizzoTextField;
    private javax.swing.JLabel jLabel;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nCivicoLabel;
    private javax.swing.JTextField nCivicoTextField;
    private javax.swing.JLabel nomeCVLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JLabel provinciaLabel;
    private javax.swing.JTextField provinciaTextField;
    private javax.swing.JButton registaVaccinatoButton;
    private javax.swing.JButton registraCVButton;
    private javax.swing.JComboBox<String> tipoVaccinoComboBox;
    private javax.swing.JLabel tipoVaccinoLabel;
    private javax.swing.JComboBox<String> tipologiaComboBox;
    private javax.swing.JLabel tipologiaLabel;         
    private JTextField textField;
}
