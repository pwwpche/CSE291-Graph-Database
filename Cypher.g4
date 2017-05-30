grammar Cypher;

cypher :  singleQuery (  ';' )?  ;

singleQuery : clause (  clause )* ;

clause : match
       | return1
       ;

match : MATCH  pattern (  WHERE  expression )? ;

return1 : RETURN  ( expression (  ','  expression )* )  ;

pattern : patternPart (  ','  patternPart )* ;

patternPart : ( variable  '='  patternElement )
            | patternElement
            ;

patternElement : ( nodePattern (  patternElementChain )* )
               | ( '(' patternElement ')' )
               ;

patternElementChain : relationshipPattern  nodePattern ;

nodePattern : '('  ( variable  )? ( nodeLabels  )? ( properties  )? ')' ;

relationshipPattern : ( leftArrowHead  dash  relationshipDetail?  dash  rightArrowHead )
                    | ( leftArrowHead  dash  relationshipDetail?  dash )
                    | ( dash  relationshipDetail?  dash  rightArrowHead )
                    | ( dash  relationshipDetail?  dash )
                    ;

relationshipDetail : '['  ( variable  )? ( relationshipTypes  )? rangeLiteral? ( properties  )? ']' ;

properties : mapLiteral
           ;

relationshipTypes : ':'  relTypeName (  '|' ':'?  relTypeName )* ;

nodeLabels : (':'  labelName) (  ':'  labelName )* ;

rangeLiteral : '*'  ( integerLiteral  )? ( '..'  ( integerLiteral  )? )? ;

labelName : symbolicName ;

relTypeName : symbolicName ;

expression : exp_not (  AND  exp_not )* ;


//exp_not : ( NOT  )* exp_arithmatic ;
exp_not : ( NOT  )? exp_arithmatic ;


//exp_arithmatic : exp_binary (  partialComparisonExpression )* ;
exp_arithmatic : exp_binary (  partialComparisonExpression )? ;

exp_binary : exp_muldiv ( (  '+'  exp_muldiv ) | (  '-'  exp_muldiv ) )* ;

exp_muldiv : exp_xor ( (  '*'  exp_xor ) | (  '/'  exp_xor ) | (  '%'  exp_xor ) )* ;

exp_xor : exp_unary (  '^'  exp_unary )* ;

exp_unary : ( ( '+' | '-' )  )* exp_basic ;


// Remove nested array visit. Only support list visiting for constants.
//exp_basic : expression2 ( (  '[' expression ']' ) | (  '[' expression? '..' expression? ']' ) )*;
exp_basic : expression2 ( (  '[' expression ']' ) | (  '[' expression? '..' expression? ']' ) )?;


expression2 : atom (  nodeLabels | propertyLookup )? ;

atom : literal
     | ( COUNT  '('  '*'  ')' )
     | relationshipsPattern
     | parenthesizedExpression
     | variable
     ;

literal : numberLiteral
        | StringLiteral
        | booleanLiteral
        | NULL
        | mapLiteral
        | listLiteral
        ;

StringLiteral : ( '"' ( StringLiteral_0 )* '"' )
             ;
	      
numberLiteral : doubleLiteral
              | integerLiteral
              ;

booleanLiteral : TRUE
               | FALSE
               ;

listLiteral : '['  ( expression  ( ','  expression  )* )? ']' ;

mapLiteral : '{'  ( propertyKeyName  ':'  expression  ( ','  propertyKeyName  ':'  expression  )* )? '}' ;

partialComparisonExpression : ( '='  exp_binary )
                            | ( '<>'  exp_binary )
                            | ( '!='  exp_binary )
                            | ( '<'  exp_binary )
                            | ( '>'  exp_binary )
                            | ( '<='  exp_binary )
                            | ( '>='  exp_binary )
                            ;

parenthesizedExpression : '('  expression  ')' ;

relationshipsPattern : nodePattern (  patternElementChain )+ ;

propertyLookup : '.'  ( propertyKeyName ) ;

propertyKeyName : symbolicName ;

variable : symbolicName ;

integerLiteral : DecimalInteger
               ;

DecimalInteger : ZeroDigit
               | ( NonZeroDigit ( Digit )* )
               ;


Digit : ZeroDigit
      | NonZeroDigit
      ;

NonZeroDigit : NonZeroOctDigit
             | '8'
             | '9'
             ;

NonZeroOctDigit : '1'
                | '2'
                | '3'
                | '4'
                | '5'
                | '6'
                | '7'
                ;

OctDigit : ZeroDigit
         | NonZeroOctDigit
         ;

ZeroDigit : '0' ;

doubleLiteral : RegularDecimalReal
              ;

RegularDecimalReal : ( Digit )* '.' ( Digit )+ ;

symbolicName : UnescapedSymbolicName
             | MATCH
             | RETURN
             | WHERE
             | AND
             | NOT
             | TRUE
             | FALSE
             ;
COUNT : ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'T' | 't' )  ;

NULL : ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'L' | 'l' )  ;

MATCH : ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' )  ;

RETURN : ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'N' | 'n' )  ;

WHERE : ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' )  ;

AND : ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )  ;

NOT : ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' )  ;

TRUE : ( 'T' | 't' ) ( 'R' | 'r' ) ( 'U' | 'u' ) ( 'E' | 'e' )  ;

FALSE : ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' )  ;

UnescapedSymbolicName : IdentifierStart ( IdentifierPart )* ;

/**
 * Based on the unicode identifier and pattern syntax
 *   (http://www.unicode.org/reports/tr31/)
 * And extended with a few characters.
 */
IdentifierStart : ID_Start
                | '_'
                | '‿'
                | '⁀'
                | '⁔'
                | '︳'
                | '︴'
                | '﹍'
                | '﹎'
                | '﹏'
                | '＿'
                ;

/**
 * Based on the unicode identifier and pattern syntax
 *   (http://www.unicode.org/reports/tr31/)
 * And extended with a few characters.
 */
IdentifierPart : ID_Continue
               ;

/**
 * Any character except "`", enclosed within `backticks`. Backticks are escaped with double backticks. */

SP : ( WHITESPACE )+ -> skip;

WHITESPACE : SPACE
           | TAB
           | LF
           | VT
           | FF
           | CR
           | FS
           | GS
           | RS
           | US
           | ' '
           | '᠎'
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | ' '
           | '　'
           | ' '
           | ' '
           | ' '
           | Comment
           ;

Comment : ( '/*' ( Comment_1 | ( '*' Comment_2 ) )* '*/' )
        | ( '//' ( Comment_3 )* CR? ( LF | EOF ) )
        ;



leftArrowHead : '<'
          ;

rightArrowHead : '>'
               ;

dash : '-'
     ;

fragment FF : [\f] ;

fragment RS : [\u001E] ;

fragment ID_Continue : [0-9A-Z_a-z];

fragment Comment_1 : [\u0000-)+-\uFFFF] ;

fragment Comment_3 : [\u0000-\t\u000B-\f\u000E-\uFFFF] ;

fragment Comment_2 : [\u0000-.0-\uFFFF] ;

fragment GS : [\u001D] ;

fragment FS : [\u001C] ;

fragment CR : [\r] ;

fragment SPACE : [ ] ;

fragment TAB : [\t] ;

fragment StringLiteral_0 : [\u0000-!#-[\]-\uFFFF] ;

fragment LF : [\n] ;

fragment VT : [\u000B] ;

fragment US : [\u001F] ;

fragment ID_Start : [A-Za-z] ;

