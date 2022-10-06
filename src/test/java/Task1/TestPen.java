package Task1;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class TestPen {

    int testPositiveInkContainerValue = 10;
    int testNegativeInkContainerValue = -1;
    double testSizeLetter = 2.0;
    String testColor = "RED";

    //Data for 'writeMethodTest'
    @DataProvider(name = "dataForWriteMethod")
    public Object[][] input() {
        return new Object[][]{
                {-1, 2.0, ""},
                {10, 1.8, "horse"},
                {10, 2.4, "hors"},
                {10, 2.0, "horse"},
                {5, 2.0, "ho"},
        };
    }

    @Test(description = "Test of 'isWork' method with positive amount of ink")
    public void isWorkMethodPositiveTest() {
        Pen penWithPositiveInk = new Pen(testPositiveInkContainerValue);
        Assert.assertTrue(penWithPositiveInk.isWork());
    }

    @Test(description = "Test of 'isWork' method with negative amount of ink")
    public void isWorkMethodNegativeTest() {
        Pen penWithNegativeInk = new Pen(testNegativeInkContainerValue);
        Assert.assertFalse(penWithNegativeInk.isWork());
    }

    @Test(description = "Test for a correct work of constructor with 'inkContainerValue' parameter")
    public void testForConstructorWithInkParameter() throws NoSuchFieldException, IllegalAccessException {
        Pen penWithEstablishedInk = new Pen(testPositiveInkContainerValue);
        Field inkField = Pen.class.getDeclaredField("inkContainerValue");
        inkField.setAccessible(true);
        int valueOfTheField = (int) inkField.get(penWithEstablishedInk);
        Assert.assertEquals(valueOfTheField, testPositiveInkContainerValue);
    }

    @Test(description = "Test for a correct work of constructor with 'sizeLetter' parameter")
    public void testForConstructorWithSizeParameter() throws NoSuchFieldException, IllegalAccessException {
        Pen penWithEstablishedSizeLetter = new Pen(testPositiveInkContainerValue, testSizeLetter);
        Field sizeField = Pen.class.getDeclaredField("sizeLetter");
        sizeField.setAccessible(true);
        double valueOfTheField = (double) sizeField.get(penWithEstablishedSizeLetter);
        Assert.assertEquals(valueOfTheField, testSizeLetter);
    }

    @Test(description = "Test for a correct work of constructor with 'color' parameter")
    public void testForConstructorWithColorParameter() throws NoSuchFieldException, IllegalAccessException {
        Pen penWithEstablishedColor = new Pen(testPositiveInkContainerValue, testSizeLetter, testColor);
        Field colorField = Pen.class.getDeclaredField("color");
        colorField.setAccessible(true);
        String valueOfTheField = (String) colorField.get(penWithEstablishedColor);
        Assert.assertEquals(valueOfTheField, testColor);
    }

    @Test(description = "Test for a correct work of 'doSomethingElse' method")
    public void doSomethingElseMethodTest() throws NoSuchFieldException, IllegalAccessException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        Pen pen = new Pen(testPositiveInkContainerValue);
        Field colorField = Pen.class.getDeclaredField("color");
        colorField.setAccessible(true);
        colorField.set(pen, testColor);
        pen.doSomethingElse();
        Assert.assertEquals(testColor, byteArrayOutputStream.toString().trim());
        System.setOut(null);
    }

    // BUG - 'getColor' method doesn't return current value of 'color' parameter
    @Test(description = "Test for a correct work of 'getColor' method")
    public void getColorMethodTest() {
        Pen penWithEstablishedColor = new Pen(testPositiveInkContainerValue, testSizeLetter, testColor);
        Assert.assertEquals(penWithEstablishedColor.getColor(), testColor);
    }

    // BUG - incorrect work of 'write' method when the product of the length of the word and 'sizeLetter' is more than 'inkContainerValue':
    // 1) when the 'inkContainerValue' is less or equal than the length of the word - method returns the incorrect value;
    // 2) when the 'inkContainerValue' is more than the length of the word - IndexOutOfBoundsException.
    @Test(description = "Test for a correct work of 'write' method", dataProvider = "dataForWriteMethod")
    public void writeMethodTest(int inkContainerValue, double sizeLetter, String expectedResult) {
        Assert.assertEquals(new Pen(inkContainerValue, sizeLetter).write("horse"), expectedResult);
    }
}

