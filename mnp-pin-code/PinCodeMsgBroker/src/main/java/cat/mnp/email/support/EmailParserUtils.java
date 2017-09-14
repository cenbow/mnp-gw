/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.email.support;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 *
 * @author anuchitr
 */
public final class EmailParserUtils {
    
	private static final Logger logger = LoggerFactory.getLogger(EmailParserUtils.class);

	/**
	 * Parses a mail message. The respective message can either be the root message
	 * or another message that is attached to another message.
	 *
	 * If the mail message is an instance of {@link String}, then a {@link EmailFragment}
	 * is being created using the email message's subject line as the file name,
	 * which will contain the mail message's content.
	 *
	 * If the mail message is an instance of {@link Multipart} then we delegate
	 * to {@link #handleMultipart(File, Multipart, javax.mail.Message, List)}.
	 *
	 * @param directory The directory for storing the message. If null this is the root message.
	 * @param mailMessage The mail message to be parsed. Must not be null.
	 * @param emailFragments Must not be null.
	 */
	public static void handleMessage(final File directory,
										final javax.mail.Message mailMessage,
										final List<EmailFragment> emailFragments) {

		Assert.notNull(mailMessage, "The mail message to be parsed must not be null.");
		Assert.notNull(emailFragments, "The collection of emailfragments must not be null.");

		final Object content;
		final String subject;

		try {
			content = mailMessage.getContent();
			subject = mailMessage.getSubject();
		} catch (IOException e) {
			throw new IllegalStateException("Error while retrieving the email contents.", e);
		} catch (MessagingException e) {
			throw new IllegalStateException("Error while retrieving the email contents.", e);
		}

		final File directoryToUse;

		if (directory == null) {
			directoryToUse = new File(subject);
		} else {
			directoryToUse = new File(directory, subject);
		}

		if (content instanceof String) {
			emailFragments.add(new EmailFragment(new File(subject), "message.txt", content));
		}
		else if (content instanceof Multipart) {
			Multipart multipart = (Multipart) content;
			handleMultipart(directoryToUse, multipart, mailMessage, emailFragments);
		}
		else {
			throw new IllegalStateException("This content type is not handled - " + content.getClass().getSimpleName());
		}

	}

	/**
	 * Parses any {@link Multipart} instances that contain text or Html attachments,
	 * {@link InputStream} instances, additional instances of {@link Multipart}
	 * or other attached instances of {@link javax.mail.Message}.
	 *
	 * Will create the respective {@link EmailFragment}s representing those attachments.
	 *
	 * Instances of {@link javax.mail.Message} are delegated to
	 * {@link #handleMessage(File, javax.mail.Message, List)}. Further instances
	 * of {@link Multipart} are delegated to
	 * {@link #handleMultipart(File, Multipart, javax.mail.Message, List)}.
	 *
	 * @param directory Must not be null
	 * @param multipart Must not be null
	 * @param mailMessage Must not be null
	 * @param emailFragments Must not be null
	 */
	public static void handleMultipart(File directory, Multipart multipart, javax.mail.Message mailMessage, List<EmailFragment> emailFragments) {

		Assert.notNull(directory, "The directory must not be null.");
		Assert.notNull(multipart, "The multipart object to be parsed must not be null.");
		Assert.notNull(mailMessage, "The mail message to be parsed must not be null.");
		Assert.notNull(emailFragments, "The collection of emailfragments must not be null.");

		final int count;

		try {
			count = multipart.getCount();
			logger.info("Number of enclosed BodyPart objects: {}.", count);
		}
		catch (MessagingException e) {
			throw new IllegalStateException("Error while retrieving the number of enclosed BodyPart objects.", e);
		}

		for (int i = 0; i < count; i++) {

			final BodyPart bp;

			try {
				bp = multipart.getBodyPart(i);
			}
			catch (MessagingException e) {
				throw new IllegalStateException("Error while retrieving body part.", e);
			}

			final String contentType;
			String filename;
			final String disposition;
			final String subject;

			try {

				contentType = MimeUtility.decodeText(bp.getContentType());
				filename    = bp.getFileName();
				disposition = bp.getDisposition();
				subject     = mailMessage.getSubject();

				if (filename == null && bp instanceof MimeBodyPart) {
					filename = ((MimeBodyPart) bp).getContentID();
				}
                if (filename != null) {
                    filename = MimeUtility.decodeText(bp.getFileName());
                }

			}
			catch (MessagingException | UnsupportedEncodingException e) {
				throw new IllegalStateException("Unable to retrieve body part meta data.", e);
			}

			if (Part.ATTACHMENT.equalsIgnoreCase(disposition)) {
				logger.info("Handdling attachment '{}', type: '{}'", filename, contentType);
			} else {
                logger.debug("BodyPart - Content Type: '{}', filename: '{}', disposition: '{}', subject: '{}'", contentType, filename, disposition, subject);
            }

			final Object content;

			try {
				content = bp.getContent();
			}
			catch (IOException | MessagingException e) {
				throw new IllegalStateException("Error while retrieving the email contents.", e);
			}

			if (content instanceof String) {

				if (Part.ATTACHMENT.equalsIgnoreCase(disposition)) {
					emailFragments.add(new EmailFragment(directory, i + "-" + filename, content));
					logger.info("Handdling attachment '{}', type: '{}'", filename, contentType);
				}
				else {

					final String textFilename;
					final ContentType ct;

					try {
						ct = new ContentType(contentType);
					}
					catch (ParseException e) {
						throw new IllegalStateException("Error while parsing content type '" + contentType + "'.", e);
					}

					if ("text/plain".equalsIgnoreCase(ct.getBaseType())) {
						textFilename = "message.txt";
					}
					else if ("text/html".equalsIgnoreCase(ct.getBaseType())) {
						textFilename = "message.html";
					}
					else {
						textFilename = "message.other";
					}

					emailFragments.add(new EmailFragment(directory, textFilename, content));
				}


			}
			else if (content instanceof InputStream) {

				final InputStream inputStream = (InputStream) content;
				final ByteArrayOutputStream bis = new ByteArrayOutputStream();

				try {
					IOUtils.copy(inputStream, bis);
				}
				catch (IOException e) {
					throw new IllegalStateException("Error while copying input stream to the ByteArrayOutputStream.", e);
				}

				emailFragments.add(new EmailFragment(directory, filename, bis.toByteArray()));

			}
			else if (content instanceof javax.mail.Message)  {
				handleMessage(directory, (javax.mail.Message) content, emailFragments);
			}
			else if (content instanceof Multipart) {
				final Multipart mp2 = (Multipart) content;
				handleMultipart(directory, mp2, mailMessage, emailFragments);
			}
			else {
				throw new IllegalStateException("Content type not handled: " + content.getClass().getSimpleName());
			}
		}
	}
}
