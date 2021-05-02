package budgetplanner.upload;

import be.pxl.paj.budgetplanner.entity.Category;
import be.pxl.paj.budgetplanner.entity.Payment;
import be.pxl.paj.budgetplanner.util.exception.InvalidLineException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PaymentMapper {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
	private static final NumberFormat DECIMAL_FORMAT = DecimalFormat.getInstance();

	public static Payment map(String paymentLine, Map<String, Category> categories) throws InvalidLineException {
		String[] split = paymentLine.split(";");
		if (split.length != 8) {
			throw new InvalidLineException("Invalid number of fields in line [" + paymentLine + "]");
		}
		try {
			Category category = categories.get(split[3]);
			if (category == null) {
				categories.put(split[3], new Category(split[3]));
			}
			Payment payment = new Payment(LocalDate.parse(split[7], FORMATTER), split[5], DECIMAL_FORMAT.parse(split[4]).doubleValue(), split[6]);
			payment.setCategory(category);
			return payment;
		} catch (ParseException e) {
			throw new InvalidLineException("Invalid data in line [" + paymentLine + "]", e);
		}
	}
}
