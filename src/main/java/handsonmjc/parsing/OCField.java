package handsonmjc.parsing;

public class OCField {

    private final String name;
    private final JVMType type;
    private final int flags;
    private final OCKlass klass;
    
    public OCField(final OCKlass klz, final String name, final JVMType type, final int flags) {
        klass = klz;
        this.name = name;
        this.type = type;
        this.flags = flags;
    }

    public String getName() {
        return name;
    }

    public JVMType getType() {
        return type;
    }

    public int getFlags() {
        return flags;
    }

    public OCKlass getKlass() {
        return klass;
    }
}
