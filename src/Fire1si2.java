import java.util.concurrent.Semaphore;

public class Fire1si2 {
    static int size = 20;
    static int[] a = new int[size];
    static Semaphore sem = new Semaphore(1);
    static Object lock = new Object();

    static class Th1 extends Thread {
        @Override
        public void run() {
            System.out.println("Th1: începe calculul de la inceput");

            try {
                sem.acquire();
                int sum = 0;
                for (int i = 0; i < a.length - 2; i += 4) {
                    if (i % 2 == 0) {
                        int prod = a[i] * a[i + 2];
                        sum += prod;
                        System.out.println("Th1 produs: " + a[i] + "*" + a[i + 2] + " = " + prod);
                        Thread.sleep(100);
                    }
                }
                System.out.println("Th1 suma totala: " + sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }

            synchronized (lock) {
                try {
                    String prenume = "Mihail-Gheorghii";
                    for (char c : prenume.toCharArray()) {
                        System.out.print(c);
                        Thread.sleep(200);
                    }
                    System.out.println();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    try {
                        String prenume = "Daniele";
                        for (char c : prenume.toCharArray()) {
                            System.out.print(c);
                            Thread.sleep(400);
                        }
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Th2 extends Thread {
        @Override
        public void run() {
            System.out.println("Th2: incepe calculul de la sfarsit");

            try {
                sem.acquire();
                int sum = 0;
                for (int i = a.length - 1; i > 1; i -= 4) {
                    if (i % 2 == 0) {
                        int prod = a[i] * a[i - 2];
                        sum += prod;
                        System.out.println("Th2 produs: " + a[i] + "*" + a[i - 2] + " = " + prod);
                        Thread.sleep(100);
                    }
                }
                System.out.println("Th2 suma totală: " + sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }

            synchronized (lock) {
                try {
                    String nume = "Tulei";
                    for (char c : nume.toCharArray()) {
                        System.out.print(c);
                        Thread.sleep(300);
                    }
                    System.out.println();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                synchronized (lock) {
                    try {
                        String nume = "Spinei";
                        for (char c : nume.toCharArray()) {
                            System.out.print(c);
                            Thread.sleep(500);
                        }
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    }

    public void startAll() {

        System.out.println("Numere generate:");
        for (int i = 0; i < size; i++) {
            a[i] = (int) (Math.random() * 50 + 1);
            System.out.print(a[i] + " ");
        }
        System.out.println("\n");

        Thread th1 = new Th1();
        Thread th2 = new Th2();

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFire Th1 și Th2 au terminat execuția.");
    }
}
