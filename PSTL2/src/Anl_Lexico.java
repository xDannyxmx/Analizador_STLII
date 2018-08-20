public class Anl_Lexico {

    static char actual;
    static int position = 0;
    static int state;
    static int last = 0;
    static String text = new String("void1");
    // Tendré que leer un archivo para las comillas.
    static boolean continues = true;
    static boolean contString = false;
    static boolean contInt = false;
    static boolean contDot = false;
    String tv[] = {
            /* 0  */ "identificador",
            /* 1  */ "entero",
            /* 2  */ "real",
            /* 3  */ "cadena",
            /* 4  */ "tipo",       // int, float, void
            /* 5  */ "opSuma",     // + -
            /* 6  */ "opMul",      // * /
            /* 7  */ "opRelac",    // < <= > >=
            /* 8  */ "opOr",       // ||
            /* 9  */ "opAnd",      // &&
            /* 10 */ "opNot",      // !
            /* 11 */ "opIgualdad", // == !=
            /* 12 */ ";",
            /* 13 */ ",",
            /* 14 */ "(",
            /* 15 */ ")",
            /* 16 */ "{",
            /* 17 */ "}",
            /* 18 */ "=",
            /* 19 */ "if",
            /* 20 */ "while",
            /* 21 */ "return",
            /* 22 */ "else",
            /* 23 */ "$"
            };

    public static void main(String[] args) {
        text = text.trim();
        Inicia();
    }

    public static void Inicia() {

        while (continues) {
            if(!text.trim().isEmpty()) {
                // Inicia
                actual = text.charAt(position);
                System.out.println("Se analiza '" + actual + "'");
                // System.out.println("Case es -> " + state);
                if (Character.isLetter(actual)) {
                    state = 1;
                    last = 0;
                    contString = true;
                } else if (Character.isDigit(actual) || actual == '.') {
                    if(actual == '.') contDot = true;
                    if(contString != true) {
                        state = 2;
                        last = 1;
                    } else {
                        state = 1;
                        last = 0;
                    }
                    // System.out.println("state -> " + state + " | last -> " + last);
                } else if (actual == '+' || actual == '-' || actual == '*' || actual == '/' || actual == '<'
                        || actual == '=' || actual == '>' || actual == '|' || actual == '&' || actual == '!') {
                    state = 3;
                    last = 2;
                } else if (actual == '"') {
                    state = 11; // cadena
                } else if (actual == ';') {
                    state = 4;
                } else if (actual == ',') {
                    state = 5;
                } else if (actual == '(') {
                    state = 6;
                } else if (actual == ')') {
                    state = 7;
                } else if (actual == '{') {
                    state = 8;
                } else if (actual == '}') {
                    state = 9;
                } else if (actual == '$') {
                    state = 10;
                } else {
                    System.out.println("Se detectó un error, como lo es mi vida");
                    continues = false;
                }
                // Validación punto y caracter
                if (contString == true && contDot == true) {
                    state = 0;
                    continues = false;
                    last = 99;
                }
                // Comienza switch
                switch (state) {
                    // ==== Encontró una letra ====
                    case 1: {
                        System.out.println("Entra a case de letra");
                        // Tipo
                        if (text.equals("int") || text.equals("float") || text.equals("void")) {
                            System.out.println("4 - tipo");
                            continues = false;
                        }
                        // Reservada
                        else if (text.equals("if") || text.equals("while") || text.equals("return") || text.equals("else")) {
                            System.out.println("19 a 22 - reservada");
                            continues = false;
                        } else {
                            // Identificador o error
                            if(last == 0 || last == 1) {
                                last = 0;
                                state = 0;
                            } else {
                                System.out.println("Se detectó un error, como lo es mi vida");
                                continues = false;
                            }
                        }
                    } break; // Ends case 1
                    case 2: {
                        System.out.println("Entra a case de digito");
                        // continues = false;
                        if(contDot == true ) {
                        	int count = text.length() - text.replace(".", "").length();
                        	if(count > 1) {
                        		System.out.println("Se detectó un error, como lo es mi vida");
                        		last = 99;
                                continues = false;
                        	} else {
                    			last = 3;
                        		state = 0;
                        		System.out.println("Acá");
                        		if(position == text.length()-1 && actual == '.') {
                            		last = 99;
                                    continues = false;
                        		}
                        	}
                        } // Ends if dot true                        
                        
                    } break; // Ends case 2
                    case 3: {
                        
                    } break; // Ends case 3
                
                } // Ends switch
                if(position < text.length()-1) {
                    position++;
                } else {
                    continues = false;
                    tellMe();
                }    
            } else {
                System.out.println("Variable text está vacia");
                continues = false;
            } // Ends if empty
        } // Ends while 'continues'

    }
    
    static void tellMe () {
        if (last == 0) {
            System.out.println("0 - identificador");
        }
        if (last == 1) {
            System.out.println("1 - entero");
        }
        if (last == 2) {
            System.out.println("3 - operador");
        }
        if (last == 3) {
            System.out.println("2 - real");
        }
        if(last == 99) {
        	System.out.println("Se detectó un error, como lo es mi vida");
        }
    }

}