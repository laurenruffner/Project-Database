grammar b0;
integer: DIGIT+;
identifier: ALPHA (ALPHA | DIGIT)*;
string_literal:  (ALPHA | DIGIT)+; //doesn't allow for empty strings
OP: '==' | '!=' | '<' | '>' | '<=' | '>=';
ALPHA: [a-zA-Z_]+; //..'z'+ | 'A'..'Z'+ | '_'+;
DIGIT: [0-9]+;
WS: [ \t\r\n]+ -> skip;
//new comment