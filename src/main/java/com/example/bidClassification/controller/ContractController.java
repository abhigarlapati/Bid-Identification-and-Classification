package com.example.bidClassification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bidClassification.dto.ContractDTO;
import com.example.bidClassification.model.Contract;
import com.example.bidClassification.model.ContractStatus;
import com.example.bidClassification.service.ContractService;
@RestController
@RequestMapping("/contract")
public class ContractController {

	 @Autowired
	 private ContractService contractService;

	    @PostMapping("/createcontract")
	    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contractdto) {
	        return ResponseEntity.ok(contractService.createContract(contractdto));
	    }

	    @PutMapping("/{id}/status")
	    public ResponseEntity<Contract> updateStatus(@PathVariable Long id, @RequestParam ContractStatus status) {
	        return ResponseEntity.ok(contractService.updateStatus(id, status));
	    }
	    @GetMapping("/getAll")
	    public ResponseEntity<List<ContractDTO>> getAllContracts()
	    {
	    	return ResponseEntity.ok(contractService.getAllContracts());
	    }
}
