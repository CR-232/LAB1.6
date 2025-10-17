package ThreadGroups;

public class ThreadGroup3 extends ThreadGroup {

    public ThreadGroup3(String name) {
        super(name);
        main();
    }

    public ThreadGroup3(ThreadGroup parent, String name) {
        super(parent, name);
        main();
    }

    public static void main() {


        ThreadGroup sys = Thread.currentThread().getThreadGroup();
        sys.list();
        sys.setMaxPriority(Thread.MAX_PRIORITY - 1);

        Thread curr = Thread.currentThread();
        curr.setPriority(curr.getPriority() + 1);
        sys.list();


        ThreadGroup G3 = new ThreadGroup("G1");
        G3.setMaxPriority(Thread.MAX_PRIORITY);

        Thread t = new Thread(G3, "1");
        t.start();
        t.setPriority(4);
        G3.list();

        t = new Thread(G3, "2");
        t.start();
        t.setPriority(curr.getPriority()+3);
        G3.list();

        G3.setMaxPriority(Thread.MAX_PRIORITY - 2);
        G3.setMaxPriority(Thread.MAX_PRIORITY);
        G3.list();

        t = new Thread(G3, "3");
        t.start();
        t.setPriority(5);
        G3.list();

        G3.setMaxPriority(Thread.MAX_PRIORITY - 2);
        G3.setMaxPriority(Thread.MAX_PRIORITY);
        G3.list();
    }
}