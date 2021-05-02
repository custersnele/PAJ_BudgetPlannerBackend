package be.pxl.paj.budgetplanner.dao;

import be.pxl.paj.budgetplanner.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {

	// REMOVE IF NECESSARY
	void save(Account account);
}
