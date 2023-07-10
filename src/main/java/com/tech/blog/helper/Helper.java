package com.tech.blog.helper;

//
import java.io.*;

//
public class Helper {

	public static boolean deleteFile(String path) {
		boolean f = false;

//		System.out.println("Path Value in delete = " + path);
		try {
			File file = new File(path);
//			System.out.println("File = " + file);
//			System.out.println("File Path value = " + file.getPath());
			file.delete();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public static boolean saveFile(InputStream is, String path) {
		boolean f = false;

		try {
			byte b[] = new byte[is.available()];
			System.out.println("Helper Input Stream = " + is);
			is.read(b);
			System.out.println("Helper Byte B = " + b);
			System.out.println("Helper Path = " + path);

			FileOutputStream fos = new FileOutputStream(path);
			System.out.println("Helper Path = " + path);
			System.out.println("Helper OutPutstream fos = " + fos);
			fos.write(b);
			fos.flush();
			fos.close();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
