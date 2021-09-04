/**
 * 
 * @author Josh Seligman <br>
 * 
 *         This is the class definition for a card
 *
 */
public class CardSeligman {
	/**
	 * Instance variable representing the value of the card
	 */
	private int myValue;

	/**
	 * Instance variable representing the suit of the card
	 */
	private String mySuit;

	/**
	 * Null constructor for the CardSeligman class
	 */
	public CardSeligman() {
		myValue = 0;
		mySuit = "None";
	} // CardSeligman - null constructor

	/**
	 * Full constructor for the CardSeligman class
	 * 
	 * @param value The initial value of the card
	 * @param suit  The initial suit of the card
	 */
	public CardSeligman(int value, String suit) {
		myValue = value;
		mySuit = suit;
	} // CardSeligman - full constructor

	/**
	 * Getter for the value of the card
	 * 
	 * @return The value of the card
	 */
	public int getValue() {
		return myValue;
	} // getValue

	/**
	 * Setter for the value of the card
	 * 
	 * @param newValue The new value of the card
	 */
	public void setValue(int newValue) {
		myValue = newValue;
	} // setValue

	/**
	 * Getter for the suit of the card
	 * 
	 * @return The suit of the card
	 */
	public String getSuit() {
		return mySuit;
	} // getSuit

	/**
	 * Setter for the suit of the card
	 * 
	 * @param newSuit The new suit of the card
	 */
	public void setSuit(String newSuit) {
		mySuit = newSuit;
	} // setSuit

	/**
	 * Creates a string representation of the CardSeligman object
	 * 
	 * @return The string representation of the card
	 */
	public String toString() {
		String ans = "";
		switch (myValue) {
			case 11:
				ans += "Jack";
				break;
			case 12:
				ans += "Queen";
				break;
			case 13:
				ans += "King";
				break;
			case 14:
				ans += "Ace";
				break;
			default:
				ans += myValue;
				break;
		} // switch
		ans += " of " + mySuit;
		return ans;
	} // toString
} // CardSeligman
