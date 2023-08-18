package hj.backend.controller;

import hj.backend.domain.Address;
import hj.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("address")
@Controller
public class AddressController {
    @Autowired
    private AddressService service; //인터페이스 Service를 호출해야한다

    @GetMapping("list.do")
    public String list(Model model){
        List<Address> list = service.listS();
        model.addAttribute("list", list);
        return  "/address/list";
    }
    @GetMapping("write.do")
    public String write(){
        return "/address/write";
    }

    @PostMapping("write.do")
    public String write(Address address){
        service.insertS(address);
        return "redirect:list.do";
    }

    @GetMapping("del.do")
    public String delete(long seq){
        service.deleteS(seq);
        return "redirect:list.do";
    }
}
