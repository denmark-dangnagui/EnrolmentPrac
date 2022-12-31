package testtotest.testPrac.entity.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testtotest.testPrac.entity.application.Application;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String number;

    private int applyCnt;

    @OneToMany
    private List<Application> applicationList = new ArrayList<>();

    @Builder
    public Member(String email, String password, String userName, Role role, String number, int applyCnt) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.role = role;
        this.number = number;
        this.applyCnt = applyCnt;
    }
}
