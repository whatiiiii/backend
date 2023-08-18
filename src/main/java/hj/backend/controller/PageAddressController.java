package hj.backend.controller;

import hj.backend.domain.Address;
import hj.backend.dto.AddressListResult;
import hj.backend.service.PageAddressService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("address_page")
@AllArgsConstructor
@Controller
public class PageAddressController {
    private final PageAddressService service; //AutoInjection //앞에 AutoWired 안써도됨

    @GetMapping("list.do")
    public String list(@PageableDefault(size=3, sort="seq", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        AddressListResult listResult= service.getAdddressListResult(pageable);
        model.addAttribute("listResult", listResult);
        return  "/address_page/list";
    }
    @GetMapping("write.do")
    public String write(){
        return "/address_page/write";
    }

    @PostMapping("write.do")
    public String write(Address address){
        service.insertS(address);
        return "redirect:list.do";
    }

    @GetMapping("del.do")
    public String delete(long seq, HttpSession session, HttpServletRequest request,
                         Object page, HttpServletResponse response,
                         ServletContext application){
        //System.out.println("@@application: "+application); //AutoInjection이 불가
        application = session.getServletContext();
        System.out.println("##application: "+application);
        System.out.println("@@session: "+session);
        System.out.println("@@request: "+request);
        System.out.println("@@page: "+page);
        System.out.println("@@response: "+response);
        service.deleteS(seq);
        return "redirect:list.do";
    }
    
}
