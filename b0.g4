grammar b0;
//batch 0
integer: DIGIT DIGIT*;
identifier: ALPHA (ALPHA | DIGIT)*;
string_literal:  '"' (ALPHA | DIGIT)+ '"'; //doesn't allow for empty strings
//batch 1
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
//batch 2
condition: conjunction ('||' conjunction)*;
conjunction: comparison ('&&' comparison)*;
comparison: operand OP operand | '(' condition ')';
//batch 3
expr: atomic_expr | selection | projection | renaming | union | difference | product | natural_join;
atomic_expr: relation_name | '(' expr ')';
selection: 'SELECT' '(' condition ')' atomic_expr;
projection: 'PROJECT' '(' attribute_list ')' atomic_expr;
renaming: 'RENAME' '(' attribute_list ')' atomic_expr;
union: atomic_expr '+' atomic_expr;
difference: atomic_expr '-' atomic_expr;
product: atomic_expr '*' atomic_expr;
natural_join: atomic_expr '&' atomic_expr;
//batch 0 cont.
OP: '==' | '!=' | '<' | '>' | '<=' | '>=';
ALPHA: [a-zA-Z_]+;
DIGIT: [0-9]+;
WS: [ \t\r\n]+ -> skip;