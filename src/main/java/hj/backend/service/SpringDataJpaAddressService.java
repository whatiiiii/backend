package hj.backend.service;

import hj.backend.domain.Address;
import hj.backend.repository.SpringDataJpaOracleAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class SpringDataJpaAddressService implements AddressService {
  //  @Autowired
    private final SpringDataJpaOracleAddressRepository repository;
  //  @Autowired
    public SpringDataJpaAddressService(SpringDataJpaOracleAddressRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Address> listS() {
        pln("@listS() by SpringDataJap");
        //return repository.findAll();
        return repository.findAll(Sort.by(Sort.Direction.DESC, "seq")); // SQL문의 order by
        //return repository.findByName("홍길동"); //리스트에서 이름이 홍길동인 사람만 출력할 때
        //return repository.findByNameAndAddr("홍길동", "서울시"); //리스트에서 이름이 홍길동이고 주소가 서울시인거 출력할 때
        //return repository.findByNameContaining("홍"); //리스트에서 이름에 홍이 들어간사람 출력할 때
    }

    @Override
    public Address insertS(Address address) {
        pln("#insertS() by SpringDataJpa");
        address = repository.save(address);
        pln("#insertS() address: " + address);
        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        pln("#deleteS() by SpringDataJpa");
        repository.deleteById(seq);
        return true;
    }
    public List<Address> findByName(String name){
        List<Address> list = repository.findByName(name);
        pln("@findByName() by SpringDataJpa list: " + list);
        return list;
    }
    public List<Address> findByNameAndAddr(String name, String addr){
        List<Address> list =repository.findByNameAndAddr(name, addr);
        pln("@findByNameAndAddr() SpringDataJpa list: " + list);
        return list;
    }
    public List<Address> findByNameContaining(String name){
        List<Address> list = repository.findByNameContaining(name);
        pln("findByNameContaining() SpringDataJpa list: " + list);
        return list;
    }


    void pln(String str){
        System.out.println(str);
    }
}