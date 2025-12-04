public class Depozit {
    private final int[] buffer;
    private int size = 0;
    private final int capacity;

    public Depozit(int D) {
        this.capacity = D;
        this.buffer = new int[D];
    }

    public synchronized void produce(int value1, int value2, String producerName) {

        if (check()) {
            System.out.println(producerName +  " a pierdut " + value1 +" si " + value2
                    + " | Depozit: " + size + "/" + capacity);
            return;
        }


        buffer[size] = value1;
        size++;
        System.out.println(producerName + " a produs: " + value1
                + " | Depozit: " + size + "/" + capacity);

        if (check()) {
            System.out.println(producerName + " a pierdut " + value2
                    + " | Depozit: " + size + "/" + capacity);
            return;
        }

        System.out.println(producerName + " a produs: " + value2
                + " | Depozit: " + size + "/" + capacity);

        buffer[size] = value2;
        size++;

        System.out.println(producerName + " a produs: " + value1 +" si " + value2
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

    public boolean check() {
        if(size == capacity) {
            return true;
        }

        return false;
    }

}
