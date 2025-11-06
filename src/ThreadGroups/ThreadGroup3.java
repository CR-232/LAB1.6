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

    public void main() {

//        ThreadGroup G3 = new ThreadGroup("G3");
        this.setMaxPriority(Thread.MAX_PRIORITY);

        Thread t = new Thread(this, "Th1");
        t.start();
        t.setPriority(4);
        this.list();
//        System.out.println("----------------");
//        System.out.println("G3.list TG3");
//        this.list();
//        System.out.println("----------------");

        t = new Thread(this, "Th2");
        t.start();
        t.setPriority(3);
//        System.out.println("----------------");
//        System.out.println("G3.list TG3");
        this.list();
//        System.out.println("----------------");

//        G3.setMaxPriority(Thread.MAX_PRIORITY - 2);
//        G3.setMaxPriority(Thread.MAX_PRIORITY);
//        G3.list();

        t = new Thread(this, "Th3");
        t.start();
        t.setPriority(5);
        this.list();
    }
}