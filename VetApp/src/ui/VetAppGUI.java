package ui;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import manager.Manager;
import model.Animal;
import model.veterinarians.Vet;

/**
 * Graphical Interface that users interact with
 * Starts program
 * @author EmilyRing
 */
public class VetAppGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	/* Main frame and panels */
	private JFrame mainFrame;
	private GridBagConstraints format = new GridBagConstraints();
	private JScrollPane vetScrollPane, animalScrollPane;
	private Panel buttonPanel, listPanel, labelPanel;
	
	/* Buttons and label  for window*/
	private JButton addAnimalButton, removeAnimalButton, removeVetButton, addVet, 
	                assignAnimal, getAnInfo, getVetInfo, completeVisit;
	private JLabel animalLabel, vetLabel, empty, empty2;
	private JList<String> vetList, animalList;
	
	/* Interacts with data */
	private Manager manager;
	
	/**
	 * Class constructor
	 */
	public VetAppGUI(){
		manager = new Manager();
		setUp();
	}
	
	/**
	 * Creates window, buttons, and labels for user interaction
	 */
	private void setUp(){
		mainFrame = new JFrame("Vet Office App");
		mainFrame.getContentPane();
		mainFrame.setSize(700, 220);
		mainFrame.setLocation(200, 200);
		createLabelPanel();
		createButtonPanel();
		createListPanel();
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Creates a panel which includes labels for the two different 
	 * window lists
	 */
	private void createLabelPanel(){
		animalLabel = new JLabel("Animals Waiting for Vet");
		vetLabel = new JLabel("Veterinarians on Staff");
		animalLabel.setPreferredSize(new Dimension(300, 15));
		
		labelPanel = new Panel();
		labelPanel.add(animalLabel, BorderLayout.WEST);
		labelPanel.add(vetLabel, BorderLayout.EAST);
		
		mainFrame.add(labelPanel, BorderLayout.NORTH);
	}
	
	/**
	 * Creates a panel for 8 buttons included in the program window
	 */
	private void createButtonPanel(){
		addAnimalButton = setButton("Add Animal");
		removeAnimalButton = setButton("Remove Animal");
		assignAnimal = setButton("Assign Animal");
		addVet = setButton("Add Vet");
		removeVetButton = setButton("Remove Vet");
		getAnInfo = setButton("Get Animal Info");
		getVetInfo = setButton("Get Vet Info");
		completeVisit = setButton("Complete Visit");

		addAnimalButton.addActionListener(this);
		removeAnimalButton.addActionListener(this);
		assignAnimal.addActionListener(this);
		addVet.addActionListener(this);
		removeVetButton.addActionListener(this);
		getAnInfo.addActionListener(this);
		getVetInfo.addActionListener(this);
		completeVisit.addActionListener(this);
		
		
		buttonPanel = new Panel(new GridBagLayout());
		
		setFormat(0,0);
		buttonPanel.add(addAnimalButton, format);
		setFormat(1,0);
		buttonPanel.add(removeAnimalButton, format);
		setFormat(5,0);
		buttonPanel.add(addVet, format);
		setFormat(6,0);
		buttonPanel.add(removeVetButton, format);
		
		setFormat(0,1);
		buttonPanel.add(getAnInfo, format);
		setFormat(1,1);
		buttonPanel.add(assignAnimal, format);
		setFormat(2,1);
		empty = new JLabel("");
		empty.setPreferredSize(new Dimension(50, 15));
		buttonPanel.add(empty, format);
		setFormat(5, 1);
		buttonPanel.add(getVetInfo, format);
		setFormat(6, 1);
		buttonPanel.add(completeVisit, format);
		empty2 = new JLabel("");
		empty2.setPreferredSize(new Dimension(60, 15));
		setFormat(0,2);
		buttonPanel.add(empty2, format);

		mainFrame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * creates a button with a standard size and label. 
	 * Used to ensure all buttons are matching
	 * @param field - String that will be displayed on the button
	 * @return newly created button
	 */
	private JButton setButton(String field){
		JButton button = new JButton(field);
		button.setPreferredSize(new Dimension(110, 20));
		return button;
	}
	
	/**
	 * Sets GridBagConstraints so object is added to correct location on window
	 * @param x - location of gridx
	 * @param y - location of gridy
	 */
	private void setFormat(int x, int y){
		format.gridx = x;
		format.gridy = y;
	}
	
	/**
	 * Creates the Panel that will include the list of animals
	 * and list of Vets in the system
	 */
	private void createListPanel(){
		listPanel = new Panel(new GridBagLayout());
		
		animalList = new JList<String>(manager.getAnimalList());
		animalList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		animalList.setLayoutOrientation(JList.VERTICAL);
		
		
		animalScrollPane = new JScrollPane(animalList);
		
		vetList = new JList<String>(manager.getVetList());
		vetList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		vetList.setLayoutOrientation(JList.VERTICAL);
		
		vetScrollPane = new JScrollPane(vetList);
		
		setFormat(0, 0);
		format.weightx = 1;
		format.weighty = 1;
		format.fill = GridBagConstraints.BOTH;
		listPanel.add(animalScrollPane, format);
		setFormat(1, 0);
		
		listPanel.add(vetScrollPane, format);
		
		mainFrame.add(listPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(addAnimalButton)){
			AddAnimalUI addAnimal = new AddAnimalUI();
			addAnimal.setVisible(true);
			Animal nu = addAnimal.getAnimal();
			if (nu != null && !manager.IsAnimalInList(nu)){
				manager.addAnimal(nu);
				animalList.setListData(manager.getAnimalList());
			} else if (manager.IsAnimalInList(nu)){
				JOptionPane.showMessageDialog(null, 
						"Error: Animal is already in list");
			}
			addAnimal.dispose();
			
			
		} else if (e.getSource().equals(removeAnimalButton)){
			int remove = animalList.getSelectedIndex();
			manager.removeAnimal(remove);
			animalList.setListData(manager.getAnimalList());
			
			
		} else if (e.getSource().equals(addVet)){
			AddVetUI addVet = new AddVetUI();
			addVet.setVisible(true);
			Vet nu = addVet.getVet();
			if (nu != null && !manager.isVetInList(nu)){
				manager.addVet(nu);
				vetList.setListData(manager.getVetList());
			} else if (manager.isVetInList(nu)){
				JOptionPane.showMessageDialog(null, "Error: VetID is already in list");
			}
			addVet.dispose();
			
			
		} else if (e.getSource().equals(removeVetButton)){
			int remove = vetList.getSelectedIndex();
			manager.removeVet(remove);
			vetList.setListData(manager.getVetList());
			
			
		} else if (e.getSource().equals(assignAnimal)){
			manager.assignAnimal(0);
			animalList.setListData(manager.getAnimalList());
			vetList.setListData(manager.getVetList());
			
			
		} else if (e.getSource().equals(getAnInfo)){
			Animal info = manager.getSelectedAnimal(animalList.getSelectedIndex());
			if (info != null){
				AnimalInfo infoScreen = new AnimalInfo(info);
				infoScreen.setVisible(true);
				infoScreen.dispose();
			}
		} else if (e.getSource().equals(getVetInfo)){
			Vet info = manager.getSelectedVet(vetList.getSelectedIndex());
			if (info != null){
				VetInfo infoScreen = new VetInfo(info);
				infoScreen.setVisible(true);
				infoScreen.dispose();
			}
		} else if (e.getSource().equals(completeVisit)){
			manager.completeVisit(vetList.getSelectedIndex());
			vetList.setListData(manager.getVetList());
		}
	}
	
	//TODO save file on closing
	
	/**
	 * Starts program 
	 * @param args main program
	 */
	public static void main(String[] args){
		new VetAppGUI();
	}
}
