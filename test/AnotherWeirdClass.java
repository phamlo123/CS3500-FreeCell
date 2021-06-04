import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This class implements the Readable interface and does nothing but throws IOException for
 * testing purposes.
 */
public class AnotherWeirdClass implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
