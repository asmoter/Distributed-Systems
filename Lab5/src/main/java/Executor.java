import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Executor {

    private String address;
    private String znode;
    private String exec;

    private Process process;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // /snap/bin/spotify

    public static void main(String[] args) throws IOException {

        String address = "localhost";
        String znode = "/z";

        System.out.println("Path to executable file: ");
        String exec = br.readLine();

        new Executor(address, znode, exec).start();
    }

    public Executor(String address, String znode, String program) {
        this.address = address;
        this.znode = znode;
        this.exec = program;
    }

    public void start() throws IOException {

        DataMonitor dataMonitor = new DataMonitor(address, znode, exists -> {
            if (exists)
                startProcess();
            else {
                stopProcess();
            }
        });
        System.out.println("Enter:" +
                "\n-> tree - to see tree structure" +
                "\n-> count - to get current number of children" +
                "\n-> q - to exit the program");
        while (true) {
            String line = br.readLine();
            if (line.equals("tree")) {
                System.out.println("Tree structure: ");
                dataMonitor.printTree(znode);
            } else if(line.equals("count")){
                int counter = dataMonitor.countChildren(znode) - 1;
                System.out.println("Current children number: " + counter);
            }
            else if (line.equals("q")) {
                stopProcess();
                System.exit(0);
            }
        }
    }

    public void startProcess() {
        if (process == null) {
            ProcessBuilder process = new ProcessBuilder(exec);
            try {
                System.out.println("Start program");
                this.process = process.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopProcess() {
        if (process != null) {
            System.out.println("Kill process");
            process.destroy();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            process = null;
        }
    }
}
