package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.AddressOutput;
import com.algaworks.algafoodapi.domain.model.Address;

/**
 * AddressOutuput
 */
@Component
public class AddressAssembler {

        public AddressOutput toOutput(Address address) {
                return new AddressOutput(
                                address.getPostal_code(),
                                address.getStreet(),
                                address.getNumber(),
                                address.getComplement(),
                                address.getNeighborhood());
        }

        public Address toEntity(AddressOutput addressInput) {
                Address address = new Address();
                address.setPostal_code(addressInput.postal_code());
                address.setStreet(addressInput.street());
                address.setNumber(addressInput.number());
                address.setComplement(addressInput.complement());
                address.setNeighborhood(addressInput.neighborhood());
                return address;
        }

}
