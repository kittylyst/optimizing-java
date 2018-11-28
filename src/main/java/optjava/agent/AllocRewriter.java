package optjava.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import org.objectweb.asm.*;

/**
 *
 * @author kittylyst
 */
// tag::ALLOC_RECORDER[]
public class AllocRewriter implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> redef, ProtectionDomain pd, byte[] bytes) throws IllegalClassFormatException {
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        ClassVisitor coster = new ClassVisitor(Opcodes.ASM5, writer) {
            @Override
            public MethodVisitor visitMethod(int ax, String name, String desc, String sig, String[] ex) {
                MethodVisitor baseMethodVisitor = super.visitMethod(ax, name, desc, sig, ex);
                return new AllocationRecordingMethodVisitor(baseMethodVisitor, ax, name, desc);
            }
        };
        reader.accept(coster, ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
    }

}
// end::ALLOC_RECORDER[]