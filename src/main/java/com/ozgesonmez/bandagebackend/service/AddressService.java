package com.ozgesonmez.bandagebackend.service;

import com.ozgesonmez.bandagebackend.entity.Address;
import com.ozgesonmez.bandagebackend.entity.AppUser;
import com.ozgesonmez.bandagebackend.repository.AddressRepository;
import com.ozgesonmez.bandagebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(
            AddressRepository addressRepository,
            UserRepository userRepository
    ) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<Address> findAllByUserEmail(String email) {
        AppUser user = findUserByEmail(email);

        return addressRepository.findAllByUserIdOrderByIdDesc(user.getId());
    }

    @Transactional
    public Address createAddress(String email, Address addressData) {
        AppUser user = findUserByEmail(email);

        Address address = new Address();
        address.setTitle(addressData.getTitle());
        address.setName(addressData.getName());
        address.setSurname(addressData.getSurname());
        address.setPhone(addressData.getPhone());
        address.setCity(addressData.getCity());
        address.setDistrict(addressData.getDistrict());
        address.setNeighborhood(addressData.getNeighborhood());
        address.setUser(user);

        return addressRepository.save(address);
    }

    @Transactional
    public Address updateAddress(String email, Address addressData) {
        if (addressData.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Address id is required."
            );
        }

        AppUser user = findUserByEmail(email);

        Address existingAddress = addressRepository
                .findByIdAndUserId(addressData.getId(), user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Address not found."
                ));

        existingAddress.setTitle(addressData.getTitle());
        existingAddress.setName(addressData.getName());
        existingAddress.setSurname(addressData.getSurname());
        existingAddress.setPhone(addressData.getPhone());
        existingAddress.setCity(addressData.getCity());
        existingAddress.setDistrict(addressData.getDistrict());
        existingAddress.setNeighborhood(addressData.getNeighborhood());

        return addressRepository.save(existingAddress);
    }

    @Transactional
    public void deleteAddress(String email, Long addressId) {
        AppUser user = findUserByEmail(email);

        Address address = addressRepository
                .findByIdAndUserId(addressId, user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Address not found."
                ));

        addressRepository.delete(address);
    }

    private AppUser findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "User not found."
                ));
    }
}