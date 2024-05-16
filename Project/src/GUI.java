public class GUI {

    private void readability;
    private void functionalies;

    public GUI(void readability, void functionalies){
        this.readability = readability;
        this.functionalies = functionalies;
    }

    public void display(){
        System.out.println("Displaying GUI");
    }

    public void switchTab(){
        System.out.println("Switching tab");
    }

    public void showInfo(){
        System.out.println("Showing info");
    }
}
