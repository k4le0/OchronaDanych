/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od;

/**
 *
 * @author k4le0
 */
public class Przekazywanie {
 
    private analizaTekst String;
    

    public Przekazanie(analizaTekst String) {
        this.analizaTekst = analizaTekst;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public String toString() {
        return String.format("%s : %s", key.toString(), value.toString());
    }
   
}
