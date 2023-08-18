package hj.backend.control;

import hj.backend.control.domain.Human;
import hj.backend.control.domain.HumanList;
import hj.backend.control.domain.ToDo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RequestMapping("test")
@Controller
public class TestController {

   @RequestMapping("")//모든 메소드가 접근 가능하다
    public void m00(){
        pln("#m00() -default URL");
    }

    @RequestMapping("/base1")//모든 메소드가 접근 가능하다
    public void m01(){
        pln("#m01() -Get, Post, Put, Delete,..지원");
    }

    @GetMapping("/base2") //get만 지원
    public void m02(){
        pln("#m02() -Only Get만 지원");
    }
    @RequestMapping(value = "/base3", method = {RequestMethod.GET, RequestMethod.POST}) //get과 post만 지원
    public void m03(){
        pln("#m02() -Get과 POST 지원");
    }

    @GetMapping(value = "/param1") //get만 지원
    public void m04(Human dto){
        pln("#m04() dto: "+dto);
    }

    @GetMapping(value = "/param2") //get만 지원
    public void m05(@RequestParam("name") String na, @RequestParam("age") int a){
        pln("#m05() na: "+na +", a: "+a);
    }

    @GetMapping(value = "/param3") //가변배열
    public void m06(@RequestParam ArrayList<String>names ){
        pln("#m06() names: "+names);
    }

    @GetMapping(value = "/param4") //
    public void m07(@RequestParam("ns") ArrayList<String>names ){
        pln("#m07() names: "+names);
    }


    @GetMapping(value = "/param5") //배열
    public void m08(@RequestParam String[] names ){
        pln("#m08() names: "+names);
        for(String name : names){
            pln("name: "+name);
        }
    }

    @GetMapping(value = "/param6") //배열
    public void m09(HumanList list){
        pln("#m09() list: "+list);
    }

    @GetMapping(value = "/param7")
    public void m10(Human dto, @RequestParam int page){
        pln("#m10() dto:"+dto+", page: "+page);
    }

    @RequestMapping(value = "/param8")
    public void m11(ToDo dto) {
        pln("#m11() - dto: " + dto);

        Date d = dto.getCdate();
        Calendar c  = Calendar.getInstance();
        c.setTime(d);

        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int dt = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR);
        int mi = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);

        pln(y + "년 " + m + "월 " + dt + "일 "+ h + ":"+mi+":" + s);
    }

    @GetMapping("/json1")
    public ResponseEntity<String> m12() {
        String msg = "{\"name\":\"홍길동\", \"age\":20}"; //JSON
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");

        return new ResponseEntity<String>(msg, headers, HttpStatus.OK);
    }

    @GetMapping("/json2")
    public @ResponseBody Human m13(){
        Human man = new Human("강감찬", 10);
        return man;
    }


    void pln(String str){
       System.out.println(str);
   }

}
