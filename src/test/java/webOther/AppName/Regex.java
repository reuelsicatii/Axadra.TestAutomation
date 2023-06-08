package webOther.AppName;

import java.util.regex.*;

public class Regex {

	public static void main(String[] args) {

		String listName = "Saveleadslink_not_consistent_all_leads (3)";

		if (Pattern.matches("^.*\\b[0-9]\\b.$", listName)) {

			System.out.println("1 Digit Save to List");

		}

		System.out.println("===========================");

		if (Pattern.matches("^.*\\b[0-9][0-9]\\b.$", listName)) {

			System.out.println("2 Digit Save to List");

		}

		System.out.println("===========================");

		if (Pattern.matches("^.*\\b[0-9][0-9][0-9]\\b.$", listName)) {

			System.out.println("3 Digit Save to List");

		}

		System.out.println("===========================");

		// String listName = "Randomname (22)";
		// System.out.println(listName.substring(listName.length() - 3,
		// listName.length() - 1));

	}

}
