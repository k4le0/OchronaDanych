package od;


import java.io.*;
import java.util.*;


public class SzyfrXOR {
    static String tekstJawny = "Przykładowy string do zaszyfrowania";
    static String klucz = "klucz";
    
    public static void main(String args[]) { 
        System.out.print("Tekst jawny to: ");
        System.out.println(tekstJawny);
        System.out.println("========================= Szyfrowanie tekstu jawnego =========================");
        int[] szyfrogram = szyfrujXOR(tekstJawny,klucz);
        System.out.print("Szyfrogram: ");
        for(int i = 0; i < szyfrogram.length; i++)
            System.out.printf("%d,", szyfrogram[i]);
        System.out.println("");
        System.out.println("========================= Odszyfrowanie szyfrogramu =========================");
        System.out.println(odszyfrujXOR(szyfrogram,klucz));        
    }

    private static int[] szyfrujXOR(String str, String key) {
        //tworzenie tablicy od dlugosci tekstu jawnego
        int[] output = new int[str.length()];
        for(int i = 0; i < str.length(); i++) {
            int o = (Integer.valueOf(str.charAt(i)) ^ Integer.valueOf(key.charAt(i % (key.length() - 1)))) + '0';
            output[i] = o;
        }
        return output;        
    }


    private static String odszyfrujXOR(int[] input, String key) {
        String output = "";        
        for(int i = 0; i < input.length; i++) {
            output += (char) ((input[i] - 48) ^ (int) key.charAt(i % (key.length() - 1)));
        }
        return output;
    }
}