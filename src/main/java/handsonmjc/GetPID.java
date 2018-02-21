package handsonmjc;

import java.lang.management.ManagementFactory;

/**
 * Java 8 GetPID class
 * 
 * @author ben
 */
public class GetPID {

    public static long getPid() {
        // ManagementFactory.getRuntimeMXBean().getName() returns the name that 
        // represents the currently running JVM. On Sun and Oracle JVMs, this 
        // name is in the format <pid>@<hostname>.

        final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        final int index = jvmName.indexOf('@');
        if (index < 1)
            return 0;

        try {
            return Long.parseLong(jvmName.substring(0, index));
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }
}
