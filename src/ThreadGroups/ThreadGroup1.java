package ThreadGroups;

public class ThreadGroup1 extends ThreadGroup {

    public ThreadGroup1(String name) {
        super(name);
        main();
    }

    public ThreadGroup1(ThreadGroup parent, String name) {
        super(parent, name);
        main();
    }

    public void main() {
//        ThreadGroup G1 = new ThreadGroup("G1");
        this.setMaxPriority(Thread.MAX_PRIORITY);

        Thread t = new Thread(this, "Thd");
        t.start();
        t.setPriority(3);
        System.out.println("----------------");
        System.out.println("G1.list Thd");
        this.list();
        System.out.println("----------------");

        t = new Thread(this, "Tha");
        t.start();
        t.setPriority(1);

        System.out.println("----------------");
        System.out.println("G1.list Tha");
        this.list();
        System.out.println("----------------");

        t = new Thread(this, "Thb");
        t.start();
        t.setPriority(3);

        System.out.println("----------------");
        System.out.println("G1.list Thb");
        this.list();
        System.out.println("----------------");


        t = new Thread(this, "Thc");
        t.start();
        t.setPriority(8);

        System.out.println("----------------");
        System.out.println("G1.list Thc");
        this.list();
        System.out.println("----------------");

    }
}
