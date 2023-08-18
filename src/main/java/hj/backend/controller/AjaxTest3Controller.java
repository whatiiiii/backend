package hj.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import hj.backend.domain.Address;
import hj.backend.service.AddressAjaxService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("ajax3")
public class AjaxTest3Controller {
    @Autowired
    private final AddressAjaxService addressAjaxService;
    @Autowired
    public AjaxTest3Controller(AddressAjaxService addressAjaxService) {
        this.addressAjaxService = addressAjaxService;
    }

    @GetMapping("search1.do")
    @ResponseBody
    public Address search1(long seq){
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }

    @PostMapping("search2.do")
    @ResponseBody
    public List<Address> search2(String name, HttpServletResponse response){
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;
    }

    @GetMapping("test.do")
    //@ResponseBody // test라는 글자가 return됨
    public String test(){
      //  return "test"; //test.jsp
        return "../index";
    }

}
