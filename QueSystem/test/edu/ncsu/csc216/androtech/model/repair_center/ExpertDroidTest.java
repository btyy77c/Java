package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests methods included in the ExpertDroid class
 * @author Emily Ring
 */
public class ExpertDroidTest {
	private ExpertDroid e1;
	
	/**
	 * Sets up testing for Droids
	 * @throws Exception error
	 */
	@Before
	public void setUp() throws Exception {
		TechDroid.startDroidNumberingAt01();
		e1 = new ExpertDroid(); 
	}
	/**
	 * tests the creation of a String of droid information
	 */
	@Test
	public void testToString(){
		assertEquals("01E: UNASSIGNED", e1.toString());
	}
}
