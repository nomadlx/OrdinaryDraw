package cn.edu.ynu.ordinarydraw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;
import cn.edu.ynu.ordinarydraw.utils.ImageOperation;
import cn.edu.ynu.ordinarydraw.utils.MD5Encoder;

public class profileService {
	private static userDao userDao;
	static {
		userDao = new userDao();
	}

	/**
	 * 获取我的基本信息
	 * 
	 * @param session
	 */
	public static void getProfile(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		session.put("myProfile", user);
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

	/**
	 * 根据地址名字获取index
	 * 
	 * @param addr
	 * @return
	 */
	public static int getAddrIndex(String addr) {
		int index = 0;
		for (String str : CONSTANT.ARRAY_ADDRS) {
			if (addr.contains(str)) {
				break;
			}
			index++;
		}
		return index;
	}

	/**
	 * 更新个人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean updateProfile(String img, String uname, String email,
			int addrindex, String udesc, Map<String, Object> session)
			throws Exception {
		User user = GLobalMethod.getNowUser(session);
		if (!img.contains(CONSTANT.IMG_PATH_FACE)) {
			// 进行图片的转储以及各种其他图片生成
			// 物理临时方形目录 到 物理头像目录

			ImageOperation.copy(img.replace(CONSTANT.IMG_LOGICAL_PATH,
					CONSTANT.IMG_PHYSICAL_PATH), img.replace(
					CONSTANT.IMG_LOGICAL_PATH
							+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
					CONSTANT.IMG_PHYSICAL_PATH + CONSTANT.IMG_PATH_FACE));
			user.setImg(img.replace(CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
					CONSTANT.IMG_PATH_FACE));
		}

		user.setAddr(CONSTANT.ARRAY_ADDRS[addrindex]);
		user.setUdesc(udesc);
		user.setUname(uname);
		return userDao.saveUser(user);
	}

	/**
	 * 更新密码
	 * 
	 * @param oldPwd
	 *            旧密码
	 * @param newPwd
	 *            新密码
	 * @return true：更新成功 false：更新失败
	 */
	public static boolean updatePwd(String oldPwd, String newPwd,
			Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		if (!MD5Encoder.encode(oldPwd).equals(user.getPwd())) {
			return false;
		}
		user.setPwd(MD5Encoder.encode(newPwd));
		userDao.saveUser(user);
		return true;
	}
}
