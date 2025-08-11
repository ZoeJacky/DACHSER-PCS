package com.DACHSER.pcs_backend.controller;

import com.DACHSER.pcs_backend.dto.IncomeDto;
import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    private IncomeService incomeService;

    @PostMapping()
    @PreAuthorize("hasRole('payment-admin')")
    public ResponseEntity<IncomeDto> createIncome(@RequestBody IncomeDto incomeDto) {
        IncomeDto savedIncome = incomeService.createIncome(incomeDto);
        return new ResponseEntity<>(savedIncome, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('payment-admin')")
    public ResponseEntity<IncomeDto> getIncomeById(@PathVariable("id") Long incomeId) {
        IncomeDto incomeDto = incomeService.getIncomeById(incomeId);
        return ResponseEntity.ok(incomeDto);
    }

    @GetMapping()
    @PreAuthorize("hasRole('payment-admin')")
    public ResponseEntity<List<IncomeDto>> getAllIncomes() {
        List<IncomeDto> incomes = incomeService.getAllIncomes();
        return ResponseEntity.ok(incomes);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('payment-admin')")
    public ResponseEntity<IncomeDto> updateIncome(@PathVariable("id") Long incomeId,
                                                      @RequestBody IncomeDto updatedIncomeDto){
        IncomeDto incomeDto = incomeService.updateIncome(incomeId, updatedIncomeDto);
        return ResponseEntity.ok(incomeDto);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('payment-admin')")
    public ResponseEntity<Map<String,String>> deleteIncome(@PathVariable("id") Long incomeId){
        incomeService.deleteIncome(incomeId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Income " + incomeId + " has been deleted");
        return ResponseEntity.ok(response);
    }
}
