import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class uusiElokuvaGUI {

	private JFrame frmLisUusiKirja;
	private JTextField nimiKentta;
	private JTextField ohjaajaKentta;
	private JTextField vuosiKentta;

	/**
	 * Launch the application.
	 */
	public static void uusielokuva() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					uusiElokuvaGUI window = new uusiElokuvaGUI();
					window.frmLisUusiKirja.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public uusiElokuvaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLisUusiKirja = new JFrame();
		frmLisUusiKirja.setTitle("Lis\u00E4\u00E4 uusi kirja");
		frmLisUusiKirja.setBounds(100, 100, 238, 300);
		frmLisUusiKirja.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmLisUusiKirja.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Elokuvan nimi");
		lblNewLabel.setBounds(20, 21, 105, 23);
		frmLisUusiKirja.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ohjaaja");
		lblNewLabel_1.setBounds(20, 109, 46, 14);
		frmLisUusiKirja.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Vuosi");
		lblNewLabel_2.setBounds(20, 192, 46, 14);
		frmLisUusiKirja.getContentPane().add(lblNewLabel_2);
		
		nimiKentta = new JTextField();
		nimiKentta.setBounds(10, 44, 86, 20);
		frmLisUusiKirja.getContentPane().add(nimiKentta);
		nimiKentta.setColumns(10);
		
		ohjaajaKentta = new JTextField();
		ohjaajaKentta.setBounds(10, 134, 86, 20);
		frmLisUusiKirja.getContentPane().add(ohjaajaKentta);
		ohjaajaKentta.setColumns(10);
		
		vuosiKentta = new JTextField();
		vuosiKentta.setBounds(10, 217, 86, 20);
		frmLisUusiKirja.getContentPane().add(vuosiKentta);
		vuosiKentta.setColumns(10);
		
		JButton btnLisKirja = new JButton("Lis\u00E4\u00E4 kirja");
		btnLisKirja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tallennaElokuva();
			}
		});
		btnLisKirja.setBounds(106, 216, 89, 23);
		frmLisUusiKirja.getContentPane().add(btnLisKirja);
	}

	public static void elokuvaTalteen(Elokuva filmi) {
		String nimi=filmi.getelokuvanNimi();
		String ohjaaja=filmi.getelokuvanOhjaaja();
		int vuosi = filmi.getelokuvanVuosi();
		
		Connection con = null;
		
		
		try {
			System.out.println("Yhdistetään tietokantaan..");
			
			
			//Luodaan yhteys tietokantaan
			con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7268556", "sql7268556", "qd5NFeCz1j");
			//Alustetaan sql komento
			String sql = "INSERT INTO Elokuva (Elokuvan_nimi, Ohjaaja, Vuosi) values (?,?,?)";
			////tehdään prepared statement jolla sql-komento ajetaan kyssäreiden paikalle ja tietokantaan
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setString(1, nimi);
			pStatement.setString(2, ohjaaja);
			pStatement.setInt(3, vuosi);
			
			pStatement.execute();
			
			System.out.println("Tallennetaan uusi elokuva talteen.");
			con.close();
		}catch(Exception e) {
			System.out.println("virhee");
			e.printStackTrace();
		}
	}
	public void tallennaElokuva() {
		//haetaan gui:n kentistä tiedot talteen
		String elokuvanNimi = nimiKentta.getText();
		String elokuvanOhjaaja = ohjaajaKentta.getText();
		String elokuvanVuosi = vuosiKentta.getText();
		
		//luodaan uusi elokuva elokuva luokasta
		Elokuva uusiElokuva = new Elokuva(elokuvanNimi, elokuvanOhjaaja, Integer.parseInt(elokuvanVuosi));
		//tallennetaan äskön luotu uusielokuva aiemmin luodutta elokuvatalteen metodilla tietokantaan
		elokuvaTalteen(uusiElokuva);
		
	}
}