/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzercmd;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GitHubAnalyzerCmd {

    public static void main(String[] args) throws IOException {
        
        String strArq = new File(".").getCanonicalPath();
        String nomeArquivo = strArq + "\\DadosGit.txt";
        System.out.println(nomeArquivo);
        
        Thread t = new Thread() { //Creating an object of Anonymous class which extends Thread class and passing this object to the reference of Thread class.
            @Override
            public void run(){   //Anonymous class overriding run() method of Thread class
                try {
                    ParseGithubWithGson(nomeArquivo, args[0]);   
                } catch (IOException ex) {
                    Logger.getLogger(GitHubAnalyzerCmd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }
    
    public static void ParseGithubWithGson(String str, String ss) throws MalformedURLException, IOException{
        
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
            gravarArq.printf("%n");
            str1 = e.getAsJsonObject().get("commit").toString();
            System.out.println(str1);
            gravarArq.print(str1);
            
            str2 = e.getAsJsonObject().get("author").toString();
            System.out.println(str2);
            gravarArq.print(str2);
            
            //str3 = e.getAsJsonObject().get("date").toString();
            //System.out.println(str3);
            //gravarArq.print(str3);*/
        }
        
        arqLeitura.close();
        in.close();
    }
    
}
