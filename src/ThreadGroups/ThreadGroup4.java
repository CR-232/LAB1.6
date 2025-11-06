package ThreadGroups;

public class ThreadGroup4 extends ThreadGroup {

    public ThreadGroup4(String name) {
        super(name);
        main();
    }

    public ThreadGroup4(ThreadGroup parent, String name) {

        super(parent, name);
        main();
    }

    public void main() {
        ThreadGroup1 G1 = new ThreadGroup1(this, "G1");
        this.list();

    }
}

