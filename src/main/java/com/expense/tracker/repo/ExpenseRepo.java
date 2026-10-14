package com.expense.tracker.repo;

import com.expense.tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {

    List<Expense> findByuser_Id(Long id);
}
