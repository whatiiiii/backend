package hj.backend.service;

import hj.backend.domain.Address;

import java.util.List;

public interface AddressAjaxService {
    List<Address> listS();
    Address insertS(Address address);
    boolean deleteS(long seq);

    //for Ajax
    Address getBySeqS(long seq);
    List<Address> getListByNames(String name);
}
