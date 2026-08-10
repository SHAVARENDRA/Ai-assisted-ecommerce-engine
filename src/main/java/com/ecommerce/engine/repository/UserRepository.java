package com.ecommerce.engine.repository;

import com.ecommerce.engine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User}.
 * <p>
 * Extending {@link JpaRepository} gives you save(), findById(), findAll(), delete()
 * without writing SQL. Custom finder methods are derived from method names.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
