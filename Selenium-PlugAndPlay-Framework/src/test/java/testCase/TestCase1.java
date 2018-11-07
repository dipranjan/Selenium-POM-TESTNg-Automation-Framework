/**
 * 
 */
package testCase;

import org.testng.annotations.Test;

import base.Base;


/**
 * @author ACER
 *
 */
public class TestCase1 extends Base{
	
	@Test
	public static void testCase1() {
		driver.get("https://www.google.com");
	}

}
