package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.*;

/**
 * Pop-up window for use to enter animal information and add to list of animals 
 * waiting to see a vet
 * @author EmilyRing
 */
public class AddAnimalUI extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	/* Main Panels and Frames */
	private JPanel buttonPanel = new JPanel();	
	private JPanel textPanel = new JPanel(new GridBagLayout());
	private GridBagConstraints format = new GridBagConstraints();
	
	/* Buttons and information fields for window */
	private JButton addAnimalButton = new JButton("Add New Animal");
	private JLabel levelLabel = new JLabel("Emergency Level: ", 10);
	private String[] emergencyLevel = new String[] {"0", "1", "2", "3", "4", "5"};
	private JComboBox<String> level = new JComboBox<>(emergencyLevel);
	private JTextField nameEnter = new JTextField(10);
	private JLabel nameLabel = new JLabel("Animal Name: ", 10);
	private JTextField ownerName = new JTextField(10);
	private JLabel ownerLabel = new JLabel("Owner Name: ", 10);
	private JTextField phone = new JTextField(10);
	private JLabel phoneLabel = new JLabel("Phone Number: ", 10);
	
	/* Radio buttons for window */
	private JRadioButton cat = new JRadioButton("Cat");
	private JRadioButton dog = new JRadioButton("Dog");
	private ButtonGroup group = new ButtonGroup();
	
	/* New animal which will be created from user */
	private Animal animal;
	
	/**
	 * Class constructor
	 */
	public AddAnimalUI(){
		super((java.awt.Frame) null, "Add New Animal", true);
		shapeTextPanel();
		
		add(textPanel, BorderLayout.NORTH);
		
		addAnimalButton.addActionListener(this);
		buttonPanel.add(addAnimalButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setLocation(400, 200);
		pack();
	}
	
	/**
	 * Adds the labels and buttons to the text panel
	 * Also updates the GridBagConstraints to set the location of each item
	 */
	private void shapeTextPanel(){
		textPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		cat.setActionCommand("Cat");
		cat.addActionListener(this);
		dog.setActionCommand("Dog");
		dog.addActionListener(this);
		
		group.add(cat);
		group.add(dog);
		setFormat(0, 0);
		textPanel.add(cat, format);
		
		setFormat(1, 0);
		textPanel.add(dog, format);

		setFormat(0, 1);
		textPanel.add(nameLabel, format);
		
		setFormat(1, 1);
		nameEnter.addActionListener(this);
		textPanel.add(nameEnter, format);
		
		setFormat(0, 2);
		textPanel.add(ownerLabel, format);
		
		setFormat(1, 2);
		ownerName.addActionListener(this);
		textPanel.add(ownerName, format);
		
		setFormat(0, 3);
		textPanel.add(phoneLabel, format);
		
		setFormat(1, 3);
		phone.addActionListener(this);
		textPanel.add(phone, format);
		
		setFormat(0, 4);
		textPanel.add(levelLabel, format);
		
		setFormat(1, 4);
		level.addActionListener(this);
		textPanel.add(level, format);
	}
	
	/**
	 * Sets GridBagConstraints for formatting before new items is added to the window
	 * @param x - sets gridx location
	 * @param y - sets gridy location
	 */
	private void setFormat(int x, int y){
		format.gridx = x;
		format.gridy = y;
	}
	
	/**
	 * @return New animal created by user
	 */
	public Animal getAnimal(){
		return animal;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addAnimalButton) && dog.isSelected()){
			
			try{
				animal = new Dog(nameEnter.getText(), 
						ownerName.getText(),
						phone.getText(), 
						Integer.parseInt((String) level.getSelectedItem()));
						setVisible(false);
			} catch (BadInfoExecption e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		} else if (e.getSource().equals(addAnimalButton) && cat.isSelected()){
			
			try{
				animal = new Cat(nameEnter.getText(), 
						ownerName.getText(),
						phone.getText(), 
						Integer.parseInt((String) level.getSelectedItem()));
						setVisible(false);
			} catch (BadInfoExecption e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		} else if (e.getSource().equals(addAnimalButton)){
			JOptionPane.showMessageDialog(null, "Error: You must select Cat or Dog");
		}
	}
}
