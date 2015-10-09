package cn.edu.ynu.ordinarydraw.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.tagDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.GoodsTag;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;
import cn.edu.ynu.ordinarydraw.utils.ImageOperation;
import cn.edu.ynu.ordinarydraw.utils.UUIDGenerator;

public class workeditService {
	private static tagDao tagDao;
	private static goodsDao goodsDao;
	static {
		tagDao = new tagDao();
		goodsDao = new goodsDao();
	}

	/**
	 * 获取作品信息
	 * 
	 * @param goodsid
	 * @param session
	 * @throws Exception
	 */
	public static void getGoodsInfo(int goodsid, Map<String, Object> session)
			throws Exception {
		Goods goods = goodsDao.getGoodsById(goodsid);
		List<String> imglist = new ArrayList<String>();
		for (int i = 0; i < goods.getGoodsimgs().size(); i++) {
			imglist.add("");
		}
		for (GoodsImg goodsImg : goods.getGoodsimgs()) {

			String path = goodsImg.getPath();/*
											 * // 进行图片的转储以及各种其他图片生成
											 * ImageOperation
											 * .copy(path.replace(CONSTANT
											 * .IMG_LOGICAL_PATH,
											 * CONSTANT.IMG_PHYSICAL_PATH),
											 * path.replace(
											 * CONSTANT.IMG_LOGICAL_PATH +
											 * CONSTANT.IMG_PATH_WORK_SQUARE,
											 * CONSTANT.IMG_PHYSICAL_PATH +
											 * CONSTANT
											 * .IMG_PATH_WORK_TEMP_SQUARE));
											 * 
											 * ImageOperation.copy(path.replace(
											 * CONSTANT.IMG_LOGICAL_PATH +
											 * CONSTANT.IMG_PATH_WORK_SQUARE,
											 * CONSTANT.IMG_PHYSICAL_PATH +
											 * CONSTANT.IMG_PATH_WORK_ORDINARY),
											 * path.replace(
											 * CONSTANT.IMG_LOGICAL_PATH +
											 * CONSTANT.IMG_PATH_WORK_SQUARE,
											 * CONSTANT.IMG_PHYSICAL_PATH +
											 * CONSTANT.IMG_PATH_WORK_TEMP));
											 * goodsImg
											 * .setPath(path.replace(CONSTANT
											 * .IMG_PATH_WORK_SQUARE,
											 * CONSTANT.IMG_PATH_WORK_TEMP_SQUARE
											 * ));
											 */
			imglist.set(goodsImg.getSeq(), path);
		}
		session.put("imgList", imglist);
		session.put("goodsInfo", goods);
	}

	public static boolean updateGoods(int goodsid, String gname, String imgs,
			float price, String tags, String gdesc, boolean isnew,
			Map<String, Object> session) throws Exception {
		User user = GLobalMethod.getNowUser(session);
		Goods goods = goodsDao.getGoodsById(goodsid);
		goods.setGname(gname);
		goods.setPrice(price);
		goods.setGdesc(gdesc);
		goods.setUpdatetime(GLobalMethod.getNowTime());
		Set<GoodsImg> goodsimgs = new HashSet<GoodsImg>();
		String[] imgStrings = imgs.trim().split(" ");
		goods.setImgcount((short) imgStrings.length);
		for (int i = 0; i < imgStrings.length; i++) {
			if (!imgStrings[i].contains(CONSTANT.IMG_PATH_WORK_SQUARE)) {
				// 进行图片的转储以及各种其他图片生成
				ImageOperation.copy(imgStrings[i].replace(
						CONSTANT.IMG_LOGICAL_PATH
								+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
						CONSTANT.IMG_PHYSICAL_PATH
								+ CONSTANT.IMG_PATH_WORK_TEMP), imgStrings[i]
						.replace(CONSTANT.IMG_LOGICAL_PATH
								+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
								CONSTANT.IMG_PHYSICAL_PATH
										+ CONSTANT.IMG_PATH_WORK_ORDINARY));
				ImageOperation.copy(imgStrings[i].replace(
						CONSTANT.IMG_LOGICAL_PATH, CONSTANT.IMG_PHYSICAL_PATH),
						imgStrings[i].replace(CONSTANT.IMG_LOGICAL_PATH
								+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
								CONSTANT.IMG_PHYSICAL_PATH
										+ CONSTANT.IMG_PATH_WORK_SQUARE));
			}

			GoodsImg goodsimg = new GoodsImg();
			goodsimg.setPath(imgStrings[i].replace(
					CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
					CONSTANT.IMG_PATH_WORK_SQUARE));
			goodsimg.setSeq((short) i);
			goodsimgs.add(goodsimg);
		}
		goods.setGoodsimgs(goodsimgs);
		return goodsDao.updateGoods(goods, tags);
	}

	/**
	 * 发布作品
	 * 
	 * @param gname
	 * @param price
	 * @param tags
	 * @param gdesc
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static boolean publishGoods(int goodsid, String gname, String imgs,
			float price, String tags, String gdesc, Map<String, Object> session)
			throws Exception {
		User user = GLobalMethod.getNowUser(session);
		Goods goods = new Goods();
		goods.setGname(gname);
		goods.setPrice(price);
		goods.setGdesc(gdesc);
		goods.setPublishtime(GLobalMethod.getNowTime());
		goods.setUpdatetime(GLobalMethod.getNowTime());
		goods.setStatu("0");
		goods.setUser(user);
		Set<GoodsTag> goodstags = new HashSet<GoodsTag>();
		String[] tagStrings = tags.trim().split(" ");
		for (int i = 0; i < tagStrings.length; i++) {
			System.out.println(tagStrings[i]);
			Tag tag = tagDao.getTagByName(tagStrings[i]);
			tag = tag == null ? new Tag(null, tagStrings[i],
					GLobalMethod.getNowTime(), 0) : tag;
			GoodsTag goodstag = new GoodsTag();
			goodstag.setTag(tag);
			goodstag.setSeq((short) i);
			goodstags.add(goodstag);
		}
		Set<GoodsImg> goodsimgs = new HashSet<GoodsImg>();
		String[] imgStrings = imgs.trim().split(" ");
		
		goods.setImgcount((short) imgStrings.length);
		for (int i = 0; i < imgStrings.length; i++) {
			// 进行图片的转储以及各种其他图片生成
			ImageOperation.copy(imgStrings[i].replace(CONSTANT.IMG_LOGICAL_PATH
					+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
					CONSTANT.IMG_PHYSICAL_PATH + CONSTANT.IMG_PATH_WORK_TEMP),
					imgStrings[i].replace(CONSTANT.IMG_LOGICAL_PATH
							+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
							CONSTANT.IMG_PHYSICAL_PATH
									+ CONSTANT.IMG_PATH_WORK_ORDINARY));
			ImageOperation.copy(imgStrings[i].replace(
					CONSTANT.IMG_LOGICAL_PATH, CONSTANT.IMG_PHYSICAL_PATH),
					imgStrings[i].replace(CONSTANT.IMG_LOGICAL_PATH
							+ CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
							CONSTANT.IMG_PHYSICAL_PATH
									+ CONSTANT.IMG_PATH_WORK_SQUARE));

			GoodsImg goodsimg = new GoodsImg();
			goodsimg.setPath(imgStrings[i].replace(
					CONSTANT.IMG_PATH_WORK_TEMP_SQUARE,
					CONSTANT.IMG_PATH_WORK_SQUARE));
			goodsimg.setSeq((short) i);
			goodsimgs.add(goodsimg);
		}
		goods.setGoodsimgs(goodsimgs);
		goods.setGoodstags(goodstags);
		return goodsDao.saveGoods(goods);
	}
}
