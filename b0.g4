grammar b0;
integer: DIGIT DIGIT*;
identifier: ALPHA (ALPHA | DIGIT)*;
string_literal:  '"' (ALPHA | DIGIT)+ '"'; //doesn't allow for empty strings
literal: (string_literal | integer);
relation_name: identifier;
attribute_name: identifier;
operand: (attribute_name | literal);
type: 'VARCHAR' '(' integer ')' | 'INTEGER';
attribute_list: attribute_name (',' attribute_name)*;
typed_attribute_list: attribute_name type (',' attribute_name)*;
open_cmd: 'OPEN' relation_name;
close_cmd: 'CLOSE' relation_name;
write_cmd: 'WRITE' relation_name;
exit_cmd: 'EXIT';
OP: '==' | '!=' | '<' | '>' | '<=' | '>=';
ALPHA: [a-zA-Z_]+; //..'z'+ | 'A'..'Z'+ | '_'+;
DIGIT: [0-9]+;
WS: [ \t\r\n]+ -> skip;