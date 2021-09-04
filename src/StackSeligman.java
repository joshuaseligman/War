/**
 * 
 * @author Josh Seligman <br>
 * 
 *         This is the class definition for a stack
 *
 */
public class StackSeligman {
	/**
	 * Instance variable for the top of the stack
	 */
	private NodeSeligman myTop;

	/**
	 * Null constructor for the stack
	 */
	public StackSeligman() {
		myTop = null;
	} // StackSeligman - null constructor

	/**
	 * Determines if the stack is empty
	 * 
	 * @return Whether the stack is empty or not
	 */
	public boolean isEmpty() {
		return (myTop == null);
	} // isEmpty

	/**
	 * Determines if the stack is full
	 * 
	 * @return Whether the stack is full or not
	 */
	public boolean isFull() {
		return false;
	} // isFull

	/**
	 * Adds a card to the top of the stack
	 * 
	 * @param card The card to add to the stack
	 * @return If the card was successfully added to the stack
	 */
	public boolean push(CardSeligman card) {
		NodeSeligman newTop = new NodeSeligman(card);
		newTop.setNext(myTop);
		myTop = newTop;
		return true;
	} // push

	/**
	 * Removes the topmost element of the stack
	 * 
	 * @return The card being removed from the stack
	 */
	public CardSeligman pop() {
		CardSeligman removedCard = null;
		if (!isEmpty()) {
			removedCard = myTop.getData();
			myTop = myTop.getNext();
		}
		return removedCard;
	} // pop
} // StackSeligman
