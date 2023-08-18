package hj.backend.service;

import hj.backend.domain.Address;
import hj.backend.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public class MybatisAddressService implements AddressService {

    private final AddressMapper mapper;
  //  @Autowired
    public MybatisAddressService(AddressMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public List<Address> listS() {
        return mapper.list();
    }

    @Override
    public Address insertS(Address address) {
        pln("@insertS() by mapper");
        mapper.insertSelectKey(address);
        long seq = address.getSeq();
        pln("@insertS() address.getSeq(): "+seq);
        address = mapper.selectBySeq(seq);
        pln("@insertS() address: "+address);
        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by mapper");
        mapper.delete(seq);
        return true;
    }

    void pln(String str){
        System.out.println(str);
    }
}
