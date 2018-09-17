import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Anl_Lexico {

	String input = new String(), token = new String(), word = new String();
	ArrayList<Componente> lstComponent = new ArrayList<Componente>();
	File txt; Scanner sc;
	int position, stateError, acceptance, wrdCnt;
	boolean continues, error;

	public static void main(String[] args) {
		Anl_Lexico lexeme = new Anl_Lexico();
		lexeme.Analyze();
	}

	public Anl_Lexico() {
		try {
			// iden 14 25.21 "Esto deberá ser una cadena" float + - * / < > <= >= || && ! == != ; , ( ) { } = if while return else $
			txt = new File("C:\\Users\\Anime\\eclipse-workspace\\PSTL2\\src\\code.txt");
			sc = new Scanner(txt);
			while (sc.hasNextLine())
				input += sc.nextLine();
		} catch(Exception ex) {
			System.out.println("Error en la lectura del archivo");
		}
		continues = true;
		error = false;
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
				case 0: // =============================================================================================
					if (c == ' ' || c == '\t' || c == '\n' || c == '\r')
						state = 0;
					else if (Character.isLetter(c) || c == '_')
						state = 1;
					else if (Character.isDigit(c))
						state = 2;
					else if (c == '.')
						state = 4;
					else if (c == '+' || c == '-' || c == '*' || c == '/')
						state = 5;
					else if (c == '<' || c == '>')
						state = 6;
					else if (c == '!' || c == '&' || c == '|' || c == '=')
						state = 8;
					else if (c == '(' || c == ')' || c == '.' || c == ',' || c == ';' || c == '{' || c == '}' || c == '$')
						state = 10;
					else if (c == '"')
						state = 11;
					else
						state = stateError;
					break;
				case 1: // =============================================================================================
					if (Character.isLetter(c) || Character.isDigit(c) || c == '_')
						state = 1;
					else if (/* Character.isLetter(c) || Character.isDigit(c) || c == '_' || */ c == '<' || c == '>'
							|| c == '=' || c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '('
							|| c == ')' || c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t'
							|| c == '\n' || c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 2: // =============================================================================================
					if (Character.isDigit(c))
						state = 2;
					else if (c == '.')
						state = 3;
					else if (Character.isLetter(c) /* || Character.isDigit(c) */ || c == '_' || c == '<' || c == '>'
							|| c == '=' /* || c == '.' */ || c == '+' || c == '-' || c == '*' || c == '/' || c == '('
							|| c == ')' || c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t'
							|| c == '\n' || c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 3: // =============================================================================================
					if (Character.isDigit(c))
						state = 4;
					else
						state = stateError;
					break;
				case 4: // =============================================================================================
					if (Character.isDigit(c))
						state = 4;
					else if (Character.isLetter(c) /* || Character.isDigit(c) */ || c == '_' || c == '<' || c == '>'
							|| c == '=' || c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '('
							|| c == ')' || c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t'
							|| c == '\n' || c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 5: // =============================================================================================
					if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<' || c == '>' || c == '='
							|| c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
							|| c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 6: // =============================================================================================
					if (c == '=')
						state = 7;
					else if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<'
							|| c == '>' /* || c == '=' */ || c == '.' || c == '+' || c == '-' || c == '*' || c == '/'
							|| c == '(' || c == ')' || c == ',' || c == ';' || c == '{' || c == '}' || c == ' '
							|| c == '\t' || c == '\n' || c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 7: // =============================================================================================
					if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<' || c == '>' || c == '='
							|| c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
							|| c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 8: // =============================================================================================
					if (c == '=' || c == '&' || c == '|')
						state = 9;
					else if (c == '!')
						state = stateError;
					else if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<' || c == '>'
					      /*|| c == '=' */ || c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '('
							|| c == ')' || c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t'
							|| c == '\n' || c == '\r' /* || c == '!' || c == '&' || c == '|' */ || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 9: // =============================================================================================
					if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<' || c == '>' || c == '='
							|| c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
							|| c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 10: // =============================================================================================
					if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<' || c == '>' || c == '='
							|| c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
							|| c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = acceptance;
					else
						state = stateError;
					break;
				case 11: // =============================================================================================
					if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<' || c == '>' || c == '='
							|| c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
							|| c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|' || c == '$')
						state = 11;
					else if (c == '"')
						state = 12;
					else
						state = stateError;
					break;
				case 12:
					if (Character.isLetter(c) || Character.isDigit(c) || c == '_' || c == '<' || c == '>' || c == '='
							|| c == '.' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
							|| c == ',' || c == ';' || c == '{' || c == '}' || c == ' ' || c == '\t' || c == '\n'
							|| c == '\r' || c == '!' || c == '&' || c == '|' || c == '$' || c == '"')
						state = acceptance;
					else
						state = stateError;
					break;
				default: // =============================================================================================
					state = stateError;
				} // End switch state

				if (state != stateError && state != acceptance && state != 0) {
					word += c;
				}
				position++;

			} // End while

			if (state == stateError || state == acceptance) {
				position--;
				if (state == stateError) {
					error = true;
					break;
				}
			} else {
				lastState = state;
			}

			if (lastState == 8 || lastState == 6)
				lastState++;

			switch (lastState) {
			case 1:
				if (word.equals("int") || word.equals("float") || word.equals("void"))
					token = "4"; //"4 | Palabra Reservada";
				else if (word.equals("if"))
					token = "19"; //"19 | Intrucción if";
				else if (word.equals("while"))
					token = "20"; //"20 | Intrucción while";
				else if (word.equals("return"))
					token = "21"; //"21 | Intrucción return";
				else if (word.equals("else"))
					token = "22"; //"22 | Intrucción else";
				else
					token = "0"; //"0 | Identificador";
				break;
			case 2:
				token = "1"; //"1 | Número Entero";
				break;
			// case 3 queda número con punto = Desconocido
			case 4:
				token = "2"; //"2 | Número Real";
				break;
			case 5:
				if (word.equals("+") || word.equals("-"))
					token = "5"; //"5 | Operador Suma";
				else if (word.equals("*") || word.equals("/"))
					token = "6"; //"6 | Operador Multiplicación";
				break;
			case 7:
				if (word.equals("<=") || word.equals(">=") || word.equals("<") || word.equals(">"))
					token = "7"; //"7 | Operador Relacional";
				break;
			case 9:
				if (word.equals("||"))
					token = "8"; //"8 | Operador OR";
				else if (word.equals("&&"))
					token = "9"; //"9 | Operador AND";
				else if (word.equals("!"))
					token = "10"; //"10 | Operador NOT";
				else if (word.equals("==") || word.equals("!="))
					token = "11"; //"11 | Operador Igualdad";
				else if(word.equals("="))
					token = "18"; //"18 | Igual ";
				else
					error = true;
				break;
			case 10:
				if (word.equals(";"))
					token = "12"; //"12 | Punto y Coma";
				else if (word.equals(","))
					token = "13"; //"13 | Coma";
				else if (word.equals("("))
					token = "14"; //"14 | Apertura Paréntesis";
				else if (word.equals(")"))
					token = "15"; //"15 | Cierre Paréntesis";
				else if (word.equals("{"))
					token = "16"; //"16 | Apertura Corchetes";
				else if (word.equals("}"))
					token = "17"; //"17 | Cierre Corchetes";
				else if (word.equals("$"))
					token = "23"; //"23 | Símbolo de Cierre";
				break;
			case 12:
				token = "3"; //"3 | Cadena";
				break;
			default:
				token = "Indice: " + lastState + " <- Desconocido";
				break;
			} // End switch lastState
			// System.out.println(wrdCnt + ".- \" " + word + " \" (" + token + ")");
			lstComponent.add(new Componente(word, token));
			wrdCnt++;
			// wut
			if (position == wLen) {
				continues = false;
			}
		} // End while continues
		if (error) {
			System.out.println("Error en el análisis");
		} else {
			Anl_Sintactico sintactic = new Anl_Sintactico();
			sintactic.ejemplo1();
			System.out.println("=================================");
			sintactic.ejemplo2(lstComponent);
			System.out.println("=================================");
			sintactic.ejemplo3(lstComponent);
			System.out.println("=================================");
			sintactic.ejercicio1();
		}
	}

}