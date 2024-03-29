import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Bet extends JFrame implements ActionListener{
	
	//game states 
	private enum GameState {
		BETTING, PLAYER_TURN, GAME_OVER
	}
	//declare the game state
	private GameState gameState;
	
	//create the game deck
	private Decks decks;
	
	//funds variables, holdFunds holds the original funds in case user wants to return to main menu
	static int playFunds;
	static int holdFunds;
	int betFunds = 0;
	int count = 0;
	
	//create an error box in case user doesn't have enough funds to bet
	JOptionPane notEnoughFundsError;
	
	//create container
	Container container = getContentPane();

	//create a label for funds and bet amount
	JLabel fundsLabel;
	JLabel betAmountLabel;
	JLabel you;
	JLabel dealer;
	JLabel youWin;
	JLabel youLose;
	JLabel busted;
	JLabel dealerScore;
	JLabel playerScore;
	
	//declare 6 JButtons for the 6 betting chips
	JButton chip1;
	JButton chip5;
	JButton chip10;
	JButton chip20;
	JButton chip50;
	JButton chip100;
	//create a button to allow user to go back to the dashboard in case they have no funds
	JButton goBackToDash;
	//create a button to bet
	JButton bet;
	
	//--------------------------------------------------------
	//Game
	//create buttons for the player to hit stand or surrender
	JButton hit;
	JButton stand;
	JButton surrender;
	JButton playAgain;
	
	
	//Function to Load the Game
    public void createGame(int funds) {
    	playFunds = funds;
    	holdFunds = funds;
    	Bet frame = new Bet();
    	frame.setTitle("Game");
    	frame.getContentPane().setBackground(new Color(53, 101, 77));
    	frame.setVisible(true);
    	frame.setBounds(10, 15, 1300, 900);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
	}
    
	//PlayFunction constructor
	Bet() {
		//set the game state
		gameState = GameState.BETTING;
		
		//create error dialog box
		notEnoughFundsError = new JOptionPane();
		
		//--------------------------------------------------------
		//LABELS
		//create the labels here
		fundsLabel = new JLabel("Funds: " + playFunds);
		betAmountLabel = new JLabel ("Bet Amount: " + betFunds);
		//game
		you = new JLabel ("YOU");
		dealer = new JLabel ("DEALER");
		youWin = new JLabel ("YOU WIN!");
		youLose = new JLabel ("YOU LOSE.");
		busted = new JLabel ("YOU BUSTED.");
		dealerScore = new JLabel();
		playerScore = new JLabel();
		
		//--------------------------------------------------------
		//BUTTONS
		//create the 6 JButton/chips with an ImageIcon 
		chip1 = new JButton(new ImageIcon ("01.png"));
		chip5 = new JButton(new ImageIcon("02.png"));
		chip10 = new JButton(new ImageIcon("03.png"));
		chip20 = new JButton(new ImageIcon("04.png"));
		chip50 = new JButton(new ImageIcon("05.png"));
		chip100 = new JButton(new ImageIcon("06.png"));
		bet = new JButton ("Bet");
		goBackToDash = new JButton ("Return");
		playAgain = new JButton ("Play Again?");
	
		
		//game
		hit = new JButton("Hit");
		stand = new JButton("Stand");
		surrender = new JButton("Surrender");
		
        //set the container layout to empty
        container.setLayout(null);
        //set labels and buttons
        setLabelButton();
        //add labels and buttons to container
		addComponentsToContainer();
		//add action listeners to labels and buttons
		addActionEvent();
		
	}
	
	//Function to Set Labels and Buttons (borders, location, foreground, font size, etc)
	public void setLabelButton() {
		
		//make buttons transparent
		bet.setContentAreaFilled(false);
		goBackToDash.setContentAreaFilled(false);
		playAgain.setContentAreaFilled(false);
		chip1.setContentAreaFilled(false);
		chip5.setContentAreaFilled(false);
		chip10.setContentAreaFilled(false);
		chip20.setContentAreaFilled(false);
		chip50.setContentAreaFilled(false); 	
		chip100.setContentAreaFilled(false);
		
		hit.setContentAreaFilled(false);
		stand.setContentAreaFilled(false);
		surrender.setContentAreaFilled(false);

		//set borders to empty
		bet.setBorder(BorderFactory.createEmptyBorder());
		goBackToDash.setBorder(BorderFactory.createEmptyBorder());
		playAgain.setBorder(BorderFactory.createEmptyBorder());
		chip1.setBorder(BorderFactory.createEmptyBorder());
		chip5.setBorder(BorderFactory.createEmptyBorder());
		chip10.setBorder(BorderFactory.createEmptyBorder());
		chip20.setBorder(BorderFactory.createEmptyBorder());
		chip50.setBorder(BorderFactory.createEmptyBorder());
		chip100.setBorder(BorderFactory.createEmptyBorder()); 
		hit.setBorder(BorderFactory.createEmptyBorder()); 
		stand.setBorder(BorderFactory.createEmptyBorder()); 
		surrender.setBorder(BorderFactory.createEmptyBorder()); 
		
		//set location of buttons and labels
		betAmountLabel.setBounds(100, 750, 700, 60);
		fundsLabel.setBounds(962,75,300,60);
		bet.setBounds(599,500,100,60);
		goBackToDash.setBounds(955, 750, 175, 60);
		playAgain.setBounds(100, 750, 400, 60);
        chip1.setBounds(345, 330, 100, 100);
        chip5.setBounds(445, 330, 100, 100);
        chip10.setBounds(545, 330, 100, 100);
        chip20.setBounds(645, 330, 100, 100);
        chip50.setBounds(745, 330, 100, 100);
        chip100.setBounds(845, 330, 100, 100);
        
        you.setBounds(605, 750, 200, 60);
        dealer.setBounds(563, 60, 500, 60);
        youWin.setBounds(550, 400, 500, 60);
        youLose.setBounds(500, 400, 500, 60);
        busted.setBounds(530, 400, 500, 60);
        dealerScore.setBounds(250,190,100,100);
        playerScore.setBounds(250,590,100, 100);
        hit.setBounds(350,420,80, 40);
        stand.setBounds(590,420,120, 40);
        surrender.setBounds(845,420,180, 40);
        
        //set foreground and font size
        bet.setForeground(Color.white);
        bet.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        goBackToDash.setForeground(Color.white);
        goBackToDash.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        playAgain.setForeground(Color.white);
        playAgain.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        fundsLabel.setForeground(Color.white);
        fundsLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        betAmountLabel.setForeground(Color.white);
        betAmountLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
       
        you.setForeground(Color.white);
        you.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        dealer.setForeground(Color.white);
        dealer.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        youWin.setForeground(Color.white);
        youWin.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        youLose.setForeground(Color.white);
        youLose.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        busted.setForeground(Color.white);
        busted.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        dealerScore.setForeground(Color.white);
        dealerScore.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 70));
        playerScore.setForeground(Color.white);
        playerScore.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 70));
        hit.setForeground(Color.white);
        hit.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        stand.setForeground(Color.white);
        stand.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        surrender.setForeground(Color.white);
        surrender.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        
        
        //hide the game stuff until later
        you.setVisible(false);
        dealer.setVisible(false);
        youWin.setVisible(false);
        youLose.setVisible(false);
        busted.setVisible(false);
        dealerScore.setVisible(false);
        playerScore.setVisible(false);
        hit.setVisible(false);
        stand.setVisible(false);
        surrender.setVisible(false);
        playAgain.setVisible(false);
	}
	//Add Buttons and Labels to Container
    public void addComponentsToContainer() {
    	
    	//add to container
    	container.add(betAmountLabel);
    	container.add(fundsLabel);
    	container.add(bet);
    	container.add(goBackToDash);
    	container.add(playAgain);
        container.add(chip1);
        container.add(chip5);
        container.add(chip10);
        container.add(chip20);
        container.add(chip50);
        container.add(chip100);
        //game
        container.add(you);
        container.add(dealer);
        container.add(youWin);
        container.add(youLose);
        container.add(busted);
        container.add(playerScore);
        container.add(dealerScore);
        container.add(hit);
        container.add(stand);
        container.add(surrender);
        
        revalidate();
        repaint();
      }
    
    //Add Action Listen
    public void addActionEvent() {
    	
    	//add action listeners
    	bet.addActionListener(this);
    	goBackToDash.addActionListener(this);
    	playAgain.addActionListener(this);
    	chip1.addActionListener(e -> handleChipButtonAction(1));
    	chip5.addActionListener(e -> handleChipButtonAction(5));
    	chip10.addActionListener(e -> handleChipButtonAction(10));
    	chip20.addActionListener(e -> handleChipButtonAction(20));
    	chip50.addActionListener(e -> handleChipButtonAction(50));
    	chip100.addActionListener(e -> handleChipButtonAction(100));
    	hit.addActionListener(this);
    	stand.addActionListener(this);
    	surrender.addActionListener(this);
    }
 
    //Handle the chip button actions
    private void handleChipButtonAction(int chipValue) {
        if (chipValue > playFunds) {
            notEnoughFundsError.showMessageDialog(null, "Not Enough Funds to Bet.\nReturn to the main menu and add funds to the bank");
        } else {
            // Update variables
            betFunds += chipValue;
            playFunds -= chipValue;

            // Update labels
            fundsLabel.setText("Funds: " + playFunds);
            betAmountLabel.setText("Bet Amount: " + betFunds);
        }
    }

    //Initialize the decks when the game begins
	public void initializeDecks() {
		decks = new Decks();
		
		//add two cards to the dealer deck
		decks.addToDealerDeck(decks.getCurrentCardFromGameDeck(count));
		count++;
		decks.addToDealerDeck(decks.getCurrentCardFromGameDeck(count));
		count++;
		
		updateDealerDeck(decks.getDealerDeck());
		
		//add two cards to the player deck
		decks.addToPlayerDeck(decks.getCurrentCardFromGameDeck(count));
		count++;
		decks.addToPlayerDeck(decks.getCurrentCardFromGameDeck(count));
		count++;
		updatePlayerDeck(decks.getPlayerDeck());
	}
	
	//Update the Dealer Deck for the GUI
	public void updateDealerDeck(Vector<Card>dDeck) {
    	
    	//declare an empty JLabel to add to the container
    	JLabel label1 = null;
    	//keep score
    	int score = 0;
    	
    	for (int i = 0; i < dDeck.size(); i++) {
    		
    		//create a new label
    		label1 = new JLabel();
    		
    		//set the icon of the label by getting iterating through the dealer's deck and getting the card image and setting that as the icon
    		label1.setIcon(new ImageIcon(dDeck.get(i).getImage()));
    		
    		//add the label(s) to the container
    		container.add(label1);
    		
    		//set the cards in a uniform way
    		label1.setBounds(345 + (i*70), 150, 132, 185);
    		score+=dDeck.get(i).getValue();
    	}
    	
    	//update the dealer score
	   	dealerScore.setText(String.valueOf(score));
	   	
    	//revalidate and repaint
		revalidate();
		repaint();
    }
    
	//Update the Player Deck for the GUI
	public void updatePlayerDeck(Vector<Card>pDeck) {
	    	
		//declare an empty JLabel to add to the container
	    JLabel label2 = null;
	    //keep score
	   	int score = 0;
	   	
	   	for (int i = 0; i < pDeck.size(); i++) {
	   		//create a new label
	   		label2 = new JLabel();
	    		
	    	//set the icon of the label by getting iterating through the dealer's deck and getting the card image and setting that as the icon
	   		label2.setIcon(new ImageIcon(pDeck.get(i).getImage()));
	   		
	   		//add the label(s) to the container
	   		container.add(label2);
	    		
	   		//set the cards in a uniform way
	   		label2.setBounds(345 + (i*70), 550, 132, 185);
    		score+=pDeck.get(i).getValue();
    	}
	   	
	   	//update the player score
	   	playerScore.setText(String.valueOf(score));
	   	
	   	//revalidate and repaint
	   	revalidate();
		repaint();
	}
	
	//reupdate GUI stuff after the game ends
	private void gameOver() {
		//update labels
		fundsLabel.setText("Funds: " + playFunds);
		//change game state
		gameState = GameState.GAME_OVER;
		//hide the hit, stand, and surrender buttons
		hit.setVisible(false);
		stand.setVisible(false);
		surrender.setVisible(false);
		//hide the dealer and you labels
		dealer.setVisible(false);
		you.setVisible(false);
		//show your funds
		fundsLabel.setVisible(true);
		
		//show a YOU WIN label
		youWin.setVisible(true);
		
		//show a return to main menu button
		goBackToDash.setVisible(true);
		//show a play again button
		playAgain.setVisible(true);
	}
	//GUI stuff if player loses 
	//Reset game after it ends 
	private void resetGame() {
	    // Reset betting variables and labels
	    betFunds = 0;
	    count = 0;
	    betAmountLabel.setText("Bet Amount: " + betFunds);
	    fundsLabel.setText("Funds: " + playFunds);

	    // Show betting elements again
	    chip1.setVisible(true);
	    chip5.setVisible(true);
	    chip10.setVisible(true);
	    chip20.setVisible(true);
	    chip50.setVisible(true);
	    chip100.setVisible(true);
	    bet.setVisible(true);
	    goBackToDash.setVisible(true);
	    betAmountLabel.setVisible(true);
	    fundsLabel.setVisible(true);

	    // Reset game state
	    gameState = GameState.BETTING;

	    // Clear dealer and player decks
	    container.removeAll();
	    container.revalidate();
	    container.repaint();

	    // Show the game buttons again
	    youWin.setVisible(false);
	    youLose.setVisible(false);
	    busted.setVisible(false);
	    dealerScore.setVisible(false);
	    playerScore.setVisible(false);
	    hit.setVisible(false);
	    stand.setVisible(false);
	    surrender.setVisible(false);
	    playAgain.setVisible(false);

	    // Set the labels and buttons again
	    setLabelButton();
	    addComponentsToContainer();
	}
	
    //User actions
	public void actionPerformed(ActionEvent e) {
		
		//GAME STATE : BETTING ---------------------------------------------------------------
		//If user clicks the goBackToDash/return button take them back to the main menu
		if (e.getSource() == goBackToDash && gameState == GameState.BETTING) {
			this.dispose();
			Dashboard newDash = new Dashboard();
			newDash.createDashboard(holdFunds);
		}
		if (e.getSource() == goBackToDash && gameState == GameState.GAME_OVER){
			this.dispose();
			Dashboard newDash = new Dashboard();
			newDash.createDashboard(playFunds);
		}
		//If user clicks on the bet button start the game
		if (e.getSource() == bet) {
			//lets hide the chips, buttons and labels
			chip1.setVisible(false);
			chip5.setVisible(false);
			chip10.setVisible(false);
			chip20.setVisible(false);
			chip50.setVisible(false);
			chip100.setVisible(false);
			bet.setVisible(false);
			goBackToDash.setVisible(false);
			
			//set the player and dealer stuff
			dealer.setVisible(true);
			you.setVisible(true);
			betAmountLabel.setVisible(false);
			fundsLabel.setVisible(false);
			
			//set the scores to visible
			playerScore.setVisible(true);
			dealerScore.setVisible(true);
			
			
			//give the options to hit, stand or surrender now
			hit.setVisible(true);
			stand.setVisible(true);
			surrender.setVisible(true);
			
			gameState = GameState.PLAYER_TURN;
			initializeDecks();
		}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//GAME STATE : PLAYER TURN ---------------------------------------------------------------
		//Player Hits
		if (e.getSource() == hit && gameState == GameState.PLAYER_TURN) {
			//add card to Player deck after hit and update the GUI
			decks.addToPlayerDeck(decks.getCurrentCardFromGameDeck(count));
			count++;
			updatePlayerDeck(decks.getPlayerDeck());
			
			//CHECK FOR BUST
			if(decks.getPlayerScore() > 21) {
				gameOver();
				
			}
			//CHECK FOR BLACKJACK
			else if (decks.getPlayerScore() == 21) {
				//give winnings to player
				playFunds += betFunds*2;
				gameOver();
			}
		}
		//Player Stands
		if (e.getSource() == stand && gameState == GameState.PLAYER_TURN) {
			//Dealer hits until he has a score of 17 or more and at this point he stands
			while (decks.getDealerScore() < 17) {
				decks.addToDealerDeck(decks.getCurrentCardFromGameDeck(count));
				count++;
				updateDealerDeck(decks.getDealerDeck());
			}
			//Check for win condition: Dealer Busts or Player Score is greater than Dealer Score
			if (decks.getDealerScore() > 21 || decks.getPlayerScore() > decks.getDealerScore()) {
				//give winnings to player
				playFunds += betFunds*2;
				gameOver();
			}
			//Dealer wins via score
			else {
				gameOver();
			}
		
		}
		//Player Surrenders
		if (e.getSource() == surrender && gameState == GameState.PLAYER_TURN) {
			gameOver();
		}
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//GAME STATE : GAME OVER ---------------------------------------------------------------
		//Player Plays Again
		if (e.getSource() == playAgain && gameState == GameState.GAME_OVER) {
			resetGame();
		}
	}
}
