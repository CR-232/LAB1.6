public class Fire1si2 {
    static int size = 99;
    static int[] a = new int[size];

    static volatile boolean th1CalcDone = false;
    static volatile boolean th2CalcDone = false;
    static volatile boolean th1PrintedNames = false;

    static class Th1 extends Thread {
        @Override
        public void run() {
            System.out.println("Th1: începe calculul de la început");

            int sum = 0;
            for (int i = 0; i < a.length - 2; i += 4) {
                if (i % 2 == 0) {
                    int prod = a[i] * a[i + 2];
                    sum += prod;
                    System.out.println("Th1 produs: " + a[i] + " * " + a[i + 2] + " = " + prod);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Th1 întrerupt în timpul somnului.");
                        Thread.currentThread().interrupt();
                        break;
                    }
                    Thread.yield();
                }
            }
            System.out.println("Th1 suma totală: " + sum);
            th1CalcDone = true;

            while (!th2CalcDone) {
                Thread.yield();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Th1 întrerupt în așteptare.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            // Afișare prenume
            afisareText("Mihail-Gheorghii", 100);
            afisareText("Daniele", 100);

            th1PrintedNames = true;
        }

        private void afisareText(String text, int delay) {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("Th1 întrerupt la afișarea textului.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println();
        }
    }

    static class Th2 extends Thread {
        @Override
        public void run() {
            System.out.println("Th2: începe calculul de la sfârșit");

            int sum = 0;
            for (int i = a.length - 1; i > 1; i -= 4) {
                if (i % 2 == 0) {
                    int prod = a[i] * a[i - 2];
                    sum += prod;
                    System.out.println("Th2 produs: " + a[i] + " * " + a[i - 2] + " = " + prod);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Th2 întrerupt în timpul somnului.");
                        Thread.currentThread().interrupt();
                        break;
                    }
                    Thread.yield();
                }
            }
            System.out.println("Th2 suma totală: " + sum);
            th2CalcDone = true;

            while (!th1PrintedNames) {
                Thread.yield();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Th2 întrerupt în așteptare.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            // Afișare nume
            afisareText("Tulei", 100);
            afisareText("Spinei", 100);
        }

        private void afisareText(String text, int delay) {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("Th2 întrerupt la afișarea textului.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println();
        }
    }

    public void startAll() {
        System.out.println("Numere generate:");
        for (int i = 0; i < size; i++) {
            a[i] = (int) (Math.random() * 99 + 1);
            System.out.print(a[i] + " ");
        }
        System.out.println("\n");

        Th1 th1 = new Th1();
        Th2 th2 = new Th2();

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            System.out.println("Execuția principală a fost întreruptă.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\nFirele Th1 și Th2 au terminat execuția complet.");
    }

    public static void main(String[] args) {
        new Fire1si2().startAll();
    }
}
