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
		try {
			while(true) {
				System.out.println("connessione stabilita con un client");
				String request = in.readLine();
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
				
				case "nuovoVaccinato":
				
				break;
				
				case "registraCitt":
					Statement stmt2 = conn.createStatement();
					String queryRegistra = "INSERT INTO utenti (nome, cognome, cf, password, email)"
	            			+ "VALUES ('"+requestArray[1]+"', '"+requestArray[2]+"', '"+requestArray[3]+
	            			"', '"+requestArray[4]+"', '"+requestArray[5]+"');";
					
                	ResultSet rs2 = stmt2.executeQuery(queryRegistra);
                	out.println("OK");
				break;
				
				case "cercaInfo":
					Statement stmt = conn.createStatement();
                	ResultSet rs = stmt.executeQuery("SELECT * FROM centrivaccinali"
                			+ " where nome='"+requestArray[1]+"'");					
					rs.next();
					String resultQ=requestArray[1]+";";
					
					resultQ+=rs.getString("indirizzo")+";";
					resultQ+=rs.getString("tipologia");
					out.println(resultQ);
				break;
				
				case "inserisciSegnalazione":
					
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
				
				case "nuovoCentroVaccinale":
				
				default:
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