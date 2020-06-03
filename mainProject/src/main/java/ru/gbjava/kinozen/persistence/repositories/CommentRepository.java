package ru.gbjava.kinozen.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.services.pojo.CommentPojo;
import ru.gbjava.kinozen.services.pojo.UserPojo;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);

    //фильтрация по userID
    @Query("SELECT c FROM Comment c WHERE (c.user.id=:userid)")
    List<CommentPojo> CommentedListByUserID(Long userid);

}
