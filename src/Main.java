public class Main {

    static int size = 100;
    static int counter = 0;
    static int[] tablou2 = new int[size];
    static int[] tablou1;

    static Thread1 th1 = new Thread1();
    static Thread2 th2 = new Thread2();
    static Thread3 th3 = new Thread3();
    static Thread4 th4 = new Thread4();

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread 1");
            int sum = 0;

            for (int i = 0; i < tablou1.length - 2; i += 4) {
                try { Thread.sleep(100); }
                catch (InterruptedException e) {
                    return;
                }

                int prod = tablou1[i] * tablou1[i + 2];
                sum += prod;
                System.out.println(currentThread().getName() +"   "+sum);
            }

            System.out.println(currentThread().getName() +"    " + sum);

            try {
                th4.join();
            }
            catch (InterruptedException e) {
                return;
            }


            String text = "Daniele  |   Mihail-Gheorgii";
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try { Thread.sleep(100); }
                catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println();
        }
    }


    public static class Thread2 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread 2");
            int sum = 0;

            for (int i = tablou1.length - 1; i >= 3; i -= 4) {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) { return; }

                int prod = tablou1[i] * tablou1[i - 2];
                sum += prod;
                System.out.println(currentThread().getName()+"    "+ sum);
            }
            System.out.println(currentThread().getName() + " " + "total sum: " + sum);

            try {
                th4.join();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }

            System.out.println("Spinei  |   Tulei");
        }
    }


    public static class Thread4 extends Thread {
        @Override
        public void run() {
            System.out.println("\nThread 4 nr: ");

            try { Thread.sleep(400); }
            catch (InterruptedException e) { return; }
//            System.out.print("Thread 4: ");
            for (int i = 908; i >= 123; i -= 1) {

                System.out.print(currentThread().getName() + " " + i + " ");
                try { Thread.sleep(30); }
                catch (InterruptedException e) { return; }
            }

            try { Thread.sleep(100); }
            catch (InterruptedException e) { return; }

            System.out.println();

            try {
                th2.interrupt();
                th2.join();
            }

            catch (InterruptedException e) {
                return;
            }

            String text = "Grupa CR-232";
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try { Thread.sleep(100); }
                catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println();
        }
    }


    public static class Thread3 extends Thread {
        @Override
        public void run() {
            System.out.print("Thread 3 nr: ");
            System.out.println("Thread 3: ");
            for (int i = 654; i <= 1278; i += 1) {

                System.out.print(this.currentThread().getName() + " " + i + " ");
                try { Thread.sleep(30); }
                catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println();

            try {
//                th1.interrupt();
                th1.join();
            }
            catch (InterruptedException e) {
                return;
            }

            String text = "Programarea Concurenta si Distribuita";
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try { Thread.sleep(100); }
                catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        System.out.println("Tablou:");
        for (int i = 0; i < 100; i++) {
            tablou2[i] = (int) Math.round((Math.random() * 100) + 15);
            System.out.print(tablou2[i] + " ");
            if (i == 50) {
                System.out.println();
            }
            if (tablou2[i] % 2 == 0) {
                counter++;
            }
        }

        System.out.println();

        tablou1 = new int[counter];
        int k = 0;

        for (int i = 0; i < 100; i++) {
            if (tablou2[i] % 2 == 0) {
                tablou1[k] = tablou2[i];
                k++;
            }
        }

        th1.start();
        th2.start();
        th3.start();
        th4.start();

        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
        } catch (InterruptedException e) {
            return;
        }
    }
}
