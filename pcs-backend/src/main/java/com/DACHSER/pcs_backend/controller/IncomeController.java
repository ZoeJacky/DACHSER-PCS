package com.DACHSER.pcs_backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

//    @PreAuthorize("hasRole('payment_admin')")
    @GetMapping("/handle")
    public String handleIncome(){
        return "handle income";
    }
}
