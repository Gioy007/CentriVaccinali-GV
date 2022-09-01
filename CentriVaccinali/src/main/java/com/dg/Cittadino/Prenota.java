package com.dg.Cittadino;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class Prenota extends JFrame {

	private JPanel contentPane;

	/**
	 * Classe che si occupa di chiedere la data in cui si vuole prenotare il vaccino, in modo poi di registrare la prenotazione
	 * 
	 * @author Giacomelli Davide 741844
	 * @author Gioele Vicini 747818
	 */
	public Prenota() {
        initComponents();
    }

	private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton1.addActionListener(new ActionListener() {
        	/*
        	 * Metodo invocato quando ci si vuole prenotare effettivamente per un vaccino
        	 * l'unico dato ancora non raccolto era la data, che verrà scelta dai combobox
        	 */
        	public void actionPerformed(ActionEvent e) {
        		String giorno=jgiorno.getSelectedItem().toString();
        		String mese=jmese.getSelectedItem().toString();
        		String anno=janno.getSelectedItem().toString();
        		if(!giorno.equals("") && !mese.equals("") && !anno.equals("")) {
        			String selectedCV=Cittadino.getSelectedCV();
            		String idUtente=Cittadino.getIdutente();
            				
            		String outString="prenotaVacc;"+idUtente+";"
            					+giorno+"-"+mese+"-"+anno+";"+selectedCV;
            		Cittadino.getOut().println(outString);
            		
            		
            		try {
            			String s=Cittadino.getIn().readLine();
    					if(s.equals("OK")) {
    						JOptionPane.showMessageDialog(null, "Prenotazione avvenuta");
    						setVisible(false);
    						Cittadino c= new Cittadino();
    						c.setVisible(true);
    						
    					}
    					else {
    						if(s.equals("NO")) {
    							JOptionPane.showMessageDialog(null, "C'e gia una prenotazione a questo nome");
    						}
    						else JOptionPane.showMessageDialog(null, "Errore di prenotazione");
    					}
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
        		}
        		else JOptionPane.showMessageDialog(contentPane, "Si prega di selezionare una data");
        	}
        });
        jgiorno = new javax.swing.JComboBox<>();
        for(int i=1;i<32;i++) {
        	jgiorno.addItem(String.valueOf(i));
        }
        jgiorno.setSelectedIndex(-1);
        
        jLabel4 = new javax.swing.JLabel();
        jmese = new javax.swing.JComboBox<>();
        for(int i=1;i<13;i++) {
        	jmese.addItem(String.valueOf(i));
        }
        jmese.setSelectedIndex(-1);
        
        jLabel5 = new javax.swing.JLabel();
        janno = new javax.swing.JComboBox<>();
        for(int i=2022;i<2040;i++) {
        	janno.addItem(String.valueOf(i));
        }
        janno.setSelectedIndex(-1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setText("Prenota vaccino");

        jLabel2.setText("Giorno");

        jButton1.setText("Prenota");

        jLabel4.setText("Mese");

        jLabel5.setText("Anno");
        
        btnNewButton = new JButton("<--");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        	}
        });
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel1)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel2)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jgiorno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(jLabel4)
        							.addGap(18)
        							.addComponent(jmese, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jLabel5))
        						.addComponent(btnNewButton))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jButton1)
        						.addComponent(janno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel2)
        				.addComponent(jgiorno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel4)
        				.addComponent(jmese, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel5)
        				.addComponent(janno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1)
        				.addComponent(btnNewButton))
        			.addGap(25))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>                        

    

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jgiorno;
    private javax.swing.JComboBox<String> jmese;
    private javax.swing.JComboBox<String> janno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private JButton btnNewButton;
    // End of variables declaration                   
}
