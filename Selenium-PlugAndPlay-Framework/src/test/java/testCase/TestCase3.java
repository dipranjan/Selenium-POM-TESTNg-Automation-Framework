/**
 * 
 */
package testCase;


import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseClass;


public class TestCase3 extends  BaseClass{
	
	@Test(groups="smoke",description="will skip this test case")
	public static void testCase3() {
		throw new SkipException("Skipping this exceution");
		}

}
