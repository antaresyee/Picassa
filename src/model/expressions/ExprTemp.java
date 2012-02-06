package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;
import model.Parser.ParserState;

public class ExprTemp extends Expression {
    String myUserCommandName;
    Expression myUserExpression;
    
    public ExprTemp(String userCommandName, Expression userExpression) {
        super(Pattern.compile(userCommandName));
        myUserCommandName = userCommandName;
        myUserExpression = userExpression;
    }
    
    public RGBColor evaluate(double x, double y, double currentTime) {
        return myUserExpression.evaluate(x,y, currentTime);
    }

    public Expression parseExpression(ParserState ps) {
        //match myUserCommandName and update position. 
        Matcher expMatcher = myRegexPattern.matcher(ps.getInput());
        expMatcher.find(ps.getCurrentPosition());
        ps.setPosition(expMatcher.end());
        ps.skipWhiteSpace();
        
        return new ExprTemp(myUserCommandName, myUserExpression);
    }

    public String toString() {
        return myUserCommandName;
    }

}
