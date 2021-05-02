package budgetplanner.dao;

import be.pxl.paj.budgetplanner.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findAccountByFirstNameAndName(String firstName, String name);
	Optional<Account> findAccountByIban(String iban);
}
