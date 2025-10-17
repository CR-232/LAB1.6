import ThreadGroups.*;

public class Main {
    public static void main(String[] args) {
    Thread Thread1 = new Thread("1");
    Thread1.setPriority(3);
    Thread1.start();


    Thread Thread2 = new Thread("2");
    Thread2.setPriority(6);
    Thread2.start();

    ThreadGroup2 tg2 = new ThreadGroup2("G2");
    tg2.setMaxPriority(10);
    tg2.list();

    ThreadGroup3 tg3 = new ThreadGroup3("G3");
    tg2.setMaxPriority(10);
    tg3.list();
    }
}