package hj.backend.controller;

import hj.backend.domain.Address;
import hj.backend.service.AddressAjaxService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("ajax4")
@RestController
public class AjaxTest4Controller {

    private final AddressAjaxService addressAjaxService;

    @GetMapping("search1.do")
    public Address search1(long seq){
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }

    @PostMapping("search2.do")
    public List<Address> search2(String name, HttpServletResponse response){
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;
    }

    @GetMapping("txt")
    public String getText(){
        return "good"; //responseBody가 restController에 의해 생략되어 있으므로 jsp가 아닌, String인 good이 나온다
    }

}
