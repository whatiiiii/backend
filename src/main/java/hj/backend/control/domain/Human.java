package hj.backend.control.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //parameter있는 생성자
@NoArgsConstructor //기본 생성자
@Data //setter, getter
public class Human {
    private String name;
    private  int age;
}
