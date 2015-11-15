package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BadInfoExecption;
import model.veterinarians.AllVet;
import model.veterinarians.CatVet;
import model.veterinarians.DogVet;
import model.veterinarians.Vet;

/**
 * Pop-up window that allows the user to add a new Veterinarian to the system
 * @author EmilyRing
 */
public class AddVetUI extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	/*main panels which contain window information */
	private JPanel textPanel = new JPanel(new GridBagLayout());
	private GridBagConstraints format = new GridBagConstraints();
	private JPanel buttonPanel = new JPanel();
	
	/* button to create new Veterinarian */
	private JButton addVetButton = new JButton("Add New Veterinarian");
	
	/* Items to add Veterinarian info */
	private JLabel vetLabel = new JLabel("Veterinarian Name: ", 10);
	private JTextField vetName = new JTextField(10);
	private JLabel idLabel = new JLabel("Veterinarian ID: ", 10);
	private JTextField vetID = new JTextField(10);
	
	/* Window radio buttons */
	private JRadioButton cat = new JRadioButton("Cat");
	private JRadioButton dog = new JRadioButton("Dog");
	private JRadioButton all = new JRadioButton("All");
	private ButtonGroup group = new ButtonGroup();
	
	/* New Veterinarian that will be added to list */
	private Vet vet;
	
	/** Constructor for class */
	public AddVetUI(){
		super((java.awt.Frame) null, "Add New Veterinarian", true);
		
		addVetButton.addActionListener(this);
		buttonPanel.add(addVetButton);
		
		shapeTextPanel();
		
		add(textPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setLocation(400, 200);
		pack();
	}
	
	/**Creates the Text fields and content for a user to enter information */
	private void shapeTextPanel(){
		textPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		vetName.addActionListener(this);
		vetID.addActionListener(this);
		
		group.add(cat);
		group.add(dog);
		group.add(all);
		
		setFormat(0,0);
		textPanel.add(cat, format);
		setFormat(1, 0);
		textPanel.add(dog, format);
		setFormat(3, 0);
		textPanel.add(all, format);
		setFormat(0, 1);
		textPanel.add(vetLabel, format);
		setFormat(1, 1);
		textPanel.add(vetName, format);
		setFormat(0, 2);
		textPanel.add(idLabel, format);
		setFormat(1, 2);
		textPanel.add(vetID, format);
	}
	
	/**
	 * Sets GridBagConstraints so items can be added to the 
	 * Panel in the correct location
	 * @param x - gridx int to be updated
	 * @param y - gridy int to be updated
	 */
	private void setFormat(int x, int y){
		format.gridx = x;
		format.gridy = y;
	}
	
	/**
	 * @return Vet Object that will be created by the user window
	 */
	public Vet getVet(){
		return vet;
	}
	
	/**
	 * Checks String for Veterinarian to make sure the string is an int
	 * @param check - String to be checked
	 * @return int version of the String
	 * @throws BadInfoExecption - thrown if String is not an int
	 */
	private int checkID(String check) throws BadInfoExecption{
		check = check.trim();
		if (check == null || check.length() == 0){
			throw new BadInfoExecption("Error: ID is empty");
		} else {
			for (int i = 0; i < check.length(); i++){
				if (!Character.isDigit(check.charAt(i))){
					throw new BadInfoExecption("Error: ID must be a number");
				}
			}
		}
		
		return Integer.parseInt(check);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addVetButton) && cat.isSelected())	{
			try{
				int id = checkID(vetID.getText());
				vet = new CatVet(vetName.getText(), id);
				setVisible(false);
			} catch (BadInfoExecption e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		} else if (e.getSource().equals(addVetButton) && dog.isSelected())	{
			try{
				int id = checkID(vetID.getText());
				vet = new DogVet(vetName.getText(), id);
				setVisible(false);
			} catch (BadInfoExecption e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		} else if (e.getSource().equals(addVetButton) && all.isSelected())	{
			try{
				int id = checkID(vetID.getText());
				vet = new AllVet(vetName.getText(), id);
				setVisible(false);
			} catch (BadInfoExecption e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error: Please select Cat, Dog, or All");
		}
	}

}
