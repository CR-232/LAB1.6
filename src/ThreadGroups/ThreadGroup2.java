package ThreadGroups;

public class ThreadGroup2 extends ThreadGroup {

    public ThreadGroup2(String name) {
        super(name);
        main();
    }

    public ThreadGroup2(ThreadGroup parent, String name) {
        super(parent, name);
        main();
    }

    public void main() {

        ThreadGroup4 G4 = new ThreadGroup4(this, "G4");
        G4.setMaxPriority(this.getMaxPriority() - 1);
        System.out.println("----------------");
        System.out.println("G4.list TG2");
        G4.list();
        System.out.println("----------------");
//        G4.start();

        Thread t = new Thread(this, "ThA");
        t.start();
        t.setPriority(1);

        System.out.println("----------------");
        System.out.println("G2.list pentru ThA");
        this.list();
        System.out.println("----------------");

    }
}
