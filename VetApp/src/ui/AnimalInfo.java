package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Animal;

/**
 * Pop-up window that displays information of selected Animal
 * @author EmilyRing
 */
public class AnimalInfo extends JDialog{
	private static final long serialVersionUID = 1L;
	
	/* Main Panel for window content */
	private JPanel textPanel = new JPanel(new GridBagLayout());
	private GridBagConstraints format = new GridBagConstraints();
	
	/* Labels which will be displayed on window*/
	private JLabel name, ownerName, animalType, phone, level;
	
	/**
	 * Constructor for window
	 * @param info - Animal Object which will be used to obtain animal info
	 */
	public AnimalInfo(Animal info){
		super((java.awt.Frame) null, "Animal Information", true);
		
		textPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		format.gridx = 0;
		format.gridy = 0;
		animalType = new JLabel(info.toString().substring(0, 3));
		textPanel.add(animalType, format);
		
		level = new JLabel("Emergeny Level:  " + info.getLevel());
		format.gridy = 1;
		textPanel.add(level, format);
		
		name = new JLabel("Animal Name:  " + info.getName());
		format.gridy = 2;
		textPanel.add(name, format);
		
		ownerName = new JLabel("Owner Name:  " + info.getOwnerName());
		format.gridy = 3;
		textPanel.add(ownerName, format);
		
		phone = new JLabel("Phone Number:  " + info.getPhone());
		format.gridy = 4;
		textPanel.add(phone,format);
		
		add(textPanel);
		setLocation(400, 200);
		pack();
	}

}
