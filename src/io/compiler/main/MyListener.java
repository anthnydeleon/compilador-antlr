package io.compiler.main;

import io.compiler.core.GrammarBaseListener;
import io.compiler.core.GrammarParser;

import java.util.HashMap;

public class MyListener extends GrammarBaseListener {
    // Eu utilizo um HashMap para armazenar variáveis e seus valores
    private HashMap<String, Double> symbolTable = new HashMap<>();

    @Override
    public void enterAssignExpr(GrammarParser.AssignExprContext ctx) {
        // Mostrar no console variaveis utilizadas
        String id = ctx.ID().getText();
        double value = evaluateExpression(ctx.expr());
        symbolTable.put(id, value);
        System.out.println("Variavel: " + id + " = " + value);
    }

    @Override
    public void exitExpr(GrammarParser.ExprContext ctx) {
        if (ctx.getParent() instanceof GrammarParser.StatementContext) {
            double result = evaluateExpression(ctx);
            // Resultado final da expressão
            System.out.println("Resultado da expressao: " + result);
        }
    }

    private double evaluateExpression(GrammarParser.ExprContext ctx) {
        // Avaliando o primeiro termo da expressão
        double result = evaluateTerm(ctx.term(0));

        for (int i = 1; i < ctx.term().size(); i++) {
            String operator = ctx.OP(i - 1).getText();
            double nextTerm = evaluateTerm(ctx.term(i));

            switch (operator) {
                case "+":
                    result += nextTerm;
                    break;
                case "-":
                    result -= nextTerm;
                    break;
                case "*":
                    result *= nextTerm;
                    break;
                case "/":
                    result /= nextTerm;
                    break;
            }
        }

        return result;
    }

    private double evaluateTerm(GrammarParser.TermContext ctx) {
        if (ctx.expr() != null) {
            // Se o termo é uma expressão entre parênteses, avalio recursivamente
            return evaluateExpression(ctx.expr());
        } else if (ctx.ID() != null) {
            // Se o termo é uma variável, recupero seu valor da tabela de símbolos
            String id = ctx.ID().getText();
            if (symbolTable.containsKey(id)) {
                return symbolTable.get(id);
            } else {
                throw new RuntimeException("Variavel undefined: " + id);
            }
        } else if (ctx.NUM() != null) {
            return Double.parseDouble(ctx.NUM().getText());
        }

        return 0;
    }
}
