
package randompickercmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RandomPickerCmd {

    public static void main(String[] args) throws IOException {
        
        ArrayList<String> nomes = new ArrayList();
        
        passaNomes(nomes, "C:\\Users\\Meu computador\\Desktop\\UFSM\\5 sem\\Paradigmas\\t5\\RandomPickerCmd\\src\\randompickercmd\\Nomes.txt");
        
        //passaNomes(nomes, args[0]);
        
        Scanner entrada = new Scanner(System.in);
        
        int sair = 0, menu;
        int i;
        while(sair == 0){
            System.out.println("\n---------------------------------\n");
            System.out.print("Digite 1 para mostrar os nomes.\n\n");
            System.out.print("Digite 2 para mostrar embaralhar os nomes.\n\n");
            System.out.print("Digite 3 para fechar o programa.\n\n");
            System.out.println("\n---------------------------------\n");
            menu = entrada.nextInt();
            for(i=0;i<300;i++){
                    System.out.println("\n");
            }
            switch( menu ){
                case 1:
                    System.out.println("\n---------------------------------\n");
                    mostraNomes(nomes);
                    System.out.println("\nPressione Enter para continuar...");
                    System.out.println("\n---------------------------------\n");
                    System.in.read();
            {
                
                for(i=0;i<300;i++){
                    System.out.println("\n");
                }

            }
                    break;
                    
                case 2:
                    System.out.println("\n---------------------------------\n");
                    Collections.shuffle(nomes);
                    System.out.println("\nLista Misturada!");
                    System.out.println("\nPressione Enter para continuar...");
                    System.out.println("\n---------------------------------\n");
                    System.in.read();
                    
                    for(i=0;i<300;i++){
                        System.out.println("\n");
                    }
                    break;
                    
                case 3:
                    sair = 1;
                    break;
                
                default:
                    System.out.println("\n---------------------------------\n");
                    System.out.printf("Voce digitou uma opcao invalida.");
                    System.out.println("\nPressione Enter para continuar...");
                    System.out.println("\n---------------------------------\n");
                    System.in.read();
            }
        }      
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
    
    public static void mostraNomes(ArrayList<String> strList){
        String linha;
        linha = new String();
        int tamanhoArray = strList.size();
        int i = 0;
        
        while(i < tamanhoArray){
                    
            linha = strList.get(i);
            System.out.println(linha);
            i++;
        }
    }
}