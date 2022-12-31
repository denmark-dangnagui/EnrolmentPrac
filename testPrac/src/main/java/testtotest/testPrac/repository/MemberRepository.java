package testtotest.testPrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testtotest.testPrac.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
