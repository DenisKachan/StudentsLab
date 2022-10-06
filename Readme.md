***Maven code for 'AllUnitTests.xml' launch***
 `mvn test -DsuiteFile="AllUnitTests.xml"`

***List of unit tests for 'Pen' class'***
- Test of 'isWork' method with positive amount of ink;
- Test of 'isWork' method with negative amount of ink;
- Test for a correct work of constructor with 'inkContainerValue' parameter;
- Test for a correct work of constructor with 'sizeLetter' parameter;
- Test for a correct work of constructor with 'color' parameter;
- Test for a correct work of 'doSomethingElse' method;
- Test for a correct work of 'getColor' method;
- Test for a correct work of 'write' method (DataProvider is used).

***List of detected bugs***
- 'getColor' method doesn't return current value of 'color' parameter;
- Incorrect work of 'write' method when the product of the length of the word and 'sizeLetter' is more than 'inkContainerValue';
   1) When the 'inkContainerValue' is less or equal than the length of the word - method returns the incorrect value;
   2) When the 'inkContainerValue' is more than the length of the word - IndexOutOfBoundsException.