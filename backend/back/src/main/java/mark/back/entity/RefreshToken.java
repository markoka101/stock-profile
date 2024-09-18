package mark.back.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;



@Getter
@Setter
@RequiredArgsConstructor
public class RefreshToken {
    @Id
    String id;

    @Lazy
    private User user;
}