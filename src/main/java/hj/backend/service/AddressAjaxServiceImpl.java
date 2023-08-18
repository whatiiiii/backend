package hj.backend.service;

import hj.backend.domain.Address;
import hj.backend.repository.AddressRepository;
import hj.backend.repository.SpringDataJpaOracleAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressAjaxServiceImpl implements AddressAjaxService {
    @Autowired
    private final SpringDataJpaOracleAddressRepository repository;
    @Autowired
    public AddressAjaxServiceImpl(SpringDataJpaOracleAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> listS() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }

    @Override
    public Address insertS(Address address) {
        address = repository.save(address);
        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        repository.deleteById(seq);
        return true;
    }

    @Override
    public Address getBySeqS(long seq) {
        Address address = repository.findById(seq).get();
        return address;
    }

    @Override
    public List<Address> getListByNames(String name) {
        //List<Address> list = repository.findByName(name);
           List<Address> list = repository.findByNameContaining(name);
        return list;
    }
}
