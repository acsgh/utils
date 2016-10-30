package com.albertoteloko.utils.io;

import com.albertoteloko.utils.ExceptionUtils;

import java.io.*;

public final class FileUtils {

    private FileUtils() {
    }

    public static void copyFile(File src, File dst) throws IOException {
        File parent = dst.getParentFile();

        if (!parent.exists()) {
            parent.mkdirs();
        }

        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dst);

        StreamUtils.copyInputStream(in, out);
        in.close();
        out.close();
    }

    public static byte[] readResourceFile(String url) {
        byte[] result = null;
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(url)) {
            if (in == null) {
                throw new FileNotFoundException("File '" + url + "' not found in classpath");
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            StreamUtils.copyInputStream(in, out);
            result = out.toByteArray();
        } catch (Exception e) {
            ExceptionUtils.throwRuntimeException(e);
        }
        return result;
    }

    public static byte[] readFile(String url) {
        byte[] result = null;
        try (InputStream in = new FileInputStream(url)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            StreamUtils.copyInputStream(in, out);
            result = out.toByteArray();
        } catch (Exception e) {
            ExceptionUtils.throwRuntimeException(e);
        }
        return result;
    }
}
