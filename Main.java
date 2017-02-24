import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {

	public static void main(String[] args) {
		Terminal term;
		term = TerminalFacade.createTerminal();
		term.enterPrivateMode();
	new Termi(term);
	}

}