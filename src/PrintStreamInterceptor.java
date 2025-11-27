import javax.swing.*;
import java.io.*;

public class PrintStreamInterceptor extends PrintStream {

    private JTextArea textArea;
    private PrintStream console;

    public PrintStreamInterceptor(JTextArea area) {
        super(new ByteArrayOutputStream());
        this.textArea = area;
        this.console = System.out;
    }

    @Override
    public void println(String s) {
        SwingUtilities.invokeLater(() -> textArea.append(s + "\n"));
        console.println(s);
    }

    public void connect() {
        System.setOut(this);
        System.setErr(this);
    }

    public void disconnect() {
        System.setOut(console);
        System.setErr(console);
    }
}
