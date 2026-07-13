package com.ozgesonmez.bandagebackend.repository;

import com.ozgesonmez.bandagebackend.entity.AppUser;
import com.ozgesonmez.bandagebackend.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository
        extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder>
    findAllByUserOrderByOrderDateDesc(AppUser user);
}