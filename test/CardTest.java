
import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardRank;
import cs3500.freecell.model.hw02.SuitType;
import org.junit.Test;

/**
 * This class contains tests for members in Card class.
 */
public class CardTest {

  Card a = new Card(SuitType.HEARTS, CardRank.ACE);
  Card b = new Card(SuitType.CLUBS, CardRank.ACE);
  Card c = new Card(SuitType.HEARTS, CardRank.ACE);
  Card d = new Card(SuitType.DIAMONDS, CardRank.KING);
  Card e = new Card(SuitType.HEARTS, CardRank.SEVEN);
  Card f = new Card(SuitType.SPADES, CardRank.ACE);
  Card g = new Card(SuitType.SPADES, CardRank.TWO);
  Card h = new Card(SuitType.HEARTS, CardRank.TWO);


  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    new Card(null, null);
  }

  @Test
  public void testEquals() {
    assertEquals(false, a.equals(b));
  }

  @Test
  public void testEqualsTrue() {
    assertEquals(true, c.equals(a));
  }

  @Test
  public void testEqualsSelf() {
    assertEquals(true, a.equals(a));
  }

  @Test
  public void sameColorSelf() {
    assertEquals(true, a.sameColor(a));
  }

  @Test
  public void sameColorSameSuit() {
    assertEquals(true, d.sameColor(e));
  }

  @Test
  public void sameColorDifferentSuit() {
    assertEquals(true, a.sameColor(d));
  }

  @Test
  public void sameColorDifferentSuits() {
    assertEquals(false, a.sameColor(b));
  }

  @Test
  public void sameColorDifferentKind() {
    assertEquals(false, e.sameColor(f));
  }

  @Test
  public void getRankACE() {
    assertEquals(CardRank.ACE, a.getRank());
  }

  @Test
  public void getRankSeven() {
    assertEquals(CardRank.SEVEN, e.getRank());
  }

  @Test
  public void getRankKing() {
    assertEquals(CardRank.KING, d.getRank());
  }

  @Test
  public void compareValueLess() {
    assertEquals(-1, a.compareValue(d));
  }

  @Test
  public void compareValueEqual() {
    assertEquals(0, a.compareValue(b));
  }

  @Test
  public void compareValueSelf() {
    assertEquals(0, a.compareValue(a));
  }

  @Test
  public void compareValueGreater() {
    assertEquals(1, e.compareValue(f));
  }

  @Test
  public void compareValueGreater2() {
    assertEquals(1, d.compareValue(e));
  }

  @Test
  public void getSuit1() {
    assertEquals(SuitType.HEARTS, a.getSuit());
  }

  @Test
  public void getSuit2() {
    assertEquals(SuitType.CLUBS, b.getSuit());
  }

  @Test
  public void getSuit3() {
    assertEquals(SuitType.DIAMONDS, d.getSuit());
  }

  @Test
  public void getSuit4() {
    assertEquals(SuitType.SPADES, f.getSuit());
  }

  @Test
  public void oneValueAboveSame() {
    assertEquals(true, g.valueOneAbove(f));
  }

  @Test
  public void oneValueAbove() {
    assertEquals(true, h.valueOneAbove(f));
  }

  @Test
  public void oneValueAboveSelf() {
    assertEquals(false, h.valueOneAbove(h));
  }

  @Test
  public void oneValueAboveSameSuit() {
    assertEquals(true, g.valueOneAbove(f));
  }

  @Test
  public void oneValueAboveDifferentSuitSameRank() {
    assertEquals(false, g.valueOneAbove(h));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorSuitNull() {
    new Card(null, CardRank.ACE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorRankNull() {
    new Card(SuitType.HEARTS, null);
  }
}