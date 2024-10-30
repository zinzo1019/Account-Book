package com.example.demo.user.service;

import com.example.demo.user.dao.CategoryDao;
import com.example.demo.user.dto.CategoryDto;
import com.example.demo.user.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Override
    public List<CategoryDto.ParentCategoryInfo> findAllGroupByParentId() {
        List<CategoryDto.ParentCategoryInfo> parentCategoryInfo = categoryDao.findAllGroupByParentId();
        return parentCategoryInfo;
    }

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        categoryDao.saveTransaction(transactionDto);
    }

}
