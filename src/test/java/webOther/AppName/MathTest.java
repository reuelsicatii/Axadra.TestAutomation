package webOther.AppName;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import testAuto.Service.LeadFinderService;

public class MathTest {

	public static void main(String[] args) throws Throwable {

		/*
		 * System.out.println(90 + 0 + 65); System.out.println((double) (90 + 0 + 65) /
		 * 3); System.out.println(Math.round((double) (90 + 0 + 65) / 3));
		 * System.out.println(Math.round(51.6777));
		 */

		// LeadFinder Service
		// ==========================================
		LeadFinderService leadFinderService = new LeadFinderService();

		HashMap<String, String> searchDetails = leadFinderService.RetrieveKeywordLocationFrom("PROD_CENTRAL");
		String tempKeyword[] = searchDetails.get("keyword").split("\\s");
		StringBuffer finalKeyword = new StringBuffer();
		for (String w : tempKeyword) {
			finalKeyword.append(StringUtils.capitalize(w) + " ");
		}
		


		System.out.println("//h2[text()='" + finalKeyword.toString() + "in "
				+ searchDetails.get("location") + "']");

	}

}
