package optjava.prac;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Inlining {

    private int count = 0;

    public static void main(String[] args) {
        Inlining i = new Inlining();
        i.run();
    }

    private void run() {
        for (int i = 0; i < 50_000; i++) {
            run2();
        }
    }

    private void run2() {
        run3();
    }

    private void run3() {
        run4();
    }

    private void run4() {
        run5();
    }

    private void run5() {
        count++;
        files();
        if (count > 15_000 && (count % 1000 == 0)) {
            Exception e = new Exception();
            e.printStackTrace(System.err);
        }
    }

    private void files() {
        try (InputStream in = Files.newInputStream(Paths.get("/etc/passwd"));
             BufferedInputStream bis = new BufferedInputStream(in);
             InputStreamReader r = new InputStreamReader(bis);
             BufferedReader br = new BufferedReader(r);
        ) {
            System.err.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
