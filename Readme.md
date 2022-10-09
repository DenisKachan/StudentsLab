***Maven code for test launch***
`mvn test -DsuiteXmlFile="AllUnitTests.xml"`

***List of unit tests for 'Pen' class'***
- Test of 'isWork' method with positive amount of ink;
- Test of 'isWork' method with negative amount of ink;
- Test of 'isWork' method without ink;
- Test for a correct work of constructor with 'inkContainerValue' parameter;
- Test for a correct work of constructor with 'sizeLetter' parameter;
- Test for a correct work of constructor with 'color' parameter;
- Test for a correct work of 'doSomethingElse' method;
- Test for a correct work of 'getColor' method;
- Test for a correct work of 'write' method with positive data(DataProvider is used);
- Test for a correct work of 'write' method with negative data(DataProvider is used);
- Test for a correct work of 'write' method when we use a pen more than once.

***List of detected bugs***
1. 'getColor' method doesn't return current value of 'color' parameter.
2. Write method returns the incorrect value. Preconditions:
- the product of the length of the word and 'sizeLetter' is more than 'inkContainerValue; 
- 'inkContainerValue' is less or equal than the length of the word.
3. Write method throws IndexOutOfBoundsException. Preconditions:
- the product of the length of the word and 'sizeLetter' is more than 'inkContainerValue;
- 'inkContainerValue' is more than the length of the word.
4. Write method throws IndexOutOfBoundsException. Preconditions:
- there is an empty space before, after or in the middle of the word;
- the product of the length of the word and 'sizeLetter' is equal to 'inkContainerValue;
- 'inkContainerValue' is less or equal than the length of the word.
5. Write method returns the incorrect value. Preconditions:
- you try to write any word with 'sizeLetter' parameter that is <=0.
6. In general you can create a pen with 'sizeLetter' parameter that is <=0.
