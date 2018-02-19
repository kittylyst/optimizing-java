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

    public MethodScanner(String name, Path p, Set<String> output) {
        className = name;
        // Save variables
    }

    public void run() {
        // Get file bytes from disc

        try {
            // Scan it
            OCKlass klass = OCKlassParser.of(null, className);

            // Find all native methods using the parsed klass object & write them to the output set
            
            // 
            //  WHAT OTHER CASES DO WE NEED TO COVER ???
            //
        } catch (ClassNotFoundException ex) {
            // Do something here...
        }

    }

}
