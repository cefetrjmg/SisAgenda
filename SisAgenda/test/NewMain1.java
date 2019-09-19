
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cristian
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File f = new File("Contato.txt");
        System.out.println(f.getAbsolutePath());
        if (!f.exists()) {
            if (f.createNewFile()) {
                System.out.println("Arquivo criado em " + f.getAbsolutePath());
            }
        }
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Hello, world!");
        bw.newLine();
        bw.write("Cristian Madeira");
        bw.write("");
        bw.close();
        fw.close();
        
        
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            System.out.println(br.readLine());
        }

    }

}
