package hj.backend.service;

import hj.backend.domain.Address;
import hj.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public class JdbcOracleAddressService implements AddressService{

    private final AddressRepository repository;
  //  @Autowired
    public JdbcOracleAddressService(AddressRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Address> listS() {
        return repository.list();
    }

    @Override
    public Address insertS(Address address) {
        return repository.insert(address);
    }

    @Override
    public boolean deleteS(long seq) {
        return repository.delete(seq);
    }
}
