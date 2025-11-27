public class Consumer extends Thread {
    private final Depozit depozit;
    private final int need;

    public Consumer(String name, Depozit d, int z) {
        setName(name);
        this.depozit = d;
        this.need = z;
    }

    @Override
    public void run() {
        for (int i = 0; i < need; i++) {
            depozit.consume(getName());
            try { Thread.sleep(400); } catch (InterruptedException e) {}
        }
        System.out.println(getName() + " a terminat consumul.");
    }
}
