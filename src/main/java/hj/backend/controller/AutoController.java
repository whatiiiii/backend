package hj.backend.controller;

import hj.backend.domain.Address;
import hj.backend.service.AddressAjaxService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("auto")
@Controller
public class AutoController {
    @Autowired
    private final AddressAjaxService addressAjaxService;
    @Autowired
    public AutoController(AddressAjaxService addressAjaxService) {
        this.addressAjaxService = addressAjaxService;
    }

    @GetMapping("auto.do")
    public String showView(){
        return "auto/autocomplete";
    }
    @PostMapping("autoData.do")
    public @ResponseBody List<Address> getAddressList(String writer){
        System.out.println("@@AutoController getAddressList() writer: "+writer);
        List<Address> list = addressAjaxService.getListByNames(writer);
        return list;
    }
}
