public class Producer extends Thread {
    private final Depozit depozit;
    private final int[] impare = {1,3,5,7,9,11,13,15,17,19};
    private volatile boolean running = true;

    public Producer(String name, Depozit d) {
        setName(name);
        this.depozit = d;
    }

    public void stopProducer() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {

            for (int i = 0; i < 2; i++) {
                int val = impare[(int) (Math.random() * impare.length)];
                depozit.produce(val, getName());
            }
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
        System.out.println(getName() + " s-a oprit.");
    }
}
