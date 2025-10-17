package ThreadGroups;

public class ThreadGroup2 extends ThreadGroup {

    public ThreadGroup2(String name) {
        super(name);
        main();
    }

    public ThreadGroup2(ThreadGroup parent, String name) {
        super(parent, name);
    }

    public static void main() {

        ThreadGroup sys = Thread.currentThread().getThreadGroup();
        sys.list();
        sys.setMaxPriority(Thread.MAX_PRIORITY - 1);

        Thread curr = Thread.currentThread();
        curr.setPriority(curr.getPriority() + 1);
        sys.list();

        ThreadGroup4 G4 = new ThreadGroup4(sys, "G4");
        G4.list(); // (8)
        G4.setMaxPriority(Thread.MAX_PRIORITY);
        G4.list(); // (9)

        Thread t = new Thread(G4, "A");
        t.start();
        t.setPriority(curr.getPriority() + 1);
        G4.list();

        G4.setMaxPriority(Thread.MAX_PRIORITY - 2);
        G4.setMaxPriority(Thread.MAX_PRIORITY);
        G4.list();


    }
}
