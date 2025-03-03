package mark.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


@Getter
@Setter
@Entity
public class RefreshToken {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false)
    private String token;

    @OneToOne
    private User user;
}