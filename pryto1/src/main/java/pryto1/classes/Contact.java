package pryto1.classes;

import java.util.ArrayList;
import java.util.List;

public class Contact{
    private String name;
    private List<String> numbers = new ArrayList<>();
    private String mail;
    private String direction;
    private String nick;

    public Contact(String name, String number, String mail, String direction, String nick) {
        this.setName(name);
        numbers.add(number);
        this.setMail(mail);
        this.setDirection(direction);
        this.setNick(nick);
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

    public void showData(){

    }

    public void editData(){

    }

    public void deleteData(){
        
    }
    
}