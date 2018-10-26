import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Tekstieditori {

	private JFrame frame;
	JEditorPane editorPane = new JEditorPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Tehdään ikkunasta windowsin näkönen
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					Tekstieditori window = new Tekstieditori();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tekstieditori() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Tekstieditori");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Mitä avaa valinta tekee
				//Luo ikkuna jolla valitaan tiedosto
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				try {
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);
					lukija = new Scanner(tiedosto);
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine()+"\n";
							
					}
					lukija.close();
				}
				catch (FileNotFoundException e) {
					System.out.println("Tiedostoa ei löydy");
				}
				editorPane.setText(rivi);
				
			}
		});
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		
		//Tallennus
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Tee tiedoston valintaikkuna
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				//Tehdään uusi tiedosto joka tallennetaan valintaikkunassa valittuun paikkaan
				//tai valintaikkunassa valittuun polkuun johon kirjoitetaan oma tiedostonimi
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				//Tulostetaan minne tallennetaan
				System.out.println ("Tallennetaan tiedostoon : " + uusiTiedosto);
				try {
					//tee printwriteri jokka voit kirjottaa uuteen tiedostoon joka valitaan valintaikkunassa. printwriteri tallentaa siihen
					PrintWriter kirjoittaja = new PrintWriter(uusiTiedosto);
					//otetaan editorPanessa oleva teksti
					String sisalto = editorPane.getText();
					//printwriteri tallentaa otetun tekstin tiedostoon
					kirjoittaja.println(sisalto);
					
					kirjoittaja.flush();
					kirjoittaja.close();
				}catch(Exception e1) {
					System.out.println("Tallennus ei toiminut");
					e1.printStackTrace();
				}
				
			}
		});
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		//Etsi
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sisalto = editorPane.getText();
				sisalto = sisalto.toLowerCase();
				Scanner lukija = new Scanner(System.in);
				String haettava = lukija.nextLine();
				int indeksi = sisalto.indexOf(haettava);
				if (indeksi == -1) {
					System.out.println ("Sanaa " + haettava + " Ei löydy");
				}
				editorPane.setSelectionColor(Color.GREEN);
				editorPane.setSelectionStart(indeksi);
				editorPane.setSelectionEnd(indeksi + haettava.length());
				
			}
		});
		mnMuokkaa.add(mntmEtsi);
		//Tietoja menunappi
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		//Tekijä
		JMenuItem mntmTekij = new JMenuItem("Tekij\u00E4");
		mntmTekij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tietoja tietoikkuna = new tietoja();
				tietoikkuna.UusiIkkuna();	
			}
		});
		mnTietoja.add(mntmTekij);
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//Avaa kuva
		JButton btnAvaa = new JButton("");
		btnAvaa.setIcon(new ImageIcon("C:\\Users\\kimi\\eclipse-workspace\\KT2Tekstieditori\\src\\open2.png"));
		btnAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Mitä avaa valinta tekee
				//Luo ikkuna jolla valitaan tiedosto
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				try {
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);
					lukija = new Scanner(tiedosto);
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine()+"\n";
							
					}
					lukija.close();
				}
				catch (FileNotFoundException e) {
					System.out.println("Tiedostoa ei löydy");
				}
				editorPane.setText(rivi);
			}
		});
		toolBar.add(btnAvaa);
		// Tallenna kuva
		JButton btnTallenna = new JButton("");
		btnTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Tee tiedoston valintaikkuna
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				//Tehdään uusi tiedosto joka tallennetaan valintaikkunassa valittuun paikkaan
				//tai valintaikkunassa valittuun polkuun johon kirjoitetaan oma tiedostonimi
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				//Tulostetaan minne tallennetaan
				System.out.println ("Tallennetaan tiedostoon : " + uusiTiedosto);
				try {
					//tee printwriteri jokka voit kirjottaa uuteen tiedostoon joka valitaan valintaikkunassa. printwriteri tallentaa siihen
					PrintWriter kirjoittaja = new PrintWriter(uusiTiedosto);
					//otetaan editorPanessa oleva teksti
					String sisalto = editorPane.getText();
					//printwriteri tallentaa otetun tekstin tiedostoon
					kirjoittaja.println(sisalto);
					
					kirjoittaja.flush();
					kirjoittaja.close();
				}catch(Exception e1) {
					System.out.println("Tallennus ei toiminut");
					e1.printStackTrace();
				}
				
		
			}
		});
		btnTallenna.setIcon(new ImageIcon("C:\\Users\\kimi\\eclipse-workspace\\KT2Tekstieditori\\src\\icons8-save-as-48.png"));
		toolBar.add(btnTallenna);
		// Kuvat laitettu näin koska eclipse ei löydä jostain syystä mitään kuvia omista hakemistoistaan
		
		
		
		frame.getContentPane().add(editorPane, BorderLayout.CENTER);
	}

}
