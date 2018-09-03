
public class Anl_Lexico {

	String input = new String(), token = new String(), word = new String();
	int position, stateError, acceptance, wrdCnt;
	boolean continues, error;

	public static void main(String[] args) {
		Anl_Lexico lexeme = new Anl_Lexico();
		lexeme.Analyze();
	}

	public Anl_Lexico() {
		continues = true;
		error = false;
		input = "&& & and ! cas || | xd";
		position = 0;
		wrdCnt = 0;
		stateError = -1;
		acceptance = 999;
	}

	public void Analyze() {
		int state, wLen, lastState;
		char c;
		while (continues) {
			word = "";
			token = "Desconocido";
			state = lastState = 0;
			wLen = input.length();
			while ((state != stateError) && (state != acceptance) && (position != wLen)) {
				c = input.charAt(position);
				lastState = state;
				switch (state) {
				case 0:
					if (Character.isLetter(c))
						state = 1;
					else if (Character.isDigit(c))
						state = 2;
					else if (c == '_')
						state = 1;
					else if (c == '!' || c == '&' || c == '|')
						state = 10;
					else if (c == '<')
						state = 5;
					else if (c == '>')
						state = 8;
					else if (c == '=')
						state = 10;
					else if (c == '.')
						state = 13;
					else if (c == '+' || c == '-' || c == '*' || c == '/')
						state = 12;
					else if (c == '(' || c == ')' || c == ',' || c == ';' || c == '{' || c == '}')
						state = 13;
					else if (c == ' ' || c == '\t' || c == '\n' || c == '\r')
						state = 0;
					else
						state = stateError;
					break;
				case 1:
					if (Character.isLetter(c))
						state = 1;
					else if (Character.isDigit(c))
						state = 1;
					else if (c == '_')
						state = 1;
					else if (c == '<' || c == '>' || c == '=' || c == '.' || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == ')' || c == ',' || c == ';' || c == '{'
							|| c == '}' || c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '!' || c == '&' || c == '|')
						state = acceptance;
					else
						state = stateError;
					break;
				case 2:
					if (Character.isDigit(c))
						state = 2;
					else if (c == '.')
						state = 3;
					else if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+'
							|| c == '-' || c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ','
							|| c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| c == '!' || c == '&' || c == '|')
						state = acceptance;
					else
						state = stateError;
					break;
				case 3:
					if (Character.isDigit(c))
						state = 4;
					else
						state = stateError;
					break;
				case 4:
					if (Character.isDigit(c))
						state = 4;
					else if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+'
							|| c == '-' || c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ','
							|| c == ';' || c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|')
						state = acceptance;
					else
						state = stateError;
					break;
				case 5:
					if (c == '>')
						state = 7;
					else if (c == '=')
						state = 10;
					else if (c == '<' || Character.isLetter(c) || c == '.' || Character.isDigit(c) || c == '+'
							|| c == '-' || c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ','
							|| c == ';' || c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|')
						state = acceptance;
					else
						state = stateError;
					break;
				case 7:
					if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ',' || c == ';'
							|| c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| Character.isDigit(c))
						state = acceptance;
					else
						state = stateError;
					break;
				case 8:
					if (c == '=')
						state = 9;
					else if (c == '<' || c == '>' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ',' || c == ';'
							|| c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| Character.isDigit(c))
						state = acceptance;
					else
						state = stateError;
					break;
				case 9:
					if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ',' || c == ';'
							|| c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| Character.isDigit(c))
						state = acceptance;
					else
						state = stateError;
					break;
				case 10:
					if (c == '=' || c == '!' || c == '&' || c == '|')
						state = 11;
					else if (c == '<' || c == '>' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ',' || c == ';'
							|| c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| c == '!' || c == '&' || c == '|' || Character.isDigit(c))
						state = acceptance;
					else
						state = stateError;
					break;
				case 11:
					if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ',' || c == ';'
							|| c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| c == '!' || c == '&' || c == '|' || Character.isDigit(c))
						state = acceptance;
					else
						state = stateError;
					break;
				case 12:
					if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ',' || c == ';'
							|| c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| Character.isDigit(c))
						state = acceptance;
					else
						state = stateError;
					break;
				case 13:
					if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
							|| c == '*' || c == '/' || c == '(' || c == '_' || c == ')' || c == ',' || c == ';'
							|| c == '{' || c == '}' || c == '.' || c == ' ' || c == '\t' || c == '\n' || c == '\r'
							|| Character.isDigit(c))
						state = acceptance;
					else
						state = stateError;
					break;
				default:
					state = stateError;
				} // End switch state
				
				if (state != stateError && state != acceptance && state != 0) {
					word += c;
				}
				position++;
				
			} // End while

			// Si estado es igual a -1 o 999 regresamos una posición y si estado es igual a
			// -1 error será verdadero.
			if (state == stateError || state == acceptance) {
				position--; // Regresamos al último caracter donde se encontró el estado de aceptación
				if (state == stateError) {
					error = true;
					break;
				}
			} else {
				lastState = state;
			}
			// Entra a switch si no hay error
			switch (lastState) {
			case 1:
				token = "Identificador";
				if (itsKeyword(word)) {
					token = "Palabra Reservada";
				} else if (itsLogicalOp(word)) {
					token = "Operador Lógico";
				}
				break;
			case 2:
				token = "Número Entero";
				break;
			case 4:
				token = "Número Real";
				break;
			case 11:
				if (itsLogicalOp(word)) {
					token = "Operador Lógico";
				} else {
					token = "Operador Relacional";
				}
				break;
			case 10:
				token = "Operador de Asignación";
				if (itsLogicalOp(word)) {
					token = "Operador Lógico";
				} 
				break;
			case 12:
				token = "Operador Aritmético";
				break;
			case 13:
				token = "Delimitador";
				break;
			default:
				token = "Desconocido";
				break;
			} // End switch lastState
			System.out.println(wrdCnt + ".- \" " + word + " \" (" + token + ")");
			wrdCnt++;
			// wut
			if (position == wLen) {
				continues = false;
			}
		} // End while continues
		if (error) {
			System.out.println("Error en el análisis");
		}
	}
	
	boolean itsKeyword(String word) {
		return word.equals("int") || word.equals("float") || word.equals("return") || word.equals("while")
				|| word.equals("if") || word.equals("else");
	}

	// Validar && ||
	boolean itsLogicalOp(String word) {
		return word.equals("&&") || word.equals("||") || word.equals("!");
	}

}
