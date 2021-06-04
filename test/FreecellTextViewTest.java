
import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import org.junit.Test;

/**
 * This class contains tests for the class FreecellTextView.
 */
public class FreecellTextViewTest {

  FreecellModel<Card> model = new SimpleFreecellModel();
  List<Card> deck = model.getDeck();
  FreecellView view = new FreecellTextView(model);


  @Test
  public void testViewNotStarted() {
    FreecellModel<Card> testModel = new SimpleFreecellModel();
    try {
      testModel.startGame(testModel.getDeck(), 1, 1, false);
    } catch (IllegalArgumentException e) {
      FreecellView viewTest = new FreecellTextView(testModel);
      assertEquals("", viewTest.toString());
    }
  }

  @Test
  public void testView13CascadePiles() {
    model.startGame(deck, 13, 4, false);
    assertEquals(forTest(), view.toString());
  }

  @Test
  public void testView4CascadePiles() {
    model.startGame(deck, 4, 4, false);
    assertEquals(forTest2(), view.toString());
  }

  @Test
  public void testView1InOpen() {
    model.startGame(deck, 4, 4, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(forTest3(), view.toString());
  }

  @Test
  public void testView1InFOUNDATION() {
    model.startGame(deck, 13, 4, false);
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);

    assertEquals(forTest4(), view.toString());
  }

  FreecellModel modelTest = new SimpleFreecellModel();

  @Test
  public void testRenderMessage() throws IOException {
    FreecellModel modelTest = new SimpleFreecellModel();
    Appendable ap = new StringBuilder();
    FreecellView testView = new FreecellTextView(modelTest, ap);
    testView.renderMessage("Nothing");
    assertEquals("Nothing", ap.toString());
  }

  @Test
  public void TestRenderBoard() throws IOException {
    FreecellModel modelTest = new SimpleFreecellModel();
    modelTest.startGame(modelTest.getDeck(), 13, 4, false);
    Appendable ap = new StringBuilder();
    FreecellView testView = new FreecellTextView(modelTest, ap);
    testView.renderBoard();
    assertEquals(forTest(), ap.toString());
  }

  @Test
  public void testNullConstructor() throws IOException {
    FreecellView view = new FreecellTextView(modelTest);
    ByteArrayOutputStream ap = new ByteArrayOutputStream();
    PrintStream ad = new PrintStream(ap);
    System.setOut(ad);
    view.renderMessage("ABC");
    assertEquals("ABC\n", ap.toString());
  }

  @Test
  public void testIOExceptionRenderMessage() {
    Appendable failer = new Weirdo();
    FreecellView view = new FreecellTextView(modelTest, failer);
    ByteArrayOutputStream ap = new ByteArrayOutputStream();
    PrintStream ad = new PrintStream(ap);
    System.setOut(ad);
    try {
      view.renderMessage("ABC");
    } catch (IOException e) {
      assertEquals("Appendable Fail", e.getMessage());
    }
  }

  @Test
  public void testIOExceptionRenderBoard() {
    Appendable failer = new Weirdo();
    FreecellView view = new FreecellTextView(modelTest, failer);
    ByteArrayOutputStream ap = new ByteArrayOutputStream();
    PrintStream ad = new PrintStream(ap);
    System.setOut(ad);
    try {
      view.renderBoard();
    } catch (IOException e) {
      assertEquals("Appendable Fail", e.getMessage());
    }
  }


  @Test
  public void testViewNotStartedComplexModel() {
    FreecellModel<Card> testModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    try {
      testModel.startGame(testModel.getDeck(), 1, 1, false);
    } catch (IllegalArgumentException e) {
      FreecellView viewTest = new FreecellTextView(testModel);
      assertEquals("", viewTest.toString());
    }
  }

  @Test
  public void testView13CascadePilesComplex() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    FreecellView view = new FreecellTextView(complexModel);
    complexModel.startGame(deck, 13, 4, false);

    assertEquals(forTest(), view.toString());
  }

  @Test
  public void testView4CascadePilesComplex() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    FreecellView view = new FreecellTextView(complexModel);
    complexModel.startGame(deck, 4, 4, false);
    assertEquals(forTest2(), view.toString());
  }

  @Test
  public void testView1InOpenComplex() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    FreecellView view = new FreecellTextView(complexModel);
    complexModel.startGame(deck, 4, 4, false);
    complexModel.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(forTest3(), view.toString());
  }

  @Test
  public void testView1InFOUNDATIONComplex() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    FreecellView view = new FreecellTextView(complexModel);

    complexModel.startGame(deck, 13, 4, false);
    complexModel.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
    complexModel.move(PileType.CASCADE, 0, 2, PileType.OPEN, 1);
    complexModel.move(PileType.CASCADE, 0, 1, PileType.OPEN, 2);
    complexModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);

    assertEquals(forTest4(), view.toString());
  }


  @Test
  public void testRenderMessageComplex() throws IOException {
    FreecellModel modelTest = FreecellModelCreator.create(GameType.MULTIMOVE);
    Appendable ap = new StringBuilder();
    FreecellView testView = new FreecellTextView(modelTest, ap);
    testView.renderMessage("Nothing");
    assertEquals("Nothing", ap.toString());
  }

  @Test
  public void TestRenderBoardComplex() throws IOException {
    FreecellModel modelTest = FreecellModelCreator.create(GameType.MULTIMOVE);
    modelTest.startGame(modelTest.getDeck(), 13, 4, false);
    Appendable ap = new StringBuilder();
    FreecellView testView = new FreecellTextView(modelTest, ap);
    testView.renderBoard();
    assertEquals(forTest(), ap.toString());
  }

  @Test
  public void testNullConstructorComplex() throws IOException {
    FreecellView view = new FreecellTextView(FreecellModelCreator.create(GameType.MULTIMOVE));
    ByteArrayOutputStream ap = new ByteArrayOutputStream();
    PrintStream ad = new PrintStream(ap);
    System.setOut(ad);
    view.renderMessage("ABC");
    assertEquals("ABC\n", ap.toString());
  }

  @Test
  public void testIOExceptionRenderMessageComplex() {
    Appendable failer = new Weirdo();
    FreecellView view = new FreecellTextView(FreecellModelCreator.create(GameType.MULTIMOVE),
        failer);
    ByteArrayOutputStream ap = new ByteArrayOutputStream();
    PrintStream ad = new PrintStream(ap);
    System.setOut(ad);
    try {
      view.renderMessage("ABC");
    } catch (IOException e) {
      assertEquals("Appendable Fail", e.getMessage());
    }
  }

  @Test
  public void testIOExceptionRenderBoardComplex() {
    Appendable failer = new Weirdo();
    FreecellView view = new FreecellTextView(FreecellModelCreator.create(GameType.MULTIMOVE),
        failer);
    ByteArrayOutputStream ap = new ByteArrayOutputStream();
    PrintStream ad = new PrintStream(ap);
    System.setOut(ad);
    try {
      view.renderBoard();
    } catch (IOException e) {
      assertEquals("Appendable Fail", e.getMessage());
    }
  }


  private String forTest() {
    StringBuilder test = new StringBuilder();
    test.append("F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n"
        + "C1: A♥, 4♦, 7♠, 10♣\nC2: A♦, 4♠, 7♣, J♥\n"
        + "C3: A♠, 4♣, 8♥, J♦\nC4: A♣, 5♥, 8♦, J♠\n"
        + "C5: 2♥, 5♦, 8♠, J♣\nC6: 2♦, 5♠, 8♣, Q♥\n"
        + "C7: 2♠, 5♣, 9♥, Q♦\nC8: 2♣, 6♥, 9♦, Q♠\n"
        + "C9: 3♥, 6♦, 9♠, Q♣\nC10: 3♦, 6♠, 9♣, K♥\n"
        + "C11: 3♠, 6♣, 10♥, K♦\nC12: 3♣, 7♥, 10♦, K♠\n"
        + "C13: 4♥, 7♦, 10♠, K♣");
    String a = test.substring(0);
    return a;
  }

  private String forTest2() {
    StringBuilder test = new StringBuilder();
    test.append("F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\n"
        + "C1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "C2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "C3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "C4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣");

    String a = test.substring(0);
    return a;
  }

  private String forTest3() {
    StringBuilder test = new StringBuilder();
    test.append("F1:\nF2:\nF3:\nF4:\nO1: K♥\nO2:\nO3:\nO4:\n"
        + "C1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n"
        + "C2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "C3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "C4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣");

    String a = test.substring(0);
    return a;
  }

  private String forTest4() {
    StringBuilder test = new StringBuilder();
    test.append("F1: A♥\nF2:\nF3:\nF4:\nO1: 10♣\nO2: 7♠\nO3: 4♦\nO4:\n"
        + "C1:\nC2: A♦, 4♠, 7♣, J♥\n"
        + "C3: A♠, 4♣, 8♥, J♦\nC4: A♣, 5♥, 8♦, J♠\n"
        + "C5: 2♥, 5♦, 8♠, J♣\nC6: 2♦, 5♠, 8♣, Q♥\n"
        + "C7: 2♠, 5♣, 9♥, Q♦\nC8: 2♣, 6♥, 9♦, Q♠\n"
        + "C9: 3♥, 6♦, 9♠, Q♣\nC10: 3♦, 6♠, 9♣, K♥\n"
        + "C11: 3♠, 6♣, 10♥, K♦\nC12: 3♣, 7♥, 10♦, K♠\n"
        + "C13: 4♥, 7♦, 10♠, K♣");
    String a = test.substring(0);
    return a;
  }

}