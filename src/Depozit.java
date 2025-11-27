public class Depozit {
    private final int[] buffer;
    private int size = 0;
    private final int capacity;

    public Depozit(int D) {
        this.capacity = D;
        this.buffer = new int[D];
    }

    public synchronized void produce(int value, String producerName) {

        if (size == capacity) {
            System.out.println(producerName + " -> Depozit plin! Obiectul " + value + " s-a pierdut.");
            return;
        }

        buffer[size] = value;
        size++;

        System.out.println(producerName + " a produs: " + value
                + " | Depozit: " + size + "/" + capacity);

        notifyAll();
    }

    public synchronized int consume(String consumerName) {
        while (size == 0) {
            System.out.println(consumerName + " -> Depozitul este gol, asteapta...");
            try { wait(); } catch (InterruptedException e) {}
        }

        int value = buffer[size - 1];
        size--;

        System.out.println(consumerName + " a consumat: " + value
                + " | Depozit: " + size + "/" + capacity);

        notifyAll();
        return value;
    }
}
