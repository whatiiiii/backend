package hj.backend.repository;

import hj.backend.domain.Address;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
public class JpaOracleAddressRepository implements AddressRepository {
  //  @Autowired
    private final EntityManager em;

    public JpaOracleAddressRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Address> list() {
        List<Address> list = em.createQuery("select a from Address a order by a.seq desc", Address.class).getResultList();
        return list;
    }

    @Override
    public Address insert(Address address) { //insert, update, delete 기능은 jpql이 필요없음, 자동으로 해줌
        em.persist(address);
        return address;
    }

    public List<Address> findByName(String name){
        List<Address> list = em.createQuery("select a from Address a where a.name=:name", Address.class).setParameter("name", name).getResultList();
        return list;
    }
    public Address findBySeq(long seq){
        Address address = em.find(Address.class, seq);
        return address;
    }
    @Override
    public boolean delete(long seq) { //interface에 void delete(long) 또는 Address delete(long seq)으로 할껄,,
        Address address = findBySeq(seq);
        em.remove(address);
        return true;
    }
}
