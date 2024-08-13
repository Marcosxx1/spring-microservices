package com.loans.constants;

public class LoansConstants {

    private LoansConstants() {}

    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "999 Av, Somewhere XY";

    public static final String MESSAGE_200 = "response.success";
    public static final String MESSAGE_201 = "account.creation.success";
    public static final String MESSAGE_204 = "account.deletion.success";
    public static final String MESSAGE_404 = "resource.not.found";
    public static final String MESSAGE_500 = "error.general";

    public static final String RESOURCE_NOT_FOUND_WITH_DATA = "resource.not.found-with-data";

    public static final String HOME_LOAN = "Home Loan"; // TODO - INTERNATIONALIZATION
    public static final int NEW_LOAN_LIMIT = 1_00_000; // TODO - INTERNATIONALIZATION
    public static final String MESSAGE_417_UPDATE =
            "Update operation failed. Please try again or contact Dev team"; // TODO - INTERNATIONALIZATION
    public static final String MESSAGE_417_DELETE =
            "Delete operation failed. Please try again or contact Dev team"; // TODO - INTERNATIONALIZATION
}
