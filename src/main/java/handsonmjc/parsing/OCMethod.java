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
    private int numParams = -1;

//    private static final byte[] JUST_RETURN = {RETURN.B()};
//    
//    public static final OCMethod OBJ_INIT = new OCMethod("java/lang/Object", "()V", "<init>:()V", JUST_RETURN);
    
    public OCMethod(final String klassName, final String sig, final String nameType, final byte[] buf) {
        signature = sig;
        nameAndType = nameType;
        bytecode = buf;
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
