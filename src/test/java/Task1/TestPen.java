package Task1;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class TestPen {

    @DataProvider(name = "positiveDataForWriteMethod")
    public Object[][] inputPositive() {
        return new Object[][]{
                {10, 1.8,"horse", "horse"},
                {10, 2.4,"horse", "hors"},      //bug
                {10, 2.0,"horse", "horse"},
                {10,2.0,"horses","horse"},      //bug
                {5, 2.0,"horse", "ho"},         //bug
                {10,2.0, " horse", "horse"},    //bug
                {10,2.0, "horse ", "horse"},    //bug
                {10, 2.0, "hor se", "hor se"},  //bug
        };
    }

    @DataProvider(name="negativeDataForWriteMethod")
    public Object[][] inputNegative(){
        return new Object[][]{
                {-1,2.0,"horse",""},
                {10,2.0,"",""},
                {10,0.0,"horse",""},            //bug
                {10,-1.0,"horse",""},           //bug
        };
    }

    @Parameters({"testPositiveInkContainerValue"})
    @Test(description = "Test of 'isWork' method with positive amount of ink")
    public void isWorkMethodPositiveTest(@Optional("10") int testPositiveInkContainerValue) {
        Pen penWithPositiveInk = new Pen(testPositiveInkContainerValue);
        Assert.assertTrue(penWithPositiveInk.isWork());
    }

    @Parameters({"testNegativeInkContainerValue"})
    @Test(description = "Test of 'isWork' method with negative amount of ink")
    public void isWorkMethodNegativeTest(@Optional("-1") int testNegativeInkContainerValue) {
        Pen penWithNegativeInk = new Pen(testNegativeInkContainerValue);
        Assert.assertFalse(penWithNegativeInk.isWork());
    }

    @Parameters({"testEmptyInkContainerValue"})
    @Test(description = "Test of 'isWork' method without ink")
    public void isWorkMethodWithoutInk(@Optional("0") int testEmptyInkContainerValue){
        Pen penWithoutInk = new Pen(testEmptyInkContainerValue);
        Assert.assertFalse(penWithoutInk.isWork());
    }

    @Parameters({"testPositiveInkContainerValue"})
    @Test(description = "Test for a correct work of constructor with 'inkContainerValue' parameter")
    public void testForConstructorWithInkParameter(@Optional("10") int testPositiveInkContainerValue) throws NoSuchFieldException, IllegalAccessException {
        Pen penWithEstablishedInk = new Pen(testPositiveInkContainerValue);
        Field inkField = Pen.class.getDeclaredField("inkContainerValue");
        inkField.setAccessible(true);
        int valueOfTheField = (int) inkField.get(penWithEstablishedInk);
        Assert.assertEquals(valueOfTheField, testPositiveInkContainerValue);
    }

    @Parameters({"testPositiveInkContainerValue","testSizeLetter"})
    @Test(description = "Test for a correct work of constructor with 'sizeLetter' parameter")
    public void testForConstructorWithSizeParameter(@Optional("10") int testPositiveInkContainerValue,
                                                    @Optional("2.0") double testSizeLetter) throws NoSuchFieldException, IllegalAccessException {
        Pen penWithEstablishedSizeLetter = new Pen(testPositiveInkContainerValue, testSizeLetter);
        Field sizeField = Pen.class.getDeclaredField("sizeLetter");
        sizeField.setAccessible(true);
        double valueOfTheField = (double) sizeField.get(penWithEstablishedSizeLetter);
        Assert.assertEquals(valueOfTheField, testSizeLetter);
    }

    @Parameters({"testPositiveInkContainerValue","testSizeLetter", "testColor"})
    @Test(description = "Test for a correct work of constructor with 'color' parameter")
    public void testForConstructorWithColorParameter(@Optional("10") int testPositiveInkContainerValue,
                                                     @Optional("2.0") double testSizeLetter,
                                                     @Optional("RED") String testColor) throws NoSuchFieldException, IllegalAccessException {
        Pen penWithEstablishedColor = new Pen(testPositiveInkContainerValue, testSizeLetter, testColor);
        Field colorField = Pen.class.getDeclaredField("color");
        colorField.setAccessible(true);
        String valueOfTheField = (String) colorField.get(penWithEstablishedColor);
        Assert.assertEquals(valueOfTheField, testColor);
    }

    @Parameters({"testPositiveInkContainerValue","testColor"})
    @Test(description = "Test for a correct work of 'doSomethingElse' method")
    public void doSomethingElseMethodTest(@Optional("10") int testPositiveInkContainerValue,
                                          @Optional("RED") String testColor) throws NoSuchFieldException, IllegalAccessException {
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

    // Bug (description in Readme file)
    @Parameters({"testPositiveInkContainerValue","testSizeLetter","testColor"})
    @Test(description = "Test for a correct work of 'getColor' method")
    public void getColorMethodTest(@Optional("10") int testPositiveInkContainerValue,
                                   @Optional("2.0") double testSizeLetter,
                                   @Optional("RED") String testColor) {
        Pen penWithEstablishedColor = new Pen(testPositiveInkContainerValue, testSizeLetter, testColor);
        Assert.assertEquals(penWithEstablishedColor.getColor(), testColor);
    }

    // Three bugs (description in Readme file)
    @Test(description = "Test for a correct work of 'write' method with positive data", dataProvider = "positiveDataForWriteMethod")
    public void positiveWriteMethodTest(int inkContainerValue, double sizeLetter,String word, String expectedResult) {
        Assert.assertEquals(new Pen(inkContainerValue, sizeLetter).write(word), expectedResult);
    }

    // Bug (description in Readme file)
    @Test(description = "Test for a correct work of 'write' method with positive data", dataProvider = "negativeDataForWriteMethod")
    public void negativeWriteMethodTest(int inkContainerValue, double sizeLetter,String word, String expectedResult) {
        Assert.assertEquals(new Pen(inkContainerValue, sizeLetter).write(word), expectedResult);
    }

    @Parameters({"testPositiveInkContainerValue", "testSizeLetter"})
    @Test
    public void testSecondUsageOfPen(@Optional("10") int testPositiveInkContainerValue,
                                   @Optional("2.0") double testSizeLetter){
        Pen pen = new Pen(testPositiveInkContainerValue,testSizeLetter);
        pen.write("abcde");
        Assert.assertEquals(pen.write("a"),"");
    }

}

