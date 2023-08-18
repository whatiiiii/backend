package hj.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import hj.backend.dto.ChartDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping("chart")
@Controller
public class ChartController {
    @GetMapping("chart.do")
    public String showView(){
        return "chart/chart";
    }

    private Random r = new Random();
    @PostMapping("chartData.do")
    public @ResponseBody List<ChartDTO> getChartData(){
        List<ChartDTO> list = new ArrayList<ChartDTO>();
        String items[] = {"봄", "여름", "가을", "겨울"};

        for(int i=0; i<items.length; i++){
            int number = r.nextInt(100);
            ChartDTO dto = new ChartDTO(items[i], number);
            list.add(dto);
        }

        return list;
    }
}
