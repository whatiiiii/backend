package hj.backend.service;

import hj.backend.domain.Address;

import java.util.List;

public interface AddressService {
    List<Address> listS();
    Address insertS(Address address);
    boolean deleteS(long seq);
}
