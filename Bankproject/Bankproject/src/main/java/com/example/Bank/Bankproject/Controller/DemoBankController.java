package com.example.Bank.Bankproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoBankController {

    @GetMapping("/banks")
    public  String getBankDetails()
    {
        return "HDFC bank";
    }
}
