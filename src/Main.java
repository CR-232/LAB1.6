import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame("Producator - Consumator");
        f.setSize(600, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        JTextArea log = new JTextArea();
        log.setEditable(false);
        JScrollPane scroll = new JScrollPane(log);
        f.add(scroll, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");
        stopBtn.setEnabled(false);

        panel.add(startBtn);
        panel.add(stopBtn);
        f.add(panel, BorderLayout.SOUTH);

        f.setVisible(true);

        startBtn.addActionListener(e -> {
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);

            log.append("Simulare pornita...\n");

            new Thread(() -> runSimulation(log, startBtn, stopBtn)).start();
        });

        stopBtn.addActionListener(e -> {
            log.append("Simulare oprita manual.\n");
            System.exit(0);
        });
    }

    public static void runSimulation(JTextArea log, JButton startBtn, JButton stopBtn) {

        int X = 2;
        int Y = 4;
        int Z = 4;
        int D = 7;

        Depozit d = new Depozit(D);

        Producer[] prod = new Producer[X];
        Consumer[] cons = new Consumer[Y];

        PrintStreamInterceptor out = new PrintStreamInterceptor(log);
        out.connect();

        for (int i = 0; i < X; i++) {
            prod[i] = new Producer("Producator_" + (i+1), d);
            prod[i].start();
        }

        for (int i = 0; i < Y; i++) {
            cons[i] = new Consumer("Consumator_" + (i+1), d, Z);
            cons[i].start();
        }

        for (Consumer c : cons) {
            try { c.join(); } catch (InterruptedException ex) {}
        }

        for (Producer p : prod) {
            p.stopProducer();
        }

        out.disconnect();

        log.append("\nToti consumatorii au terminat. Producatorii au fost opriti.\n");

        stopBtn.setEnabled(false);
        startBtn.setEnabled(true);
    }
}
