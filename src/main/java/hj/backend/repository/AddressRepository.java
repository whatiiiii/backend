package hj.backend.repository;

import hj.backend.domain.Address;
import java.util.List;

public interface AddressRepository {
    List<Address> list();
    Address insert(Address address);
    boolean delete(long seq);



}
