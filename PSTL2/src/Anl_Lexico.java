
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
		input = "iden 14 25.21 cadena float + - * / < > <= >= || && ! == != ; , ( ) { } = if while return else $"; // < <= > >=
		// iden 14 25.21 cadena float + - * / = < > <= >= || && ! == != ; , ( ) { } = if while return else $
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
					else if (c == '!' || c == '&' || c == '|' || c == '=')
						state = 10;
					else if (c == '<' || c == '>')
						state = 11;
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
					if (c == '=')
						 state = 14;
					else if (c == '<' || c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
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
					if (c == '=')
						state = 14; // Here
					else if (c == '>' || c == '=' || Character.isLetter(c) || c == '.' || c == '+' || c == '-'
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
				case 14:
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
			// System.out.print("lastState -> " + lastState + "    ");
			switch (lastState) {
			case 1:
				token = "0 | Identificador";
				if (itsType(word)) {
					token = "4 | Palabra Reservada";
					// asdasdsda
				} if (itsOrOp(word)) {
					token = "8 | Operador OR";
				} else if (itsAndOp(word)) {
					token = "9 | Operador AND";
				} else if (word.equals("if")) {
					token = "19 | Intrucción if";
				} else if (word.equals("while")) {
					token = "20 | Intrucción while";
				} else if (word.equals("return")) {
					token = "21 | Intrucción return";
				} else if (word.equals("else")) {
					token = "22 | Intrucción else";
				}
				break;
			case 2:
				token = "1 | Número Entero";
				break;
			case 4:
				token = "2 | Número Real";
				break;
			case 11:
				if (itsOrOp(word)) {
					token = "8 | Operador OR";
				} else if (itsAndOp(word)) {
					token = "9 | Operador AND";
				} else if (itsRelOp(word)){
					token = "7 | Operador Relacional";
				} else if(itsEqualityOp(word)) {
					token = "11 | Operador Igualdad";
				} 
				break;
			case 10:
				if (itsOrOp(word)) {
					token = "8 | Operador OR";
				} else if (itsAndOp(word)) {
					token = "9 | Operador AND";
				} else if (itsNotOp(word)) {
					token = "10 | Operador NOT";
				} else {
					token = "18 | Igual";
				}
				break;
			case 12:
				if(itsSumOp(word)) {
					token = "5 | Operador Suma";
				} else if (itsMulOp(word)) {
					token = "6 | Operador Multiplicación";
				}
				break;
			case 13:
				if (word.equals(";"))
					token = "12 | Punto y Coma";
				else if(word.equals(","))
					token = "13 | Coma";
				else if(word.equals("("))
					token = "14 | Apertura Paréntesis";
				else if(word.equals(")"))
					token = "15 | Cierre Paréntesis";
				else if(word.equals("{"))
					token = "16 | Apertura Corchetes";
				else if(word.equals("}"))
					token = "17 | Cierre Corchetes";
				break;
			case 14:
				if(itsRelOp(word)) {
					token = "7 | Operador Relacional";
				} else {
					token = "Desconocido";
				}
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
	
	boolean itsType(String word) {
		return word.equals("int") || word.equals("float") || word.equals("void");
	}
	
	boolean itsSumOp(String word) { // + -
		return word.equals("+") || word.equals("-");
	}
	
	boolean itsMulOp(String word) { // * /
		return word.equals("*") || word.equals("/");
	}

	boolean itsRelOp(String word) {
		return word.equals("<") || word.equals("<=") || word.equals(">") || word.equals(">=");
	}
	
	boolean itsOrOp(String word) { // ||
		return word.equals("||");
	}
	
	boolean itsAndOp(String word) { // &&
		return word.equals("&&");
	}
	
	boolean itsNotOp(String word) { // !
		return word.equals("!");
	}
	
	boolean itsEqualityOp(String word) { // == !=
		return word.equals("==") || word.equals("!=");
	}

}
