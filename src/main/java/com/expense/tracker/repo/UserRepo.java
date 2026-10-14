package com.expense.tracker.repo;

import com.expense.tracker.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<user, Long> {


}
