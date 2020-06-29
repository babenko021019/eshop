package info.unproj.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {

    private Integer id;

    @JsonProperty("user")
    private Integer userId;

    @JsonProperty("user")
    private Integer userLogin;

    private Integer userBalance;
}
