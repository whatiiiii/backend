package hj.backend.controller;

import hj.backend.domain.Board;
import hj.backend.dto.BoardListResult;
import hj.backend.service.BoardService;
import hj.backend.service.PageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("board")
@Controller
public class BoardController {
    @Autowired
    private PageBoardService service;


    @GetMapping("list.do")
    public String list(@PageableDefault(size=3, sort="seq", direction = Sort.Direction.DESC) Pageable pageable, Model model){
     //   List<Board> list = service.listS();
     //   model.addAttribute("list", list);
        BoardListResult listResult = service.getBoardListResult(pageable);
        model.addAttribute("listResult", listResult);
        return "/board/list";
    }

    @GetMapping("write.do")
    public String write(){
        return "/board/write";
    }

    @PostMapping("write.do")
    public String write(Board board){
            service.insertS(board);
            return "redirect:list.do";
    }

    @GetMapping("content.do")
    public String content(long seq, Model model){
        Board board = service.contentListS(seq);
        model.addAttribute("content",board);
        return "/board/content";
    }

    @GetMapping("update.do")
    public String update(long seq, Model model){
        Board board = service.contentListS(seq);
        model.addAttribute("content",board);
        return "/board/update";
    }

    @PostMapping("update.do")
    public String update(Board board){
        service.updateS(board);
        return "redirect:list.do";
    }

    @GetMapping("del.do")
    public String delete(long seq){
        service.deleteS(seq);
        return "redirect:list.do";
    }


}
