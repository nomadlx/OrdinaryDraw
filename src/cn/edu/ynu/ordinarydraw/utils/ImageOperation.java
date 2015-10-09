package cn.edu.ynu.ordinarydraw.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageOperation {
	/**
	 * 把图片剪切成正方形
	 * 
	 * @param srcImg
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean square(String srcPath, String decPath)
			throws IOException {
		File srcFile = new File(srcPath);
		BufferedImage srcBi = ImageIO.read(srcFile);
		int wh = Integer.min(srcBi.getWidth(), srcBi.getHeight());
		System.out.println(srcBi.getWidth()+","+srcBi.getHeight());
		BufferedImage decBi = null;
		if (wh <= 300) {
			decBi = new BufferedImage(wh, wh, BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < wh; i++) {
				for (int j = 0; j < wh; j++) {
					decBi.setRGB(i, j, srcBi.getRGB(i, j));
				}
			}
		} else {
			int mid=wh/300;
			System.out.println(mid);
			decBi = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < 300; i++) {
				for (int j = 0; j < 300; j++) {
					decBi.setRGB(i, j, srcBi.getRGB(i*mid, j*mid));
				}
			}

		}
		File file = new File(decPath);
		ImageIO.write(decBi, "PNG", file);
		return true;
	}

	/**
	 * 图片拷贝
	 * 
	 * @param srcPath
	 * @param decPath
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static boolean copy(String srcPath, String decPath) throws Exception {
		File srcFile = new File(srcPath);
		BufferedImage srcBi = ImageIO.read(srcFile);
		int w = srcBi.getWidth();
		int h = srcBi.getHeight();
		BufferedImage decBi = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				decBi.setRGB(i, j, srcBi.getRGB(i, j));
			}
		}
		File file = new File(decPath);
		ImageIO.write(decBi, "PNG", file);
		return true;
	}

}
