/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



/**
 *
 * @author k4le0
 */
public class FXMLDocumentController implements Initializable {
    
    private Stage stage;
    //public StringBuilder jawnyWczytanyTekst, zaszyfrowanyWczytanyTeskt;
    public TextArea zaszyfrowanyWczytanyTesktArea,odszyfrowanyWczytanyTesktArea; 
    public String zaszyfrowanyWczytanyTeskt,odszyfrowanyTekst;
   
    
    
    
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
    
    public void analizaTekstu(ActionEvent event) {
        
    }

    // ========================PRZYCISKI SZYFROWANIE 
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label1_1.setText("Hello World!");
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
