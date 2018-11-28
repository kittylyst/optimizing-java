package optjava.agent;

import java.lang.instrument.Instrumentation;

/**
 *
 * @author kittylyst
 */
// tag::ALLOC_RECORDER[]
public class AllocAgent {
    public static void premain(String args, Instrumentation instrumentation) {
        AllocRewriter transformer = new AllocRewriter();
        instrumentation.addTransformer(transformer);
    }

}
// end::ALLOC_RECORDER[]