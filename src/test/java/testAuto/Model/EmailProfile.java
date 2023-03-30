package testAuto.Model;

public class EmailProfile {

	private String EmaillAdd;
	private String PasswordOne;
	private String PasswordTwo;
	private String TimeStamp;	
	private String VerificationCode;
	private String RecoveryEmail;
	private String VerificationEmail;

	public EmailProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailProfile(String emaillAdd, String passwordOne, String passwordTwo, String timeStamp,
			String verificationCode, String recoveryEmail) {
		super();
		EmaillAdd = emaillAdd;
		PasswordOne = passwordOne;
		PasswordTwo = passwordTwo;
		TimeStamp = timeStamp;
		VerificationCode = verificationCode;
		RecoveryEmail = recoveryEmail;
	}

	public String getEmaillAdd() {
		return EmaillAdd;
	}

	public void setEmaillAdd(String emaillAdd) {
		EmaillAdd = emaillAdd;
	}

	public String getPasswordOne() {
		return PasswordOne;
	}

	public void setPasswordOne(String passwordOne) {
		PasswordOne = passwordOne;
	}

	public String getPasswordTwo() {
		return PasswordTwo;
	}

	public void setPasswordTwo(String passwordTwo) {
		PasswordTwo = passwordTwo;
	}	

	public String getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}

	public String getVerificationCode() {
		return VerificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		VerificationCode = verificationCode;
	}	

	public String getRecoveryEmail() {
		return RecoveryEmail;
	}

	public void setRecoveryEmail(String recoveryEmail) {
		RecoveryEmail = recoveryEmail;
	}	

	public String getVerificationEmail() {
		return VerificationEmail;
	}

	public void setVerificationEmail(String verificationEmail) {
		VerificationEmail = verificationEmail;
	}

	@Override
	public String toString() {
		return "EmailProfile [EmaillAdd=" + EmaillAdd + ", PasswordOne=" + PasswordOne + ", PasswordTwo=" + PasswordTwo
				+ ", TimeStamp=" + TimeStamp + ", VerificationCode=" + VerificationCode + ", RecoveryEmail="
				+ RecoveryEmail + "]";
	}

	
	
	

}
