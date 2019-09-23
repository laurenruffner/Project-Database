grammar b0;
integer: DIGIT DIGIT*;
identifier: ALPHA (ALPHA | DIGIT)*;
string_literal:  '"' (ALPHA | DIGIT)+ '"'; //doesn't allow for empty strings
literal: (string_literal | integer);
relation_name: identifier;
attribute_name: identifier;
operand: (attribute_name | literal);
OP: '==' | '!=' | '<' | '>' | '<=' | '>=';
ALPHA: [a-zA-Z_]+; //..'z'+ | 'A'..'Z'+ | '_'+;
DIGIT: [0-9]+;
WS: [ \t\r\n]+ -> skip;