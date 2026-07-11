package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.entity.Address;
import com.ozgesonmez.bandagebackend.service.AddressService;
import com.ozgesonmez.bandagebackend.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user/address")
@CrossOrigin
public class AddressController {

    private final AddressService addressService;
    private final JwtService jwtService;

    public AddressController(
            AddressService addressService,
            JwtService jwtService
    ) {
        this.addressService = addressService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<AddressResponse> getAddresses(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String email = extractEmail(authorizationHeader);

        return addressService.findAllByUserEmail(email)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse createAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddressRequest request
    ) {
        String email = extractEmail(authorizationHeader);

        Address addressData = toEntity(request);
        Address savedAddress =
                addressService.createAddress(email, addressData);

        return toResponse(savedAddress);
    }

    @PutMapping
    public AddressResponse updateAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddressRequest request
    ) {
        String email = extractEmail(authorizationHeader);

        if (request.id() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Address id is required."
            );
        }

        Address addressData = toEntity(request);
        addressData.setId(request.id());

        Address updatedAddress =
                addressService.updateAddress(email, addressData);

        return toResponse(updatedAddress);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long addressId
    ) {
        String email = extractEmail(authorizationHeader);

        addressService.deleteAddress(email, addressId);
    }

    private String extractEmail(String authorizationHeader) {
        if (
                authorizationHeader == null ||
                        !authorizationHeader.startsWith("Bearer ")
        ) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authorization token is missing."
            );
        }

        String token = authorizationHeader.substring(7);

        try {
            if (!jwtService.isTokenValid(token)) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Token is invalid or expired."
                );
            }

            return jwtService.extractEmail(token);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Token is invalid or expired."
            );
        }
    }

    private Address toEntity(AddressRequest request) {
        Address address = new Address();

        address.setTitle(request.title());
        address.setName(request.name());
        address.setSurname(request.surname());
        address.setPhone(request.phone());
        address.setCity(request.city());
        address.setDistrict(request.district());
        address.setNeighborhood(request.neighborhood());

        return address;
    }

    private AddressResponse toResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getTitle(),
                address.getName(),
                address.getSurname(),
                address.getPhone(),
                address.getCity(),
                address.getDistrict(),
                address.getNeighborhood()
        );
    }

    public record AddressRequest(
            Long id,
            String title,
            String name,
            String surname,
            String phone,
            String city,
            String district,
            String neighborhood
    ) {
    }

    public record AddressResponse(
            Long id,
            String title,
            String name,
            String surname,
            String phone,
            String city,
            String district,
            String neighborhood
    ) {
    }
}