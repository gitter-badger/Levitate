package eu.blackwoods.levitate.syntax;

import java.util.List;

import eu.blackwoods.levitate.SyntaxHandler;
import eu.blackwoods.levitate.exception.SyntaxResponseException;

/**
 * Checks if user-input is anything
 * @author Kenneth Wussmann
 */
public class WildcardSyntax implements SyntaxHandler {

	@Override
	public void check(String parameter, String passed) throws SyntaxResponseException {
		
	}

	@Override
	public List<String> getTabComplete(String parameter, String passed) {
		return null;
	}
}
