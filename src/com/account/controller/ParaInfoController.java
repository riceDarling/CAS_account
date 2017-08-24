package com.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.ParaInfo;
import com.account.entity.Warehouse;
import com.account.service.ParaInfoService;
import com.account.utils.PageBean;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;
import com.account.utils.pagebean.ParaInfoPage;

/**
 * 单位Controller
 */
@Controller
@RequestMapping("/paraInfo")
public class ParaInfoController  {
	
	@Autowired
	private ParaInfoService piService;
	
	/**
	 * 分页查询单位信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="findList")
	public ResponseModel<ParaInfoPage> findList(ParaInfoPage piPage, HttpServletRequest req) {
		ResponseModel<ParaInfoPage> rm = new ResponseModel<ParaInfoPage>();
		try {
			List<ParaInfo> list = piService.findList(piPage);
			piPage.setCurrentPage(PageBean.getCurrentPage(req));
			piPage.setUrl(PageBean.getUrl(req));
			piPage.setPageTotal(PageBean.getPageCount(req));
			piPage.setTotalCount(piService.findListCount(piPage));
			piPage.setList(list);
			rm.setSuccessMessage("操作成功", piPage);
		} catch (Exception e) {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 新增单位信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="insert")
	public ResponseModel<String> insert(ParaInfo ParaInfo) {
		ResponseModel<String> rm = new ResponseModel<String>();
		
		if (piService.insertSelective(ParaInfo) > 0) {
			rm.setSuccessMessage("操作成功", null);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 根据主键删除单位信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="delete")
	public ResponseModel<String> delete(String id) {
		ResponseModel<String> rm = new ResponseModel<String>();

		if (piService.deleteByPrimaryKey(Integer.parseInt(id)) > 0) {
			rm.setSuccessMessage("操作成功", null);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 根据主键查询单位信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="selectById")
	public ResponseModel<ParaInfo> selectById(String id) {
		ResponseModel<ParaInfo> rm = new ResponseModel<ParaInfo>();

		ParaInfo mt = piService.selectByPrimaryKey(Integer.parseInt(id));
		if (mt != null) {
			rm.setSuccessMessage("操作成功", mt);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value ="getParaInfoList")
	public ResponseModel<List<ParaInfo>> getParaInfoList(int pId) {
		ResponseModel<List<ParaInfo>> rm = new ResponseModel<List<ParaInfo>>();
		try {
			List<ParaInfo> li=piService.getParaInfoList(pId);
			rm.setSuccessMessage("操作成功", li);
		} catch (Exception e) {
			rm.setErrorMessage("获取参数项失败", null);
		}
		return rm;
	}
	
	/**
	 * accountPurchaseForm页面所需参数项
	 * @param pId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="getPurchaseParaInfoList")
	public ResponseModel<Map<String,List<ParaInfo>>> getPurchaseParaInfoList() {
		ResponseModel<Map<String,List<ParaInfo>>> rm = new ResponseModel<Map<String,List<ParaInfo>>>();
		try {
			Map<String,List<ParaInfo>> map=new HashMap<String,List<ParaInfo>>();
			//包装方式
			List<ParaInfo> packway=piService.getParaInfoList(4);
			//运输方式
			List<ParaInfo> transport=piService.getParaInfoList(6);
			//支付方式
			List<ParaInfo> payway=piService.getParaInfoList(5);
			map.put("packway", packway);
			map.put("transport", transport);
			map.put("payway", payway);
			rm.setSuccessMessage("操作成功", map);
		} catch (Exception e) {
			rm.setErrorMessage("获取参数项失败", null);
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String title, String checker,PageUtil paging , String startTime, String endTime) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<ParaInfo> list= piService.selectView( title, checker,  paging, startTime, endTime );
			int total = piService.selectViewCount( title, checker,  startTime, endTime );
			paging.setList( list );
			paging.setTotalCount(total);
			rm.setSuccessMessage("操作成功", paging);
		}catch( Exception e ){
			e.printStackTrace();
			rm.isErrorMsg("查询失败");
		}
		return rm;
	}
}