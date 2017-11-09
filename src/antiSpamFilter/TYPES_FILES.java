package antiSpamFilter;

public enum TYPES_FILES {
	HAM_SPAM,
	RULES;
	
	double getValue() {
        switch (this) {
            case HAM_SPAM:
                return 2;
            case RULES:
                return 1;
            default:
                throw new AssertionError("Invalid file");
        }
    }
}
