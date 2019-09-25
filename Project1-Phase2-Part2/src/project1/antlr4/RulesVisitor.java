// Generated from C:/Users/laure/IdeaProjects/untitled1/src/project1\Rules.g4 by ANTLR 4.7.2
package project1.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RulesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RulesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RulesParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(RulesParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(RulesParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#string_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_literal(RulesParser.String_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(RulesParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#relation_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation_name(RulesParser.Relation_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#attribute_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_name(RulesParser.Attribute_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#operand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand(RulesParser.OperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(RulesParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#attribute_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_list(RulesParser.Attribute_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#typed_attribute_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyped_attribute_list(RulesParser.Typed_attribute_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#open_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpen_cmd(RulesParser.Open_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#close_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClose_cmd(RulesParser.Close_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#write_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite_cmd(RulesParser.Write_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#exit_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit_cmd(RulesParser.Exit_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(RulesParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#conjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConjunction(RulesParser.ConjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(RulesParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RulesParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#atomic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic_expr(RulesParser.Atomic_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#selection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection(RulesParser.SelectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#projection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjection(RulesParser.ProjectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#renaming}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenaming(RulesParser.RenamingContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#union}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnion(RulesParser.UnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#difference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDifference(RulesParser.DifferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#product}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduct(RulesParser.ProductContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#natural_join}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNatural_join(RulesParser.Natural_joinContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#show_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_cmd(RulesParser.Show_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#create_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_cmd(RulesParser.Create_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#update_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_cmd(RulesParser.Update_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#insert_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_cmd(RulesParser.Insert_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#delete_cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_cmd(RulesParser.Delete_cmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(RulesParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(RulesParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(RulesParser.ProgramContext ctx);
}