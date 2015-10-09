package cn.edu.ynu.ordinarydraw.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import cn.edu.ynu.ordinarydraw.dao.tagDao;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;
import cn.edu.ynu.ordinarydraw.utils.ImageOperation;
import cn.edu.ynu.ordinarydraw.utils.UUIDGenerator;

public class commonService {
	private static tagDao tagDao;
	static {
		tagDao = new tagDao();
	}

	/**
	 * 接收用户上传的图片，存储并返回显示的逻辑路径
	 * 
	 * @param upImg
	 * @param upImgFileName
	 * @param upImgContentType
	 * @param session
	 * @return 图片显示的逻辑路径
	 * @throws Exception
	 */
	public static String showImg(File upImg, String upImgFileName,
			String upImgContentType) throws Exception {
		String uuid = UUIDGenerator.getUUID();
		String srcpath = CONSTANT.IMG_PHYSICAL_PATH
				+ CONSTANT.IMG_PATH_WORK_TEMP + uuid + "."
				+ upImgContentType.split("/")[1];
		String decpath = CONSTANT.IMG_PHYSICAL_PATH
				+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE + uuid + ".PNG";
		String decpath2 = CONSTANT.IMG_PHYSICAL_PATH
				+ CONSTANT.IMG_PATH_WORK_TEMP + uuid + ".PNG";
		FileOutputStream fos = new FileOutputStream(srcpath);
		FileInputStream fis = new FileInputStream(upImg);
		byte[] buffer = new byte[(int) upImg.length()];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		ImageOperation.square(srcpath, decpath);
		ImageOperation.copy(srcpath, decpath2);
		fos.close();
		fis.close();
		return decpath.replace(CONSTANT.IMG_PHYSICAL_PATH,
				CONSTANT.IMG_LOGICAL_PATH);
	}

	public static int getTagid(String tname) {
		Tag tag = tagDao.getTagByName(tname);
		return tag == null ? 0 : tag.getTagid();
	}

	/**
	 * 判断用户是否已经登录
	 * 
	 * @param session
	 * @return
	 */
	public static boolean islogined(Map<String, Object> session) {
		if (GLobalMethod.getNowUser(session) != null) {
			return true;
		} else {
			return false;
		}
	}
}
