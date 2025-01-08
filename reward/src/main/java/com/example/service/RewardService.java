package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.RewardDTO;
import com.example.dto.TransactionDTO;
import com.example.util.DataUtil;

@Service
public class RewardService {

	public List<RewardDTO> calculateRewards() {
		Map<Long, RewardDTO> rewardsMap = new HashMap<>();
		List<TransactionDTO> transactions = filterLatestThreeMonthsRecords(DataUtil.getData());
		for (TransactionDTO transaction : transactions) {
			Long customerId = transaction.getCustomerId();
			Double amount = transaction.getAmount();
			String month = transaction.getTransactionDate().getMonth().toString();

			int points = calculatePoints(amount);

			rewardsMap.putIfAbsent(customerId, new RewardDTO(customerId));
			rewardsMap.get(customerId).addPoints(month, points);
		}

		return new ArrayList<>(rewardsMap.values());
	}

	public int calculatePoints(Double amount) {
		int points = 0;
		if (amount > 100) {
			points += (amount - 100) * 2;
		}
		if (amount > 50) {
			points += Math.min(amount - 50, 50) * 1;
		}
		return points;
	}

	public List<TransactionDTO> getAllCustomerDetails() {
		return DataUtil.getData();
	}

	public static List<TransactionDTO> filterLatestThreeMonthsRecords(List<TransactionDTO> transactions) {
		LocalDate currentDate = LocalDate.now();
		LocalDate threeMonthsAgo = currentDate.minusMonths(3);
		return transactions.stream().filter(transaction -> transaction != null)
				.filter(transaction -> transaction.getTransactionDate().isAfter(threeMonthsAgo)
						|| transaction.getTransactionDate().isEqual(threeMonthsAgo))
				.collect(Collectors.toList());
	}

	public Map<Long, RewardDTO> getCalculateRewardPointForCustomer(Long customerId) {
		Map<Long, RewardDTO> rewardsMap = new HashMap<>();
		boolean exists = DataUtil.getData().stream().anyMatch(item -> customerId.equals(item.getCustomerId()));
		if (exists) {
			LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
			List<TransactionDTO> filteredTransactions = DataUtil.getData().stream()
					.filter(transaction -> transaction.getCustomerId().equals(customerId))
					.filter(transaction -> !transaction.getTransactionDate().isBefore(threeMonthsAgo)).toList();
			for (TransactionDTO transaction : filteredTransactions) {
				Long custId = transaction.getCustomerId();
				Double amount = transaction.getAmount();
				String month = transaction.getTransactionDate().getMonth().toString();

				int points = calculatePoints(amount);

				rewardsMap.putIfAbsent(custId, new RewardDTO(custId));
				rewardsMap.get(custId).addPoints(month, points);
			}
		}
		return rewardsMap;
	}

}
