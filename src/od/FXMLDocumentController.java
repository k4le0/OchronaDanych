/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.application.Platform;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;
import java.io.*;


/**
 *
 * @author k4le0
 */
public class FXMLDocumentController implements Initializable {
    
    private Stage stage;
    //public StringBuilder jawnyWczytanyTekst, zaszyfrowanyWczytanyTeskt;
    public TextArea zaszyfrowanyWczytanyTesktArea,odszyfrowanyWczytanyTesktArea, analizaArea; 
    public String zaszyfrowanyWczytanyTeskt,odszyfrowanyTekst, analizaTekst;
   
    
    
    
    @FXML
    private Label label1_1,label2_1,label3_1,label4_1,label5_1;
    
    @FXML
    TextField kluczPodwstawieniowy;

  
    

    
    public void init(Stage stage){
        this.stage = stage;
        //this.zaszyfrowanyWczytanyTeskt = zaszyfrowanyWczytanyTeskt;
        //this.odszyfrowanyTekst = odszyfrowanyTekst;
        //TODO
    }
    
    public void openFile(ActionEvent event) {
        System.out.println("Open file");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
            );
        File file = fileChooser.showOpenDialog(stage);
        
        if(file != null){
            System.out.println("Choosen file " + file);
            try {
            //Files.lines(file.toPath()).forEach(System.out::println);
            String content = new String(Files.readAllBytes(file.toPath()));
            zaszyfrowanyWczytanyTesktArea.setText(content);
            zaszyfrowanyWczytanyTeskt = content;
            }catch (IOException ex){
                Logger.getLogger(FXMLDocumentController.class.getName())
                        .log(Level.SEVERE,null,ex);
            }
        }
    }
    
    /**public void analizaTekstu(ActionEvent event) {
    analizaArea.setText(analizaTekst); 
    }

    // ========================PRZYCISKI SZYFROWANIE 
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label1_1.setText("Hello World!");
    } */
    
    public static float round2(float number, int scale) {
    int pow = 10;
    for (int i = 1; i < scale; i++)
        pow *= 10;
    float tmp = number * pow;
    return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
}
    
    public void analizaTekstu (ActionEvent event) {
    //BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    //System.out.println("Enter Any Text: ");
    //String output = BR.readLine();
    String output = zaszyfrowanyWczytanyTeskt;
    output=output.toLowerCase();
    
    int length = output.length();
    char character;
    int totalCount = 0;

    //we'll store each encountered character in this map, along with a count of the number
    //of times encountered.
    Map<Character, Integer> map = new HashMap<Character,Integer>();

    //Loop over the output once, character by character
    for (int i = 0; i < length; i++)
    {
        character = output.charAt(i);
        totalCount++; //This is the total number of characters we've found in the output

        Integer countForCharacter = 0;
        //check in map if we have a count for this character
        if (map.containsKey(character)) {
            //get the current count we have for this character
            countForCharacter = map.get(character);
            //increment
            countForCharacter++;
            //increment the count
        } else {
            countForCharacter = 1;
        }

        //Now put the up to date count into the map
        map.put(character, countForCharacter);
    }


    //Get the found characters as an array of Character
    Character[] charactersFound = map.keySet().toArray(new Character[0]);

    analizaArea.setText("Litera\tCzestotliwosc\tLiczba\n");
    for(int k = 2; k < charactersFound.length; k++)
    {
        character = charactersFound[k];
        analizaArea.appendText(character+
                "\t" +"\t" +
                //Following line gets the count for the character and divides by totalCount,
                //making sure that the the result is a floating point
                round2((map.get(character)/((float)totalCount)),3)  +
                "\t"+"\t" +
                //get the count for the character
                map.get(character)+"\n");
    }
    
    //analizaArea.setText(TekstDoWpisania);  
    //analizaArea.appendText(output);
}

    
    public void dodajTekstDoArea(String TekstDoWpisania)
    {
    analizaArea.setText(TekstDoWpisania);    
    }
    
    @FXML
    private void przyciskSzyfrujPodstawienie(ActionEvent event) {
        System.out.println("You clicked me!");
        label1_1.setText("Hello World!");
    }
    @FXML
    private void przyciskSzyfrujCezar(ActionEvent event) {
        System.out.println("You clicked me!");
        label2_1.setText("Hello World!");
    }
    @FXML
    private void przyciskSzyfrujXOR(ActionEvent event) {
        System.out.println("You clicked me!");
        label3_1.setText("Hello World!");
    }
    @FXML
    private void przyciskSzyfrujVigenere(ActionEvent event) {
        System.out.println("You clicked me!");
        label4_1.setText("Hello World!");
    }
    @FXML
    private void przyciskSzyfrujPlayfair(ActionEvent event) {
        System.out.println("You clicked me!");
        label5_1.setText("Hello World!");
    }    
   
// ========================PRZYCISKI ODSZYFROWANIE 
    @FXML
    private void przyciskOdszyfrujPodstawienie(ActionEvent event) {
        System.out.println("Rozpoczęto odszyfrowanie tekstu metodą podstawieniową ");
        System.out.println("Wykorzystywany klucz to ");
        System.out.println(kluczPodwstawieniowy.getText());
        odszyfrowanyTekst = SzyfrPodstawieniowy.decrypt(kluczPodwstawieniowy.getText(), zaszyfrowanyWczytanyTeskt);
        label1_1.setText("Tekst odszyfrowano, wynik poniżej:");
        System.out.println(odszyfrowanyTekst);
        odszyfrowanyWczytanyTesktArea.setText(odszyfrowanyTekst);
        
    }
    @FXML
    private void przyciskOdszyfrujCezar(ActionEvent event) {
        System.out.println("You clicked me!");
        label2_1.setText("Hello World!");
    }
    @FXML
    private void przyciskOdszyfrujXOR(ActionEvent event) {
        System.out.println("You clicked me!");
        label3_1.setText("Hello World!");
    }
    @FXML
    private void przyciskOdszyfrujVigenere(ActionEvent event) {
        System.out.println("You clicked me!");
        label4_1.setText("Hello World!");
    }
    @FXML
    private void przyciskOdszyfrujPlayfair(ActionEvent event) {
        System.out.println("You clicked me!");
        label5_1.setText("Hello World!");
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
}
