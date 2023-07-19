package farmSystem.zerozeronbbang.domains.category.dto;

import farmSystem.zerozeronbbang.domains.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResFindCategoriseDto {
    private Long id;
    private int categoryCode;
    private String categoryName;
    @Builder
    public ResFindCategoriseDto(Category category) {
        this.categoryCode=category.getCategoryCode();
        this.id=category.getId();
        this.categoryName=category.getCategoryName();
    }
}
