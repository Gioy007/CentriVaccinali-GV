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

/**
 * 
 * @author Giacomelli Davide 781844
 *
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
	 * @param url per il database
	 * @param user username per
	 * @param psw
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
					Statement stmt2 = conn.createStatement();
					String queryRegistra = "INSERT INTO utenti (nome, cognome, cf, password, email)"
	            			+ "VALUES ('"+requestArray[1]+"', '"+requestArray[2]+"', '"+requestArray[3]+
	            			"', '"+requestArray[4]+"', '"+requestArray[5]+"');";
					
					stmt2.executeUpdate(queryRegistra);
					conn.close();
                	out.println("OK");
				break;
				
				case "cercaInfo":
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt = conn.createStatement();
                	ResultSet rs = stmt.executeQuery("SELECT * FROM centrivaccinali"
                			+ " where nome='"+requestArray[1]+"'");
                	conn.close();
					rs.next();
					String resultQ=requestArray[1]+";";
					
					resultQ+=rs.getString("indirizzo")+";";
					resultQ+=rs.getString("tipologia");
					out.println(resultQ);
					conn.close();
				break;
				
				case "inserisciSegnalazione":
					
				break;
				
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
				
				case "nuovoCentroVaccinale":
					
					conn = DriverManager.getConnection(url, user, psw); 
					Statement stmt4 = conn.createStatement(); 
					String indirizzo = requestArray[5] + " " + requestArray[6];
					String queryNewCV =  "INSERT INTO centrivaccinali (nome, comune, indirizzo, cap, provincia, tipologia)" //COMUNE INDIRIZZO CAP PROVINCIA SONO DA AGGIUNGERE NEL DB
											+ "VALUES ('"+requestArray[1]+"', '"+ requestArray[2]+"', '" + indirizzo + requestArray[3]+"', '"
													+requestArray[4]+"', '" +requestArray[7]+"');";
																							
					stmt4.executeUpdate(queryNewCV);
					conn.close();
                	out.println("OK");
				
				default:
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	} 
	
}