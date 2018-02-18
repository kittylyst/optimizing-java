package handsonmjc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author ben
 */
public class AsynchIO {

    public static void main(String[] args) {
    }

    public static void futureStyle() {
        Path file = Paths.get("/tmp/hugeFile.txt");
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(file)) {
            ByteBuffer buffer = ByteBuffer.allocate(10_000_000);
            Future<Integer> result = channel.read(buffer, 0);
            while (!result.isDone()) {
                doSomethingElse();
            }
            Integer bytesRead = result.get();
            System.out.println("Bytes read [" + bytesRead + "]");
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void callbackStyle() {
        Path file = Paths.get("/tmp/hugeFile.txt");
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(file)) {
            ByteBuffer buffer = ByteBuffer.allocate(10_000_000);

            channel.read(buffer, 0, buffer,
                    new CompletionHandler<Integer, ByteBuffer>() {
                        public void completed(Integer result, ByteBuffer attachment) {
                            System.out.println("Bytes read [" + result + "]");
                        }

                        public void failed(Throwable fail, ByteBuffer attachment) {
                            fail.printStackTrace();
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void doSomethingElse() {
        // Maybe just sleep
    }

}
