package hj.backend.service;

import hj.backend.domain.Address;
import hj.backend.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
@Transactional //JPA에는 반드시 Tansactional를 써줘야 한다
public class JpaAddressService implements AddressService {
  //  @Autowired
    private final AddressRepository repository;
   // @Autowired
    public JpaAddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> listS() {
        pln("@list@() by jpa");
        return repository.list();
    }

    @Override
    public Address insertS(Address address) {
        pln("@insertS() by jpa: (Before) address: "+address);
        address = repository.insert(address);
        pln("@insertS() by jpa: (After) address: "+address);
        pln("@insertS() by jpa: seq: "+address.getSeq());
        pln("@insertS() by jpa: rdate: "+address.getRdate());

        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by jpa: ");
        return repository.delete(seq);
    }

    void pln(String str){
        System.out.println(str);
    }
}
