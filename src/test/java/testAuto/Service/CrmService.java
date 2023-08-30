package testAuto.Service;

import org.apache.commons.text.RandomStringGenerator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.Random;
import testAuto.Service.WebAuditService;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrmService {

	WebAuditService webAuditService = new WebAuditService();

	public String generateRandomString(int length) throws Throwable {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

		return generator.generate(length);

	}

	public String generateRandomPhoneNumber() throws Throwable {
		Random random = new Random();

		// Generate the area code (3 digits)
		// Ensure a minimum of 100
		int areaCode = random.nextInt(900) + 100;

		// Generate the exchange code (3 digits)
		// Ensure a minimum of 100
		int exchangeCode = random.nextInt(900) + 100;

		// Generate the subscriber number (4 digits)
		// Ensure a minimum of 1000
		int subscriberNumber = random.nextInt(9000) + 1000;

		return String.format("(%03d) %03d-%04d", areaCode, exchangeCode, subscriberNumber);

	}

	public void updateCSVFileRecord(String InputFileName, String OutputFileName) throws Throwable {

		System.out.println(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + InputFileName);
		System.out.println(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + OutputFileName);

		// generate randomName
		String randomName = generateRandomString(4);

		// Update values
		String[] updatedValues = { (randomName + new SimpleDateFormat("_yyMMddHHmmss").format(new Date()) + "_csv"), randomName + "@gmail.com",
				webAuditService.RetrieveURLFrom("PROD_CENTRAL"), "United States", generateRandomPhoneNumber(),
				"Uncategorized", "Opportunity" };

		// try-with-resources
		// used to automatically close these resources when they are no longer needed,
		// reducing the risk of resource leaks
		try (BufferedReader reader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + InputFileName));
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + OutputFileName));) {
			List<String> lines = new ArrayList<>();

			String line;

			// Read all lines from the original file
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			// Update the second row
			if (lines.size() == 2) {
				lines.set(1, String.join(",", updatedValues));
			}

			// Write the updated content to the new file
			for (String updatedLine : lines) {
				writer.write(updatedLine);
				writer.newLine();
			}

			System.out.println(OutputFileName + " file updated successfully.");
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void updateTXTFileRecord(String InputFileName, String OutputFileName) throws Throwable {

		System.out.println(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + InputFileName);
		System.out.println(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + OutputFileName);

		// generate randomName
		String randomName = generateRandomString(4);

		// Update values
		String[] updatedValues = { randomName + new SimpleDateFormat("_yyMMddHHmmss").format(new Date()) + "_txt", randomName + "@gmail.com",
				webAuditService.RetrieveURLFrom("PROD_CENTRAL"),
				"350 S Grand Ave, Los Angeles, CA 90071, United States", generateRandomPhoneNumber(), "Uncategorized",
				"Opportunity" };

		// try-with-resources
		// used to automatically close these resources when they are no longer needed,
		// reducing the risk of resource leaks
		try (BufferedReader reader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + InputFileName));
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + OutputFileName));) {
			List<String> lines = new ArrayList<>();

			String line;

			// Read all lines from the original file
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			// Update the second row
			if (lines.size() == 2) {
				lines.set(1, String.join("\t", updatedValues));
			}

			// Write the updated content to the new file
			for (String updatedLine : lines) {
				writer.write(updatedLine);
				writer.newLine();
			}

			System.out.println(OutputFileName + " file updated successfully.");
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void updateXLSFileRecord(String FileName) throws Throwable {

		// try-with-resources
		// used to automatically close these resources when they are no longer needed,
		// reducing the risk of resource leaks

		System.out.println(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + FileName);
		try (FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + FileName);
				Workbook workbook = new HSSFWorkbook(fileInputStream)) {

			// generate randomName
			String randomName = generateRandomString(4);

			// updating first sheet
			Sheet sheet = workbook.getSheetAt(0);

			// updating second row
			Row rowToUpdate = sheet.getRow(1);

			for (int i = 0; i < 7; i++) {

				if (rowToUpdate != null) {

					// Get cell in the row
					Cell cellToUpdate = rowToUpdate.getCell(i);

					// Update the first cell in the row
					if (cellToUpdate != null && i == 0) {
						cellToUpdate.setCellValue(randomName + new SimpleDateFormat("_yyMMddHHmmss").format(new Date()) + "_xls");
					}

					else if (cellToUpdate != null && i == 1) {
						cellToUpdate.setCellValue(randomName + "@gmail.com");
					}

					else if (cellToUpdate != null && i == 2) {
						cellToUpdate.setCellValue(webAuditService.RetrieveURLFrom("PROD_CENTRAL"));
					}

					else if (cellToUpdate != null && i == 3) {
						cellToUpdate.setCellValue("350 S Grand Ave, Los Angeles, CA 90071, United States");
					}

					else if (cellToUpdate != null && i == 4) {
						cellToUpdate.setCellValue(generateRandomPhoneNumber());
					}

					else if (cellToUpdate != null && i == 5) {
						cellToUpdate.setCellValue("Uncategorized");
					}

					else if (cellToUpdate != null && i == 6) {
						cellToUpdate.setCellValue("Opportunity");
					}

					else {
						// Cell doesn't exist, create a new one
						cellToUpdate = rowToUpdate.createCell(i);
						cellToUpdate.setCellValue("Updated Value");
					}
				}

			}

			try (FileOutputStream fileOutputStream = new FileOutputStream(
					System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + FileName)) {
				workbook.write(fileOutputStream);
			}

			System.out.println(FileName + " file updated successfully.");
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void updateXLSXFileRecord(String FileName) throws Throwable {

		// try-with-resources
		// used to automatically close these resources when they are no longer needed,
		// reducing the risk of resource leaks

		System.out.println(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + FileName);
		try (FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + FileName);
				Workbook workbook = new XSSFWorkbook(fileInputStream)) {

			// generate randomName
			String randomName = generateRandomString(4);

			// updating first sheet
			Sheet sheet = workbook.getSheetAt(0);

			// updating second row
			Row rowToUpdate = sheet.getRow(1);

			for (int i = 0; i < 7; i++) {

				if (rowToUpdate != null) {

					// Get cell in the row
					Cell cellToUpdate = rowToUpdate.getCell(i);

					// Update the first cell in the row
					if (cellToUpdate != null && i == 0) {
						cellToUpdate.setCellValue(randomName + new SimpleDateFormat("_yyMMddHHmmss").format(new Date()) + "_xlsx");
					}

					else if (cellToUpdate != null && i == 1) {
						cellToUpdate.setCellValue(randomName + "@gmail.com");
					}

					else if (cellToUpdate != null && i == 2) {
						cellToUpdate.setCellValue(webAuditService.RetrieveURLFrom("PROD_CENTRAL"));
					}

					else if (cellToUpdate != null && i == 3) {
						cellToUpdate.setCellValue("350 S Grand Ave, Los Angeles, CA 90071, United States");
					}

					else if (cellToUpdate != null && i == 4) {
						cellToUpdate.setCellValue(generateRandomPhoneNumber());
					}

					else if (cellToUpdate != null && i == 5) {
						cellToUpdate.setCellValue("Uncategorized");
					}

					else if (cellToUpdate != null && i == 6) {
						cellToUpdate.setCellValue("Opportunity");
					}

					else {
						// Cell doesn't exist, create a new one
						cellToUpdate = rowToUpdate.createCell(i);
						cellToUpdate.setCellValue("Updated Value");
					}
				}

			}

			try (FileOutputStream fileOutputStream = new FileOutputStream(
					System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + FileName)) {
				workbook.write(fileOutputStream);
			}

			System.out.println(FileName + " file updated successfully.");
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
