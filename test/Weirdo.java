import java.io.IOException;

/**
 * This class implements the Appendable interface and does nothing but throws IOException for
 * testing purposes.
 */
public class Weirdo implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}

