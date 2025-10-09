package Fluxuri;

public class Counter2imp extends Thread {
    private int from, to, step;
    private int[] tablou;

    public Counter2imp(int from, int to, int step, int[] tablou) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.tablou = tablou;
    }

    @Override
    public void run() {
        int i = from;
        int p1, p2, s;
        int sumapozitii = 0;

        // Th3 / Th4: Suma pozitiilor unde sunt valori impare (două câte două)
        while (i != to) {
            if (tablou[i] % 2 != 0) {
                p1 = i;
                i += step;
                do {
                    if (tablou[i] % 2 != 0) {
                        p2 = i;
                        s = p1 + p2; // sumăm pozițiile (nu valorile)
                        sumapozitii += s;
                        System.out.println(getName() + " -> (" + p1 + "," + p2 +
                                ")  valori: " + tablou[p1] + "," + tablou[p2] +
                                "  suma poz: " + s +
                                "  total: " + sumapozitii);
                        break;
                    }
                    i += step;
                } while (i != to);
            }
            i += step;
        }

        System.out.println(getName() + " → Suma totală a pozițiilor = " + sumapozitii);
    }
}
