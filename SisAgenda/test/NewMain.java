
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristian
 */
public class NewMain {

    public static void main(String[] args) {
        String resp="s";
        String nome, disc;
        float notas[] =new float[3];
        Scanner in =new Scanner(System.in);
        
        while(resp.equalsIgnoreCase("s")){
            System.out.println("Nome:");
            nome=in.next();
            System.out.println("Disciplina:");
            disc=in.next();
            for (int i = 0; i < 3; i++) {
                System.out.printf("Nota[%d]:",i+1);
                notas[i]=in.nextFloat();
            }
            
            System.out.println("Deseja continuar(S/N)?");
            resp=in.next();
            
        }
    }

}
