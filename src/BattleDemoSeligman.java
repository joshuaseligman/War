import java.io.*;
import java.util.*;

/**
 * @author Josh Seligman <br>
 * 
 *         Purpose: This program simulates a game of the War card game. <br>
 * 
 *         Input: The program inputs the path for the file containing the
 *         information of the shuffled deck. <br>
 * 
 *         Output: The program outputs the summary of the game. The summary
 *         contains the number of cards in the deck, whether or not the game
 *         ended with a winner, the number of cards Player 1 ended with, the
 *         number of cards Player 2 ended with, and the winner of the game. <br>
 */
public class BattleDemoSeligman {
	/**
	 * The Scanner object for keyboard input
	 */
	static Scanner keyboard = new Scanner(System.in);

	/**
	 * Deals the deck out to the 2 players
	 * 
	 * @param filePath The path of the file to read that contains the information of
	 *                 the deck
	 * @param p1       Player 1's stack of cards
	 * @param p2       Player 2's stack of cards
	 * @return The number of cards in the deck, Integer.MAX_VALUE if an error occurs
	 */
	public static int deal(String filePath, StackSeligman p1, StackSeligman p2) {
		// Declare variables for the file
		File deckFile = null;
		Scanner deckReader;

		// Declare variables for the card and dealing
		int cardValue = 0;
		String cardSuit;
		CardSeligman newCard;
		boolean dealToP1 = true;

		int sumCards = 0;

		try {
			// Create the file
			deckFile = new File(filePath);
			deckReader = new Scanner(deckFile);

			// Read through the file
			while (deckReader.hasNext()) {
				// Get the value and suit of the card
				cardValue = deckReader.nextInt();
				cardSuit = deckReader.next();
				newCard = new CardSeligman(cardValue, cardSuit);
				// Deal to the appropriate player
				if (dealToP1)
					p1.push(newCard);
				else
					p2.push(newCard);
				// Actions to do after dealing the card
				sumCards++;
				dealToP1 = !dealToP1;
			} // while

			deckReader.close();
		} // try
		catch (FileNotFoundException ex) {
			System.err.println("Failed to find file: " + deckFile.getAbsolutePath());
			sumCards = Integer.MAX_VALUE;
		} // catch - FileNotFoundException
		catch (InputMismatchException ex) {
			System.err.println("There is a type mismatch in the file being read");
			System.err.println(ex.getMessage());
			sumCards = Integer.MAX_VALUE;
		} // catch - InputMismatchException
		catch (NoSuchElementException ex) {
			System.err.println("Tried to read beyond the scope of the file");
			System.err.println(ex.getMessage());
			sumCards = Integer.MAX_VALUE;
		} // catch - NoSuchElementException
		catch (NullPointerException ex) {
			System.err.println("Null pointer found");
			System.err.println(ex.getMessage());
			sumCards = Integer.MAX_VALUE;
		} // catch - NullPointerException
		catch (Exception ex) {
			System.err.println("Something went wrong");
			ex.printStackTrace();
			sumCards = Integer.MAX_VALUE;
		} // catch - Exception

		// Return the number of cards dealt
		return sumCards;
	} // deal

	/**
	 * Plays the next card in the stack
	 * 
	 * @param playerStack The stack to take the card from
	 * @return The card being played
	 */
	public static CardSeligman playCard(StackSeligman playerStack) {
		return playerStack.pop();
	} // playCard

	/**
	 * Compares two cards and determines the winning card
	 * 
	 * @param card1 The first card to compare
	 * @param card2 The second card to compare
	 * @return The winning card, null if tie
	 */
	public static CardSeligman compareCards(CardSeligman card1, CardSeligman card2) {
		// Declare local variables for the method
		CardSeligman winner;
		int suitComparison = 0;

		// Determine the based on value
		if (card1.getValue() > card2.getValue())
			winner = card1;
		else if (card1.getValue() < card2.getValue())
			winner = card2;
		else {
			// Compare suits if the values are the same
			// Spades > Hearts > Diamonds > Clubs
			// Can compare the lexicographic order of the suits to determine the winner
			suitComparison = card1.getSuit().compareToIgnoreCase(card2.getSuit());
			if (suitComparison > 0)
				winner = card1;
			else if (suitComparison < 0)
				winner = card2;
			else
				winner = null;
		} // else

		// Return the reference to the winning card
		return winner;
	} // compare

	/**
	 * Adds the cards to the winning deck
	 * 
	 * @param cardStack   The stack to add the cards to
	 * @param winningCard The winning card from the round
	 * @param losingCard  The losing card from the round
	 */
	public static void winningPlay(StackSeligman cardStack, CardSeligman winningCard, CardSeligman losingCard) {
		cardStack.push(winningCard);
		cardStack.push(losingCard);
	} // winningPlay

	/**
	 * Places the cards into the discard stack of the appropriate player
	 * 
	 * @param cardStack1 The discard stack of the first player
	 * @param card1      The card played by the first player
	 * @param cardStack2 The discard stack of the second player
	 * @param card2      The card played by the second player
	 */
	public static void tiePlay(StackSeligman cardStack1, CardSeligman card1, StackSeligman cardStack2,
			CardSeligman card2) {
		cardStack1.push(card1);
		cardStack2.push(card2);
	} // tiePlay

	/**
	 * Moves cards from discard pile back to the play pile
	 * 
	 * @param discardDeck The discard deck to empty out
	 * @param playDeck    The stack to copy the discard stack over to
	 * @return Whether there are cards left in the discard prior to calling the
	 *         method
	 */
	public static boolean moveDiscardToPlay(StackSeligman discardDeck, StackSeligman playDeck) {
		// Declare and initialize local variables
		boolean hasDiscard = false;
		StackSeligman temp = new StackSeligman();
		CardSeligman card;

		// If the discard stack has cards in it, then move the cards over
		if (!discardDeck.isEmpty()) {
			hasDiscard = true;

			// Move the discard stack to the temp stack
			while (!discardDeck.isEmpty()) {
				card = discardDeck.pop();
				temp.push(card);
			} // while

			// Move the temp stack back over to play stack
			while (!temp.isEmpty()) {
				card = temp.pop();
				playDeck.push(card);
			} // while
		} // if

		// Return whether the discard stack existed
		return hasDiscard;
	} // moveDiscardToPlay

	/**
	 * Counts the number of cards in a stack
	 * 
	 * @param stack The stack to count the cards for
	 * @return The number of cards in the stack
	 */
	public static int countCards(StackSeligman stack) {
		// Declare and initialize variables for the method
		int numCards = 0;
		StackSeligman temp = new StackSeligman();
		CardSeligman card;

		// Move the cards to the temp stack and count how many cards there are
		while (!stack.isEmpty()) {
			card = stack.pop();
			temp.push(card);
			numCards++;
		} // while

		// Move the cards back to the original stack
		while (!temp.isEmpty()) {
			card = temp.pop();
			stack.push(card);
		} // while

		// Return the number of cards
		return numCards;
	} // countCards

	/**
	 * Prints the results of the game
	 * 
	 * @param numCards      The total number of cards in the deck
	 * @param numPlays      The number of rounds played
	 * @param p1PlayDeck    The play deck for Player 1
	 * @param p1DiscardDeck The discard deck for Player 1
	 * @param p2PlayDeck    The play deck for Player 2
	 * @param p2DiscardDeck The discard deck for Player 2
	 */
	public static void printResults(int numCards, int numPlays, StackSeligman p1PlayDeck, StackSeligman p1DiscardDeck,
			StackSeligman p2PlayDeck, StackSeligman p2DiscardDeck) {
		// Determine the total number of cards each player has
		int p1Total = countCards(p1PlayDeck) + countCards(p1DiscardDeck);
		int p2Total = countCards(p2PlayDeck) + countCards(p2DiscardDeck);

		System.out.println("\nWar Card Game Summary");
		System.out.println("=====================");

		// Output the total number of cards in the deck
		System.out.print("The game started with " + numCards);
		if (numCards == 1)
			System.out.println(" card");
		else
			System.out.println(" cards");

		// Output the total number of rounds played
		if (numPlays == 1)
			System.out.println("There was " + numPlays + " play in the game");
		else
			System.out.println("There were " + numPlays + " plays in the game");

		// Exactly 1 person can have 0 cards to have a clear winner
		if ((p1Total == 0) ^ (p2Total == 0))
			System.out.println("The game ended with a clear winner");
		// If both have 0 cards, then the deck was empty and the game was never played
		else if ((p1Total == 0) && (p2Total == 0))
			System.out.println("The game was never played");
		// If both players have cards, then the game went past 1000 rounds
		else
			System.out.println("The game took too long");

		// Output the results for Player 1
		System.out.print("Player 1 ended with " + p1Total);
		if (p1Total == 1)
			System.out.println(" card");
		else
			System.out.println(" cards");

		// Output the results for Player 2
		System.out.print("Player 2 ended with " + p2Total);
		if (p2Total == 1)
			System.out.println(" card");
		else
			System.out.println(" cards");

		// Output the winner, if there is one
		System.out.print("The winner was ");
		if (p1Total > p2Total)
			System.out.println("Player 1");
		else if (p2Total > p1Total)
			System.out.println("Player 2");
		else
			System.out.println("no one");
	} // printResults

	/**
	 * The main method for the game
	 * 
	 * @param args The method arguments
	 */
	public static void main(String[] args) {
		// Declare and initialize variables for the game
		String deckPath;

		StackSeligman p1Play = new StackSeligman();
		StackSeligman p1Discard = new StackSeligman();

		StackSeligman p2Play = new StackSeligman();
		StackSeligman p2Discard = new StackSeligman();

		CardSeligman p1Card;
		CardSeligman p2Card;
		CardSeligman winningCard;

		boolean p1HasCards = true;
		boolean p2HasCards = true;

		int totalCards = 0;
		int round = 0;

		// Welcome messages
		System.out.println("Welcome to the War Game");
		System.out.println("This game simulates a game of War played between 2 players");

		// Inputting the file path for the deck
		System.out.print("\nEnter the filepath of the shuffled deck: ");
		deckPath = keyboard.next();

		// Deal the cards and get the total number of cards that have been dealt
		totalCards = deal(deckPath, p1Play, p2Play);

		// If totalCards is Integer.MAX_VALUE, then an error occurred when reading the
		// file
		// and we do not want to run the game if that is the case
		if (totalCards != Integer.MAX_VALUE) {

			// Handle special cases of 0 or 1 cards in the deck
			if ((totalCards == 0) || (p2Play.isEmpty()))
				p2HasCards = false;

			// Keep playing until a player runs out of cards or until the round reaches 1000
			while ((p1HasCards) && (p2HasCards) && (round < 1000)) {
				// Get the cards and determine the winner
				p1Card = playCard(p1Play);
				p2Card = playCard(p2Play);
				winningCard = compareCards(p1Card, p2Card);

				// Handle the cards based on the winner
				if (winningCard == p1Card) {
					// System.out.println("Player 1 wins with " + p1Card.toString() + " over " +
					// p2Card.toString());
					winningPlay(p1Discard, p1Card, p2Card);
				} // if
				else if (winningCard == p2Card) {
					// System.out.println("Player 2 wins with " + p2Card.toString() + " over " +
					// p1Card.toString());
					winningPlay(p2Discard, p2Card, p1Card);
				} // else if
				else {
					// System.out.println("Tie with " + p1Card.toString());
					tiePlay(p1Discard, p1Card, p2Discard, p2Card);
				} // else

				// Handle the decks if the play decks become empty
				if (p1Play.isEmpty())
					p1HasCards = moveDiscardToPlay(p1Discard, p1Play);
				if (p2Play.isEmpty())
					p2HasCards = moveDiscardToPlay(p2Discard, p2Play);

				// Increment the round number
				round++;
			} // while

			// Display the results of the game
			printResults(totalCards, round, p1Play, p1Discard, p2Play, p2Discard);

			// Closing message
			System.out.println("\nThank you for playing the War Game. Goodbye");
		} // if

		// Close the keyboard
		keyboard.close();
	} // main
} // BattleDemoSeligman
