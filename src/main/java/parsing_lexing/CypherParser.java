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

package parsing_lexing;// Generated from Cypher.g4 by ANTLR 4.7

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CypherParser extends Parser {
    static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9,
            T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17,
            T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24,
            T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31,
            T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38,
            T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45,
            T__45=46, UNION=47, ALL=48, OPTIONAL=49, MATCH=50, UNWIND=51, AS=52, MERGE=53,
            ON=54, CREATE=55, SET=56, DETACH=57, DELETE=58, REMOVE=59, CALL=60, YIELD=61,
            WITH=62, DISTINCT=63, RETURN=64, ORDER=65, BY=66, L_SKIP=67, LIMIT=68,
            ASCENDING=69, ASC=70, DESCENDING=71, DESC=72, WHERE=73, OR=74, XOR=75,
            AND=76, NOT=77, IN=78, STARTS=79, ENDS=80, CONTAINS=81, IS=82, NULL=83,
            COUNT=84, FILTER=85, EXTRACT=86, ANY=87, NONE=88, SINGLE=89, TRUE=90,
            FALSE=91, EXISTS=92, CASE=93, ELSE=94, END=95, WHEN=96, THEN=97, StringLiteral=98,
            EscapedChar=99, HexInteger=100, DecimalInteger=101, OctalInteger=102,
            HexLetter=103, HexDigit=104, Digit=105, NonZeroDigit=106, NonZeroOctDigit=107,
            OctDigit=108, ZeroDigit=109, ExponentDecimalReal=110, RegularDecimalReal=111,
            CONSTRAINT=112, DO=113, FOR=114, REQUIRE=115, UNIQUE=116, MANDATORY=117,
            SCALAR=118, OF=119, ADD=120, DROP=121, UnescapedSymbolicName=122, IdentifierStart=123,
            IdentifierPart=124, EscapedSymbolicName=125, SP=126, WHITESPACE=127, Comment=128;
    public static final int
            RULE_cypher = 0, RULE_statement = 1, RULE_query = 2, RULE_regularQuery = 3,
            RULE_union = 4, RULE_singleQuery = 5, RULE_singlePartQuery = 6, RULE_readOnlyEnd = 7,
            RULE_readUpdateEnd = 8, RULE_updatingEnd = 9, RULE_multiPartQuery = 10,
            RULE_readPart = 11, RULE_updatingPart = 12, RULE_updatingStartClause = 13,
            RULE_updatingClause = 14, RULE_readingClause = 15, RULE_match = 16, RULE_unwind = 17,
            RULE_merge = 18, RULE_mergeAction = 19, RULE_create = 20, RULE_set = 21,
            RULE_setItem = 22, RULE_delete = 23, RULE_remove = 24, RULE_removeItem = 25,
            RULE_inQueryCall = 26, RULE_standaloneCall = 27, RULE_yieldItems = 28,
            RULE_yieldItem = 29, RULE_with = 30, RULE_returnX = 31, RULE_returnBody = 32,
            RULE_returnItems = 33, RULE_returnItem = 34, RULE_order = 35, RULE_skip = 36,
            RULE_limit = 37, RULE_sortItem = 38, RULE_where = 39, RULE_pattern = 40,
            RULE_patternPart = 41, RULE_anonymousPatternPart = 42, RULE_patternElement = 43,
            RULE_nodePattern = 44, RULE_patternElementChain = 45, RULE_relationshipPattern = 46,
            RULE_relationshipDetail = 47, RULE_properties = 48, RULE_relationshipTypes = 49,
            RULE_nodeLabels = 50, RULE_nodeLabel = 51, RULE_rangeLiteral = 52, RULE_labelName = 53,
            RULE_relTypeName = 54, RULE_expression = 55, RULE_orExpression = 56, RULE_xorExpression = 57,
            RULE_andExpression = 58, RULE_notExpression = 59, RULE_comparisonExpression = 60,
            RULE_addOrSubtractExpression = 61, RULE_multiplyDivideModuloExpression = 62,
            RULE_powerOfExpression = 63, RULE_unaryAddOrSubtractExpression = 64, RULE_stringListNullOperatorExpression = 65,
            RULE_propertyOrLabelsExpression = 66, RULE_atom = 67, RULE_literal = 68,
            RULE_booleanLiteral = 69, RULE_listLiteral = 70, RULE_partialComparisonExpression = 71,
            RULE_parenthesizedExpression = 72, RULE_relationshipsPattern = 73, RULE_filterExpression = 74,
            RULE_idInColl = 75, RULE_functionInvocation = 76, RULE_functionName = 77,
            RULE_explicitProcedureInvocation = 78, RULE_implicitProcedureInvocation = 79,
            RULE_procedureResultField = 80, RULE_procedureName = 81, RULE_namespace = 82,
            RULE_listComprehension = 83, RULE_patternComprehension = 84, RULE_propertyLookup = 85,
            RULE_caseExpression = 86, RULE_caseAlternatives = 87, RULE_variable = 88,
            RULE_numberLiteral = 89, RULE_mapLiteral = 90, RULE_parameter = 91, RULE_propertyExpression = 92,
            RULE_propertyKeyName = 93, RULE_integerLiteral = 94, RULE_doubleLiteral = 95,
            RULE_schemaName = 96, RULE_reservedWord = 97, RULE_symbolicName = 98,
            RULE_leftArrowHead = 99, RULE_rightArrowHead = 100, RULE_dash = 101;
    public static final String[] ruleNames = {
            "cypher", "statement", "query", "regularQuery", "union", "singleQuery",
            "singlePartQuery", "readOnlyEnd", "readUpdateEnd", "updatingEnd", "multiPartQuery",
            "readPart", "updatingPart", "updatingStartClause", "updatingClause", "readingClause",
            "match", "unwind", "merge", "mergeAction", "create", "set", "setItem",
            "delete", "remove", "removeItem", "inQueryCall", "standaloneCall", "yieldItems",
            "yieldItem", "with", "returnX", "returnBody", "returnItems", "returnItem",
            "order", "skip", "limit", "sortItem", "where", "pattern", "patternPart",
            "anonymousPatternPart", "patternElement", "nodePattern", "patternElementChain",
            "relationshipPattern", "relationshipDetail", "properties", "relationshipTypes",
            "nodeLabels", "nodeLabel", "rangeLiteral", "labelName", "relTypeName",
            "expression", "orExpression", "xorExpression", "andExpression", "notExpression",
            "comparisonExpression", "addOrSubtractExpression", "multiplyDivideModuloExpression",
            "powerOfExpression", "unaryAddOrSubtractExpression", "stringListNullOperatorExpression",
            "propertyOrLabelsExpression", "atom", "literal", "booleanLiteral", "listLiteral",
            "partialComparisonExpression", "parenthesizedExpression", "relationshipsPattern",
            "filterExpression", "idInColl", "functionInvocation", "functionName",
            "explicitProcedureInvocation", "implicitProcedureInvocation", "procedureResultField",
            "procedureName", "namespace", "listComprehension", "patternComprehension",
            "propertyLookup", "caseExpression", "caseAlternatives", "variable", "numberLiteral",
            "mapLiteral", "parameter", "propertyExpression", "propertyKeyName", "integerLiteral",
            "doubleLiteral", "schemaName", "reservedWord", "symbolicName", "leftArrowHead",
            "rightArrowHead", "dash"
    };

    private static final String[] _LITERAL_NAMES = {
            null, "';'", "','", "'='", "'+='", "'-'", "'*'", "'('", "')'", "'['",
            "']'", "':'", "'|'", "'..'", "'+'", "'/'", "'%'", "'^'", "'=~'", "'<>'",
            "'<'", "'>'", "'<='", "'>='", "'.'", "'{'", "'}'", "'$'", "'\u00E2\u0178\u00A8'",
            "'\u00E3\u20AC\u02C6'", "'\u00EF\u00B9\u00A4'", "'\u00EF\u00BC\u0153'",
            "'\u00E2\u0178\u00A9'", "'\u00E3\u20AC\u2030'", "'\u00EF\u00B9\u00A5'",
            "'\u00EF\u00BC\u017E'", "'\u00C2\u00AD'", "'\u00E2\u20AC\uFFFD'", "'\u00E2\u20AC\u2018'",
            "'\u00E2\u20AC\u2019'", "'\u00E2\u20AC\u201C'", "'\u00E2\u20AC\u201D'",
            "'\u00E2\u20AC\u2022'", "'\u00E2\u02C6\u2019'", "'\u00EF\u00B9\u02DC'",
            "'\u00EF\u00B9\u00A3'", "'\u00EF\u00BC\uFFFD'", null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, "'0'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, "UNION",
            "ALL", "OPTIONAL", "MATCH", "UNWIND", "AS", "MERGE", "ON", "CREATE", "SET",
            "DETACH", "DELETE", "REMOVE", "CALL", "YIELD", "WITH", "DISTINCT", "RETURN",
            "ORDER", "BY", "L_SKIP", "LIMIT", "ASCENDING", "ASC", "DESCENDING", "DESC",
            "WHERE", "OR", "XOR", "AND", "NOT", "IN", "STARTS", "ENDS", "CONTAINS",
            "IS", "NULL", "COUNT", "FILTER", "EXTRACT", "ANY", "NONE", "SINGLE", "TRUE",
            "FALSE", "EXISTS", "CASE", "ELSE", "END", "WHEN", "THEN", "StringLiteral",
            "EscapedChar", "HexInteger", "DecimalInteger", "OctalInteger", "HexLetter",
            "HexDigit", "Digit", "NonZeroDigit", "NonZeroOctDigit", "OctDigit", "ZeroDigit",
            "ExponentDecimalReal", "RegularDecimalReal", "CONSTRAINT", "DO", "FOR",
            "REQUIRE", "UNIQUE", "MANDATORY", "SCALAR", "OF", "ADD", "DROP", "UnescapedSymbolicName",
            "IdentifierStart", "IdentifierPart", "EscapedSymbolicName", "SP", "WHITESPACE",
            "Comment"
    };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() { return "Cypher.g4"; }

    @Override
    public String[] getRuleNames() { return ruleNames; }

    @Override
    public String getSerializedATN() { return _serializedATN; }

    @Override
    public ATN getATN() { return _ATN; }

    public CypherParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
    }

    public static class CypherContext extends ParserRuleContext {
        public StatementContext statement() {
            return getRuleContext(StatementContext.class,0);
        }

        public TerminalNode EOF() { return getToken(CypherParser.EOF, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public CypherContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_cypher; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCypher(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCypher(this);
        }
    }

    public final CypherContext cypher() throws RecognitionException {
        CypherContext _localctx = new CypherContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_cypher);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(205);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(204);
                        match(SP);
                    }
                }

                setState(207);
                statement();
                setState(212);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
                    case 1: {
                        setState(209);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(208);
                                match(SP);
                            }
                        }

                        setState(211);
                        match(T__0);
                    }
                    break;
                }
                setState(215);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(214);
                        match(SP);
                    }
                }

                setState(217);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StatementContext extends ParserRuleContext {
        public QueryContext query() {
            return getRuleContext(QueryContext.class,0);
        }

        public StatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_statement; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStatement(this);
        }
    }

    public final StatementContext statement() throws RecognitionException {
        StatementContext _localctx = new StatementContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_statement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(219);
                query();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class QueryContext extends ParserRuleContext {
        public RegularQueryContext regularQuery() {
            return getRuleContext(RegularQueryContext.class,0);
        }

        public StandaloneCallContext standaloneCall() {
            return getRuleContext(StandaloneCallContext.class,0);
        }

        public QueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_query; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterQuery(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitQuery(this);
        }
    }

    public final QueryContext query() throws RecognitionException {
        QueryContext _localctx = new QueryContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_query);
        try {
            setState(223);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(221);
                    regularQuery();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(222);
                    standaloneCall();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RegularQueryContext extends ParserRuleContext {
        public SingleQueryContext singleQuery() {
            return getRuleContext(SingleQueryContext.class,0);
        }

        public List<UnionContext> union() {
            return getRuleContexts(UnionContext.class);
        }

        public UnionContext union(int i) {
            return getRuleContext(UnionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public RegularQueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_regularQuery; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRegularQuery(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRegularQuery(this);
        }
    }

    public final RegularQueryContext regularQuery() throws RecognitionException {
        RegularQueryContext _localctx = new RegularQueryContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_regularQuery);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(225);
                singleQuery();
                setState(232);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,6,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(227);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(226);
                                        match(SP);
                                    }
                                }

                                setState(229);
                                union();
                            }
                        }
                    }
                    setState(234);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,6,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class UnionContext extends ParserRuleContext {
        public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }

        public SingleQueryContext singleQuery() {
            return getRuleContext(SingleQueryContext.class,0);
        }

        public UnionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_union; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnion(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnion(this);
        }
    }

    public final UnionContext union() throws RecognitionException {
        UnionContext _localctx = new UnionContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_union);
        int _la;
        try {
            setState(247);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(235);
                        match(UNION);
                        setState(236);
                        match(SP);
                        setState(237);
                        match(ALL);
                        setState(239);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(238);
                                match(SP);
                            }
                        }

                        setState(241);
                        singleQuery();
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(242);
                        match(UNION);
                        setState(244);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(243);
                                match(SP);
                            }
                        }

                        setState(246);
                        singleQuery();
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SingleQueryContext extends ParserRuleContext {
        public SinglePartQueryContext singlePartQuery() {
            return getRuleContext(SinglePartQueryContext.class,0);
        }

        public MultiPartQueryContext multiPartQuery() {
            return getRuleContext(MultiPartQueryContext.class,0);
        }

        public SingleQueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_singleQuery; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSingleQuery(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSingleQuery(this);
        }
    }

    public final SingleQueryContext singleQuery() throws RecognitionException {
        SingleQueryContext _localctx = new SingleQueryContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_singleQuery);
        try {
            setState(251);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(249);
                    singlePartQuery();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(250);
                    multiPartQuery();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SinglePartQueryContext extends ParserRuleContext {
        public ReadOnlyEndContext readOnlyEnd() {
            return getRuleContext(ReadOnlyEndContext.class,0);
        }

        public ReadUpdateEndContext readUpdateEnd() {
            return getRuleContext(ReadUpdateEndContext.class,0);
        }

        public UpdatingEndContext updatingEnd() {
            return getRuleContext(UpdatingEndContext.class,0);
        }

        public SinglePartQueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_singlePartQuery; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSinglePartQuery(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSinglePartQuery(this);
        }
    }

    public final SinglePartQueryContext singlePartQuery() throws RecognitionException {
        SinglePartQueryContext _localctx = new SinglePartQueryContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_singlePartQuery);
        try {
            setState(256);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(253);
                    readOnlyEnd();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(254);
                    readUpdateEnd();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(255);
                    updatingEnd();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReadOnlyEndContext extends ParserRuleContext {
        public ReadPartContext readPart() {
            return getRuleContext(ReadPartContext.class,0);
        }

        public ReturnXContext returnX() {
            return getRuleContext(ReturnXContext.class,0);
        }

        public ReadOnlyEndContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_readOnlyEnd; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReadOnlyEnd(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReadOnlyEnd(this);
        }
    }

    public final ReadOnlyEndContext readOnlyEnd() throws RecognitionException {
        ReadOnlyEndContext _localctx = new ReadOnlyEndContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_readOnlyEnd);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(258);
                readPart();
                setState(259);
                returnX();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReadUpdateEndContext extends ParserRuleContext {
        public List<ReadingClauseContext> readingClause() {
            return getRuleContexts(ReadingClauseContext.class);
        }

        public ReadingClauseContext readingClause(int i) {
            return getRuleContext(ReadingClauseContext.class,i);
        }

        public List<UpdatingClauseContext> updatingClause() {
            return getRuleContexts(UpdatingClauseContext.class);
        }

        public UpdatingClauseContext updatingClause(int i) {
            return getRuleContext(UpdatingClauseContext.class,i);
        }

        public ReturnXContext returnX() {
            return getRuleContext(ReturnXContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ReadUpdateEndContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_readUpdateEnd; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReadUpdateEnd(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReadUpdateEnd(this);
        }
    }

    public final ReadUpdateEndContext readUpdateEnd() throws RecognitionException {
        ReadUpdateEndContext _localctx = new ReadUpdateEndContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_readUpdateEnd);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(261);
                readingClause();
                setState(268);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,13,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(263);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(262);
                                        match(SP);
                                    }
                                }

                                setState(265);
                                readingClause();
                            }
                        }
                    }
                    setState(270);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,13,_ctx);
                }
                setState(275);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(272);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(271);
                                        match(SP);
                                    }
                                }

                                setState(274);
                                updatingClause();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(277);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,15,_ctx);
                } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
                setState(283);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
                    case 1: {
                        setState(280);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(279);
                                match(SP);
                            }
                        }

                        setState(282);
                        returnX();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class UpdatingEndContext extends ParserRuleContext {
        public UpdatingStartClauseContext updatingStartClause() {
            return getRuleContext(UpdatingStartClauseContext.class,0);
        }

        public List<UpdatingClauseContext> updatingClause() {
            return getRuleContexts(UpdatingClauseContext.class);
        }

        public UpdatingClauseContext updatingClause(int i) {
            return getRuleContext(UpdatingClauseContext.class,i);
        }

        public ReturnXContext returnX() {
            return getRuleContext(ReturnXContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public UpdatingEndContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_updatingEnd; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUpdatingEnd(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUpdatingEnd(this);
        }
    }

    public final UpdatingEndContext updatingEnd() throws RecognitionException {
        UpdatingEndContext _localctx = new UpdatingEndContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_updatingEnd);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(285);
                updatingStartClause();
                setState(292);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,19,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(287);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(286);
                                        match(SP);
                                    }
                                }

                                setState(289);
                                updatingClause();
                            }
                        }
                    }
                    setState(294);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,19,_ctx);
                }
                setState(299);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
                    case 1: {
                        setState(296);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(295);
                                match(SP);
                            }
                        }

                        setState(298);
                        returnX();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MultiPartQueryContext extends ParserRuleContext {
        public List<WithContext> with() {
            return getRuleContexts(WithContext.class);
        }

        public WithContext with(int i) {
            return getRuleContext(WithContext.class,i);
        }

        public SinglePartQueryContext singlePartQuery() {
            return getRuleContext(SinglePartQueryContext.class,0);
        }

        public List<ReadPartContext> readPart() {
            return getRuleContexts(ReadPartContext.class);
        }

        public ReadPartContext readPart(int i) {
            return getRuleContext(ReadPartContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<UpdatingPartContext> updatingPart() {
            return getRuleContexts(UpdatingPartContext.class);
        }

        public UpdatingPartContext updatingPart(int i) {
            return getRuleContext(UpdatingPartContext.class,i);
        }

        public UpdatingStartClauseContext updatingStartClause() {
            return getRuleContext(UpdatingStartClauseContext.class,0);
        }

        public MultiPartQueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_multiPartQuery; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMultiPartQuery(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMultiPartQuery(this);
        }
    }

    public final MultiPartQueryContext multiPartQuery() throws RecognitionException {
        MultiPartQueryContext _localctx = new MultiPartQueryContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_multiPartQuery);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(308);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case OPTIONAL:
                    case MATCH:
                    case UNWIND:
                    case CALL:
                    case WITH: {
                        setState(301);
                        readPart();
                    }
                    break;
                    case MERGE:
                    case CREATE: {
                        {
                            setState(302);
                            updatingStartClause();
                            setState(304);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==SP) {
                                {
                                    setState(303);
                                    match(SP);
                                }
                            }

                            setState(306);
                            updatingPart();
                        }
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                setState(310);
                with();
                setState(312);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(311);
                        match(SP);
                    }
                }

                setState(322);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,26,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(314);
                                readPart();
                                setState(315);
                                updatingPart();
                                setState(316);
                                with();
                                setState(318);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(317);
                                        match(SP);
                                    }
                                }

                            }
                        }
                    }
                    setState(324);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,26,_ctx);
                }
                setState(325);
                singlePartQuery();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReadPartContext extends ParserRuleContext {
        public List<ReadingClauseContext> readingClause() {
            return getRuleContexts(ReadingClauseContext.class);
        }

        public ReadingClauseContext readingClause(int i) {
            return getRuleContext(ReadingClauseContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ReadPartContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_readPart; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReadPart(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReadPart(this);
        }
    }

    public final ReadPartContext readPart() throws RecognitionException {
        ReadPartContext _localctx = new ReadPartContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_readPart);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(333);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPTIONAL) | (1L << MATCH) | (1L << UNWIND) | (1L << CALL))) != 0)) {
                    {
                        {
                            setState(327);
                            readingClause();
                            setState(329);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==SP) {
                                {
                                    setState(328);
                                    match(SP);
                                }
                            }

                        }
                    }
                    setState(335);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class UpdatingPartContext extends ParserRuleContext {
        public List<UpdatingClauseContext> updatingClause() {
            return getRuleContexts(UpdatingClauseContext.class);
        }

        public UpdatingClauseContext updatingClause(int i) {
            return getRuleContext(UpdatingClauseContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public UpdatingPartContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_updatingPart; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUpdatingPart(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUpdatingPart(this);
        }
    }

    public final UpdatingPartContext updatingPart() throws RecognitionException {
        UpdatingPartContext _localctx = new UpdatingPartContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_updatingPart);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(342);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MERGE) | (1L << CREATE) | (1L << SET) | (1L << DETACH) | (1L << DELETE) | (1L << REMOVE))) != 0)) {
                    {
                        {
                            setState(336);
                            updatingClause();
                            setState(338);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==SP) {
                                {
                                    setState(337);
                                    match(SP);
                                }
                            }

                        }
                    }
                    setState(344);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class UpdatingStartClauseContext extends ParserRuleContext {
        public CreateContext create() {
            return getRuleContext(CreateContext.class,0);
        }

        public MergeContext merge() {
            return getRuleContext(MergeContext.class,0);
        }

        public UpdatingStartClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_updatingStartClause; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUpdatingStartClause(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUpdatingStartClause(this);
        }
    }

    public final UpdatingStartClauseContext updatingStartClause() throws RecognitionException {
        UpdatingStartClauseContext _localctx = new UpdatingStartClauseContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_updatingStartClause);
        try {
            setState(347);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case CREATE:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(345);
                    create();
                }
                break;
                case MERGE:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(346);
                    merge();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class UpdatingClauseContext extends ParserRuleContext {
        public CreateContext create() {
            return getRuleContext(CreateContext.class,0);
        }

        public MergeContext merge() {
            return getRuleContext(MergeContext.class,0);
        }

        public DeleteContext delete() {
            return getRuleContext(DeleteContext.class,0);
        }

        public SetContext set() {
            return getRuleContext(SetContext.class,0);
        }

        public RemoveContext remove() {
            return getRuleContext(RemoveContext.class,0);
        }

        public UpdatingClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_updatingClause; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUpdatingClause(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUpdatingClause(this);
        }
    }

    public final UpdatingClauseContext updatingClause() throws RecognitionException {
        UpdatingClauseContext _localctx = new UpdatingClauseContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_updatingClause);
        try {
            setState(354);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case CREATE:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(349);
                    create();
                }
                break;
                case MERGE:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(350);
                    merge();
                }
                break;
                case DETACH:
                case DELETE:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(351);
                    delete();
                }
                break;
                case SET:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(352);
                    set();
                }
                break;
                case REMOVE:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(353);
                    remove();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReadingClauseContext extends ParserRuleContext {
        public MatchContext match() {
            return getRuleContext(MatchContext.class,0);
        }

        public UnwindContext unwind() {
            return getRuleContext(UnwindContext.class,0);
        }

        public InQueryCallContext inQueryCall() {
            return getRuleContext(InQueryCallContext.class,0);
        }

        public ReadingClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_readingClause; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReadingClause(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReadingClause(this);
        }
    }

    public final ReadingClauseContext readingClause() throws RecognitionException {
        ReadingClauseContext _localctx = new ReadingClauseContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_readingClause);
        try {
            setState(359);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case OPTIONAL:
                case MATCH:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(356);
                    match();
                }
                break;
                case UNWIND:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(357);
                    unwind();
                }
                break;
                case CALL:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(358);
                    inQueryCall();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MatchContext extends ParserRuleContext {
        public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }

        public PatternContext pattern() {
            return getRuleContext(PatternContext.class,0);
        }

        public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public WhereContext where() {
            return getRuleContext(WhereContext.class,0);
        }

        public MatchContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_match; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMatch(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMatch(this);
        }
    }

    public final MatchContext match() throws RecognitionException {
        MatchContext _localctx = new MatchContext(_ctx, getState());
        enterRule(_localctx, 32, RULE_match);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(363);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==OPTIONAL) {
                    {
                        setState(361);
                        match(OPTIONAL);
                        setState(362);
                        match(SP);
                    }
                }

                setState(365);
                match(MATCH);
                setState(367);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(366);
                        match(SP);
                    }
                }

                setState(369);
                pattern();
                setState(374);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
                    case 1: {
                        setState(371);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(370);
                                match(SP);
                            }
                        }

                        setState(373);
                        where();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class UnwindContext extends ParserRuleContext {
        public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode AS() { return getToken(CypherParser.AS, 0); }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public UnwindContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_unwind; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnwind(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnwind(this);
        }
    }

    public final UnwindContext unwind() throws RecognitionException {
        UnwindContext _localctx = new UnwindContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_unwind);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(376);
                match(UNWIND);
                setState(378);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(377);
                        match(SP);
                    }
                }

                setState(380);
                expression();
                setState(381);
                match(SP);
                setState(382);
                match(AS);
                setState(383);
                match(SP);
                setState(384);
                variable();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MergeContext extends ParserRuleContext {
        public TerminalNode MERGE() { return getToken(CypherParser.MERGE, 0); }

        public PatternPartContext patternPart() {
            return getRuleContext(PatternPartContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<MergeActionContext> mergeAction() {
            return getRuleContexts(MergeActionContext.class);
        }

        public MergeActionContext mergeAction(int i) {
            return getRuleContext(MergeActionContext.class,i);
        }

        public MergeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_merge; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMerge(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMerge(this);
        }
    }

    public final MergeContext merge() throws RecognitionException {
        MergeContext _localctx = new MergeContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_merge);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(386);
                match(MERGE);
                setState(388);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(387);
                        match(SP);
                    }
                }

                setState(390);
                patternPart();
                setState(395);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,40,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(391);
                                match(SP);
                                setState(392);
                                mergeAction();
                            }
                        }
                    }
                    setState(397);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,40,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MergeActionContext extends ParserRuleContext {
        public TerminalNode ON() { return getToken(CypherParser.ON, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }

        public SetContext set() {
            return getRuleContext(SetContext.class,0);
        }

        public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }

        public MergeActionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_mergeAction; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMergeAction(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMergeAction(this);
        }
    }

    public final MergeActionContext mergeAction() throws RecognitionException {
        MergeActionContext _localctx = new MergeActionContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_mergeAction);
        try {
            setState(408);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(398);
                        match(ON);
                        setState(399);
                        match(SP);
                        setState(400);
                        match(MATCH);
                        setState(401);
                        match(SP);
                        setState(402);
                        set();
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(403);
                        match(ON);
                        setState(404);
                        match(SP);
                        setState(405);
                        match(CREATE);
                        setState(406);
                        match(SP);
                        setState(407);
                        set();
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CreateContext extends ParserRuleContext {
        public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }

        public PatternContext pattern() {
            return getRuleContext(PatternContext.class,0);
        }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public CreateContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_create; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCreate(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCreate(this);
        }
    }

    public final CreateContext create() throws RecognitionException {
        CreateContext _localctx = new CreateContext(_ctx, getState());
        enterRule(_localctx, 40, RULE_create);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(410);
                match(CREATE);
                setState(412);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(411);
                        match(SP);
                    }
                }

                setState(414);
                pattern();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SetContext extends ParserRuleContext {
        public TerminalNode SET() { return getToken(CypherParser.SET, 0); }

        public List<SetItemContext> setItem() {
            return getRuleContexts(SetItemContext.class);
        }

        public SetItemContext setItem(int i) {
            return getRuleContext(SetItemContext.class,i);
        }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public SetContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_set; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSet(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSet(this);
        }
    }

    public final SetContext set() throws RecognitionException {
        SetContext _localctx = new SetContext(_ctx, getState());
        enterRule(_localctx, 42, RULE_set);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(416);
                match(SET);
                setState(418);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(417);
                        match(SP);
                    }
                }

                setState(420);
                setItem();
                setState(425);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la==T__1) {
                    {
                        {
                            setState(421);
                            match(T__1);
                            setState(422);
                            setItem();
                        }
                    }
                    setState(427);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SetItemContext extends ParserRuleContext {
        public PropertyExpressionContext propertyExpression() {
            return getRuleContext(PropertyExpressionContext.class,0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public NodeLabelsContext nodeLabels() {
            return getRuleContext(NodeLabelsContext.class,0);
        }

        public SetItemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_setItem; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSetItem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSetItem(this);
        }
    }

    public final SetItemContext setItem() throws RecognitionException {
        SetItemContext _localctx = new SetItemContext(_ctx, getState());
        enterRule(_localctx, 44, RULE_setItem);
        int _la;
        try {
            setState(464);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(428);
                        propertyExpression();
                        setState(430);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(429);
                                match(SP);
                            }
                        }

                        setState(432);
                        match(T__2);
                        setState(434);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(433);
                                match(SP);
                            }
                        }

                        setState(436);
                        expression();
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(438);
                        variable();
                        setState(440);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(439);
                                match(SP);
                            }
                        }

                        setState(442);
                        match(T__2);
                        setState(444);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(443);
                                match(SP);
                            }
                        }

                        setState(446);
                        expression();
                    }
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    {
                        setState(448);
                        variable();
                        setState(450);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(449);
                                match(SP);
                            }
                        }

                        setState(452);
                        match(T__3);
                        setState(454);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(453);
                                match(SP);
                            }
                        }

                        setState(456);
                        expression();
                    }
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    {
                        setState(458);
                        variable();
                        setState(460);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(459);
                                match(SP);
                            }
                        }

                        setState(462);
                        nodeLabels();
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class DeleteContext extends ParserRuleContext {
        public TerminalNode DELETE() { return getToken(CypherParser.DELETE, 0); }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public TerminalNode DETACH() { return getToken(CypherParser.DETACH, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public DeleteContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_delete; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDelete(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDelete(this);
        }
    }

    public final DeleteContext delete() throws RecognitionException {
        DeleteContext _localctx = new DeleteContext(_ctx, getState());
        enterRule(_localctx, 46, RULE_delete);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(468);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==DETACH) {
                    {
                        setState(466);
                        match(DETACH);
                        setState(467);
                        match(SP);
                    }
                }

                setState(470);
                match(DELETE);
                setState(472);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(471);
                        match(SP);
                    }
                }

                setState(474);
                expression();
                setState(485);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,57,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(476);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(475);
                                        match(SP);
                                    }
                                }

                                setState(478);
                                match(T__1);
                                setState(480);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(479);
                                        match(SP);
                                    }
                                }

                                setState(482);
                                expression();
                            }
                        }
                    }
                    setState(487);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,57,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RemoveContext extends ParserRuleContext {
        public TerminalNode REMOVE() { return getToken(CypherParser.REMOVE, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<RemoveItemContext> removeItem() {
            return getRuleContexts(RemoveItemContext.class);
        }

        public RemoveItemContext removeItem(int i) {
            return getRuleContext(RemoveItemContext.class,i);
        }

        public RemoveContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_remove; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRemove(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRemove(this);
        }
    }

    public final RemoveContext remove() throws RecognitionException {
        RemoveContext _localctx = new RemoveContext(_ctx, getState());
        enterRule(_localctx, 48, RULE_remove);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(488);
                match(REMOVE);
                setState(489);
                match(SP);
                setState(490);
                removeItem();
                setState(501);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,60,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(492);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(491);
                                        match(SP);
                                    }
                                }

                                setState(494);
                                match(T__1);
                                setState(496);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(495);
                                        match(SP);
                                    }
                                }

                                setState(498);
                                removeItem();
                            }
                        }
                    }
                    setState(503);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,60,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RemoveItemContext extends ParserRuleContext {
        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public NodeLabelsContext nodeLabels() {
            return getRuleContext(NodeLabelsContext.class,0);
        }

        public PropertyExpressionContext propertyExpression() {
            return getRuleContext(PropertyExpressionContext.class,0);
        }

        public RemoveItemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_removeItem; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRemoveItem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRemoveItem(this);
        }
    }

    public final RemoveItemContext removeItem() throws RecognitionException {
        RemoveItemContext _localctx = new RemoveItemContext(_ctx, getState());
        enterRule(_localctx, 50, RULE_removeItem);
        try {
            setState(508);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(504);
                        variable();
                        setState(505);
                        nodeLabels();
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(507);
                    propertyExpression();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InQueryCallContext extends ParserRuleContext {
        public TerminalNode CALL() { return getToken(CypherParser.CALL, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ExplicitProcedureInvocationContext explicitProcedureInvocation() {
            return getRuleContext(ExplicitProcedureInvocationContext.class,0);
        }

        public TerminalNode YIELD() { return getToken(CypherParser.YIELD, 0); }

        public YieldItemsContext yieldItems() {
            return getRuleContext(YieldItemsContext.class,0);
        }

        public InQueryCallContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_inQueryCall; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterInQueryCall(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitInQueryCall(this);
        }
    }

    public final InQueryCallContext inQueryCall() throws RecognitionException {
        InQueryCallContext _localctx = new InQueryCallContext(_ctx, getState());
        enterRule(_localctx, 52, RULE_inQueryCall);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(510);
                match(CALL);
                setState(511);
                match(SP);
                setState(512);
                explicitProcedureInvocation();
                setState(519);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
                    case 1: {
                        setState(514);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(513);
                                match(SP);
                            }
                        }

                        setState(516);
                        match(YIELD);
                        setState(517);
                        match(SP);
                        setState(518);
                        yieldItems();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StandaloneCallContext extends ParserRuleContext {
        public TerminalNode CALL() { return getToken(CypherParser.CALL, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ExplicitProcedureInvocationContext explicitProcedureInvocation() {
            return getRuleContext(ExplicitProcedureInvocationContext.class,0);
        }

        public ImplicitProcedureInvocationContext implicitProcedureInvocation() {
            return getRuleContext(ImplicitProcedureInvocationContext.class,0);
        }

        public TerminalNode YIELD() { return getToken(CypherParser.YIELD, 0); }

        public YieldItemsContext yieldItems() {
            return getRuleContext(YieldItemsContext.class,0);
        }

        public StandaloneCallContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_standaloneCall; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStandaloneCall(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStandaloneCall(this);
        }
    }

    public final StandaloneCallContext standaloneCall() throws RecognitionException {
        StandaloneCallContext _localctx = new StandaloneCallContext(_ctx, getState());
        enterRule(_localctx, 54, RULE_standaloneCall);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(521);
                match(CALL);
                setState(522);
                match(SP);
                setState(525);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
                    case 1: {
                        setState(523);
                        explicitProcedureInvocation();
                    }
                    break;
                    case 2: {
                        setState(524);
                        implicitProcedureInvocation();
                    }
                    break;
                }
                setState(531);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
                    case 1: {
                        setState(527);
                        match(SP);
                        setState(528);
                        match(YIELD);
                        setState(529);
                        match(SP);
                        setState(530);
                        yieldItems();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class YieldItemsContext extends ParserRuleContext {
        public List<YieldItemContext> yieldItem() {
            return getRuleContexts(YieldItemContext.class);
        }

        public YieldItemContext yieldItem(int i) {
            return getRuleContext(YieldItemContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public YieldItemsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_yieldItems; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterYieldItems(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitYieldItems(this);
        }
    }

    public final YieldItemsContext yieldItems() throws RecognitionException {
        YieldItemsContext _localctx = new YieldItemsContext(_ctx, getState());
        enterRule(_localctx, 56, RULE_yieldItems);
        int _la;
        try {
            int _alt;
            setState(548);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case ALL:
                case COUNT:
                case FILTER:
                case EXTRACT:
                case ANY:
                case NONE:
                case SINGLE:
                case HexLetter:
                case UnescapedSymbolicName:
                case EscapedSymbolicName:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(533);
                        yieldItem();
                        setState(544);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input,68,_ctx);
                        while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                            if ( _alt==1 ) {
                                {
                                    {
                                        setState(535);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(534);
                                                match(SP);
                                            }
                                        }

                                        setState(537);
                                        match(T__1);
                                        setState(539);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(538);
                                                match(SP);
                                            }
                                        }

                                        setState(541);
                                        yieldItem();
                                    }
                                }
                            }
                            setState(546);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input,68,_ctx);
                        }
                    }
                }
                break;
                case T__4:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(547);
                    match(T__4);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class YieldItemContext extends ParserRuleContext {
        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public ProcedureResultFieldContext procedureResultField() {
            return getRuleContext(ProcedureResultFieldContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode AS() { return getToken(CypherParser.AS, 0); }

        public YieldItemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_yieldItem; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterYieldItem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitYieldItem(this);
        }
    }

    public final YieldItemContext yieldItem() throws RecognitionException {
        YieldItemContext _localctx = new YieldItemContext(_ctx, getState());
        enterRule(_localctx, 58, RULE_yieldItem);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(555);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
                    case 1: {
                        setState(550);
                        procedureResultField();
                        setState(551);
                        match(SP);
                        setState(552);
                        match(AS);
                        setState(553);
                        match(SP);
                    }
                    break;
                }
                setState(557);
                variable();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class WithContext extends ParserRuleContext {
        public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ReturnBodyContext returnBody() {
            return getRuleContext(ReturnBodyContext.class,0);
        }

        public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }

        public WhereContext where() {
            return getRuleContext(WhereContext.class,0);
        }

        public WithContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_with; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWith(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWith(this);
        }
    }

    public final WithContext with() throws RecognitionException {
        WithContext _localctx = new WithContext(_ctx, getState());
        enterRule(_localctx, 60, RULE_with);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(559);
                match(WITH);
                setState(564);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
                    case 1: {
                        setState(561);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(560);
                                match(SP);
                            }
                        }

                        setState(563);
                        match(DISTINCT);
                    }
                    break;
                }
                setState(566);
                match(SP);
                setState(567);
                returnBody();
                setState(572);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
                    case 1: {
                        setState(569);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(568);
                                match(SP);
                            }
                        }

                        setState(571);
                        where();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReturnXContext extends ParserRuleContext {
        public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ReturnBodyContext returnBody() {
            return getRuleContext(ReturnBodyContext.class,0);
        }

        public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }

        public ReturnXContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_returnX; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnX(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnX(this);
        }
    }

    public final ReturnXContext returnX() throws RecognitionException {
        ReturnXContext _localctx = new ReturnXContext(_ctx, getState());
        enterRule(_localctx, 62, RULE_returnX);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(574);
                match(RETURN);
                setState(579);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
                    case 1: {
                        setState(576);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(575);
                                match(SP);
                            }
                        }

                        setState(578);
                        match(DISTINCT);
                    }
                    break;
                }
                setState(581);
                match(SP);
                setState(582);
                returnBody();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReturnBodyContext extends ParserRuleContext {
        public ReturnItemsContext returnItems() {
            return getRuleContext(ReturnItemsContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public OrderContext order() {
            return getRuleContext(OrderContext.class,0);
        }

        public SkipContext skip() {
            return getRuleContext(SkipContext.class,0);
        }

        public LimitContext limit() {
            return getRuleContext(LimitContext.class,0);
        }

        public ReturnBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_returnBody; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnBody(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnBody(this);
        }
    }

    public final ReturnBodyContext returnBody() throws RecognitionException {
        ReturnBodyContext _localctx = new ReturnBodyContext(_ctx, getState());
        enterRule(_localctx, 64, RULE_returnBody);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(584);
                returnItems();
                setState(587);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
                    case 1: {
                        setState(585);
                        match(SP);
                        setState(586);
                        order();
                    }
                    break;
                }
                setState(591);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
                    case 1: {
                        setState(589);
                        match(SP);
                        setState(590);
                        skip();
                    }
                    break;
                }
                setState(595);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
                    case 1: {
                        setState(593);
                        match(SP);
                        setState(594);
                        limit();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReturnItemsContext extends ParserRuleContext {
        public List<ReturnItemContext> returnItem() {
            return getRuleContexts(ReturnItemContext.class);
        }

        public ReturnItemContext returnItem(int i) {
            return getRuleContext(ReturnItemContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ReturnItemsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_returnItems; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItems(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItems(this);
        }
    }

    public final ReturnItemsContext returnItems() throws RecognitionException {
        ReturnItemsContext _localctx = new ReturnItemsContext(_ctx, getState());
        enterRule(_localctx, 66, RULE_returnItems);
        int _la;
        try {
            int _alt;
            setState(625);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__5:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(597);
                        match(T__5);
                        setState(608);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input,82,_ctx);
                        while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                            if ( _alt==1 ) {
                                {
                                    {
                                        setState(599);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(598);
                                                match(SP);
                                            }
                                        }

                                        setState(601);
                                        match(T__1);
                                        setState(603);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(602);
                                                match(SP);
                                            }
                                        }

                                        setState(605);
                                        returnItem();
                                    }
                                }
                            }
                            setState(610);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input,82,_ctx);
                        }
                    }
                }
                break;
                case T__4:
                case T__6:
                case T__8:
                case T__13:
                case T__24:
                case T__26:
                case ALL:
                case NOT:
                case NULL:
                case COUNT:
                case FILTER:
                case EXTRACT:
                case ANY:
                case NONE:
                case SINGLE:
                case TRUE:
                case FALSE:
                case EXISTS:
                case CASE:
                case StringLiteral:
                case HexInteger:
                case DecimalInteger:
                case OctalInteger:
                case HexLetter:
                case ExponentDecimalReal:
                case RegularDecimalReal:
                case UnescapedSymbolicName:
                case EscapedSymbolicName:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(611);
                        returnItem();
                        setState(622);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input,85,_ctx);
                        while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                            if ( _alt==1 ) {
                                {
                                    {
                                        setState(613);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(612);
                                                match(SP);
                                            }
                                        }

                                        setState(615);
                                        match(T__1);
                                        setState(617);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(616);
                                                match(SP);
                                            }
                                        }

                                        setState(619);
                                        returnItem();
                                    }
                                }
                            }
                            setState(624);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input,85,_ctx);
                        }
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReturnItemContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode AS() { return getToken(CypherParser.AS, 0); }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public ReturnItemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_returnItem; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItem(this);
        }
    }

    public final ReturnItemContext returnItem() throws RecognitionException {
        ReturnItemContext _localctx = new ReturnItemContext(_ctx, getState());
        enterRule(_localctx, 68, RULE_returnItem);
        try {
            setState(634);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(627);
                        expression();
                        setState(628);
                        match(SP);
                        setState(629);
                        match(AS);
                        setState(630);
                        match(SP);
                        setState(631);
                        variable();
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(633);
                    expression();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class OrderContext extends ParserRuleContext {
        public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode BY() { return getToken(CypherParser.BY, 0); }

        public List<SortItemContext> sortItem() {
            return getRuleContexts(SortItemContext.class);
        }

        public SortItemContext sortItem(int i) {
            return getRuleContext(SortItemContext.class,i);
        }

        public OrderContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_order; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterOrder(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitOrder(this);
        }
    }

    public final OrderContext order() throws RecognitionException {
        OrderContext _localctx = new OrderContext(_ctx, getState());
        enterRule(_localctx, 70, RULE_order);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(636);
                match(ORDER);
                setState(637);
                match(SP);
                setState(638);
                match(BY);
                setState(639);
                match(SP);
                setState(640);
                sortItem();
                setState(648);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la==T__1) {
                    {
                        {
                            setState(641);
                            match(T__1);
                            setState(643);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==SP) {
                                {
                                    setState(642);
                                    match(SP);
                                }
                            }

                            setState(645);
                            sortItem();
                        }
                    }
                    setState(650);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SkipContext extends ParserRuleContext {
        public TerminalNode L_SKIP() { return getToken(CypherParser.L_SKIP, 0); }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public SkipContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_skip; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSkip(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSkip(this);
        }
    }

    public final SkipContext skip() throws RecognitionException {
        SkipContext _localctx = new SkipContext(_ctx, getState());
        enterRule(_localctx, 72, RULE_skip);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(651);
                match(L_SKIP);
                setState(652);
                match(SP);
                setState(653);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class LimitContext extends ParserRuleContext {
        public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public LimitContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_limit; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLimit(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLimit(this);
        }
    }

    public final LimitContext limit() throws RecognitionException {
        LimitContext _localctx = new LimitContext(_ctx, getState());
        enterRule(_localctx, 74, RULE_limit);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(655);
                match(LIMIT);
                setState(656);
                match(SP);
                setState(657);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SortItemContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public TerminalNode ASCENDING() { return getToken(CypherParser.ASCENDING, 0); }

        public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }

        public TerminalNode DESCENDING() { return getToken(CypherParser.DESCENDING, 0); }

        public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public SortItemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_sortItem; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSortItem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSortItem(this);
        }
    }

    public final SortItemContext sortItem() throws RecognitionException {
        SortItemContext _localctx = new SortItemContext(_ctx, getState());
        enterRule(_localctx, 76, RULE_sortItem);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(659);
                expression();
                setState(664);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
                    case 1: {
                        setState(661);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(660);
                                match(SP);
                            }
                        }

                        setState(663);
                        _la = _input.LA(1);
                        if ( !(((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (ASCENDING - 69)) | (1L << (ASC - 69)) | (1L << (DESCENDING - 69)) | (1L << (DESC - 69)))) != 0)) ) {
                            _errHandler.recoverInline(this);
                        } else {
                            if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class WhereContext extends ParserRuleContext {
        public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public WhereContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_where; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWhere(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWhere(this);
        }
    }

    public final WhereContext where() throws RecognitionException {
        WhereContext _localctx = new WhereContext(_ctx, getState());
        enterRule(_localctx, 78, RULE_where);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(666);
                match(WHERE);
                setState(667);
                match(SP);
                setState(668);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PatternContext extends ParserRuleContext {
        public List<PatternPartContext> patternPart() {
            return getRuleContexts(PatternPartContext.class);
        }

        public PatternPartContext patternPart(int i) {
            return getRuleContext(PatternPartContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public PatternContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_pattern; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPattern(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPattern(this);
        }
    }

    public final PatternContext pattern() throws RecognitionException {
        PatternContext _localctx = new PatternContext(_ctx, getState());
        enterRule(_localctx, 80, RULE_pattern);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(670);
                patternPart();
                setState(681);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,94,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(672);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(671);
                                        match(SP);
                                    }
                                }

                                setState(674);
                                match(T__1);
                                setState(676);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(675);
                                        match(SP);
                                    }
                                }

                                setState(678);
                                patternPart();
                            }
                        }
                    }
                    setState(683);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,94,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PatternPartContext extends ParserRuleContext {
        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public AnonymousPatternPartContext anonymousPatternPart() {
            return getRuleContext(AnonymousPatternPartContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public PatternPartContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_patternPart; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternPart(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternPart(this);
        }
    }

    public final PatternPartContext patternPart() throws RecognitionException {
        PatternPartContext _localctx = new PatternPartContext(_ctx, getState());
        enterRule(_localctx, 82, RULE_patternPart);
        int _la;
        try {
            setState(695);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case ALL:
                case COUNT:
                case FILTER:
                case EXTRACT:
                case ANY:
                case NONE:
                case SINGLE:
                case HexLetter:
                case UnescapedSymbolicName:
                case EscapedSymbolicName:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(684);
                        variable();
                        setState(686);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(685);
                                match(SP);
                            }
                        }

                        setState(688);
                        match(T__2);
                        setState(690);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(689);
                                match(SP);
                            }
                        }

                        setState(692);
                        anonymousPatternPart();
                    }
                }
                break;
                case T__6:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(694);
                    anonymousPatternPart();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AnonymousPatternPartContext extends ParserRuleContext {
        public PatternElementContext patternElement() {
            return getRuleContext(PatternElementContext.class,0);
        }

        public AnonymousPatternPartContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_anonymousPatternPart; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAnonymousPatternPart(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAnonymousPatternPart(this);
        }
    }

    public final AnonymousPatternPartContext anonymousPatternPart() throws RecognitionException {
        AnonymousPatternPartContext _localctx = new AnonymousPatternPartContext(_ctx, getState());
        enterRule(_localctx, 84, RULE_anonymousPatternPart);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(697);
                patternElement();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PatternElementContext extends ParserRuleContext {
        public NodePatternContext nodePattern() {
            return getRuleContext(NodePatternContext.class,0);
        }

        public List<PatternElementChainContext> patternElementChain() {
            return getRuleContexts(PatternElementChainContext.class);
        }

        public PatternElementChainContext patternElementChain(int i) {
            return getRuleContext(PatternElementChainContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public PatternElementContext patternElement() {
            return getRuleContext(PatternElementContext.class,0);
        }

        public PatternElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_patternElement; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElement(this);
        }
    }

    public final PatternElementContext patternElement() throws RecognitionException {
        PatternElementContext _localctx = new PatternElementContext(_ctx, getState());
        enterRule(_localctx, 86, RULE_patternElement);
        int _la;
        try {
            int _alt;
            setState(713);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(699);
                        nodePattern();
                        setState(706);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input,99,_ctx);
                        while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                            if ( _alt==1 ) {
                                {
                                    {
                                        setState(701);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(700);
                                                match(SP);
                                            }
                                        }

                                        setState(703);
                                        patternElementChain();
                                    }
                                }
                            }
                            setState(708);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input,99,_ctx);
                        }
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(709);
                        match(T__6);
                        setState(710);
                        patternElement();
                        setState(711);
                        match(T__7);
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NodePatternContext extends ParserRuleContext {
        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public NodeLabelsContext nodeLabels() {
            return getRuleContext(NodeLabelsContext.class,0);
        }

        public PropertiesContext properties() {
            return getRuleContext(PropertiesContext.class,0);
        }

        public NodePatternContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_nodePattern; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodePattern(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodePattern(this);
        }
    }

    public final NodePatternContext nodePattern() throws RecognitionException {
        NodePatternContext _localctx = new NodePatternContext(_ctx, getState());
        enterRule(_localctx, 88, RULE_nodePattern);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(715);
                match(T__6);
                setState(717);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(716);
                        match(SP);
                    }
                }

                setState(723);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==ALL || ((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & ((1L << (COUNT - 84)) | (1L << (FILTER - 84)) | (1L << (EXTRACT - 84)) | (1L << (ANY - 84)) | (1L << (NONE - 84)) | (1L << (SINGLE - 84)) | (1L << (HexLetter - 84)) | (1L << (UnescapedSymbolicName - 84)) | (1L << (EscapedSymbolicName - 84)))) != 0)) {
                    {
                        setState(719);
                        variable();
                        setState(721);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(720);
                                match(SP);
                            }
                        }

                    }
                }

                setState(729);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==T__10) {
                    {
                        setState(725);
                        nodeLabels();
                        setState(727);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(726);
                                match(SP);
                            }
                        }

                    }
                }

                setState(735);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==T__24 || _la==T__26) {
                    {
                        setState(731);
                        properties();
                        setState(733);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(732);
                                match(SP);
                            }
                        }

                    }
                }

                setState(737);
                match(T__7);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PatternElementChainContext extends ParserRuleContext {
        public RelationshipPatternContext relationshipPattern() {
            return getRuleContext(RelationshipPatternContext.class,0);
        }

        public NodePatternContext nodePattern() {
            return getRuleContext(NodePatternContext.class,0);
        }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public PatternElementChainContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_patternElementChain; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElementChain(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElementChain(this);
        }
    }

    public final PatternElementChainContext patternElementChain() throws RecognitionException {
        PatternElementChainContext _localctx = new PatternElementChainContext(_ctx, getState());
        enterRule(_localctx, 90, RULE_patternElementChain);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(739);
                relationshipPattern();
                setState(741);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(740);
                        match(SP);
                    }
                }

                setState(743);
                nodePattern();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RelationshipPatternContext extends ParserRuleContext {
        public LeftArrowHeadContext leftArrowHead() {
            return getRuleContext(LeftArrowHeadContext.class,0);
        }

        public List<DashContext> dash() {
            return getRuleContexts(DashContext.class);
        }

        public DashContext dash(int i) {
            return getRuleContext(DashContext.class,i);
        }

        public RightArrowHeadContext rightArrowHead() {
            return getRuleContext(RightArrowHeadContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public RelationshipDetailContext relationshipDetail() {
            return getRuleContext(RelationshipDetailContext.class,0);
        }

        public RelationshipPatternContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_relationshipPattern; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipPattern(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipPattern(this);
        }
    }

    public final RelationshipPatternContext relationshipPattern() throws RecognitionException {
        RelationshipPatternContext _localctx = new RelationshipPatternContext(_ctx, getState());
        enterRule(_localctx, 92, RULE_relationshipPattern);
        int _la;
        try {
            setState(809);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,125,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(745);
                        leftArrowHead();
                        setState(747);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(746);
                                match(SP);
                            }
                        }

                        setState(749);
                        dash();
                        setState(751);
                        _errHandler.sync(this);
                        switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
                            case 1: {
                                setState(750);
                                match(SP);
                            }
                            break;
                        }
                        setState(754);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==T__8) {
                            {
                                setState(753);
                                relationshipDetail();
                            }
                        }

                        setState(757);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(756);
                                match(SP);
                            }
                        }

                        setState(759);
                        dash();
                        setState(761);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(760);
                                match(SP);
                            }
                        }

                        setState(763);
                        rightArrowHead();
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(765);
                        leftArrowHead();
                        setState(767);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(766);
                                match(SP);
                            }
                        }

                        setState(769);
                        dash();
                        setState(771);
                        _errHandler.sync(this);
                        switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
                            case 1: {
                                setState(770);
                                match(SP);
                            }
                            break;
                        }
                        setState(774);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==T__8) {
                            {
                                setState(773);
                                relationshipDetail();
                            }
                        }

                        setState(777);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(776);
                                match(SP);
                            }
                        }

                        setState(779);
                        dash();
                    }
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    {
                        setState(781);
                        dash();
                        setState(783);
                        _errHandler.sync(this);
                        switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
                            case 1: {
                                setState(782);
                                match(SP);
                            }
                            break;
                        }
                        setState(786);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==T__8) {
                            {
                                setState(785);
                                relationshipDetail();
                            }
                        }

                        setState(789);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(788);
                                match(SP);
                            }
                        }

                        setState(791);
                        dash();
                        setState(793);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(792);
                                match(SP);
                            }
                        }

                        setState(795);
                        rightArrowHead();
                    }
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    {
                        setState(797);
                        dash();
                        setState(799);
                        _errHandler.sync(this);
                        switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
                            case 1: {
                                setState(798);
                                match(SP);
                            }
                            break;
                        }
                        setState(802);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==T__8) {
                            {
                                setState(801);
                                relationshipDetail();
                            }
                        }

                        setState(805);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(804);
                                match(SP);
                            }
                        }

                        setState(807);
                        dash();
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RelationshipDetailContext extends ParserRuleContext {
        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public RelationshipTypesContext relationshipTypes() {
            return getRuleContext(RelationshipTypesContext.class,0);
        }

        public RangeLiteralContext rangeLiteral() {
            return getRuleContext(RangeLiteralContext.class,0);
        }

        public PropertiesContext properties() {
            return getRuleContext(PropertiesContext.class,0);
        }

        public RelationshipDetailContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_relationshipDetail; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipDetail(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipDetail(this);
        }
    }

    public final RelationshipDetailContext relationshipDetail() throws RecognitionException {
        RelationshipDetailContext _localctx = new RelationshipDetailContext(_ctx, getState());
        enterRule(_localctx, 94, RULE_relationshipDetail);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(811);
                match(T__8);
                setState(813);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(812);
                        match(SP);
                    }
                }

                setState(819);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==ALL || ((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & ((1L << (COUNT - 84)) | (1L << (FILTER - 84)) | (1L << (EXTRACT - 84)) | (1L << (ANY - 84)) | (1L << (NONE - 84)) | (1L << (SINGLE - 84)) | (1L << (HexLetter - 84)) | (1L << (UnescapedSymbolicName - 84)) | (1L << (EscapedSymbolicName - 84)))) != 0)) {
                    {
                        setState(815);
                        variable();
                        setState(817);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(816);
                                match(SP);
                            }
                        }

                    }
                }

                setState(825);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==T__10) {
                    {
                        setState(821);
                        relationshipTypes();
                        setState(823);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(822);
                                match(SP);
                            }
                        }

                    }
                }

                setState(828);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==T__5) {
                    {
                        setState(827);
                        rangeLiteral();
                    }
                }

                setState(834);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==T__24 || _la==T__26) {
                    {
                        setState(830);
                        properties();
                        setState(832);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(831);
                                match(SP);
                            }
                        }

                    }
                }

                setState(836);
                match(T__9);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PropertiesContext extends ParserRuleContext {
        public MapLiteralContext mapLiteral() {
            return getRuleContext(MapLiteralContext.class,0);
        }

        public ParameterContext parameter() {
            return getRuleContext(ParameterContext.class,0);
        }

        public PropertiesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_properties; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProperties(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProperties(this);
        }
    }

    public final PropertiesContext properties() throws RecognitionException {
        PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
        enterRule(_localctx, 96, RULE_properties);
        try {
            setState(840);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__24:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(838);
                    mapLiteral();
                }
                break;
                case T__26:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(839);
                    parameter();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RelationshipTypesContext extends ParserRuleContext {
        public List<RelTypeNameContext> relTypeName() {
            return getRuleContexts(RelTypeNameContext.class);
        }

        public RelTypeNameContext relTypeName(int i) {
            return getRuleContext(RelTypeNameContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public RelationshipTypesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_relationshipTypes; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipTypes(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipTypes(this);
        }
    }

    public final RelationshipTypesContext relationshipTypes() throws RecognitionException {
        RelationshipTypesContext _localctx = new RelationshipTypesContext(_ctx, getState());
        enterRule(_localctx, 98, RULE_relationshipTypes);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(842);
                match(T__10);
                setState(844);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(843);
                        match(SP);
                    }
                }

                setState(846);
                relTypeName();
                setState(860);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,139,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(848);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(847);
                                        match(SP);
                                    }
                                }

                                setState(850);
                                match(T__11);
                                setState(852);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==T__10) {
                                    {
                                        setState(851);
                                        match(T__10);
                                    }
                                }

                                setState(855);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(854);
                                        match(SP);
                                    }
                                }

                                setState(857);
                                relTypeName();
                            }
                        }
                    }
                    setState(862);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,139,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NodeLabelsContext extends ParserRuleContext {
        public List<NodeLabelContext> nodeLabel() {
            return getRuleContexts(NodeLabelContext.class);
        }

        public NodeLabelContext nodeLabel(int i) {
            return getRuleContext(NodeLabelContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public NodeLabelsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_nodeLabels; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabels(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabels(this);
        }
    }

    public final NodeLabelsContext nodeLabels() throws RecognitionException {
        NodeLabelsContext _localctx = new NodeLabelsContext(_ctx, getState());
        enterRule(_localctx, 100, RULE_nodeLabels);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(863);
                nodeLabel();
                setState(870);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,141,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(865);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(864);
                                        match(SP);
                                    }
                                }

                                setState(867);
                                nodeLabel();
                            }
                        }
                    }
                    setState(872);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,141,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NodeLabelContext extends ParserRuleContext {
        public LabelNameContext labelName() {
            return getRuleContext(LabelNameContext.class,0);
        }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public NodeLabelContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_nodeLabel; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabel(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabel(this);
        }
    }

    public final NodeLabelContext nodeLabel() throws RecognitionException {
        NodeLabelContext _localctx = new NodeLabelContext(_ctx, getState());
        enterRule(_localctx, 102, RULE_nodeLabel);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(873);
                match(T__10);
                setState(875);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(874);
                        match(SP);
                    }
                }

                setState(877);
                labelName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RangeLiteralContext extends ParserRuleContext {
        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<IntegerLiteralContext> integerLiteral() {
            return getRuleContexts(IntegerLiteralContext.class);
        }

        public IntegerLiteralContext integerLiteral(int i) {
            return getRuleContext(IntegerLiteralContext.class,i);
        }

        public RangeLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_rangeLiteral; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRangeLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRangeLiteral(this);
        }
    }

    public final RangeLiteralContext rangeLiteral() throws RecognitionException {
        RangeLiteralContext _localctx = new RangeLiteralContext(_ctx, getState());
        enterRule(_localctx, 104, RULE_rangeLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(879);
                match(T__5);
                setState(881);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(880);
                        match(SP);
                    }
                }

                setState(887);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((((_la - 100)) & ~0x3f) == 0 && ((1L << (_la - 100)) & ((1L << (HexInteger - 100)) | (1L << (DecimalInteger - 100)) | (1L << (OctalInteger - 100)))) != 0)) {
                    {
                        setState(883);
                        integerLiteral();
                        setState(885);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(884);
                                match(SP);
                            }
                        }

                    }
                }

                setState(899);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==T__12) {
                    {
                        setState(889);
                        match(T__12);
                        setState(891);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(890);
                                match(SP);
                            }
                        }

                        setState(897);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (((((_la - 100)) & ~0x3f) == 0 && ((1L << (_la - 100)) & ((1L << (HexInteger - 100)) | (1L << (DecimalInteger - 100)) | (1L << (OctalInteger - 100)))) != 0)) {
                            {
                                setState(893);
                                integerLiteral();
                                setState(895);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(894);
                                        match(SP);
                                    }
                                }

                            }
                        }

                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class LabelNameContext extends ParserRuleContext {
        public SchemaNameContext schemaName() {
            return getRuleContext(SchemaNameContext.class,0);
        }

        public LabelNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_labelName; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLabelName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLabelName(this);
        }
    }

    public final LabelNameContext labelName() throws RecognitionException {
        LabelNameContext _localctx = new LabelNameContext(_ctx, getState());
        enterRule(_localctx, 106, RULE_labelName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(901);
                schemaName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RelTypeNameContext extends ParserRuleContext {
        public SchemaNameContext schemaName() {
            return getRuleContext(SchemaNameContext.class,0);
        }

        public RelTypeNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_relTypeName; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelTypeName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelTypeName(this);
        }
    }

    public final RelTypeNameContext relTypeName() throws RecognitionException {
        RelTypeNameContext _localctx = new RelTypeNameContext(_ctx, getState());
        enterRule(_localctx, 108, RULE_relTypeName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(903);
                schemaName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExpressionContext extends ParserRuleContext {
        public OrExpressionContext orExpression() {
            return getRuleContext(OrExpressionContext.class,0);
        }

        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_expression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression(this);
        }
    }

    public final ExpressionContext expression() throws RecognitionException {
        ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
        enterRule(_localctx, 110, RULE_expression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(905);
                orExpression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class OrExpressionContext extends ParserRuleContext {
        public List<XorExpressionContext> xorExpression() {
            return getRuleContexts(XorExpressionContext.class);
        }

        public XorExpressionContext xorExpression(int i) {
            return getRuleContext(XorExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<TerminalNode> OR() { return getTokens(CypherParser.OR); }

        public TerminalNode OR(int i) {
            return getToken(CypherParser.OR, i);
        }

        public OrExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_orExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterOrExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitOrExpression(this);
        }
    }

    public final OrExpressionContext orExpression() throws RecognitionException {
        OrExpressionContext _localctx = new OrExpressionContext(_ctx, getState());
        enterRule(_localctx, 112, RULE_orExpression);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(907);
                xorExpression();
                setState(914);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,150,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(908);
                                match(SP);
                                setState(909);
                                match(OR);
                                setState(910);
                                match(SP);
                                setState(911);
                                xorExpression();
                            }
                        }
                    }
                    setState(916);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,150,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class XorExpressionContext extends ParserRuleContext {
        public List<AndExpressionContext> andExpression() {
            return getRuleContexts(AndExpressionContext.class);
        }

        public AndExpressionContext andExpression(int i) {
            return getRuleContext(AndExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<TerminalNode> XOR() { return getTokens(CypherParser.XOR); }

        public TerminalNode XOR(int i) {
            return getToken(CypherParser.XOR, i);
        }

        public XorExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_xorExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterXorExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitXorExpression(this);
        }
    }

    public final XorExpressionContext xorExpression() throws RecognitionException {
        XorExpressionContext _localctx = new XorExpressionContext(_ctx, getState());
        enterRule(_localctx, 114, RULE_xorExpression);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(917);
                andExpression();
                setState(924);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,151,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(918);
                                match(SP);
                                setState(919);
                                match(XOR);
                                setState(920);
                                match(SP);
                                setState(921);
                                andExpression();
                            }
                        }
                    }
                    setState(926);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,151,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AndExpressionContext extends ParserRuleContext {
        public List<NotExpressionContext> notExpression() {
            return getRuleContexts(NotExpressionContext.class);
        }

        public NotExpressionContext notExpression(int i) {
            return getRuleContext(NotExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<TerminalNode> AND() { return getTokens(CypherParser.AND); }

        public TerminalNode AND(int i) {
            return getToken(CypherParser.AND, i);
        }

        public AndExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_andExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAndExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAndExpression(this);
        }
    }

    public final AndExpressionContext andExpression() throws RecognitionException {
        AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
        enterRule(_localctx, 116, RULE_andExpression);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(927);
                notExpression();
                setState(934);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,152,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(928);
                                match(SP);
                                setState(929);
                                match(AND);
                                setState(930);
                                match(SP);
                                setState(931);
                                notExpression();
                            }
                        }
                    }
                    setState(936);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,152,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NotExpressionContext extends ParserRuleContext {
        public ComparisonExpressionContext comparisonExpression() {
            return getRuleContext(ComparisonExpressionContext.class,0);
        }

        public List<TerminalNode> NOT() { return getTokens(CypherParser.NOT); }

        public TerminalNode NOT(int i) {
            return getToken(CypherParser.NOT, i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public NotExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_notExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNotExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNotExpression(this);
        }
    }

    public final NotExpressionContext notExpression() throws RecognitionException {
        NotExpressionContext _localctx = new NotExpressionContext(_ctx, getState());
        enterRule(_localctx, 118, RULE_notExpression);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(943);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la==NOT) {
                    {
                        {
                            setState(937);
                            match(NOT);
                            setState(939);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==SP) {
                                {
                                    setState(938);
                                    match(SP);
                                }
                            }

                        }
                    }
                    setState(945);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(946);
                comparisonExpression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ComparisonExpressionContext extends ParserRuleContext {
        public AddOrSubtractExpressionContext addOrSubtractExpression() {
            return getRuleContext(AddOrSubtractExpressionContext.class,0);
        }

        public List<PartialComparisonExpressionContext> partialComparisonExpression() {
            return getRuleContexts(PartialComparisonExpressionContext.class);
        }

        public PartialComparisonExpressionContext partialComparisonExpression(int i) {
            return getRuleContext(PartialComparisonExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_comparisonExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterComparisonExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitComparisonExpression(this);
        }
    }

    public final ComparisonExpressionContext comparisonExpression() throws RecognitionException {
        ComparisonExpressionContext _localctx = new ComparisonExpressionContext(_ctx, getState());
        enterRule(_localctx, 120, RULE_comparisonExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(948);
                addOrSubtractExpression();
                setState(955);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,156,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(950);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(949);
                                        match(SP);
                                    }
                                }

                                setState(952);
                                partialComparisonExpression();
                            }
                        }
                    }
                    setState(957);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,156,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AddOrSubtractExpressionContext extends ParserRuleContext {
        public List<MultiplyDivideModuloExpressionContext> multiplyDivideModuloExpression() {
            return getRuleContexts(MultiplyDivideModuloExpressionContext.class);
        }

        public MultiplyDivideModuloExpressionContext multiplyDivideModuloExpression(int i) {
            return getRuleContext(MultiplyDivideModuloExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public AddOrSubtractExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_addOrSubtractExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAddOrSubtractExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAddOrSubtractExpression(this);
        }
    }

    public final AddOrSubtractExpressionContext addOrSubtractExpression() throws RecognitionException {
        AddOrSubtractExpressionContext _localctx = new AddOrSubtractExpressionContext(_ctx, getState());
        enterRule(_localctx, 122, RULE_addOrSubtractExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(958);
                multiplyDivideModuloExpression();
                setState(977);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,162,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            setState(975);
                            _errHandler.sync(this);
                            switch ( getInterpreter().adaptivePredict(_input,161,_ctx) ) {
                                case 1: {
                                    {
                                        setState(960);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(959);
                                                match(SP);
                                            }
                                        }

                                        setState(962);
                                        match(T__13);
                                        setState(964);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(963);
                                                match(SP);
                                            }
                                        }

                                        setState(966);
                                        multiplyDivideModuloExpression();
                                    }
                                }
                                break;
                                case 2: {
                                    {
                                        setState(968);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(967);
                                                match(SP);
                                            }
                                        }

                                        setState(970);
                                        match(T__4);
                                        setState(972);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(971);
                                                match(SP);
                                            }
                                        }

                                        setState(974);
                                        multiplyDivideModuloExpression();
                                    }
                                }
                                break;
                            }
                        }
                    }
                    setState(979);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,162,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MultiplyDivideModuloExpressionContext extends ParserRuleContext {
        public List<PowerOfExpressionContext> powerOfExpression() {
            return getRuleContexts(PowerOfExpressionContext.class);
        }

        public PowerOfExpressionContext powerOfExpression(int i) {
            return getRuleContext(PowerOfExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public MultiplyDivideModuloExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_multiplyDivideModuloExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMultiplyDivideModuloExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMultiplyDivideModuloExpression(this);
        }
    }

    public final MultiplyDivideModuloExpressionContext multiplyDivideModuloExpression() throws RecognitionException {
        MultiplyDivideModuloExpressionContext _localctx = new MultiplyDivideModuloExpressionContext(_ctx, getState());
        enterRule(_localctx, 124, RULE_multiplyDivideModuloExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(980);
                powerOfExpression();
                setState(1007);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,170,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            setState(1005);
                            _errHandler.sync(this);
                            switch ( getInterpreter().adaptivePredict(_input,169,_ctx) ) {
                                case 1: {
                                    {
                                        setState(982);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(981);
                                                match(SP);
                                            }
                                        }

                                        setState(984);
                                        match(T__5);
                                        setState(986);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(985);
                                                match(SP);
                                            }
                                        }

                                        setState(988);
                                        powerOfExpression();
                                    }
                                }
                                break;
                                case 2: {
                                    {
                                        setState(990);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(989);
                                                match(SP);
                                            }
                                        }

                                        setState(992);
                                        match(T__14);
                                        setState(994);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(993);
                                                match(SP);
                                            }
                                        }

                                        setState(996);
                                        powerOfExpression();
                                    }
                                }
                                break;
                                case 3: {
                                    {
                                        setState(998);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(997);
                                                match(SP);
                                            }
                                        }

                                        setState(1000);
                                        match(T__15);
                                        setState(1002);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(1001);
                                                match(SP);
                                            }
                                        }

                                        setState(1004);
                                        powerOfExpression();
                                    }
                                }
                                break;
                            }
                        }
                    }
                    setState(1009);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,170,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PowerOfExpressionContext extends ParserRuleContext {
        public List<UnaryAddOrSubtractExpressionContext> unaryAddOrSubtractExpression() {
            return getRuleContexts(UnaryAddOrSubtractExpressionContext.class);
        }

        public UnaryAddOrSubtractExpressionContext unaryAddOrSubtractExpression(int i) {
            return getRuleContext(UnaryAddOrSubtractExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public PowerOfExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_powerOfExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPowerOfExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPowerOfExpression(this);
        }
    }

    public final PowerOfExpressionContext powerOfExpression() throws RecognitionException {
        PowerOfExpressionContext _localctx = new PowerOfExpressionContext(_ctx, getState());
        enterRule(_localctx, 126, RULE_powerOfExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1010);
                unaryAddOrSubtractExpression();
                setState(1021);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,173,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(1012);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(1011);
                                        match(SP);
                                    }
                                }

                                setState(1014);
                                match(T__16);
                                setState(1016);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(1015);
                                        match(SP);
                                    }
                                }

                                setState(1018);
                                unaryAddOrSubtractExpression();
                            }
                        }
                    }
                    setState(1023);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,173,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class UnaryAddOrSubtractExpressionContext extends ParserRuleContext {
        public StringListNullOperatorExpressionContext stringListNullOperatorExpression() {
            return getRuleContext(StringListNullOperatorExpressionContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public UnaryAddOrSubtractExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_unaryAddOrSubtractExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnaryAddOrSubtractExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnaryAddOrSubtractExpression(this);
        }
    }

    public final UnaryAddOrSubtractExpressionContext unaryAddOrSubtractExpression() throws RecognitionException {
        UnaryAddOrSubtractExpressionContext _localctx = new UnaryAddOrSubtractExpressionContext(_ctx, getState());
        enterRule(_localctx, 128, RULE_unaryAddOrSubtractExpression);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1030);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la==T__4 || _la==T__13) {
                    {
                        {
                            setState(1024);
                            _la = _input.LA(1);
                            if ( !(_la==T__4 || _la==T__13) ) {
                                _errHandler.recoverInline(this);
                            } else {
                                if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                                _errHandler.reportMatch(this);
                                consume();
                            }
                            setState(1026);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==SP) {
                                {
                                    setState(1025);
                                    match(SP);
                                }
                            }

                        }
                    }
                    setState(1032);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(1033);
                stringListNullOperatorExpression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StringListNullOperatorExpressionContext extends ParserRuleContext {
        public List<PropertyOrLabelsExpressionContext> propertyOrLabelsExpression() {
            return getRuleContexts(PropertyOrLabelsExpressionContext.class);
        }

        public PropertyOrLabelsExpressionContext propertyOrLabelsExpression(int i) {
            return getRuleContext(PropertyOrLabelsExpressionContext.class,i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<TerminalNode> IS() { return getTokens(CypherParser.IS); }

        public TerminalNode IS(int i) {
            return getToken(CypherParser.IS, i);
        }

        public List<TerminalNode> NULL() { return getTokens(CypherParser.NULL); }

        public TerminalNode NULL(int i) {
            return getToken(CypherParser.NULL, i);
        }

        public List<TerminalNode> NOT() { return getTokens(CypherParser.NOT); }

        public TerminalNode NOT(int i) {
            return getToken(CypherParser.NOT, i);
        }

        public List<TerminalNode> IN() { return getTokens(CypherParser.IN); }

        public TerminalNode IN(int i) {
            return getToken(CypherParser.IN, i);
        }

        public List<TerminalNode> STARTS() { return getTokens(CypherParser.STARTS); }

        public TerminalNode STARTS(int i) {
            return getToken(CypherParser.STARTS, i);
        }

        public List<TerminalNode> WITH() { return getTokens(CypherParser.WITH); }

        public TerminalNode WITH(int i) {
            return getToken(CypherParser.WITH, i);
        }

        public List<TerminalNode> ENDS() { return getTokens(CypherParser.ENDS); }

        public TerminalNode ENDS(int i) {
            return getToken(CypherParser.ENDS, i);
        }

        public List<TerminalNode> CONTAINS() { return getTokens(CypherParser.CONTAINS); }

        public TerminalNode CONTAINS(int i) {
            return getToken(CypherParser.CONTAINS, i);
        }

        public StringListNullOperatorExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_stringListNullOperatorExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStringListNullOperatorExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStringListNullOperatorExpression(this);
        }
    }

    public final StringListNullOperatorExpressionContext stringListNullOperatorExpression() throws RecognitionException {
        StringListNullOperatorExpressionContext _localctx = new StringListNullOperatorExpressionContext(_ctx, getState());
        enterRule(_localctx, 130, RULE_stringListNullOperatorExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1035);
                propertyOrLabelsExpression();
                setState(1089);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,184,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            setState(1087);
                            _errHandler.sync(this);
                            switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
                                case 1: {
                                    {
                                        setState(1037);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(1036);
                                                match(SP);
                                            }
                                        }

                                        setState(1039);
                                        match(T__8);
                                        setState(1040);
                                        expression();
                                        setState(1041);
                                        match(T__9);
                                    }
                                }
                                break;
                                case 2: {
                                    {
                                        setState(1044);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(1043);
                                                match(SP);
                                            }
                                        }

                                        setState(1046);
                                        match(T__8);
                                        setState(1048);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__8) | (1L << T__13) | (1L << T__24) | (1L << T__26) | (1L << ALL))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (NOT - 77)) | (1L << (NULL - 77)) | (1L << (COUNT - 77)) | (1L << (FILTER - 77)) | (1L << (EXTRACT - 77)) | (1L << (ANY - 77)) | (1L << (NONE - 77)) | (1L << (SINGLE - 77)) | (1L << (TRUE - 77)) | (1L << (FALSE - 77)) | (1L << (EXISTS - 77)) | (1L << (CASE - 77)) | (1L << (StringLiteral - 77)) | (1L << (HexInteger - 77)) | (1L << (DecimalInteger - 77)) | (1L << (OctalInteger - 77)) | (1L << (HexLetter - 77)) | (1L << (ExponentDecimalReal - 77)) | (1L << (RegularDecimalReal - 77)) | (1L << (UnescapedSymbolicName - 77)) | (1L << (EscapedSymbolicName - 77)))) != 0)) {
                                            {
                                                setState(1047);
                                                expression();
                                            }
                                        }

                                        setState(1050);
                                        match(T__12);
                                        setState(1052);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__8) | (1L << T__13) | (1L << T__24) | (1L << T__26) | (1L << ALL))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (NOT - 77)) | (1L << (NULL - 77)) | (1L << (COUNT - 77)) | (1L << (FILTER - 77)) | (1L << (EXTRACT - 77)) | (1L << (ANY - 77)) | (1L << (NONE - 77)) | (1L << (SINGLE - 77)) | (1L << (TRUE - 77)) | (1L << (FALSE - 77)) | (1L << (EXISTS - 77)) | (1L << (CASE - 77)) | (1L << (StringLiteral - 77)) | (1L << (HexInteger - 77)) | (1L << (DecimalInteger - 77)) | (1L << (OctalInteger - 77)) | (1L << (HexLetter - 77)) | (1L << (ExponentDecimalReal - 77)) | (1L << (RegularDecimalReal - 77)) | (1L << (UnescapedSymbolicName - 77)) | (1L << (EscapedSymbolicName - 77)))) != 0)) {
                                            {
                                                setState(1051);
                                                expression();
                                            }
                                        }

                                        setState(1054);
                                        match(T__9);
                                    }
                                }
                                break;
                                case 3: {
                                    {
                                        setState(1071);
                                        _errHandler.sync(this);
                                        switch ( getInterpreter().adaptivePredict(_input,181,_ctx) ) {
                                            case 1: {
                                                {
                                                    setState(1056);
                                                    _errHandler.sync(this);
                                                    _la = _input.LA(1);
                                                    if (_la==SP) {
                                                        {
                                                            setState(1055);
                                                            match(SP);
                                                        }
                                                    }

                                                    setState(1058);
                                                    match(T__17);
                                                }
                                            }
                                            break;
                                            case 2: {
                                                {
                                                    setState(1059);
                                                    match(SP);
                                                    setState(1060);
                                                    match(IN);
                                                }
                                            }
                                            break;
                                            case 3: {
                                                {
                                                    setState(1061);
                                                    match(SP);
                                                    setState(1062);
                                                    match(STARTS);
                                                    setState(1063);
                                                    match(SP);
                                                    setState(1064);
                                                    match(WITH);
                                                }
                                            }
                                            break;
                                            case 4: {
                                                {
                                                    setState(1065);
                                                    match(SP);
                                                    setState(1066);
                                                    match(ENDS);
                                                    setState(1067);
                                                    match(SP);
                                                    setState(1068);
                                                    match(WITH);
                                                }
                                            }
                                            break;
                                            case 5: {
                                                {
                                                    setState(1069);
                                                    match(SP);
                                                    setState(1070);
                                                    match(CONTAINS);
                                                }
                                            }
                                            break;
                                        }
                                        setState(1074);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                        if (_la==SP) {
                                            {
                                                setState(1073);
                                                match(SP);
                                            }
                                        }

                                        setState(1076);
                                        propertyOrLabelsExpression();
                                    }
                                }
                                break;
                                case 4: {
                                    {
                                        setState(1077);
                                        match(SP);
                                        setState(1078);
                                        match(IS);
                                        setState(1079);
                                        match(SP);
                                        setState(1080);
                                        match(NULL);
                                    }
                                }
                                break;
                                case 5: {
                                    {
                                        setState(1081);
                                        match(SP);
                                        setState(1082);
                                        match(IS);
                                        setState(1083);
                                        match(SP);
                                        setState(1084);
                                        match(NOT);
                                        setState(1085);
                                        match(SP);
                                        setState(1086);
                                        match(NULL);
                                    }
                                }
                                break;
                            }
                        }
                    }
                    setState(1091);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,184,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PropertyOrLabelsExpressionContext extends ParserRuleContext {
        public AtomContext atom() {
            return getRuleContext(AtomContext.class,0);
        }

        public List<PropertyLookupContext> propertyLookup() {
            return getRuleContexts(PropertyLookupContext.class);
        }

        public PropertyLookupContext propertyLookup(int i) {
            return getRuleContext(PropertyLookupContext.class,i);
        }

        public List<NodeLabelsContext> nodeLabels() {
            return getRuleContexts(NodeLabelsContext.class);
        }

        public NodeLabelsContext nodeLabels(int i) {
            return getRuleContext(NodeLabelsContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public PropertyOrLabelsExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_propertyOrLabelsExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyOrLabelsExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyOrLabelsExpression(this);
        }
    }

    public final PropertyOrLabelsExpressionContext propertyOrLabelsExpression() throws RecognitionException {
        PropertyOrLabelsExpressionContext _localctx = new PropertyOrLabelsExpressionContext(_ctx, getState());
        enterRule(_localctx, 132, RULE_propertyOrLabelsExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1092);
                atom();
                setState(1102);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,187,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(1094);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(1093);
                                        match(SP);
                                    }
                                }

                                setState(1098);
                                _errHandler.sync(this);
                                switch (_input.LA(1)) {
                                    case T__23: {
                                        setState(1096);
                                        propertyLookup();
                                    }
                                    break;
                                    case T__10: {
                                        setState(1097);
                                        nodeLabels();
                                    }
                                    break;
                                    default:
                                        throw new NoViableAltException(this);
                                }
                            }
                        }
                    }
                    setState(1104);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,187,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AtomContext extends ParserRuleContext {
        public LiteralContext literal() {
            return getRuleContext(LiteralContext.class,0);
        }

        public ParameterContext parameter() {
            return getRuleContext(ParameterContext.class,0);
        }

        public CaseExpressionContext caseExpression() {
            return getRuleContext(CaseExpressionContext.class,0);
        }

        public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ListComprehensionContext listComprehension() {
            return getRuleContext(ListComprehensionContext.class,0);
        }

        public PatternComprehensionContext patternComprehension() {
            return getRuleContext(PatternComprehensionContext.class,0);
        }

        public TerminalNode FILTER() { return getToken(CypherParser.FILTER, 0); }

        public FilterExpressionContext filterExpression() {
            return getRuleContext(FilterExpressionContext.class,0);
        }

        public TerminalNode EXTRACT() { return getToken(CypherParser.EXTRACT, 0); }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }

        public TerminalNode ANY() { return getToken(CypherParser.ANY, 0); }

        public TerminalNode NONE() { return getToken(CypherParser.NONE, 0); }

        public TerminalNode SINGLE() { return getToken(CypherParser.SINGLE, 0); }

        public RelationshipsPatternContext relationshipsPattern() {
            return getRuleContext(RelationshipsPatternContext.class,0);
        }

        public ParenthesizedExpressionContext parenthesizedExpression() {
            return getRuleContext(ParenthesizedExpressionContext.class,0);
        }

        public FunctionInvocationContext functionInvocation() {
            return getRuleContext(FunctionInvocationContext.class,0);
        }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public AtomContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_atom; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAtom(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAtom(this);
        }
    }

    public final AtomContext atom() throws RecognitionException {
        AtomContext _localctx = new AtomContext(_ctx, getState());
        enterRule(_localctx, 134, RULE_atom);
        int _la;
        try {
            setState(1218);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,211,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1105);
                    literal();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1106);
                    parameter();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1107);
                    caseExpression();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    {
                        setState(1108);
                        match(COUNT);
                        setState(1110);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1109);
                                match(SP);
                            }
                        }

                        setState(1112);
                        match(T__6);
                        setState(1114);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1113);
                                match(SP);
                            }
                        }

                        setState(1116);
                        match(T__5);
                        setState(1118);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1117);
                                match(SP);
                            }
                        }

                        setState(1120);
                        match(T__7);
                    }
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(1121);
                    listComprehension();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(1122);
                    patternComprehension();
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    {
                        setState(1123);
                        match(FILTER);
                        setState(1125);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1124);
                                match(SP);
                            }
                        }

                        setState(1127);
                        match(T__6);
                        setState(1129);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1128);
                                match(SP);
                            }
                        }

                        setState(1131);
                        filterExpression();
                        setState(1133);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1132);
                                match(SP);
                            }
                        }

                        setState(1135);
                        match(T__7);
                    }
                }
                break;
                case 8:
                    enterOuterAlt(_localctx, 8);
                {
                    {
                        setState(1137);
                        match(EXTRACT);
                        setState(1139);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1138);
                                match(SP);
                            }
                        }

                        setState(1141);
                        match(T__6);
                        setState(1143);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1142);
                                match(SP);
                            }
                        }

                        setState(1145);
                        filterExpression();
                        setState(1147);
                        _errHandler.sync(this);
                        switch ( getInterpreter().adaptivePredict(_input,196,_ctx) ) {
                            case 1: {
                                setState(1146);
                                match(SP);
                            }
                            break;
                        }
                        setState(1154);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==T__11 || _la==SP) {
                            {
                                setState(1150);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(1149);
                                        match(SP);
                                    }
                                }

                                setState(1152);
                                match(T__11);
                                setState(1153);
                                expression();
                            }
                        }

                        setState(1156);
                        match(T__7);
                    }
                }
                break;
                case 9:
                    enterOuterAlt(_localctx, 9);
                {
                    {
                        setState(1158);
                        match(ALL);
                        setState(1160);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1159);
                                match(SP);
                            }
                        }

                        setState(1162);
                        match(T__6);
                        setState(1164);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1163);
                                match(SP);
                            }
                        }

                        setState(1166);
                        filterExpression();
                        setState(1168);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1167);
                                match(SP);
                            }
                        }

                        setState(1170);
                        match(T__7);
                    }
                }
                break;
                case 10:
                    enterOuterAlt(_localctx, 10);
                {
                    {
                        setState(1172);
                        match(ANY);
                        setState(1174);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1173);
                                match(SP);
                            }
                        }

                        setState(1176);
                        match(T__6);
                        setState(1178);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1177);
                                match(SP);
                            }
                        }

                        setState(1180);
                        filterExpression();
                        setState(1182);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1181);
                                match(SP);
                            }
                        }

                        setState(1184);
                        match(T__7);
                    }
                }
                break;
                case 11:
                    enterOuterAlt(_localctx, 11);
                {
                    {
                        setState(1186);
                        match(NONE);
                        setState(1188);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1187);
                                match(SP);
                            }
                        }

                        setState(1190);
                        match(T__6);
                        setState(1192);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1191);
                                match(SP);
                            }
                        }

                        setState(1194);
                        filterExpression();
                        setState(1196);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1195);
                                match(SP);
                            }
                        }

                        setState(1198);
                        match(T__7);
                    }
                }
                break;
                case 12:
                    enterOuterAlt(_localctx, 12);
                {
                    {
                        setState(1200);
                        match(SINGLE);
                        setState(1202);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1201);
                                match(SP);
                            }
                        }

                        setState(1204);
                        match(T__6);
                        setState(1206);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1205);
                                match(SP);
                            }
                        }

                        setState(1208);
                        filterExpression();
                        setState(1210);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1209);
                                match(SP);
                            }
                        }

                        setState(1212);
                        match(T__7);
                    }
                }
                break;
                case 13:
                    enterOuterAlt(_localctx, 13);
                {
                    setState(1214);
                    relationshipsPattern();
                }
                break;
                case 14:
                    enterOuterAlt(_localctx, 14);
                {
                    setState(1215);
                    parenthesizedExpression();
                }
                break;
                case 15:
                    enterOuterAlt(_localctx, 15);
                {
                    setState(1216);
                    functionInvocation();
                }
                break;
                case 16:
                    enterOuterAlt(_localctx, 16);
                {
                    setState(1217);
                    variable();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class LiteralContext extends ParserRuleContext {
        public NumberLiteralContext numberLiteral() {
            return getRuleContext(NumberLiteralContext.class,0);
        }

        public TerminalNode StringLiteral() { return getToken(CypherParser.StringLiteral, 0); }

        public BooleanLiteralContext booleanLiteral() {
            return getRuleContext(BooleanLiteralContext.class,0);
        }

        public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }

        public MapLiteralContext mapLiteral() {
            return getRuleContext(MapLiteralContext.class,0);
        }

        public ListLiteralContext listLiteral() {
            return getRuleContext(ListLiteralContext.class,0);
        }

        public LiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_literal; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLiteral(this);
        }
    }

    public final LiteralContext literal() throws RecognitionException {
        LiteralContext _localctx = new LiteralContext(_ctx, getState());
        enterRule(_localctx, 136, RULE_literal);
        try {
            setState(1226);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case HexInteger:
                case DecimalInteger:
                case OctalInteger:
                case ExponentDecimalReal:
                case RegularDecimalReal:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1220);
                    numberLiteral();
                }
                break;
                case StringLiteral:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1221);
                    match(StringLiteral);
                }
                break;
                case TRUE:
                case FALSE:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1222);
                    booleanLiteral();
                }
                break;
                case NULL:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1223);
                    match(NULL);
                }
                break;
                case T__24:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(1224);
                    mapLiteral();
                }
                break;
                case T__8:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(1225);
                    listLiteral();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class BooleanLiteralContext extends ParserRuleContext {
        public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }

        public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }

        public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_booleanLiteral; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterBooleanLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitBooleanLiteral(this);
        }
    }

    public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
        BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
        enterRule(_localctx, 138, RULE_booleanLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1228);
                _la = _input.LA(1);
                if ( !(_la==TRUE || _la==FALSE) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ListLiteralContext extends ParserRuleContext {
        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public ListLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_listLiteral; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterListLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitListLiteral(this);
        }
    }

    public final ListLiteralContext listLiteral() throws RecognitionException {
        ListLiteralContext _localctx = new ListLiteralContext(_ctx, getState());
        enterRule(_localctx, 140, RULE_listLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1230);
                match(T__8);
                setState(1232);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1231);
                        match(SP);
                    }
                }

                setState(1251);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__8) | (1L << T__13) | (1L << T__24) | (1L << T__26) | (1L << ALL))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (NOT - 77)) | (1L << (NULL - 77)) | (1L << (COUNT - 77)) | (1L << (FILTER - 77)) | (1L << (EXTRACT - 77)) | (1L << (ANY - 77)) | (1L << (NONE - 77)) | (1L << (SINGLE - 77)) | (1L << (TRUE - 77)) | (1L << (FALSE - 77)) | (1L << (EXISTS - 77)) | (1L << (CASE - 77)) | (1L << (StringLiteral - 77)) | (1L << (HexInteger - 77)) | (1L << (DecimalInteger - 77)) | (1L << (OctalInteger - 77)) | (1L << (HexLetter - 77)) | (1L << (ExponentDecimalReal - 77)) | (1L << (RegularDecimalReal - 77)) | (1L << (UnescapedSymbolicName - 77)) | (1L << (EscapedSymbolicName - 77)))) != 0)) {
                    {
                        setState(1234);
                        expression();
                        setState(1236);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1235);
                                match(SP);
                            }
                        }

                        setState(1248);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while (_la==T__1) {
                            {
                                {
                                    setState(1238);
                                    match(T__1);
                                    setState(1240);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1239);
                                            match(SP);
                                        }
                                    }

                                    setState(1242);
                                    expression();
                                    setState(1244);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1243);
                                            match(SP);
                                        }
                                    }

                                }
                            }
                            setState(1250);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                    }
                }

                setState(1253);
                match(T__9);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PartialComparisonExpressionContext extends ParserRuleContext {
        public AddOrSubtractExpressionContext addOrSubtractExpression() {
            return getRuleContext(AddOrSubtractExpressionContext.class,0);
        }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public PartialComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_partialComparisonExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPartialComparisonExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPartialComparisonExpression(this);
        }
    }

    public final PartialComparisonExpressionContext partialComparisonExpression() throws RecognitionException {
        PartialComparisonExpressionContext _localctx = new PartialComparisonExpressionContext(_ctx, getState());
        enterRule(_localctx, 142, RULE_partialComparisonExpression);
        int _la;
        try {
            setState(1285);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__2:
                    enterOuterAlt(_localctx, 1);
                {
                    {
                        setState(1255);
                        match(T__2);
                        setState(1257);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1256);
                                match(SP);
                            }
                        }

                        setState(1259);
                        addOrSubtractExpression();
                    }
                }
                break;
                case T__18:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(1260);
                        match(T__18);
                        setState(1262);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1261);
                                match(SP);
                            }
                        }

                        setState(1264);
                        addOrSubtractExpression();
                    }
                }
                break;
                case T__19:
                    enterOuterAlt(_localctx, 3);
                {
                    {
                        setState(1265);
                        match(T__19);
                        setState(1267);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1266);
                                match(SP);
                            }
                        }

                        setState(1269);
                        addOrSubtractExpression();
                    }
                }
                break;
                case T__20:
                    enterOuterAlt(_localctx, 4);
                {
                    {
                        setState(1270);
                        match(T__20);
                        setState(1272);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1271);
                                match(SP);
                            }
                        }

                        setState(1274);
                        addOrSubtractExpression();
                    }
                }
                break;
                case T__21:
                    enterOuterAlt(_localctx, 5);
                {
                    {
                        setState(1275);
                        match(T__21);
                        setState(1277);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1276);
                                match(SP);
                            }
                        }

                        setState(1279);
                        addOrSubtractExpression();
                    }
                }
                break;
                case T__22:
                    enterOuterAlt(_localctx, 6);
                {
                    {
                        setState(1280);
                        match(T__22);
                        setState(1282);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1281);
                                match(SP);
                            }
                        }

                        setState(1284);
                        addOrSubtractExpression();
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ParenthesizedExpressionContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ParenthesizedExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_parenthesizedExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterParenthesizedExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitParenthesizedExpression(this);
        }
    }

    public final ParenthesizedExpressionContext parenthesizedExpression() throws RecognitionException {
        ParenthesizedExpressionContext _localctx = new ParenthesizedExpressionContext(_ctx, getState());
        enterRule(_localctx, 144, RULE_parenthesizedExpression);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1287);
                match(T__6);
                setState(1289);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1288);
                        match(SP);
                    }
                }

                setState(1291);
                expression();
                setState(1293);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1292);
                        match(SP);
                    }
                }

                setState(1295);
                match(T__7);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RelationshipsPatternContext extends ParserRuleContext {
        public NodePatternContext nodePattern() {
            return getRuleContext(NodePatternContext.class,0);
        }

        public List<PatternElementChainContext> patternElementChain() {
            return getRuleContexts(PatternElementChainContext.class);
        }

        public PatternElementChainContext patternElementChain(int i) {
            return getRuleContext(PatternElementChainContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public RelationshipsPatternContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_relationshipsPattern; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipsPattern(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipsPattern(this);
        }
    }

    public final RelationshipsPatternContext relationshipsPattern() throws RecognitionException {
        RelationshipsPatternContext _localctx = new RelationshipsPatternContext(_ctx, getState());
        enterRule(_localctx, 146, RULE_relationshipsPattern);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1297);
                nodePattern();
                setState(1302);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(1299);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(1298);
                                        match(SP);
                                    }
                                }

                                setState(1301);
                                patternElementChain();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(1304);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,229,_ctx);
                } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FilterExpressionContext extends ParserRuleContext {
        public IdInCollContext idInColl() {
            return getRuleContext(IdInCollContext.class,0);
        }

        public WhereContext where() {
            return getRuleContext(WhereContext.class,0);
        }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public FilterExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_filterExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFilterExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFilterExpression(this);
        }
    }

    public final FilterExpressionContext filterExpression() throws RecognitionException {
        FilterExpressionContext _localctx = new FilterExpressionContext(_ctx, getState());
        enterRule(_localctx, 148, RULE_filterExpression);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1306);
                idInColl();
                setState(1311);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,231,_ctx) ) {
                    case 1: {
                        setState(1308);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1307);
                                match(SP);
                            }
                        }

                        setState(1310);
                        where();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class IdInCollContext extends ParserRuleContext {
        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode IN() { return getToken(CypherParser.IN, 0); }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public IdInCollContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_idInColl; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIdInColl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIdInColl(this);
        }
    }

    public final IdInCollContext idInColl() throws RecognitionException {
        IdInCollContext _localctx = new IdInCollContext(_ctx, getState());
        enterRule(_localctx, 150, RULE_idInColl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1313);
                variable();
                setState(1314);
                match(SP);
                setState(1315);
                match(IN);
                setState(1316);
                match(SP);
                setState(1317);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FunctionInvocationContext extends ParserRuleContext {
        public FunctionNameContext functionName() {
            return getRuleContext(FunctionNameContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public FunctionInvocationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_functionInvocation; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFunctionInvocation(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFunctionInvocation(this);
        }
    }

    public final FunctionInvocationContext functionInvocation() throws RecognitionException {
        FunctionInvocationContext _localctx = new FunctionInvocationContext(_ctx, getState());
        enterRule(_localctx, 152, RULE_functionInvocation);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1319);
                functionName();
                setState(1321);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1320);
                        match(SP);
                    }
                }

                setState(1323);
                match(T__6);
                setState(1325);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1324);
                        match(SP);
                    }
                }

                setState(1331);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==DISTINCT) {
                    {
                        setState(1327);
                        match(DISTINCT);
                        setState(1329);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1328);
                                match(SP);
                            }
                        }

                    }
                }

                setState(1350);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__8) | (1L << T__13) | (1L << T__24) | (1L << T__26) | (1L << ALL))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (NOT - 77)) | (1L << (NULL - 77)) | (1L << (COUNT - 77)) | (1L << (FILTER - 77)) | (1L << (EXTRACT - 77)) | (1L << (ANY - 77)) | (1L << (NONE - 77)) | (1L << (SINGLE - 77)) | (1L << (TRUE - 77)) | (1L << (FALSE - 77)) | (1L << (EXISTS - 77)) | (1L << (CASE - 77)) | (1L << (StringLiteral - 77)) | (1L << (HexInteger - 77)) | (1L << (DecimalInteger - 77)) | (1L << (OctalInteger - 77)) | (1L << (HexLetter - 77)) | (1L << (ExponentDecimalReal - 77)) | (1L << (RegularDecimalReal - 77)) | (1L << (UnescapedSymbolicName - 77)) | (1L << (EscapedSymbolicName - 77)))) != 0)) {
                    {
                        setState(1333);
                        expression();
                        setState(1335);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1334);
                                match(SP);
                            }
                        }

                        setState(1347);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while (_la==T__1) {
                            {
                                {
                                    setState(1337);
                                    match(T__1);
                                    setState(1339);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1338);
                                            match(SP);
                                        }
                                    }

                                    setState(1341);
                                    expression();
                                    setState(1343);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1342);
                                            match(SP);
                                        }
                                    }

                                }
                            }
                            setState(1349);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                    }
                }

                setState(1352);
                match(T__7);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FunctionNameContext extends ParserRuleContext {
        public SymbolicNameContext symbolicName() {
            return getRuleContext(SymbolicNameContext.class,0);
        }

        public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }

        public FunctionNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_functionName; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFunctionName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFunctionName(this);
        }
    }

    public final FunctionNameContext functionName() throws RecognitionException {
        FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
        enterRule(_localctx, 154, RULE_functionName);
        try {
            setState(1356);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case ALL:
                case COUNT:
                case FILTER:
                case EXTRACT:
                case ANY:
                case NONE:
                case SINGLE:
                case HexLetter:
                case UnescapedSymbolicName:
                case EscapedSymbolicName:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1354);
                    symbolicName();
                }
                break;
                case EXISTS:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1355);
                    match(EXISTS);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExplicitProcedureInvocationContext extends ParserRuleContext {
        public ProcedureNameContext procedureName() {
            return getRuleContext(ProcedureNameContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public ExplicitProcedureInvocationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_explicitProcedureInvocation; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExplicitProcedureInvocation(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExplicitProcedureInvocation(this);
        }
    }

    public final ExplicitProcedureInvocationContext explicitProcedureInvocation() throws RecognitionException {
        ExplicitProcedureInvocationContext _localctx = new ExplicitProcedureInvocationContext(_ctx, getState());
        enterRule(_localctx, 156, RULE_explicitProcedureInvocation);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1358);
                procedureName();
                setState(1360);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1359);
                        match(SP);
                    }
                }

                setState(1362);
                match(T__6);
                setState(1364);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1363);
                        match(SP);
                    }
                }

                setState(1383);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__8) | (1L << T__13) | (1L << T__24) | (1L << T__26) | (1L << ALL))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (NOT - 77)) | (1L << (NULL - 77)) | (1L << (COUNT - 77)) | (1L << (FILTER - 77)) | (1L << (EXTRACT - 77)) | (1L << (ANY - 77)) | (1L << (NONE - 77)) | (1L << (SINGLE - 77)) | (1L << (TRUE - 77)) | (1L << (FALSE - 77)) | (1L << (EXISTS - 77)) | (1L << (CASE - 77)) | (1L << (StringLiteral - 77)) | (1L << (HexInteger - 77)) | (1L << (DecimalInteger - 77)) | (1L << (OctalInteger - 77)) | (1L << (HexLetter - 77)) | (1L << (ExponentDecimalReal - 77)) | (1L << (RegularDecimalReal - 77)) | (1L << (UnescapedSymbolicName - 77)) | (1L << (EscapedSymbolicName - 77)))) != 0)) {
                    {
                        setState(1366);
                        expression();
                        setState(1368);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1367);
                                match(SP);
                            }
                        }

                        setState(1380);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while (_la==T__1) {
                            {
                                {
                                    setState(1370);
                                    match(T__1);
                                    setState(1372);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1371);
                                            match(SP);
                                        }
                                    }

                                    setState(1374);
                                    expression();
                                    setState(1376);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1375);
                                            match(SP);
                                        }
                                    }

                                }
                            }
                            setState(1382);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                    }
                }

                setState(1385);
                match(T__7);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ImplicitProcedureInvocationContext extends ParserRuleContext {
        public ProcedureNameContext procedureName() {
            return getRuleContext(ProcedureNameContext.class,0);
        }

        public ImplicitProcedureInvocationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_implicitProcedureInvocation; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterImplicitProcedureInvocation(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitImplicitProcedureInvocation(this);
        }
    }

    public final ImplicitProcedureInvocationContext implicitProcedureInvocation() throws RecognitionException {
        ImplicitProcedureInvocationContext _localctx = new ImplicitProcedureInvocationContext(_ctx, getState());
        enterRule(_localctx, 158, RULE_implicitProcedureInvocation);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1387);
                procedureName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ProcedureResultFieldContext extends ParserRuleContext {
        public SymbolicNameContext symbolicName() {
            return getRuleContext(SymbolicNameContext.class,0);
        }

        public ProcedureResultFieldContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_procedureResultField; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProcedureResultField(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProcedureResultField(this);
        }
    }

    public final ProcedureResultFieldContext procedureResultField() throws RecognitionException {
        ProcedureResultFieldContext _localctx = new ProcedureResultFieldContext(_ctx, getState());
        enterRule(_localctx, 160, RULE_procedureResultField);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1389);
                symbolicName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ProcedureNameContext extends ParserRuleContext {
        public NamespaceContext namespace() {
            return getRuleContext(NamespaceContext.class,0);
        }

        public SymbolicNameContext symbolicName() {
            return getRuleContext(SymbolicNameContext.class,0);
        }

        public ProcedureNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_procedureName; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProcedureName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProcedureName(this);
        }
    }

    public final ProcedureNameContext procedureName() throws RecognitionException {
        ProcedureNameContext _localctx = new ProcedureNameContext(_ctx, getState());
        enterRule(_localctx, 162, RULE_procedureName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1391);
                namespace();
                setState(1392);
                symbolicName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NamespaceContext extends ParserRuleContext {
        public List<SymbolicNameContext> symbolicName() {
            return getRuleContexts(SymbolicNameContext.class);
        }

        public SymbolicNameContext symbolicName(int i) {
            return getRuleContext(SymbolicNameContext.class,i);
        }

        public NamespaceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_namespace; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNamespace(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNamespace(this);
        }
    }

    public final NamespaceContext namespace() throws RecognitionException {
        NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
        enterRule(_localctx, 164, RULE_namespace);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1399);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,249,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        {
                            {
                                setState(1394);
                                symbolicName();
                                setState(1395);
                                match(T__23);
                            }
                        }
                    }
                    setState(1401);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,249,_ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ListComprehensionContext extends ParserRuleContext {
        public FilterExpressionContext filterExpression() {
            return getRuleContext(FilterExpressionContext.class,0);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class,0);
        }

        public ListComprehensionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_listComprehension; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterListComprehension(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitListComprehension(this);
        }
    }

    public final ListComprehensionContext listComprehension() throws RecognitionException {
        ListComprehensionContext _localctx = new ListComprehensionContext(_ctx, getState());
        enterRule(_localctx, 166, RULE_listComprehension);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1402);
                match(T__8);
                setState(1404);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1403);
                        match(SP);
                    }
                }

                setState(1406);
                filterExpression();
                setState(1415);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,253,_ctx) ) {
                    case 1: {
                        setState(1408);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1407);
                                match(SP);
                            }
                        }

                        setState(1410);
                        match(T__11);
                        setState(1412);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1411);
                                match(SP);
                            }
                        }

                        setState(1414);
                        expression();
                    }
                    break;
                }
                setState(1418);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1417);
                        match(SP);
                    }
                }

                setState(1420);
                match(T__9);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PatternComprehensionContext extends ParserRuleContext {
        public RelationshipsPatternContext relationshipsPattern() {
            return getRuleContext(RelationshipsPatternContext.class,0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public VariableContext variable() {
            return getRuleContext(VariableContext.class,0);
        }

        public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }

        public PatternComprehensionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_patternComprehension; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternComprehension(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternComprehension(this);
        }
    }

    public final PatternComprehensionContext patternComprehension() throws RecognitionException {
        PatternComprehensionContext _localctx = new PatternComprehensionContext(_ctx, getState());
        enterRule(_localctx, 168, RULE_patternComprehension);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1422);
                match(T__8);
                setState(1424);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1423);
                        match(SP);
                    }
                }

                setState(1434);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==ALL || ((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & ((1L << (COUNT - 84)) | (1L << (FILTER - 84)) | (1L << (EXTRACT - 84)) | (1L << (ANY - 84)) | (1L << (NONE - 84)) | (1L << (SINGLE - 84)) | (1L << (HexLetter - 84)) | (1L << (UnescapedSymbolicName - 84)) | (1L << (EscapedSymbolicName - 84)))) != 0)) {
                    {
                        setState(1426);
                        variable();
                        setState(1428);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1427);
                                match(SP);
                            }
                        }

                        setState(1430);
                        match(T__2);
                        setState(1432);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1431);
                                match(SP);
                            }
                        }

                    }
                }

                setState(1436);
                relationshipsPattern();
                setState(1438);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1437);
                        match(SP);
                    }
                }

                setState(1448);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==WHERE) {
                    {
                        setState(1440);
                        match(WHERE);
                        setState(1442);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1441);
                                match(SP);
                            }
                        }

                        setState(1444);
                        expression();
                        setState(1446);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1445);
                                match(SP);
                            }
                        }

                    }
                }

                setState(1450);
                match(T__11);
                setState(1452);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1451);
                        match(SP);
                    }
                }

                setState(1454);
                expression();
                setState(1456);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1455);
                        match(SP);
                    }
                }

                setState(1458);
                match(T__9);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PropertyLookupContext extends ParserRuleContext {
        public PropertyKeyNameContext propertyKeyName() {
            return getRuleContext(PropertyKeyNameContext.class,0);
        }

        public TerminalNode SP() { return getToken(CypherParser.SP, 0); }

        public PropertyLookupContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_propertyLookup; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyLookup(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyLookup(this);
        }
    }

    public final PropertyLookupContext propertyLookup() throws RecognitionException {
        PropertyLookupContext _localctx = new PropertyLookupContext(_ctx, getState());
        enterRule(_localctx, 170, RULE_propertyLookup);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1460);
                match(T__23);
                setState(1462);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1461);
                        match(SP);
                    }
                }

                {
                    setState(1464);
                    propertyKeyName();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CaseExpressionContext extends ParserRuleContext {
        public TerminalNode END() { return getToken(CypherParser.END, 0); }

        public TerminalNode ELSE() { return getToken(CypherParser.ELSE, 0); }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public TerminalNode CASE() { return getToken(CypherParser.CASE, 0); }

        public List<CaseAlternativesContext> caseAlternatives() {
            return getRuleContexts(CaseAlternativesContext.class);
        }

        public CaseAlternativesContext caseAlternatives(int i) {
            return getRuleContext(CaseAlternativesContext.class,i);
        }

        public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_caseExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCaseExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCaseExpression(this);
        }
    }

    public final CaseExpressionContext caseExpression() throws RecognitionException {
        CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
        enterRule(_localctx, 172, RULE_caseExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1488);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,271,_ctx) ) {
                    case 1: {
                        {
                            setState(1466);
                            match(CASE);
                            setState(1471);
                            _errHandler.sync(this);
                            _alt = 1;
                            do {
                                switch (_alt) {
                                    case 1: {
                                        {
                                            setState(1468);
                                            _errHandler.sync(this);
                                            _la = _input.LA(1);
                                            if (_la==SP) {
                                                {
                                                    setState(1467);
                                                    match(SP);
                                                }
                                            }

                                            setState(1470);
                                            caseAlternatives();
                                        }
                                    }
                                    break;
                                    default:
                                        throw new NoViableAltException(this);
                                }
                                setState(1473);
                                _errHandler.sync(this);
                                _alt = getInterpreter().adaptivePredict(_input,267,_ctx);
                            } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
                        }
                    }
                    break;
                    case 2: {
                        {
                            setState(1475);
                            match(CASE);
                            setState(1477);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==SP) {
                                {
                                    setState(1476);
                                    match(SP);
                                }
                            }

                            setState(1479);
                            expression();
                            setState(1484);
                            _errHandler.sync(this);
                            _alt = 1;
                            do {
                                switch (_alt) {
                                    case 1: {
                                        {
                                            setState(1481);
                                            _errHandler.sync(this);
                                            _la = _input.LA(1);
                                            if (_la==SP) {
                                                {
                                                    setState(1480);
                                                    match(SP);
                                                }
                                            }

                                            setState(1483);
                                            caseAlternatives();
                                        }
                                    }
                                    break;
                                    default:
                                        throw new NoViableAltException(this);
                                }
                                setState(1486);
                                _errHandler.sync(this);
                                _alt = getInterpreter().adaptivePredict(_input,270,_ctx);
                            } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
                        }
                    }
                    break;
                }
                setState(1498);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,274,_ctx) ) {
                    case 1: {
                        setState(1491);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1490);
                                match(SP);
                            }
                        }

                        setState(1493);
                        match(ELSE);
                        setState(1495);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1494);
                                match(SP);
                            }
                        }

                        setState(1497);
                        expression();
                    }
                    break;
                }
                setState(1501);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1500);
                        match(SP);
                    }
                }

                setState(1503);
                match(END);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CaseAlternativesContext extends ParserRuleContext {
        public TerminalNode WHEN() { return getToken(CypherParser.WHEN, 0); }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public TerminalNode THEN() { return getToken(CypherParser.THEN, 0); }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public CaseAlternativesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_caseAlternatives; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCaseAlternatives(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCaseAlternatives(this);
        }
    }

    public final CaseAlternativesContext caseAlternatives() throws RecognitionException {
        CaseAlternativesContext _localctx = new CaseAlternativesContext(_ctx, getState());
        enterRule(_localctx, 174, RULE_caseAlternatives);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1505);
                match(WHEN);
                setState(1507);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1506);
                        match(SP);
                    }
                }

                setState(1509);
                expression();
                setState(1511);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1510);
                        match(SP);
                    }
                }

                setState(1513);
                match(THEN);
                setState(1515);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1514);
                        match(SP);
                    }
                }

                setState(1517);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VariableContext extends ParserRuleContext {
        public SymbolicNameContext symbolicName() {
            return getRuleContext(SymbolicNameContext.class,0);
        }

        public VariableContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_variable; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterVariable(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitVariable(this);
        }
    }

    public final VariableContext variable() throws RecognitionException {
        VariableContext _localctx = new VariableContext(_ctx, getState());
        enterRule(_localctx, 176, RULE_variable);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1519);
                symbolicName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NumberLiteralContext extends ParserRuleContext {
        public DoubleLiteralContext doubleLiteral() {
            return getRuleContext(DoubleLiteralContext.class,0);
        }

        public IntegerLiteralContext integerLiteral() {
            return getRuleContext(IntegerLiteralContext.class,0);
        }

        public NumberLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_numberLiteral; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNumberLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNumberLiteral(this);
        }
    }

    public final NumberLiteralContext numberLiteral() throws RecognitionException {
        NumberLiteralContext _localctx = new NumberLiteralContext(_ctx, getState());
        enterRule(_localctx, 178, RULE_numberLiteral);
        try {
            setState(1523);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case ExponentDecimalReal:
                case RegularDecimalReal:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1521);
                    doubleLiteral();
                }
                break;
                case HexInteger:
                case DecimalInteger:
                case OctalInteger:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1522);
                    integerLiteral();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MapLiteralContext extends ParserRuleContext {
        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public List<PropertyKeyNameContext> propertyKeyName() {
            return getRuleContexts(PropertyKeyNameContext.class);
        }

        public PropertyKeyNameContext propertyKeyName(int i) {
            return getRuleContext(PropertyKeyNameContext.class,i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class,i);
        }

        public MapLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_mapLiteral; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMapLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMapLiteral(this);
        }
    }

    public final MapLiteralContext mapLiteral() throws RecognitionException {
        MapLiteralContext _localctx = new MapLiteralContext(_ctx, getState());
        enterRule(_localctx, 180, RULE_mapLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1525);
                match(T__24);
                setState(1527);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==SP) {
                    {
                        setState(1526);
                        match(SP);
                    }
                }

                setState(1562);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << ALL) | (1L << OPTIONAL) | (1L << MATCH) | (1L << UNWIND) | (1L << AS) | (1L << MERGE) | (1L << ON) | (1L << CREATE) | (1L << SET) | (1L << DETACH) | (1L << DELETE) | (1L << REMOVE) | (1L << WITH) | (1L << DISTINCT))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (EXISTS - 64)) | (1L << (CASE - 64)) | (1L << (ELSE - 64)) | (1L << (END - 64)) | (1L << (WHEN - 64)) | (1L << (THEN - 64)) | (1L << (HexLetter - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (DO - 64)) | (1L << (FOR - 64)) | (1L << (REQUIRE - 64)) | (1L << (UNIQUE - 64)) | (1L << (MANDATORY - 64)) | (1L << (SCALAR - 64)) | (1L << (OF - 64)) | (1L << (ADD - 64)) | (1L << (DROP - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)))) != 0)) {
                    {
                        setState(1529);
                        propertyKeyName();
                        setState(1531);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1530);
                                match(SP);
                            }
                        }

                        setState(1533);
                        match(T__10);
                        setState(1535);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1534);
                                match(SP);
                            }
                        }

                        setState(1537);
                        expression();
                        setState(1539);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la==SP) {
                            {
                                setState(1538);
                                match(SP);
                            }
                        }

                        setState(1559);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while (_la==T__1) {
                            {
                                {
                                    setState(1541);
                                    match(T__1);
                                    setState(1543);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1542);
                                            match(SP);
                                        }
                                    }

                                    setState(1545);
                                    propertyKeyName();
                                    setState(1547);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1546);
                                            match(SP);
                                        }
                                    }

                                    setState(1549);
                                    match(T__10);
                                    setState(1551);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1550);
                                            match(SP);
                                        }
                                    }

                                    setState(1553);
                                    expression();
                                    setState(1555);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la==SP) {
                                        {
                                            setState(1554);
                                            match(SP);
                                        }
                                    }

                                }
                            }
                            setState(1561);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                    }
                }

                setState(1564);
                match(T__25);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ParameterContext extends ParserRuleContext {
        public SymbolicNameContext symbolicName() {
            return getRuleContext(SymbolicNameContext.class,0);
        }

        public TerminalNode DecimalInteger() { return getToken(CypherParser.DecimalInteger, 0); }

        public ParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_parameter; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterParameter(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitParameter(this);
        }
    }

    public final ParameterContext parameter() throws RecognitionException {
        ParameterContext _localctx = new ParameterContext(_ctx, getState());
        enterRule(_localctx, 182, RULE_parameter);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1566);
                match(T__26);
                setState(1569);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case ALL:
                    case COUNT:
                    case FILTER:
                    case EXTRACT:
                    case ANY:
                    case NONE:
                    case SINGLE:
                    case HexLetter:
                    case UnescapedSymbolicName:
                    case EscapedSymbolicName: {
                        setState(1567);
                        symbolicName();
                    }
                    break;
                    case DecimalInteger: {
                        setState(1568);
                        match(DecimalInteger);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PropertyExpressionContext extends ParserRuleContext {
        public AtomContext atom() {
            return getRuleContext(AtomContext.class,0);
        }

        public List<PropertyLookupContext> propertyLookup() {
            return getRuleContexts(PropertyLookupContext.class);
        }

        public PropertyLookupContext propertyLookup(int i) {
            return getRuleContext(PropertyLookupContext.class,i);
        }

        public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }

        public TerminalNode SP(int i) {
            return getToken(CypherParser.SP, i);
        }

        public PropertyExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_propertyExpression; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyExpression(this);
        }
    }

    public final PropertyExpressionContext propertyExpression() throws RecognitionException {
        PropertyExpressionContext _localctx = new PropertyExpressionContext(_ctx, getState());
        enterRule(_localctx, 184, RULE_propertyExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1571);
                atom();
                setState(1576);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(1573);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if (_la==SP) {
                                    {
                                        setState(1572);
                                        match(SP);
                                    }
                                }

                                setState(1575);
                                propertyLookup();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(1578);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,292,_ctx);
                } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PropertyKeyNameContext extends ParserRuleContext {
        public SchemaNameContext schemaName() {
            return getRuleContext(SchemaNameContext.class,0);
        }

        public PropertyKeyNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_propertyKeyName; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyKeyName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyKeyName(this);
        }
    }

    public final PropertyKeyNameContext propertyKeyName() throws RecognitionException {
        PropertyKeyNameContext _localctx = new PropertyKeyNameContext(_ctx, getState());
        enterRule(_localctx, 186, RULE_propertyKeyName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1580);
                schemaName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class IntegerLiteralContext extends ParserRuleContext {
        public TerminalNode HexInteger() { return getToken(CypherParser.HexInteger, 0); }

        public TerminalNode OctalInteger() { return getToken(CypherParser.OctalInteger, 0); }

        public TerminalNode DecimalInteger() { return getToken(CypherParser.DecimalInteger, 0); }

        public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_integerLiteral; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIntegerLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIntegerLiteral(this);
        }
    }

    public final IntegerLiteralContext integerLiteral() throws RecognitionException {
        IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
        enterRule(_localctx, 188, RULE_integerLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1582);
                _la = _input.LA(1);
                if ( !(((((_la - 100)) & ~0x3f) == 0 && ((1L << (_la - 100)) & ((1L << (HexInteger - 100)) | (1L << (DecimalInteger - 100)) | (1L << (OctalInteger - 100)))) != 0)) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class DoubleLiteralContext extends ParserRuleContext {
        public TerminalNode ExponentDecimalReal() { return getToken(CypherParser.ExponentDecimalReal, 0); }

        public TerminalNode RegularDecimalReal() { return getToken(CypherParser.RegularDecimalReal, 0); }

        public DoubleLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_doubleLiteral; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDoubleLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDoubleLiteral(this);
        }
    }

    public final DoubleLiteralContext doubleLiteral() throws RecognitionException {
        DoubleLiteralContext _localctx = new DoubleLiteralContext(_ctx, getState());
        enterRule(_localctx, 190, RULE_doubleLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1584);
                _la = _input.LA(1);
                if ( !(_la==ExponentDecimalReal || _la==RegularDecimalReal) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SchemaNameContext extends ParserRuleContext {
        public SymbolicNameContext symbolicName() {
            return getRuleContext(SymbolicNameContext.class,0);
        }

        public ReservedWordContext reservedWord() {
            return getRuleContext(ReservedWordContext.class,0);
        }

        public SchemaNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_schemaName; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSchemaName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSchemaName(this);
        }
    }

    public final SchemaNameContext schemaName() throws RecognitionException {
        SchemaNameContext _localctx = new SchemaNameContext(_ctx, getState());
        enterRule(_localctx, 192, RULE_schemaName);
        try {
            setState(1588);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,293,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1586);
                    symbolicName();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1587);
                    reservedWord();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ReservedWordContext extends ParserRuleContext {
        public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }

        public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }

        public TerminalNode ASCENDING() { return getToken(CypherParser.ASCENDING, 0); }

        public TerminalNode BY() { return getToken(CypherParser.BY, 0); }

        public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }

        public TerminalNode DELETE() { return getToken(CypherParser.DELETE, 0); }

        public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }

        public TerminalNode DESCENDING() { return getToken(CypherParser.DESCENDING, 0); }

        public TerminalNode DETACH() { return getToken(CypherParser.DETACH, 0); }

        public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }

        public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }

        public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }

        public TerminalNode MERGE() { return getToken(CypherParser.MERGE, 0); }

        public TerminalNode ON() { return getToken(CypherParser.ON, 0); }

        public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }

        public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }

        public TerminalNode REMOVE() { return getToken(CypherParser.REMOVE, 0); }

        public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }

        public TerminalNode SET() { return getToken(CypherParser.SET, 0); }

        public TerminalNode L_SKIP() { return getToken(CypherParser.L_SKIP, 0); }

        public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }

        public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }

        public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }

        public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }

        public TerminalNode AND() { return getToken(CypherParser.AND, 0); }

        public TerminalNode AS() { return getToken(CypherParser.AS, 0); }

        public TerminalNode CONTAINS() { return getToken(CypherParser.CONTAINS, 0); }

        public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }

        public TerminalNode ENDS() { return getToken(CypherParser.ENDS, 0); }

        public TerminalNode IN() { return getToken(CypherParser.IN, 0); }

        public TerminalNode IS() { return getToken(CypherParser.IS, 0); }

        public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }

        public TerminalNode OR() { return getToken(CypherParser.OR, 0); }

        public TerminalNode STARTS() { return getToken(CypherParser.STARTS, 0); }

        public TerminalNode XOR() { return getToken(CypherParser.XOR, 0); }

        public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }

        public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }

        public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }

        public TerminalNode CONSTRAINT() { return getToken(CypherParser.CONSTRAINT, 0); }

        public TerminalNode DO() { return getToken(CypherParser.DO, 0); }

        public TerminalNode FOR() { return getToken(CypherParser.FOR, 0); }

        public TerminalNode REQUIRE() { return getToken(CypherParser.REQUIRE, 0); }

        public TerminalNode UNIQUE() { return getToken(CypherParser.UNIQUE, 0); }

        public TerminalNode CASE() { return getToken(CypherParser.CASE, 0); }

        public TerminalNode WHEN() { return getToken(CypherParser.WHEN, 0); }

        public TerminalNode THEN() { return getToken(CypherParser.THEN, 0); }

        public TerminalNode ELSE() { return getToken(CypherParser.ELSE, 0); }

        public TerminalNode END() { return getToken(CypherParser.END, 0); }

        public TerminalNode MANDATORY() { return getToken(CypherParser.MANDATORY, 0); }

        public TerminalNode SCALAR() { return getToken(CypherParser.SCALAR, 0); }

        public TerminalNode OF() { return getToken(CypherParser.OF, 0); }

        public TerminalNode ADD() { return getToken(CypherParser.ADD, 0); }

        public TerminalNode DROP() { return getToken(CypherParser.DROP, 0); }

        public ReservedWordContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_reservedWord; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReservedWord(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReservedWord(this);
        }
    }

    public final ReservedWordContext reservedWord() throws RecognitionException {
        ReservedWordContext _localctx = new ReservedWordContext(_ctx, getState());
        enterRule(_localctx, 194, RULE_reservedWord);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1590);
                _la = _input.LA(1);
                if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << ALL) | (1L << OPTIONAL) | (1L << MATCH) | (1L << UNWIND) | (1L << AS) | (1L << MERGE) | (1L << ON) | (1L << CREATE) | (1L << SET) | (1L << DETACH) | (1L << DELETE) | (1L << REMOVE) | (1L << WITH) | (1L << DISTINCT))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (EXISTS - 64)) | (1L << (CASE - 64)) | (1L << (ELSE - 64)) | (1L << (END - 64)) | (1L << (WHEN - 64)) | (1L << (THEN - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (DO - 64)) | (1L << (FOR - 64)) | (1L << (REQUIRE - 64)) | (1L << (UNIQUE - 64)) | (1L << (MANDATORY - 64)) | (1L << (SCALAR - 64)) | (1L << (OF - 64)) | (1L << (ADD - 64)) | (1L << (DROP - 64)))) != 0)) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SymbolicNameContext extends ParserRuleContext {
        public TerminalNode UnescapedSymbolicName() { return getToken(CypherParser.UnescapedSymbolicName, 0); }

        public TerminalNode EscapedSymbolicName() { return getToken(CypherParser.EscapedSymbolicName, 0); }

        public TerminalNode HexLetter() { return getToken(CypherParser.HexLetter, 0); }

        public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }

        public TerminalNode FILTER() { return getToken(CypherParser.FILTER, 0); }

        public TerminalNode EXTRACT() { return getToken(CypherParser.EXTRACT, 0); }

        public TerminalNode ANY() { return getToken(CypherParser.ANY, 0); }

        public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }

        public TerminalNode NONE() { return getToken(CypherParser.NONE, 0); }

        public TerminalNode SINGLE() { return getToken(CypherParser.SINGLE, 0); }

        public SymbolicNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_symbolicName; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSymbolicName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSymbolicName(this);
        }
    }

    public final SymbolicNameContext symbolicName() throws RecognitionException {
        SymbolicNameContext _localctx = new SymbolicNameContext(_ctx, getState());
        enterRule(_localctx, 196, RULE_symbolicName);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1592);
                _la = _input.LA(1);
                if ( !(_la==ALL || ((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & ((1L << (COUNT - 84)) | (1L << (FILTER - 84)) | (1L << (EXTRACT - 84)) | (1L << (ANY - 84)) | (1L << (NONE - 84)) | (1L << (SINGLE - 84)) | (1L << (HexLetter - 84)) | (1L << (UnescapedSymbolicName - 84)) | (1L << (EscapedSymbolicName - 84)))) != 0)) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class LeftArrowHeadContext extends ParserRuleContext {
        public LeftArrowHeadContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_leftArrowHead; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLeftArrowHead(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLeftArrowHead(this);
        }
    }

    public final LeftArrowHeadContext leftArrowHead() throws RecognitionException {
        LeftArrowHeadContext _localctx = new LeftArrowHeadContext(_ctx, getState());
        enterRule(_localctx, 198, RULE_leftArrowHead);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1594);
                _la = _input.LA(1);
                if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30))) != 0)) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RightArrowHeadContext extends ParserRuleContext {
        public RightArrowHeadContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_rightArrowHead; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRightArrowHead(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRightArrowHead(this);
        }
    }

    public final RightArrowHeadContext rightArrowHead() throws RecognitionException {
        RightArrowHeadContext _localctx = new RightArrowHeadContext(_ctx, getState());
        enterRule(_localctx, 200, RULE_rightArrowHead);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1596);
                _la = _input.LA(1);
                if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class DashContext extends ParserRuleContext {
        public DashContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override public int getRuleIndex() { return RULE_dash; }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDash(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDash(this);
        }
    }

    public final DashContext dash() throws RecognitionException {
        DashContext _localctx = new DashContext(_ctx, getState());
        enterRule(_localctx, 202, RULE_dash);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1598);
                _la = _input.LA(1);
                if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45))) != 0)) ) {
                    _errHandler.recoverInline(this);
                } else {
                    if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u0082\u0643\4\2\t"+
                    "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
                    "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
                    ",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
                    "\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
                    "\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
                    "\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
                    "\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
                    "`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\3\2\5\2\u00d0\n\2\3\2\3"+
                    "\2\5\2\u00d4\n\2\3\2\5\2\u00d7\n\2\3\2\5\2\u00da\n\2\3\2\3\2\3\3\3\3\3"+
                    "\4\3\4\5\4\u00e2\n\4\3\5\3\5\5\5\u00e6\n\5\3\5\7\5\u00e9\n\5\f\5\16\5"+
                    "\u00ec\13\5\3\6\3\6\3\6\3\6\5\6\u00f2\n\6\3\6\3\6\3\6\5\6\u00f7\n\6\3"+
                    "\6\5\6\u00fa\n\6\3\7\3\7\5\7\u00fe\n\7\3\b\3\b\3\b\5\b\u0103\n\b\3\t\3"+
                    "\t\3\t\3\n\3\n\5\n\u010a\n\n\3\n\7\n\u010d\n\n\f\n\16\n\u0110\13\n\3\n"+
                    "\5\n\u0113\n\n\3\n\6\n\u0116\n\n\r\n\16\n\u0117\3\n\5\n\u011b\n\n\3\n"+
                    "\5\n\u011e\n\n\3\13\3\13\5\13\u0122\n\13\3\13\7\13\u0125\n\13\f\13\16"+
                    "\13\u0128\13\13\3\13\5\13\u012b\n\13\3\13\5\13\u012e\n\13\3\f\3\f\3\f"+
                    "\5\f\u0133\n\f\3\f\3\f\5\f\u0137\n\f\3\f\3\f\5\f\u013b\n\f\3\f\3\f\3\f"+
                    "\3\f\5\f\u0141\n\f\7\f\u0143\n\f\f\f\16\f\u0146\13\f\3\f\3\f\3\r\3\r\5"+
                    "\r\u014c\n\r\7\r\u014e\n\r\f\r\16\r\u0151\13\r\3\16\3\16\5\16\u0155\n"+
                    "\16\7\16\u0157\n\16\f\16\16\16\u015a\13\16\3\17\3\17\5\17\u015e\n\17\3"+
                    "\20\3\20\3\20\3\20\3\20\5\20\u0165\n\20\3\21\3\21\3\21\5\21\u016a\n\21"+
                    "\3\22\3\22\5\22\u016e\n\22\3\22\3\22\5\22\u0172\n\22\3\22\3\22\5\22\u0176"+
                    "\n\22\3\22\5\22\u0179\n\22\3\23\3\23\5\23\u017d\n\23\3\23\3\23\3\23\3"+
                    "\23\3\23\3\23\3\24\3\24\5\24\u0187\n\24\3\24\3\24\3\24\7\24\u018c\n\24"+
                    "\f\24\16\24\u018f\13\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
                    "\25\5\25\u019b\n\25\3\26\3\26\5\26\u019f\n\26\3\26\3\26\3\27\3\27\5\27"+
                    "\u01a5\n\27\3\27\3\27\3\27\7\27\u01aa\n\27\f\27\16\27\u01ad\13\27\3\30"+
                    "\3\30\5\30\u01b1\n\30\3\30\3\30\5\30\u01b5\n\30\3\30\3\30\3\30\3\30\5"+
                    "\30\u01bb\n\30\3\30\3\30\5\30\u01bf\n\30\3\30\3\30\3\30\3\30\5\30\u01c5"+
                    "\n\30\3\30\3\30\5\30\u01c9\n\30\3\30\3\30\3\30\3\30\5\30\u01cf\n\30\3"+
                    "\30\3\30\5\30\u01d3\n\30\3\31\3\31\5\31\u01d7\n\31\3\31\3\31\5\31\u01db"+
                    "\n\31\3\31\3\31\5\31\u01df\n\31\3\31\3\31\5\31\u01e3\n\31\3\31\7\31\u01e6"+
                    "\n\31\f\31\16\31\u01e9\13\31\3\32\3\32\3\32\3\32\5\32\u01ef\n\32\3\32"+
                    "\3\32\5\32\u01f3\n\32\3\32\7\32\u01f6\n\32\f\32\16\32\u01f9\13\32\3\33"+
                    "\3\33\3\33\3\33\5\33\u01ff\n\33\3\34\3\34\3\34\3\34\5\34\u0205\n\34\3"+
                    "\34\3\34\3\34\5\34\u020a\n\34\3\35\3\35\3\35\3\35\5\35\u0210\n\35\3\35"+
                    "\3\35\3\35\3\35\5\35\u0216\n\35\3\36\3\36\5\36\u021a\n\36\3\36\3\36\5"+
                    "\36\u021e\n\36\3\36\7\36\u0221\n\36\f\36\16\36\u0224\13\36\3\36\5\36\u0227"+
                    "\n\36\3\37\3\37\3\37\3\37\3\37\5\37\u022e\n\37\3\37\3\37\3 \3 \5 \u0234"+
                    "\n \3 \5 \u0237\n \3 \3 \3 \5 \u023c\n \3 \5 \u023f\n \3!\3!\5!\u0243"+
                    "\n!\3!\5!\u0246\n!\3!\3!\3!\3\"\3\"\3\"\5\"\u024e\n\"\3\"\3\"\5\"\u0252"+
                    "\n\"\3\"\3\"\5\"\u0256\n\"\3#\3#\5#\u025a\n#\3#\3#\5#\u025e\n#\3#\7#\u0261"+
                    "\n#\f#\16#\u0264\13#\3#\3#\5#\u0268\n#\3#\3#\5#\u026c\n#\3#\7#\u026f\n"+
                    "#\f#\16#\u0272\13#\5#\u0274\n#\3$\3$\3$\3$\3$\3$\3$\5$\u027d\n$\3%\3%"+
                    "\3%\3%\3%\3%\3%\5%\u0286\n%\3%\7%\u0289\n%\f%\16%\u028c\13%\3&\3&\3&\3"+
                    "&\3\'\3\'\3\'\3\'\3(\3(\5(\u0298\n(\3(\5(\u029b\n(\3)\3)\3)\3)\3*\3*\5"+
                    "*\u02a3\n*\3*\3*\5*\u02a7\n*\3*\7*\u02aa\n*\f*\16*\u02ad\13*\3+\3+\5+"+
                    "\u02b1\n+\3+\3+\5+\u02b5\n+\3+\3+\3+\5+\u02ba\n+\3,\3,\3-\3-\5-\u02c0"+
                    "\n-\3-\7-\u02c3\n-\f-\16-\u02c6\13-\3-\3-\3-\3-\5-\u02cc\n-\3.\3.\5.\u02d0"+
                    "\n.\3.\3.\5.\u02d4\n.\5.\u02d6\n.\3.\3.\5.\u02da\n.\5.\u02dc\n.\3.\3."+
                    "\5.\u02e0\n.\5.\u02e2\n.\3.\3.\3/\3/\5/\u02e8\n/\3/\3/\3\60\3\60\5\60"+
                    "\u02ee\n\60\3\60\3\60\5\60\u02f2\n\60\3\60\5\60\u02f5\n\60\3\60\5\60\u02f8"+
                    "\n\60\3\60\3\60\5\60\u02fc\n\60\3\60\3\60\3\60\3\60\5\60\u0302\n\60\3"+
                    "\60\3\60\5\60\u0306\n\60\3\60\5\60\u0309\n\60\3\60\5\60\u030c\n\60\3\60"+
                    "\3\60\3\60\3\60\5\60\u0312\n\60\3\60\5\60\u0315\n\60\3\60\5\60\u0318\n"+
                    "\60\3\60\3\60\5\60\u031c\n\60\3\60\3\60\3\60\3\60\5\60\u0322\n\60\3\60"+
                    "\5\60\u0325\n\60\3\60\5\60\u0328\n\60\3\60\3\60\5\60\u032c\n\60\3\61\3"+
                    "\61\5\61\u0330\n\61\3\61\3\61\5\61\u0334\n\61\5\61\u0336\n\61\3\61\3\61"+
                    "\5\61\u033a\n\61\5\61\u033c\n\61\3\61\5\61\u033f\n\61\3\61\3\61\5\61\u0343"+
                    "\n\61\5\61\u0345\n\61\3\61\3\61\3\62\3\62\5\62\u034b\n\62\3\63\3\63\5"+
                    "\63\u034f\n\63\3\63\3\63\5\63\u0353\n\63\3\63\3\63\5\63\u0357\n\63\3\63"+
                    "\5\63\u035a\n\63\3\63\7\63\u035d\n\63\f\63\16\63\u0360\13\63\3\64\3\64"+
                    "\5\64\u0364\n\64\3\64\7\64\u0367\n\64\f\64\16\64\u036a\13\64\3\65\3\65"+
                    "\5\65\u036e\n\65\3\65\3\65\3\66\3\66\5\66\u0374\n\66\3\66\3\66\5\66\u0378"+
                    "\n\66\5\66\u037a\n\66\3\66\3\66\5\66\u037e\n\66\3\66\3\66\5\66\u0382\n"+
                    "\66\5\66\u0384\n\66\5\66\u0386\n\66\3\67\3\67\38\38\39\39\3:\3:\3:\3:"+
                    "\3:\7:\u0393\n:\f:\16:\u0396\13:\3;\3;\3;\3;\3;\7;\u039d\n;\f;\16;\u03a0"+
                    "\13;\3<\3<\3<\3<\3<\7<\u03a7\n<\f<\16<\u03aa\13<\3=\3=\5=\u03ae\n=\7="+
                    "\u03b0\n=\f=\16=\u03b3\13=\3=\3=\3>\3>\5>\u03b9\n>\3>\7>\u03bc\n>\f>\16"+
                    ">\u03bf\13>\3?\3?\5?\u03c3\n?\3?\3?\5?\u03c7\n?\3?\3?\5?\u03cb\n?\3?\3"+
                    "?\5?\u03cf\n?\3?\7?\u03d2\n?\f?\16?\u03d5\13?\3@\3@\5@\u03d9\n@\3@\3@"+
                    "\5@\u03dd\n@\3@\3@\5@\u03e1\n@\3@\3@\5@\u03e5\n@\3@\3@\5@\u03e9\n@\3@"+
                    "\3@\5@\u03ed\n@\3@\7@\u03f0\n@\f@\16@\u03f3\13@\3A\3A\5A\u03f7\nA\3A\3"+
                    "A\5A\u03fb\nA\3A\7A\u03fe\nA\fA\16A\u0401\13A\3B\3B\5B\u0405\nB\7B\u0407"+
                    "\nB\fB\16B\u040a\13B\3B\3B\3C\3C\5C\u0410\nC\3C\3C\3C\3C\3C\5C\u0417\n"+
                    "C\3C\3C\5C\u041b\nC\3C\3C\5C\u041f\nC\3C\3C\5C\u0423\nC\3C\3C\3C\3C\3"+
                    "C\3C\3C\3C\3C\3C\3C\3C\3C\5C\u0432\nC\3C\5C\u0435\nC\3C\3C\3C\3C\3C\3"+
                    "C\3C\3C\3C\3C\3C\7C\u0442\nC\fC\16C\u0445\13C\3D\3D\5D\u0449\nD\3D\3D"+
                    "\5D\u044d\nD\7D\u044f\nD\fD\16D\u0452\13D\3E\3E\3E\3E\3E\5E\u0459\nE\3"+
                    "E\3E\5E\u045d\nE\3E\3E\5E\u0461\nE\3E\3E\3E\3E\3E\5E\u0468\nE\3E\3E\5"+
                    "E\u046c\nE\3E\3E\5E\u0470\nE\3E\3E\3E\3E\5E\u0476\nE\3E\3E\5E\u047a\n"+
                    "E\3E\3E\5E\u047e\nE\3E\5E\u0481\nE\3E\3E\5E\u0485\nE\3E\3E\3E\3E\5E\u048b"+
                    "\nE\3E\3E\5E\u048f\nE\3E\3E\5E\u0493\nE\3E\3E\3E\3E\5E\u0499\nE\3E\3E"+
                    "\5E\u049d\nE\3E\3E\5E\u04a1\nE\3E\3E\3E\3E\5E\u04a7\nE\3E\3E\5E\u04ab"+
                    "\nE\3E\3E\5E\u04af\nE\3E\3E\3E\3E\5E\u04b5\nE\3E\3E\5E\u04b9\nE\3E\3E"+
                    "\5E\u04bd\nE\3E\3E\3E\3E\3E\3E\5E\u04c5\nE\3F\3F\3F\3F\3F\3F\5F\u04cd"+
                    "\nF\3G\3G\3H\3H\5H\u04d3\nH\3H\3H\5H\u04d7\nH\3H\3H\5H\u04db\nH\3H\3H"+
                    "\5H\u04df\nH\7H\u04e1\nH\fH\16H\u04e4\13H\5H\u04e6\nH\3H\3H\3I\3I\5I\u04ec"+
                    "\nI\3I\3I\3I\5I\u04f1\nI\3I\3I\3I\5I\u04f6\nI\3I\3I\3I\5I\u04fb\nI\3I"+
                    "\3I\3I\5I\u0500\nI\3I\3I\3I\5I\u0505\nI\3I\5I\u0508\nI\3J\3J\5J\u050c"+
                    "\nJ\3J\3J\5J\u0510\nJ\3J\3J\3K\3K\5K\u0516\nK\3K\6K\u0519\nK\rK\16K\u051a"+
                    "\3L\3L\5L\u051f\nL\3L\5L\u0522\nL\3M\3M\3M\3M\3M\3M\3N\3N\5N\u052c\nN"+
                    "\3N\3N\5N\u0530\nN\3N\3N\5N\u0534\nN\5N\u0536\nN\3N\3N\5N\u053a\nN\3N"+
                    "\3N\5N\u053e\nN\3N\3N\5N\u0542\nN\7N\u0544\nN\fN\16N\u0547\13N\5N\u0549"+
                    "\nN\3N\3N\3O\3O\5O\u054f\nO\3P\3P\5P\u0553\nP\3P\3P\5P\u0557\nP\3P\3P"+
                    "\5P\u055b\nP\3P\3P\5P\u055f\nP\3P\3P\5P\u0563\nP\7P\u0565\nP\fP\16P\u0568"+
                    "\13P\5P\u056a\nP\3P\3P\3Q\3Q\3R\3R\3S\3S\3S\3T\3T\3T\7T\u0578\nT\fT\16"+
                    "T\u057b\13T\3U\3U\5U\u057f\nU\3U\3U\5U\u0583\nU\3U\3U\5U\u0587\nU\3U\5"+
                    "U\u058a\nU\3U\5U\u058d\nU\3U\3U\3V\3V\5V\u0593\nV\3V\3V\5V\u0597\nV\3"+
                    "V\3V\5V\u059b\nV\5V\u059d\nV\3V\3V\5V\u05a1\nV\3V\3V\5V\u05a5\nV\3V\3"+
                    "V\5V\u05a9\nV\5V\u05ab\nV\3V\3V\5V\u05af\nV\3V\3V\5V\u05b3\nV\3V\3V\3"+
                    "W\3W\5W\u05b9\nW\3W\3W\3X\3X\5X\u05bf\nX\3X\6X\u05c2\nX\rX\16X\u05c3\3"+
                    "X\3X\5X\u05c8\nX\3X\3X\5X\u05cc\nX\3X\6X\u05cf\nX\rX\16X\u05d0\5X\u05d3"+
                    "\nX\3X\5X\u05d6\nX\3X\3X\5X\u05da\nX\3X\5X\u05dd\nX\3X\5X\u05e0\nX\3X"+
                    "\3X\3Y\3Y\5Y\u05e6\nY\3Y\3Y\5Y\u05ea\nY\3Y\3Y\5Y\u05ee\nY\3Y\3Y\3Z\3Z"+
                    "\3[\3[\5[\u05f6\n[\3\\\3\\\5\\\u05fa\n\\\3\\\3\\\5\\\u05fe\n\\\3\\\3\\"+
                    "\5\\\u0602\n\\\3\\\3\\\5\\\u0606\n\\\3\\\3\\\5\\\u060a\n\\\3\\\3\\\5\\"+
                    "\u060e\n\\\3\\\3\\\5\\\u0612\n\\\3\\\3\\\5\\\u0616\n\\\7\\\u0618\n\\\f"+
                    "\\\16\\\u061b\13\\\5\\\u061d\n\\\3\\\3\\\3]\3]\3]\5]\u0624\n]\3^\3^\5"+
                    "^\u0628\n^\3^\6^\u062b\n^\r^\16^\u062c\3_\3_\3`\3`\3a\3a\3b\3b\5b\u0637"+
                    "\nb\3c\3c\3d\3d\3e\3e\3f\3f\3g\3g\3g\2\2h\2\4\6\b\n\f\16\20\22\24\26\30"+
                    "\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080"+
                    "\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098"+
                    "\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0"+
                    "\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8"+
                    "\u00ca\u00cc\2\f\3\2GJ\4\2\7\7\20\20\3\2\\]\3\2fh\3\2pq\6\2\61=@U\\cr"+
                    "{\7\2\62\62V[ii||\177\177\4\2\26\26\36!\4\2\27\27\"%\4\2\7\7&\60\2\u0728"+
                    "\2\u00cf\3\2\2\2\4\u00dd\3\2\2\2\6\u00e1\3\2\2\2\b\u00e3\3\2\2\2\n\u00f9"+
                    "\3\2\2\2\f\u00fd\3\2\2\2\16\u0102\3\2\2\2\20\u0104\3\2\2\2\22\u0107\3"+
                    "\2\2\2\24\u011f\3\2\2\2\26\u0136\3\2\2\2\30\u014f\3\2\2\2\32\u0158\3\2"+
                    "\2\2\34\u015d\3\2\2\2\36\u0164\3\2\2\2 \u0169\3\2\2\2\"\u016d\3\2\2\2"+
                    "$\u017a\3\2\2\2&\u0184\3\2\2\2(\u019a\3\2\2\2*\u019c\3\2\2\2,\u01a2\3"+
                    "\2\2\2.\u01d2\3\2\2\2\60\u01d6\3\2\2\2\62\u01ea\3\2\2\2\64\u01fe\3\2\2"+
                    "\2\66\u0200\3\2\2\28\u020b\3\2\2\2:\u0226\3\2\2\2<\u022d\3\2\2\2>\u0231"+
                    "\3\2\2\2@\u0240\3\2\2\2B\u024a\3\2\2\2D\u0273\3\2\2\2F\u027c\3\2\2\2H"+
                    "\u027e\3\2\2\2J\u028d\3\2\2\2L\u0291\3\2\2\2N\u0295\3\2\2\2P\u029c\3\2"+
                    "\2\2R\u02a0\3\2\2\2T\u02b9\3\2\2\2V\u02bb\3\2\2\2X\u02cb\3\2\2\2Z\u02cd"+
                    "\3\2\2\2\\\u02e5\3\2\2\2^\u032b\3\2\2\2`\u032d\3\2\2\2b\u034a\3\2\2\2"+
                    "d\u034c\3\2\2\2f\u0361\3\2\2\2h\u036b\3\2\2\2j\u0371\3\2\2\2l\u0387\3"+
                    "\2\2\2n\u0389\3\2\2\2p\u038b\3\2\2\2r\u038d\3\2\2\2t\u0397\3\2\2\2v\u03a1"+
                    "\3\2\2\2x\u03b1\3\2\2\2z\u03b6\3\2\2\2|\u03c0\3\2\2\2~\u03d6\3\2\2\2\u0080"+
                    "\u03f4\3\2\2\2\u0082\u0408\3\2\2\2\u0084\u040d\3\2\2\2\u0086\u0446\3\2"+
                    "\2\2\u0088\u04c4\3\2\2\2\u008a\u04cc\3\2\2\2\u008c\u04ce\3\2\2\2\u008e"+
                    "\u04d0\3\2\2\2\u0090\u0507\3\2\2\2\u0092\u0509\3\2\2\2\u0094\u0513\3\2"+
                    "\2\2\u0096\u051c\3\2\2\2\u0098\u0523\3\2\2\2\u009a\u0529\3\2\2\2\u009c"+
                    "\u054e\3\2\2\2\u009e\u0550\3\2\2\2\u00a0\u056d\3\2\2\2\u00a2\u056f\3\2"+
                    "\2\2\u00a4\u0571\3\2\2\2\u00a6\u0579\3\2\2\2\u00a8\u057c\3\2\2\2\u00aa"+
                    "\u0590\3\2\2\2\u00ac\u05b6\3\2\2\2\u00ae\u05d2\3\2\2\2\u00b0\u05e3\3\2"+
                    "\2\2\u00b2\u05f1\3\2\2\2\u00b4\u05f5\3\2\2\2\u00b6\u05f7\3\2\2\2\u00b8"+
                    "\u0620\3\2\2\2\u00ba\u0625\3\2\2\2\u00bc\u062e\3\2\2\2\u00be\u0630\3\2"+
                    "\2\2\u00c0\u0632\3\2\2\2\u00c2\u0636\3\2\2\2\u00c4\u0638\3\2\2\2\u00c6"+
                    "\u063a\3\2\2\2\u00c8\u063c\3\2\2\2\u00ca\u063e\3\2\2\2\u00cc\u0640\3\2"+
                    "\2\2\u00ce\u00d0\7\u0080\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
                    "\u00d1\3\2\2\2\u00d1\u00d6\5\4\3\2\u00d2\u00d4\7\u0080\2\2\u00d3\u00d2"+
                    "\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\7\3\2\2\u00d6"+
                    "\u00d3\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00da\7\u0080"+
                    "\2\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
                    "\u00dc\7\2\2\3\u00dc\3\3\2\2\2\u00dd\u00de\5\6\4\2\u00de\5\3\2\2\2\u00df"+
                    "\u00e2\5\b\5\2\u00e0\u00e2\58\35\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2"+
                    "\2\2\u00e2\7\3\2\2\2\u00e3\u00ea\5\f\7\2\u00e4\u00e6\7\u0080\2\2\u00e5"+
                    "\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\5\n"+
                    "\6\2\u00e8\u00e5\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea"+
                    "\u00eb\3\2\2\2\u00eb\t\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\7\61\2"+
                    "\2\u00ee\u00ef\7\u0080\2\2\u00ef\u00f1\7\62\2\2\u00f0\u00f2\7\u0080\2"+
                    "\2\u00f1\u00f0\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00fa"+
                    "\5\f\7\2\u00f4\u00f6\7\61\2\2\u00f5\u00f7\7\u0080\2\2\u00f6\u00f5\3\2"+
                    "\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\5\f\7\2\u00f9"+
                    "\u00ed\3\2\2\2\u00f9\u00f4\3\2\2\2\u00fa\13\3\2\2\2\u00fb\u00fe\5\16\b"+
                    "\2\u00fc\u00fe\5\26\f\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2\u00fe"+
                    "\r\3\2\2\2\u00ff\u0103\5\20\t\2\u0100\u0103\5\22\n\2\u0101\u0103\5\24"+
                    "\13\2\u0102\u00ff\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103"+
                    "\17\3\2\2\2\u0104\u0105\5\30\r\2\u0105\u0106\5@!\2\u0106\21\3\2\2\2\u0107"+
                    "\u010e\5 \21\2\u0108\u010a\7\u0080\2\2\u0109\u0108\3\2\2\2\u0109\u010a"+
                    "\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\5 \21\2\u010c\u0109\3\2\2\2\u010d"+
                    "\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0115\3\2"+
                    "\2\2\u0110\u010e\3\2\2\2\u0111\u0113\7\u0080\2\2\u0112\u0111\3\2\2\2\u0112"+
                    "\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\5\36\20\2\u0115\u0112\3"+
                    "\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118"+
                    "\u011d\3\2\2\2\u0119\u011b\7\u0080\2\2\u011a\u0119\3\2\2\2\u011a\u011b"+
                    "\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\5@!\2\u011d\u011a\3\2\2\2\u011d"+
                    "\u011e\3\2\2\2\u011e\23\3\2\2\2\u011f\u0126\5\34\17\2\u0120\u0122\7\u0080"+
                    "\2\2\u0121\u0120\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\3\2\2\2\u0123"+
                    "\u0125\5\36\20\2\u0124\u0121\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3"+
                    "\2\2\2\u0126\u0127\3\2\2\2\u0127\u012d\3\2\2\2\u0128\u0126\3\2\2\2\u0129"+
                    "\u012b\7\u0080\2\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c"+
                    "\3\2\2\2\u012c\u012e\5@!\2\u012d\u012a\3\2\2\2\u012d\u012e\3\2\2\2\u012e"+
                    "\25\3\2\2\2\u012f\u0137\5\30\r\2\u0130\u0132\5\34\17\2\u0131\u0133\7\u0080"+
                    "\2\2\u0132\u0131\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
                    "\u0135\5\32\16\2\u0135\u0137\3\2\2\2\u0136\u012f\3\2\2\2\u0136\u0130\3"+
                    "\2\2\2\u0137\u0138\3\2\2\2\u0138\u013a\5> \2\u0139\u013b\7\u0080\2\2\u013a"+
                    "\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u0144\3\2\2\2\u013c\u013d\5\30"+
                    "\r\2\u013d\u013e\5\32\16\2\u013e\u0140\5> \2\u013f\u0141\7\u0080\2\2\u0140"+
                    "\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0143\3\2\2\2\u0142\u013c\3\2"+
                    "\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
                    "\u0147\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148\5\16\b\2\u0148\27\3\2\2"+
                    "\2\u0149\u014b\5 \21\2\u014a\u014c\7\u0080\2\2\u014b\u014a\3\2\2\2\u014b"+
                    "\u014c\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u0149\3\2\2\2\u014e\u0151\3\2"+
                    "\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\31\3\2\2\2\u0151\u014f"+
                    "\3\2\2\2\u0152\u0154\5\36\20\2\u0153\u0155\7\u0080\2\2\u0154\u0153\3\2"+
                    "\2\2\u0154\u0155\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0152\3\2\2\2\u0157"+
                    "\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\33\3\2\2"+
                    "\2\u015a\u0158\3\2\2\2\u015b\u015e\5*\26\2\u015c\u015e\5&\24\2\u015d\u015b"+
                    "\3\2\2\2\u015d\u015c\3\2\2\2\u015e\35\3\2\2\2\u015f\u0165\5*\26\2\u0160"+
                    "\u0165\5&\24\2\u0161\u0165\5\60\31\2\u0162\u0165\5,\27\2\u0163\u0165\5"+
                    "\62\32\2\u0164\u015f\3\2\2\2\u0164\u0160\3\2\2\2\u0164\u0161\3\2\2\2\u0164"+
                    "\u0162\3\2\2\2\u0164\u0163\3\2\2\2\u0165\37\3\2\2\2\u0166\u016a\5\"\22"+
                    "\2\u0167\u016a\5$\23\2\u0168\u016a\5\66\34\2\u0169\u0166\3\2\2\2\u0169"+
                    "\u0167\3\2\2\2\u0169\u0168\3\2\2\2\u016a!\3\2\2\2\u016b\u016c\7\63\2\2"+
                    "\u016c\u016e\7\u0080\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e"+
                    "\u016f\3\2\2\2\u016f\u0171\7\64\2\2\u0170\u0172\7\u0080\2\2\u0171\u0170"+
                    "\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0178\5R*\2\u0174"+
                    "\u0176\7\u0080\2\2\u0175\u0174\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177"+
                    "\3\2\2\2\u0177\u0179\5P)\2\u0178\u0175\3\2\2\2\u0178\u0179\3\2\2\2\u0179"+
                    "#\3\2\2\2\u017a\u017c\7\65\2\2\u017b\u017d\7\u0080\2\2\u017c\u017b\3\2"+
                    "\2\2\u017c\u017d\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\5p9\2\u017f\u0180"+
                    "\7\u0080\2\2\u0180\u0181\7\66\2\2\u0181\u0182\7\u0080\2\2\u0182\u0183"+
                    "\5\u00b2Z\2\u0183%\3\2\2\2\u0184\u0186\7\67\2\2\u0185\u0187\7\u0080\2"+
                    "\2\u0186\u0185\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018d"+
                    "\5T+\2\u0189\u018a\7\u0080\2\2\u018a\u018c\5(\25\2\u018b\u0189\3\2\2\2"+
                    "\u018c\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\'\3"+
                    "\2\2\2\u018f\u018d\3\2\2\2\u0190\u0191\78\2\2\u0191\u0192\7\u0080\2\2"+
                    "\u0192\u0193\7\64\2\2\u0193\u0194\7\u0080\2\2\u0194\u019b\5,\27\2\u0195"+
                    "\u0196\78\2\2\u0196\u0197\7\u0080\2\2\u0197\u0198\79\2\2\u0198\u0199\7"+
                    "\u0080\2\2\u0199\u019b\5,\27\2\u019a\u0190\3\2\2\2\u019a\u0195\3\2\2\2"+
                    "\u019b)\3\2\2\2\u019c\u019e\79\2\2\u019d\u019f\7\u0080\2\2\u019e\u019d"+
                    "\3\2\2\2\u019e\u019f\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a1\5R*\2\u01a1"+
                    "+\3\2\2\2\u01a2\u01a4\7:\2\2\u01a3\u01a5\7\u0080\2\2\u01a4\u01a3\3\2\2"+
                    "\2\u01a4\u01a5\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01ab\5.\30\2\u01a7\u01a8"+
                    "\7\4\2\2\u01a8\u01aa\5.\30\2\u01a9\u01a7\3\2\2\2\u01aa\u01ad\3\2\2\2\u01ab"+
                    "\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac-\3\2\2\2\u01ad\u01ab\3\2\2\2"+
                    "\u01ae\u01b0\5\u00ba^\2\u01af\u01b1\7\u0080\2\2\u01b0\u01af\3\2\2\2\u01b0"+
                    "\u01b1\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b4\7\5\2\2\u01b3\u01b5\7\u0080"+
                    "\2\2\u01b4\u01b3\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6"+
                    "\u01b7\5p9\2\u01b7\u01d3\3\2\2\2\u01b8\u01ba\5\u00b2Z\2\u01b9\u01bb\7"+
                    "\u0080\2\2\u01ba\u01b9\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bc\3\2\2\2"+
                    "\u01bc\u01be\7\5\2\2\u01bd\u01bf\7\u0080\2\2\u01be\u01bd\3\2\2\2\u01be"+
                    "\u01bf\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1\5p9\2\u01c1\u01d3\3\2\2"+
                    "\2\u01c2\u01c4\5\u00b2Z\2\u01c3\u01c5\7\u0080\2\2\u01c4\u01c3\3\2\2\2"+
                    "\u01c4\u01c5\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c8\7\6\2\2\u01c7\u01c9"+
                    "\7\u0080\2\2\u01c8\u01c7\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01ca\3\2\2"+
                    "\2\u01ca\u01cb\5p9\2\u01cb\u01d3\3\2\2\2\u01cc\u01ce\5\u00b2Z\2\u01cd"+
                    "\u01cf\7\u0080\2\2\u01ce\u01cd\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d0"+
                    "\3\2\2\2\u01d0\u01d1\5f\64\2\u01d1\u01d3\3\2\2\2\u01d2\u01ae\3\2\2\2\u01d2"+
                    "\u01b8\3\2\2\2\u01d2\u01c2\3\2\2\2\u01d2\u01cc\3\2\2\2\u01d3/\3\2\2\2"+
                    "\u01d4\u01d5\7;\2\2\u01d5\u01d7\7\u0080\2\2\u01d6\u01d4\3\2\2\2\u01d6"+
                    "\u01d7\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01da\7<\2\2\u01d9\u01db\7\u0080"+
                    "\2\2\u01da\u01d9\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc"+
                    "\u01e7\5p9\2\u01dd\u01df\7\u0080\2\2\u01de\u01dd\3\2\2\2\u01de\u01df\3"+
                    "\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e2\7\4\2\2\u01e1\u01e3\7\u0080\2\2"+
                    "\u01e2\u01e1\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e6"+
                    "\5p9\2\u01e5\u01de\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7"+
                    "\u01e8\3\2\2\2\u01e8\61\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01eb\7=\2\2"+
                    "\u01eb\u01ec\7\u0080\2\2\u01ec\u01f7\5\64\33\2\u01ed\u01ef\7\u0080\2\2"+
                    "\u01ee\u01ed\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f2"+
                    "\7\4\2\2\u01f1\u01f3\7\u0080\2\2\u01f2\u01f1\3\2\2\2\u01f2\u01f3\3\2\2"+
                    "\2\u01f3\u01f4\3\2\2\2\u01f4\u01f6\5\64\33\2\u01f5\u01ee\3\2\2\2\u01f6"+
                    "\u01f9\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8\63\3\2\2"+
                    "\2\u01f9\u01f7\3\2\2\2\u01fa\u01fb\5\u00b2Z\2\u01fb\u01fc\5f\64\2\u01fc"+
                    "\u01ff\3\2\2\2\u01fd\u01ff\5\u00ba^\2\u01fe\u01fa\3\2\2\2\u01fe\u01fd"+
                    "\3\2\2\2\u01ff\65\3\2\2\2\u0200\u0201\7>\2\2\u0201\u0202\7\u0080\2\2\u0202"+
                    "\u0209\5\u009eP\2\u0203\u0205\7\u0080\2\2\u0204\u0203\3\2\2\2\u0204\u0205"+
                    "\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0207\7?\2\2\u0207\u0208\7\u0080\2"+
                    "\2\u0208\u020a\5:\36\2\u0209\u0204\3\2\2\2\u0209\u020a\3\2\2\2\u020a\67"+
                    "\3\2\2\2\u020b\u020c\7>\2\2\u020c\u020f\7\u0080\2\2\u020d\u0210\5\u009e"+
                    "P\2\u020e\u0210\5\u00a0Q\2\u020f\u020d\3\2\2\2\u020f\u020e\3\2\2\2\u0210"+
                    "\u0215\3\2\2\2\u0211\u0212\7\u0080\2\2\u0212\u0213\7?\2\2\u0213\u0214"+
                    "\7\u0080\2\2\u0214\u0216\5:\36\2\u0215\u0211\3\2\2\2\u0215\u0216\3\2\2"+
                    "\2\u02169\3\2\2\2\u0217\u0222\5<\37\2\u0218\u021a\7\u0080\2\2\u0219\u0218"+
                    "\3\2\2\2\u0219\u021a\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021d\7\4\2\2\u021c"+
                    "\u021e\7\u0080\2\2\u021d\u021c\3\2\2\2\u021d\u021e\3\2\2\2\u021e\u021f"+
                    "\3\2\2\2\u021f\u0221\5<\37\2\u0220\u0219\3\2\2\2\u0221\u0224\3\2\2\2\u0222"+
                    "\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0227\3\2\2\2\u0224\u0222\3\2"+
                    "\2\2\u0225\u0227\7\7\2\2\u0226\u0217\3\2\2\2\u0226\u0225\3\2\2\2\u0227"+
                    ";\3\2\2\2\u0228\u0229\5\u00a2R\2\u0229\u022a\7\u0080\2\2\u022a\u022b\7"+
                    "\66\2\2\u022b\u022c\7\u0080\2\2\u022c\u022e\3\2\2\2\u022d\u0228\3\2\2"+
                    "\2\u022d\u022e\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0230\5\u00b2Z\2\u0230"+
                    "=\3\2\2\2\u0231\u0236\7@\2\2\u0232\u0234\7\u0080\2\2\u0233\u0232\3\2\2"+
                    "\2\u0233\u0234\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0237\7A\2\2\u0236\u0233"+
                    "\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u0239\7\u0080\2"+
                    "\2\u0239\u023e\5B\"\2\u023a\u023c\7\u0080\2\2\u023b\u023a\3\2\2\2\u023b"+
                    "\u023c\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u023f\5P)\2\u023e\u023b\3\2\2"+
                    "\2\u023e\u023f\3\2\2\2\u023f?\3\2\2\2\u0240\u0245\7B\2\2\u0241\u0243\7"+
                    "\u0080\2\2\u0242\u0241\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0244\3\2\2\2"+
                    "\u0244\u0246\7A\2\2\u0245\u0242\3\2\2\2\u0245\u0246\3\2\2\2\u0246\u0247"+
                    "\3\2\2\2\u0247\u0248\7\u0080\2\2\u0248\u0249\5B\"\2\u0249A\3\2\2\2\u024a"+
                    "\u024d\5D#\2\u024b\u024c\7\u0080\2\2\u024c\u024e\5H%\2\u024d\u024b\3\2"+
                    "\2\2\u024d\u024e\3\2\2\2\u024e\u0251\3\2\2\2\u024f\u0250\7\u0080\2\2\u0250"+
                    "\u0252\5J&\2\u0251\u024f\3\2\2\2\u0251\u0252\3\2\2\2\u0252\u0255\3\2\2"+
                    "\2\u0253\u0254\7\u0080\2\2\u0254\u0256\5L\'\2\u0255\u0253\3\2\2\2\u0255"+
                    "\u0256\3\2\2\2\u0256C\3\2\2\2\u0257\u0262\7\b\2\2\u0258\u025a\7\u0080"+
                    "\2\2\u0259\u0258\3\2\2\2\u0259\u025a\3\2\2\2\u025a\u025b\3\2\2\2\u025b"+
                    "\u025d\7\4\2\2\u025c\u025e\7\u0080\2\2\u025d\u025c\3\2\2\2\u025d\u025e"+
                    "\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u0261\5F$\2\u0260\u0259\3\2\2\2\u0261"+
                    "\u0264\3\2\2\2\u0262\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0274\3\2"+
                    "\2\2\u0264\u0262\3\2\2\2\u0265\u0270\5F$\2\u0266\u0268\7\u0080\2\2\u0267"+
                    "\u0266\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u0269\3\2\2\2\u0269\u026b\7\4"+
                    "\2\2\u026a\u026c\7\u0080\2\2\u026b\u026a\3\2\2\2\u026b\u026c\3\2\2\2\u026c"+
                    "\u026d\3\2\2\2\u026d\u026f\5F$\2\u026e\u0267\3\2\2\2\u026f\u0272\3\2\2"+
                    "\2\u0270\u026e\3\2\2\2\u0270\u0271\3\2\2\2\u0271\u0274\3\2\2\2\u0272\u0270"+
                    "\3\2\2\2\u0273\u0257\3\2\2\2\u0273\u0265\3\2\2\2\u0274E\3\2\2\2\u0275"+
                    "\u0276\5p9\2\u0276\u0277\7\u0080\2\2\u0277\u0278\7\66\2\2\u0278\u0279"+
                    "\7\u0080\2\2\u0279\u027a\5\u00b2Z\2\u027a\u027d\3\2\2\2\u027b\u027d\5"+
                    "p9\2\u027c\u0275\3\2\2\2\u027c\u027b\3\2\2\2\u027dG\3\2\2\2\u027e\u027f"+
                    "\7C\2\2\u027f\u0280\7\u0080\2\2\u0280\u0281\7D\2\2\u0281\u0282\7\u0080"+
                    "\2\2\u0282\u028a\5N(\2\u0283\u0285\7\4\2\2\u0284\u0286\7\u0080\2\2\u0285"+
                    "\u0284\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u0287\3\2\2\2\u0287\u0289\5N"+
                    "(\2\u0288\u0283\3\2\2\2\u0289\u028c\3\2\2\2\u028a\u0288\3\2\2\2\u028a"+
                    "\u028b\3\2\2\2\u028bI\3\2\2\2\u028c\u028a\3\2\2\2\u028d\u028e\7E\2\2\u028e"+
                    "\u028f\7\u0080\2\2\u028f\u0290\5p9\2\u0290K\3\2\2\2\u0291\u0292\7F\2\2"+
                    "\u0292\u0293\7\u0080\2\2\u0293\u0294\5p9\2\u0294M\3\2\2\2\u0295\u029a"+
                    "\5p9\2\u0296\u0298\7\u0080\2\2\u0297\u0296\3\2\2\2\u0297\u0298\3\2\2\2"+
                    "\u0298\u0299\3\2\2\2\u0299\u029b\t\2\2\2\u029a\u0297\3\2\2\2\u029a\u029b"+
                    "\3\2\2\2\u029bO\3\2\2\2\u029c\u029d\7K\2\2\u029d\u029e\7\u0080\2\2\u029e"+
                    "\u029f\5p9\2\u029fQ\3\2\2\2\u02a0\u02ab\5T+\2\u02a1\u02a3\7\u0080\2\2"+
                    "\u02a2\u02a1\3\2\2\2\u02a2\u02a3\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a4\u02a6"+
                    "\7\4\2\2\u02a5\u02a7\7\u0080\2\2\u02a6\u02a5\3\2\2\2\u02a6\u02a7\3\2\2"+
                    "\2\u02a7\u02a8\3\2\2\2\u02a8\u02aa\5T+\2\u02a9\u02a2\3\2\2\2\u02aa\u02ad"+
                    "\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ab\u02ac\3\2\2\2\u02acS\3\2\2\2\u02ad"+
                    "\u02ab\3\2\2\2\u02ae\u02b0\5\u00b2Z\2\u02af\u02b1\7\u0080\2\2\u02b0\u02af"+
                    "\3\2\2\2\u02b0\u02b1\3\2\2\2\u02b1\u02b2\3\2\2\2\u02b2\u02b4\7\5\2\2\u02b3"+
                    "\u02b5\7\u0080\2\2\u02b4\u02b3\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02b6"+
                    "\3\2\2\2\u02b6\u02b7\5V,\2\u02b7\u02ba\3\2\2\2\u02b8\u02ba\5V,\2\u02b9"+
                    "\u02ae\3\2\2\2\u02b9\u02b8\3\2\2\2\u02baU\3\2\2\2\u02bb\u02bc\5X-\2\u02bc"+
                    "W\3\2\2\2\u02bd\u02c4\5Z.\2\u02be\u02c0\7\u0080\2\2\u02bf\u02be\3\2\2"+
                    "\2\u02bf\u02c0\3\2\2\2\u02c0\u02c1\3\2\2\2\u02c1\u02c3\5\\/\2\u02c2\u02bf"+
                    "\3\2\2\2\u02c3\u02c6\3\2\2\2\u02c4\u02c2\3\2\2\2\u02c4\u02c5\3\2\2\2\u02c5"+
                    "\u02cc\3\2\2\2\u02c6\u02c4\3\2\2\2\u02c7\u02c8\7\t\2\2\u02c8\u02c9\5X"+
                    "-\2\u02c9\u02ca\7\n\2\2\u02ca\u02cc\3\2\2\2\u02cb\u02bd\3\2\2\2\u02cb"+
                    "\u02c7\3\2\2\2\u02ccY\3\2\2\2\u02cd\u02cf\7\t\2\2\u02ce\u02d0\7\u0080"+
                    "\2\2\u02cf\u02ce\3\2\2\2\u02cf\u02d0\3\2\2\2\u02d0\u02d5\3\2\2\2\u02d1"+
                    "\u02d3\5\u00b2Z\2\u02d2\u02d4\7\u0080\2\2\u02d3\u02d2\3\2\2\2\u02d3\u02d4"+
                    "\3\2\2\2\u02d4\u02d6\3\2\2\2\u02d5\u02d1\3\2\2\2\u02d5\u02d6\3\2\2\2\u02d6"+
                    "\u02db\3\2\2\2\u02d7\u02d9\5f\64\2\u02d8\u02da\7\u0080\2\2\u02d9\u02d8"+
                    "\3\2\2\2\u02d9\u02da\3\2\2\2\u02da\u02dc\3\2\2\2\u02db\u02d7\3\2\2\2\u02db"+
                    "\u02dc\3\2\2\2\u02dc\u02e1\3\2\2\2\u02dd\u02df\5b\62\2\u02de\u02e0\7\u0080"+
                    "\2\2\u02df\u02de\3\2\2\2\u02df\u02e0\3\2\2\2\u02e0\u02e2\3\2\2\2\u02e1"+
                    "\u02dd\3\2\2\2\u02e1\u02e2\3\2\2\2\u02e2\u02e3\3\2\2\2\u02e3\u02e4\7\n"+
                    "\2\2\u02e4[\3\2\2\2\u02e5\u02e7\5^\60\2\u02e6\u02e8\7\u0080\2\2\u02e7"+
                    "\u02e6\3\2\2\2\u02e7\u02e8\3\2\2\2\u02e8\u02e9\3\2\2\2\u02e9\u02ea\5Z"+
                    ".\2\u02ea]\3\2\2\2\u02eb\u02ed\5\u00c8e\2\u02ec\u02ee\7\u0080\2\2\u02ed"+
                    "\u02ec\3\2\2\2\u02ed\u02ee\3\2\2\2\u02ee\u02ef\3\2\2\2\u02ef\u02f1\5\u00cc"+
                    "g\2\u02f0\u02f2\7\u0080\2\2\u02f1\u02f0\3\2\2\2\u02f1\u02f2\3\2\2\2\u02f2"+
                    "\u02f4\3\2\2\2\u02f3\u02f5\5`\61\2\u02f4\u02f3\3\2\2\2\u02f4\u02f5\3\2"+
                    "\2\2\u02f5\u02f7\3\2\2\2\u02f6\u02f8\7\u0080\2\2\u02f7\u02f6\3\2\2\2\u02f7"+
                    "\u02f8\3\2\2\2\u02f8\u02f9\3\2\2\2\u02f9\u02fb\5\u00ccg\2\u02fa\u02fc"+
                    "\7\u0080\2\2\u02fb\u02fa\3\2\2\2\u02fb\u02fc\3\2\2\2\u02fc\u02fd\3\2\2"+
                    "\2\u02fd\u02fe\5\u00caf\2\u02fe\u032c\3\2\2\2\u02ff\u0301\5\u00c8e\2\u0300"+
                    "\u0302\7\u0080\2\2\u0301\u0300\3\2\2\2\u0301\u0302\3\2\2\2\u0302\u0303"+
                    "\3\2\2\2\u0303\u0305\5\u00ccg\2\u0304\u0306\7\u0080\2\2\u0305\u0304\3"+
                    "\2\2\2\u0305\u0306\3\2\2\2\u0306\u0308\3\2\2\2\u0307\u0309\5`\61\2\u0308"+
                    "\u0307\3\2\2\2\u0308\u0309\3\2\2\2\u0309\u030b\3\2\2\2\u030a\u030c\7\u0080"+
                    "\2\2\u030b\u030a\3\2\2\2\u030b\u030c\3\2\2\2\u030c\u030d\3\2\2\2\u030d"+
                    "\u030e\5\u00ccg\2\u030e\u032c\3\2\2\2\u030f\u0311\5\u00ccg\2\u0310\u0312"+
                    "\7\u0080\2\2\u0311\u0310\3\2\2\2\u0311\u0312\3\2\2\2\u0312\u0314\3\2\2"+
                    "\2\u0313\u0315\5`\61\2\u0314\u0313\3\2\2\2\u0314\u0315\3\2\2\2\u0315\u0317"+
                    "\3\2\2\2\u0316\u0318\7\u0080\2\2\u0317\u0316\3\2\2\2\u0317\u0318\3\2\2"+
                    "\2\u0318\u0319\3\2\2\2\u0319\u031b\5\u00ccg\2\u031a\u031c\7\u0080\2\2"+
                    "\u031b\u031a\3\2\2\2\u031b\u031c\3\2\2\2\u031c\u031d\3\2\2\2\u031d\u031e"+
                    "\5\u00caf\2\u031e\u032c\3\2\2\2\u031f\u0321\5\u00ccg\2\u0320\u0322\7\u0080"+
                    "\2\2\u0321\u0320\3\2\2\2\u0321\u0322\3\2\2\2\u0322\u0324\3\2\2\2\u0323"+
                    "\u0325\5`\61\2\u0324\u0323\3\2\2\2\u0324\u0325\3\2\2\2\u0325\u0327\3\2"+
                    "\2\2\u0326\u0328\7\u0080\2\2\u0327\u0326\3\2\2\2\u0327\u0328\3\2\2\2\u0328"+
                    "\u0329\3\2\2\2\u0329\u032a\5\u00ccg\2\u032a\u032c\3\2\2\2\u032b\u02eb"+
                    "\3\2\2\2\u032b\u02ff\3\2\2\2\u032b\u030f\3\2\2\2\u032b\u031f\3\2\2\2\u032c"+
                    "_\3\2\2\2\u032d\u032f\7\13\2\2\u032e\u0330\7\u0080\2\2\u032f\u032e\3\2"+
                    "\2\2\u032f\u0330\3\2\2\2\u0330\u0335\3\2\2\2\u0331\u0333\5\u00b2Z\2\u0332"+
                    "\u0334\7\u0080\2\2\u0333\u0332\3\2\2\2\u0333\u0334\3\2\2\2\u0334\u0336"+
                    "\3\2\2\2\u0335\u0331\3\2\2\2\u0335\u0336\3\2\2\2\u0336\u033b\3\2\2\2\u0337"+
                    "\u0339\5d\63\2\u0338\u033a\7\u0080\2\2\u0339\u0338\3\2\2\2\u0339\u033a"+
                    "\3\2\2\2\u033a\u033c\3\2\2\2\u033b\u0337\3\2\2\2\u033b\u033c\3\2\2\2\u033c"+
                    "\u033e\3\2\2\2\u033d\u033f\5j\66\2\u033e\u033d\3\2\2\2\u033e\u033f\3\2"+
                    "\2\2\u033f\u0344\3\2\2\2\u0340\u0342\5b\62\2\u0341\u0343\7\u0080\2\2\u0342"+
                    "\u0341\3\2\2\2\u0342\u0343\3\2\2\2\u0343\u0345\3\2\2\2\u0344\u0340\3\2"+
                    "\2\2\u0344\u0345\3\2\2\2\u0345\u0346\3\2\2\2\u0346\u0347\7\f\2\2\u0347"+
                    "a\3\2\2\2\u0348\u034b\5\u00b6\\\2\u0349\u034b\5\u00b8]\2\u034a\u0348\3"+
                    "\2\2\2\u034a\u0349\3\2\2\2\u034bc\3\2\2\2\u034c\u034e\7\r\2\2\u034d\u034f"+
                    "\7\u0080\2\2\u034e\u034d\3\2\2\2\u034e\u034f\3\2\2\2\u034f\u0350\3\2\2"+
                    "\2\u0350\u035e\5n8\2\u0351\u0353\7\u0080\2\2\u0352\u0351\3\2\2\2\u0352"+
                    "\u0353\3\2\2\2\u0353\u0354\3\2\2\2\u0354\u0356\7\16\2\2\u0355\u0357\7"+
                    "\r\2\2\u0356\u0355\3\2\2\2\u0356\u0357\3\2\2\2\u0357\u0359\3\2\2\2\u0358"+
                    "\u035a\7\u0080\2\2\u0359\u0358\3\2\2\2\u0359\u035a\3\2\2\2\u035a\u035b"+
                    "\3\2\2\2\u035b\u035d\5n8\2\u035c\u0352\3\2\2\2\u035d\u0360\3\2\2\2\u035e"+
                    "\u035c\3\2\2\2\u035e\u035f\3\2\2\2\u035fe\3\2\2\2\u0360\u035e\3\2\2\2"+
                    "\u0361\u0368\5h\65\2\u0362\u0364\7\u0080\2\2\u0363\u0362\3\2\2\2\u0363"+
                    "\u0364\3\2\2\2\u0364\u0365\3\2\2\2\u0365\u0367\5h\65\2\u0366\u0363\3\2"+
                    "\2\2\u0367\u036a\3\2\2\2\u0368\u0366\3\2\2\2\u0368\u0369\3\2\2\2\u0369"+
                    "g\3\2\2\2\u036a\u0368\3\2\2\2\u036b\u036d\7\r\2\2\u036c\u036e\7\u0080"+
                    "\2\2\u036d\u036c\3\2\2\2\u036d\u036e\3\2\2\2\u036e\u036f\3\2\2\2\u036f"+
                    "\u0370\5l\67\2\u0370i\3\2\2\2\u0371\u0373\7\b\2\2\u0372\u0374\7\u0080"+
                    "\2\2\u0373\u0372\3\2\2\2\u0373\u0374\3\2\2\2\u0374\u0379\3\2\2\2\u0375"+
                    "\u0377\5\u00be`\2\u0376\u0378\7\u0080\2\2\u0377\u0376\3\2\2\2\u0377\u0378"+
                    "\3\2\2\2\u0378\u037a\3\2\2\2\u0379\u0375\3\2\2\2\u0379\u037a\3\2\2\2\u037a"+
                    "\u0385\3\2\2\2\u037b\u037d\7\17\2\2\u037c\u037e\7\u0080\2\2\u037d\u037c"+
                    "\3\2\2\2\u037d\u037e\3\2\2\2\u037e\u0383\3\2\2\2\u037f\u0381\5\u00be`"+
                    "\2\u0380\u0382\7\u0080\2\2\u0381\u0380\3\2\2\2\u0381\u0382\3\2\2\2\u0382"+
                    "\u0384\3\2\2\2\u0383\u037f\3\2\2\2\u0383\u0384\3\2\2\2\u0384\u0386\3\2"+
                    "\2\2\u0385\u037b\3\2\2\2\u0385\u0386\3\2\2\2\u0386k\3\2\2\2\u0387\u0388"+
                    "\5\u00c2b\2\u0388m\3\2\2\2\u0389\u038a\5\u00c2b\2\u038ao\3\2\2\2\u038b"+
                    "\u038c\5r:\2\u038cq\3\2\2\2\u038d\u0394\5t;\2\u038e\u038f\7\u0080\2\2"+
                    "\u038f\u0390\7L\2\2\u0390\u0391\7\u0080\2\2\u0391\u0393\5t;\2\u0392\u038e"+
                    "\3\2\2\2\u0393\u0396\3\2\2\2\u0394\u0392\3\2\2\2\u0394\u0395\3\2\2\2\u0395"+
                    "s\3\2\2\2\u0396\u0394\3\2\2\2\u0397\u039e\5v<\2\u0398\u0399\7\u0080\2"+
                    "\2\u0399\u039a\7M\2\2\u039a\u039b\7\u0080\2\2\u039b\u039d\5v<\2\u039c"+
                    "\u0398\3\2\2\2\u039d\u03a0\3\2\2\2\u039e\u039c\3\2\2\2\u039e\u039f\3\2"+
                    "\2\2\u039fu\3\2\2\2\u03a0\u039e\3\2\2\2\u03a1\u03a8\5x=\2\u03a2\u03a3"+
                    "\7\u0080\2\2\u03a3\u03a4\7N\2\2\u03a4\u03a5\7\u0080\2\2\u03a5\u03a7\5"+
                    "x=\2\u03a6\u03a2\3\2\2\2\u03a7\u03aa\3\2\2\2\u03a8\u03a6\3\2\2\2\u03a8"+
                    "\u03a9\3\2\2\2\u03a9w\3\2\2\2\u03aa\u03a8\3\2\2\2\u03ab\u03ad\7O\2\2\u03ac"+
                    "\u03ae\7\u0080\2\2\u03ad\u03ac\3\2\2\2\u03ad\u03ae\3\2\2\2\u03ae\u03b0"+
                    "\3\2\2\2\u03af\u03ab\3\2\2\2\u03b0\u03b3\3\2\2\2\u03b1\u03af\3\2\2\2\u03b1"+
                    "\u03b2\3\2\2\2\u03b2\u03b4\3\2\2\2\u03b3\u03b1\3\2\2\2\u03b4\u03b5\5z"+
                    ">\2\u03b5y\3\2\2\2\u03b6\u03bd\5|?\2\u03b7\u03b9\7\u0080\2\2\u03b8\u03b7"+
                    "\3\2\2\2\u03b8\u03b9\3\2\2\2\u03b9\u03ba\3\2\2\2\u03ba\u03bc\5\u0090I"+
                    "\2\u03bb\u03b8\3\2\2\2\u03bc\u03bf\3\2\2\2\u03bd\u03bb\3\2\2\2\u03bd\u03be"+
                    "\3\2\2\2\u03be{\3\2\2\2\u03bf\u03bd\3\2\2\2\u03c0\u03d3\5~@\2\u03c1\u03c3"+
                    "\7\u0080\2\2\u03c2\u03c1\3\2\2\2\u03c2\u03c3\3\2\2\2\u03c3\u03c4\3\2\2"+
                    "\2\u03c4\u03c6\7\20\2\2\u03c5\u03c7\7\u0080\2\2\u03c6\u03c5\3\2\2\2\u03c6"+
                    "\u03c7\3\2\2\2\u03c7\u03c8\3\2\2\2\u03c8\u03d2\5~@\2\u03c9\u03cb\7\u0080"+
                    "\2\2\u03ca\u03c9\3\2\2\2\u03ca\u03cb\3\2\2\2\u03cb\u03cc\3\2\2\2\u03cc"+
                    "\u03ce\7\7\2\2\u03cd\u03cf\7\u0080\2\2\u03ce\u03cd\3\2\2\2\u03ce\u03cf"+
                    "\3\2\2\2\u03cf\u03d0\3\2\2\2\u03d0\u03d2\5~@\2\u03d1\u03c2\3\2\2\2\u03d1"+
                    "\u03ca\3\2\2\2\u03d2\u03d5\3\2\2\2\u03d3\u03d1\3\2\2\2\u03d3\u03d4\3\2"+
                    "\2\2\u03d4}\3\2\2\2\u03d5\u03d3\3\2\2\2\u03d6\u03f1\5\u0080A\2\u03d7\u03d9"+
                    "\7\u0080\2\2\u03d8\u03d7\3\2\2\2\u03d8\u03d9\3\2\2\2\u03d9\u03da\3\2\2"+
                    "\2\u03da\u03dc\7\b\2\2\u03db\u03dd\7\u0080\2\2\u03dc\u03db\3\2\2\2\u03dc"+
                    "\u03dd\3\2\2\2\u03dd\u03de\3\2\2\2\u03de\u03f0\5\u0080A\2\u03df\u03e1"+
                    "\7\u0080\2\2\u03e0\u03df\3\2\2\2\u03e0\u03e1\3\2\2\2\u03e1\u03e2\3\2\2"+
                    "\2\u03e2\u03e4\7\21\2\2\u03e3\u03e5\7\u0080\2\2\u03e4\u03e3\3\2\2\2\u03e4"+
                    "\u03e5\3\2\2\2\u03e5\u03e6\3\2\2\2\u03e6\u03f0\5\u0080A\2\u03e7\u03e9"+
                    "\7\u0080\2\2\u03e8\u03e7\3\2\2\2\u03e8\u03e9\3\2\2\2\u03e9\u03ea\3\2\2"+
                    "\2\u03ea\u03ec\7\22\2\2\u03eb\u03ed\7\u0080\2\2\u03ec\u03eb\3\2\2\2\u03ec"+
                    "\u03ed\3\2\2\2\u03ed\u03ee\3\2\2\2\u03ee\u03f0\5\u0080A\2\u03ef\u03d8"+
                    "\3\2\2\2\u03ef\u03e0\3\2\2\2\u03ef\u03e8\3\2\2\2\u03f0\u03f3\3\2\2\2\u03f1"+
                    "\u03ef\3\2\2\2\u03f1\u03f2\3\2\2\2\u03f2\177\3\2\2\2\u03f3\u03f1\3\2\2"+
                    "\2\u03f4\u03ff\5\u0082B\2\u03f5\u03f7\7\u0080\2\2\u03f6\u03f5\3\2\2\2"+
                    "\u03f6\u03f7\3\2\2\2\u03f7\u03f8\3\2\2\2\u03f8\u03fa\7\23\2\2\u03f9\u03fb"+
                    "\7\u0080\2\2\u03fa\u03f9\3\2\2\2\u03fa\u03fb\3\2\2\2\u03fb\u03fc\3\2\2"+
                    "\2\u03fc\u03fe\5\u0082B\2\u03fd\u03f6\3\2\2\2\u03fe\u0401\3\2\2\2\u03ff"+
                    "\u03fd\3\2\2\2\u03ff\u0400\3\2\2\2\u0400\u0081\3\2\2\2\u0401\u03ff\3\2"+
                    "\2\2\u0402\u0404\t\3\2\2\u0403\u0405\7\u0080\2\2\u0404\u0403\3\2\2\2\u0404"+
                    "\u0405\3\2\2\2\u0405\u0407\3\2\2\2\u0406\u0402\3\2\2\2\u0407\u040a\3\2"+
                    "\2\2\u0408\u0406\3\2\2\2\u0408\u0409\3\2\2\2\u0409\u040b\3\2\2\2\u040a"+
                    "\u0408\3\2\2\2\u040b\u040c\5\u0084C\2\u040c\u0083\3\2\2\2\u040d\u0443"+
                    "\5\u0086D\2\u040e\u0410\7\u0080\2\2\u040f\u040e\3\2\2\2\u040f\u0410\3"+
                    "\2\2\2\u0410\u0411\3\2\2\2\u0411\u0412\7\13\2\2\u0412\u0413\5p9\2\u0413"+
                    "\u0414\7\f\2\2\u0414\u0442\3\2\2\2\u0415\u0417\7\u0080\2\2\u0416\u0415"+
                    "\3\2\2\2\u0416\u0417\3\2\2\2\u0417\u0418\3\2\2\2\u0418\u041a\7\13\2\2"+
                    "\u0419\u041b\5p9\2\u041a\u0419\3\2\2\2\u041a\u041b\3\2\2\2\u041b\u041c"+
                    "\3\2\2\2\u041c\u041e\7\17\2\2\u041d\u041f\5p9\2\u041e\u041d\3\2\2\2\u041e"+
                    "\u041f\3\2\2\2\u041f\u0420\3\2\2\2\u0420\u0442\7\f\2\2\u0421\u0423\7\u0080"+
                    "\2\2\u0422\u0421\3\2\2\2\u0422\u0423\3\2\2\2\u0423\u0424\3\2\2\2\u0424"+
                    "\u0432\7\24\2\2\u0425\u0426\7\u0080\2\2\u0426\u0432\7P\2\2\u0427\u0428"+
                    "\7\u0080\2\2\u0428\u0429\7Q\2\2\u0429\u042a\7\u0080\2\2\u042a\u0432\7"+
                    "@\2\2\u042b\u042c\7\u0080\2\2\u042c\u042d\7R\2\2\u042d\u042e\7\u0080\2"+
                    "\2\u042e\u0432\7@\2\2\u042f\u0430\7\u0080\2\2\u0430\u0432\7S\2\2\u0431"+
                    "\u0422\3\2\2\2\u0431\u0425\3\2\2\2\u0431\u0427\3\2\2\2\u0431\u042b\3\2"+
                    "\2\2\u0431\u042f\3\2\2\2\u0432\u0434\3\2\2\2\u0433\u0435\7\u0080\2\2\u0434"+
                    "\u0433\3\2\2\2\u0434\u0435\3\2\2\2\u0435\u0436\3\2\2\2\u0436\u0442\5\u0086"+
                    "D\2\u0437\u0438\7\u0080\2\2\u0438\u0439\7T\2\2\u0439\u043a\7\u0080\2\2"+
                    "\u043a\u0442\7U\2\2\u043b\u043c\7\u0080\2\2\u043c\u043d\7T\2\2\u043d\u043e"+
                    "\7\u0080\2\2\u043e\u043f\7O\2\2\u043f\u0440\7\u0080\2\2\u0440\u0442\7"+
                    "U\2\2\u0441\u040f\3\2\2\2\u0441\u0416\3\2\2\2\u0441\u0431\3\2\2\2\u0441"+
                    "\u0437\3\2\2\2\u0441\u043b\3\2\2\2\u0442\u0445\3\2\2\2\u0443\u0441\3\2"+
                    "\2\2\u0443\u0444\3\2\2\2\u0444\u0085\3\2\2\2\u0445\u0443\3\2\2\2\u0446"+
                    "\u0450\5\u0088E\2\u0447\u0449\7\u0080\2\2\u0448\u0447\3\2\2\2\u0448\u0449"+
                    "\3\2\2\2\u0449\u044c\3\2\2\2\u044a\u044d\5\u00acW\2\u044b\u044d\5f\64"+
                    "\2\u044c\u044a\3\2\2\2\u044c\u044b\3\2\2\2\u044d\u044f\3\2\2\2\u044e\u0448"+
                    "\3\2\2\2\u044f\u0452\3\2\2\2\u0450\u044e\3\2\2\2\u0450\u0451\3\2\2\2\u0451"+
                    "\u0087\3\2\2\2\u0452\u0450\3\2\2\2\u0453\u04c5\5\u008aF\2\u0454\u04c5"+
                    "\5\u00b8]\2\u0455\u04c5\5\u00aeX\2\u0456\u0458\7V\2\2\u0457\u0459\7\u0080"+
                    "\2\2\u0458\u0457\3\2\2\2\u0458\u0459\3\2\2\2\u0459\u045a\3\2\2\2\u045a"+
                    "\u045c\7\t\2\2\u045b\u045d\7\u0080\2\2\u045c\u045b\3\2\2\2\u045c\u045d"+
                    "\3\2\2\2\u045d\u045e\3\2\2\2\u045e\u0460\7\b\2\2\u045f\u0461\7\u0080\2"+
                    "\2\u0460\u045f\3\2\2\2\u0460\u0461\3\2\2\2\u0461\u0462\3\2\2\2\u0462\u04c5"+
                    "\7\n\2\2\u0463\u04c5\5\u00a8U\2\u0464\u04c5\5\u00aaV\2\u0465\u0467\7W"+
                    "\2\2\u0466\u0468\7\u0080\2\2\u0467\u0466\3\2\2\2\u0467\u0468\3\2\2\2\u0468"+
                    "\u0469\3\2\2\2\u0469\u046b\7\t\2\2\u046a\u046c\7\u0080\2\2\u046b\u046a"+
                    "\3\2\2\2\u046b\u046c\3\2\2\2\u046c\u046d\3\2\2\2\u046d\u046f\5\u0096L"+
                    "\2\u046e\u0470\7\u0080\2\2\u046f\u046e\3\2\2\2\u046f\u0470\3\2\2\2\u0470"+
                    "\u0471\3\2\2\2\u0471\u0472\7\n\2\2\u0472\u04c5\3\2\2\2\u0473\u0475\7X"+
                    "\2\2\u0474\u0476\7\u0080\2\2\u0475\u0474\3\2\2\2\u0475\u0476\3\2\2\2\u0476"+
                    "\u0477\3\2\2\2\u0477\u0479\7\t\2\2\u0478\u047a\7\u0080\2\2\u0479\u0478"+
                    "\3\2\2\2\u0479\u047a\3\2\2\2\u047a\u047b\3\2\2\2\u047b\u047d\5\u0096L"+
                    "\2\u047c\u047e\7\u0080\2\2\u047d\u047c\3\2\2\2\u047d\u047e\3\2\2\2\u047e"+
                    "\u0484\3\2\2\2\u047f\u0481\7\u0080\2\2\u0480\u047f\3\2\2\2\u0480\u0481"+
                    "\3\2\2\2\u0481\u0482\3\2\2\2\u0482\u0483\7\16\2\2\u0483\u0485\5p9\2\u0484"+
                    "\u0480\3\2\2\2\u0484\u0485\3\2\2\2\u0485\u0486\3\2\2\2\u0486\u0487\7\n"+
                    "\2\2\u0487\u04c5\3\2\2\2\u0488\u048a\7\62\2\2\u0489\u048b\7\u0080\2\2"+
                    "\u048a\u0489\3\2\2\2\u048a\u048b\3\2\2\2\u048b\u048c\3\2\2\2\u048c\u048e"+
                    "\7\t\2\2\u048d\u048f\7\u0080\2\2\u048e\u048d\3\2\2\2\u048e\u048f\3\2\2"+
                    "\2\u048f\u0490\3\2\2\2\u0490\u0492\5\u0096L\2\u0491\u0493\7\u0080\2\2"+
                    "\u0492\u0491\3\2\2\2\u0492\u0493\3\2\2\2\u0493\u0494\3\2\2\2\u0494\u0495"+
                    "\7\n\2\2\u0495\u04c5\3\2\2\2\u0496\u0498\7Y\2\2\u0497\u0499\7\u0080\2"+
                    "\2\u0498\u0497\3\2\2\2\u0498\u0499\3\2\2\2\u0499\u049a\3\2\2\2\u049a\u049c"+
                    "\7\t\2\2\u049b\u049d\7\u0080\2\2\u049c\u049b\3\2\2\2\u049c\u049d\3\2\2"+
                    "\2\u049d\u049e\3\2\2\2\u049e\u04a0\5\u0096L\2\u049f\u04a1\7\u0080\2\2"+
                    "\u04a0\u049f\3\2\2\2\u04a0\u04a1\3\2\2\2\u04a1\u04a2\3\2\2\2\u04a2\u04a3"+
                    "\7\n\2\2\u04a3\u04c5\3\2\2\2\u04a4\u04a6\7Z\2\2\u04a5\u04a7\7\u0080\2"+
                    "\2\u04a6\u04a5\3\2\2\2\u04a6\u04a7\3\2\2\2\u04a7\u04a8\3\2\2\2\u04a8\u04aa"+
                    "\7\t\2\2\u04a9\u04ab\7\u0080\2\2\u04aa\u04a9\3\2\2\2\u04aa\u04ab\3\2\2"+
                    "\2\u04ab\u04ac\3\2\2\2\u04ac\u04ae\5\u0096L\2\u04ad\u04af\7\u0080\2\2"+
                    "\u04ae\u04ad\3\2\2\2\u04ae\u04af\3\2\2\2\u04af\u04b0\3\2\2\2\u04b0\u04b1"+
                    "\7\n\2\2\u04b1\u04c5\3\2\2\2\u04b2\u04b4\7[\2\2\u04b3\u04b5\7\u0080\2"+
                    "\2\u04b4\u04b3\3\2\2\2\u04b4\u04b5\3\2\2\2\u04b5\u04b6\3\2\2\2\u04b6\u04b8"+
                    "\7\t\2\2\u04b7\u04b9\7\u0080\2\2\u04b8\u04b7\3\2\2\2\u04b8\u04b9\3\2\2"+
                    "\2\u04b9\u04ba\3\2\2\2\u04ba\u04bc\5\u0096L\2\u04bb\u04bd\7\u0080\2\2"+
                    "\u04bc\u04bb\3\2\2\2\u04bc\u04bd\3\2\2\2\u04bd\u04be\3\2\2\2\u04be\u04bf"+
                    "\7\n\2\2\u04bf\u04c5\3\2\2\2\u04c0\u04c5\5\u0094K\2\u04c1\u04c5\5\u0092"+
                    "J\2\u04c2\u04c5\5\u009aN\2\u04c3\u04c5\5\u00b2Z\2\u04c4\u0453\3\2\2\2"+
                    "\u04c4\u0454\3\2\2\2\u04c4\u0455\3\2\2\2\u04c4\u0456\3\2\2\2\u04c4\u0463"+
                    "\3\2\2\2\u04c4\u0464\3\2\2\2\u04c4\u0465\3\2\2\2\u04c4\u0473\3\2\2\2\u04c4"+
                    "\u0488\3\2\2\2\u04c4\u0496\3\2\2\2\u04c4\u04a4\3\2\2\2\u04c4\u04b2\3\2"+
                    "\2\2\u04c4\u04c0\3\2\2\2\u04c4\u04c1\3\2\2\2\u04c4\u04c2\3\2\2\2\u04c4"+
                    "\u04c3\3\2\2\2\u04c5\u0089\3\2\2\2\u04c6\u04cd\5\u00b4[\2\u04c7\u04cd"+
                    "\7d\2\2\u04c8\u04cd\5\u008cG\2\u04c9\u04cd\7U\2\2\u04ca\u04cd\5\u00b6"+
                    "\\\2\u04cb\u04cd\5\u008eH\2\u04cc\u04c6\3\2\2\2\u04cc\u04c7\3\2\2\2\u04cc"+
                    "\u04c8\3\2\2\2\u04cc\u04c9\3\2\2\2\u04cc\u04ca\3\2\2\2\u04cc\u04cb\3\2"+
                    "\2\2\u04cd\u008b\3\2\2\2\u04ce\u04cf\t\4\2\2\u04cf\u008d\3\2\2\2\u04d0"+
                    "\u04d2\7\13\2\2\u04d1\u04d3\7\u0080\2\2\u04d2\u04d1\3\2\2\2\u04d2\u04d3"+
                    "\3\2\2\2\u04d3\u04e5\3\2\2\2\u04d4\u04d6\5p9\2\u04d5\u04d7\7\u0080\2\2"+
                    "\u04d6\u04d5\3\2\2\2\u04d6\u04d7\3\2\2\2\u04d7\u04e2\3\2\2\2\u04d8\u04da"+
                    "\7\4\2\2\u04d9\u04db\7\u0080\2\2\u04da\u04d9\3\2\2\2\u04da\u04db\3\2\2"+
                    "\2\u04db\u04dc\3\2\2\2\u04dc\u04de\5p9\2\u04dd\u04df\7\u0080\2\2\u04de"+
                    "\u04dd\3\2\2\2\u04de\u04df\3\2\2\2\u04df\u04e1\3\2\2\2\u04e0\u04d8\3\2"+
                    "\2\2\u04e1\u04e4\3\2\2\2\u04e2\u04e0\3\2\2\2\u04e2\u04e3\3\2\2\2\u04e3"+
                    "\u04e6\3\2\2\2\u04e4\u04e2\3\2\2\2\u04e5\u04d4\3\2\2\2\u04e5\u04e6\3\2"+
                    "\2\2\u04e6\u04e7\3\2\2\2\u04e7\u04e8\7\f\2\2\u04e8\u008f\3\2\2\2\u04e9"+
                    "\u04eb\7\5\2\2\u04ea\u04ec\7\u0080\2\2\u04eb\u04ea\3\2\2\2\u04eb\u04ec"+
                    "\3\2\2\2\u04ec\u04ed\3\2\2\2\u04ed\u0508\5|?\2\u04ee\u04f0\7\25\2\2\u04ef"+
                    "\u04f1\7\u0080\2\2\u04f0\u04ef\3\2\2\2\u04f0\u04f1\3\2\2\2\u04f1\u04f2"+
                    "\3\2\2\2\u04f2\u0508\5|?\2\u04f3\u04f5\7\26\2\2\u04f4\u04f6\7\u0080\2"+
                    "\2\u04f5\u04f4\3\2\2\2\u04f5\u04f6\3\2\2\2\u04f6\u04f7\3\2\2\2\u04f7\u0508"+
                    "\5|?\2\u04f8\u04fa\7\27\2\2\u04f9\u04fb\7\u0080\2\2\u04fa\u04f9\3\2\2"+
                    "\2\u04fa\u04fb\3\2\2\2\u04fb\u04fc\3\2\2\2\u04fc\u0508\5|?\2\u04fd\u04ff"+
                    "\7\30\2\2\u04fe\u0500\7\u0080\2\2\u04ff\u04fe\3\2\2\2\u04ff\u0500\3\2"+
                    "\2\2\u0500\u0501\3\2\2\2\u0501\u0508\5|?\2\u0502\u0504\7\31\2\2\u0503"+
                    "\u0505\7\u0080\2\2\u0504\u0503\3\2\2\2\u0504\u0505\3\2\2\2\u0505\u0506"+
                    "\3\2\2\2\u0506\u0508\5|?\2\u0507\u04e9\3\2\2\2\u0507\u04ee\3\2\2\2\u0507"+
                    "\u04f3\3\2\2\2\u0507\u04f8\3\2\2\2\u0507\u04fd\3\2\2\2\u0507\u0502\3\2"+
                    "\2\2\u0508\u0091\3\2\2\2\u0509\u050b\7\t\2\2\u050a\u050c\7\u0080\2\2\u050b"+
                    "\u050a\3\2\2\2\u050b\u050c\3\2\2\2\u050c\u050d\3\2\2\2\u050d\u050f\5p"+
                    "9\2\u050e\u0510\7\u0080\2\2\u050f\u050e\3\2\2\2\u050f\u0510\3\2\2\2\u0510"+
                    "\u0511\3\2\2\2\u0511\u0512\7\n\2\2\u0512\u0093\3\2\2\2\u0513\u0518\5Z"+
                    ".\2\u0514\u0516\7\u0080\2\2\u0515\u0514\3\2\2\2\u0515\u0516\3\2\2\2\u0516"+
                    "\u0517\3\2\2\2\u0517\u0519\5\\/\2\u0518\u0515\3\2\2\2\u0519\u051a\3\2"+
                    "\2\2\u051a\u0518\3\2\2\2\u051a\u051b\3\2\2\2\u051b\u0095\3\2\2\2\u051c"+
                    "\u0521\5\u0098M\2\u051d\u051f\7\u0080\2\2\u051e\u051d\3\2\2\2\u051e\u051f"+
                    "\3\2\2\2\u051f\u0520\3\2\2\2\u0520\u0522\5P)\2\u0521\u051e\3\2\2\2\u0521"+
                    "\u0522\3\2\2\2\u0522\u0097\3\2\2\2\u0523\u0524\5\u00b2Z\2\u0524\u0525"+
                    "\7\u0080\2\2\u0525\u0526\7P\2\2\u0526\u0527\7\u0080\2\2\u0527\u0528\5"+
                    "p9\2\u0528\u0099\3\2\2\2\u0529\u052b\5\u009cO\2\u052a\u052c\7\u0080\2"+
                    "\2\u052b\u052a\3\2\2\2\u052b\u052c\3\2\2\2\u052c\u052d\3\2\2\2\u052d\u052f"+
                    "\7\t\2\2\u052e\u0530\7\u0080\2\2\u052f\u052e\3\2\2\2\u052f\u0530\3\2\2"+
                    "\2\u0530\u0535\3\2\2\2\u0531\u0533\7A\2\2\u0532\u0534\7\u0080\2\2\u0533"+
                    "\u0532\3\2\2\2\u0533\u0534\3\2\2\2\u0534\u0536\3\2\2\2\u0535\u0531\3\2"+
                    "\2\2\u0535\u0536\3\2\2\2\u0536\u0548\3\2\2\2\u0537\u0539\5p9\2\u0538\u053a"+
                    "\7\u0080\2\2\u0539\u0538\3\2\2\2\u0539\u053a\3\2\2\2\u053a\u0545\3\2\2"+
                    "\2\u053b\u053d\7\4\2\2\u053c\u053e\7\u0080\2\2\u053d\u053c\3\2\2\2\u053d"+
                    "\u053e\3\2\2\2\u053e\u053f\3\2\2\2\u053f\u0541\5p9\2\u0540\u0542\7\u0080"+
                    "\2\2\u0541\u0540\3\2\2\2\u0541\u0542\3\2\2\2\u0542\u0544\3\2\2\2\u0543"+
                    "\u053b\3\2\2\2\u0544\u0547\3\2\2\2\u0545\u0543\3\2\2\2\u0545\u0546\3\2"+
                    "\2\2\u0546\u0549\3\2\2\2\u0547\u0545\3\2\2\2\u0548\u0537\3\2\2\2\u0548"+
                    "\u0549\3\2\2\2\u0549\u054a\3\2\2\2\u054a\u054b\7\n\2\2\u054b\u009b\3\2"+
                    "\2\2\u054c\u054f\5\u00c6d\2\u054d\u054f\7^\2\2\u054e\u054c\3\2\2\2\u054e"+
                    "\u054d\3\2\2\2\u054f\u009d\3\2\2\2\u0550\u0552\5\u00a4S\2\u0551\u0553"+
                    "\7\u0080\2\2\u0552\u0551\3\2\2\2\u0552\u0553\3\2\2\2\u0553\u0554\3\2\2"+
                    "\2\u0554\u0556\7\t\2\2\u0555\u0557\7\u0080\2\2\u0556\u0555\3\2\2\2\u0556"+
                    "\u0557\3\2\2\2\u0557\u0569\3\2\2\2\u0558\u055a\5p9\2\u0559\u055b\7\u0080"+
                    "\2\2\u055a\u0559\3\2\2\2\u055a\u055b\3\2\2\2\u055b\u0566\3\2\2\2\u055c"+
                    "\u055e\7\4\2\2\u055d\u055f\7\u0080\2\2\u055e\u055d\3\2\2\2\u055e\u055f"+
                    "\3\2\2\2\u055f\u0560\3\2\2\2\u0560\u0562\5p9\2\u0561\u0563\7\u0080\2\2"+
                    "\u0562\u0561\3\2\2\2\u0562\u0563\3\2\2\2\u0563\u0565\3\2\2\2\u0564\u055c"+
                    "\3\2\2\2\u0565\u0568\3\2\2\2\u0566\u0564\3\2\2\2\u0566\u0567\3\2\2\2\u0567"+
                    "\u056a\3\2\2\2\u0568\u0566\3\2\2\2\u0569\u0558\3\2\2\2\u0569\u056a\3\2"+
                    "\2\2\u056a\u056b\3\2\2\2\u056b\u056c\7\n\2\2\u056c\u009f\3\2\2\2\u056d"+
                    "\u056e\5\u00a4S\2\u056e\u00a1\3\2\2\2\u056f\u0570\5\u00c6d\2\u0570\u00a3"+
                    "\3\2\2\2\u0571\u0572\5\u00a6T\2\u0572\u0573\5\u00c6d\2\u0573\u00a5\3\2"+
                    "\2\2\u0574\u0575\5\u00c6d\2\u0575\u0576\7\32\2\2\u0576\u0578\3\2\2\2\u0577"+
                    "\u0574\3\2\2\2\u0578\u057b\3\2\2\2\u0579\u0577\3\2\2\2\u0579\u057a\3\2"+
                    "\2\2\u057a\u00a7\3\2\2\2\u057b\u0579\3\2\2\2\u057c\u057e\7\13\2\2\u057d"+
                    "\u057f\7\u0080\2\2\u057e\u057d\3\2\2\2\u057e\u057f\3\2\2\2\u057f\u0580"+
                    "\3\2\2\2\u0580\u0589\5\u0096L\2\u0581\u0583\7\u0080\2\2\u0582\u0581\3"+
                    "\2\2\2\u0582\u0583\3\2\2\2\u0583\u0584\3\2\2\2\u0584\u0586\7\16\2\2\u0585"+
                    "\u0587\7\u0080\2\2\u0586\u0585\3\2\2\2\u0586\u0587\3\2\2\2\u0587\u0588"+
                    "\3\2\2\2\u0588\u058a\5p9\2\u0589\u0582\3\2\2\2\u0589\u058a\3\2\2\2\u058a"+
                    "\u058c\3\2\2\2\u058b\u058d\7\u0080\2\2\u058c\u058b\3\2\2\2\u058c\u058d"+
                    "\3\2\2\2\u058d\u058e\3\2\2\2\u058e\u058f\7\f\2\2\u058f\u00a9\3\2\2\2\u0590"+
                    "\u0592\7\13\2\2\u0591\u0593\7\u0080\2\2\u0592\u0591\3\2\2\2\u0592\u0593"+
                    "\3\2\2\2\u0593\u059c\3\2\2\2\u0594\u0596\5\u00b2Z\2\u0595\u0597\7\u0080"+
                    "\2\2\u0596\u0595\3\2\2\2\u0596\u0597\3\2\2\2\u0597\u0598\3\2\2\2\u0598"+
                    "\u059a\7\5\2\2\u0599\u059b\7\u0080\2\2\u059a\u0599\3\2\2\2\u059a\u059b"+
                    "\3\2\2\2\u059b\u059d\3\2\2\2\u059c\u0594\3\2\2\2\u059c\u059d\3\2\2\2\u059d"+
                    "\u059e\3\2\2\2\u059e\u05a0\5\u0094K\2\u059f\u05a1\7\u0080\2\2\u05a0\u059f"+
                    "\3\2\2\2\u05a0\u05a1\3\2\2\2\u05a1\u05aa\3\2\2\2\u05a2\u05a4\7K\2\2\u05a3"+
                    "\u05a5\7\u0080\2\2\u05a4\u05a3\3\2\2\2\u05a4\u05a5\3\2\2\2\u05a5\u05a6"+
                    "\3\2\2\2\u05a6\u05a8\5p9\2\u05a7\u05a9\7\u0080\2\2\u05a8\u05a7\3\2\2\2"+
                    "\u05a8\u05a9\3\2\2\2\u05a9\u05ab\3\2\2\2\u05aa\u05a2\3\2\2\2\u05aa\u05ab"+
                    "\3\2\2\2\u05ab\u05ac\3\2\2\2\u05ac\u05ae\7\16\2\2\u05ad\u05af\7\u0080"+
                    "\2\2\u05ae\u05ad\3\2\2\2\u05ae\u05af\3\2\2\2\u05af\u05b0\3\2\2\2\u05b0"+
                    "\u05b2\5p9\2\u05b1\u05b3\7\u0080\2\2\u05b2\u05b1\3\2\2\2\u05b2\u05b3\3"+
                    "\2\2\2\u05b3\u05b4\3\2\2\2\u05b4\u05b5\7\f\2\2\u05b5\u00ab\3\2\2\2\u05b6"+
                    "\u05b8\7\32\2\2\u05b7\u05b9\7\u0080\2\2\u05b8\u05b7\3\2\2\2\u05b8\u05b9"+
                    "\3\2\2\2\u05b9\u05ba\3\2\2\2\u05ba\u05bb\5\u00bc_\2\u05bb\u00ad\3\2\2"+
                    "\2\u05bc\u05c1\7_\2\2\u05bd\u05bf\7\u0080\2\2\u05be\u05bd\3\2\2\2\u05be"+
                    "\u05bf\3\2\2\2\u05bf\u05c0\3\2\2\2\u05c0\u05c2\5\u00b0Y\2\u05c1\u05be"+
                    "\3\2\2\2\u05c2\u05c3\3\2\2\2\u05c3\u05c1\3\2\2\2\u05c3\u05c4\3\2\2\2\u05c4"+
                    "\u05d3\3\2\2\2\u05c5\u05c7\7_\2\2\u05c6\u05c8\7\u0080\2\2\u05c7\u05c6"+
                    "\3\2\2\2\u05c7\u05c8\3\2\2\2\u05c8\u05c9\3\2\2\2\u05c9\u05ce\5p9\2\u05ca"+
                    "\u05cc\7\u0080\2\2\u05cb\u05ca\3\2\2\2\u05cb\u05cc\3\2\2\2\u05cc\u05cd"+
                    "\3\2\2\2\u05cd\u05cf\5\u00b0Y\2\u05ce\u05cb\3\2\2\2\u05cf\u05d0\3\2\2"+
                    "\2\u05d0\u05ce\3\2\2\2\u05d0\u05d1\3\2\2\2\u05d1\u05d3\3\2\2\2\u05d2\u05bc"+
                    "\3\2\2\2\u05d2\u05c5\3\2\2\2\u05d3\u05dc\3\2\2\2\u05d4\u05d6\7\u0080\2"+
                    "\2\u05d5\u05d4\3\2\2\2\u05d5\u05d6\3\2\2\2\u05d6\u05d7\3\2\2\2\u05d7\u05d9"+
                    "\7`\2\2\u05d8\u05da\7\u0080\2\2\u05d9\u05d8\3\2\2\2\u05d9\u05da\3\2\2"+
                    "\2\u05da\u05db\3\2\2\2\u05db\u05dd\5p9\2\u05dc\u05d5\3\2\2\2\u05dc\u05dd"+
                    "\3\2\2\2\u05dd\u05df\3\2\2\2\u05de\u05e0\7\u0080\2\2\u05df\u05de\3\2\2"+
                    "\2\u05df\u05e0\3\2\2\2\u05e0\u05e1\3\2\2\2\u05e1\u05e2\7a\2\2\u05e2\u00af"+
                    "\3\2\2\2\u05e3\u05e5\7b\2\2\u05e4\u05e6\7\u0080\2\2\u05e5\u05e4\3\2\2"+
                    "\2\u05e5\u05e6\3\2\2\2\u05e6\u05e7\3\2\2\2\u05e7\u05e9\5p9\2\u05e8\u05ea"+
                    "\7\u0080\2\2\u05e9\u05e8\3\2\2\2\u05e9\u05ea\3\2\2\2\u05ea\u05eb\3\2\2"+
                    "\2\u05eb\u05ed\7c\2\2\u05ec\u05ee\7\u0080\2\2\u05ed\u05ec\3\2\2\2\u05ed"+
                    "\u05ee\3\2\2\2\u05ee\u05ef\3\2\2\2\u05ef\u05f0\5p9\2\u05f0\u00b1\3\2\2"+
                    "\2\u05f1\u05f2\5\u00c6d\2\u05f2\u00b3\3\2\2\2\u05f3\u05f6\5\u00c0a\2\u05f4"+
                    "\u05f6\5\u00be`\2\u05f5\u05f3\3\2\2\2\u05f5\u05f4\3\2\2\2\u05f6\u00b5"+
                    "\3\2\2\2\u05f7\u05f9\7\33\2\2\u05f8\u05fa\7\u0080\2\2\u05f9\u05f8\3\2"+
                    "\2\2\u05f9\u05fa\3\2\2\2\u05fa\u061c\3\2\2\2\u05fb\u05fd\5\u00bc_\2\u05fc"+
                    "\u05fe\7\u0080\2\2\u05fd\u05fc\3\2\2\2\u05fd\u05fe\3\2\2\2\u05fe\u05ff"+
                    "\3\2\2\2\u05ff\u0601\7\r\2\2\u0600\u0602\7\u0080\2\2\u0601\u0600\3\2\2"+
                    "\2\u0601\u0602\3\2\2\2\u0602\u0603\3\2\2\2\u0603\u0605\5p9\2\u0604\u0606"+
                    "\7\u0080\2\2\u0605\u0604\3\2\2\2\u0605\u0606\3\2\2\2\u0606\u0619\3\2\2"+
                    "\2\u0607\u0609\7\4\2\2\u0608\u060a\7\u0080\2\2\u0609\u0608\3\2\2\2\u0609"+
                    "\u060a\3\2\2\2\u060a\u060b\3\2\2\2\u060b\u060d\5\u00bc_\2\u060c\u060e"+
                    "\7\u0080\2\2\u060d\u060c\3\2\2\2\u060d\u060e\3\2\2\2\u060e\u060f\3\2\2"+
                    "\2\u060f\u0611\7\r\2\2\u0610\u0612\7\u0080\2\2\u0611\u0610\3\2\2\2\u0611"+
                    "\u0612\3\2\2\2\u0612\u0613\3\2\2\2\u0613\u0615\5p9\2\u0614\u0616\7\u0080"+
                    "\2\2\u0615\u0614\3\2\2\2\u0615\u0616\3\2\2\2\u0616\u0618\3\2\2\2\u0617"+
                    "\u0607\3\2\2\2\u0618\u061b\3\2\2\2\u0619\u0617\3\2\2\2\u0619\u061a\3\2"+
                    "\2\2\u061a\u061d\3\2\2\2\u061b\u0619\3\2\2\2\u061c\u05fb\3\2\2\2\u061c"+
                    "\u061d\3\2\2\2\u061d\u061e\3\2\2\2\u061e\u061f\7\34\2\2\u061f\u00b7\3"+
                    "\2\2\2\u0620\u0623\7\35\2\2\u0621\u0624\5\u00c6d\2\u0622\u0624\7g\2\2"+
                    "\u0623\u0621\3\2\2\2\u0623\u0622\3\2\2\2\u0624\u00b9\3\2\2\2\u0625\u062a"+
                    "\5\u0088E\2\u0626\u0628\7\u0080\2\2\u0627\u0626\3\2\2\2\u0627\u0628\3"+
                    "\2\2\2\u0628\u0629\3\2\2\2\u0629\u062b\5\u00acW\2\u062a\u0627\3\2\2\2"+
                    "\u062b\u062c\3\2\2\2\u062c\u062a\3\2\2\2\u062c\u062d\3\2\2\2\u062d\u00bb"+
                    "\3\2\2\2\u062e\u062f\5\u00c2b\2\u062f\u00bd\3\2\2\2\u0630\u0631\t\5\2"+
                    "\2\u0631\u00bf\3\2\2\2\u0632\u0633\t\6\2\2\u0633\u00c1\3\2\2\2\u0634\u0637"+
                    "\5\u00c6d\2\u0635\u0637\5\u00c4c\2\u0636\u0634\3\2\2\2\u0636\u0635\3\2"+
                    "\2\2\u0637\u00c3\3\2\2\2\u0638\u0639\t\7\2\2\u0639\u00c5\3\2\2\2\u063a"+
                    "\u063b\t\b\2\2\u063b\u00c7\3\2\2\2\u063c\u063d\t\t\2\2\u063d\u00c9\3\2"+
                    "\2\2\u063e\u063f\t\n\2\2\u063f\u00cb\3\2\2\2\u0640\u0641\t\13\2\2\u0641"+
                    "\u00cd\3\2\2\2\u0128\u00cf\u00d3\u00d6\u00d9\u00e1\u00e5\u00ea\u00f1\u00f6"+
                    "\u00f9\u00fd\u0102\u0109\u010e\u0112\u0117\u011a\u011d\u0121\u0126\u012a"+
                    "\u012d\u0132\u0136\u013a\u0140\u0144\u014b\u014f\u0154\u0158\u015d\u0164"+
                    "\u0169\u016d\u0171\u0175\u0178\u017c\u0186\u018d\u019a\u019e\u01a4\u01ab"+
                    "\u01b0\u01b4\u01ba\u01be\u01c4\u01c8\u01ce\u01d2\u01d6\u01da\u01de\u01e2"+
                    "\u01e7\u01ee\u01f2\u01f7\u01fe\u0204\u0209\u020f\u0215\u0219\u021d\u0222"+
                    "\u0226\u022d\u0233\u0236\u023b\u023e\u0242\u0245\u024d\u0251\u0255\u0259"+
                    "\u025d\u0262\u0267\u026b\u0270\u0273\u027c\u0285\u028a\u0297\u029a\u02a2"+
                    "\u02a6\u02ab\u02b0\u02b4\u02b9\u02bf\u02c4\u02cb\u02cf\u02d3\u02d5\u02d9"+
                    "\u02db\u02df\u02e1\u02e7\u02ed\u02f1\u02f4\u02f7\u02fb\u0301\u0305\u0308"+
                    "\u030b\u0311\u0314\u0317\u031b\u0321\u0324\u0327\u032b\u032f\u0333\u0335"+
                    "\u0339\u033b\u033e\u0342\u0344\u034a\u034e\u0352\u0356\u0359\u035e\u0363"+
                    "\u0368\u036d\u0373\u0377\u0379\u037d\u0381\u0383\u0385\u0394\u039e\u03a8"+
                    "\u03ad\u03b1\u03b8\u03bd\u03c2\u03c6\u03ca\u03ce\u03d1\u03d3\u03d8\u03dc"+
                    "\u03e0\u03e4\u03e8\u03ec\u03ef\u03f1\u03f6\u03fa\u03ff\u0404\u0408\u040f"+
                    "\u0416\u041a\u041e\u0422\u0431\u0434\u0441\u0443\u0448\u044c\u0450\u0458"+
                    "\u045c\u0460\u0467\u046b\u046f\u0475\u0479\u047d\u0480\u0484\u048a\u048e"+
                    "\u0492\u0498\u049c\u04a0\u04a6\u04aa\u04ae\u04b4\u04b8\u04bc\u04c4\u04cc"+
                    "\u04d2\u04d6\u04da\u04de\u04e2\u04e5\u04eb\u04f0\u04f5\u04fa\u04ff\u0504"+
                    "\u0507\u050b\u050f\u0515\u051a\u051e\u0521\u052b\u052f\u0533\u0535\u0539"+
                    "\u053d\u0541\u0545\u0548\u054e\u0552\u0556\u055a\u055e\u0562\u0566\u0569"+
                    "\u0579\u057e\u0582\u0586\u0589\u058c\u0592\u0596\u059a\u059c\u05a0\u05a4"+
                    "\u05a8\u05aa\u05ae\u05b2\u05b8\u05be\u05c3\u05c7\u05cb\u05d0\u05d2\u05d5"+
                    "\u05d9\u05dc\u05df\u05e5\u05e9\u05ed\u05f5\u05f9\u05fd\u0601\u0605\u0609"+
                    "\u060d\u0611\u0615\u0619\u061c\u0623\u0627\u062c\u0636";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}