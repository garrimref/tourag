package com.reltour.tourag.repositories;

import com.reltour.tourag.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    void deleteById(Long id);
}
