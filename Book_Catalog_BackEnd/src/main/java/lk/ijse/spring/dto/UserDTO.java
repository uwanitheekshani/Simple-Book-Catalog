package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author acer on 6/25/2023.
 * @project Book_Catalog_BackEnd
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDTO {
    private String email;
    private String password;
}
