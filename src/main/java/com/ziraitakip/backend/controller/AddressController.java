package com.ziraitakip.backend.controller;

import com.ziraitakip.backend.model.Address;
import com.ziraitakip.backend.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ziraitakip.backend.util.ResponseBody;

import java.util.List;

@RestController
@RequestMapping("/address")
@Tag(name = "address", description = "Address API")
public class AddressController {

    @Autowired
    private AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public ResponseEntity getAll(HttpServletRequest request) {
        System.out.println("request: " + request);
        try {
            List<Address> addresses = addressService.findAll();
            if(addresses == null || addresses.size() == 0) {
                return ResponseBody.send("No data found", addresses, HttpStatus.NOT_FOUND);
            }
            return ResponseBody.send("Data found", addresses, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseBody.send(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@RequestParam Long id) {
        try {
            System.out.println("id: " + id);
            Address address = addressService.findById(id);
            if(address == null) {
                return ResponseBody.send("No data found", address, HttpStatus.NOT_FOUND);
            }
            return ResponseBody.send("Data found", address, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseBody.send(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
