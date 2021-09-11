/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * Nombre de archivo: ApplicationControllerTest.java 
 * Autor: johanama 
 * Fecha de creación: 9 sep 2021
 */

package com.tis.mx.application.controller;

import static org.junit.Assert.assertEquals;

import com.tis.mx.application.dto.InitialInvestmentDto;
import com.tis.mx.application.dto.InvestmentYieldDto;
import com.tis.mx.application.service.CompoundInterestCalculator;
import com.tis.mx.application.service.impl.CompoundInterestCalculatorImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * The Class ApplicationControllerTest.
 */
public class ApplicationControllerTest {

  /** The controller. */
  private ApplicationController controller;

  /** The initial investment. */
  private InitialInvestmentDto initialInvestment;

  /** The calculator. */
  private CompoundInterestCalculator calculator;

  /**
   * Creates the values before to test.
   */
  @Before
  public void createValuesBeforeToTest() {

    this.calculator = new CompoundInterestCalculatorImpl();
    this.controller = new ApplicationController(this.calculator);
    this.initialInvestment = new InitialInvestmentDto();

    this.initialInvestment.setInitialInvestment(15380.00);
    this.initialInvestment.setYearlyInput(3061.00);
    this.initialInvestment.setYearlyInputIncrement(3);
    this.initialInvestment.setInvestmentYears(5);
    this.initialInvestment.setInvestmentYield(.21f);

    this.initialInvestment.setInitialInvestment(22312.00);
    this.initialInvestment.setYearlyInput(3091.00);
    this.initialInvestment.setYearlyInputIncrement(4);
    this.initialInvestment.setInvestmentYears(5);
    this.initialInvestment.setInvestmentYield(.21f);

    this.initialInvestment.setInitialInvestment(30738.00);
    this.initialInvestment.setYearlyInput(3122.00);
    this.initialInvestment.setYearlyInputIncrement(5);
    this.initialInvestment.setInvestmentYears(5);
    this.initialInvestment.setInvestmentYield(.21f);
  }

  /**
   * Should generate table yield.
   */
  @Test
  public void shouldGenerateTableYield() {

    List<InvestmentYieldDto> tableYieldYear = controller.createTableYield(initialInvestment);

    assertEquals(5, tableYieldYear.size());

    InvestmentYieldDto Year = tableYieldYear.get(0);
    assertEquals(Double.valueOf(5000.00), Year.getInitialInvestment());
    assertEquals(Double.valueOf(3000.00), Year.getYearlyInput());
    assertEquals(Double.valueOf(1680.00), Year.getInvestmentYield());
    assertEquals(Double.valueOf(9680.00), Year.getFinalBalance());
  }

}


