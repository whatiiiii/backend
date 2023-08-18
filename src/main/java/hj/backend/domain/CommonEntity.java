package hj.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass //공통되는 entity 정의
@AllArgsConstructor(access = AccessLevel.PROTECTED) //protected 생성자를 만들어라 , 기본값은 public 이다 
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected 생성자를 만들어라 , 기본값은 public 이다
@Data
@EntityListeners(AuditingEntityListener.class) //@entity 을 써주지않아도 main spring에 등록
public class CommonEntity {
    @CreatedDate //날짜 자동 생성됨
    @Column(updatable = false, nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime rdate;
    @LastModifiedDate //마지막에 수정된 날짜
    @Column(nullable = false) //updatable 기본값이 true이기 때문에 따로 명시하지 않아줘도 된다
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime udate;

}
