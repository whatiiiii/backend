package hj.backend;

import hj.backend.mapper.AddressMapper;
import hj.backend.mapper.BoardMapper;
import hj.backend.repository.*;
import hj.backend.service.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class SpringConfig {
//    @Autowired DataSource dataSource;  //Jdbc + JdbcTemplate 이용
//    @Autowired AddressMapper mapper; //Mybatis
    @Autowired BoardMapper boardmapper;  //Mybatis
    @Autowired EntityManager em; //JPA를 이용
    @Autowired SpringDataJpaOracleAddressRepository repository11; //SpringDataJpa
    @Autowired
    SpringDataJpaOracleBoardRepository repository2;
    @Autowired

    /*
    @Bean
    public AddressRepository addressRepository(){
        //return new JdbcOracleAddressRepository(dataSource);
        //return new JdbcTemplateOracleAddressRepository(dataSource);
        return new JpaOracleAddressRepository(em);
    }
    */

    @Bean
    public AddressService addressService(){ //Jdbc + MyBatis + JPA + SpringDataJpa
        //return new JdbcOracleAddressService(addressRepository());
        //return new MybatisAddressService(mapper);
        //return new JpaAddressService(addressRepository());
        return new SpringDataJpaAddressService(repository11);
   }
    /*
    @Bean
    public BoardRepository boardRepository(){
        //return new JdbcOracleBoardRepository(dataSource);
        //return new JdbcTemplateOracleBoardRepository(dataSource);
        return new JpaOracleBoardRepository(em);
    }*/
    //@Bean

    public BoardService boardService(){
        //return new JdbcOracleBoardService(boardRepository());
        //return new MybatisBoardService(boardmapper);
      //  return new JpaBoardService(boardRepository());
          return new SpringDataJpaBoardService(repository2);
    }




}