/**
 * 
 * @author Josh Seligman <br>
 * 
 *         This is the class definition for a node
 *
 */
public class NodeSeligman {
	/**
	 * Instance variable for the CardSeligman object stored in the node
	 */
	private CardSeligman myCard;

	/**
	 * Instance variable that points to the next node in the list
	 */
	private NodeSeligman myNext;

	/**
	 * Null constructor for the NodeSeligman class
	 */
	public NodeSeligman() {
		myCard = null;
		myNext = null;
	} // NodeSeligman - null constructor

	/**
	 * Constructor for the NodeSeligman class
	 * 
	 * @param card The initial card that is stored in the node
	 */
	public NodeSeligman(CardSeligman card) {
		myCard = card;
		myNext = null;
	} // NodeSeligman - constructor

	/**
	 * Getter for the card
	 * 
	 * @return The card being stored in the node
	 */
	public CardSeligman getData() {
		return myCard;
	} // getData

	/**
	 * Setter for the card
	 * 
	 * @param newCard The new card to be stored in the node
	 */
	public void setData(CardSeligman newCard) {
		myCard = newCard;
	} // setData

	/**
	 * Getter for the next node in the list
	 * 
	 * @return The next node in the list
	 */
	public NodeSeligman getNext() {
		return myNext;
	} // getNext

	/**
	 * Setter for the next node in the list
	 * 
	 * @param newNext The new next node in the list
	 */
	public void setNext(NodeSeligman newNext) {
		myNext = newNext;
	} // setNext
} // NodeSeligman
