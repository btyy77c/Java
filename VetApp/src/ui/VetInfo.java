package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Animal;
import model.veterinarians.Vet;

/**
 * Pop-up window that displays information for a selected Veterinarian 
 * @author EmilyRing
 */
public class VetInfo extends JDialog{
	private static final long serialVersionUID = 1L;
	
	/* Main panel and formatting for window */
	private JPanel textPanel = new JPanel(new GridBagLayout());
	private GridBagConstraints format = new GridBagConstraints();

	/* JLabels which will display Vet information */
	private JLabel name, id, type, animaltype, level, ownername, phone;
	
	/**
	 * Constructor for class
	 * @param info Vet Object which is used to obtain Vet information
	 */
	public VetInfo(Vet info){
		super((java.awt.Frame) null, "Veterinarian Information", true);
		textPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		format.gridx = 0;
		
		type = setLabel(info.getType() + " Veterinarian", 0);
		textPanel.add(type,format);
		
		name = setLabel("Veterinarian Name:  " + info.getName(), 1);
		textPanel.add(name, format);
		
		id = setLabel("Veterinarian ID:  " + info.getID(), 2);
		textPanel.add(id, format);
		
		Animal animal = info.getAnimal();
		
		if (animal == null){
			animaltype = setLabel("Currently Seeing:  No Animals", 3);
		} else if (animal.toString().startsWith("Cat")){
			animaltype = setLabel("Currently Seeing:  Cat named " + animal.getName(), 3);
		} else {
			animaltype = setLabel("Currently Seeing:  Dog named " + animal.getName(), 3);
		}
		textPanel.add(animaltype, format);
		
		if (animal != null){
			level = setLabel("Animal Emergency Level:  " + animal.getLevel(), 4);
			textPanel.add(level, format);
			ownername = setLabel("Owner Name:  " + animal.getOwnerName(), 5);
			textPanel.add(ownername, format);
			phone = setLabel("Owner Phone Number:  " + animal.getPhone(), 6);
			textPanel.add(phone, format);
		}
		
		add(textPanel);
		setLocation(400, 200);
		pack();
	}
	
	/**
	 * Sets labels and label position before adding JLabel to window
	 * @param field - String which will be displayed in JLabel
	 * @param y - gridy position for the JLabel
	 * @return updated JLabel
	 */
	private JLabel setLabel(String field, int y){
		format.gridy = y;
		return new JLabel(field);
	}
}
