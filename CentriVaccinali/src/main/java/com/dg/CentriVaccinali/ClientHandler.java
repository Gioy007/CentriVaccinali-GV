package com.dg.CentriVaccinali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/*
 * Classe per gestire il singolo client e soddisfare la richiesta fatta
 * 
 * @author Giacomelli Davide 741844
 * @author Gioele Vicini 747818
 */
public class ClientHandler implements Runnable {

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private String url = "";
	private String user = "";
	private String psw = "";
	private Connection conn;

	/**
	 * 
	 * @param clientSocket
	 * @param url          url del database
	 * @param user         username per connettersi al DataBase
	 * @param psw          password relativa all'utente
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
			while (true) {

				String request = in.readLine();
				System.out.println("ho ricevuto: " + request);
				String[] requestArray;
				try {
					requestArray = request.split(";");
				} catch (Exception e) {
					requestArray = new String[1];
					requestArray[0] = request;
				}
				conn = DriverManager.getConnection(url, user, psw);
				/*
				 * Switch per soddisfare ogni richiesta possibile fatta al server
				 */
				switch (requestArray[0]) {

				/*
				 * Caso per soddisfare la richiesta di login
				 */
				case "login":
					String query = "SELECT *" + "FROM utenti " + "WHERE email = '" + requestArray[1]
							+ "' AND password = '" + requestArray[2] + "'";

					Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery(query);

					result.next();
					out.println(result.getString("admin") + ";" + result.getString("userid"));

					break;

				/*
				 * Caso per soddisfare la richiesta di cercare un centro vaccinale tramite il
				 * nome o un pezzo di nome
				 */
				case "cercaNome":

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT * FROM centrivaccinali" + " where nome like '%" + requestArray[1] + "%'");

					String centri = "";
					while (rs.next()) {
						centri += rs.getString("nome") + ";";
					}

					out.println(centri);

					break;

				/*
				 * Caso per soddisfare la richiesta di calcolare il numero di eventi avversi e
				 * la loro media di severita
				 */
				case "numeroMedia":
					Statement stmt13 = conn.createStatement();
					ResultSet rs13 = stmt13
							.executeQuery("select idcentro from centrivaccinali where nome='" + requestArray[1] + "'");
					rs13.next();
					String sr = "select count(*), avg(severita) "
							+ "from (eventiavversi e left join vaccinati v on e.idvacc=v.idvacc) left join centrivaccinali cv on v.idcentrovacc=cv.idcentro "
							+ "where idcentrovacc=" + rs13.getString("idcentro") + " group by idcentrovacc";
					ResultSet rs14 = stmt13.executeQuery(sr);

					try {
						rs14.next();
						String due = String.valueOf(rs14.getFloat("avg"));
						String uno = String.valueOf(rs14.getString("count"));
						// String due=String.valueOf(rs14.getFloat("avg"));
						out.println(uno + ";" + due);
					} catch (Exception e) {
						out.println("0;0");
					}
					break;

				/*
				 * Caso per soddisfare la richiesta di cercare un centro vaccinale tramite il
				 * comune e tipologia
				 */
				case "cercaComune":
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery("SELECT * FROM centrivaccinali" + " where comune='"
							+ requestArray[1] + "' AND tipologia = '" + requestArray[2] + "'");

					String centri1 = "";
					while (rs1.next()) {
						centri1 += rs1.getString("nome") + ";";
					}

					out.println(centri1);

					break;

				/*
				 * Caso per soddisfare la richiesta di cercare un centro vaccinale tramite la
				 * tipologia
				 */
				case "cercaTipologia":
					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(
							"SELECT * FROM centrivaccinali" + " where tipologia='" + requestArray[1] + "'");

					System.out.println(rs2.toString());

					String centri2 = "";
					while (rs2.next()) {
						centri2 += rs2.getString("nome") + ";";
					}

					out.println(centri2);

					break;

				/*
				 * Caso per soddisfare la richiesta di inserire un nuovo vaccinato nel db
				 */
				case "nuovoVaccinato":
					Statement stmt3 = conn.createStatement();
					String queryGetIDfromCF = "SELECT * FROM utenti WHERE cf = '" + requestArray[1] + "'";
					ResultSet result2 = stmt3.executeQuery(queryGetIDfromCF);
					result2.next();
					String useridR= result2.getString("userid");
					int dose=0;
					
					String queryDose="select max(dose) from vaccinati where userid='"+useridR+"'";
					result2 = stmt3.executeQuery(queryDose);
					
					try {
						result2.next();
						result2.getString("max");
					}catch(Exception e) {			
					}
					dose++;
					
					String queryNewVaccinato = "INSERT INTO vaccinati (idvacc, userid, datasomm, tipovacc, idcentrovacc, dose)"
							+ "VALUES ('" + requestArray[4] + "', '" + useridR + "', '"
							+ requestArray[2] + "', '" + requestArray[3] + "', '" + requestArray[5] + "', "+dose+");";

					stmt3.executeUpdate(queryNewVaccinato);

					String queryDelete="DELETE FROM prenotati WHERE userid='"+useridR+"';";
					stmt3.executeUpdate(queryDelete);
					
					out.println("OK");
					break;

				/*
				 * Caso per soddisfare la richiesta di registrare un nuovo cittadino al portale
				 */
				case "registraCitt":
					
					Statement stmt4 = conn.createStatement();
					String queryChkEsistenza = "SELECT * FROM utenti WHERE email = '"+ requestArray[5] +"' OR cf = '"+requestArray[3]+"'";
					ResultSet result0 = stmt4.executeQuery(queryChkEsistenza);
					if(result0.next()) {
						out.println("ERRORE");
					}else {
						String queryRegistra = "INSERT INTO utenti (nome, cognome, cf, password, email)" + "VALUES ('"
								+ requestArray[1] + "', '" + requestArray[2] + "', '" + requestArray[3] + "', '"
								+ requestArray[4] + "', '" + requestArray[5] + "');";

						stmt4.executeUpdate(queryRegistra);
						out.println("OK");
					}
					

					
					break;

				/*
				 * Caso per soddisfare la richiesta di inserire un nuovo caso di evento avverso
				 */
				case "inserisciSintomo":
					Statement stmt10 = conn.createStatement();
					String querySintomo = "select idevento from eventi where nome='" + requestArray[2] + "'";
					ResultSet idevento = stmt10.executeQuery(querySintomo);

					String queryInserisciS = "INSERT INTO eventiavversi (idvacc, idevento, severita, note) VALUES ('"
							+ requestArray[1] + "','" + idevento + "','" + requestArray[3] + "','" + requestArray[4]
							+ "');";

					stmt10.executeUpdate(queryInserisciS);
					out.println("OK");
					break;

				/*
				 * Caso per soddisfare la richiesta di prenotare un vaccino presso un centro
				 * vaccinale
				 */
				case "prenotaVacc":
					Statement stmt9 = conn.createStatement();
					String queryIDC="select idcentro from centrivaccinali where nome ='"+requestArray[3]+"'";
					ResultSet rsIDC = stmt9.executeQuery(queryIDC);

					rsIDC.next();
					
					String queryPrenota = "INSERT INTO prenotati (userid, dataprenotazione, idcentro) VALUES ('" + requestArray[1]
							+ "','" + requestArray[2] + "','" + rsIDC.getString("idcentro") + "');";

					try {
						stmt9.executeUpdate(queryPrenota);
						out.println("OK");
					} catch (Exception e) {
						out.println("NO");
					}
					break;

				/*
				 * Caso per soddisfare la richiesta di aggiungere un nuovo tipo di sintomo non
				 * ancora apparso con il vaccino
				 */
				case "aggiungiSintomo":
					Statement stmt5 = conn.createStatement();
					String queryNuovo = "INSERT INTO eventi (nome) VALUES ('" + requestArray[1] + "');";

					stmt5.executeUpdate(queryNuovo);
					out.println("OK");
					break;

				/*
				 * Caso per soddisfare la richiesta di sapere che vaccino ha fatto l'utente
				 */
				case "vaccini":
					Statement stmt6 = conn.createStatement();
					String queryVacc = "select idvacc, tipovacc, dose from vaccinati v left join utenti on v.userid=utenti.userid "
							+ "where v.userid='" + requestArray[1] + "' order by datasomm";
					String Hvaccini="";
					
					ResultSet rs6 = stmt6.executeQuery(queryVacc);
					
					try {
						rs6.next();

						do {
							Hvaccini += rs6.getString("idvacc") + ";"+ rs6.getString("tipovacc") + ";" +  rs6.getString("dose") + ";";
						} while (rs6.next());

						Hvaccini.substring(0, Hvaccini.length() - 1);
						out.println(Hvaccini);
					} catch (Exception e) {
						out.println("NO");
					}
					break;

				/*
				 * Caso per soddisfare la richiesta di sapere tutti i nomi degli eventi avversi
				 * presenti
				 */
				case "nomeEventi":
					Statement stmt11 = conn.createStatement();
					ResultSet rs9 = stmt11.executeQuery("SELECT nome FROM eventi");
					String eventi = "";
					rs9.next();

					do {
						eventi += rs9.getString("nome") + ";";
					} while (rs9.next());

					eventi.substring(0, eventi.length() - 1);
					out.println(eventi);

					break;
					
				case "aggiungiSegnalazione":
					
					Statement stmtAggiungiSegnalazione = conn.createStatement();
					String queryAggiungiSegnalazione = "INSERT INTO eventiavversi(idvacc, idevento, severita, note)\r\n"
							+ "	VALUES ('" + requestArray[1] + "', '" + requestArray[2] + "', '" + requestArray[3] + "','" + requestArray[4] + "');";

					stmtAggiungiSegnalazione.executeUpdate(queryAggiungiSegnalazione);

					out.println("OK");
					break;

				/*
				 * Caso per soddisfare la richiesta di registrare un nuovo centro vaccinale al
				 * portale
				 */
				case "nuovoCentroVaccinale":

					Statement stmt8 = conn.createStatement();
					String indirizzo = requestArray[5] + " " + requestArray[6];
					String queryNewCV = "INSERT INTO centrivaccinali (nome, comune, indirizzo, cap, provincia, tipologia)"
							+ "VALUES ('" + requestArray[1] + "', '" + requestArray[2] + "', '" + indirizzo
							+ requestArray[3] + "', '" + requestArray[4] + "', '" + requestArray[7] + "');";

					stmt8.executeUpdate(queryNewCV);

					out.println("OK");

				}
				conn.close();

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}