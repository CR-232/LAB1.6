import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class ProducatorConsumator {

    private static final int PRODUCERS = 2;
    private static final int CONSUMERS = 4;
    private static final int GOAL = 4;
    private static final int BUFFER_SIZE = 7;
    private static final int F = 2;

    private static final BlockingQueue<Integer> buffer =
            new ArrayBlockingQueue<>(BUFFER_SIZE);

    private static final AtomicInteger totalProduced = new AtomicInteger(0);
    private static final AtomicInteger totalConsumed = new AtomicInteger(0);

    private static final int TOTAL_OBJECTS = CONSUMERS * GOAL;

    private static final Map<Integer, AtomicInteger> consumerCount =
            new ConcurrentHashMap<>();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(PRODUCERS + CONSUMERS);

        for (int i = 1; i <= CONSUMERS; i++) {
            consumerCount.put(i, new AtomicInteger(0));
        }

        for (int i = 1; i <= PRODUCERS; i++) {
            executor.execute(new Producer(i));
        }

        for (int i = 1; i <= CONSUMERS; i++) {
            executor.execute(new Consumer(i));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n======= RAPORT FINAL =======");
        System.out.println("Total produse: " + totalProduced.get());
        System.out.println("Total consumate: " + totalConsumed.get());

        consumerCount.forEach((id, cnt) ->
                System.out.println("Consumator " + id + " -> " + cnt.get() + " obiecte consumate"));

        System.out.println("=============================");
    }

    static class Producer implements Runnable {
        private final int id;
        private final Random random = new Random();

        Producer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (totalProduced.get() < TOTAL_OBJECTS) {

                    for (int k = 0; k < F; k++) {
                        if (totalProduced.get() >= TOTAL_OBJECTS) break;

                        int item = generateOddNumber();

                        if (buffer.remainingCapacity() == 0) {
                            System.out.println("[Producator " + id + "] Depozitul e plin, asteapta...");
                        }

                        buffer.put(item);
                        int p = totalProduced.incrementAndGet();

                        System.out.println("[Producator " + id + "] produs: "
                                + item + " | Total produse: " + p
                                + " | Depozit: " + buffer.size() + "/" + BUFFER_SIZE);
                    }

                    Thread.sleep(200);
                }

                System.out.println("Producatorul " + id + " a terminat.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private int generateOddNumber() {
            int n;
            do {
                n = random.nextInt(50) + 1;
            } while (n % 2 == 0);
            return n;
        }
    }

    static class Consumer implements Runnable {
        private final int id;

        Consumer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {

                while (consumerCount.get(id).get() < GOAL) {

                    if (buffer.isEmpty()) {
                        System.out.println("[Consumator " + id + "] Depozitul e gol, asteapta...");
                    }

                    int item = buffer.take();
                    consumerCount.get(id).incrementAndGet();
                    int consumed = totalConsumed.incrementAndGet();

                    System.out.println("[Consumator " + id + "] a consumat: "
                            + item + " | Cons. pers.: "
                            + consumerCount.get(id).get()
                            + " | Depozit: " + buffer.size()
                            + " | Total global: " + consumed);

                    Thread.sleep(300);
                }

                System.out.println("[Consumator " + id + "] s-a saturat cu " + GOAL + " obiecte.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
