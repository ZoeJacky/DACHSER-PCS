package com.DACHSER.pcs_backend.controller;

import com.DACHSER.pcs_backend.dto.CostDto;
import com.DACHSER.pcs_backend.dto.IncomeDto;
import com.DACHSER.pcs_backend.service.CostService;
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
@RequestMapping("/api/costs")
public class CostController {

    private CostService costService;

    @PostMapping()
    @PreAuthorize("hasRole('cost-admin')")
    public ResponseEntity<CostDto> createCost(@RequestBody CostDto costDto) {
        CostDto savedCost = costService.createCost(costDto);
        return new ResponseEntity<>(savedCost, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('cost-admin')")
    public ResponseEntity<CostDto> getCostById(@PathVariable("id") Long costId) {
        CostDto costDto = costService.getCostById(costId);
        return ResponseEntity.ok(costDto);
    }

    @GetMapping()
    @PreAuthorize("hasRole('cost-admin')")
    public ResponseEntity<List<CostDto>> getAllCosts() {
        List<CostDto> costs = costService.getAllCosts();
        return ResponseEntity.ok(costs);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('cost-admin')")
    public ResponseEntity<CostDto> updateCost(@PathVariable("id") Long costId,
                                                  @RequestBody CostDto updatedCostDto){
        CostDto costDto = costService.updateCost(costId, updatedCostDto);
        return ResponseEntity.ok(costDto);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('cost-admin')")
    public ResponseEntity<Map<String,String>> deleteCost(@PathVariable("id") Long costId){
        costService.deleteCost(costId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cost " + costId + " has been deleted");
        return ResponseEntity.ok(response);
    }


}
