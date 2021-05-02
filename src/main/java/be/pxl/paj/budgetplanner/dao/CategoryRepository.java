package be.pxl.paj.budgetplanner.dao;


import be.pxl.paj.budgetplanner.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {

	// REMOVE IF NECESSARY
	void save(Category category);
}
