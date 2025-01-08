package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.RewardDTO;
import com.example.dto.TransactionDTO;
import com.example.service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {
	@Autowired
	private RewardService rewardService;

	@GetMapping("/test")
	public ResponseEntity<String> testEndpoint() {
		return ResponseEntity.ok("welcome to reward calculation!");
	}

	@GetMapping("/transacation-details")
	public ResponseEntity<List<TransactionDTO>> getAllCustomerDetails() {
		List<TransactionDTO> transDetailsList = rewardService.getAllCustomerDetails();
		return ResponseEntity.ok(transDetailsList);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Map<Long, RewardDTO>> getCalculateRewardPointForCustomer(@PathVariable Long customerId) {
		Map<Long, RewardDTO> rewards = rewardService.getCalculateRewardPointForCustomer(customerId);
		return ResponseEntity.ok(rewards);
	}

	@GetMapping("/customer/all")
	public ResponseEntity<List<RewardDTO>> calculateRewards() {
		List<RewardDTO> rewards = rewardService.calculateRewards();
		return ResponseEntity.ok(rewards);
	}

}
