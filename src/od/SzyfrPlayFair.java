/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od;



import java.util.Scanner;
 
public class SzyfrPlayFair
{
    private String slowoKlucz      = new String();
    private String klucz           = new String();
    private char   macierz_tab[][] = new char[5][5];
 
    public void ustawKlucz(String k) // metoda ustawienia sekretnego słowa-klucza, efektem wywołania metody jest wartość klucza, jeżeli w kluczu będą powtarzające się litery, wszystkie bez pierwszej zostaną usunięte
    {
        String slowo = new String();
        boolean flaga = false;
        slowo = slowo + k.charAt(0); // charAt(index) metoda zwraca wartość znaku w określonym index.Pierwszy znak w ciągu jest w indeksie 0, jeśli wartości z index są poza zakresem zwrotu pusty ciąg.
        for (int i = 1; i < k.length(); i++)
        {
            for (int j = 0; j < slowo.length(); j++)
            {
                if (k.charAt(i) == slowo.charAt(j))
                {
                    flaga = true;
                }
            }
            if (flaga == false)
                slowo = slowo + k.charAt(i);
            flaga = false;
        }
        slowoKlucz = slowo;
    }
 
    public void KeyGen() // metoda generowania klucza z łańcucha, w taki sposób, że można utworzyć ten sam klucz z tego samego ciągu
    /* public class KeyGenerator 
		extends Object 
		Ta klasa zapewnia funkcjonalność tajnego (symetrycznego) generatora kluczy. 
		Generatory kluczy są konstruowane przy użyciu jednej z metod klasy getInstance tej klasy.*/
    	
    {
        boolean flaga = true;
        char blok;
        klucz = slowoKlucz;
        for (int i = 0; i < 26; i++)
        {
            blok = (char) (i + 97); // metoda użyta do wstawiania znaków kontrolnych do ciągów znaków, tabela ASCII Dec(97)=Char(a)
            if (blok == 'j')
                continue;
            for (int j = 0; j < slowoKlucz.length(); j++)
            {
                if (blok == slowoKlucz.charAt(j))
                {
                    flaga = false;
                    break;
                }
            }
            if (flaga)
                klucz = klucz + blok;
            flaga = true;
        }
        System.out.println(klucz);
        matrix();
    }
 
    private void matrix() // do szyfrowania każdego z bloków używamy kwadratu szyfrującego. Są to litery alfabetu wstawione w kwadrat (macierz) 5 na 5
    {
        int licznik = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                macierz_tab[i][j] = klucz.charAt(licznik);
                System.out.print(macierz_tab[i][j] + " ");
                licznik++;
            }
            System.out.println();
        }
    }
 
    private String format(String staryTekst)// dzielimy tekst na bloki dwuliterowe, format (format String, Object ... args), zwraca sformatowany ciąg znaków przy użyciu określonego ciągu znaków i argumentów
    {										
    
    
        int i = 0;
        int dlugosc = 0;
        String tekst = new String();
        dlugosc = staryTekst.length();
        for (int t = 0; t < dlugosc; t++)
        {
            if (staryTekst.charAt(t) == 'j')
            {
                tekst = tekst + 'i';
            }
            else
                tekst = tekst + staryTekst.charAt(t);
        }
        dlugosc = tekst.length();
        for (i = 0; i < dlugosc; i = i + 2)
        {
            if (tekst.charAt(i + 1) == tekst.charAt(i))
            {
                tekst = tekst.substring(0, i + 1) + 's' + tekst.substring(i + 1);
            }
        }
        return tekst;
    }
 
    private String[] Podziel(String ciag) // // przed szyfrowaniem, należy podzielić tekst jawny na bloki dwuliterowe, metoda, która może podzielić macierz elementów N na pary
    {
        String wzor = format(ciag); // format (format String, Object ... args) Zwraca sformatowany ciąg znaków przy użyciu określonego ciągu znaków i argumentów.
        int rozmiar = wzor.length();
        if (rozmiar % 2 != 0)
        {
            rozmiar++;
            wzor = wzor + 's';
        }
        String s[] = new String[rozmiar / 2];
        int licznik = 0;
        for (int i = 0; i < rozmiar / 2; i++)
        {
            s[i] = wzor.substring(licznik, licznik + 2);
            licznik = licznik + 2;
        }
        return s;
    }
 
    public int[] GetDiminsions(char litera) // szyfrujemy każdy z bloków dwuliterowych, przekazując tablicę, aby znaleźć jej długość, wymiar tablicy
    {
        int[] Klucz = new int[2];
        if (litera == 'j')
            litera = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (macierz_tab[i][j] == litera)
                {
                    Klucz[0] = i;
                    Klucz[1] = j;
                    break;
                }
            }
        }
        return Klucz;
    }
 /*
  Szyfrujemy każdy z bloków dwuliterowych według trzech zasad:
w a   n i
1. Jeśli obie litery bloku są w tym samym wierszu, zastępujemy je literami następującymi po nich (stosując zasadę cykliczności): wn ---> ai.
i
w
a
g 
n
2. Jeżeli obie litery bloku znajdują się w tej samej kolumnie, zastępujemy je literami, które są pod nimi (też stosując zasadę cykliczności): nw ---> ia.
i  g  w
a  k  n
3. Jeśli litery znajdują się na pozycjach m-n (wiersz numer m, kolumna numer n, licząc od górnego i lewego kąta) oraz k-l, zastępujemy je literami z pozycji m-l oraz k-n: wa ---> in.
*/
    public String szyfruj(String tekstJawny) //W jednym kroku szyfrowywane są 2 litery
    {
        String zrod_tab[] = Podziel(tekstJawny);
        String Kod = new String();
        char pierwsza;
        char druga;
        int cz1[] = new int[2];
        int cz2[] = new int[2];
        for (int i = 0; i < zrod_tab.length; i++)
        {
            pierwsza = zrod_tab[i].charAt(0);
            druga = zrod_tab[i].charAt(1);
            cz1 = GetDiminsions(pierwsza);
            cz2 = GetDiminsions(druga);
            if (cz1[0] == cz2[0])
            {
                if (cz1[1] < 4)
                    cz1[1]++;
                else
                    cz1[1] = 0;
                if (cz2[1] < 4)
                    cz2[1]++;
                else
                    cz2[1] = 0;
            }
            else if (cz1[1] == cz2[1])
            {
                if (cz1[0] < 4)
                    cz1[0]++;
                else
                    cz1[0] = 0;
                if (cz2[0] < 4)
                    cz2[0]++;
                else
                    cz2[0] = 0;
            }
            else
            {
                int temp = cz1[1];
                cz1[1] = cz2[1];
                cz2[1] = temp;
            }
            Kod = Kod + macierz_tab[cz1[0]][cz1[1]]
                    + macierz_tab[cz2[0]][cz2[1]];
        }
        return Kod;
    }
 
    public static void main(String[] args)
    {
        SzyfrPlayFair s = new SzyfrPlayFair(); // instancja klasy,obiekt,{nazwa klasy} określa typ nowo tworzonego obiektu a {argumenty inicjalizacji obiektu} to wartości które będą użyte do inicjalizacji stanu tego obiektu
        Scanner sc = new Scanner(System.in);
        System.out.println("Wprowadź słowo klucz:");
        String slowoklucz = sc.next();
        s.ustawKlucz(slowoklucz);
        s.KeyGen();
        System.out.println("Wprowadź słowo do szyfrowania: (Długość musi być parzysta)");
        String jawny = sc.next();
        if (jawny.length() % 2 == 0)
        {
            System.out.println("Szyfrowane: " + s.szyfruj(jawny));
        }
        else
        {
            System.out.println("Długość musi być parzysta");
        }
        
    }
}