/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzergui;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GitHubAnalyzerGUI extends Application{
    
    ListView<String> listView;
    
    static String str = "";
    static int a = 0;
    static Canvas canvas = new Canvas ( 8000, 8000 );
    static GraphicsContext ctx = canvas.getGraphicsContext2D ( );
    static ScrollPane s1 = new ScrollPane ( );
    
    @Override
    public void start(Stage primaryStage){
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vbox, 1000, 500);
        
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Tools");
        Menu menu3 = new Menu("Help");
 
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        
        MenuItem menuItem1 = new MenuItem("Exit");
        menuItem1.setOnAction(e -> Platform.exit());
        
        listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        MenuItem menuItem2 = new MenuItem("Open");   
        menuItem2.setOnAction((e -> {
            try {
                fileStr(listView, primaryStage);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GitHubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GitHubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String ss = listView.getSelectionModel().getSelectedItem();

                String strArq = "";
                try {
                    strArq = new File(".").getCanonicalPath();
                } catch (IOException ex) {
                    Logger.getLogger(GitHubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                //String nomeArq = strArq + "\\Teste.txt";

                s1.setPannable ( true );
                s1.setLayoutX ( 20 );
                s1.setLayoutY ( 20 );
                s1.setPrefSize ( 1000, 500 );
                s1.setContent ( canvas );
                
                Thread t = new Thread() { //Creating an object of Anonymous class which extends Thread class and passing this object to the reference of Thread class.
                    @Override
                    public void run(){   //Anonymous class overriding run() method of Thread class
                        setName("GitThread");
                        try {
                            fileWrite(ss);
                        } catch (IOException ex) {
                            Logger.getLogger(GitHubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                t.start();

                Label secondLabel = new Label("Commits Information");
                secondLabel.setFont(Font.font("Arial", 25));
                Label secondLabel2 = new Label("          ");

                VBox secondaryLayout = new VBox();
                secondaryLayout.getChildren().addAll(secondLabel, secondLabel2, s1);
                Scene secondScene = new Scene(secondaryLayout, 1000, 500);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);
                newWindow.show();
            }
        });

        MenuItem menuItem3 = new MenuItem("About");
        menuItem3.setOnAction((ActionEvent event) -> {
            Label secondLabel = new Label("         About");
            secondLabel.setFont(Font.font("Arial", 25));
            Label secondLabel2 = new Label("          ");
            Text text1 = new Text();
            text1.setFont(Font.font("Arial", 15));
            text1.setWrappingWidth(200);
            text1.setTextAlignment(TextAlignment.JUSTIFY);
            text1.setText("       Thiago Bellotti Pavin\n\n       GitHubAnalyzerGUI");
            VBox secondaryLayout = new VBox();
            secondaryLayout.getChildren().addAll(secondLabel, secondLabel2, text1);
            Scene secondScene = new Scene(secondaryLayout, 200, 100);
            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            // Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);
            newWindow.show();
        });
        
        menu1.getItems().addAll(menuItem2, menuItem1);
        menu3.getItems().add(menuItem3);

        vbox.getChildren().addAll(menuBar, listView);
        
        primaryStage.setTitle("t7 - Thiago Pavin");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    //Funciona! Esta função deixa escolher o arquivo e escreve suas linhas na listView
    private void fileStr(ListView<String> listView, Stage primaryStage) throws FileNotFoundException, IOException{
    
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("TXT", "*.txt")
            );
            
            File file = fileChooser.showOpenDialog(primaryStage);
            
            String fileAsString = file.toString();
            String linha = new String();
            
            FileReader leitorDeArquivo = new FileReader(fileAsString);
            BufferedReader bufferDeArquivo = new BufferedReader(leitorDeArquivo);
                
            while(true){

                linha = bufferDeArquivo.readLine();

                if(linha == null){
                    break;
                }
                listView.getItems().add(linha);
            }
    }
    
    //Funciona! Esta função lê um arquivo e copia para um text na tela
    private static void Ler_Arquivo (String s) throws IOException {

        FileReader leitorDeArquivo = new FileReader(s);
        BufferedReader bufferDeArquivo = new BufferedReader(leitorDeArquivo);
        
        ctx.setFont ( Font.font ( "Arial", FontWeight.NORMAL, 13 ) );
        String line = "";
        String num = "";
        while ( ( line = bufferDeArquivo.readLine()) != null ) {
             a++;
             num += a + "\n";
             str += line + "\n";
        }
        ctx.setFill ( Color.RED );
        ctx.fillText ( num, 10, 80 );
        ctx.setFill ( Color.BLACK );
        ctx.fillText ( str, 50, 80 );

    }
     
    public void fileWrite(String ss) throws IOException{
        
        String strArq = new File(".").getCanonicalPath();
        String nomeArquivo = strArq + "\\DadosGit.txt";
        System.out.println(nomeArquivo);
        
        ParseGithubWithGson(nomeArquivo, ss);

        Ler_Arquivo(nomeArquivo);   
    }
    
    public void ParseGithubWithGson(String str, String ss) throws MalformedURLException, IOException{
        String urlstr = ss;

        URL url = new URL(urlstr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        System.out.println("Response code: " + con.getResponseCode());

        BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));

        // Response header (includes pagination links)
        System.out.println(con.getHeaderFields().get("Link").get(0));

        // Parse a nested JSON response using Gson
        JsonParser parser = new JsonParser();
        JsonArray results = parser.parse(in.readLine()).getAsJsonArray();
        System.out.println("Size: "+ results.size());
        
        FileWriter arqLeitura = new FileWriter(str);
        PrintWriter gravarArq = new PrintWriter(arqLeitura);
        
        String str1 = "";
        String str2 = "";
        //String str3 = "";
        
        for (JsonElement e : results) {
            
            str1 = e.getAsJsonObject().get("commit").toString();

            System.out.println(str1);
            gravarArq.print(str1);
            
            gravarArq.print("\n");
            
            str2 = e.getAsJsonObject().get("author").toString();
            System.out.println(str2);
            gravarArq.print(str2);
            
            gravarArq.print("\n");
            //str3 = e.getAsJsonObject().get("date").toString();
            //System.out.println(str3);
            //gravarArq.print(str3);
        }
        
        arqLeitura.close();
        in.close();
    }

}