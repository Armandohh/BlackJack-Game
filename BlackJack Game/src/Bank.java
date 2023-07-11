import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;


public class Bank extends JFrame implements ActionListener{
	
	//funds variable
	static int bankFunds;
	//create container
	Container container = getContentPane();
	//create a label to display funds
	JLabel fundsLabel;
	
	//add a text field to enter funds
	JFormattedTextField addTheseFunds;
	
	//add a button to add funds
	JButton add;
	//create a button to allow user to go back to the dashboard in case they have no funds
	JButton goBackToDash;
	
	//add a numberFormat and numberFormatter for the formatted text field to only allow user to enter integers
	NumberFormat integer;
	NumberFormatter numberFormatter;
	
	//Function to Create the Game
	public void createBank (int funds) {
		bankFunds = funds;
		Bank frame = new Bank();
		frame.setTitle("Bank");
		frame.getContentPane().setBackground(new Color(53, 101, 77));
    	frame.setVisible(true);
    	frame.setBounds(10, 15, 1300, 900);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
	}
	
	//Bank Constructor
	Bank() {
		//create the number format and number formatter
		integer = NumberFormat.getIntegerInstance();
		numberFormatter = new NumberFormatter(integer);
		numberFormatter.setValueClass(Integer.class);
		//dont allow anything other than char
		numberFormatter.setAllowsInvalid(false);
		//min value of int to be 1
		numberFormatter.setMinimum(1);
		//max value of int to be 500
		numberFormatter.setMaximum(500);
				
		//create buttons
		add = new JButton ("Add");
		goBackToDash = new JButton ("Return");
		
		//create text field
		addTheseFunds = new JFormattedTextField(numberFormatter);
		
		//create labels and buttons
		fundsLabel = new JLabel ("Your Funds: " + bankFunds);
		
		//set the container layout to empty
		container.setLayout(null);
		
		//set labels and buttons
		setLabelButton();
		//add labels and buttons to container
		addComponentsToContainer();
		//add action listeners to labels and buttons
		addActionEvent();
	}
	
	//Function to Set Labels and Buttons
	public void setLabelButton() {
		//set background and border
		add.setBorderPainted(true);
		add.setBackground(Color.gray);
		goBackToDash.setBorder(BorderFactory.createEmptyBorder());
		goBackToDash.setContentAreaFilled(false);
		
		//set location
		fundsLabel.setBounds(438,200,500,60);
		addTheseFunds.setBounds(330,300,600,100);
		addTheseFunds.setHorizontalAlignment(JFormattedTextField.CENTER);
		add.setBounds(430,500,400,50);
		goBackToDash.setBounds(955, 700, 175, 60);
		
		//set foreground and font
		fundsLabel.setForeground(Color.white);
        fundsLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
        addTheseFunds.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));	
        add.setForeground(Color.black);
        add.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        goBackToDash.setForeground(Color.white);
        goBackToDash.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
	}
	
	//Add Buttons and Labels to Container
	public void addComponentsToContainer() {
		container.add(goBackToDash);
		container.add(fundsLabel);
		container.add(addTheseFunds);
		container.add(add);
		revalidate();
		repaint();
	}
	
	//Add Action Listeners
	public void addActionEvent() {
		goBackToDash.addActionListener(this);
		add.addActionListener(this);
	}
	
	//Actions performed when user clicks on buttons
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//If user clicks the goBackToDash/return button take them back to the main menu
		if (e.getSource() == goBackToDash) {
			this.dispose();
			Dashboard newDash = new Dashboard();
			newDash.createDashboard(bankFunds);
		}
		if (e.getSource() == add) {
			//if the text field is empty and they are trying to add prompt a dialog box with an error
			if (addTheseFunds.getValue() == null)
				JOptionPane.showMessageDialog(null, "Add some funds or return to the main menu.");
			//else dont let the user exceed more than 1500 
			else if ((bankFunds + (int)addTheseFunds.getValue()) > 1500)
				JOptionPane.showMessageDialog(null, "You have a lot of funds.\nCannot add funds when over 1500.\nPlay some games.");
			//else put the user input into their funds and update label	
			else {
				//update funds
				bankFunds += (int)addTheseFunds.getValue();
				//update label
				fundsLabel.setText("Your Funds: " + bankFunds);
			}	
		}
		
	}
}
