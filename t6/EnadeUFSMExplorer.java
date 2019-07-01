
package enadeufsmexplorer;


import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class EnadeUFSMExplorer extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vbox, 1000, 500);
        
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Help");
        Menu menu3 = new Menu("Info");
 
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        
        MenuItem menuItem4 = new MenuItem("Info sobre programa");
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
 
                Label secondLabel = new Label("          Informacao sobre o programa");
                secondLabel.setFont(Font.font("Arial", 25));
                Label secondLabel2 = new Label("          ");
                
                Text text = new Text();
                text.setFont(Font.font("Arial", 15));
                text.setWrappingWidth(500);
                text.setTextAlignment(TextAlignment.JUSTIFY);
                text.setText("  Professora nao consegui ler o arquivo csv, por problemas na formatacao do arquivo, ela variava muito e nao consegui retirar todos os dados corretamente\n Mas consegui:\n  - Baixar o aquivo\n  - Criar os menus\n  - Criar a tableview\n  - Organizar a interface");
 
                VBox secondaryLayout = new VBox();
                secondaryLayout.getChildren().addAll(secondLabel, secondLabel2, text);
 
                Scene secondScene = new Scene(secondaryLayout, 500, 300);
 
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
        
        MenuItem menuItem1 = new MenuItem("Exit");
        menuItem1.setOnAction(e -> Platform.exit());
        
        MenuItem menuItem2 = new MenuItem("Reload");
        
        MenuItem menuItem3 = new MenuItem("Source");

        MenuItem menuItem5 = new MenuItem("About");
        menuItem5.setOnAction((ActionEvent event) -> {
            Label secondLabel = new Label("         About");
            secondLabel.setFont(Font.font("Arial", 25));
            Label secondLabel2 = new Label("          ");
            Text text1 = new Text();
            text1.setFont(Font.font("Arial", 15));
            text1.setWrappingWidth(200);
            text1.setTextAlignment(TextAlignment.JUSTIFY);
            text1.setText("       Thiago Bellotti Pavin\n\n       EnadeUFSMExplorer");
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
        
        menu1.getItems().addAll(menuItem2, menuItem3, menuItem1);
        menu2.getItems().add(menuItem5);
        menu3.getItems().add(menuItem4);
        
        Label label = new Label("Enade");
        
        String str = new File(".").getCanonicalPath();
        System.out.println(str);

        String arq = str + "\\enade.csv";
        DonwloadCsv(arq);
        
        TableView table = new TableView();

        TableDados.config(table, scene);
        
        vbox.getChildren().addAll(menuBar,label, table);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void DonwloadCsv (String str) throws MalformedURLException, IOException{
        String arq = str;
        if(!new File(arq).exists()) {
            URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv");
            File file = new File(arq);

            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream(file);

            int bytes = 0;

            while ((bytes = is.read()) != -1) {
                fos.write(bytes);
            }

            is.close();

            fos.close();
        }
    }
}
