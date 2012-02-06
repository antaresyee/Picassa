package tests;

import java.awt.Color;
import model.Parser;
import model.ParserException;
import model.RGBColor;
import static org.junit.Assert.*;
import org.junit.*;


public class ParserTest
{
    // useful constants
    private static final RGBColor BLACK = new RGBColor(Color.BLACK);
    private static final RGBColor GRAY = new RGBColor(0.5);
    private static final RGBColor WHITE = new RGBColor(Color.WHITE);

    
    private Parser myParser = new Parser();


    @Before
    public void setUp ()
    {
        // initialize stuff here
    }


    @Test
    public void testConstant ()
    {
        RGBColor actual = myParser.makeExpression("1").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("-1").evaluate(0,0,0);
        assertTrue(BLACK.equals(actual));
        actual = myParser.makeExpression("0.5").evaluate(0,0,0);
        assertTrue(GRAY.equals(actual));
        actual = myParser.makeExpression(".5").evaluate(0,0,0);
        assertTrue(GRAY.equals(actual));
        try
        {
            myParser.makeExpression("0.5 0.5").evaluate(0,0,0);
            fail("Exception should have been thrown");
        }
        catch (ParserException e)
        {
            // actually that's good
            assertEquals(ParserException.Type.EXTRA_CHARACTERS, e.getType());
        }
        assertTrue(GRAY.equals(actual));
    }


    @Test
    public void testBinaryOps ()
    {
        RGBColor actual = myParser.makeExpression("(plus .1 .9)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(plus (plus 0.01 0.09) (plus 0.4 0.5))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("   (plus (plus 0.01 0.09)   (plus 0.4 0.5  )   )").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(minus (div 1.8 2) (mul -10 0.01))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(mod (mul 6 0.5) (plus 1 1))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(exp (mul 2 1) (plus -1 1))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(neg (mul -1 1))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(color .75 .32 (mul -1 1))").evaluate(0,0,0);
        assertTrue(new RGBColor(.75, .32, -1).equals(actual));
        actual = myParser.makeExpression("(floor 1.2)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(ceil 0.5)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(abs -1)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(wrap 1.75)").evaluate(0,0,0);
        assertTrue(new RGBColor(-0.25).equals(actual));
        actual = myParser.makeExpression("(wrap 4.75)").evaluate(0,0,0);
        assertTrue(new RGBColor(0.75).equals(actual));
        actual = myParser.makeExpression("(wrap 2.75)").evaluate(0,0,0);
        assertTrue(new RGBColor(0.75).equals(actual));
        actual = myParser.makeExpression("(wrap -1.75)").evaluate(0,0,0);
        assertTrue(new RGBColor(0.25).equals(actual));
        actual = myParser.makeExpression("(wrap -2.75)").evaluate(0,0,0);
        assertTrue(new RGBColor(-0.75).equals(actual));
        actual = myParser.makeExpression("(sum 0 1 -1 (mul 0 1) 1)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(product 1 -1 (sum 1 -2))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(average 3 1 (neg 0) 0)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(min 5 (mul .25 4) 2.42)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(max 1 -0.03 (minus 5 6))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(+ .1 .9)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(- (/ 1.8 2) (* -10 0.01))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(% (mul 6 0.5) (plus 1 1))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(^ (mul 2 1) (plus -1 1))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(! (mul -1 1))").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(if 0.3 (mul 1 1) -1)").evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        
        
        try
        {
            myParser.makeExpression("(fooo 0.1 0.9)").evaluate(0,0,0);
            fail("Exception should have been thrown");
        }
        catch (ParserException e)
        {
            // actually that's good
            assertEquals(ParserException.Type.UNKNOWN_COMMAND, e.getType());
        }
    }
}
