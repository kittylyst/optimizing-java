package handsonmjc.parsing;

/**
 *
 * @author ben
 */
public class OCMethod {

    private final String className;
    private final String nameAndType;
    private final byte[] bytecode;
    private final String signature;
    private final int flags;
    private int numParams = -1;

    public OCMethod(final String klassName, final String sig, final String nameType, final int fls, final byte[] buf) {
        signature = sig;
        nameAndType = nameType;
        bytecode = buf;
        flags = fls;
        className = klassName;
    }

    public String getClassName() {
        return className;
    }

    public String getNameAndType() {
        return nameAndType;
    }

    public String getSignature() {
        return signature;
    }

    public int getFlags() {
        return flags;
    }
    
    public int numParams() {
        if (numParams > -1)
            return numParams;
        numParams = 0;
        OUTER:
        for (char c : signature.toCharArray()) {
            switch (c) {
                case '(':
                    break;
                case 'Z':
                case 'B':
                case 'S':
                case 'C':
                case 'I':
                case 'J':
                case 'F':
                case 'D':
                    numParams++;
                    break;
                case 'L':
                    // FIXME Parse type
                    throw new IllegalStateException("Class parameters not implemented yet");
                case ')':
                    break OUTER;
                default:
                    throw new IllegalStateException("Saw illegal char: " + c + " as type descriptors");
            }
        }
        return numParams;
    }

    public byte[] getBytecode() {
        return bytecode;
    }

}
