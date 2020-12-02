package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

//	Calculator calculator = new Calculator();
//	// Calculator IS CALLED BY CalculatorService
//	CalculatorService classUnderTest = new CalculatorServiceImpl(calculator);
	@Mock
	Calculator calculator;

	CalculatorService classUnderTest;

	@BeforeEach
	public void init() {
		classUnderTest = new CalculatorServiceImpl(calculator);
	}
	
	@Test
	public void calculate_shouldUseCalculator_forAddition() {
		// GIVEN
		when(calculator.add(1, 2)).thenReturn(3);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();

		// THEN
		//verify(calculator).add(1, 2);
	    verify(calculator, times(1)).add(any(Integer.class), any(Integer.class));
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void calculate_shouldThrowIllegalArgumentException_forADivisionBy0() {
		// GIVEN
		when(calculator.divide(1, 0)).thenThrow(new ArithmeticException());

		// WHEN
		assertThrows(IllegalArgumentException.class, () -> classUnderTest.calculate(
				new CalculationModel(CalculationType.DIVISION, 1, 0)));

		// THEN
		verify(calculator, times(1)).divide(1, 0);
	}


//	@Test
//	public void add_returnsTheSum_ofTwoPositiveNumbers() {
//		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();
//		assertThat(result).isEqualTo(3);
//	}

//	@Test
//	public void add_returnsTheSum_ofTwoNegativeNumbers() {
//		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.ADDITION, -1, 2))
//				.getSolution();
//
//		assertThat(result).isEqualTo(1);
//	}

//	@Test
//	public void add_returnsTheSum_ofZeroAndZero() {
//		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
//		assertThat(result).isEqualTo(0);
//	}
}
