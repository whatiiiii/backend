package hj.backend.repository;

import hj.backend.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcTemplateOracleBoardRepositoryTest {
    @Autowired
    JdbcTemplateOracleBoardRepository repository;

    @Test
    void GetRepository(){
        pln("#repository: "+repository);
    }
    @Test
    void list() {
        List<Board> list = repository.list();
        pln("#list: "+list);
    }

    @Test
    void insert() {
        Board board = new Board(-1L, "현지", "111@naver.com","안녕","안녕", null, null);
        Board boardDb = repository.insert(board);
        pln("#insert: "+boardDb);
    }

    @Test
    void contentList() {
        Board board = repository.contentList(16);
        pln("#contentList: "+board);
    }

    @Test
    void update() {
        Board board = new Board(16, null,"333@naver.com","bye","bye",null,null);
        repository.update(board);
    }

    @Test
    void delete() {
        boolean flag = repository.delete(19);
        pln("#flag: "+flag);
    }

    void pln(String str){
        System.out.println(str);
    }
}