package hj.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("ajax2")
public class AjaxTest2Controller {
    @Autowired
    private final AddressAjaxService addressAjaxService;
    @Autowired
    public AjaxTest2Controller(AddressAjaxService addressAjaxService) {
        this.addressAjaxService = addressAjaxService;
    }

    @GetMapping("search1.do")
    public void search1(long seq, HttpServletResponse response){
        Address address = addressAjaxService.getBySeqS(seq);
        ObjectMapper om = new ObjectMapper();
        try{
            String addressJson = om.writeValueAsString(address);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch (JsonProcessingException je){
        }catch(IOException ie) {}
    }

    @PostMapping("search2.do")
    public void search2(String name, HttpServletResponse response){
        List<Address> list = addressAjaxService.getListByNames(name);
        //pln("#AjaxTest1Controller search2() list: " + list);

        ObjectMapper om = new ObjectMapper();
        try {
            String addressJson = om.writeValueAsString(list);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch(JsonProcessingException je) {
        }catch(IOException ie) {}

    }

    void pln(String str){
        System.out.println(str);
    }


}
