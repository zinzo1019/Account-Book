package com.example.demo.user.dao;

import com.example.demo.user.dto.CategoryDto;
import com.example.demo.user.dto.TransactionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {

    /**
     * 계층형 카테고리 조회
     * 최상위 카테고리를 기준으로 하위 카테고리 리스트를 조회한다.
     *
     * @return
     */
    List<CategoryDto.ParentCategoryInfo> findAllGroupByParentId();

    /**
     * 지출 저장
     * @param transactionDto
     */
    void saveTransaction(TransactionDto transactionDto);
}
