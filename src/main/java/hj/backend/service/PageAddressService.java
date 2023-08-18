package hj.backend.service;

import hj.backend.domain.Address;
import hj.backend.dto.AddressListResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageAddressService {
    Page<Address> findAll(Pageable pageable);
    AddressListResult getAdddressListResult(Pageable pageable);

    Address insertS(Address address);

    void deleteS(long seq);
}
