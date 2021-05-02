package budgetplanner.dto;

import be.pxl.paj.budgetplanner.entity.Category;

public class CategoryDTO {
	private Long id;
	private String name;

	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
