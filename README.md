# Parser - Calculadora

Parser de uma calculadora com ANTLR. A gramática permite:
- declarar variáveis (NUMBER. ex: 1, 2, 2.5)
- expressões aritméticas com 4 operadores + - * /
- precedência ( "(" , ")" )

## Como usar

- Baixa o Antlr no site oficial (https://www.antlr.org/download/antlr-4.13.2-complete.jar)
- você deve referenciar este JAR como biblioteca do seu projeto
- Gerar os arquivos JAVA a partir do arquivo Grammar.g4, o comando é este:
``` java -cp antlr-4.13.2-complete.jar org.antlr.v4.Tool Grammar.g4 -o .\src\io\compiler\core\ -package io.compiler.core```
- Rodar o arquivo MainClass.java

## Alterar o arquivo input.in para a gramática aceita
- Exemplo de arquivo e operações:
```
program
    x = 10
    y = 3
    (x*y)/2
endprog
```