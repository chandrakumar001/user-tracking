package json;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class SimpleTeacherService implements TeacherService {

    @Override
    public void addReview(@NotNull String teacherID,
                          Review review) {

    }

    /*private final TeacherDAO teacherDAO;

    public SimpleTeacherService(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }
    
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addReview(String teacherID, Review review) {
        Objects.requireNonNull(teacherID);
        Objects.requireNonNull(review);
        Teacher teacher = teacherDAO
                .findById(UUID.fromString(teacherID))
                .orElseThrow(() -> new EntityNotFoundException(teacherID));
        review.setDate(LocalDate.now());
        if(teacher.getReviews() == null){
            teacher.setReviews(new ArrayList<>());
        }
        teacher.getReviews().add(review);
        teacherDAO.save(teacher);
    }*/
    //url https://developer.okta.com/blog/2019/02/20/spring-boot-with-postgresql-flyway-jsonb

}