package com.example.demo.user.controller;

import com.example.demo.user.dto.TransactionDto;
import com.example.demo.user.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;

    @GetMapping
    public String get_main(Model model) {
        model.addAttribute("categories", categoryService.findAllGroupByParentId());
        return "user/main";
    }

    @PostMapping("/user/save-transaction")
    public ResponseEntity get_main(TransactionDto transactionDto) {
        transactionDto.setUserId("user1@naver.com");
        categoryService.saveTransaction(transactionDto);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
