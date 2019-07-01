
package enadeufsmexplorer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableDados {
    
    private String ano;
    private String Prova;
    private String TipoQuestao;
    private String IdQuestao;
    private String Objeto;
    private String AcertosCurso;
    private String AcertosRegiao;
    private String AcertosBrasil;
    private String Dif;
    
    public static void config(TableView table, Scene scene){
        
        TableColumn Col1 = new TableColumn("Ano");
        TableColumn Col2 = new TableColumn("Prova");
        TableColumn Col3 = new TableColumn("Tipo Questao");
        TableColumn Col4 = new TableColumn("Id Questao");
        TableColumn Col5 = new TableColumn("Objeto");
        TableColumn Col6 = new TableColumn("Acertos Curso");
        TableColumn Col7 = new TableColumn("Acertos Regiao");
        TableColumn Col8 = new TableColumn("Acertos Brasil");
        TableColumn Col9 = new TableColumn("Dif.");
        
        Col1.prefWidthProperty().bind(scene.widthProperty().divide(20).subtract(2.1/9));
        Col1.maxWidthProperty().bind(Col1.prefWidthProperty());
        Col1.setResizable(false);
        
        Col2.prefWidthProperty().bind(scene.widthProperty().divide(20).subtract(2.1/9));
        Col2.maxWidthProperty().bind(Col2.prefWidthProperty());
        Col2.setResizable(false);
        
        Col3.prefWidthProperty().bind(scene.widthProperty().divide(7).subtract(2.1/9));
        Col3.maxWidthProperty().bind(Col3.prefWidthProperty());
        Col3.setResizable(false);
        
        Col4.prefWidthProperty().bind(scene.widthProperty().divide(9).subtract(2.1/9));
        Col4.maxWidthProperty().bind(Col4.prefWidthProperty());
        Col4.setResizable(false);
        
        Col5.prefWidthProperty().bind(scene.widthProperty().divide(9).subtract(2.1/9));
        Col5.maxWidthProperty().bind(Col5.prefWidthProperty());
        Col5.setResizable(false);
        
        Col6.prefWidthProperty().bind(scene.widthProperty().divide(7).subtract(2.1/9));
        Col6.maxWidthProperty().bind(Col6.prefWidthProperty());
        Col6.setResizable(false);
        
        Col7.prefWidthProperty().bind(scene.widthProperty().divide(7).subtract(2.1/9));
        Col7.maxWidthProperty().bind(Col7.prefWidthProperty());
        Col7.setResizable(false);
        
        Col8.prefWidthProperty().bind(scene.widthProperty().divide(7).subtract(2.1/9));
        Col8.maxWidthProperty().bind(Col8.prefWidthProperty());
        Col8.setResizable(false);
        
        Col9.prefWidthProperty().bind(scene.widthProperty().divide(9).subtract(2.1/9));
        Col9.maxWidthProperty().bind(Col9.prefWidthProperty());
        Col9.setResizable(false);
        
        table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8, Col9);
        table.autosize();
        
    }
    
    public TableDados(String ano, String Prova, String TipoQuestao, String IdQuestao, String Objeto, String AcertosCurso, String AcertosRegiao, String AcertosBrasil, String Dif){
        this.ano = ano;
        this.Prova = Prova;
        this.TipoQuestao = TipoQuestao;
        this.IdQuestao = IdQuestao;
        this.Objeto = Objeto;
        this.AcertosCurso = AcertosCurso;
        this.AcertosRegiao = AcertosRegiao;
        this.AcertosBrasil = AcertosBrasil;       
        this.Dif = Dif;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getProva() {
        return Prova;
    }

    public void setProva(String Prova) {
        this.Prova = Prova;
    }

    public String getTipoQuestao() {
        return TipoQuestao;
    }

    public void setTipoQuestao(String TipoQuestao) {
        this.TipoQuestao = TipoQuestao;
    }

    public String getIdQuestao() {
        return IdQuestao;
    }

    public void setIdQuestao(String IdQuestao) {
        this.IdQuestao = IdQuestao;
    }

    public String getObjeto() {
        return Objeto;
    }

    public void setObjeto(String Objeto) {
        this.Objeto = Objeto;
    }

    public String getAcertosCurso() {
        return AcertosCurso;
    }

    public void setAcertosCurso(String AcertosCurso) {
        this.AcertosCurso = AcertosCurso;
    }

    public String getAcertosRegiao() {
        return AcertosRegiao;
    }

    public void setAcertosRegiao(String AcertosRegiao) {
        this.AcertosRegiao = AcertosRegiao;
    }

    public String getAcertosBrasil() {
        return AcertosBrasil;
    }

    public void setAcertosBrasil(String AcertosBrasil) {
        this.AcertosBrasil = AcertosBrasil;
    }

    public String getDif() {
        return Dif;
    }

    public void setDif(String Dif) {
        this.Dif = Dif;
    }
    
    public static String[] ReadCsv (String str, int n){
        
        ///abre um arquivo e cria um file
       File arquivoCSV = new File(str);
        
        try{
            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);
            
            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = new String();
            
            //ignora a primeira linha do arquivo
            leitor.nextLine();
                
            //recebe cada linha do arquivo
            linhasDoArquivo = leitor.nextLine();

            //separa os campos entre as virgulas de cada linha
            String[] valoresEntreVirgulas = linhasDoArquivo.split(",");

            return valoresEntreVirgulas;
            
        }catch(FileNotFoundException e){
            
        } 
        return null;
    }
    
}
