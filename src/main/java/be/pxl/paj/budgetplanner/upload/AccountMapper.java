package be.pxl.paj.budgetplanner.upload;

import be.pxl.paj.budgetplanner.entity.Account;
import be.pxl.paj.budgetplanner.exception.InvalidLineException;

public class AccountMapper {

	public static Account map(String line) throws InvalidLineException {
		String[] split = line.split(";");
		if (split.length != 8) {
			throw new InvalidLineException("Invalid number of fields in line [" + line + "]");
		}
	    return new Account(split[2], split[1], split[0]);
	}
}
