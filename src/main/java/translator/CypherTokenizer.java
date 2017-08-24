/*
 * Copyright (c) 2017.
 *
 * Oliver Crawford <o.crawford@hotmail.co.uk>
 * Lucian Carata <lc525@cam.ac.uk>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
