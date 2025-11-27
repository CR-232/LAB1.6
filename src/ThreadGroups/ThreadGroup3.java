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

        Thread th1 = new Thread(this, () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Th1");
        th1.setPriority(4);
        th1.start();

        Thread th2 = new Thread(this, () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Th2");
        th2.setPriority(3);
        th2.start();

        Thread th3 = new Thread(this, () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Th3");
        th3.setPriority(5);
        th3.start();

        this.list();
    }
}