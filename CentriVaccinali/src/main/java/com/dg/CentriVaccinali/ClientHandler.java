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

public class ClientHandler implements Runnable{

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientHandler(Socket clientSocket) throws IOException {
		this.client=clientSocket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
		
	}
	public void run(Connection conn) {
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
					
					Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery(query);
					
					result.next();
					System.out.println(result.getString("admin"));
					System.out.println(result.getString("userid"));
					out.println(result.getString("admin")+";"+result.getString("userid"));	
				break;
				
				case "nuovoVaccinato": //bisogna aggiungere nella tab utenti l'id del centro al quale si è registrati 
										
					System.out.println("nuovo vaccinato");
					Statement stmt3 = conn.createStatement(); 
					String queryGetCVbyCF = "SELECT * FROM utenti WHERE cf = '"+requestArray[1]+"'";
					ResultSet result2 = stmt3.executeQuery(queryGetCVbyCF);
					result2.next();
					String queryNewVaccinato = "INSERT INTO vaccinati (userid, datasomm, tipovacc, idcentrovacc)"
											+ "VALUES ('"+result2.getString("userid")+"', '"+requestArray[2]+"', '"
													+requestArray[3]+"', '" + result2.getString("idcentrovacc")+"');";
					
					stmt3.executeUpdate(queryNewVaccinato);
                	out.println("OK");
				break;
				
				case "registraCitt": 
					Statement stmt2 = conn.createStatement();
					String queryRegistra = "INSERT INTO utenti (nome, cognome, cf, password, email)"
	            			+ "VALUES ('"+requestArray[1]+"', '"+requestArray[2]+"', '"+requestArray[3]+
	            			"', '"+requestArray[4]+"', '"+requestArray[5]+"');";
					
					stmt2.executeUpdate(queryRegistra);
                	out.println("OK");
				break;
				
				case "cercaInfo":
					
					System.out.println("cerco info");
					Statement stmt = conn.createStatement();
					System.out.println("statement fatto");
                	ResultSet rs = stmt.executeQuery("SELECT * FROM centrivaccinali"
                			+ " where nome='"+requestArray[1]+"'");
                	System.out.println("query fatta");
					rs.next();
					String resultQ=requestArray[1]+";";
					
					resultQ+=rs.getString("indirizzo")+";";
					resultQ+=rs.getString("tipologia");
					out.println(resultQ);
				break;
				
				case "inserisciSintomo":
					
				break;
				
				case "aggiungiSintomo":
					Statement stmt4 = conn.createStatement();
					String queryNuovo = "INSERT INTO eventi (nome) VALUES ('"+requestArray[1]+"');";
					
					stmt4.executeUpdate(queryNuovo);
                	out.println("OK");
				break;
				
				case "centriDisp":
		            Statement stmt1 = conn.createStatement();		            
		            ResultSet rs1 = stmt1.executeQuery("SELECT nome FROM centrivaccinali");
		            String centri="";
		            rs1.next();
		            
		            do {
		            	centri+=rs1.getString("nome")+";";
		            }
		            while(rs1.next());
		            out.println(centri);
				break;
				
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
					Statement stmt5 = conn.createStatement();
					ResultSet rs5 = stmt5.executeQuery("SELECT nome FROM eventi");
					String eventi="";
					rs5.next();
					
					do {
						eventi+=rs5.getString("nome")+";";
					}while(rs5.next());
					
					eventi.substring(0, eventi.length() - 1);  
					out.println(eventi);
					
				break;
				
				case "nuovoCentroVaccinale":
					
					Statement stmtNCV = conn.createStatement(); 
					String indirizzo = requestArray[5] + " " + requestArray[6];
					String queryNewCV =  "INSERT INTO centrivaccinali (nome, comune, indirizzo, cap, provincia, tipologia)" //COMUNE INDIRIZZO CAP PROVINCIA SONO DA AGGIUNGERE NEL DB
											+ "VALUES ('"+requestArray[1]+"', '"+ requestArray[2]+"', '" + indirizzo + requestArray[3]+"', '"
													+requestArray[4]+"', '" +requestArray[7]+"');";
																							
					stmtNCV.executeUpdate(queryNewCV);
                	out.println("OK");
					
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		//mi aspetto le credenziali per il login
		
		//controllo
		
		//mando la response con codice per dire quale finestra aprire(cittadini/operatori)
		
		//attendo nuove disposizioni
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	} 
	
}