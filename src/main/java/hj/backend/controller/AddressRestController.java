package hj.backend.controller;

import hj.backend.domain.Address;
import hj.backend.service.AddressAjaxService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("rest_addr")
@RestController
@CrossOrigin(origins = "*", maxAge=3600) //##매우중요!!!
public class AddressRestController {

    private final AddressAjaxService addressAjaxService;

    //(1)Create(insert )
    @PostMapping("create1")
    public void create1(Address address){//파라미터를 javascript object로 받을 때,,
        addressAjaxService.insertS(address);
    }
    //@GetMapping("create1")으로 테스팅
    //<a href="rest_addr/create1?name=오늘은&addr=수요일">Restful API</a><br/><br/>

    @PostMapping("create2")
    public void create2(@RequestBody Address address){ //json으로 받을때는 @RequestBody를 써준다  //파라미터를 json으로 받을 때ㅡ,,
        addressAjaxService.insertS(address);
    }
    //http://127.0.0.1:8080/rest_addr/create2
    //{"seq":-1, "name":"현동", "addr":"오예스"}
    //{"name":"현동", "addr":"오예스"}

    //(2) Read(select)
    @GetMapping("read")
    public List<Address> read(){
        List<Address> list = addressAjaxService.listS();
        return list;
    }

    @GetMapping("read/{seq}")
    public Address read(@PathVariable long seq){ //"read/{seq}" 받아온값을 넣어준다
         Address address= addressAjaxService.getBySeqS(seq);
        return address;
    }
    //http://127.0.0.1:8080/rest_addr/read/2

    //아래 방법도 가능은 하지만 @GetMapping("read/{seq}")와 공존 할 수 없음
    /*
    @GetMapping(value="read", params={"na"})
    public List<Address> read(@RequestParam("na") String name){
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;
    }

    //http://127.0.0.1:8080/rest_addr/read?na=현
    */

    @DeleteMapping("delete/{seq}")
    public void delete(@PathVariable long seq){
        addressAjaxService.deleteS(seq);
    }
    //http://127.0.0.1:8080/rest_addr/delete/37



}
