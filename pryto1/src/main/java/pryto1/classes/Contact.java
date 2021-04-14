package pryto1.classes;

import java.util.Scanner;

public class Contact{
    private String name;
    private String numbers;
    private String mail;
    private String direction;
    private String nick;

    public Contact(){}

    public Contact(String name, String numbers, String mail, String direction, String nick) {
        this.name = name;
        this.numbers = numbers;
        this.mail = mail;
        this.direction = direction;
        this.nick = nick;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void editData(int option, String newValue){
        Scanner scan = new Scanner(System.in);
        switch(option){
            case 1: 
                setName(newValue);
                break;
            case 2: 
                String addingNumber = getNumbers()==" "? newValue:","+newValue;
                setNumbers(getNumbers()+addingNumber);
                break;
            case 3:    
                setNumbers(getNumbers().replaceAll(newValue.substring(0, newValue.indexOf("|")),
                    newValue.substring(newValue.indexOf("|")+1, newValue.length())));
                break;
            case 4:
                setMail(newValue);
                break;
            case 5: 
                setDirection(newValue);
                break;
            case 6: 
                setNick(newValue);
                break;
            default: 
                System.out.println("Error: Opción invalida");
                break;
        }
        scan.close();
    }

}