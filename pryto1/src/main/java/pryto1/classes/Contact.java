package pryto1.classes;

public class Contact{
    private String name;
    private String numbers;
    private String mail;
    private String direction;
    private String nick;

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

    public void showData(){
        System.out.println("==============================");
        System.out.println("Nombre: "+name);
        System.out.println("Numeros: "+numbers);
        System.out.println("E-Mail: "+mail);
        System.out.println("Dirección: "+direction);
        System.out.println("Apodo: "+nick);
        System.out.println("==============================");        
    }

    public void editData(){

    }

    public void deleteData(){
        
    }    
}