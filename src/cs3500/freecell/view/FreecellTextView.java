package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModelState;
import java.io.IOException;

/**
 * This class implements the FreecellView interface, serving the purpose of creating a String
 * representation of the current state of the Freecell model.
 */
public class FreecellTextView implements FreecellView {

  private final FreecellModelState<?> model;
  private Appendable ap;

  /**
   * Constructing an instance of the class FreecellTextView, with the Appendable object set to
   * null.
   *
   * @param model model of the game Freecel that the methods will be getting information from.
   */
  public FreecellTextView(FreecellModelState<?> model) {
    this.model = model;
    this.ap = null;
  }

  /**
   * Constructing an instance of the class FreecellTextView, with two arguments model and ap.
   *
   * @param model model of the game Freecel that the methods will be getting information from.
   * @param ap    an Appendable object that the methods in this object append its result onto.
   */
  public FreecellTextView(FreecellModelState<?> model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }


  @Override
  public String toString() {
    try {
      StringBuilder temp = new StringBuilder();
      temp.append(foundationToString());
      temp.append(openToString());
      temp.append(cascadeToString());
      String main = temp.substring(0);
      return main;
    } catch (IllegalStateException e) {
      return "";
    }
  }

  @Override
  public void renderBoard() throws IOException {
    if (ap == null) {
      System.out.println(this);
    } else {
      try {
        ap.append(this.toString());
      } catch (IOException e) {
        throw new IOException("Appendable Fail");
      }
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (ap == null) {
      System.out.println(message);
    } else {
      try {
        ap.append(message);
      } catch (IOException e) {
        throw new IOException("Appendable Fail");
      }
    }
  }

  /**
   * Helps contructing a StringBuilder representing four foundation piles of the current state of
   * the the model for the game Freecell.
   *
   * @return a StringBuilder that represents the four foundation piles and the cards (if any) inside
   *         them.
   */
  private StringBuilder foundationToString() {
    int numFoundationPiles = 4;
    StringBuilder temp = new StringBuilder();
    for (int i = 0; i < numFoundationPiles; i++) {
      if (model.getNumCardsInFoundationPile(i) == 0) {
        temp.append(String.format("F%d:", i + 1));
      } else {
        temp.append(String.format("F%d: ", i + 1));
        for (int j = 0; j < model.getNumCardsInFoundationPile(i); j++) {
          temp.append(model.getFoundationCardAt(i, j));
          if (j != model.getNumCardsInFoundationPile(i) - 1) {
            temp.append(", ");
          }
        }
      }
      temp.append(System.getProperty("line.separator"));
    }
    return temp;
  }

  /**
   * Helps contructing a StringBuilder representing the open piles of the current state of the model
   * for the game Freecell.
   *
   * @return a StringBuilder that represents the the open piles and the cards (if any) inside them.
   */
  private StringBuilder openToString() {
    int numOpenPiles = model.getNumOpenPiles();
    StringBuilder temp = new StringBuilder();
    for (int i = 0; i < numOpenPiles; i++) {
      if (model.getNumCardsInOpenPile(i) == 0) {
        temp.append(String.format("O%d:", i + 1));
      } else {
        temp.append(String.format("O%d: ", i + 1));
        temp.append(model.getOpenCardAt(i));
      }
      temp.append(System.getProperty("line.separator"));
    }
    return temp;
  }

  /**
   * Helps contructing a StringBuilder representing the Cascade piles of the current state of the
   * model for the game Freecell.
   *
   * @return a StringBuilder that represents the the Cascade piles and the cards (if any) inside
   *         them.
   */
  private StringBuilder cascadeToString() {
    int numCascadePiles = model.getNumCascadePiles();
    StringBuilder temp = new StringBuilder();
    for (int i = 0; i < numCascadePiles; i++) {
      if (model.getNumCardsInCascadePile(i) == 0) {
        temp.append(String.format("C%d:", i + 1));
      } else {
        temp.append(String.format("C%d: ", i + 1));
        for (int j = 0; j < model.getNumCardsInCascadePile(i); j++) {
          temp.append(model.getCascadeCardAt(i, j));
          if (j != model.getNumCardsInCascadePile(i) - 1) {
            temp.append(", ");
          }
        }
      }
      if (i != numCascadePiles - 1) {
        temp.append(System.getProperty("line.separator"));
      }
    }
    return temp;
  }
}

