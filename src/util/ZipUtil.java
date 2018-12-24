package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sinan
 *
 */
public class ZipUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtil.class);

	private static final String ZIP_FILES_PATH = "ext" + File.separator + "zipfiles" + File.separator;

	/**
	 * Constructor
	 */
	private ZipUtil() {
	}

	/**
	 * archive
	 * 
	 * @param archivePath
	 */
	public static void archive(String archivePath) {

		LOGGER.info("Archive is starting...");

		try {

			File files = new File(archivePath);

			if (files.listFiles().length == 0) {
				LOGGER.info("Archive is not starting...");
				return;
			}

			String zipName = ZIP_FILES_PATH + Thread.currentThread().getName() + "_"
					+ new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + ".zip";

			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));

			for (File file : files.listFiles()) {
				addToZipFile(file, zos);
			}

			zos.close();

			LOGGER.info("Archive files are deleting...");

			for (File file : files.listFiles()) {
				file.delete();
			}

			LOGGER.info("Archive completed.");

		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 
	 * @param fileName
	 * @param zos
	 * @throws IOException
	 */
	private static void addToZipFile(File file, ZipOutputStream zos) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		zos.putNextEntry(new ZipEntry(file.getName()));

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();

	}

}