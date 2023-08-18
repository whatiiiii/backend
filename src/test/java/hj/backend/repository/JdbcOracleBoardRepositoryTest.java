package hj.backend.repository;

import hj.backend.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcOracleBoardRepositoryTest {
    @Autowired
    JdbcOracleBoardRepository repository;
    @Test
    void testGetConnection() {
        pln("#repository: "+repository);
        pln("#con: "+repository.getConnection());
    }

    @Test
    void testList() {
        List<Board> list = repository.list();
        pln("#list: "+list);
    }

    @Test
    void testInsert() {
        Board board = new Board(-1L, "현주", "111@naver.com","안녕","안녕", null, null);
        Board boardDb = repository.insert(board);
        pln("#insert: "+boardDb);
    }

    @Test
    void testContentList() {
        Board board = repository.contentList(16);
        pln("#contentList: "+board);
    }

    @Test
    void testUpdate() {
        Board board = new Board(16, null,"222@naver.com","바이","바이",null,null);
        repository.update(board);

    }

    @Test
    void testDelete() {
        boolean flag = repository.delete(1);
        pln("#flag: "+flag);
    }

    void pln(String str){
        System.out.println(str);
    }
}