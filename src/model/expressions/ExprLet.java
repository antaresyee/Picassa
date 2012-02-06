package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprLet extends Expression {
    // let statement looks like: (let userCommandName userExpression
    // myExpression)
    public static final Pattern USER_COMMANDNAME_PATTERN = Pattern
            .compile("[a-zA-Z]+");
    static Pattern myPattern = Pattern.compile("\\((let)");

    public ExprLet() {
        super(myPattern);
    }

    /**
     * This method is a "refused bequest."
     */
    public RGBColor evaluate(double x, double y, double currentTime) {
        throw new ParserException(
                "Called evaluate on an ExprLet instance. Not allowed.");
    }

    public Expression parseExpression(ParserState ps) {
        // parse "(let"
        ps.skipWhiteSpace();
        Matcher expMatcher = myRegexPattern.matcher(ps.getInput());
        expMatcher.find(ps.getCurrentPosition());
        ps.setPosition(expMatcher.end());
        ps.skipWhiteSpace();

        // parse userCommandName
        expMatcher = USER_COMMANDNAME_PATTERN.matcher(ps.getInput());
        expMatcher.find(ps.getCurrentPosition());
        String userCommandName = ps.getInput().substring(expMatcher.start(),
                expMatcher.end());
        ps.setPosition(expMatcher.end());
        ps.skipWhiteSpace();

        // parse tempOperand
        Expression userExpression = Parser.parseInput(ps);
        ps.skipWhiteSpace();

        ps.setExprTemp(new ExprTemp(userCommandName, userExpression));

        // parse myExpression
        Expression myExpression = Parser.parseInput(ps);
        ps.skipWhiteSpace();

        if (ps.currentCharacter() == ')') {
            ps.setPosition(ps.getCurrentPosition() + 1);
            ps.removeExpr(userCommandName);
            return myExpression;
        } else {
            throw new ParserException("Expected close paren, instead found "
                    + ps.getInput().substring(ps.getCurrentPosition()));
        }
    }

    /**
     * Returns string representation of expression
     */
    public String toString() {
        return "(let)";
    }

}
