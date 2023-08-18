package hj.backend.controller;

import hj.backend.domain.Board;
import hj.backend.service.BoardAjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest_board")
@CrossOrigin(origins ="*", maxAge = 3600)
public class BoardRestController {
    @Autowired
    private final BoardAjaxService boardAjaxService;
    @Autowired
    public BoardRestController(BoardAjaxService boardAjaxService) {
        this.boardAjaxService = boardAjaxService;
    }

    @GetMapping("create1")
    public void create1(Board board){
        boardAjaxService.insertS(board);
    }

    @PostMapping("create2")
    public void create2(@RequestBody Board board){
        boardAjaxService.insertS(board);
    }
    //http://127.0.0.1:8080/rest_board/create2
    //{"writer":"아이", "email":"@@", "subject":"수학", "content":"내용"}

    @GetMapping("read")
    public List<Board> read(){
        List<Board> list = boardAjaxService.listS();
        return list;
    }
    //http://127.0.0.1:8080/rest_board/read

    @PutMapping("update")
    public void update(@RequestBody Board board){
        boardAjaxService.updateS(board);
    }
    //http://127.0.0.1:8080/rest_board/update
    //{"seq":"4", "email":"@@@@@", "subject":"영어", "content":"내용없음"}

    @GetMapping("read/{seq}")
    public Board read(@PathVariable long seq){
        Board board = boardAjaxService.getBySeqS(seq);
        return board;
    }
    //http://127.0.0.1:8080/rest_board/read/39

    @DeleteMapping("delete/{seq}")
    public void delete(@PathVariable long seq){
        boardAjaxService.deleteS(seq);
    }
    //http://127.0.0.1:8080/rest_board/delete/3
}

