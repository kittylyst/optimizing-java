package handsonmjc.scanner;

import handsonmjc.parsing.OCKlass;
import handsonmjc.parsing.OCKlassParser;
import java.nio.file.Path;
import java.util.Set;

/**
 *
 * @author ben
 */
public class MethodScanner implements Runnable {

    private final String className;
    private final Path path;

    public MethodScanner(String name, Path p, Set<String> output) {
        className = name;
        path = p;
        // Save variables
    }

    public void run() {
        // Get file bytes from disc

        try {
            // Scan the bytes & construct a class object
            OCKlass klass = OCKlassParser.of(null, className);

            // Find all native methods using the parsed klass object & write them to the output set
            
            // 
            //  WHAT OTHER CASES DO WE NEED TO COVER ???
            //
        } catch (ClassNotFoundException ex) {
            // Do something here...
        }

    }

    @Override
    public String toString() {
        return "MethodScanner{" + "className=" + className + ", path=" + path + '}';
    }

    
}
