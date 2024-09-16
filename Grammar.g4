grammar Grammar;

program
    : 'program' (statement)* 'endprog'
    ;

statement
    : assignExpr
    | expr
    ;

assignExpr
    : ID '=' expr
    ;

expr
    : term ((OP term)*)
    ;

term
    : '(' expr ')'
    | ID
    | NUM
    ;

OP : '+' | '-' | '*' | '/' ;

ID : [a-z] (([a-z] | [A-Z] | [0-9])*)? ;

NUM : [0-9]+ ('.' [0-9]+)? ;

WS : [ \t\n\r]+ -> skip;
