import static org.junit.Assert.assertEquals;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardRank;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.SuitType;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.Test;

/**
 * This class implements the tests for the SimpleFreecellController class.
 */
public class SimpleFreecellControllerTest {

  FreecellModel model = new SimpleFreecellModel();

  @Test
  public void testNotFinishedGameButQuit() {
    Appendable append = new StringBuilder("");

    Readable input = new StringReader("C1 1 F1 Q");
    Appendable output = new StringBuilder("");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, 52, 4, false);
    FreecellTextView view = new FreecellTextView(model, append);

    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(deck, 52, 4, false);
    newModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }


  @Test(expected = IllegalStateException.class)
  public void testNotEnoughInput() {
    Readable input = new StringReader("C2 1 F2");
    Appendable output = new StringBuilder("");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, 52, 4, false);
  }

  @Test
  public void testNoMove() {
    Appendable append = new StringBuilder("");
    Readable input = new StringReader("Q");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 10, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);

    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 10, 2, false);

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testNoMoveButNotQuit() {
    Appendable append = new StringBuilder("");
    Readable input = new StringReader("");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 10, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);

    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 10, 2, false);

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }

  @Test
  public void testTwoMoves() {
    Appendable append = new StringBuilder("");
    Readable input = new StringReader("C1 1 F1 C5 1 F1 Q");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);

    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    newModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    newModel.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }

  @Test
  public void testFinishGame() {
    Appendable append = new StringBuilder("");
    StringBuilder a = new StringBuilder();
    for (int i = 0; i < 13; i++) {
      a.append(String.format("C%d 1 F%d ", i * 4 + 1, 1));
      a.append(String.format("C%d 1 F%d ", i * 4 + 2, 2));
      a.append(String.format("C%d 1 F%d ", i * 4 + 3, 3));
      a.append(String.format("C%d 1 F%d ", i * 4 + 4, 4));
    }

    Readable input1 = new StringReader(a.toString());
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input1, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);

    for (int i = 0; i < 13; i++) {
      newModel.move(PileType.CASCADE, i * 4, 0, PileType.FOUNDATION, 0);
      newModel.move(PileType.CASCADE, i * 4 + 1, 0, PileType.FOUNDATION, 1);
      newModel.move(PileType.CASCADE, i * 4 + 2, 0, PileType.FOUNDATION, 2);
      newModel.move(PileType.CASCADE, i * 4 + 3, 0, PileType.FOUNDATION, 3);

    }

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());

  }

  @Test
  public void testFinishGameWithWrongSourcePileInputs() {
    Appendable append = new StringBuilder("");
    StringBuilder a = new StringBuilder();
    for (int i = 0; i < 13; i++) {
      a.append(String.format("C%d 1 F%d ", i * 4 + 1, 1));
      a.append(String.format("C%d 1 F%d ", i * 4 + 2, 2));
      a.append(String.format("C%d 1 F%d ", i * 4 + 3, 3));
      a.append(" NotCare ");
      a.append(String.format("C%d 1 F%d ", i * 4 + 4, 4));
    }
    Readable input1 = new StringReader(a.toString());
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input1, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);

    for (int i = 0; i < 13; i++) {
      newModel.move(PileType.CASCADE, i * 4, 0, PileType.FOUNDATION, 0);
      newModel.move(PileType.CASCADE, i * 4 + 1, 0, PileType.FOUNDATION, 1);
      newModel.move(PileType.CASCADE, i * 4 + 2, 0, PileType.FOUNDATION, 2);
      newModel.move(PileType.CASCADE, i * 4 + 3, 0, PileType.FOUNDATION, 3);

    }

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());

  }

  @Test
  public void testFinishGameWithWrongInputsWithMoreInputsWhenGameAlreadyFinished()
      throws IOException {
    Appendable append = new StringBuilder("");
    StringBuilder a = new StringBuilder();
    for (int i = 0; i < 13; i++) {
      a.append(String.format("C%d 1 F%d ", i * 4 + 1, 1));
      a.append(String.format("C%d 1 F%d ", i * 4 + 2, 2));
      a.append(String.format("C%d 1 F%d ", i * 4 + 3, 3));
      a.append(String.format("C%d 1 F%d ", i * 4 + 4, 4));
      a.append(" NotCare ");

    }
    Readable input1 = new StringReader(a.toString());
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input1, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);

    for (int i = 0; i < 13; i++) {
      newModel.move(PileType.CASCADE, i * 4, 0, PileType.FOUNDATION, 0);
      newModel.move(PileType.CASCADE, i * 4 + 1, 0, PileType.FOUNDATION, 1);
      newModel.move(PileType.CASCADE, i * 4 + 2, 0, PileType.FOUNDATION, 2);
      newModel.move(PileType.CASCADE, i * 4 + 3, 0, PileType.FOUNDATION, 3);

    }

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }


  @Test
  public void testFinishGameWithWrongCardIndexInputs() {
    Appendable append = new StringBuilder("");
    StringBuilder a = new StringBuilder();
    for (int i = 0; i < 13; i++) {
      a.append(String.format("C%d 1 F%d ", i * 4 + 1, 1));
      a.append(String.format("C%d Weird Stuff 1 F%d ", i * 4 + 2, 2));
      a.append(String.format("C%d 1 F%d ", i * 4 + 3, 3));
      a.append(String.format("C%d 1 F%d ", i * 4 + 4, 4));
    }
    Readable input1 = new StringReader(a.toString());
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input1, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);

    for (int i = 0; i < 13; i++) {
      newModel.move(PileType.CASCADE, i * 4, 0, PileType.FOUNDATION, 0);
      newModel.move(PileType.CASCADE, i * 4 + 1, 0, PileType.FOUNDATION, 1);
      newModel.move(PileType.CASCADE, i * 4 + 2, 0, PileType.FOUNDATION, 2);
      newModel.move(PileType.CASCADE, i * 4 + 3, 0, PileType.FOUNDATION, 3);

    }

    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }

  @Test
  public void testFinishGameWithWrongDestinationInputs() {
    Appendable append = new StringBuilder("");
    StringBuilder a = new StringBuilder();
    for (int i = 0; i < 13; i++) {
      a.append(String.format("C%d 1 F%d ", i * 4 + 1, 1));
      a.append(String.format("C%d 1 F%d ", i * 4 + 2, 2));
      a.append(String.format("C%d 1 Strangers F%d ", i * 4 + 3, 3));
      a.append(String.format("C%d 1 F%d ", i * 4 + 4, 4));
    }
    Readable input1 = new StringReader(a.toString());
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input1, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);

    for (int i = 0; i < 13; i++) {
      newModel.move(PileType.CASCADE, i * 4, 0, PileType.FOUNDATION, 0);
      newModel.move(PileType.CASCADE, i * 4 + 1, 0, PileType.FOUNDATION, 1);
      newModel.move(PileType.CASCADE, i * 4 + 2, 0, PileType.FOUNDATION, 2);
      newModel.move(PileType.CASCADE, i * 4 + 3, 0, PileType.FOUNDATION, 3);

    }
    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }

  @Test
  public void testInvalidMove() {
    Appendable append = new StringBuilder("");
    Readable input = new StringReader("C55 1 F1 C5 1 F1 Q");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellTextView view = new FreecellTextView(model, append);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    assertEquals(new FreecellTextView(newModel, append).toString(), view.toString());
  }

  @Test
  public void testIsGameOver() {
    Readable input = new StringReader("C1 1 F1 C2 1 F1 Q");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);

    assertEquals(false, model.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void testNotEnoughValidInputForOneMove() {
    Readable input = new StringReader("CC CC CC CC CC C1 1");
    Appendable output = new StringBuilder();

    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);

  }

  @Test(expected = IllegalStateException.class)
  public void testNotEnoughValidInputForOneMove1() {
    Readable input = new StringReader("CC CC CC CC CC TTT1 TT");
    Appendable output = new StringBuilder();

    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellView view = new FreecellTextView(model);

  }

  @Test
  public void testMockStartGameWithNoMove() {
    StringBuilder log = new StringBuilder();
    StringBuilder irrelevantLog = new StringBuilder();
    Readable input = new StringReader("Q");
    Appendable output = new StringBuilder();
    FreecellModel mockModel = new MockModel(log, irrelevantLog);
    FreecellController controller = new SimpleFreecellController(mockModel, input, output);
    List<Card> deck = mockModel.getDeck();
    controller.playGame(deck, 52, 2, false);
    assertEquals("getDeck was called correctly. Provided deck with 52 cascade piles and 2 "
        + "open piles and shuffle status is false.", log.toString());
  }

  @Test
  public void testMockStartGameWithOneMove() {
    StringBuilder log = new StringBuilder();
    StringBuilder irrelevantLog = new StringBuilder();
    Readable input = new StringReader("C1 1 F1 q");
    Appendable output = new StringBuilder();
    FreecellModel mockModel = new MockModel(log, irrelevantLog);
    FreecellController controller = new SimpleFreecellController(mockModel, input, output);
    List<Card> deck = mockModel.getDeck();
    controller.playGame(deck, 52, 2, false);
    assertEquals("getDeck was called correctly. Provided deck with "
        + "52 cascade piles and 2 "
        + "open piles and shuffle status is false. "
        + "Source pile type is CASCADE,"
        + " source pile number is 0, card index is 0, destination pile type is"
        + " FOUNDATION, destination pile number is 0.", log.toString());
  }

  @Test
  public void testMockStartGameTwoMoves() {
    StringBuilder log = new StringBuilder();
    StringBuilder irrelevantLog = new StringBuilder();

    Readable input = new StringReader("C1 1 F1 C2 1 F2 q");
    Appendable output = new StringBuilder();
    FreecellModel mockModel = new MockModel(log, irrelevantLog);
    FreecellController controller = new SimpleFreecellController(mockModel, input, output);
    List<Card> deck = mockModel.getDeck();
    controller.playGame(deck, 52, 2, false);
    assertEquals("getDeck was called correctly. Provided deck with "
        + "52 cascade piles and 2 "
        + "open piles and shuffle status is false. "
        + "Source pile type is CASCADE,"
        + " source pile number is 0, card index is 0, destination pile type is"
        + " FOUNDATION, destination pile number is 0. Source pile type is CASCADE, "
        + "source pile number is 1, card index is 0, "
        + "destination pile type is FOUNDATION, destination pile number is 1.", log.toString());
  }

  @Test
  public void testMockStartGameOneMoveWithSomeIncorrectInputs() {
    StringBuilder log = new StringBuilder();
    StringBuilder irrelevantLog = new StringBuilder();
    Readable input = new StringReader("C1 TTT 1 F1 q");
    Appendable output = new StringBuilder();
    FreecellModel mockModel = new MockModel(log, irrelevantLog);
    FreecellController controller = new SimpleFreecellController(mockModel, input, output);
    List<Card> deck = mockModel.getDeck();
    controller.playGame(deck, 52, 2, false);
    assertEquals("getDeck was called correctly. Provided deck with "
        + "52 cascade piles and 2 "
        + "open piles and shuffle status is false. "
        + "Source pile type is CASCADE,"
        + " source pile number is 0, card index is 0, destination pile type is"
        + " FOUNDATION, destination pile number is 0.", log.toString());
  }

  @Test
  public void testRenderMessageIfGameIsNotFinished() {
    Appendable ap = new StringBuilder();
    Readable input = new StringReader("C1 1 F1 Q");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);

    FreecellModel model2 = new SimpleFreecellModel();
    model2.startGame(model2.getDeck(), 52, 2, false);
    FreecellModel testModel = new SimpleFreecellModel();
    testModel.startGame(testModel.getDeck(), 52, 2, false);
    FreecellView viewBefore = new FreecellTextView(testModel);
    model2.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    FreecellView view = new FreecellTextView(model2);

    assertEquals(viewBefore.toString() + "\n" + "\n" +
            view.toString() + "\n\n\nGame quit prematurely.",
        output.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullConstuctor() {
    Appendable output = null;
    Readable input = new StringReader("C1 1 F1 Q");
    FreecellController controller = new SimpleFreecellController(model, input, output);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor3() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("abc");
    FreecellController controller = new SimpleFreecellController(null, input, output);
  }

  @Test
  public void testManySpaceBetweenInputs() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 1        \n F1 C10 1 O1 Q");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, 52, 2, false);
    assertEquals(0, model.getNumCardsInCascadePile(0));
    assertEquals(1, model.getNumCardsInFoundationPile(0));
    assertEquals(1, model.getNumCardsInOpenPile(0));
    assertEquals(52, model.getNumCascadePiles());
    assertEquals(2, model.getNumOpenPiles());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullDeckPlayGame() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 1 O2 Q");
    List<Card> deck = null;
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(deck, 10, 10, true);
  }


  @Test
  public void testPlayGameShuffle() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("Q");
    List<Card> deck = model.getDeck();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(deck, 52, 2, true);
    Card a = new Card(SuitType.HEARTS, CardRank.ACE);
    FreecellView view2 = new FreecellTextView(model);
    assertEquals(false, a.equals(model.getCascadeCardAt(0, 0)));
    assertEquals(52, model.getNumCascadePiles());
    assertEquals(2, model.getNumOpenPiles());
    assertEquals(view2.toString() + "\n\nGame quit prematurely.", output.toString());
  }

  @Test
  public void testPlayGameShuffleWithMock() {
    Appendable output = new StringBuilder();
    StringBuilder irrelevantLog = new StringBuilder();

    Readable input = new StringReader("C1 1 F1 Q");
    StringBuilder log = new StringBuilder();
    FreecellModel mockModel = new MockModel(log, irrelevantLog);
    FreecellController controller = new SimpleFreecellController(mockModel, input, output);
    controller.playGame(mockModel.getDeck(), 52, 10, true);

    assertEquals("getDeck was called correctly. Provided deck with "
            + "52 cascade piles and 10 "
            + "open piles and shuffle status is true. "
            + "Source pile type is CASCADE,"
            + " source pile number is 0, card index is 0, destination pile type is"
            + " FOUNDATION, destination pile number is 0.",
        log.toString());
    assertEquals("getNumCardsInFoundationPile is called"
        + "getNumCardsInFoundationPile is called"
        + "getNumCardsInFoundationPile is called"
        + "getNumCardsInFoundationPile is called"
        + "getNumOpenPiles is called"
        + "getNumCascadePiles is called"
        + "getNumCardsInFoundationPile is called"
        + "getNumCardsInFoundationPile is calledget"
        + "NumCardsInFoundationPile is called"
        + "getNumCardsInFoundationPile is called"
        + "getNumOpenPiles is called"
        + "getNumCascadePiles is called", irrelevantLog.toString());
  }

  @Test
  public void testRenderMessageWithIncorrectInput() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader(" TTT C1 1 F1 Q");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    FreecellView newView = new FreecellTextView(newModel);
    FreecellView view = new FreecellTextView(model);

    assertEquals(newView.toString() + "\n" + "Entered source pile input is invalid."
        + " Please re-enter source pile input!\n\n" + view.toString() +
        "\n\n\nGame quit prematurely.", output.toString());
  }

  @Test
  public void testRenderMessageIncomplete() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 1 F1 T");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    FreecellView newView = new FreecellTextView(newModel);
    FreecellView view = new FreecellTextView(model);

    try {
      controller.playGame(model.getDeck(), 52, 2, false);
    } catch (IllegalStateException e) {
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
      assertEquals(newView.toString() + "\n\n" + view.toString() + "\n\n" +
              "Entered source pile input is invalid. Please re-enter source pile input!\n"
          , output.toString());
    }
  }

  @Test
  public void testRenderMessageCouldNotStartGame() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 1 F1 T");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 1, 1, false);
    assertEquals("Could not start game.", output.toString());
  }

  @Test
  public void testRenderMessageIncorrectCardIndex() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C4 T 2 1 F1");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    FreecellView newView = new FreecellTextView(newModel);
    try {
      controller.playGame(model.getDeck(), 52, 2, false);
    } catch (IllegalStateException e) {
      assertEquals(newView.toString() + "\n" + "Entered card index is invalid. "
              + "Please re-enter card index!\n"
              + "Destination pile input is invalid, please provide valid Destination Pile Input\n"
              + "Invalid move. Try again. The provided Cascade card index is not valid\n"
          , output.toString());
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }

  @Test
  public void testRenderBoardBeforeMakingMove() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    FreecellView newView = new FreecellTextView(newModel);
    try {
      controller.playGame(model.getDeck(), 52, 2, false);
    } catch (IllegalStateException e) {
      assertEquals(newView.toString() + "\n", output.toString());
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }

  @Test
  public void testRenderMessageWithIncorrectDestinationInput() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 1 OK O1 Q");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    FreecellView newView = new FreecellTextView(newModel);
    FreecellView view = new FreecellTextView(model);

    assertEquals(newView.toString() + "\n" + "Destination pile input is invalid,"
        + " please provide valid Destination Pile Input\n\n" + view.toString() +
        "\n\n\nGame quit prematurely.", output.toString());
  }

  @Test
  public void testRenderMessageIncorrectCardIndexTwice() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C4 T T 1 F1");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    FreecellModel newModel = new SimpleFreecellModel();
    newModel.startGame(newModel.getDeck(), 52, 2, false);
    FreecellView newView = new FreecellTextView(newModel);
    FreecellModel newModel1 = new SimpleFreecellModel();
    newModel1.startGame(newModel1.getDeck(), 52, 2, false);
    newModel1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    FreecellView newView1 = new FreecellTextView(newModel1);
    try {
      controller.playGame(model.getDeck(), 52, 2, false);
    } catch (IllegalStateException e) {
      assertEquals(newView.toString() + "\n" + "Entered card index is invalid. "
              + "Please re-enter card index!\n" + "Entered card index is invalid. "
              + "Please re-enter card index!\n\n" + newView1.toString() + "\n\n"
          , output.toString());
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }

  @Test
  public void testIOException() {
    Appendable output = new Weirdo();
    Readable input = new StringReader("C1 1 F1");
    FreecellController controller = new SimpleFreecellController(model, input, output);
    try {
      controller.playGame(model.getDeck(), 52, 2, false);
    } catch (IllegalStateException e) {
      assertEquals("Could not render board or render message", e.getMessage());
    }
  }

  @Test
  public void testNullConstructor() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("");
    try {
      FreecellController controller = new SimpleFreecellController(null, rd, ap);
    } catch (IllegalArgumentException e) {
      assertEquals("One of the provided parameters is null!", e.getMessage());
    }
  }

  @Test
  public void testNullConstructor1() {
    Readable rd = new StringReader("");
    try {
      FreecellController controller = new SimpleFreecellController(model, rd, null);
    } catch (IllegalArgumentException e) {
      assertEquals("One of the provided parameters is null!", e.getMessage());
    }
  }

  @Test
  public void testNullConstructor2() {
    Appendable ap = new StringBuilder();
    try {
      FreecellController controller = new SimpleFreecellController(model, null, ap);
    } catch (IllegalArgumentException e) {
      assertEquals("One of the provided parameters is null!", e.getMessage());
    }
  }

  @Test
  public void testNullDeck() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("ABC");
    try {
      FreecellController controller = new SimpleFreecellController(model, rd, ap);
      controller.playGame(null, 10, 10, false);
    } catch (IllegalArgumentException e) {
      assertEquals("deck is null!", e.getMessage());
    }
  }

  @Test
  public void testRenderMessageIfGameIsNotFinished2() {
    Appendable ap = new StringBuilder();
    Readable input = new StringReader("C1 1 F1 C2 Q");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);

    FreecellModel model2 = new SimpleFreecellModel();
    model2.startGame(model2.getDeck(), 52, 2, false);
    FreecellModel testModel = new SimpleFreecellModel();
    testModel.startGame(testModel.getDeck(), 52, 2, false);
    FreecellView viewBefore = new FreecellTextView(testModel);
    model2.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    FreecellView view = new FreecellTextView(model2);

    assertEquals(viewBefore.toString() + "\n" + "\n" +
            view.toString() + "\n\n\nGame quit prematurely.",
        output.toString());
  }

  @Test
  public void testRenderMessageIfGameIsNotFinished3() {
    Appendable ap = new StringBuilder();
    Readable input = new StringReader("C1 1 F1 C2 1 Q");
    Appendable output = new StringBuilder();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 52, 2, false);

    FreecellModel model2 = new SimpleFreecellModel();
    model2.startGame(model2.getDeck(), 52, 2, false);
    FreecellModel testModel = new SimpleFreecellModel();
    testModel.startGame(testModel.getDeck(), 52, 2, false);
    FreecellView viewBefore = new FreecellTextView(testModel);
    model2.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    FreecellView view = new FreecellTextView(model2);

    assertEquals(viewBefore.toString() + "\n" + "\n" +
            view.toString() + "\n\n\nGame quit prematurely.",
        output.toString());
  }

  @Test
  public void testIOException2() {
    Appendable output = new StringBuilder();
    Readable input = new AnotherWeirdClass();
    FreecellController controller = new SimpleFreecellController(model, input, output);
    try {
      controller.playGame(model.getDeck(), 52, 2, false);
    } catch (IllegalStateException e) {
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }

  @Test
  public void testUsingComplexModel() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 4 C2 C2 4 C8 C8 4 C10 Q");
    FreecellController controller = new SimpleFreecellController(complexModel, input, output);
    controller.playGame(complexModel.getDeck(), 13, 3, false);
    assertEquals(3, complexModel.getNumCardsInCascadePile(0));
    assertEquals(3, complexModel.getNumCardsInCascadePile(1));
    assertEquals(3, complexModel.getNumCardsInCascadePile(7));
    assertEquals(7, complexModel.getNumCardsInCascadePile(9));

  }

  @Test
  public void testUsingComplexModel2() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 4 C2 C2 4 C8 C8 4 C10");
    FreecellController controller = new SimpleFreecellController(complexModel, input, output);
    try {
      controller.playGame(complexModel.getDeck(), 13, 3, false);
    } catch (IllegalStateException e) {
      assertEquals(3, complexModel.getNumCardsInCascadePile(0));
      assertEquals(3, complexModel.getNumCardsInCascadePile(1));
      assertEquals(3, complexModel.getNumCardsInCascadePile(7));
      assertEquals(7, complexModel.getNumCardsInCascadePile(9));
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }

  @Test
  public void testUsingComplexModelWithWrongInputSource() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 4 C2 weirdthing C2 4 C8 C8 4 C10");
    FreecellController controller = new SimpleFreecellController(complexModel, input, output);
    try {
      controller.playGame(complexModel.getDeck(), 13, 3, false);
    } catch (IllegalStateException e) {
      assertEquals(3, complexModel.getNumCardsInCascadePile(0));
      assertEquals(3, complexModel.getNumCardsInCascadePile(1));
      assertEquals(3, complexModel.getNumCardsInCascadePile(7));
      assertEquals(7, complexModel.getNumCardsInCascadePile(9));
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }

  @Test
  public void testUsingComplexModelWithWrongInputCard() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 4 C2 C2 weirdthing 4 C8 C8 4 C10");
    FreecellController controller = new SimpleFreecellController(complexModel, input, output);
    try {
      controller.playGame(complexModel.getDeck(), 13, 3, false);
    } catch (IllegalStateException e) {
      assertEquals(3, complexModel.getNumCardsInCascadePile(0));
      assertEquals(3, complexModel.getNumCardsInCascadePile(1));
      assertEquals(3, complexModel.getNumCardsInCascadePile(7));
      assertEquals(7, complexModel.getNumCardsInCascadePile(9));
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }

  @Test
  public void testUsingComplexModelWithWrongInputDestination() {
    FreecellModel complexModel = FreecellModelCreator.create(GameType.MULTIMOVE);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("C1 4 C2 C2 4 weirdthing C8 C8 4 C10");
    FreecellController controller = new SimpleFreecellController(complexModel, input, output);
    try {
      controller.playGame(complexModel.getDeck(), 13, 3, false);
    } catch (IllegalStateException e) {
      assertEquals(3, complexModel.getNumCardsInCascadePile(0));
      assertEquals(3, complexModel.getNumCardsInCascadePile(1));
      assertEquals(3, complexModel.getNumCardsInCascadePile(7));
      assertEquals(7, complexModel.getNumCardsInCascadePile(9));
      assertEquals("Missing inputs to finish the game or did not quit", e.getMessage());
    }
  }


}