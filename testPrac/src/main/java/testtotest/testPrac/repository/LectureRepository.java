package testtotest.testPrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testtotest.testPrac.entity.lecture.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
