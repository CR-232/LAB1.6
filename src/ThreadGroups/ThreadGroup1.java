package ThreadGroups;

public class ThreadGroup1 extends ThreadGroup {

    public ThreadGroup1(String name) {
        super(name);
        main();
    }

    public ThreadGroup1(ThreadGroup parent, String name) {
        super(parent, name);
    }

    public void main() {
        ThreadGroup sys = Thread.currentThread().getThreadGroup();
        sys.list();
        sys.setMaxPriority(Thread.MAX_PRIORITY - 1);

        Thread curr = Thread.currentThread();
        curr.setPriority(curr.getPriority() + 1);
        sys.list();


        ThreadGroup G1 = new ThreadGroup("G1");
        G1.setMaxPriority(Thread.MAX_PRIORITY);

        Thread t = new Thread(G1, "d");
        t.start();
        t.setPriority(3);
        G1.list();

        G1.setMaxPriority(Thread.MAX_PRIORITY - 2);
        G1.setMaxPriority(Thread.MAX_PRIORITY);
        G1.list();

        t = new Thread(G1, "a");
        t.start();
        t.setPriority(Thread.MAX_PRIORITY);

        G1.list();
        G1.setMaxPriority(Thread.MIN_PRIORITY + 2);

        t = new Thread(G1, "b");
        t.start();
        t.setPriority(t.getPriority() - 1);

        G1.list();
        t = new Thread(G1, "c");
        t.start();
        t.setPriority(t.getPriority() - 1);
    }
}
