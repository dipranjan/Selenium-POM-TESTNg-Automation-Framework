/**
 * 
 */
package testCase;

import org.testng.annotations.Test;

import base.BaseClass;



/**
 * @author ACER
 *
 */
public class TestCase1 extends  BaseClass{
	
	@Test
	public static void testCase1() {
		driver.get("https://www.google.com");
	}

}
