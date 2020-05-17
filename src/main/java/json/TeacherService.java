package json;

import javax.validation.constraints.NotNull;

public interface TeacherService {
    /**
     *
     * @param teacherID
     * @param review
     * @throws javax.persistence.EntityNotFoundException if source is null
     */
    void addReview(@NotNull String teacherID, @NotNull Review review);
}