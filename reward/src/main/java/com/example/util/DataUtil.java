package com.example.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.dto.TransactionDTO;

public class DataUtil {

	public static List<TransactionDTO> getData() {

		List<TransactionDTO> transactions = Arrays.asList(
				new TransactionDTO(1L, 120.0, LocalDate.parse("2025-01-01")),
				new TransactionDTO(2L, 80.0, LocalDate.parse("2024-12-05")),
				new TransactionDTO(1L, 65.0, LocalDate.parse("2024-11-15")),
				new TransactionDTO(1L, 300.0, LocalDate.parse("2024-12-20")),
				new TransactionDTO(2L, 75.0, LocalDate.parse("2024-11-07")),
				new TransactionDTO(3L, 500.0, LocalDate.parse("2024-08-07")),
				new TransactionDTO(4L, 350.0, LocalDate.parse("2023-08-07")),
				new TransactionDTO(4L, 345.0, LocalDate.parse("2022-08-07")),
				new TransactionDTO(5L, 130.0, LocalDate.parse("2024-12-07")),
				new TransactionDTO(3L, 80.0, LocalDate.parse("2024-12-05")));
		return transactions;
	}

}
