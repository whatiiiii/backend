package hj.backend.service;

import hj.backend.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcOracleAddressServiceTest {
    @Autowired
    AddressService service;
    @Test
    void testListS() {
        List<Address> list = service.listS();
        pln("@list: "+list);
    }

    @Test
    void testInsertS() {
        Address address = new Address(-1L, "수경", "동작구", null);
        Address addressDb = service.insertS(address);
        pln("@insert: "+addressDb);
    }

    @Test
    void testDeleteS() {
        long seq = 10;
        boolean flag = service.deleteS(seq);
        pln("@flag: "+flag);
    }

    void pln(String str){
        System.out.println(str);
    }
}