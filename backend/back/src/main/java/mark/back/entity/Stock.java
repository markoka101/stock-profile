package mark.back.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column
    private String symbol;

    /*
    Value will be in USD
    Value will hold the last price found
     */
    @NonNull
    @Column
    private Float value;

    /*
    The date is the last time the price was updated
     */
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column
    private Date date;

    @ManyToMany(mappedBy = "stocksSaved")
    @JsonIgnore
    private Set<User> saved;

}