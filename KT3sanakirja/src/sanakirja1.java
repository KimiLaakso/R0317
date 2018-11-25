import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class sanakirja1 {
	public static void main(String[] args) {
		
		HashMap<String, String> sanakaannokset=new HashMap<String, String>();
		
		sanakaannokset.put("kissa", "cat");
		sanakaannokset.put("koira", "dog");
		sanakaannokset.put("hevonen", "horse");
		sanakaannokset.put("auto", "car");
		sanakaannokset.put("vene", "boat");
	
		String sana1;
		String sana2;
		
		try {
			FileInputStream fis = new FileInputStream("hashmap.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			sanakaannokset = (HashMap) ois.readObject();
		}catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
		
		do {
			System.out.println("Sanakairjassa on sanat: ");//TUlostaa sanakirjan sanat aina kun on tehty joku toiminto
			Set set = sanakaannokset.entrySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Map.Entry mentry = (Map.Entry)it.next();
				System.out.print(mentry.getKey() + "=" + mentry.getValue()+" ");
			}
			System.out.println();
			
		System.out.println("Anna sana jonka k��nn�ksen haluat tai lis�� sana kirjoittamalla lis�� tai lopeta v�lily�nnill�: ");
		
		Scanner scanner = new Scanner(System.in);
		sana1 = scanner.nextLine(); //Etsitty sana
		String k��nn�s=sanakaannokset.get(sana1);
		
		if(sanakaannokset.containsKey(sana1)) { //Jos hashmapista l�ytyy sana1 eli etsitty sana tulostetaan sana ja sen k��nn�s
			System.out.println("Sanan " + sana1 + " k��nn�s on "+ k��nn�s);
			System.out.println("V�lily�nti lopettaa");
		}
		if(sana1.contains(" ")) { //Jos sy�tetty sana on v�lily�nti lopetetaan ohjelma
			System.out.println("Lopetetaan");
		}
		if (sana1.contains("lis��")) { //jos sy�tetty sana on lis�� annetaan lis�t� uusi sana
			System.out.println("Anna haluttu sana ensin suomeksi sitten englanniksi: ");
			sana1=scanner.nextLine();
		      sana2=scanner.nextLine();
		      sanakaannokset.put(sana1, sana2);
		      
		}
		}while (!sana1.contains(" ")); //Jatketaan ohjelmaa kunnes sy�tetty sana on v�lily�nti joka lopettaa ohjelman
		
		try {
			FileOutputStream fos = new FileOutputStream("hashmap.ser");//Valitaan tiedosto hashmap.ser minne kirjoitetaan 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(sanakaannokset);//Kirjotetaan sanakaannokset hashmap tiedostoon hashmap.ser
			oos.close();
			fos.close();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}