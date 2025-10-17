package ThreadGroups;

public class ThreadGroup4 extends ThreadGroup {

    public ThreadGroup4(String name) {
        super(name);
        main();
    }

    public ThreadGroup4(ThreadGroup parent, String name) {
        super(parent, name);
    }

    public void main() {

    ThreadGroup sys = Thread.currentThread().getThreadGroup();
        sys.list();
        sys.setMaxPriority(Thread.MAX_PRIORITY - 1);

    Thread curr = Thread.currentThread();
        curr.setPriority(curr.getPriority() + 1);
        sys.list();



        ThreadGroup1 G1 = new ThreadGroup1("G1");
        G1.list(); // (8)
        G1.setMaxPriority(Thread.MAX_PRIORITY);
        G1.list(); // (9)


    }
}

