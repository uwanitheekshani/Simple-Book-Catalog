package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookDTO {
    private String bookId;
    private String category;
    private String title;
    private String author;
    private double price;
}
