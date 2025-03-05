package mark.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class StockResponse {

    private String symbol;
    private Float value;
    private Date date;
    private Long usersTracking;
}
