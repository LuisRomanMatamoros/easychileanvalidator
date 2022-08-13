package dev.lroman.easychileanrutvalidator.Util;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import dev.lroman.easychileanrutvalidator.Exception.RutException;

/**
 * test Rut class
 * @author lroman
 *
 */
public class RutTest {
	
	public RutTest() {
		
	}
	
	@Test
	public void testEraseDots() {
		String rutWithDots = "11.111.111";
		assertEquals(Rut.eraseDots(rutWithDots), "11111111");
	}
	
	@Test
	public void testSplitDv() {
		String rutWithDots = "11111111-1";
		String[] splitRut = Rut.splitDv(rutWithDots);
		
		assertEquals(splitRut.length, 2);
		assertEquals(splitRut[0], "11111111");
		assertEquals(splitRut[1], "1");
	}
	
	@Test
	public void testCleaning() {
		String rutToClean = "11.111.111-1";
		try {
			String[] result = Rut.cleaning(rutToClean);
			assertEquals(result.length, 2);
			assertEquals(result[0], "11111111");
			assertEquals(result[1], "1");			
		} catch (RutException e) {
			Assert.fail("Error: " + e.getMessage());
		}

	}
	
	@Test
	public void testCalculateDv() {
		String rutToGetDv = "11111111";
		String dvCalulated = Rut.calculateDv(rutToGetDv);
		assertEquals(dvCalulated, "1");
	}
	
	
	@Test
	public void testValidateRut() {
		String rutToValidate = "11.111.111-1";
		try {
			Rut rut = new Rut(rutToValidate);
			
			assertEquals(rut.getRut(), "11111111");
			assertEquals(rut.getDv(), "1");
			
		} catch (RutException e) {
			Assert.fail("Error: " + e.getMessage());
		}

		
	}
	
	@Test(expected = RutException.class)
	public void testValidateRutError() throws RutException {
		String rutToValidate = "11.111.111-2";
		new Rut(rutToValidate);		
	}
	
	
	@Test(expected = RutException.class)
	public void testCleaningError() throws RutException {
		String rutToClean = "1-1.11-1.111-1";
		Rut.cleaning(rutToClean);
	}
	
	@Test(expected = RutException.class)
	public void testValidarRutError() throws RutException {
		String rutToClean = "11.111.111-8";
		new Rut(rutToClean);
	}

}
