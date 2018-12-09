import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class elokuvaGUI {

	private JFrame frmElokuvat;
	private JTable table;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					elokuvaGUI window = new elokuvaGUI();
					window.frmElokuvat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public elokuvaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmElokuvat = new JFrame();
		frmElokuvat.setTitle("Elokuvat");
		frmElokuvat.setBounds(100, 100, 450, 300);
		frmElokuvat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmElokuvat.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnLisElokuva = new JButton("Lis\u00E4\u00E4 elokuva");
		btnLisElokuva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Kun nappia painetaan avataan uusi ikkuna luokasta uusiElokuvaGUI
				uusiElokuvaGUI uusiikkuna = new uusiElokuvaGUI();
				uusiikkuna.uusielokuva();
			}
		});
		panel.add(btnLisElokuva);
		
		JButton btnPivitTaulu = new JButton("P\u00E4ivit\u00E4 taulu");
		btnPivitTaulu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Päivitetään taulua..");
				DefaultTableModel model = new DefaultTableModel(new String[] {"Elokuvan nimi", "Ohjaaja", "Vuosi",}, 0);
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7268556", "sql7268556", "qd5NFeCz1j");
					
					//Alustetaan sql komento jolla saadaan kaikki tiedot tietokannasta
					String sql = "SELECT * FROM Elokuva";
					//luodaa statement joka tajuu tietokantaa
					Statement statement = con.createStatement();
					//otetaan taulun elokuva kaikki tiedot talteen resul muuttujaan
					ResultSet result = statement.executeQuery(sql);
					
					//tehdää silmukka joka lisää rivit malliin
					while(result.next()) {
						//tulostaa niin kauan kuin result muuttuja löytää uutta tietoa lisääsen modeliin uutena rivinä
						String elokuvanNimi = result.getString("Elokuvan_nimi");
						String elokuvanOhjaaja = result.getString("Ohjaaja");
						String elokuvanVuosi = result.getString("Vuosi");
						model.addRow(new Object[] {elokuvanNimi, elokuvanOhjaaja, elokuvanVuosi});
						
						//Liitetään malli tauluun
						table.setModel(model);
					}
					System.out.println("Taulu päivitetty");
				con.close();
				}catch(Exception e) {
					System.out.println("Virhe");
					e.printStackTrace();
				}	
				
			}
		});
		panel.add(btnPivitTaulu);
		
		JButton btnPoistaRivi = new JButton("Poista rivi");
		btnPoistaRivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				poistaRivi(table);
			}
		});
		panel.add(btnPoistaRivi);
		
		//Tehdään taulu joka tulostaa tietokannassa olevat tiedot
		
		table = new JTable();
		frmElokuvat.getContentPane().add(table, BorderLayout.CENTER);
		
		//Tehdään taulukon malli
		DefaultTableModel model = new DefaultTableModel(new String[] {"Elokuvan nimi", "Ohjaaja", "Vuosi",}, 0);
		
		//luodaa yhteys tietokantaan
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7268556", "sql7268556", "qd5NFeCz1j");
			
			//Alustetaan sql komento jolla saadaan kaikki tiedot tietokannasta
			String sql = "SELECT * FROM Elokuva";
			//luodaa statement joka tajuu tietokantaa
			Statement statement = con.createStatement();
			//otetaan taulun elokuva kaikki tiedot talteen resul muuttujaan
			ResultSet result = statement.executeQuery(sql);
			
			//tehdää silmukka joka lisää rivit malliin
			while(result.next()) {
				//tulostaa niin kauan kuin result muuttuja löytää uutta tietoa lisääsen modeliin uutena rivinä
				String elokuvanNimi = result.getString("Elokuvan_nimi");
				String elokuvanOhjaaja = result.getString("Ohjaaja");
				String elokuvanVuosi = result.getString("Vuosi");
				model.addRow(new Object[] {elokuvanNimi, elokuvanOhjaaja, elokuvanVuosi});
				
				//Liitetään malli tauluun
				table.setModel(model);
			}
		con.close();
		}catch(Exception e) {
			System.out.println("Virhe");
			e.printStackTrace();
		}	
	}
	
	//Luodaan metodi joka poistaa rivin
	public void poistaRivi(JTable table) {
		//alustetaan muuttuja joka on jtable = table:n valittu rivi
		int rivi = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		String valitturivi = model.getValueAt(rivi, 0).toString();
		
		//Tehdään silmukka joka poistaa valitun rivin
		if(rivi>=0)
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7268556", "sql7268556", "qd5NFeCz1j");
			String sql = "DELETE FROM Elokuva WHERE Elokuvan_nimi=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, valitturivi);
			System.out.println("Poistetaan valittu rivi..");
			stmt.executeUpdate();
			System.out.println("Rivi poistettu");
			
			
			//Suljetaan yhteydet
			con.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	}