package handsonmjc.parsing;

/**
 * Represents a reference to another entry in the constant pool
 * 
 * @author ben
 */
public class Ref {

    private final int other;
    
    public Ref(int other) {
        this.other = other;
    }

    public int getOther() {
        return other;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.other;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Ref other = (Ref) obj;
        if (this.other != other.other)
            return false;
        return true;
    }
    
    
}
