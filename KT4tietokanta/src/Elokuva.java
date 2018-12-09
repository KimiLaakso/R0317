import java.sql.*;

public class Elokuva {
		private String elokuvanNimi;
		private String elokuvanOhjaaja;
		private int elokuvanVuosi;
		
		public Elokuva(){
			this.elokuvanNimi="Ei kerrottu";
			this.elokuvanOhjaaja="Tuntematon";
			this.elokuvanVuosi=0;
		}
		public Elokuva(String elokuvanNimi, String elokuvanOhjaaja, int elokuvanVuosi) {
			this.elokuvanNimi=elokuvanNimi;
			this.elokuvanOhjaaja=elokuvanOhjaaja;
			this.elokuvanVuosi=elokuvanVuosi;
			
		}
		public String getelokuvanNimi() {
			return elokuvanNimi;
		}
		public void setelokuvanNimi(String uusielokuvanNimi) {
			this.elokuvanNimi=uusielokuvanNimi;
		}
		public String getelokuvanOhjaaja() {
			return elokuvanOhjaaja;
		}
		public void setelokuvanOhjaaja(String uusielokuvanOhjaaja) {
			this.elokuvanOhjaaja=uusielokuvanOhjaaja;
		}
		public int getelokuvanVuosi() {
			return elokuvanVuosi;
		}
		public void setelokuvanVuosi(int uusielokuvanVuosi) {
			this.elokuvanVuosi=uusielokuvanVuosi;
		}
		public String toString() {
			return "Elokuvan nimi= " + elokuvanNimi +" elokuvan ohjaaja= " + elokuvanOhjaaja + " elokuvan vuosi = " + elokuvanVuosi;
			
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}