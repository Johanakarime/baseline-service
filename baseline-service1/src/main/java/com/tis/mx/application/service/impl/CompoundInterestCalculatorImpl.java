/* 
* This program is free software: you can redistribute it and/or modify  
* it under the terms of the GNU General Public License as published by  
* the Free Software Foundation, version 3.
*
* This program is distributed in the hope that it will be useful, but 
* WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
* General Public License for more details.
*
* Nombre de archivo: CompoundInterestCalculatorImpl.java
* Autor: johanama
* Fecha de creación: 10 sep 2021
*/


package com.tis.mx.application.service.impl;

import com.tis.mx.application.dto.InitialInvestmentDto;
import com.tis.mx.application.dto.InvestmentYieldDto;
import com.tis.mx.application.service.CompoundInterestCalculator;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The Class CompoundInterestCalculatorImpl.
 */
@Service
public class CompoundInterestCalculatorImpl implements CompoundInterestCalculator {

  /**
   * Creates the revenue grid.
   *
   * @param initialInvestment the initial investment
   * @return the list
   */
  @Override
  public List<InvestmentYieldDto> createRevenueGrid(InitialInvestmentDto initialInvestment) {

    List<InvestmentYieldDto> tablaDeRendimiento = new ArrayList<>();

    int ciclosDeInversion = initialInvestment.getInvestmentYears();

    for (int ciclo = 0; ciclo < ciclosDeInversion; ciclo++) {

      InvestmentYieldDto rendimientoAnual = null;

      if (ciclo == 0) {
        rendimientoAnual = this.calcularRendimientoAnual(initialInvestment, null);
      } else {

        rendimientoAnual =
            this.calcularRendimientoAnual(initialInvestment, tablaDeRendimiento.get(ciclo - 1));
      }

      tablaDeRendimiento.add(rendimientoAnual);
    }


    return tablaDeRendimiento;
  }


  /**
   * Calcular rendimiento anual.
   *
   * @param inversionInicial the inversion inicial
   * @param rendimientoAnterior the rendimiento anterior
   * @return the investment yield dto
   */
  private InvestmentYieldDto calcularRendimientoAnual(InitialInvestmentDto inversionInicial,
      InvestmentYieldDto rendimientoAnterior) {

    InvestmentYieldDto rendimiento = new InvestmentYieldDto();

    if (rendimientoAnterior == null) {
      /**
       * Aqui no existe rendimiento anterior
       */
      rendimiento.setInvestmentYear(1);
      rendimiento.setInitialInvestment(inversionInicial.getInitialInvestment());
      rendimiento.setYearlyInput(inversionInicial.getYearlyInput());
    } else {
      /**
       * Aqui si hay un rendimiento anterior
       */
      rendimiento.setInvestmentYear(rendimientoAnterior.getInvestmentYear() + 1);
      rendimiento.setInitialInvestment(rendimientoAnterior.getFinalBalance());

      Float aportacion = rendimientoAnterior.getYearlyInput()
          * (1 + (inversionInicial.getYearlyInputIncrement() / 100));
      rendimiento.setYearlyInput(aportacion);
    }

    Float rendimientoAnual = rendimiento.getInitialInvestment() + rendimiento.getYearlyInput();
    rendimientoAnual = rendimientoAnual * (inversionInicial.getInvestmentYield() / 100);

    rendimiento.setInvestmentYield(rendimientoAnual);


    Float saldoFinal = rendimiento.getInitialInvestment() + rendimiento.getYearlyInput()
        + rendimiento.getInvestmentYield();

    rendimiento.setFinalBalance(saldoFinal);

    return rendimiento;
  }

  /**
   * Validate input.
   *
   * @param initialInvestment the initial investment
   * @return true, if successful
   */
  @Override
  public boolean validateInput(InitialInvestmentDto initialInvestment) {

    this.setDefaults(initialInvestment);
    boolean cumple = true;

    cumple = (initialInvestment.getInitialInvestment() >= 1000);
    cumple = cumple && (initialInvestment.getYearlyInput() >= 0.0);
    cumple = cumple && (initialInvestment.getYearlyInputIncrement() >= 0);
    cumple = cumple && (initialInvestment.getInvestmentYears() > 0.0);
    cumple = cumple && (initialInvestment.getInvestmentYield() > 0.0);

    return cumple;
  }

  /**
   * Sets the defaults.
   *
   * @param initialInvestment the new defaults
   */
  private void setDefaults(InitialInvestmentDto initialInvestment) {
    Float yearlyInput = initialInvestment.getYearlyInput();
    yearlyInput = yearlyInput == null ? 0 : yearlyInput;
    initialInvestment.setYearlyInput(yearlyInput);

    Integer yearlyInputIncrement = initialInvestment.getYearlyInputIncrement();
    yearlyInputIncrement = yearlyInputIncrement == null ? 0 : yearlyInputIncrement;
    initialInvestment.setYearlyInputIncrement(yearlyInputIncrement);
  }


}
