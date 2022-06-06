package com.dg.CentriVaccinali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe per la gestione dei singli client
 * @author Giacomelli Davide 781844
 */
public class ClientHandler implements Runnable{

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private String url = "";
	private String user= "";
	private String psw = "";
	private Connection conn;
	
	/**
	 * 
	 * @param clientSocket
	 * @param url url del database
	 * @param user username per connettersi al DataBase
	 * @param psw password relativa all'utente
	 * @throws IOException
	 */
	public ClientHandler(Socket clientSocket, String url, String user, String psw) throws IOException {
		this.client = clientSocket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
		
		this.url = url;
		this.user = user;
		this.psw = psw;
		
	}
	
	/**
	 * thread che gestisce le singole connessioni al server
	 */
	@Override
	public void run() {
		System.out.println("connessione stabilita con un client");
		try {
			while(true) {
				
				String request = in.readLine();
				System.out.println("ho ricevuto: " + request);
				String[] requestArray;
				try {
					requestArray = request.split(";");
				}catch(Exception e) {
					requestArray=new String[1];
					requestArray[0]=request;
				}
				switch(requestArray[0]) {
				case "login":
					String query = "SELECT *"
							+ "FROM utenti "
							+ "WHERE email = '" + requestArray[1] + "' AND password = '" + requestArray[2]+"'"; 
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery(query);
					conn.close();
					
					result.next();
					System.out.println(result.getString("admin"));
					System.out.println(result.getString("userid"));
					out.println(result.getString("admin")+";"+result.getString("userid"));
					
					
				break;
				
				case "cercaNome":
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt = conn.createStatement();
                	ResultSet rs = stmt.executeQuery("SELECT * FROM centrivaccinali"
                			+ " where nome='"+requestArray[1]+"' AND tipologia = '" + requestArray[2]+"'");
                	conn.close();
                	
                	String centri="";
                	while(rs.next()) {
                		centri+=rs.getString("nome")+";";
                	}
                	
                	out.println(centri);
					
				break;
				
				
				case "cercaComune":
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt1 = conn.createStatement();
                	ResultSet rs1 = stmt1.executeQuery("SELECT * FROM centrivaccinali"
                			+ " where comune='"+requestArray[1]+"' AND tipologia = '" + requestArray[2]+"'");
                	conn.close();
                	
                	String centri1="";
                	while(rs1.next()) {
                		centri1+=rs1.getString("comune")+";";
                	}
                	
                	out.println(centri1);
                	
					
				break;
					
				
				case "cercaTipologia":
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt2 = conn.createStatement();
                	ResultSet rs2 = stmt2.executeQuery("SELECT * FROM centrivaccinali"
                			+ " where tipologia='"+requestArray[1]+"'");
                	conn.close();
                	
                	String centri2="";
                	while(rs2.next()) {
                		centri2+=rs2.getString("tipologia")+";";
                	}
                	
                	out.println(centri2);
                	
					
				break;
				
				case "nuovoVaccinato": //bisogna aggiungere nella tab utenti l'id del centro al quale si è registrati 
										
					System.out.println("nuovo vaccinato");
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt3 = conn.createStatement(); 
					String queryGetCVbyCF = "SELECT * FROM utenti WHERE cf = '"+requestArray[1]+"'";
					ResultSet result2 = stmt3.executeQuery(queryGetCVbyCF);
					result2.next();
					String queryNewVaccinato = "INSERT INTO vaccinati (userid, datasomm, tipovacc, idcentrovacc)"
											+ "VALUES ('"+result2.getString("userid")+"', '"+requestArray[2]+"', '"
													+requestArray[3]+"', '" + result2.getString("idcentrovacc")+"');";
					
					stmt3.executeUpdate(queryNewVaccinato);
					conn.close();
					
                	out.println("OK");
				break;
				
				case "registraCitt": 
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt4 = conn.createStatement();
					String queryRegistra = "INSERT INTO utenti (nome, cognome, cf, password, email)"
	            			+ "VALUES ('"+requestArray[1]+"', '"+requestArray[2]+"', '"+requestArray[3]+
	            			"', '"+requestArray[4]+"', '"+requestArray[5]+"');";
					
					stmt4.executeUpdate(queryRegistra);
					conn.close();
                	out.println("OK");
				break;
				/*
				case "cercaInfo":
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt4 = conn.createStatement();
                	ResultSet rs4 = stmt.executeQuery("SELECT * FROM centrivaccinali"
                			+ " where nome='"+requestArray[1]+"'");
                	conn.close();
					rs4.next();
					String resultQ=requestArray[1]+";";
					
					resultQ+=rs.getString("indirizzo")+";";
					resultQ+=rs.getString("tipologia");
					out.println(resultQ);
					conn.close();
				break;
				*/
				case "inserisciSintomo":
					
				break;
				
				case "aggiungiSintomo":
					Statement stmt5 = conn.createStatement();
					String queryNuovo = "INSERT INTO eventi (nome) VALUES ('"+requestArray[1]+"');";
					
					stmt5.executeUpdate(queryNuovo);
                	out.println("OK");
				break;
				/*
				case "centriDisp":
					
					conn = DriverManager.getConnection(url, user, psw); 
		            Statement stmt1 = conn.createStatement();		            
		            ResultSet rs1 = stmt1.executeQuery("SELECT nome FROM centrivaccinali");
		            conn.close();
		            String centri="";
		            rs1.next();
		            
		            do {
		            	centri+=rs1.getString("nome")+";";
		            }
		            while(rs1.next());
		            out.println(centri);
				break;
				*/
				case "vaccini":
					Statement stmt6 = conn.createStatement();
					String queryVacc="select idvacc, tipovacc from vaccinati v left join utenti on v.userid=utenti.userid "
		            		+ "where v.userid='"+requestArray[1]+"' order by datasomm";
					ResultSet rs6 = stmt6.executeQuery(queryVacc);
					rs6.next();
					String Hvaccini="";
					
					do {
						Hvaccini+=rs6.getString("tipovacc")+";"+rs6.getString("idvacc")+";";
					}while(rs6.next());
					
					Hvaccini.substring(0, Hvaccini.length() - 1);  
					out.println(Hvaccini);
				break;
				
				case "aggiungiSegnalazione":
					Statement stmt7 = conn.createStatement();
					String querySegn = "INSERT INTO eventiavversi(idvacc, idevento, severita, note)"+
		            		"VALUES ('"+requestArray[1]+"', '"+requestArray[2]+"', '"
							+requestArray[3]+"','"+requestArray[4]+"')";
					
					stmt7.executeUpdate(querySegn);
					out.println("OK");					
				break;
				
				case "nomeEventi":
					Statement stmt9 = conn.createStatement();
					ResultSet rs9 = stmt9.executeQuery("SELECT nome FROM eventi");
					String eventi="";
					rs9.next();
					
					do {
						eventi+=rs9.getString("nome")+";";
					}while(rs9.next());
					
					eventi.substring(0, eventi.length() - 1);  
					out.println(eventi);
					
				break;
				
				case "nuovoCentroVaccinale":
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt8 = conn.createStatement(); 
					String indirizzo = requestArray[5] + " " + requestArray[6];
					String queryNewCV =  "INSERT INTO centrivaccinali (nome, comune, indirizzo, cap, provincia, tipologia)" //COMUNE INDIRIZZO CAP PROVINCIA SONO DA AGGIUNGERE NEL DB
											+ "VALUES ('"+requestArray[1]+"', '"+ requestArray[2]+"', '" + indirizzo + requestArray[3]+"', '"
													+requestArray[4]+"', '" +requestArray[7]+"');";
																							
					stmt8.executeUpdate(queryNewCV);
					conn.close();
                	out.println("OK");
					
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	} 
	
}