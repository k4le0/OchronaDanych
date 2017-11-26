import java.util.*;


public class SzyfrCezara {


	public static final String ALFABET = "abcdefghijklmnopqrstuvwxyz"; //final wskazuje, że wartość zmiennej nie ulegnie zmianie, zmienna, kórej wartości nie można zmienić po zadeklarowaniu

		public static String szyfruj(String tekstJawny, int klucz) //metoda statyczna pozwalająca na współużytkowanie dla wszystkich instancji klasy.
		{
			tekstJawny = tekstJawny.toLowerCase();
			String szyfrogram = "";
			for (int i = 0; i < tekstJawny.length(); i++)
			{
				int pozycjaZnaku = ALFABET.indexOf(tekstJawny.charAt(i));
				int etykieta = (klucz + pozycjaZnaku) % 26; //modulo 26
				char zamienione = ALFABET.charAt(etykieta);
				szyfrogram += zamienione;
			}
			return szyfrogram;
}

		public static String odszyfruj(String szyfrogram, int klucz)
	{
			szyfrogram = szyfrogram.toLowerCase();
			String tekstJawny = "";
			for (int i = 0; i < szyfrogram.length(); i++)
				{
			int pozycjaZnaku = ALFABET.indexOf(szyfrogram.charAt(i));
			int etykieta = (pozycjaZnaku - klucz) % 26;
			if (etykieta < 0)
				{
			etykieta = ALFABET.length() + etykieta;
				}
			char zamienione = ALFABET.charAt(etykieta);
			tekstJawny += zamienione;
				}
			return tekstJawny;
			}
				public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Wprowadź tekst: ");
		String tekst = new String();
		tekst = sc.next();
		System.out.println("Podaj klucz z przedziału [-26..26]: ");
		int klucz = sc.nextInt();
		System.out.println("Tekst zaszyfrowany: ");
		System.out.println(szyfruj(tekst, klucz));
		System.out.println("Tekst odszyfrowany: " );
		System.out.println(odszyfruj(szyfruj(tekst, klucz), klucz));
		
		}
}
			



