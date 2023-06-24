package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author acer on 6/24/2023.
 * @project Book_Catalog_BackEnd
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Book {

    @Id
    private String bookId;
    private String category;
    private String title;
    private String author;
    private BigDecimal price;

}
