package com.abc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockListTest {

	@Test
	public void mockingFinalMethod() {
		
		MyList myList = new MyList();
		
		MyList mock2 = mock(MyList.class);
		
		 when(mock2.abc()).thenReturn(1000);
		
		Assertions.assertEquals(mock2.abc(), myList.abc());
	}
}
