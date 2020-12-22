package helpers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import platforms.AndroidPlatform;
import platforms.WebPortal;

public class CommunicationHelper {
	private static Properties emailProperties;

	private static void initEmailProperties() throws IOException {
		FileReader reader = new FileReader("src/test/resources/testdata/emailsettings.properties");
		emailProperties = new Properties();
		emailProperties.load(reader);
	}

//    Testcase ID /Test ID/ - /Scenario Name/ has failed.
//    A new defect has been created in JIRA for the same.
//    Please click on the link below to view the bug in JIRA /link/
//
//    Thanks
//    Automated Test Execution team

	public static void sendEmail(String toAddress, String scenarioName, String testCaseId, String attachmentPath,
			String videoPath, String bugId, boolean previousBug, String JiraAssignee) throws IOException {
		initEmailProperties();
		boolean sendEmail = Boolean.parseBoolean(emailProperties.getProperty("email.update"));
		if (sendEmail) {
			final String username = emailProperties.getProperty("email.userName");
			final String password = emailProperties.getProperty("email.password");

			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true"); // TLS

			DateFormat dateFormat = new SimpleDateFormat(emailProperties.getProperty("email.dateFormat"));
			Date date = new Date();
			String dateStr = dateFormat.format(date);
			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(toAddress)); // toAddress
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress)// toAddress
				);
				message.setSubject("JPMC Demo | " + dateStr + " | " + testCaseId + " - Has been failed");
				Multipart multipart = new MimeMultipart();

				MimeBodyPart textBodyPart = new MimeBodyPart();
				String emailContentOnDefect = "";
				if (previousBug) {
					emailContentOnDefect = "Already a defect is available";
				} else {
					emailContentOnDefect = "A new defect has been created";
				}
				textBodyPart.setText("Dear " + JiraAssignee + "," + "\n\n" + testCaseId + " - \"" + scenarioName
						+ "\" has failed. \n" + emailContentOnDefect
						+ " in JIRA for the same.\nPlease click on the link below to view the bug in JIRA.\n\n");
				MimeBodyPart bugLink = new MimeBodyPart();
				bugLink.setContent("https://apifixzephyr.atlassian.net/browse/" + bugId, "text/html");

				MimeBodyPart finalBodyPart = new MimeBodyPart();
				finalBodyPart.setText("\n\nThanks\nAutomated Test Execution team\n");

				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachmentPath);
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName("Failed_Scenario_ScreenShot_" + dateStr + "_" + testCaseId);

				MimeBodyPart attachmentBodyPart1 = new MimeBodyPart();
				DataSource source1 = new FileDataSource(videoPath);
				attachmentBodyPart1.setDataHandler(new DataHandler(source1));
				attachmentBodyPart1.setFileName("Failed_Scenario_Video_" + dateStr + "_" + testCaseId+".avi");

				multipart.addBodyPart(textBodyPart);
				multipart.addBodyPart(attachmentBodyPart);
				multipart.addBodyPart(attachmentBodyPart1);
				multipart.addBodyPart(bugLink);
				multipart.addBodyPart(finalBodyPart);

				message.setContent(multipart);
				Transport.send(message);

				System.out.println("Email notification sent on Failure scenario.");

			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Not sending email");
		}
	}

	public static void sendEmail(String toAddress, String scenarioName, String testCaseId, String attachmentPath,
			String videoPath, String JiraAssignee) throws IOException {
		initEmailProperties();
		boolean sendEmail = Boolean.parseBoolean(emailProperties.getProperty("email.update"));
		if (sendEmail) {
			final String username = emailProperties.getProperty("email.userName");
			final String password = emailProperties.getProperty("email.password");

			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true"); // TLS

			DateFormat dateFormat = new SimpleDateFormat(emailProperties.getProperty("email.dateFormat"));
			Date date = new Date();
			String dateStr = dateFormat.format(date);
			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(toAddress)); // toAddress
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress)// toAddress
				);
				message.setSubject("JPMC Demo | " + dateStr + " | " + testCaseId + " - Has been failed");
				Multipart multipart = new MimeMultipart();

				MimeBodyPart textBodyPart = new MimeBodyPart();

				textBodyPart.setText("Dear " + JiraAssignee + "," + "\n\n" + testCaseId + " - \"" + scenarioName
						+ "\" has failed. Please find the attached failure screenshot");

				MimeBodyPart finalBodyPart = new MimeBodyPart();
				finalBodyPart.setText("\n\nThanks\nAutomated Test Execution team\n");

				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachmentPath);
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName("Failed_Scenario_ScreenShot_" + dateStr + "_" + testCaseId);

				MimeBodyPart attachmentBodyPart1 = new MimeBodyPart();
				DataSource source1 = new FileDataSource(videoPath);
				attachmentBodyPart1.setDataHandler(new DataHandler(source1));
				attachmentBodyPart1.setFileName("Failed_Scenario_Video_" + dateStr + "_" + testCaseId+".avi");

				multipart.addBodyPart(textBodyPart);
				multipart.addBodyPart(attachmentBodyPart);
				multipart.addBodyPart(attachmentBodyPart1);
				multipart.addBodyPart(finalBodyPart);

				message.setContent(multipart);
				Transport.send(message);

				System.out.println("Email notification sent on Failure scenario.");

			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Not sending email");
		}
	}

	public static String takeScreenShot(Scenario scenario) throws Exception {
		String screenShotPath = "";
		String screenshotName = scenario.getName().replaceAll(" ", "_");
		String featureName = scenario.getId().split(";")[0];
		try {
			if (ConfigurationHelper.getPlatform().equals("android")) {
				File scrFile = ((TakesScreenshot) AndroidPlatform.getDriver()).getScreenshotAs(OutputType.FILE);

				screenShotPath = "target/report/FailureScreenShots/" + screenshotName + "/" + featureName + ".png";
				FileUtils.copyFile(scrFile, new File(screenShotPath));
				final byte[] screenshot = ((TakesScreenshot) AndroidPlatform.getDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
				Reporter.addScreenCaptureFromPath("FailureScreenShots/" + screenshotName + "/" + featureName + ".png",
						"Screen shot");
			} else {
				File scrFile = ((TakesScreenshot) WebPortal.getDriver()).getScreenshotAs(OutputType.FILE);
				screenShotPath = "target/report/FailureScreenShots/" + screenshotName + "/" + featureName + ".png";
				FileUtils.copyFile(scrFile, new File(screenShotPath));
				final byte[] screenshot = ((TakesScreenshot) WebPortal.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
				Reporter.addScreenCaptureFromPath("FailureScreenShots/" + screenshotName + "/" + featureName + ".png",
						"Screen shot");
			}
		} catch (Exception e) {
			throw new Exception("Unable to Take Screen Shot");
		}
		return screenShotPath;
	}

	public static void closeApp(Scenario scenario) throws Exception {
		File videoPath = new File(VideoRecorder.getVideoPath());
		if (ConfigurationHelper.getPlatform().equals("android") && AndroidPlatform.getDriver() != null) {
			AndroidPlatform.getDriver().closeApp();
		} else if (WebPortal.getDriver() != null) {
			WebPortal.getDriver().quit();
			VideoRecorder.stopRecording();
		}
		if (!scenario.isFailed()) {
			if (videoPath.delete()) {
				System.out.println("File deleted successfully");
			} else {
				System.out.println("Unable to Delete the file");
			}
		}else {
			String videoHtml = "<video width=\"864\" height=\"576\" controls><source src=\""+VideoRecorder.getVideoPath()+"\"type=\"video/avi\">Your browser does not support the video tag.</video>";
	        scenario.embed(videoHtml.toString().getBytes(), "text/html");
	        Reporter.addScreenCaptureFromPath("Videos/" + VideoRecorder.getVideoPath(),
					"Screen shot");
		}
	}
}
