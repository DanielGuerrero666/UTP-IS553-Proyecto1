package pryto1.classes;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ArchivesTests {

    public static void main(String[] args) {
        
        try {
            Scanner scan = new Scanner(new File("pryto1/archives/test.txt"));
            while(scan.hasNextLine()){
                int index;
                int aux;

                String line = scan.nextLine();
                index = line.indexOf(";");
                String name = line.substring(0, index);
                aux = index+1;
                index = line.indexOf(";", aux);
                String number = line.substring(aux, index);
                aux = index+1;
                index = line.indexOf(";", aux);
                String mail = line.substring(aux, index);
                aux = index+1;
                index = line.indexOf(";", aux);
                String direction = line.substring(aux, index);
                aux = index+1;
                index = line.length();
                String nick = line.substring(aux, index);

                System.out.println(name);
                System.out.println(number);
                System.out.println(mail);
                System.out.println(direction);
                System.out.println(nick);
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("Error: File not Found...");
        }
    }    
}
