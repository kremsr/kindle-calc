import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import com.amazon.kindle.kindlet.AbstractKindlet;
import com.amazon.kindle.kindlet.KindletContext;
import com.amazon.kindle.kindlet.event.KindleKeyCodes;
import com.amazon.kindle.kindlet.ui.KBoxLayout;
import com.amazon.kindle.kindlet.ui.KButton;
import com.amazon.kindle.kindlet.ui.KImage;
import com.amazon.kindle.kindlet.ui.KLabel;
import com.amazon.kindle.kindlet.ui.KLabelMultiline;
import com.amazon.kindle.kindlet.ui.KMenu;
import com.amazon.kindle.kindlet.ui.KMenuItem;
import com.amazon.kindle.kindlet.ui.KPanel;
import com.amazon.kindle.kindlet.ui.KTextComponent;
import com.amazon.kindle.kindlet.ui.KTextField;
import com.amazon.kindle.kindlet.ui.KindletUIResources;
import com.amazon.kindle.kindlet.ui.KindletUIResources.KFontStyle;
import com.amazon.kindle.kindlet.ui.border.KLineBorder;

public class MyKindlet extends AbstractKindlet {    

    KLabel label100 = new KLabel("          ");
    KLabel label200 = new KLabel("          ");
    KLabel label300 = new KLabel("          ");
	KLabel label400 = new KLabel("          ");
	KLabel label500 = new KLabel("          ");
	KLabel label600 = new KLabel("          ");
	KLabel label700 = new KLabel("          ");
    int i = 0;
	
	//Defining the Strings
	String priceMeal = "0";
    String percentTip = "0";
    String numPeople = "0";
    String tipAmountString = "0";
    String pricePerPersonString = "0";
    String totalMealPriceString = "0";
	
    //Defining the Double values
    double mealPrice = 0;
	double percentToTip = 0;
	double tipAmount = 0;
	double totalMealPrice = 0;
	double numOfPeople = 0;
	double pricePerPerson = 0;
	
	//Defining the formatter
	DecimalFormat twoDigits = new DecimalFormat("0.00");
	
	final KTextField field1 = new KTextField(5);  //User to input price of meal
	final KPanel panel1 = new KPanel();
	
	
	// Code for the Menu items
	final KMenu menu = new KMenu();
    final KMenuItem menuItem = new KMenuItem("About");
    final KMenuItem menuItem2 = new KMenuItem("Help");
    final KMenuItem menuItem3 = new KMenuItem("Tip Calculator");
    final KMenuItem menuItem4 = new KMenuItem("Quick Reference");
    final KMenuItem menuItem5 = new KMenuItem("Main Menu");
    
    //Creating the back key functionality items
    //There are several for the different scenarios needed within the program
	
    //This is the code for the back key being consumed only

    KeyEventDispatcher eventConsume = new KeyEventDispatcher() {

        public boolean dispatchKeyEvent(final KeyEvent key) {
            if (key.getKeyCode() == KindleKeyCodes.VK_BACK) {
                key.consume();
                return true;
            }
            return false;
        }
    };
    
    
	//creating a button
	final KButton tipCalcButton = new KButton("tip calculator");
	final KButton aboutButton = new KButton("about");
	final KButton helpButton = new KButton("help");
	final KButton quickReferenceButton = new KButton("quick reference");
	final KButton homeButton = new KButton("main menu");
	final KButton homeButton2 = new KButton("main menu");
	final KButton homeButton3 = new KButton("main menu");
   	
    final ComponentAdapter tipCalcButtonFocus = new ComponentAdapter(){
    	public void componentShown(final ComponentEvent e) {
        	tipCalcButton.requestFocus();
        	}
    };
    
    final ComponentAdapter fieldOneEnable = new ComponentAdapter(){
    	public void componentShown(final ComponentEvent e) {
        	field1.requestFocus();
        	}
    
    };
    /*
    KeyEventDispatcher event_about = new KeyEventDispatcher() {

        public boolean dispatchKeyEvent(final KeyEvent key) {
            if (key.getKeyCode() == KindleKeyCodes.VK_BACK) {
            	key.consume();
                return true;
            }
            return false;
        }
          
    };
    
    KeyEventDispatcher eventLandingPage = new KeyEventDispatcher() {

        public boolean dispatchKeyEvent(final KeyEvent key) {
            if (key.getKeyCode() == KindleKeyCodes.VK_BACK) {
            	key.consume();
                return true;
            }
            return false;
        }
          
    };        
	*/
    
    
    
    /*
     * This is starting of the main part of the code-base.  May need to go ahead and create another 
     * section to display this, so that I can easily get back to it with the back button.
     * 
     */ 
    public void create(final KindletContext context) {

       	final Container root = context.getRootContainer();		

    	//Consuming the back button
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(eventConsume);

        Font f3= KindletUIResources.getInstance().getFont(
  			  KindletUIResources.KFontFamilyName.SERIF,
  			  21,
  			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
  			  false);  // antialiasing off

        tipCalcButton.setFont(f3);
        aboutButton.setFont(f3);
        helpButton.setFont(f3);
        quickReferenceButton.setFont(f3);
        
        //Button listeners
    	tipCalcButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent Event) {
			
				// Action to be completed when the button is pressed
				//tipCalcButton.setFocusable(false);
				page2(root);
				field1.requestFocus();
		        //This is for the Focus so that it goes to the first entry, but doesn't go back to the
				//first entry after screen saver and other inputs
				//root.addComponentListener(fieldOneEnable);
				
				/*
				root.addComponentListener(new ComponentAdapter() {
		            public void componentShown(final ComponentEvent e) {
		            	i++;
		            	if(i==1){
		            	field1.requestFocus();
		            	}
		            	else{
		            	}            	
		            }
		        }); */
			}	
    	});
    	
    	//Code to run with the Help Button
    	helpButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				//helpButton.setFocusable(false);
				help(root);
				
			}
    		
    	});
    	
    	//Code to run with the About Button
    	aboutButton.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg1) {
    			//aboutButton.setFocusable(false);
    			about(root);
    		}
    	});  	
    	
    	//Code to run with the Quick Reference Button
    	quickReferenceButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				//quickReferenceButton.setFocusable(false);
				quickReference(root);
				
			}
    		
    	});
    	
    	//Code to run with the home Button
    	homeButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
        		landingPage(root);				
			}
    		
    	});
    	//Code to run with the home Button
    	homeButton2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
        		landingPage(root);				
			}
    		
    	});
    	//Code to run with the home Button
    	homeButton3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
        		landingPage(root);				
			}
    		
    	});
		
    	menuItem.setActionCommand("Tip Calculator");
        menuItem.setActionCommand("About");
        menuItem.setActionCommand("Help");
        menuItem.setActionCommand("Quick Reference");
        menuItem.setActionCommand("Home");
        menu.add(menuItem3);
        menu.add(menuItem);
        menu.add(menuItem2);
        menu.add(menuItem4);
        context.setMenu(menu);
       
        //This is the code for the action listeners here for menu item (should be the same as the button presses from this page)
         
        menuItem.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
            	//Code to take us to the about section
            	about(root);
            	//homeButton.requestFocus();
            }
        });
        
        menuItem2.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		//Code to take us to the help section
        		help(root);
        		//homeButton.requestFocus();
        	}
        });
        
        menuItem3.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		//Code to take us to the tip calculator section
        		page2(root);
        		field1.requestFocus();
    	        //Trying to work on the focus issue
    			//root.addComponentListener(fieldOneEnable);
        		
        		/*root.addComponentListener(new ComponentAdapter() {
    	            public void componentShown(final ComponentEvent e) {
    	            	i++;
    	            	if(i==1){
    	            	field1.requestFocus();
    	            	}
    	            	else{
    	            	}            	
    	            }
    	        });*/
    		}	
    	});
        	
        
        menuItem4.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		//Code to take us to the help section
        		quickReference(root);
        		//homeButton.requestFocus();
        	}
        });
        
        menuItem5.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		//Code to take us to the help section
        		landingPage(root);
        		tipCalcButton.requestFocus();
        		//root.addComponentListener(new ComponentAdapter() {
		          //  public void componentShown(final ComponentEvent e) {
		            //	tipCalcButton.requestFocus();
		            	//}
		        //});
        	}
        });
         
        KPanel panel21 = new KPanel();
        KPanel panel22 = new KPanel();
        //KPanel panel23 = new KPanel();
        //KPanel panel24 = new KPanel();
        //KPanel panel25 = new KPanel();
        //KPanel panel26 = new KPanel();
        KPanel panel27 = new KPanel(new GridLayout(4,1,20,20));
        
    	// Adding the image for the landing page
        final String IMAGE_NAME_1 = "kindle1.jpg";
        final Image image1 = Toolkit.getDefaultToolkit().createImage(getClass().getResource(IMAGE_NAME_1));
        

        panel21.add(new KImage(image1));
        panel21.setFocusable(false);
    	//panel23.add(tipCalcButton);
    	//panel24.add(aboutButton);
    	//panel25.add(helpButton);
    	//panel26.add(quickReferenceButton);
    	panel27.add(tipCalcButton);
    	panel27.add(aboutButton);
    	panel27.add(helpButton);
    	panel27.add(quickReferenceButton);
        
        
        panel22.setLayout(new KBoxLayout(panel22, KBoxLayout.Y_AXIS));

        
        panel22.add(panel21);
    	//panel22.add(panel23);
    	//panel22.add(panel24);
    	//panel22.add(panel25);
    	//panel22.add(panel26);
        panel22.add(panel27);
        root.add(label100, BorderLayout.NORTH);
        root.add(panel22, BorderLayout.CENTER);
        root.add(label200, BorderLayout.EAST);
        root.add(label300, BorderLayout.WEST);
        root.add(label400, BorderLayout.SOUTH);
        root.repaint();
        root.removeComponentListener(fieldOneEnable);
        root.removeComponentListener(tipCalcButtonFocus);
        //root.add(panel22, BorderLayout.SOUTH);
    }

    
    
    /*This will be the code section for the landing page, that we go back to from other pages
     * 
     * 
     * 
     */
    public void landingPage (final Container root){
    	
    	/*
    	//creating a button
    	final KButton tipCalcButton = new KButton("tip calculator");
    	final KButton aboutButton = new KButton("about");
    	final KButton helpButton = new KButton("help");
    	final KButton quickReferenceButton = new KButton("quick reference");
       	*/
    	//do I still need these listeners?  They should already be enabled the first time the program runs thru
    	//Button listeners

    	/*
    	tipCalcButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent Event) {
			
				// Action to be completed when the button is pressed
				tipCalcButton.setFocusable(false);
				page2(root);
				field1.requestFocus();
		        //This is for the Focus so that it goes to the first entry, but doesn't go back to the
				//first entry after screen saver and other inputs
				root.addComponentListener(new ComponentAdapter() {
		            public void componentShown(final ComponentEvent e) {
		            	i++;
		            	if(i==1){
		            	field1.requestFocus();
		            	}
		            	else{
		            	}            	
		            }
		        });
			}	
    	});
    	
    	//Code to run with the Help Button
    	helpButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				helpButton.setFocusable(false);
				help(root);
				
			}
    		
    	});
    	
    	//Code to run with the About Button
    	aboutButton.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg1) {
    			aboutButton.setFocusable(false);
    			about(root);
    		}
    	});  	
    	
    	//Code to run with the Quick Reference Button
    	quickReferenceButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				quickReferenceButton.setFocusable(false);
				quickReference(root);
				
			}
    		
    	});
    	*/
        Font f4= KindletUIResources.getInstance().getFont(
    			  KindletUIResources.KFontFamilyName.SERIF,
    			  21,
    			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
    			  false);  // antialiasing off

          tipCalcButton.setFont(f4);
          aboutButton.setFont(f4);
          helpButton.setFont(f4);
          quickReferenceButton.setFont(f4);
    	
    	menu.removeAll();
    	menu.add(menuItem3);
        menu.add(menuItem);
        menu.add(menuItem2);
        menu.add(menuItem4);
        
        
        KPanel panel21 = new KPanel();
        KPanel panel22 = new KPanel();
        //KPanel panel23 = new KPanel();
        //KPanel panel24 = new KPanel();
        //KPanel panel25 = new KPanel();
        //KPanel panel26 = new KPanel();
        KPanel panel27 = new KPanel(new GridLayout(4,1,20,20));
        
    	// Adding the image for the landing page
        final String IMAGE_NAME_1 = "kindle1.jpg";
        final Image image1 = Toolkit.getDefaultToolkit().createImage(getClass().getResource(IMAGE_NAME_1));
        

        panel21.add(new KImage(image1));
        panel21.setFocusable(false);
    	//panel23.add(tipCalcButton);
    	//panel24.add(aboutButton);
    	//panel25.add(helpButton);
    	//panel26.add(quickReferenceButton);
    	panel27.add(tipCalcButton);
    	panel27.add(aboutButton);
    	panel27.add(helpButton);
    	panel27.add(quickReferenceButton);
        
        
        panel22.setLayout(new KBoxLayout(panel22, KBoxLayout.Y_AXIS));

        
        panel22.add(panel21);
    	//panel22.add(panel23);
    	//panel22.add(panel24);
    	//panel22.add(panel25);
    	//panel22.add(panel26);
        panel22.add(panel27);
        root.removeAll();
        root.add(label100, BorderLayout.NORTH);
        root.add(panel22, BorderLayout.CENTER);
        root.add(label200, BorderLayout.EAST);
        root.add(label300, BorderLayout.WEST);
        root.add(label400, BorderLayout.SOUTH);
        root.repaint();
        root.removeComponentListener(fieldOneEnable);
        root.removeComponentListener(tipCalcButtonFocus);
        //root.add(panel22, BorderLayout.SOUTH);
    	tipCalcButton.requestFocus();
    }
    
    /*This the code section for the 'Help' page
     * 
     * 
     * 
     */
    public void help (final Container root){
    	
        KLabel label30 = new KLabel("     ");
    	KLabel label31 = new KLabel("       Help:");
    	KLabel label33 = new KLabel("     ");
    	//KPanel panel30 = new KPanel();
    	KPanel panel31 = new KPanel();
    	KPanel panel32 = new KPanel();
    	KPanel panel33 = new KPanel();
    	//KPanel panel34 = new KPanel();
        final KLabelMultiline labelMultiLine4 = new KLabelMultiline("\nTip Calculator input fields restrictions:\n-  Number of people < 100\n-  Tip percent < 1000%\n-  Current Amount < $99,999\n-  All values are to be positive\n\nBack Button Functionality:\nThe Back button is not operable within this application.  Please utilize the Menu key functionality to navigate the application\n\nFor further assistance please feel free to send an email to:\nspikybear123@gmail.com \n\n(Version 1.0, Build 02)");
    	//final KLabelMultiline labelMultiLine2 = new KLabelMultiline("\nPlease feel free to send any questions or comments to: \nspikybear123@gmail.com");
    	//final KLabelMultiline labelMultiLine3 = new KLabelMultiline("\n\nFor additonal information please refer to the 'help' or 'quick reference' sections.");
    	
    	labelMultiLine4.setRows(KTextComponent.SHOW_ALL_LINES);
    	
    	//removing all menu's and then adding in the ones that i need, 'About' & 'Tip Calculator' 	
    	menu.removeAll();
    	menu.add(menuItem5);
    	menu.add(menuItem3);
    	menu.add(menuItem);
    	menu.add(menuItem4);
    	
    	//Setting the font for the home button
        Font f5= KindletUIResources.getInstance().getFont(
    			  KindletUIResources.KFontFamilyName.SERIF,
    			  25,
    			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
    			  false);  // antialiasing off
        Font f10= KindletUIResources.getInstance().getFont(
  			  KindletUIResources.KFontFamilyName.SERIF,
  			  21,
  			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
  			  false);  // antialiasing off
        Font f11= KindletUIResources.getInstance().getFont(
    			  KindletUIResources.KFontFamilyName.SERIF,
    			  31, //change back to 31
    			  KFontStyle.BOLD,   //Change this to BOLD for the necessary items.
    			  false);  // antialiasing off
        
        label31.setFont(f11);
        homeButton2.setFont(f5);
    	labelMultiLine4.setFont(f10);
    	//labelMultiLine2.setFont(f10);
    	//labelMultiLine3.setFont(f10);

    	
    	panel31.setLayout(new BorderLayout());
    	panel31.add(label100, BorderLayout.NORTH);
    	panel31.add(label200, BorderLayout.EAST);
    	panel31.add(label300, BorderLayout.WEST);
    	panel31.add(label31, BorderLayout.CENTER);
    	panel31.setFocusable(false);
        //panel40.setLayout(new KBoxLayout(panel40, KBoxLayout.Y_AXIS));

        //panel40.add(label41);
    	//panel40.add(labelMultiLine);
    	//panel40.add(labelMultiLine3);
        //panel40.add(labelMultiLine2);      
        //panel40.add(label43);
    	//panel40.add(label42);
    	//panel40.add(label40);
    	//panel40.add(homeButton);
    	
    	panel32.setLayout(new KBoxLayout(panel32, KBoxLayout.Y_AXIS));
    	panel32.add(homeButton2);
    	panel32.add(label30);
    	panel32.add(label33);
    	
    	panel33.setLayout(new BorderLayout());
    	panel33.add(panel32, BorderLayout.CENTER);
    	panel33.add(label400, BorderLayout.WEST);
    	panel33.add(label500, BorderLayout.EAST);
    	panel33.add(label600, BorderLayout.NORTH);
    	
    	
    	root.setLayout(new BorderLayout());		
        root.removeAll();
        root.add(panel31, BorderLayout.NORTH);
        root.add(panel33, BorderLayout.SOUTH);
        root.add(label200, BorderLayout.EAST);
        root.add(label300, BorderLayout.WEST);
		root.add(labelMultiLine4, BorderLayout.CENTER);
        root.repaint();
		homeButton2.requestFocus();

    }
    
    
    
    /*This the code section for the 'quick reference' page
     * 
     * 
     * 
     */
    public void quickReference (final Container root){        
        KLabel label50 = new KLabel("     ");
    	KLabel label51 = new KLabel("       Quick Reference:");
    	KLabel label53 = new KLabel("     ");
    	//KPanel panel50 = new KPanel();
    	KPanel panel51 = new KPanel();
    	KPanel panel52 = new KPanel();
    	KPanel panel53 = new KPanel();
    	//KPanel panel54 = new KPanel();
        final KLabelMultiline labelMultiLine5 = new KLabelMultiline("\nBelow are some quick tips for tipping in different global regions\n\nUnited States & Canada:\nStandard tipping guidance is between 15-20%\nTip is based on the level of service received\n\nCentral/South America & Europe:\nStandard tipping guidance is up to 10%\nVaries by country\n\nAsia:\nNo tipping in the majority of asian countires\n\nAdditional:\nBe sure to verify on your bill that a service charge hasn't already been charged, especially for large groups\nMake sure to input the pre-tax amount for the tip calculation");
    	//final KLabelMultiline labelMultiLine2 = new KLabelMultiline("\nPlease feel free to send any questions or comments to: \nspikybear123@gmail.com");
    	//final KLabelMultiline labelMultiLine3 = new KLabelMultiline("\n\nFor additonal information please refer to the 'help' or 'quick reference' sections.");
    	
    	labelMultiLine5.setRows(KTextComponent.SHOW_ALL_LINES);
    	
    	//removing all menu's and then adding in the ones that i need, 'About' & 'Tip Calculator' 	
    	menu.removeAll();
    	menu.add(menuItem5);
    	menu.add(menuItem3);
    	menu.add(menuItem);
    	menu.add(menuItem2);
    	
    	//Setting the font for the home button
        Font f5= KindletUIResources.getInstance().getFont(
    			  KindletUIResources.KFontFamilyName.SERIF,
    			  25,
    			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
    			  false);  // antialiasing off
        Font f10= KindletUIResources.getInstance().getFont(
  			  KindletUIResources.KFontFamilyName.SERIF,
  			  21,
  			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
  			  false);  // antialiasing off
        Font f11= KindletUIResources.getInstance().getFont(
    			  KindletUIResources.KFontFamilyName.SERIF,
    			  31, //change back to 31
    			  KFontStyle.BOLD,   //Change this to BOLD for the necessary items.
    			  false);  // antialiasing off
        
        label51.setFont(f11);
        homeButton3.setFont(f5);
    	labelMultiLine5.setFont(f10);
    	//labelMultiLine2.setFont(f10);
    	//labelMultiLine3.setFont(f10);

    	
    	panel51.setLayout(new BorderLayout());
    	panel51.add(label100, BorderLayout.NORTH);
    	panel51.add(label200, BorderLayout.EAST);
    	panel51.add(label300, BorderLayout.WEST);
    	panel51.add(label51, BorderLayout.CENTER);
    	panel51.setFocusable(false);
        //panel40.setLayout(new KBoxLayout(panel40, KBoxLayout.Y_AXIS));

        //panel40.add(label41);
    	//panel40.add(labelMultiLine);
    	//panel40.add(labelMultiLine3);
        //panel40.add(labelMultiLine2);      
        //panel40.add(label43);
    	//panel40.add(label42);
    	//panel40.add(label40);
    	//panel40.add(homeButton);
    	
    	panel52.setLayout(new KBoxLayout(panel52, KBoxLayout.Y_AXIS));
    	panel52.add(homeButton3);
    	panel52.add(label50);
    	panel52.add(label53);
    	
    	panel53.setLayout(new BorderLayout());
    	panel53.add(panel52, BorderLayout.CENTER);
    	panel53.add(label400, BorderLayout.WEST);
    	panel53.add(label500, BorderLayout.EAST);
    	panel53.add(label600, BorderLayout.NORTH);
    	
    	
    	root.setLayout(new BorderLayout());		
        root.removeAll();
        root.add(panel51, BorderLayout.NORTH);
        root.add(panel53, BorderLayout.SOUTH);
        root.add(label200, BorderLayout.EAST);
        root.add(label300, BorderLayout.WEST);
		root.add(labelMultiLine5, BorderLayout.CENTER);
        root.repaint();
		homeButton3.requestFocus();

        
        
        
        
    }
    
    /*This is the code section for the 'About' page
     * 
     * 
     * 
     */
    

    
    public void about (final Container root) {
    	
    	KLabel label40 = new KLabel("     ");
    	KLabel label41 = new KLabel("       About:");
    	KLabel label42 = new KLabel("(Version 1.0, Build 02)");
    	KLabel label43 = new KLabel("     ");
    	//KPanel panel40 = new KPanel();
    	KPanel panel41 = new KPanel();
    	KPanel panel42 = new KPanel();
    	KPanel panel43 = new KPanel();
    	//KPanel panel44 = new KPanel();
        final KLabelMultiline labelMultiLine = new KLabelMultiline("\nThis application 'Tip Calculator' is desined to be an extremely easy to use application for determining the tip percentage for an event, or to split the bill between multiple parties.\n\nFor additonal information please refer to the 'help' or 'quick reference' sections.\n\nPlease feel free to send any questions or comments to:\nspikybear123@gmail.com \n\n(Version 1.0, Build 02) ");
    	//final KLabelMultiline labelMultiLine2 = new KLabelMultiline("\nPlease feel free to send any questions or comments to: \nspikybear123@gmail.com");
    	//final KLabelMultiline labelMultiLine3 = new KLabelMultiline("\n\nFor additonal information please refer to the 'help' or 'quick reference' sections.");
    	
    	labelMultiLine.setRows(KTextComponent.SHOW_ALL_LINES);
    	
    	
    	//This is the remove the menu item for 'About' as you are currently on the 'About' page
    	menu.removeAll();
    	menu.add(menuItem5);
    	menu.add(menuItem3);
    	menu.add(menuItem2);
    	menu.add(menuItem4);
    	
    	//Setting the font for the home button
        Font f5= KindletUIResources.getInstance().getFont(
    			  KindletUIResources.KFontFamilyName.SERIF,
    			  25,
    			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
    			  false);  // antialiasing off
        Font f10= KindletUIResources.getInstance().getFont(
  			  KindletUIResources.KFontFamilyName.SERIF,
  			  25,
  			  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
  			  false);  // antialiasing off
        Font f11= KindletUIResources.getInstance().getFont(
    			  KindletUIResources.KFontFamilyName.SERIF,
    			  31, //change back to 31
    			  KFontStyle.BOLD,   //Change this to BOLD for the necessary items.
    			  false);  // antialiasing off
        
        label41.setFont(f11);
        label42.setFont(f10);
        homeButton.setFont(f5);
    	labelMultiLine.setFont(f10);
    	//labelMultiLine2.setFont(f10);
    	//labelMultiLine3.setFont(f10);

    	
    	panel41.setLayout(new BorderLayout());
    	panel41.add(label100, BorderLayout.NORTH);
    	panel41.add(label200, BorderLayout.EAST);
    	panel41.add(label300, BorderLayout.WEST);
    	panel41.add(label41, BorderLayout.CENTER);
    	panel41.setFocusable(false);
        //panel40.setLayout(new KBoxLayout(panel40, KBoxLayout.Y_AXIS));

        //panel40.add(label41);
    	//panel40.add(labelMultiLine);
    	//panel40.add(labelMultiLine3);
        //panel40.add(labelMultiLine2);      
        //panel40.add(label43);
    	//panel40.add(label42);
    	//panel40.add(label40);
    	//panel40.add(homeButton);
    	
    	panel42.setLayout(new KBoxLayout(panel42, KBoxLayout.Y_AXIS));
    	panel42.add(homeButton);
    	panel42.add(label40);
    	panel42.add(label43);
    	
    	panel43.setLayout(new BorderLayout());
    	panel43.add(panel42, BorderLayout.CENTER);
    	panel43.add(label400, BorderLayout.WEST);
    	panel43.add(label500, BorderLayout.EAST);
    	panel43.add(label600, BorderLayout.NORTH);
    	
    	
    	root.setLayout(new BorderLayout());		
        root.removeAll();
        root.add(panel41, BorderLayout.NORTH);
        root.add(panel43, BorderLayout.SOUTH);
        root.add(label200, BorderLayout.EAST);
        root.add(label300, BorderLayout.WEST);
		root.add(labelMultiLine, BorderLayout.CENTER);
        root.repaint();
		homeButton.requestFocus();
    }
    
    
    /*This is the large code section for the Tip Calculator application
     * 
     * 
     * 
     * 
     */
    
    public void page2 (final Container root){
    	
    	/*
    	 * Code to change the menu items
    	 */
    	
    	//This is to remove the menu item for 'tip calculator as you are currently within the 'tip calculator' page
    	menu.removeAll();
    	menu.add(menuItem5);
    	menu.add(menuItem);
    	menu.add(menuItem2);
    	menu.add(menuItem4);
    	
    	//Defining the labels
    	final KLabel label1 = new KLabel("Current Amount ($):     ");
    	final KLabel label2 = new KLabel("Tip Percent (%):            ");
    	final KLabel label3 = new KLabel("# of People:                   ");
    	final KLabel label4 = new KLabel("Tip Amount ($):");
    	final KLabel label5 = new KLabel("Total Amount ($):");
    	final KLabel label6 = new KLabel("Amount per Person ($):");
    	final KLabel label7 = new KLabel("0.00"); // Default value for Tip Amount to be displayed
    	final KLabel label8 = new KLabel("0.00"); // Default value for Total Amount to be displayed
    	final KLabel label9 = new KLabel("0.00"); // Default value for Amount per Person to be displayed
    	final KLabel label10 = new KLabel(""); //This is for the error messages at the bottom of the display
    	final KLabel label11 = new KLabel("");  // This is for the North region of the root, which I want to be blank
        final KLabel label12 = new KLabel("");  // This is for the top most portion of the main UI component, trying to get it to move down from the border area
        
        
    	//Definition of buttons
    	final KButton button_calculate = new KButton("Calculate");
    	final KButton button_clear = new KButton("Clear");
    	
    	//Definition of Text Input fields
    	//final KTextField field1 = new KTextField(5);  //User to input price of meal
    	final KTextField field2 = new KTextField("15", 5); //User to input price of tip
    	final KTextField field3 = new KTextField("1",5); //User to input number of people
    	
    	//Setting borders
    	field1.setBorder(new KLineBorder(2, true));
    	field2.setBorder(new KLineBorder(2, true));
    	field3.setBorder(new KLineBorder(2, true));
    	    	
    	//This is how to set the font's will need to do further testing to verify the BOLD functionality
    	//The recommended font sizes are 17, 19, 21, 25, 31, 36, 60, and 86 (in points). 
    	//They've been optimized for the Kindle (Free 3G + Wi-Fi, 6") and Kindle (Wi-Fi, 6").
    	
    	Font f= KindletUIResources.getInstance().getFont(
				  KindletUIResources.KFontFamilyName.SERIF,
				  21,
				  KFontStyle.PLAIN,   //Change this to BOLD for the necessary items.
				  false);  // antialiasing off
    	
    	button_clear.setFont(f);
    	button_calculate.setFont(f);
    	
    	Font f2= KindletUIResources.getInstance().getFont(
				  KindletUIResources.KFontFamilyName.SERIF,
				  31,
				  KFontStyle.BOLD,   //Change this to BOLD for the necessary items.
				  false);  // antialiasing off
    	
    	label7.setFont(f2);
    	label8.setFont(f2);
    	label9.setFont(f2);
    	
    	    	
    	
    	//This should lock the orientation of the device.  Need to verify on live Kindle
    	//context.getOrientationController().lockOrientation(KindleOrientation.PORTRAIT);
    	
        button_calculate.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
            	
            	//Going to put this in a Try block, this will allow me to catch any inputs which aren't numbers
            	try{
            		twoDigits = new DecimalFormat("0.00");
                    //Gather the input for the meal price, STRING Value
                    priceMeal = field1.getText();
                    //Convert the input for mealprice into a DOUBLE value
                    mealPrice = Double.parseDouble(priceMeal);
                    //Gather the input for the tip amount
                    percentTip = field2.getText();
                    //Convert the input for tip amount into a DOUBLE value
                    percentToTip = Double.parseDouble(percentTip);
                    //Converting the tip amount into a perecentage value, i.e. 0.17
                    percentToTip = percentToTip / 100;
                    //Gather the input for number of people
                    numPeople = field3.getText();
                    //Convert the number of people to a DOUBLE value
                    numOfPeople = Double.parseDouble(numPeople);
                    
                    if(numOfPeople < 1){
                        //Defining all of the labels so that if the text is changed on a second attempt, the previous values are also removed
                        label7.setText("0.00");
                        label8.setText("0.00");
                        label9.setText("0.00");
                        label10.setText("You know there needs to be at least one person paying");
                    }
                    else{
                    
                    if(mealPrice < 0 || percentToTip < 0){
                        label7.setText("0.00");
                        label8.setText("0.00");
                        label9.setText("0.00");
                        label10.setText("Make sure your amounts are positive"); 
                    }
                    else{
                    
                    if(mealPrice > 99999 || percentToTip > 10 || numOfPeople > 100){
                        label7.setText("0.00");
                        label8.setText("0.00");
                        label9.setText("0.00");
                        label10.setText("Oops, Make sure your values aren't to large"); 
                    }
                    
                    else{	
                    //Calculate tip amount
                    tipAmount = mealPrice * percentToTip;

                    //Add tip to the price of the meal
                    totalMealPrice = tipAmount + mealPrice;

                    //Divide total by number of people 
                    pricePerPerson = totalMealPrice / numOfPeople;
                    
                    //Changing the amounts to Strings, with a format of two decimal places
                    tipAmountString = twoDigits.format(tipAmount);
                    totalMealPriceString = twoDigits.format(totalMealPrice);
                    pricePerPersonString = twoDigits.format(pricePerPerson);
                    
                    
                    //Defining the labels to output the strings
                    label7.setText(tipAmountString);
                    label8.setText(totalMealPriceString);
                    label9.setText(pricePerPersonString);
                    label10.setText("");
                    	} // This is closing the else portion of the third statement
                    	} // This is closing the else portion of the second if statement
                        } // This is closing the else portion of the first if statement
                    	
            		
            		
            	}
            	catch(NumberFormatException nfe){
            		//Making sure the values are removed if there is an issue with the inputs
            		label7.setText("0.00");
            		label8.setText("0.00");
            		label9.setText("0.00");
            		label10.setText("Oops, Make sure your values are correct");
            	}
            	
            	
            
                // Repaints the label component with the updated text
                label7.repaint();
                label8.repaint();
                label9.repaint();
                label10.repaint();
                
            }
        });
    	
    	button_clear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
			
				//Clear the values as the user pressed the Clear button
				label7.setText("0.00");
				label8.setText("0.00");
				label9.setText("0.00");
				label10.setText("");
				field1.setText("");
				field2.setText("");
				field3.setText("");
				
				//Repaint the labels and text fields with the updated values
				label7.repaint();
				label8.repaint();
				label9.repaint();
				label10.repaint();
				field1.repaint();
				field2.repaint();
				field3.repaint();
				
			}
    		
    	});
    	
    	
        KPanel panel2 = new KPanel();
        KPanel panel3 = new KPanel();
        KPanel panel4 = new KPanel();
        //KPanel panel5 = new KPanel();
        //KPanel panel6 = new KPanel();
        //KPanel panel7 = new KPanel();
        KPanel panel8 = new KPanel();
        // THis code was added on 20-Sept to put a border around this frame
        KPanel panel9 = new KPanel(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(final Graphics g) {
                super.paint(g);

                
                // Draw a border around the panel
                g.setColor(Color.BLACK);
                g.drawRoundRect(15, 1, getWidth()-30, getHeight()-30, 15, 15);
            }
        };
        
        KPanel panel10 = new KPanel();
        //KPanel panel11 = new KPanel();
        //KPanel panel12 = new KPanel();
        //KPanel panel13 = new KPanel();
        //KPanel panel14 = new KPanel();
        KPanel panel15 = new KPanel(new GridLayout(7,2,20,40));
        KPanel panel16 = new KPanel();
        //KPanel panel17 = new KPanel();
        KPanel panel18 = new KPanel();
        KPanel panel20 = new KPanel();
        
        
        FocusListener listener = new FocusListener() {

			public void focusGained(FocusEvent arg0) {
				KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(eventConsume);
			}

			public void focusLost(FocusEvent arg0) {
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(eventConsume);
		           
				}
        	
        };
        
        field1.addFocusListener(listener);
        field2.addFocusListener(listener);
        field3.addFocusListener(listener);
        
        
    	// Trying to redo the layouts, this way didn't seem to be working perfectly
        // Creates a basic panel container going to start with 9 panels for now
        /*
        //This is the code to get the requestFocus on the first panel, only at the first start-up
        root.addComponentListener(new ComponentAdapter() {
            public void componentShown(final ComponentEvent e) {
            	i++;
            	if(i==1){
            	field1.requestFocus();
            	}
            	else{
            	}            	
            }
        });
        */
      //Top half 
        panel1.add(field1); 
        panel2.add(field2); 
        panel3.add(field3);
        panel15.add(label1);
        panel15.add(panel1);
        panel15.add(label2);
        panel15.add(panel2);
        panel15.add(label3);
        panel15.add(panel3);
        
      //Buttons which go in the middle
        panel18.add(button_clear);
        panel20.add(button_calculate);
        panel15.add(panel18);
        panel15.add(panel20);
        
        //Bottom half
        panel15.add(label4);
        panel15.add(label7);
        panel15.add(label5);
        panel15.add(label8);
        panel15.add(label6);
        panel15.add(label9);
        panel16.add(panel15);        
        
        //Error pane which goes in the very bottom
        panel8.add(label10);
        panel8.setFocusable(false);
        
        //Header item that is going on top to get the boxes away from the border
        panel4.add(label12);
        panel4.setFocusable(false);
        
        
        panel9.setLayout(new KBoxLayout(panel9, KBoxLayout.Y_AXIS));
        panel9.add(panel4);   // This is to provide a buffer on the top end
        panel9.add(panel16);  // This is the fields + labels + buttons seen by the user
        panel9.add(panel8);   // This is the place holder for the error codes

        panel10.add(label11);
		panel10.setFocusable(false);
        
        
        root.setLayout(new BorderLayout());		
        root.removeAll();
        root.add(panel10, BorderLayout.NORTH);
		root.add(panel9, BorderLayout.CENTER);
		root.repaint();
		root.removeComponentListener(fieldOneEnable);
    }
    

    public void start() {
    }    

    public void stop() {
        // TODO Auto-generated method stub
    }    

    public void destroy() {
        // TODO Auto-generated method stub
    }
}
