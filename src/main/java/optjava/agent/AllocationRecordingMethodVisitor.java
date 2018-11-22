package optjava.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.GeneratorAdapter;

/**
 *
 * @author kittylyst
 */
// tag::ALLOC_RECORDER[]
public final class AllocationRecordingMethodVisitor extends GeneratorAdapter {
    private final String runtimeAccounterTypeName = "optjava/bc/RuntimeCostAccounter";

    public AllocationRecordingMethodVisitor(MethodVisitor methodVisitor, int access, String name, String desc) {
        super(Opcodes.ASM5, methodVisitor, access, name, desc);
    }

    /**
     * This method is called when visiting an opcode with a single int operand.
     * For our purposes this is a NEWARRAY opcode.
     *
     * @param opcode
     * @param operand 
     */
    @Override
    public void visitIntInsn(final int opcode, final int operand) {
        if (opcode != Opcodes.NEWARRAY) {
            super.visitIntInsn(opcode, operand);
            return;
        }

        // Opcode is NEWARRAY - recordArrayAllocation:(Ljava/lang/String;I)V
        // operand value should be one of Opcodes.T_BOOLEAN, 
        // Opcodes.T_CHAR, Opcodes.T_FLOAT, Opcodes.T_DOUBLE, Opcodes.T_BYTE, 
        // Opcodes.T_SHORT, Opcodes.T_INT or Opcodes.T_LONG.
        final int typeSize;
        switch (operand) {
            case Opcodes.T_BOOLEAN:
            case Opcodes.T_BYTE:
                typeSize = 1;
                break;
            case Opcodes.T_SHORT:
            case Opcodes.T_CHAR:
                typeSize = 2;
                break;
            case Opcodes.T_INT:
            case Opcodes.T_FLOAT:
                typeSize = 4;
                break;
            case Opcodes.T_LONG:
            case Opcodes.T_DOUBLE:
                typeSize = 8;
                break;
            default:
                throw new IllegalStateException("Illegal operand to NEWARRAY seen: " + operand);
        }
        super.visitInsn(Opcodes.DUP);
        super.visitLdcInsn(typeSize);
        super.visitMethodInsn(Opcodes.INVOKESTATIC, runtimeAccounterTypeName, "recordArrayAllocation", "(II)V", true);
        super.visitIntInsn(opcode, operand);
    }

    /**
     * This method is called when visiting an opcode with a single operand, that
     * is a type (represented here as a String).
     *
     * For our purposes this is either a NEW opcode or a ANEWARRAY
     *
     * @param opcode 
     * @param type 
     */
    @Override
    public void visitTypeInsn(final int opcode, final String type) {
        // opcode is either NEW - recordAllocation:(Ljava/lang/String;)V
        // or ANEWARRAY - recordArrayAllocation:(Ljava/lang/String;I)V
        switch (opcode) {
            case Opcodes.NEW:
                super.visitLdcInsn(type);
                super.visitMethodInsn(Opcodes.INVOKESTATIC, runtimeAccounterTypeName, "recordAllocation", "(Ljava/lang/String;)V", true);
                break;
            case Opcodes.ANEWARRAY:
                super.visitInsn(Opcodes.DUP);
                super.visitLdcInsn(8);
                super.visitMethodInsn(Opcodes.INVOKESTATIC, runtimeAccounterTypeName, "recordArrayAllocation", "(II)V", true);
                break;
        }

        super.visitTypeInsn(opcode, type);
    }
}
// end::ALLOC_RECORDER[]