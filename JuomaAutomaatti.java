import java.util.Scanner;

public class JuomaAutomaatti {
	//Konstruktori
	public JuomaAutomaatti () {
		this.teet�=50;
		this.kahvia=50;
		this.kaakaota=50;
	}
	//Tiedot
	private int teet�;
	private int kahvia;
	private int kaakaota;
	
	//Getterit
	public int getteet�() {
		return this.teet�;
	}
	
	public int getkahvia() {
		return this.kahvia;
	}
	
	public int getkaakaota() {
		return this.kaakaota;
	}
	
	//Setterit
	public void setteet�(int uusiteet�) {
		this.teet�=uusiteet�;
	}
	
	public void setkahvia(int uusikahvia) {
		this.kahvia=uusikahvia;
	}
	
	public void setkaakaota(int uusikaakaota) {
		this.kaakaota=uusikaakaota;
	}
	
	//Metodit jotka tekee juomat
	public void valmistaKahvi() {
		//Kysyt��n onko kahvia enemm�n kuin 10
		if (this.kahvia >= 10) {
			//Jos on otetaan 10 pois ja tulostetaan valmistusviesti
			this.kahvia=this.kahvia-10;
			
		}//Jos ei tulostetaan loppuminen
		else System.out.println("Kahvi loppu");
				
	}
	// Toistetaan muille valmistusmetodeille sama kuin kahville
	public void valmistaTee() {
		if (this.teet� >= 10) {
			this.teet�=this.teet� - 10;
		
	} else
		System.out.println("Tee loppu.");
	}
	public void valmistaKaakao() {
		if (this.kaakaota >= 10) {
		this.kaakaota=this.kaakaota-10;
		
	}else
		System.out.println("Kaakao loppu.");
	}
	public String toString() {
		return "Odota hetki, juomasi valmistuu. Kahvia j�ljell�:" + kahvia + ", teet� j�ljell� " + teet� + ", kaakaota j�ljell� " + kaakaota;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Luodaan oma juomaautomaatti-luokan mukainen olio
		JuomaAutomaatti omaKone = new JuomaAutomaatti();
		Scanner lukija = new Scanner(System.in);
		//alustetaan valinta jonka mukaan juoma valitaan
		int valinta;
		
		do {
			System.out.println("****Juoma-automaatti****\n1. Kahvi\n2. Tee\n3. Kaakao\n4. Lopeta.\n*******************");
			//sy�tet��n valintaan numero 1-4 v�lill�.
			valinta = lukija.nextInt();
			
			//Jos sy�tetty luku isompi kuin 0 ja pienempi kuin 4 tulostetaan k�ytt�liittym� uudelleen ja pyydet��n uusi numero
			if (valinta == 1){
				omaKone.valmistaKahvi();
				System.out.println(omaKone);	
			}
			if (valinta == 2) {
				omaKone.valmistaTee();
				System.out.println(omaKone);
			}
			if (valinta == 3) {
				omaKone.valmistaKaakao();
				System.out.println(omaKone);
			}
			if (valinta == 4)
				System.out.println("Lopetettu");
		} while (valinta > 0 && valinta <4);
	}

}
