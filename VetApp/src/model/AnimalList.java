package model;

/**
 * List of animals waiting to be seen
 * @author EmilyRing
 */
public class AnimalList {
	/** List of all animals waiting*/
	private List list;
	/** number of items in the list*/
	private int number;
	
	/** Class constructor */
	public AnimalList(){
		list = null;
		number = 0;
	}
	
	/** 
	 * @return a String ArrayList list of all animals in office 
	 */
	public String[] toArrayString(){
		String[] viewList;
		
		if (number > 0){
			List create = list;
			viewList = new String[number];
			
			for (int i = 0; i < number; i++){
				viewList[i] = create.top.toString();
				create = create.next;
			}
			
		} else {
			viewList = new String[]{"", "", ""};
		}
		return viewList;
	}
	
	/**
	 * @return Animal Object at the top of the list
	 */
	public Animal getFirstAnimal(){
		return list.top;
	}
	
	/**
	 * @param position - of the selected animal in the list
	 * @return Animal at the selected position
	 */
	public Animal getSelected(int position){
		Animal selected = null;
		List check = list;
		
		while (check.top != null && position > 0){
			check = check.next;
			position --;
		}
		
		if (check != null){
			selected = check.top;
		}
		
		return selected;
	}
	
	/** Adds a new animal to the list */
	public void add(Animal nu){
		if (number == 0){
			list = new List(nu, list);
		} else if (!doesAnimalExists(nu) && number > 0) {
			list.addAnimal(nu);
		}
		number++;
	}
	
	/** 
	 * checks to see if an animal is already in the list
	 * @param nu animal to be added to the list
	 * @return True is animal is already in the list 
	 */
	public boolean doesAnimalExists(Animal nu) {
		boolean check = false;
		List checkList = list;
		
		while(checkList != null){
			if (checkList.top.getName().equals(nu.getName()) && 
					checkList.top.getPhone().equals(nu.getPhone()) &&
					checkList.top.getOwnerName().equals(nu.getOwnerName())){
				check = true;
			}
			checkList = checkList.next;
		}
		
		return check;
	}
	
	/** Removes item at position */
	public Animal remove(int position){
		Animal rem = null;
		if (list != null && position == 0){
			rem = list.top;
			list = list.next;
			number--;
		} else if (list != null && position == 1){
			list.next = list.next.next;
			number--;
		} else if (list != null && position > 1){
			rem = list.removeAnimal(position);
		} 
		
		return rem;
	}
	
	/** Internal class used to create list */
	public class List {
		private Animal top;
		private List next;
		
		/**constructor for internal class */
		public List(Animal to, List nex){
			top = to;
			next = nex;
		}
		
		/** Removed item from the list */
		public Animal removeAnimal(int position){
			Animal rem = null;
			if (position == 1){
				rem = next.top;
				next = next.next;
				number--;
			} else {
				next.removeAnimal(position - 1);
			}
			
			return rem;
		}
		
		/** Adds a new animal to internal list */
		public void addAnimal(Animal nu){
			double emLevel = nu.getLevel() - .5;
			if (top == null) {
				top = nu;
			} else if (emLevel > top.getLevel()){
				Animal previous = top;
				top = nu;
				next = new List(previous, next);
			} else if (next == null){
				next = new List(nu, null);
			} else {
				next.addAnimal(nu);
			}
			
		}
	}
}


