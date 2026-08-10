package com.ecommerce.engine.repository;

import com.ecommerce.engine.model.Order;
import com.ecommerce.engine.model.OrderStatus;
import com.ecommerce.engine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(OrderStatus status);


    @Query("SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.product WHERE o.user.id = :userId")
    List<Order> findOrdersWithDetailsByUserId(@Param("userId") Long userId);
}
