package com.gorb.texthandling.interpreter;

import com.gorb.texthandling.exception.TextException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class ExpressionInterpreterTest {
    @DataProvider(name = "data")
    public Object[][] createData() {
        return new Object[][]{
                {0, "(3|2)&4"},
                {-360, "~2|5|352"},
                {487, "(200^300)|3"}
        };
    }

    @Test(dataProvider = "data")
    public void calculate(Integer expected, String expression) throws TextException {
        ExpressionInterpreter interpreter = new ExpressionInterpreter(expression);
        Integer res = interpreter.calculate();
        assertEquals(expected, res);
    }
}
