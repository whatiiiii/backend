package hj.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name="ADDRESS_SEQ_GENERATOR", sequenceName="ADDRESS_SEQ", initialValue = 1, allocationSize = 1)//Oracle일때
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ_GENERATOR")//Oracle일때
    private long seq;
    //@Column(name="username") //DB컬럼이름이 username일 경우
    private String name;
    private String addr;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul") //시간 년월일 시분초까지 다 나오게 함
    @CreationTimestamp //추가
    private Date rdate;
}
