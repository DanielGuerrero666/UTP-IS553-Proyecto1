/* Clase Phonebook, JavaSE-1.8, 16/04/2021, Juan Daniel Noriega Guerrero
 * Clase principal del sistema, los metodos que permiten el funcionamiento del mismo tiene su mayor
 * peso aqui.
 * 
 */
package pryto1.classes;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class Phonebook{
    private List<Contact> contactList = new ArrayList<>(); 
    private static File archive;
    private static String contactInfoFormat;
    private static String contactName;
    private static String contactNumbers;
    private static String contactMail;
    private static String contactDirection;
    private static String contactNick;

    /* Metodo "addContact", recibe un contacto y lo agrega a la lista de contactos
    */

    public void addContact(Contact contact){
        contactList.add(contact);
    }

    /* Metodo "searchContactByName", recibe un String name con el nombre del contacto a buscar
    *  devuelve un array de contactos que coinciden con el nombre buscado.
    *  En caso de no hallar ningun contacto con el nombre dado, arroja una OriginalException
    *  maneja una lista de contactos coincidentes llamada "tempList".
    */
    public Contact[] searchContactByName(String name) throws OriginalException{
        int searchedCounter=0;
        List<Contact> tempList = new ArrayList<>();
        Contact[] contactArray;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getName().compareTo(name) == 0 ){
                tempList.add(instanceOfContact);
            }else{
                searchedCounter++;   
            }
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este nombre");
        }else{
            contactArray = new Contact[tempList.size()]; 
            int j=0;
            for(Contact instanceOfContact : tempList){
                contactArray[j] = instanceOfContact;
                j++;
            }
        }
        tempList.clear();
        return contactArray;
    }

    /* Metodo "searchContactByNumber" que recibe un String number con el numero a buscar
    *  recorre la lista de contactos y accede a su atributo "numbers" para comparar con el
    *  numero recibido como parametro, de no hallarlo arroja una OriginalException.
    */
    public Contact searchContactByNumber(String number) throws OriginalException{
        int searchedCounter=0;
        Contact foundedContact = new Contact();
        for(Contact instanceOfContact : contactList){
            String numbersChain = instanceOfContact.getNumbers();
            int aux=0;
            int index;
            do {
                index = numbersChain.indexOf(",", aux);
                if(index == -1){
                    index = numbersChain.length();
                }
                String actualNumber = numbersChain.substring(aux, index);
                if(actualNumber.compareTo(number) == 0){
                    foundedContact = instanceOfContact;
                    break;
                }else{
                    aux = index+1;
                }
                aux = index+1;
            } while (index != numbersChain.length());
            searchedCounter++;
        }
        if(searchedCounter-1 == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este número");
        }
        return foundedContact;
    }

    /* Metodo "searchContactByMail", recibe un String mail con el correo del contacto a buscar
    *  devuelve un array de contactos que coinciden con el correo buscado.
    *  En caso de no hallar ningun contacto con el correo dado, arroja una OriginalException
    *  maneja una lista de contactos coincidentes llamada "tempList".
    */
    public Contact[] searchContactByMail(String mail) throws OriginalException{
        int searchedCounter=0;
        List<Contact> tempList = new ArrayList<>();
        Contact[] contactArray;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getMail().compareTo(mail) == 0 ){
                tempList.add(instanceOfContact);
            }else{
                searchedCounter++;   
            }
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este correo electronico");
        }else{
            contactArray = new Contact[tempList.size()]; 
            int j=0;
            for(Contact instanceOfContact : tempList){
                contactArray[j] = instanceOfContact;
                j++;
            }
        }
        tempList.clear();
        return contactArray;
    }

    /* Metodo "searchContactByDirection", recibe un String direction con la
    *  direccion del contacto a buscar devuelve un array de contactos 
    *  que coinciden con la direccion buscada.
    *  En caso de no hallar ningun contacto con la direccion dada, arroja una OriginalException
    *  maneja una lista de contactos coincidentes llamada "tempList".
    */
    public Contact[] searchContactByDirection(String direction) throws OriginalException{
        int searchedCounter=0;
        List<Contact> tempList = new ArrayList<>();
        Contact[] contactArray;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getDirection().compareTo(direction) == 0 ){
                tempList.add(instanceOfContact);
            }else{
                searchedCounter++;   
            }
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo esta dirección");
        }else{
            contactArray = new Contact[tempList.size()]; 
            int j=0;
            for(Contact instanceOfContact : tempList){
                contactArray[j] = instanceOfContact;
                j++;
            }
        }  
        tempList.clear();
        return contactArray;
    }

    /* Metodo "searchContactByNick", recibe un String nick con el sobrenombre del contacto a buscar
    *  devuelve un array de contactos que coinciden con el sobrenombre buscado.
    *  En caso de no hallar ningun contacto con el sobrenombre dado, arroja una OriginalException
    *  maneja una lista de contactos coincidentes llamada "tempList".
    */
    public Contact[] searchContactByNick(String nick) throws OriginalException{
        int searchedCounter=0;
        List<Contact> tempList = new ArrayList<>();
        Contact[] contactArray;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getNick().compareTo(nick) == 0 ){
                tempList.add(instanceOfContact);
            }else{
                searchedCounter++;   
            }
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este sobrenombre");
        }else{
            contactArray = new Contact[tempList.size()]; 
            int j=0;
            for(Contact instanceOfContact : tempList){
                contactArray[j] = instanceOfContact;
                j++;
            }
        }
        tempList.clear();
        return contactArray;
    }

    /* Metodo "returnInfo" devuelve un array de todos los contactos en la lista de contactos actual
    */
    public Contact[] returnInfo(){
        Contact[] contactArray = new Contact[contactList.size()]; 
        int i=0;
        for(Contact instanceOfContact : contactList){
            contactArray[i] = instanceOfContact;
            i++;
        }
        return contactArray;
    }

    /* Metodo "identifyEspecificContact" recibe la posicion de la lista desde la perspectiva del usuario
    *  y luego la busca en la lista de contactos, devuelve el contacto hallado en la posicion dada.
    */
    public Contact identifyEspecificContact(int positionInList) throws OriginalException{
        positionInList--;
        if(positionInList>=contactList.size()){
            throw new OriginalException("Posicion vacia, no es posible acceder a la informacion");
        }
       return contactList.get(positionInList);
    }

    /* Metodo "editEspecificContactInfo" en conjunto con el metodo editaData de la clase Contact
    *  filtra las opciones del metodo a invocar con el parametro option, el parametro positionInList
    *  se encarga de indetificar el contacto a editar y el parametro newInfo es la nueva informacion 
    *  dada como parametro. 
    */
    public void editEspecificContactInfo(int option, int positionInList, String newInfo){
        positionInList--;
        contactList.get(positionInList).editData(option, newInfo);
    }

    /* metodo "deleteContact" recibe la posicion del contacto a eliminar como unico parametro
    */
    public void deleteContact(int positionInList){
        positionInList--;
        contactList.remove(positionInList);
    }

    /* metodo "verifyContactNumbers" este metodo se encarga de recorrer la lista cargada del archivo 
    *  y verificar si existen numeros repetidos, en cuyo caso detendra la ejecucion del programa.
    */
    public void verifyContactNumbers() throws OriginalException{
        if(contactList.isEmpty()==false){
            for(Contact instanceOfContact : contactList){
                String numbersChain = instanceOfContact.getNumbers();
                int aux=0;
                int index;
                do {
                    index = numbersChain.indexOf(",", aux);
                    if(index == -1){
                        index = numbersChain.length();
                    }
                    String actualNumber = numbersChain.substring(aux, index);
                    int counter = 0;
                    for(Contact innerInstanceOfContact : contactList){
                        if(innerInstanceOfContact.getNumbers().indexOf(actualNumber) != -1){
                            counter++;
                            if(counter>1){
                                throw new  OriginalException("Error: Número repetido hallado, Imposible acceder a la informacion de contacto...");
                            }
                        }
                    }
                    aux = index+1;
                } while (index != numbersChain.length());
            }
        }
    }

    /* Metodo "verifyEditContactNumbers" Tambien verifica que no se guarden numeros repetidos, pero
    *  se limita a realizarlo una vez tras la edicion del atributo "numbers" en cualquier contacto.
    *
    *  Indicara al usuario del suceso de guardar un numero ya existente.
    */
    public void verifyEditContactNumbers() throws OriginalException{
        if(contactList.isEmpty()==false){
            for(Contact instanceOfContact : contactList){
                String numbersChain = instanceOfContact.getNumbers();
                int aux=0;
                int index;
                do {
                    index = numbersChain.indexOf(",", aux);
                    if(index == -1){
                        index = numbersChain.length();
                    }
                    String actualNumber = numbersChain.substring(aux, index);
                    int counter = 0;
                    for(Contact innerInstanceOfContact : contactList){
                        if(innerInstanceOfContact.getNumbers().indexOf(actualNumber) != -1){
                            counter++;
                            if(counter>1){
                                throw new OriginalException("Error: No es posible almacenar números repetidos en la agenda");
                            }
                        }
                    }
                    aux = index+1;
                } while (index != numbersChain.length());
            }
        }
    }

    /* Metodo "verifyStructureOfFile" recibe un nombre de archivo ubicado en la carpeta "archives"
    *  verifica que la estructura del archivo cumpla con lo requerido para su correcta lectura.
    */
    public Integer verifyStructureOfFile(String vFileName){
        int counter=0;
        try {
            Scanner scan = new Scanner(new File("pryto1/archives/"+vFileName+".txt"));
            String line = scan.nextLine();
            counter=0;
            while (line.contains(";")) {
                line = line.substring(line.indexOf(";") + ";".length(), line.length());
                counter++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return counter;
    }

    /* Metodo "loadData" recibe el nombre del archivo a cargar como parametro.
    *  Como su numbre inidca, este es el encargado de cargar la informacion desde el archivo
    */
    public void loadData(String fileName){
        try {
            Scanner scan = new Scanner(new File("pryto1/archives/"+fileName+".txt"));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                int index = line.indexOf(";");
                contactName = line.substring(0, index);
                int aux = index+1;
                index = line.indexOf(";", aux);
                contactNumbers = line.substring(aux, index);
                aux = index+1;
                index = line.indexOf(";", aux);
                contactMail = line.substring(aux, index);
                aux = index+1;
                index = line.indexOf(";", aux);
                contactDirection = line.substring(aux, index);
                aux = index+1;
                index = line.length();
                contactNick = line.substring(aux, index);
                Contact instanceOfContact = new Contact(contactName, contactNumbers,
                     contactMail, contactDirection, contactNick);
                addContact(instanceOfContact);
            }
            scan.close();
        } catch (IOException ex) {
            archive = new File("pryto1/archives/default.txt");
                try {
                    archive.createNewFile();
                    loadData("default");
                } catch (IOException ex2) {
                    ex2.printStackTrace();
            }
        }
    }    

    /*  Metodo "saveData" Recibe el nombre del archivo sobre el cual escribira la informacion
    *   De no hallarlo creara uno nuevo y escribira sobre este la informacion creada por el programa
    *   Respetando la estructura de archivo.
    */
    public void saveData(String fileName){
        try {
            FileWriter writer = new FileWriter("pryto1/archives/"+fileName+".txt");
            for(Contact instanceOfContact : contactList){
                contactInfoFormat = instanceOfContact.getName()+";"+instanceOfContact.getNumbers()+";"+
                    instanceOfContact.getMail()+";"+instanceOfContact.getDirection()+";"+
                        instanceOfContact.getNick();                   
                
                writer.write(contactInfoFormat+"\n");
            }
            writer.close();
            contactList.clear();
        } catch (IOException e) {
            File newFile = new File("pryto1/archives/"+fileName+".txt");
            try {
                newFile.createNewFile();
                saveData(fileName);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /*  Metodo "clean" permite un acceso externo a la limpiezza de la lista de contactos
    */
    public void clean(){
        contactList.clear();
    }
}