package cn.edu.ynu.ordinarydraw.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;
import cn.edu.ynu.ordinarydraw.utils.ImageOperation;
import cn.edu.ynu.ordinarydraw.utils.MD5Encoder;
import cn.edu.ynu.ordinarydraw.utils.UUIDGenerator;

public class registerService {
	private static userDao userDao;
	static {
		userDao = new userDao();
	}

	/**
	 * 获取各个省份
	 * 
	 * @return
	 */
	public static List<String> getAddrs() {
		List<String> addrs = new ArrayList<String>();
		for (String str : CONSTANT.ARRAY_ADDRS) {
			addrs.add(str);
		}
		return addrs;
	}

	/*	*//**
	 * 上传头像处理
	 * 
	 * @param upImg
	 * @param upImgFileName
	 * @return
	 * @throws Exception
	 */
	/*
	 * public static String uploadImg(File upImg, String upImgFileName, String
	 * upImgContentType, Map<String, Object> session) throws Exception { String
	 * uuid = UUIDGenerator.getUUID(); String filename = uuid + "." +
	 * upImgContentType.split("/")[1]; String path = CONSTANT.IMG_PHYSICAL_PATH
	 * + CONSTANT.IMG_PATH_WORK_TEMP + filename; FileOutputStream fos = new
	 * FileOutputStream(path); FileInputStream fis = new FileInputStream(upImg);
	 * byte[] buffer = new byte[(int) upImg.length()]; int len = 0; while ((len
	 * = fis.read(buffer)) > 0) { fos.write(buffer, 0, len); } String decPath =
	 * path.replace(CONSTANT.IMG_PATH_WORK_TEMP,
	 * CONSTANT.IMG_PATH_FACE).replace("jpeg", "PNG");
	 * ImageOperation.square(path, decPath); return decPath; }
	 */

	/**
	 * 注册用户
	 * 
	 * @param email
	 * @param pwd
	 * @param uname
	 * @param addrindex
	 * @param udesc
	 * @return
	 * @throws Exception
	 */
	public static boolean registerUser(String email, String pwd, String uname,
			int addrindex, String udesc, String img) throws Exception {
		User user = new User();
		user.setEmail(email);
		user.setPwd(MD5Encoder.encode(pwd));
		user.setUname(uname);
		user.setAddr(addrindex == (CONSTANT.ARRAY_ADDRS.length - 1) ? CONSTANT.ARRAY_ADDRS[addrindex]
				: "中国，" + CONSTANT.ARRAY_ADDRS[addrindex]);
		// 进行图片的转储以及各种其他图片生成
		// 物理临时方形目录 到 物理头像目录
		ImageOperation.copy(img.replace(CONSTANT.IMG_LOGICAL_PATH,
				CONSTANT.IMG_PHYSICAL_PATH), img.replace(
				CONSTANT.IMG_LOGICAL_PATH + CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
				CONSTANT.IMG_PHYSICAL_PATH + CONSTANT.IMG_PATH_FACE));
		user.setImg(img.replace(CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
				CONSTANT.IMG_PATH_FACE));
		user.setUdesc(udesc);
		user.setStatu("0");
		System.out.println(user.getEmail()+","+user.getUdesc()+","+user.getUname());
		return userDao.saveUser(user);

	}
}
