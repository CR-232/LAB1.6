package Fluxuri;

// conditia 1;
// Sumele produselor numerelor de pe
//poziţii impare două câte două începând
//căutarea și sumarea cu primul element

// conditia 2:
// Sumele produselor numerelor de pe poziţii
//impare două câte două începând căutarea și
//sumarea cu ultimul element

public class Thread56 extends Thread {
    private int from, to, step;
    private int[] tablou;

    public Thread56(int from, int to, int step, int[] tablou) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.tablou = tablou;
    }

    @Override
    public void run() {
        int i = from;
        int p1, p2, s = 0;


        while (i != to) {

            if (i == 101 || i == -1) {
                break;
            }

            if (tablou[i] % 2 != 0) {
                p1 = i;
                i += step;

                do {

                    if (tablou[i] % 2 != 0) {
                        p2 = i;
                        s += p2*p1;
                        System.out.println(getName() + ": (" + p1 + "," + p2 +
                                ")  valori: (" + tablou[p1] + "," + tablou[p2] +
                                ")  total: " + s);
                        break;
                    }
                    i += step;
                } while (i != to);
            }
            i += step;
        }

        System.out.println(getName() + ": Suma total = " + s);
    }
}
