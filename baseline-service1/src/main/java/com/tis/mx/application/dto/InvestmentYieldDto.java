/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * Nombre de archivo: InvestmentYieldDto.java 
 * Autor: johanama 
 * Fecha de creación: 7 sep 2021
 */


package com.tis.mx.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class InvestmentYieldDto.
 */

/**
 * Gets the final balance.
 *
 * @return the final balance
 */
@Getter

/**
 * Sets the final balance.
 *
 * @param finalBalance the new final balance
 */
@Setter
@AllArgsConstructor
public class InvestmentYieldDto {

  /** The investment year. */
  private Integer investmentYear;

  /** The initial investment. */
  private Double initialInvestment;

  /** The yearly input. */
  private Double yearlyInput;

  /** The investment yield. */
  private Double investmentYield;

  /** The final balance. */
  private Double finalBalance;
}
