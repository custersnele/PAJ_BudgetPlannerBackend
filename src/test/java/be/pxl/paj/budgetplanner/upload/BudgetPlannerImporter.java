package be.pxl.paj.budgetplanner.upload;


import be.pxl.paj.budgetplanner.dao.AccountRepository;
import be.pxl.paj.budgetplanner.dao.CategoryRepository;
import be.pxl.paj.budgetplanner.entity.Account;
import be.pxl.paj.budgetplanner.entity.Category;
import be.pxl.paj.budgetplanner.entity.Payment;
import be.pxl.paj.budgetplanner.exception.InvalidLineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.HashMap;
import java.util.Map;

@Component
public class BudgetPlannerImporter {
	private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
	private static final PathMatcher CSV_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Async
	public void readCsv(MultipartFile file) {
		Map<String, Account> accounts = new HashMap<>();
		Map<String, Category> categories = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) { // try-with-resources
			String line = null;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				try {
					Account account = AccountMapper.map(line);
					if (accounts.containsKey(account.getIban())) {
						Payment payment = PaymentMapper.map(line, categories);
						accounts.get(account.getIban()).addPayment(payment);
					} else {
						account.addPayment(PaymentMapper.map(line, categories));
						accounts.put(account.getIban(), account);
					}
				} catch (InvalidLineException e) {
					LOGGER.error("Error while mapping line: {}", e.getMessage());
				}
			}
			for (Category category : categories.values()) {
				categoryRepository.save(category);
			}
			for (Account account : accounts.values()) {
				accountRepository.save(account);
			}
		} catch (IOException e) {
			LOGGER.fatal("An error occurred while processing the file.");
		}

	}
}
