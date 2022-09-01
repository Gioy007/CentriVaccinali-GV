package com.dg.Cittadino;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InfoCentro extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoCentro frame = new InfoCentro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoCentro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Info Centro Vaccinale");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		JLabel nomeLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.NORTH, nomeLabel, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, nomeLabel, 6, SpringLayout.EAST, lblNewLabel_1);
		contentPane.add(nomeLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Comune:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 6, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Via:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 6, SpringLayout.SOUTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tipologia:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 6, SpringLayout.SOUTH, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_5);
		
		JLabel comuneLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.NORTH, comuneLabel, 6, SpringLayout.SOUTH, nomeLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, comuneLabel, 20, SpringLayout.EAST, lblNewLabel_3);
		contentPane.add(comuneLabel);
		
		JLabel viaLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.NORTH, viaLabel, 6, SpringLayout.SOUTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, viaLabel, 9, SpringLayout.EAST, lblNewLabel_4);
		contentPane.add(viaLabel);
		
		JLabel tipologiaLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.NORTH, tipologiaLabel, 0, SpringLayout.NORTH, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, tipologiaLabel, 6, SpringLayout.EAST, lblNewLabel_5);
		contentPane.add(tipologiaLabel);
		
		JButton okButton = new JButton("<--");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, okButton, 0, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, okButton, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(okButton);
		
		Cittadino.getOut().println("infoCV;"+Cittadino.getSelectedCV());
        
        
        try {
        	int i=0;
			String[] response= Cittadino.getIn().readLine().split(";");

			nomeLabel.setText(response[i]);
			comuneLabel.setText(response[++i]);
			viaLabel.setText(response[++i]);
			tipologiaLabel.setText(response[++i]);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
