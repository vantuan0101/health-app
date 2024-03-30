package com.vantuan.careplanmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/careplan")
public class CarePlanController {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public  String getCarePlan(){
        return "CarePlan 1";
    }
}
