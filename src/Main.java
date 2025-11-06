import ThreadGroups.*;

public class Main {
    public static void main(String[] args) {

        ThreadGroup Main = new ThreadGroup("Main");

    Thread Thread1 = new Thread(Main, "Th1");
    Thread1.setPriority(3);
    Thread1.start();
    Main.list();

    Thread Thread2 = new Thread(Main, "Th2");
    Thread2.setPriority(6);
    Thread2.start();
    Main.list();


    ThreadGroup2 tg2 = new ThreadGroup2(Main, "G2");

    ThreadGroup3 tg3 = new ThreadGroup3(Main, "G3");

    System.out.println("----------------------------------------------");
    Main.list();

    }
}