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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private static List<Vaccino> vacciniFatti=new ArrayList<Vaccino>();
	
	public static void main(String args[]) {
		Sintomi s=new Sintomi();
		s.setVisible(true);
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
			Cittadino.getOut().println("nomeEventi;");
			String[] risposta = Cittadino.getIn().readLine().split(";");

			for (int i = 0; i < risposta.length; i++) {
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
				NuovoSintomo ns = new NuovoSintomo();
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

		for (int i = 1; i < 6; i++) {
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

		final JComboBox jvfatti = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, jvfatti, -4, SpringLayout.NORTH, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.WEST, jvfatti, 0, SpringLayout.WEST, jsintomo);
		sl_contentPane.putConstraint(SpringLayout.EAST, jvfatti, -118, SpringLayout.EAST, contentPane);
		/*
		 * Selezionare il tipo di vaccino fatto
		 */
		try {
			Cittadino.getOut().println("vaccini;" + Cittadino.getIdutente());
			String s=Cittadino.getIn().readLine();
			if (!s.equals("NO")) {
				String[] risposta = s.split(";");
				int ii;
				for (int i = 0; i < risposta.length; i=i+3) {
					ii=i+1;
					String ss=risposta[1+i] + " (" + risposta[2+i] + ")";
					jvfatti.addItem(ss);
					
					String userid=risposta[i];
					String codice=risposta[1+i];
					int dose=Integer.parseInt(risposta[2+i]);					
					
					Vaccino v=new Vaccino(userid, codice, dose);
					vacciniFatti.add(v);
				} 
			}
				
			jvfatti.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		contentPane.add(jvfatti);

		JButton btnNewButton_1 = new JButton("Aggiungi");
		btnNewButton_1.addActionListener(new ActionListener() {
			/*
			 * Aggiunge il nuovo evento avverso nel db
			 */
			public void actionPerformed(ActionEvent e) {
				int sintomo = jsintomo.getSelectedIndex();
				
				String severita = jseverita.getSelectedItem().toString();
				String note = jnote.getText();
				
				if(sintomo!=-1 && !severita.equals("") && !note.equals("")) {
					sintomo++;
					Vaccino vaccino = vacciniFatti.get(jvfatti.getSelectedIndex());
					String idvacc=vaccino.codice;
					
					try {
						Cittadino.getOut()
								.println("aggiungiSegnalazione;" + idvacc + ";" + sintomo + ";" + severita + ";" + note);

						if (Cittadino.getIn().readLine().equals("OK")) {
							JOptionPane.showMessageDialog(null, "Segnalazione registrata");
							
							setVisible(false);
							Cittadino c= new Cittadino();
							c.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Errore di segnalazione");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				else JOptionPane.showMessageDialog(contentPane, "Si prega di inserire tutti i dati");

			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_1);

	}
}
