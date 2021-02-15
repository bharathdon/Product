package com.abc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinalListTest {

	//mocking final classes
	
	
	@Test
	public void sizeTest() {
		
		FinalList finalList = new FinalList();
		
		FinalList finalList2 = mock(FinalList.class);
		
		when(finalList2.size()).thenReturn(100);
		
		Assertions.assertEquals(finalList2.size(), finalList.size());
	}
}
