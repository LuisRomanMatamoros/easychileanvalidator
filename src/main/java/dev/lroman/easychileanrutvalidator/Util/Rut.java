package dev.lroman.easychileanrutvalidator.Util;

import dev.lroman.easychileanrutvalidator.Exception.RutException;

/**
 * 
 * this is Rut object to use and validate the chilean rut (Identification number)
 * 
 * @author lroman
 *
 */
public class Rut {
	
	private String rut;
	private String dv;
	
	/**
	 * 
	 * Constructor 
	 * 
	 * @param A RUT than could has dots and dash like this "11.111.111-1"
	 * @throws RutException
	 * 
	 */
	public Rut(String rut) throws RutException {
		validateRut(rut);
	}
		
	/**
	 * 
	 * A validation rut function this will validate the rut and if is correct, it sets 
	 * the parameters in this class.
	 * 
	 * @param A RUT than could has dots and dash
	 * @throws RutException 
	 * 
	 */
	public void validateRut(String rut) throws RutException {
		String[] rutCleaned = cleaning(rut);
		String dvCalculated = calculateDv(rutCleaned[0]);
		
		if(dvCalculated.equals(rutCleaned[1])) {
			setRut(rutCleaned[0]);
			setDv(rutCleaned[1]);
		}else {
			throw new RutException("veritication value gave us isn't correct.");
		}
	}
	
	/**
	 * 
	 * A cleaning function this will give a String array with two values 
	 * the first one will has the identification number and 
	 * the second one will has the verification value.
	 * 
	 * @param rut
	 * @return String[]
	 * @throws RutException
	 * 
	 */
	public static String[] cleaning(String rut) throws RutException {
		String[] result = splitDv(rut);
		result[0] = eraseDots(result[0]);
		
		if(result.length == 2) {
			return result;
		}
		
		throw new RutException("Formato del rut no es valido.");
	}
	
	/**
	 * 
	 * A function that calculate the verification code from identification number
	 * 
	 * @param rutSinDv "11111111"
	 * @return
	 * 
	 */
	public static String calculateDv(String rutSinDv) {
		int addition= 0;
		int lengthRut = rutSinDv.length();
		for(int counter = lengthRut - 1, multiplier = 2; counter >= 0; counter--, multiplier++) {
			int numeroRut = Character.getNumericValue(rutSinDv.charAt(counter));
			int resultadoMultiplicacion = numeroRut * multiplier;
			addition += resultadoMultiplicacion;
			if(multiplier >= 7 ) {
				multiplier = 1;
			}
		}
		double divicion = addition / 11;
		int divicionWithoutDecimal = (int) divicion;
		int divicionToMultiply = divicionWithoutDecimal * 11;
		int resultOfSubtraction = addition - divicionToMultiply;
		int result = 11 - resultOfSubtraction;
		if(result == 11) {
			return "0";
		}
		if(result == 10) {
			return "k";
		}
		
		return String.valueOf(result);
	}
	
	/**
	 * 
	 * a function that return a String[] with indetification and verification number
	 * 
	 * @param rut
	 * @return String{}
	 * 
	 */
	public static String[] splitDv(String rut) {
		return rut.split("-");
	}
	
	/**
	 * 
	 * a function that erase the points 
	 * @param rut
	 * @return
	 * 
	 */
	public static String eraseDots(String rut) {
		return rut.replace(".", "");
	}
	
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * @return the dv
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @param dv the dv to set
	 */
	public void setDv(String dv) {
		this.dv = dv;
	}

	
}
