import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Dashboard extends JFrame implements ActionListener {
	
	//create a variable to hold user funds to play the game
	static int funds;
	
	//create a container to hold the labels and buttons in order to be displayed
	Container container = getContentPane();

	//declare labels and buttons
	JLabel BlackJackLabel;
	JButton playButton;
	JButton bankButton;
	JButton quitButton;
	
	
	//call function to load the dashboard
	public void createDashboard(int passedFunds) {
		Dashboard frame = new Dashboard();
	    frame.setTitle("Dashboard");
	    frame.getContentPane().setBackground(new Color(53, 101, 77));
	    frame.setVisible(true);
	    frame.setBounds(10, 15, 1300, 900);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	    frame.setLocationRelativeTo(null);
	    
	    //this is used to update the funds from an end game
	    funds = passedFunds;
	}
	
	//Constructor
	Dashboard() {
		
		//create the labels and buttons
		BlackJackLabel = new JLabel("BLACKJACK");
		playButton = new JButton("Play");
		bankButton = new JButton("Bank");
		quitButton = new JButton("Quit");
			
		//set container layout to null
	    container.setLayout(null);
		setLabelButton();
		addComponentsToContainer();
		addActionEvent();
		}
		

	//Set Location and Size of buttons
	public void setLabelButton() {
		Font f = BlackJackLabel.getFont();
		
		//Blackjack Label
	    BlackJackLabel.setBounds(462, 4, 500, 200);
	    BlackJackLabel.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
	    BlackJackLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 60));
	    BlackJackLabel.setForeground(Color.black);
	    
	    //Play Button
	    playButton.setBounds(230, 200, 800, 150);
	    playButton.setBorderPainted(false);
	    playButton.setBackground(Color.gray);
	    playButton.setForeground(Color.black);
	    playButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
	    playButton.setOpaque(true);
	    
	    //Bank Button
	    bankButton.setBounds(230, 400, 800, 150);
	    bankButton.setBorderPainted(false);
	    bankButton.setBackground(Color.gray);
	    bankButton.setForeground(Color.black);
	    bankButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
	    bankButton.setOpaque(true);
	  
	    //Quit Button
	    quitButton.setBounds(230, 600, 800, 150);
	    quitButton.setBorderPainted(false);
	    quitButton.setBackground(Color.gray);
	    quitButton.setForeground(Color.black);
	    quitButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
	    quitButton.setOpaque(true);

	  }
	
	//Add buttons to container
	public void addComponentsToContainer() {
		container.add(BlackJackLabel);
	    container.add(playButton);
	    container.add(bankButton);
	    container.add(quitButton);
	  }
	  
	  //Add action listeners when the user clicked the buttons
	  public void addActionEvent() {
		   playButton.addActionListener(this);
		   quitButton.addActionListener(this);
		   bankButton.addActionListener(this);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if user clicks the play button start a Game
		if (e.getSource() == playButton) {
			Bet game = new Bet();
			game.createGame(funds);
			this.dispose();
		}
		
		//if user clicks the bank button load the bank menu
		else if (e.getSource() == bankButton) {
			Bank bank = new Bank();
			bank.createBank(funds);
			this.dispose();
		}
		//if the user quits close the application
		else if (e.getSource() == quitButton) {
			System.exit(ABORT);
		}
	}

}
