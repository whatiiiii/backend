package hj.backend.repository;

import hj.backend.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcOracleAddressRepositoryTest {
    @Autowired
    JdbcOracleAddressRepository repository;
    @Test
    void testGetConnection() {
        pln("#repository: "+repository);
        pln("#con: "+repository.getConnection());
    }

    @Test
    void testList() {
        List<Address> list = repository.list();
        pln("#list: "+list);

    }

    @Test
    void testInsert() {
        Address address = new Address(-1L, "현주", "부산", null);
        Address addressDb = repository.insert(address);
        pln("#insert: "+addressDb);
    }

    @Test
    void testDelete() {
        boolean flag = repository.delete(4);
        pln("#flag: "+flag);
    }

    void pln(String str){
        System.out.println(str);
    }
}