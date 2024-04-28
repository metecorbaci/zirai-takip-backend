package com.ziraitakip.backend.service;

import com.ziraitakip.backend.model.Address;
import com.ziraitakip.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address findById(Long id){
        return addressRepository.findById(id);
    }

    public Address save(Address address){
        return addressRepository.save(address);
    }

    public boolean deleteById(Integer id){
        try {
            addressRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
