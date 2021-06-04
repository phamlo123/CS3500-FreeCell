package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the FreecellController parameterized with Card. This class also contains
 * information about the controller of the game Freecell by delegating user inputs to the Model and
 * the View class for execution.
 */
public class SimpleFreecellController implements FreecellController<Card> {

  private final FreecellModel<Card> model;
  private final Readable rd;
  private final Appendable ap;
  private FreecellView view;

  /**
   * Construct an object of SimpleFreecellController class.
   *
   * @param model the SimpleFreecellModel that will be the model for the controller to call.
   * @param rd    an object that implements the Readable interface - represents the user inputs to
   *              this controller object
   * @param ap    an object that implements the Appendable interface - represents the view output to
   *              the console
   * @throws IllegalArgumentException when either of the argument is null.
   */

  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if ((model == null) || (rd == null) || (ap == null)) {
      throw new IllegalArgumentException("One of the provided parameters is null!");
    }
    this.model = model;
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {
    view = new FreecellTextView(model, ap);
    if (deck == null) {
      throw new IllegalArgumentException("deck is null!");
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      this.displayMessage("Could not start game.");
      return;
    }

    //try to render the current board
    this.displayBoard();
    Scanner scan = new Scanner(rd);
    outer:
    while (scan.hasNext()) {
      if (!model.isGameOver()) {
        String firstInput = scan.next();
        while (verifyingFirstAndThirdInput(firstInput) == InputState.INCORRECT) {
          this.displayMessage("Entered source pile input is invalid. "
              + "Please re-enter source pile input!\n");
          if (!scan.hasNext()) {
            //break if there is no more next input
            break outer;
          }
          firstInput = scan.next();
        }

        if (verifyingFirstAndThirdInput(firstInput) == InputState.QUIT) {
          this.displayMessage("\nGame quit prematurely.");
          return;
        }
        if (!scan.hasNext()) {
          //break if there is no more next input
          break;
        }
        String secondInput = scan.next();
        while (verifyingSecondInput(secondInput) == InputState.INCORRECT) {
          this.displayMessage("Entered card index is invalid. "
              + "Please re-enter card index!\n");
          if (!scan.hasNext()) {
            //break if there is no more next input
            break outer;
          }
          secondInput = scan.next();
        }
        if (verifyingSecondInput(secondInput) == InputState.QUIT) {
          this.displayMessage("\nGame quit prematurely.");
          return;
        }
        if (!scan.hasNext()) {
          //break if there is no more next input
          break;
        }
        String thirdInput = scan.next();
        while (verifyingFirstAndThirdInput(thirdInput) == InputState.INCORRECT) {
          this.displayMessage("Destination pile input is invalid, "
              + "please provide valid Destination Pile Input\n");
          if (!scan.hasNext()) {
            //break if there is no more next input
            break outer;
          }
          thirdInput = scan.next();
        }

        if (verifyingFirstAndThirdInput(thirdInput) == InputState.QUIT) {
          this.displayMessage("\nGame quit prematurely.");
          return;
        }

        try {
          executeMove(model, firstInput, secondInput, thirdInput);
        } catch (IOException e) {
          throw new IllegalStateException("Appendable object failed.");
        }


      } else {
        //Terminate While loop if game is over, regardless of whether or not there is more inputs.
        return;
      }
    }

    if (!model.isGameOver() && !scan.hasNext()) {
      throw new IllegalStateException("Missing inputs to finish the game or did not quit");
    }

    if (model.isGameOver()) {
      this.displayBoard();
      this.displayMessage("Game over.");
    }
  }

  /**
   * Execute the move on the model by calling the move method from the model with the given inputs.
   *
   * @param model       is the model that the move method is called on.
   * @param firstInput  represents the Source Pile input from user.
   * @param secondInput represents the card index input from user.
   * @param thirdInput  represents destination pile input from user.
   * @throws IOException if Appendable argument fails for any reason.
   */
  private void executeMove(FreecellModel model, String firstInput,
      String secondInput, String thirdInput) throws IOException {

    String sourcePileType = firstInput.substring(0, 1);
    String sourcePileIndexString = firstInput.substring(1);
    int sourcePileIndex = Integer.parseInt(sourcePileIndexString);

    int cardIndex = Integer.parseInt(secondInput);

    String destinationPileType = thirdInput.substring(0, 1);
    String destinationPileIndexString = thirdInput.substring(1);
    int destinationPileIndex = Integer.parseInt(destinationPileIndexString);

    try {
      switch (sourcePileType) {
        case "C":
          if (destinationPileType.equals("C")) {
            model.move(PileType.CASCADE, sourcePileIndex - 1,
                cardIndex - 1, PileType.CASCADE, destinationPileIndex - 1);
          } else if (destinationPileType.equals("O")) {
            model.move(PileType.CASCADE, sourcePileIndex - 1,
                cardIndex - 1, PileType.OPEN, destinationPileIndex - 1);
          } else if (destinationPileType.equals("F")) {
            model.move(PileType.CASCADE, sourcePileIndex - 1,
                cardIndex - 1, PileType.FOUNDATION, destinationPileIndex - 1);
          }
          this.displayMessage("\n");
          break;
        case "O":
          if (destinationPileType.equals("C")) {
            model.move(PileType.OPEN, sourcePileIndex - 1,
                cardIndex - 1, PileType.CASCADE, destinationPileIndex - 1);
          } else if (destinationPileType.equals("O")) {
            model.move(PileType.OPEN, sourcePileIndex - 1,
                cardIndex - 1, PileType.OPEN, destinationPileIndex - 1);
          } else if (destinationPileType.equals("F")) {
            model.move(PileType.OPEN, sourcePileIndex - 1,
                cardIndex - 1, PileType.FOUNDATION, destinationPileIndex - 1);
          }
          this.displayMessage("\n");
          break;
        default:
          this.displayMessage("The provided Pile is not valid\n");
      }
      this.displayBoard();
      this.displayMessage("\n");
    } catch (IllegalArgumentException e) {
      this.displayMessage("Invalid move. Try again. " + e.getMessage() + "\n");
    }
  }

  /**
   * Determines the predefined InputState of the given source pile or destination pile String input
   * from user. A CORRECT input starts with one of the following letters: C, O, F, Q, q; and is
   * followed by a valid number. A QUIT input contains the letter Q or q. And an INCORRECT input is
   * everything else that is neither CORRECT or QUIT.
   *
   * @param firstOrthird is either the source pile input or destination pile input from user in the
   *                     form of a String.
   * @return the InputState of the String input.
   */
  private InputState verifyingFirstAndThirdInput(String firstOrthird) {
    InputState a;
    if (!firstOrthird.substring(0, 1).equals("C") && !firstOrthird.substring(0, 1).equals("O")
        && !firstOrthird.substring(0, 1).equals("F") && !firstOrthird.contains("Q")
        && !firstOrthird.contains("q")) {
      a = InputState.INCORRECT;
    } else if (firstOrthird.contains("Q") || firstOrthird.contains("q")) {
      a = InputState.QUIT;
    } else {
      try {
        Integer.parseInt(firstOrthird.substring(1));
        a = InputState.CORRECT;
      } catch (NumberFormatException e) {
        a = InputState.INCORRECT;
      }
    }
    return a;
  }

  /**
   * Determines the predefined InputState of the given source pile or destination pile String input
   * from user. A CORRECT input either contains the letter Q or q, or is a valid number. QUIT input
   * contains Q or q. And an INCORRECT input is everything else that is neither CORRECT or QUIT.
   *
   * @param secondInput is the card index input from user in the form of a String
   * @return the InputState of the String input.
   */
  private InputState verifyingSecondInput(String secondInput) {
    InputState a;
    if (secondInput.contains("Q") || secondInput.contains("q")) {
      return InputState.QUIT;
    } else {
      try {
        Integer.parseInt(secondInput);
        a = InputState.CORRECT;
      } catch (NumberFormatException e) {
        a = InputState.INCORRECT;
      }
      return a;
    }
  }

  /**
   * Display the message in the console by calling the renderMessage(String msg) on the view field
   * of this object.
   *
   * @param msg is the message to be displayed
   * @throws IllegalStateException if the appendable argument fails for any reason in the
   *                               FreecellTextView class
   */
  private void displayMessage(String msg) throws IllegalStateException {
    try {
      view.renderMessage(msg);
    } catch (IOException e) {
      throw new IllegalStateException("Could not render message");
    }
  }

  /**
   * Display the board of this model current state in the console by callng the renderBoard() method
   * in the view field of this object.
   *
   * @throws IllegalStateException if the appendable argument fails for any reason
   */
  private void displayBoard() throws IllegalStateException {
    try {
      view.renderBoard();
      view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not render board or render message");
    }
  }

}


