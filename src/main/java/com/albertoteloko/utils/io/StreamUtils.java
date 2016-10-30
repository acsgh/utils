package com.albertoteloko.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class StreamUtils {

	private StreamUtils() {
	}

	public static final void copyInputStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);
	}

}
