package hj.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name="FILEUP_SEQ_GENERATOR", sequenceName="FILEUP_SEQ", initialValue = 1, allocationSize = 1)
@Table(name="fileup") //테이블 명과 클래스 명이 다른경우 명시해줘야한다
public class FileUp {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILEUP_SEQ_GENERATOR")
    @Column(name="file_id")
    private long id;
    private String orgnm;
    private String savednm;
    private String savedpath;

    @Builder
    public FileUp(long id, String orgnm, String savednm, String savedpath){
        this.id = id;
        this.orgnm = orgnm;
        this.savednm = savednm;
        this.savedpath =savedpath;
    }

}
