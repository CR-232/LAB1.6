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
        this.setMaxPriority(Thread.MAX_PRIORITY);

        Thread thd = new Thread(this, () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Thd");
        thd.setPriority(3);
        thd.start();

        Thread tha = new Thread(this, () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Tha");
        tha.setPriority(1);
        tha.start();

        Thread thb = new Thread(this, () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Thb");
        thb.setPriority(3);
        thb.start();

        Thread thc = new Thread(this, () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Thc");
        thc.setPriority(8);
        thc.start();

        this.list();

//
//        System.out.println("----------------");

    }
}
