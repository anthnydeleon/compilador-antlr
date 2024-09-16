package io.compiler.main;

import io.compiler.core.GrammarLexer;
import io.compiler.core.GrammarParser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class MainClass {
	public static void main(String[] args) {
		try {

			GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName("input.in"));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			GrammarParser parser = new GrammarParser(tokenStream);

			// Parseando o programa
			GrammarParser.ProgramContext programContext = parser.program();

			// Usando o Listener
			ParseTreeWalker walker = new ParseTreeWalker();
			MyListener listener = new MyListener();
			walker.walk(listener, programContext);

			System.out.println("\n\nGrámatica compilada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
