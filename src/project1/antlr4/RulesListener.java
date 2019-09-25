// Generated from C:/Users/laure/IdeaProjects/untitled1/src/project1\Rules.g4 by ANTLR 4.7.2
package project1.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RulesParser}.
 */
public interface RulesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RulesParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(RulesParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(RulesParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(RulesParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(RulesParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void enterString_literal(RulesParser.String_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void exitString_literal(RulesParser.String_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(RulesParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(RulesParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#relation_name}.
	 * @param ctx the parse tree
	 */
	void enterRelation_name(RulesParser.Relation_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#relation_name}.
	 * @param ctx the parse tree
	 */
	void exitRelation_name(RulesParser.Relation_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_name(RulesParser.Attribute_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_name(RulesParser.Attribute_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(RulesParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(RulesParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(RulesParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(RulesParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#attribute_list}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_list(RulesParser.Attribute_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#attribute_list}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_list(RulesParser.Attribute_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#typed_attribute_list}.
	 * @param ctx the parse tree
	 */
	void enterTyped_attribute_list(RulesParser.Typed_attribute_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#typed_attribute_list}.
	 * @param ctx the parse tree
	 */
	void exitTyped_attribute_list(RulesParser.Typed_attribute_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#open_cmd}.
	 * @param ctx the parse tree
	 */
	void enterOpen_cmd(RulesParser.Open_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#open_cmd}.
	 * @param ctx the parse tree
	 */
	void exitOpen_cmd(RulesParser.Open_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#close_cmd}.
	 * @param ctx the parse tree
	 */
	void enterClose_cmd(RulesParser.Close_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#close_cmd}.
	 * @param ctx the parse tree
	 */
	void exitClose_cmd(RulesParser.Close_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#write_cmd}.
	 * @param ctx the parse tree
	 */
	void enterWrite_cmd(RulesParser.Write_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#write_cmd}.
	 * @param ctx the parse tree
	 */
	void exitWrite_cmd(RulesParser.Write_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#exit_cmd}.
	 * @param ctx the parse tree
	 */
	void enterExit_cmd(RulesParser.Exit_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#exit_cmd}.
	 * @param ctx the parse tree
	 */
	void exitExit_cmd(RulesParser.Exit_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(RulesParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(RulesParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(RulesParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(RulesParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(RulesParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(RulesParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(RulesParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(RulesParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#atomic_expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_expr(RulesParser.Atomic_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#atomic_expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_expr(RulesParser.Atomic_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#selection}.
	 * @param ctx the parse tree
	 */
	void enterSelection(RulesParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#selection}.
	 * @param ctx the parse tree
	 */
	void exitSelection(RulesParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#projection}.
	 * @param ctx the parse tree
	 */
	void enterProjection(RulesParser.ProjectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#projection}.
	 * @param ctx the parse tree
	 */
	void exitProjection(RulesParser.ProjectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#renaming}.
	 * @param ctx the parse tree
	 */
	void enterRenaming(RulesParser.RenamingContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#renaming}.
	 * @param ctx the parse tree
	 */
	void exitRenaming(RulesParser.RenamingContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(RulesParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(RulesParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#difference}.
	 * @param ctx the parse tree
	 */
	void enterDifference(RulesParser.DifferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#difference}.
	 * @param ctx the parse tree
	 */
	void exitDifference(RulesParser.DifferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(RulesParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(RulesParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#natural_join}.
	 * @param ctx the parse tree
	 */
	void enterNatural_join(RulesParser.Natural_joinContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#natural_join}.
	 * @param ctx the parse tree
	 */
	void exitNatural_join(RulesParser.Natural_joinContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#show_cmd}.
	 * @param ctx the parse tree
	 */
	void enterShow_cmd(RulesParser.Show_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#show_cmd}.
	 * @param ctx the parse tree
	 */
	void exitShow_cmd(RulesParser.Show_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#create_cmd}.
	 * @param ctx the parse tree
	 */
	void enterCreate_cmd(RulesParser.Create_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#create_cmd}.
	 * @param ctx the parse tree
	 */
	void exitCreate_cmd(RulesParser.Create_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#update_cmd}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_cmd(RulesParser.Update_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#update_cmd}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_cmd(RulesParser.Update_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#insert_cmd}.
	 * @param ctx the parse tree
	 */
	void enterInsert_cmd(RulesParser.Insert_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#insert_cmd}.
	 * @param ctx the parse tree
	 */
	void exitInsert_cmd(RulesParser.Insert_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#delete_cmd}.
	 * @param ctx the parse tree
	 */
	void enterDelete_cmd(RulesParser.Delete_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#delete_cmd}.
	 * @param ctx the parse tree
	 */
	void exitDelete_cmd(RulesParser.Delete_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(RulesParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(RulesParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(RulesParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(RulesParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(RulesParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(RulesParser.ProgramContext ctx);
}