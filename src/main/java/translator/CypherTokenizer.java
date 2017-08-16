package translator;

import intermediate_rep.DecodedQuery;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parsing_lexing.CypherLexer;
import parsing_lexing.CypherParser;

import java.util.ArrayList;

/**
 * Generates tokens from the Cypher input. Uses ANTLRv4 tool.
 */
public class CypherTokenizer {
    private static CypherWalker cypherWalker;

    /**
     * Takes Cypher query as input and returns a DecodedQuery object representing it.
     *
     * @param cypher      Cypher input.
     * @param DEBUG_PRINT Print out debug statements or not.
     * @return DecodedQuery object representing the Cypher input.
     * @throws Exception Error somewhere.
     */
    public static DecodedQuery decode(String cypher, boolean DEBUG_PRINT) {
        return CypherTranslator.generateDecodedQuery(getTokenList(cypher, DEBUG_PRINT), cypherWalker);
    }

    /**
     * Extract the token list from the Cypher input. Uses ANTLR classes to perform this. Some tokens
     * are excluded, such as EOF and semi colons.
     *
     * @param cyp         Cypher input as text.
     * @param DEBUG_PRINT Print out debug statements or not.
     * @return A list of tokens as deciphered by the ANTLR classes, based on the openCypher grammar.
     */
    public static ArrayList<String> getTokenList(String cyp, boolean DEBUG_PRINT) {
        CypherLexer lexer = new CypherLexer(new ANTLRInputStream(cyp));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        CypherParser parser = new CypherParser(tokens);

        // dangerous - comment out if something is going wrong.
        parser.removeErrorListeners();

        ParseTree tree = parser.cypher();
        ParseTreeWalker walker = new ParseTreeWalker();

        cypherWalker = null;
        cypherWalker = new CypherWalker();
        walker.walk(cypherWalker, tree);

        if (DEBUG_PRINT) cypherWalker.printInformation();

        ArrayList<String> tokenList = new ArrayList<>();

        for (Object t : tokens.getTokens()) {
            CommonToken tok = (CommonToken) t;
            String s = tok.getText().toLowerCase();

            // exclude some tokens from the list of tokens. This includes the EOF pointer,
            // semi-colons, and alias artifacts.
            if (!" ".equals(s) && !"<eof>".equals(s) && !";".equals(s) && !"as".equals(s) &&
                    !cypherWalker.getAlias().contains(s)) {
                tokenList.add(s);
            }
        }
        return tokenList;
    }
}
