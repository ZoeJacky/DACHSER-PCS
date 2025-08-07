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
@RequestMapping("/api/costs")
public class CostController {

//    @PreAuthorize("hasRole('costs_admin')")
    @GetMapping("/handle")
    public String handleCost(){
        return "handle cost";
    }



}
