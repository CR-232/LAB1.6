package App;

import Fluxuri.Counter1imp;
import Fluxuri.Counter2imp;
import Fluxuri.Thread56;

public class Main {
    public static void main(String[] args) {
        // Creăm tabloul comun
        int[] tablou = new int[101];

        // Generăm valori între 0–98
        for (int i = 0; i < 100; i++) {
            tablou[i] = (int) (Math.random() * 99 + 1);
            System.out.print(tablou[i] + " ");
            if (i==50) {
                System.out.println();
            }
        }
        System.out.println("\n");

        // Th1 și Th2
        Counter1imp th1 = new Counter1imp(0, 99, 1, tablou);
        Counter1imp th2 = new Counter1imp(99, 0, -1, tablou);
        th1.setName("Th1");
        th2.setName("Th2");

        // Th3 și Th4
        Counter2imp th3 = new Counter2imp(0, 99, 1, tablou);
        Counter2imp th4 = new Counter2imp(99, 0, -1, tablou);
        th3.setName("Th3");
        th4.setName("Th4");

        // Thread 5 si Thread 6
        Thread56 th5 = new Thread56(0, 99, 1, tablou);
        Thread56 th6 = new Thread56(99, 0, -1, tablou);
        th5.setName("Thread 5");
        th6.setName("Thread 6");



        // Pornim toate firele
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        th6.start();

        System.out.println("\n");
        // Așteptăm terminarea
        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
            th5.join();
            th6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Textul final (cu întârziere între litere)
        String text = "Lucrarea realizata de: \n Tulei Mihail (Th1/Th2) \n Cuturov Oleg ( Th3/Th4 ) \n Spinei Daniele (Th5/Th6) \n CR-232";
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
            Thread.sleep(100);
            } catch (InterruptedException ignored)
            {

            }
        }
        System.out.println();
    }
}
