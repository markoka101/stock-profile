package mark.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mark.back.entity.Stock;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private Set<Stock> stocks;
}
