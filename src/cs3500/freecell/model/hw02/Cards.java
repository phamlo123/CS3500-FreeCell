package cs3500.freecell.model.hw02;

/**
 * This is the interface for the cards in the game of Freecell, with public methods to be called
 * with it implementations.
 */
public interface Cards {

  /**
   * Make a string for this card object.
   *
   * @return String representation of this Card object, consisting of its Rank followed by its Suit
   * @throws IllegalArgumentException if a suit Different from the 4 defined in the method is passed
   *                                  in as a parameter
   */
  @Override
  String toString() throws IllegalArgumentException;


  /**
   * Compare if that object and this card is equal to each other.
   *
   * @param that is an object
   * @return whether or not this card is the same as the given object by checking their qualities
   */
  @Override
  boolean equals(Object that);


  /**
   * Create a hashCode for this card.
   *
   * @return the Integer hashCode of a card
   */

  @Override
  int hashCode();

  /**
   * Compare the value of this card and other card.
   *
   * @param other card passed in
   * @return an integer signaling whether this card has value equal to other card or less than or
   *         greater than
   */
  int compareValue(Card other);

  /**
   * Check if this card has the same color as the other card according to its suits.
   *
   * @param other card passed in
   * @return a boolean representing whether this card has the same color as the other card.
   */
  boolean sameColor(Card other);

  /**
   * Check if this card and other card has the same color.
   *
   * @return the color of this card. There are only 2 available colors of a card - black or red
   *         depending of its suit
   */
  Color getColor();

  /**
   * check if this card's value is exactly one above the other card's value.
   *
   * @param other is a card
   * @return whether or not this card's value is exactly one above that of other
   */
  boolean valueOneAbove(Card other);

  /**
   * get the value associated with the rank of this card.
   *
   * @return the inherent value associated with this card suit (each suit has a differnt value
   *         ranging from 1 to 13)
   */
  int getValue();

  /**
   * get the rank of this card.
   *
   * @return the Rank of this Card Object
   */
  CardRank getRank();

  /**
   * get the suit of this card.
   *
   * @return the Suit of this Card Object
   */
  SuitType getSuit();

}
