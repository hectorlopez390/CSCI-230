import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
public class Picture_Slider {
    public static void main(String[] args) {
      JFrame frame = new ImageViewerFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }
}
class ImageViewerFrame extends JFrame
   {
   private JLabel label;
   private JFileChooser chooser;
   private static final int DEFAULT_WIDTH = 800;
   private static final int DEFAULT_HEIGHT = 800;
   private static ArrayList<String> sliderLines = new ArrayList<String>();
   private static String urlStrings[] = null; //holds urls from slider file
   private static String captionStrings[] = null; //holds captions from slider file
   private static int i = 0; //counter 
   public ImageViewerFrame()
     {
      setTitle("Picture Slider");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      // use a label to display the images
      label = new JLabel("Welcome to Picture Slider! Please open a slider file to view images.");
      //set alignment of label and label text
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setVerticalAlignment(JLabel.CENTER);
      label.setHorizontalTextPosition(JLabel.CENTER);
      label.setVerticalTextPosition(JLabel.BOTTOM);
      Container contentPane = getContentPane();
      contentPane.add(label);
      // set up the file chooser
      chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));
      // set up the menu bar
      JMenuBar menuBar = new JMenuBar();
      JToolBar toolBar = new JToolBar();
      contentPane.add(menuBar, BorderLayout.NORTH);
      contentPane.add(toolBar, BorderLayout.SOUTH);
      
      JMenu menu = new JMenu("File");
      menu.setMnemonic(KeyEvent.VK_F);
      menuBar.add(menu);
      JMenuItem openItem = new JMenuItem("Open");
      menu.add(openItem);
      openItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_O, ActionEvent.ALT_MASK));
      openItem.addActionListener(new 
         ActionListener()
         {
             @Override
            public void actionPerformed(ActionEvent evt)
            {
               //trigger file chooser dialog
               int r = chooser.showOpenDialog(null);
               // set the image as the icon of the label
               if(r == JFileChooser.APPROVE_OPTION)
               {
                  String name = chooser.getSelectedFile().getPath();
                  try {
                	  //get each line from slider file into string arrayList
                	  @SuppressWarnings("resource")
                	  BufferedReader br = new BufferedReader(new FileReader(name));
                	  String line;
                	  
                	  i = 0;
                	  while((line = br.readLine()) != null) {
                		  sliderLines.add(line);
                		  i++;
             		  }
                	  //set array sizes equal to amount of slider file lines
                	  urlStrings = new String[sliderLines.size()];
                	  captionStrings = new String[sliderLines.size()];
                	  
                	  //Parse each line from slider line (changes line to string for url and string for caption
                	  for(i = 0; i < sliderLines.size(); i++)
                	  {
                		  String temp = sliderLines.get(i);
                		  String[] splitArr = temp.split("#", 2);
                		  urlStrings[i] = splitArr[0];
                		  captionStrings[i] = splitArr[1];
                	  }
                	  i = 0; //reset counter to beginning after slider file is read
                	  //set image icon from URL link
                	  URL url = new URL(urlStrings[0]);
                	  BufferedImage bi = ImageIO.read(url);
               		  ImageIcon icon = new ImageIcon(bi);
               		  
                      //set icon and caption to label
                      label.setIcon(icon);
                      label.setText(captionStrings[0]);
                  } catch(MalformedURLException e) {
                	  e.printStackTrace();
                  } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
                	  e.printStackTrace();
                  } catch (IOException e) {
					// TODO Auto-generated catch block
                	  e.printStackTrace();
                  }
               }
            }
         });
      
      
      
      JMenuItem exitItem = new JMenuItem("Exit");
      menu.addSeparator();
      menu.add(exitItem);
      exitItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_X, ActionEvent.ALT_MASK));
      exitItem.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               System.exit(0);
            }
         });
      JMenu helpMenu = new JMenu("Help");
      helpMenu.setMnemonic(KeyEvent.VK_H);
      menuBar.add(helpMenu);
      JMenuItem aboutItem = new JMenuItem("About");
      aboutItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_A, ActionEvent.ALT_MASK));
      helpMenu.add(aboutItem);
      menuBar.add(helpMenu);
      aboutItem.addActionListener(new 
      ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent event)
          {
              JOptionPane.showMessageDialog(null, "Picture Slider by Hector Lopez. Version 1.0");
          }
      });   
      
      //user manual
      JMenuItem  helpUserManual = new JMenuItem("Help/User Manual");
      helpUserManual.setAccelerator(KeyStroke.getKeyStroke(
    	        KeyEvent.VK_M, ActionEvent.ALT_MASK));
      helpMenu.add(helpUserManual);
      helpUserManual.addActionListener(new 
      ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent event)
          {
        	    JPanel middlePanel = new JPanel ();
        	    middlePanel.setBorder(new TitledBorder("Help/User Manual"));

        	    JTextArea display = new JTextArea(15, 60);
        	    display.setText("1. System Requirements\n - Mac OSX \n - Windows 10\n\n"
        	    		+ "2. Slide Format File\n - \"URL here\"#\"caption here\" (seperated by \"#\" i.e must be .txt file)\n\n"
        	    		+ "3. Starting The System\n - Click on file, then open on the menubar, and navigate to the slide file you"
        	    		+ "would like to open.\n\n"
        	    		+ "4. Running The System\n - Use the 4 Buttons on the bottom toolbar to navigate through the pictures\n"
        	    		+ " - Previous Button - Shows previous picture in slide file\n - Home Button - Goes back to home page\n"
        	    		+ " - Next Button - Shows next picture in slide file\n - Last Button - Shows last picture in slide file\n\n"
        	    		+ "5. Shutting The System Down\n - Click on file, then exit on the menu bar if you would like to close"
        	    		+ " the program.");
        	    display.setEditable(false); // set textArea non-editable
        	    JScrollPane scroll = new JScrollPane(display);
        	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        	    
        	    middlePanel.add(scroll);
        	    JFrame frame = new JFrame();
        	    frame.add(middlePanel);
        	    frame.pack();
        	    frame.setLocationRelativeTo(null);
        	    frame.setVisible(true);
          }
      });
      
      //*******start of control bar
      JButton prevControl = new JButton("Previous");
      toolBar.add(prevControl);
      prevControl.addActionListener(new
    		  ActionListener()
    	      {
    	  		@Override
    	      	public void actionPerformed(ActionEvent event)
    	            {
    	  			if(urlStrings == null || captionStrings == null)
	  					{
	  						label.setText("No slider has been open. Please open a slider file to view images.");
	  					}
    	  			else 
    	  			{
						try {
							if((i-1) < 0)
							{
								i = (urlStrings.length - 1); //allows for loop of images (pressing previous goes to last image)
							}
							else
							{
								i = i -1;
							}
	    	      			URL url;
							url = new URL(urlStrings[i]);
	    	      			BufferedImage bi = ImageIO.read(url);
	    	      			ImageIcon icon = new ImageIcon(bi);
	    	      			//set image and caption
	    	      			label.setIcon(icon);
	    	      			label.setText(captionStrings[i]);
		                  } catch(MalformedURLException e) {
		                	  e.printStackTrace();
		                  } catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
		                	  e.printStackTrace();
		                  } catch (IOException e) {
							// TODO Auto-generated catch block
		                	  e.printStackTrace();
		                  }
    	  			}
    	          }
    	       });
      
      
      JButton homeControl = new JButton("Home");
      toolBar.add(homeControl);
      homeControl.addActionListener(new
    		  ActionListener()
    	      {
    	  		@Override
    	      	public void actionPerformed(ActionEvent event)
    	            {
    	  				urlStrings = null; //reset URL and caption arrays
    	  				captionStrings = null;
    	  				label.setText("Welcome to Picture Slider! Please open a slider file to view images.");
    	  				
    	            }
    	       });
      
      JButton nextControl = new JButton("Next");
      toolBar.add(nextControl);
      nextControl.addActionListener(new
    		  ActionListener()
    	      {
    	  		@Override
    	      	public void actionPerformed(ActionEvent event)
    	            {
    	  			if(urlStrings == null || captionStrings == null)
	  					{
	  						label.setText("No slider has been open. Please open a slider file to view images.");
	  					}
    	  			else 
    	  			{
						try {
							if((i+1) >= urlStrings.length)
							{
								i = 0; //allows for loop of images (pressing next goes to first image)
							}
							else
							{
								i = i + 1;
							}
	    	      			URL url;
							url = new URL(urlStrings[i]);
	    	      			BufferedImage bi = ImageIO.read(url);
	    	      			ImageIcon icon = new ImageIcon(bi);
	    	      			//set image and caption
	    	      			label.setIcon(icon);
	    	      			label.setText(captionStrings[i]);
		                  } catch(MalformedURLException e) {
		                	  e.printStackTrace();
		                  } catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
		                	  e.printStackTrace();
		                  } catch (IOException e) {
							// TODO Auto-generated catch block
		                	  e.printStackTrace();
		                  }
    	  				}
    	            }
    	       });
      JButton lastControl = new JButton("Last");
      toolBar.add(lastControl);
      lastControl.addActionListener(new
    		  ActionListener()
    	      {
    	  		@Override
    	      	public void actionPerformed(ActionEvent event)
    	            {
    	  			if(urlStrings == null || captionStrings == null)
	  					{
	  						label.setText("No slider has been open. Please open a slider file to view images.");
	  					}
    	  			else 
    	  			{
						try {
							i = (urlStrings.length - 1); // set counter equal to last index in array
							URL url;
							url = new URL(urlStrings[i]);
	    	      			BufferedImage bi = ImageIO.read(url);
	    	      			ImageIcon icon = new ImageIcon(bi);
	    	      			//set image and caption
	    	      			label.setIcon(icon);
	    	      			label.setText(captionStrings[i]);
		                  } catch(MalformedURLException e) {
		                	  e.printStackTrace();
		                  } catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
		                	  e.printStackTrace();
		                  } catch (IOException e) {
							// TODO Auto-generated catch block
		                	  e.printStackTrace();
		                  }
    	  				}
    	            }
    	       });
   }
}