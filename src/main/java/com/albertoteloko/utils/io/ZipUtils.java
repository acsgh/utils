package com.albertoteloko.utils.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ZipUtils {
	private static final Logger log = LoggerFactory.getLogger(ZipUtils.class);

	private ZipUtils() {
	}

	public static void unzipFolder(File zipFile, File destinationFolder) {
		log.debug("Unzipping file: '{}' into folder: '{}'", zipFile, destinationFolder);

		ZipInputStream input = null;
		try {
			if (!destinationFolder.exists()) {
				destinationFolder.mkdirs();
			}
			input = new ZipInputStream(new FileInputStream(zipFile));

			log.debug("\tExtracting directory: {}", destinationFolder);
			unzipFolder(input, destinationFolder);
		} catch (IOException ioe) {
			log.error("Unable to unzip", ioe);
			throw new RuntimeException(ioe);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void unzipFolder(ZipInputStream input, File destinationFolder) {
		try {
			if (!destinationFolder.exists()) {
				destinationFolder.mkdirs();
			}

			ZipEntry entry = null;

			while ((entry = input.getNextEntry()) != null) {
				File destinationFile = new File(destinationFolder, entry.getName());
				if (entry.isDirectory()) {
					log.debug("\tExtracting directory: {}", destinationFile);

					destinationFile.mkdirs();
				} else {
					log.debug("\tExtracting file: {}", destinationFile);

					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(destinationFile));
					StreamUtils.copyInputStream(input, output);
					output.close();
				}
			}

		} catch (IOException ioe) {
			log.error("Unable to unzip", ioe);
			throw new RuntimeException(ioe);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void zipFolder(File srcFolder, File zipFile) {
		if (!srcFolder.isDirectory()) {
			throw new IllegalArgumentException("The source location " + srcFolder + " is not a folder");
		}
		if (!srcFolder.exists()) {
			throw new IllegalArgumentException("The source folder " + srcFolder + " doesn't exists");
		}

		log.debug("Zipping folder: '{}' into file: '{}'", srcFolder, zipFile);

		ZipOutputStream output = null;
		try {
			output = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
			log.debug("\tZipping directory: {}", srcFolder);
			zipFolder(srcFolder, srcFolder, output);

			output.flush();
			output.close();
		} catch (IOException ioe) {
			log.error("Unable to zip", ioe);
			throw new RuntimeException(ioe);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static void zipFolder(File baseFolder, File srcFolder, ZipOutputStream output) throws IOException {
		File[] files = srcFolder.listFiles();

		for (File file : files) {
			String name = file.getAbsolutePath().replace(baseFolder.getAbsolutePath(), "") + (file.isDirectory() ? "/" : "");

			if (name.startsWith("/")) {
				name = name.substring(1);
			}
			
			ZipEntry entry = new ZipEntry(name);
			output.putNextEntry(entry);

			if (file.isDirectory()) {
				log.debug("\tZipping directory: {}", file);

				zipFolder(baseFolder, file, output);
			} else {
				log.debug("\tZipping file: {}", file);
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				StreamUtils.copyInputStream(in, output);
				in.close();
			}

			output.closeEntry();
		}
	}
}
