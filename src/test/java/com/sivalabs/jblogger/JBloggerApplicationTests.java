/**
 * 
 */
package com.sivalabs.jblogger;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Siva
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=JBloggerApplication.class)
public class JBloggerApplicationTests 
{
	@Test
	public void contextLoads()
	{
		assertTrue(true);
	}
}
