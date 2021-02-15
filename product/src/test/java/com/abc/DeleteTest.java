package com.abc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

//add the mockito-inline dependency in om.xml extenally for testing static methods

/*<dependency>
<groupId>org.mockito</groupId>
<artifactId>mockito-inline</artifactId>
<version>3.6.28</version>
</dependency>
*/public class DeleteTest {

	@Test  
	public void DeleteTestClass() {
		MockedStatic<Delete> mockedStatic = Mockito.mockStatic(Delete.class);
		mockedStatic.when(Delete::abc).thenReturn("bharath");
		Assertions.assertEquals(Delete.abc(), "bharath");
	}
}
