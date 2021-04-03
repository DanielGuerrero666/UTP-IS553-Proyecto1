package pryto1.classes;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ArchivesTests {

    public static void main(String[] args) {
        
        try {
            FileWriter writer = new FileWriter("pryto1/archives/default.txt",true);
            String input = "Prueba de escritura";
            writer.write("\n"+input);
            Scanner scan = new Scanner(new File("pryto1/archives/default.txt"));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                System.out.println(line);
            }
            scan.close();
            writer.close();

        } catch (IOException ex) {
            System.out.println("Error...");
        }
    }    
}
