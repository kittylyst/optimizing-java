package handsonmjc.parsing;

import java.util.Objects;

/**
 *
 * @author ben
 */
public final class CPEntry {
    private final short index;
    private final CPType type;
    private final Number num;
    private final String str;
    private final Ref ref;
    private final Ref ref2;

    private CPEntry(short i, CPType t, Number n, String s, Ref r, Ref r2) {
        index = i;
        type = t;
        num = n;
        str = s;
        ref = r;
        ref2 = r2;
    }

    public static CPEntry of(short i, CPType t, Number num) {
        return new CPEntry(i, t, num, num.toString(), null, null);
    }

    public static CPEntry of(short i, CPType t, String s) {
        return new CPEntry(i, t, null, s, null, null);
    }

    // Class reference: an uint16 within the constant pool to a UTF-8 string containing the fully qualified class name
    /**
     *
     * @param i
     * @param t
     * @param r
     * @return 
     */
    public static CPEntry of(short i, CPType t, Ref r) {
        return new CPEntry(i, t, null, null, r, null);
    }

    public static CPEntry of(short i, CPType t, Ref r, Ref r2) {
        return new CPEntry(i, t, null, null, r, r2);
    }

    public CPType getType() {
        return type;
    }

    public Number getNum() {
        return num;
    }

    public String getStr() {
        return str;
    }

    public Ref getRef() {
        return ref;
    }

    public Ref getRef2() {
        return ref2;
    }

    public short getIndex() {
        return index;
    }
        
    @Override
    public String toString() {
        return "CPEntry{" + "type=" + type + ", num=" + num + ", str=" + str + ", ref=" + ref + ", ref2=" + ref2 + '}';
    }
    
    
}
