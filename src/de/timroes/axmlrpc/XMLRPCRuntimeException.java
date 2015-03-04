package de.timroes.axmlrpc;

/**
 *
 * @author Tim Roes
 */
public class XMLRPCRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XMLRPCRuntimeException(String ex) {
		super(ex);
	}

	public XMLRPCRuntimeException(Exception ex) {
		super(ex);
	}
	
}