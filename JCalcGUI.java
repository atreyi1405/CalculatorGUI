
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



import javax.swing.JButton;
import javax.swing.SwingUtilities;

import acm.gui.*;
import acm.program.*;
/**
 * 
 * This program builds a GUI calculator that works on infix to postfix conversion
 */
public class JCalcGUI extends Program{
	
	postFix pf=new postFix();
	
/* Private instance variable */
	private DoubleField infixField;
	private DoubleField postfixField;
	
/** Program dimensions */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 500;
	
	

/* Private static array of JButtons */
	
	JButton buttons[]= new JButton[28];
	String text[]= new String[]{"C", "", "", "/", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "", "=", "(", ")", "", "", "", "", "", "Quit" };
	
	
/*Initialize graphical user interface */
	    
	    public void init() {
	    setLayout(new TableLayout(9,4));   //9 rows, 4 columns
	    setBackground(Color.CYAN);   //Setting Background Color
		infixField= new DoubleField();  
    	infixField.setActionCommand("=");
    	infixField.addActionListener(this);  //adding actionlistener
    	infixField.addKeyListener(this);
		add(infixField, "gridwidth=4");   //gridwidth is used so that the infix row covers all columns as per design requirements
		postfixField= new DoubleField();
		add(postfixField, "gridwidth=4");  
		for (int i = 0; i < buttons.length; i++) {
		    buttons[i] = createButton(text[i]);    //createButton method described below
		    add(buttons[i]);
		    infixField.setActionCommand(text[i]);
		    buttons[i].addActionListener(this);   
		    
		}
		addActionListeners();  //adding actionlisteners
	}
	    
	    
	    
	/* The calculator works best when the numbers are entered through keyboard */
	    
	    public void actionPerformed(ActionEvent e) {
	    	
	    	String formula="";
	        String cmd= e.getActionCommand();
	        if(cmd.equals("=")) {
	            String infix= infixField.getText();
	            Queue Qin = pf.parse (infix); // Parse input string
		        Queue pfResult = pf.In2Post(Qin); // Convert to postfix
		        double eval = pf.PostEval(pfResult); // Evaluate the expression
		        postfixField.setValue(eval);	
	    	}
	        
	        else if(cmd.equals("C")) {   //Clear when "C" is clicked
	        	infixField.setText("");
	        	postfixField.setText("");
	        }
	        
	        else if(cmd.equals("Quit")) {    //Exit when "Quit" is clicked
	        	System.exit(0);
	        }
	    
	        else {
	        	
	        	formula=formula+cmd;
	        	
	        	infixField.setText(formula);
	        	
	        }
	    
	    }
	    
	 /* Enter button works the same way as "=" JButton */
	    
	    public void keyPressed(KeyEvent e) {
	    	int key=e.getKeyCode();
	    	if(key==KeyEvent.VK_ENTER) {
	    		String infix= infixField.getText();
	            Queue Qin = pf.parse (infix); // Parse input string
		        Queue pfResult = pf.In2Post(Qin); // Convert to postfix
		        double eval = pf.PostEval(pfResult); // Evaluate the expression
		        postfixField.setValue(eval);
	    	}
	    	
	    	JButton myButton=new JButton("Enter");
	    	SwingUtilities.getRootPane(myButton).setDefaultButton(myButton);
	    	
	    }
	    
	/** createButton adds all the jbuttons in the frame */
	    public JButton createButton(String s) {
	        JButton btn1 = new JButton(s);
	        return btn1;
	    }
}