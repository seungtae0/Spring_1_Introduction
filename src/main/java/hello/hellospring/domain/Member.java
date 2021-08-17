package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리!
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // PK. DB에서 시퀀스 같은 존재.
    private Long id;

    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
