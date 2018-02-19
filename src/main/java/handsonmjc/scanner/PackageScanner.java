package handsonmjc.scanner;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 *
 */
public final class PackageScanner {

    private static final String USAGE_STRING = "Usage: PackageScanner <classes root dir> <package to scan>";

    private final SortedSet<String> nativeMethods = new TreeSet<>();

    // FIXME
    private final BlockingQueue<MethodScanner> downstream = null;
    
    private final String basePath;
    private final String packageName;

    PackageScanner(final String classesRoot, final String packageToScan) {
        basePath = classesRoot;
        packageName = packageToScan;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            usage();
            System.exit(1);
        }

        final PackageScanner scanner = new PackageScanner(args[0], args[1]);
        scanner.scanDir();
        scanner.printResults();
    }

    void printResults() {
        for (String method : nativeMethods) {
            System.out.println(method);
        }
        System.out.println("Total native methods in package "+ packageName +": " + nativeMethods.size());
    }

    private static void usage() {
        System.err.println(USAGE_STRING);
    }

    /**
    
     @param basePath
     @param packageName
     @throws IOException 
     */
    void scanDir() throws IOException {
        final Path baseDir = Paths.get(basePath);
        final Path scanDir = Paths.get(basePath, convertQualifiedClassNameToInternalForm(packageName));
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(scanDir, "*.class")) {
            for (Path p : stream) {
                final String className = baseDir.relativize(p).toString().replace(".class", "");
                final MethodScanner scanner = new MethodScanner(className, p, nativeMethods);
                try {
                     System.out.println(scanner);
                    downstream.put(scanner);
                } catch (InterruptedException ex) {
                    // WHAT DO WE DO HERE ????
                }
            }
        }
        //
        // KICK OFF SCANNER MANAGER
        // 
    }

    public SortedSet<String> getNativeMethods() {
        return nativeMethods;
    }

    /**
     * Helper method that converts from a fully-qualified class name to the internal class
     * name format (as used in the Constant Pool). No obvious library method to do this
     * appears to exist, hence this code. If one exists, rip this out.
     * @param qualifiedClassName
     * @return 
     */
    public static String convertQualifiedClassNameToInternalForm(final String qualifiedClassName) {
        String out = qualifiedClassName.replaceAll("\\.", "/");
        // Doesn't attempt to deal with inner classes etc yet...
        return out;
    }

    /**
     * Helper method that converts from the internal class name format (as used in the 
     * Constant Pool) to a fully-qualified class name. No obvious library method to do this
     * appears to exist, hence this code. If one exists, rip this out.
     * @param classInternalName
     * @return 
     */
    public static String convertInternalFormToQualifiedClassName(final String classInternalName) {
        String out = classInternalName.replaceAll("/", "\\.");
        // Doesn't attempt to deal with inner classes etc yet...
        return out;
    }

}
