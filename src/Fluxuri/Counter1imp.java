package Fluxuri;

public class Counter1imp extends Thread {
    private int from, to, step;
    private int[] tablou;

    public Counter1imp(int from, int to, int step, int[] tablou) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.tablou = tablou;
    }

    public void run() {
        int i = from;
        int s1, s2, s;
        int produs = 0;
        int sumaprodus = 0;

        while (i != to) {
            if (tablou[i] % 2 != 0) {
                s1 = i;
                i += step;
                do {
                    if (tablou[i] % 2 != 0) {
                        s2 = i;
                        produs = tablou[s1] * tablou[s2];
                        sumaprodus += produs;
                        System.out.println(getName() + " : " + s1 + ", " + s2 +

                                "  Valori: " + tablou[s1] + ", " + tablou[s2] +
                                "  Produs: " + produs +
                                "  Suma: " + sumaprodus);

                        break;
                    }
                    i += step;
                } while (i != to);
            }
            i += step;
        }
    }
}

