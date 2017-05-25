package analizadorlx.logica;
//import analizadorlx.logica.Token;
%%
%class LexicoJFlexGenerado
%type Token
%line
%column
D= [0-9]
L = [a-zA-Z]
TL = "."
SP = ","
S = "_" | "{" | "}" | "-" | "<" | ">" | "!" | "°" | "|" | "¬" | "#" | "$" | "%" | "&" | "/" | "(" | ")" | "=" | "?" | "'" | "¡" | "¿" | "*" | "+" | "~" | "[" | "]" | "^" | "´"
R =  "o" | "Traer" | "De" | "Donde" | "Sea" | "Igual" | "a" | "Diferente" | "Seleccionar" | "Agrupado" | "Por" | "Este" |  "Entre" | "y" | "No" | "Descendente" | "Ordenado" | "Ingresar" | "Elimine" | "Todos" | "Los" | "Datos" | "Actualice" | "En" | "El" | "Sumar" | "Restar" | "Multiplicar" | "Dividir"
WHITE = [ \t\r\n]
%{
public String lexema;
public int line;
public int column;
%}
%%
{WHITE} {/*Ignore*/}
"//".* {/*Ignore*/}
{R} {lexema = yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.PALABRA_RESERVADA, line, column);}
{L}({L}|{D})* {lexema=yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.IDENTIFICADOR, line, column) ;} 
{D}?{D}* {lexema=yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.ENTERO, line, column) ;}
"\""[^*]~"\"" {lexema=yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.CADENA, line, column);}
"-->" {lexema=yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.SIMBOLO, line, column) ;}
"--"[^*]~"--" {lexema=yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.COMENTARIO, line, column);}
"{" {lexema=yytext();  line=yyline; column=yycolumn ;  return new Token(lexema, analizadorlx.logica.Token.SIMBOLO, line, column);}
"}" {lexema=yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.SIMBOLO, line, column);}
{TL} {lexema = yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.TERMINACION_LINEA, line, column);}
{SP} {lexema = yytext();  line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.SEPARADOR, line, column);}
. { lexema = yytext(); line=yyline; column=yycolumn ; return new Token(lexema, analizadorlx.logica.Token.NO_RECONOCIDO, line, column);}




