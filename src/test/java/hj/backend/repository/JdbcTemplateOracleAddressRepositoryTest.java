package hj.backend.repository;

import hj.backend.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcTemplateOracleAddressRepositoryTest {
    @Autowired
    JdbcTemplateOracleAddressRepository repository;

    @Test
    void GetRepository(){
        pln("#repository: "+repository);
    }
    @Test
    void list() {
        List<Address> list = repository.list();
        pln("#list: "+list);
    }

    @Test
    void insert() {
        Address address = new Address(-1L, "도훈", "성북", null);
        Address addressDb = repository.insert(address);
        pln("#insert: "+addressDb);
    }

    @Test
    void delete() {
        boolean flag = repository.delete(20);
        pln("#flag: "+flag);
    }

    private void pln(String str){
        System.out.println(str);
    }
}