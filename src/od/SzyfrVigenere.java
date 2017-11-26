/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od;import java.util.Scanner;

public class SzyfrVigenere {
 
private String wejscie;
private String wyjscie;
private char[][] alfabetTab = new char['z' + 1]['z' + 1];
private Scanner sc = new Scanner(System.in);
 

public static void main(String[] args) {
SzyfrVigenere prog = new SzyfrVigenere();
prog.utworzalfabetTab();
prog.pobierzDane();
 
}

public void utworzalfabetTab() {
System.out.println("Tablica Vigenera");
 
for (char a = 'a'; a <= 'z'; a++) {
System.out.println("");
char b = a;
for (int z = 'a'; z <= 'z'; z++) {
if (b == 'z' + 1) {
b = 'a';
}
alfabetTab[a][z] = b;
System.out.print(alfabetTab[a][z] + ",");
b++;
}
}
}
 

public void pobierzDane() {
System.out.println("\n\n Wybierz opcje: \n\n 1. Szyfrowanie\n 2. Odszyfrowanie\n 3. Koniec\n");
System.out.print(":");
String opcje = sc.nextLine();
if ("1".equals(opcje)) {
System.out.println("Podaj tekst do szyfrowania.");
System.out.print("  tekst:");
wejscie = sc.nextLine();
System.out.println("\nPodaj hasło niekrótsze niż " + wejscie.length());
System.out.print("  hasło:");
wyjscie = sc.nextLine();
szyfruj(wejscie, wyjscie);
pobierzDane();
} else if ("2".equals(opcje)) {
System.out.println("Podaj tekst do odszyfrowania");
System.out.print("  tekst:");
wejscie = sc.nextLine();
System.out.println("\nPodaj hasło niekrótsze niż " + wejscie.length());
System.out.print("  hasło:");
wyjscie = sc.nextLine();
deszyfruj(wejscie, wyjscie);
pobierzDane();
} else if ("3".equals(opcje)) {
System.out.println("Dziękuję i Żegnaj!");
} else {
pobierzDane();
}
 
}
 
public void szyfruj(String wejscie, String wyjscie) {
char[] tekstTable = wejscie.toCharArray();
char[] wyjscieTable = wyjscie.toCharArray();
System.out.print("\n    Rezultat:");
for (int i = 0; i < tekstTable.length; i++) {
System.out.print(alfabetTab[tekstTable[i]][wyjscieTable[i]]);
}
 
}
 

public void deszyfruj(String wejscie, String wyjscie) {
char[] tekstTable = wejscie.toCharArray();
char[] wyjscieTable = wyjscie.toCharArray();
 
System.out.print("\n    Wynik:");
for (int i = 0; i < tekstTable.length; i++) {
if (tekstTable[i] != ' ') {
for (int z = 'a'; z <= 'z'; z++) {
if (tekstTable[i] == alfabetTab[wyjscieTable[i]][z]) {
System.out.print(alfabetTab['a'][z]);
 
}
}
} else {
System.out.print(" ");
}
}
 
}
}