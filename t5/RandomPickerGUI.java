
package randompickergui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class RandomPickerGUI extends Application {
    
    Stage janela;
    Scene scene;
    Button button, buttonNext, buttonExit, buttonHelp;
    ListView<String> listView;
    int pos = 0;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        janela = primaryStage;
        janela.setTitle("RandomPickerGUI");
        button = new Button("Shuffle");
        buttonNext = new Button("Next");
        buttonExit = new Button("Exit");
        buttonHelp = new Button("Help");
        
        ArrayList<String> nomes = new ArrayList();
    
        passaNomes(nomes, "C:\\Users\\Meu computador\\Desktop\\UFSM\\5 sem\\Paradigmas\\t5\\RandomPickerGUI\\src\\randompickergui\\Nomes.txt");
        
        listView = new ListView<>();
        
        int tamanho = nomes.size(), i;
        for(i=0; i < tamanho; i++){
            String nome = nomes.get(i);
            listView.getItems().add(nome);
        }
        
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        button.setOnAction(e -> buttonPressionado(nomes));
        
        buttonNext.setOnAction(e -> buttonNextPressionado(nomes));
        
        buttonExit.setOnAction(e -> Platform.exit());
        
        buttonHelp.setOnAction(e -> buttonHelp());
                
        HBox layoutButtons = new HBox(10);
        layoutButtons.setPadding(new Insets(10,10,10,10));
        layoutButtons.getChildren().addAll(button, buttonNext, buttonHelp, buttonExit);
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,20,20,20));
        layout.getChildren().addAll(listView, layoutButtons);
        
        buttonNext.setDisable(true);
        scene = new Scene(layout, 300, 280);
        janela.setScene(scene);
        janela.show();
        
    }

    
    
    public static void passaNomes(ArrayList<String> strList, String str){
        String linha = new String();
        String nomeArquivo = str;
        
        File arq = new File(nomeArquivo);
        
        if(arq.exists()){
            
            try{
                FileReader leitorDeArquivo = new FileReader(nomeArquivo);
                BufferedReader bufferDeArquivo = new BufferedReader(leitorDeArquivo);
                
                while(true){
                    
                    linha = bufferDeArquivo.readLine();
                    
                    if(linha == null){
                        break;
                    }
                    strList.add(linha);
                }
                
            }catch(IOException e){
                System.out.println("Erro ao ler arquivo");
            }
        }
    }
    
    private void buttonPressionado(ArrayList<String> strList){
        int i,tamanho = strList.size();
        String nome;
        
        Collections.shuffle(strList);
        listView.getItems().clear();
        buttonNext.setDisable(false);
        pos = 0;

    }
    
    private void buttonNextPressionado(ArrayList<String> strList){
        String nome;
        int tamanho = strList.size();
        
        nome = strList.get(pos);
        listView.getItems().add(nome);
        if(pos == tamanho-1){
            buttonNext.setDisable(true);
        }else{
            pos++; 
        }
    }
    
    private void buttonHelp(){
        Label label1 = new Label("RandomPickerGUI");
        Label label2 = new Label("Thiago Bellotti Pavin");
        Label label3 = new Label("         ");
        Label label4 = new Label("         ");
        Label label5 = new Label("         ");
        
        label1.setFont(Font.font("Arial", 25));
        label2.setFont(Font.font("Arial", 24));
                
        Button buttonExit2;
        buttonExit = new Button("Exit");
        
        VBox layout2 = new VBox(10);
        layout2.setPadding(new Insets(10,20,20,20));
        layout2.getChildren().addAll(label1, label4, label2, label3, label5, buttonExit);
        
        Scene secondScene = new Scene(layout2, 300, 200);
        
        Stage secondStage = new Stage();
        secondStage.setTitle("About");
        secondStage.setScene(secondScene);
        
        buttonExit.setOnAction(e -> secondStage.close());
        
        secondStage.show();
    }
}
