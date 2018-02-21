package handsonmjc.scanner;

import handsonmjc.parsing.OCKlass;
import handsonmjc.parsing.OCKlassParser;
import handsonmjc.parsing.OCMethod;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author ben
 */
public class MethodScanner implements Runnable {

    private final String className;
    private final Path path;
    private final CopyOnWriteArraySet<String> writeTo;
    private BlockingQueue<MethodScanner> queue;

    public MethodScanner(String name, Path p, CopyOnWriteArraySet<String> output) {
        className = name;
        path = p;
        writeTo = output;
    }

    public void run() {
        try {
            // Get file bytes from disc
            byte[] buffy = Files.readAllBytes(path);

            // Scan the bytes & construct a class object
            OCKlass klass = OCKlassParser.of(buffy, className);

            // Find all native methods using the parsed klass object & write them to the output set
            for (OCMethod m : klass.getMethods()) {
                if ((m.getFlags() & OCKlassParser.ACC_NATIVE) > 0) {
                    writeTo.add(m.getClassName() + "." + m.getNameAndType());
                }
            }

            for (String name : klass.getKlassNames()) {
                System.out.println("Class found: " + name);
                String relClassName = className + ".class";
                
                Path base = Paths.get(relClassName).relativize(path);
                System.out.println("Base: " + base);
                MethodScanner ms = new MethodScanner(name, path, writeTo);
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Could not parse: " + path);
        } catch (IOException ex) {
            System.err.println("Could not open: " + path);
        }

    }

    @Override
    public String toString() {
        return "MethodScanner{" + "className=" + className + ", path=" + path + '}';
    }

    public void setQueue(BlockingQueue<MethodScanner> forProcessing) {
        queue = forProcessing;
    }

}
