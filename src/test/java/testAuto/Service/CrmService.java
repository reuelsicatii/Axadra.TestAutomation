package testAuto.Service;

import org.apache.commons.text.RandomStringGenerator;

public class CrmService {

	public String generateRandomString(int length) throws Throwable {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

		return generator.generate(length);

	}

}
