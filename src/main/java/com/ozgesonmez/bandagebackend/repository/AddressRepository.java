package com.ozgesonmez.bandagebackend.repository;

import com.ozgesonmez.bandagebackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUserIdOrderByIdDesc(Long userId);

    Optional<Address> findByIdAndUserId(Long addressId, Long userId);
}