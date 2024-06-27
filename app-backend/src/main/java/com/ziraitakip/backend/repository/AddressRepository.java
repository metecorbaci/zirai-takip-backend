package com.ziraitakip.backend.repository;

import com.ziraitakip.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findById(Long id);
    List<Address> findAll();
}
