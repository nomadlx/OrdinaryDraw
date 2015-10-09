package cn.edu.ynu.ordinarydraw.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIPpack {
	public static boolean packfiles(String[] srcfiles, String decfile)
			throws IOException {
		byte[] buffer = new byte[1024];
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(decfile));
		for (int i = 0; i < srcfiles.length; i++) {
			File file = new File(srcfiles[i]);
			FileInputStream fis = new FileInputStream(file);
			out.putNextEntry(new ZipEntry(file.getName()));
			int len;
			// 读入需要下载的文件的内容，打包到zip文件
			while ((len = fis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.closeEntry();
			fis.close();
		}
		out.close();
		return true;
	}

	public static void main(String[] args) throws IOException {
		String[] srcfiles = new String[2];
		srcfiles[0]="/home/nomadlx/Desktop/6597327052378831647.jpg";
		srcfiles[1]="/home/nomadlx/Desktop/6630537801095823149.jpg";
		String decfile = "/home/nomadlx/Desktop/zipdemo.zip";
		ZIPpack.packfiles(srcfiles, decfile);
	}
}
