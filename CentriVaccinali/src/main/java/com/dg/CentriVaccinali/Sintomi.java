package com.dg.CentriVaccinali;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;

/*
 * Classe per registrare un nuovo evento avverso di un determinato centro vaccinale
 * 
 * @author Giacomelli Davide 741844
 * @author Gioele Vicini 747818
 */
public class Sintomi extends JFrame {

	private JPanel contentPane;
	private JTextField jnote;
	private static Map<String, String> Hvaccini;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cittadino.setIdutente("5");
					Sintomi frame = new Sintomi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creazione del frame
	 */
	public Sintomi() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 396, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Sintomi");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sintomo");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		final JComboBox jsintomo = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, jsintomo, 77, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, jsintomo, 6, SpringLayout.EAST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 4, SpringLayout.NORTH, jsintomo);
		contentPane.add(jsintomo);
	    
		/*
		 * Inserisce gli eventi avversi nel combobox per poi selezionare quello avuto
		 */
        try {
        	Cittadino.getOut().println("SELECT nome FROM eventi");
        	String[] risposta =Cittadino.getIn().readLine().split(";");
        	
            for(int i=0;i<risposta.length;i++) {
            	jsintomo.addItem(risposta[i]);
            }

            jsintomo.setSelectedIndex(-1);
                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }		
		
		JButton btnNewButton = new JButton("Nuovo sintomo");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 209, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, jsintomo, -6, SpringLayout.WEST, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, -4, SpringLayout.NORTH, lblNewLabel_1);
		btnNewButton.addActionListener(new ActionListener() {
			/*
			 * Aggiunta al db di un nuovo sintomo non ancora registrato
			 */
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NuovoSintomo ns= new NuovoSintomo();
				ns.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Severita");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		final JComboBox jseverita = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 4, SpringLayout.NORTH, jseverita);
		sl_contentPane.putConstraint(SpringLayout.NORTH, jseverita, 6, SpringLayout.SOUTH, jsintomo);
		sl_contentPane.putConstraint(SpringLayout.WEST, jseverita, 0, SpringLayout.WEST, jsintomo);
		sl_contentPane.putConstraint(SpringLayout.EAST, jseverita, -264, SpringLayout.EAST, contentPane);
		
		for(int i=1;i<6;i++) {
			jseverita.addItem(i);
		}
		jseverita.setSelectedIndex(-1);
		contentPane.add(jseverita);
		
		JLabel lblNewLabel_3 = new JLabel("Note");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 15, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_3);
		
		jnote = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, jnote, 6, SpringLayout.SOUTH, jseverita);
		sl_contentPane.putConstraint(SpringLayout.WEST, jnote, 0, SpringLayout.WEST, jsintomo);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, jnote, -56, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, jnote, 0, SpringLayout.EAST, btnNewButton);
		contentPane.add(jnote);
		jnote.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Vaccino");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_4);
		
		final JComboBox jvaccino = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, jvaccino, -4, SpringLayout.NORTH, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.WEST, jvaccino, 0, SpringLayout.WEST, jsintomo);
		sl_contentPane.putConstraint(SpringLayout.EAST, jvaccino, -118, SpringLayout.EAST, contentPane);
		/*
		 * Selezionare il tipo di vaccino fatto
		 */
		try {            
            Cittadino.getOut().println("vaccini;"+Cittadino.getIdutente());
            String[] risposta=Cittadino.getIn().readLine().split(";");
            Hvaccini=new HashMap<String, String>();
            
            for(int i=0;i<risposta.length;i=i+2) {
            	jsintomo.addItem(risposta[i]);
            	Hvaccini.put(risposta[i], risposta[++i]);
            }

            jsintomo.setSelectedIndex(-1);           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
		
		contentPane.add(jvaccino);
		
		JButton btnNewButton_1 = new JButton("Aggiungi");		
		btnNewButton_1.addActionListener(new ActionListener() {
			/*
			 * Aggiunge il nuovo evento avverso nel db
			 */
			public void actionPerformed(ActionEvent e) {
				int sintomo= jsintomo.getSelectedIndex();
				sintomo++;
				String severita=jseverita.getSelectedItem().toString();
				String note=jnote.getText();
				String idvacc=Hvaccini.get(jvaccino.getSelectedItem().toString());			
				 
				try {
					Cittadino.getOut().println("aggiungiSegnalazione"+idvacc+";"+sintomo+";"+severita+";"+note);
			           	

		            if(Cittadino.getIn().readLine().equals("OK")) {
		            	JOptionPane.showMessageDialog(null, "Segnalazione registrata");
		            }
		            else {
		            	JOptionPane.showMessageDialog(null, "Errore di segnalazione");
		            }   
				 } catch (Exception e1) {
			            JOptionPane.showMessageDialog(null, e1);
			        }

			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_1);
		
	}
}
