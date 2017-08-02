package parsing_lexing;// Generated from Cypher.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CypherParser}.
 */
public interface CypherListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void enterCypher(CypherParser.CypherContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void exitCypher(CypherParser.CypherContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CypherParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CypherParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(CypherParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(CypherParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void enterRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void exitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(CypherParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(CypherParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void enterSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void exitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singlePartQuery}.
	 * @param ctx the parse tree
	 */
	void enterSinglePartQuery(CypherParser.SinglePartQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singlePartQuery}.
	 * @param ctx the parse tree
	 */
	void exitSinglePartQuery(CypherParser.SinglePartQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#readOnlyEnd}.
	 * @param ctx the parse tree
	 */
	void enterReadOnlyEnd(CypherParser.ReadOnlyEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#readOnlyEnd}.
	 * @param ctx the parse tree
	 */
	void exitReadOnlyEnd(CypherParser.ReadOnlyEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#readUpdateEnd}.
	 * @param ctx the parse tree
	 */
	void enterReadUpdateEnd(CypherParser.ReadUpdateEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#readUpdateEnd}.
	 * @param ctx the parse tree
	 */
	void exitReadUpdateEnd(CypherParser.ReadUpdateEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#updatingEnd}.
	 * @param ctx the parse tree
	 */
	void enterUpdatingEnd(CypherParser.UpdatingEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#updatingEnd}.
	 * @param ctx the parse tree
	 */
	void exitUpdatingEnd(CypherParser.UpdatingEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#multiPartQuery}.
	 * @param ctx the parse tree
	 */
	void enterMultiPartQuery(CypherParser.MultiPartQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#multiPartQuery}.
	 * @param ctx the parse tree
	 */
	void exitMultiPartQuery(CypherParser.MultiPartQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#readPart}.
	 * @param ctx the parse tree
	 */
	void enterReadPart(CypherParser.ReadPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#readPart}.
	 * @param ctx the parse tree
	 */
	void exitReadPart(CypherParser.ReadPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#updatingPart}.
	 * @param ctx the parse tree
	 */
	void enterUpdatingPart(CypherParser.UpdatingPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#updatingPart}.
	 * @param ctx the parse tree
	 */
	void exitUpdatingPart(CypherParser.UpdatingPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#updatingStartClause}.
	 * @param ctx the parse tree
	 */
	void enterUpdatingStartClause(CypherParser.UpdatingStartClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#updatingStartClause}.
	 * @param ctx the parse tree
	 */
	void exitUpdatingStartClause(CypherParser.UpdatingStartClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#updatingClause}.
	 * @param ctx the parse tree
	 */
	void enterUpdatingClause(CypherParser.UpdatingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#updatingClause}.
	 * @param ctx the parse tree
	 */
	void exitUpdatingClause(CypherParser.UpdatingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#readingClause}.
	 * @param ctx the parse tree
	 */
	void enterReadingClause(CypherParser.ReadingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#readingClause}.
	 * @param ctx the parse tree
	 */
	void exitReadingClause(CypherParser.ReadingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void enterMatch(CypherParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void exitMatch(CypherParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 */
	void enterUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 */
	void exitUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 */
	void enterMerge(CypherParser.MergeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 */
	void exitMerge(CypherParser.MergeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void enterMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void exitMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(CypherParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(CypherParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(CypherParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(CypherParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void enterSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void exitSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(CypherParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(CypherParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 */
	void enterRemove(CypherParser.RemoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 */
	void exitRemove(CypherParser.RemoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void enterRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void exitRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#inQueryCall}.
	 * @param ctx the parse tree
	 */
	void enterInQueryCall(CypherParser.InQueryCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#inQueryCall}.
	 * @param ctx the parse tree
	 */
	void exitInQueryCall(CypherParser.InQueryCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#standaloneCall}.
	 * @param ctx the parse tree
	 */
	void enterStandaloneCall(CypherParser.StandaloneCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#standaloneCall}.
	 * @param ctx the parse tree
	 */
	void exitStandaloneCall(CypherParser.StandaloneCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 */
	void enterYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 */
	void exitYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 */
	void enterYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 */
	void exitYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(CypherParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(CypherParser.WithContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnX}.
	 * @param ctx the parse tree
	 */
	void enterReturnX(CypherParser.ReturnXContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnX}.
	 * @param ctx the parse tree
	 */
	void exitReturnX(CypherParser.ReturnXContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void enterReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void exitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void enterReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void exitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void enterReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void exitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void enterOrder(CypherParser.OrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void exitOrder(CypherParser.OrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void enterSkip(CypherParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void exitSkip(CypherParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void enterLimit(CypherParser.LimitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void exitLimit(CypherParser.LimitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void enterSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void exitSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(CypherParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(CypherParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(CypherParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(CypherParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void enterPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void exitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#anonymousPatternPart}.
	 * @param ctx the parse tree
	 */
	void enterAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#anonymousPatternPart}.
	 * @param ctx the parse tree
	 */
	void exitAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void enterPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void exitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void enterNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void exitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void enterPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void exitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void enterLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void exitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void enterRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void exitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(CypherParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(CypherParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void enterXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void exitXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#addOrSubtractExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddOrSubtractExpression(CypherParser.AddOrSubtractExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#addOrSubtractExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddOrSubtractExpression(CypherParser.AddOrSubtractExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#multiplyDivideModuloExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyDivideModuloExpression(CypherParser.MultiplyDivideModuloExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#multiplyDivideModuloExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyDivideModuloExpression(CypherParser.MultiplyDivideModuloExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#powerOfExpression}.
	 * @param ctx the parse tree
	 */
	void enterPowerOfExpression(CypherParser.PowerOfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#powerOfExpression}.
	 * @param ctx the parse tree
	 */
	void exitPowerOfExpression(CypherParser.PowerOfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unaryAddOrSubtractExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryAddOrSubtractExpression(CypherParser.UnaryAddOrSubtractExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unaryAddOrSubtractExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryAddOrSubtractExpression(CypherParser.UnaryAddOrSubtractExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#stringListNullOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringListNullOperatorExpression(CypherParser.StringListNullOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#stringListNullOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringListNullOperatorExpression(CypherParser.StringListNullOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyOrLabelsExpression}.
	 * @param ctx the parse tree
	 */
	void enterPropertyOrLabelsExpression(CypherParser.PropertyOrLabelsExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyOrLabelsExpression}.
	 * @param ctx the parse tree
	 */
	void exitPropertyOrLabelsExpression(CypherParser.PropertyOrLabelsExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CypherParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CypherParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void enterListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void exitListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 */
	void enterIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 */
	void exitIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#explicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitProcedureInvocation(CypherParser.ExplicitProcedureInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#explicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitProcedureInvocation(CypherParser.ExplicitProcedureInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#implicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void enterImplicitProcedureInvocation(CypherParser.ImplicitProcedureInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#implicitProcedureInvocation}.
	 * @param ctx the parse tree
	 */
	void exitImplicitProcedureInvocation(CypherParser.ImplicitProcedureInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#procedureResultField}.
	 * @param ctx the parse tree
	 */
	void enterProcedureResultField(CypherParser.ProcedureResultFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#procedureResultField}.
	 * @param ctx the parse tree
	 */
	void exitProcedureResultField(CypherParser.ProcedureResultFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#procedureName}.
	 * @param ctx the parse tree
	 */
	void enterProcedureName(CypherParser.ProcedureNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#procedureName}.
	 * @param ctx the parse tree
	 */
	void exitProcedureName(CypherParser.ProcedureNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(CypherParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(CypherParser.NamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void enterListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void exitListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 */
	void enterPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 */
	void exitPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void enterPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void exitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void enterCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void exitCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(CypherParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(CypherParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void enterMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void exitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(CypherParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void enterPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void exitPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void enterSchemaName(CypherParser.SchemaNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void exitSchemaName(CypherParser.SchemaNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void enterReservedWord(CypherParser.ReservedWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void exitReservedWord(CypherParser.ReservedWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void enterSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void exitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void enterDash(CypherParser.DashContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void exitDash(CypherParser.DashContext ctx);
}