package com.example.demo.user.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDto {
    private Long transactionId;                    // 거래 아이디
    private String userId;                         // 사용자 아이디
    private Long accountId;                        // 계좌 아이디
    private CategoryDto.CategoryInfo categoryInfo; // 카테고리 정보
    private BigDecimal amount;                     // 가격
    private String description;                    // 메모
    private LocalDate transactionDate;             // 등록 날짜
}