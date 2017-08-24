package com.account.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.AccountInquiry;
import com.account.entity.AccountInquiryDetail;
import com.account.entity.AccountRequisition;
import com.account.entity.Admin;
import com.account.service.AccountInquiryDetailService;
import com.account.service.AccountInquiryService;
import com.account.service.AccountRequisitionActService;
import com.account.service.AccountRequisitionService;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;
import com.alibaba.fastjson.JSON;

/**
 * 询价单Controller
 * 
 */
@Controller
@RequestMapping("/accountInquiry")
public class AccountInquiryController {

	@Autowired
	private AccountInquiryService accountInquiryService;

	@Autowired
	private AccountRequisitionService accountRequisitionService;

	@Autowired
	private AccountInquiryDetailService accountInquiryDetailService;

	@Autowired
	private AccountRequisitionActService accountRequisitionActService;

	/**
	 * 询价单列表
	 * 
	 * @param requisitionid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public ResponseModel<PageUtil<AccountInquiry>> form(AccountInquiry entity) {
		ResponseModel<PageUtil<AccountInquiry>> rm = new ResponseModel<PageUtil<AccountInquiry>>();
		try {
			entity.setPage(new PageUtil<AccountInquiry>());
			accountInquiryService.findPage(entity);
			rm.isSuccessMsg(entity.getPage(), "获取询价信息成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取询价信息失败");
		}
		return rm;
	}

	/**
	 * 获取询价单
	 * 
	 * @param accountInquiryid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "get")
	public ResponseModel<AccountInquiry> get(String accountInquiryid) {
		ResponseModel<AccountInquiry> rm = new ResponseModel<AccountInquiry>();
		try {
			AccountInquiry entity = accountInquiryService.getById(accountInquiryid);
			rm.isSuccessMsg(entity, "获取询价单成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取询价单失败");
		}
		return rm;
	}

	/**
	 * 询价记录
	 * 
	 * @param requisitionid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "detail")
	public ResponseModel<List<AccountInquiryDetail>> detail(String accountInquiryOrdernum) {
		ResponseModel<List<AccountInquiryDetail>> rm = new ResponseModel<List<AccountInquiryDetail>>();
		try {
			// AccountInquiry entity=accountInquiryService.getById(accountInquiryid);
			List<AccountInquiryDetail> list = accountInquiryDetailService.selectInquiryDetailByOrdernum(accountInquiryOrdernum);
			rm.isSuccessMsg(list, "获取询价记录成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取询价记录失败");
		}
		return rm;
	}

	/**
	 * 保存询价记录
	 * 
	 * @param accountInquiryid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveDetail")
	public ResponseModel<String> saveDetail(@RequestBody String data) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			AccountInquiry accountInquiry = JSON.parseObject(data, AccountInquiry.class);
			accountInquiryDetailService.saveDetail(accountInquiry);
			rm.isSuccessMsg("", "保存询价记录成功");

		} catch (Exception e) {
			rm.isErrorMsg("保存询价记录失败");
		}
		return rm;
	}

	/**
	 * 提交询价记录进行审核
	 * 
	 * @param inquirydetailId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "detailupdata")
	public ResponseModel<String> detailupdata(@RequestParam(required = false, value = "inquirydetailId[]") String[] inquirydetailId, String checker) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			if (inquirydetailId.length < 0) {
				rm.isErrorMsg("没有选择询价记录,提交失败");
			} else {
				accountInquiryService.detailupdata(inquirydetailId, checker);
				rm.isSuccessMsg(null, "提交询价单成功");
			}
		} catch (Exception e) {
			rm.isErrorMsg("提交询价单失败");
		}
		return rm;
	}

	/**
	 * 获取所有的询价标题
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectAllTitle")
	public ResponseModel<List<Map<String, Object>>> selectAllTitle() {
		ResponseModel<List<Map<String, Object>>> rm = new ResponseModel<List<Map<String, Object>>>();
		try {
			List<Map<String, Object>> list = accountInquiryService.selectAllTitle();
			rm.isSuccessMsg(list, "获取标题成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取标题失败");
		}
		return rm;
	}

	/**
	 * 获取所有的物资
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectAllmaterial")
	public ResponseModel<List<Map<String, Object>>> selectAllmaterial(String accountInquiryOrdernum) {
		ResponseModel<List<Map<String, Object>>> rm = new ResponseModel<List<Map<String, Object>>>();
		try {
			List<Map<String, Object>> list = accountInquiryDetailService.selectAllmaterial(accountInquiryOrdernum);
			rm.isSuccessMsg(list, "获取物资成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取物资失败");
		}
		return rm;
	}

	/**
	 * 获取询价子表
	 * 
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectDetail")
	public ResponseModel<List<AccountInquiryDetail>> selectDetail(AccountInquiryDetail entity) {
		ResponseModel<List<AccountInquiryDetail>> rm = new ResponseModel<List<AccountInquiryDetail>>();
		try {
			List<AccountInquiryDetail> list = accountInquiryDetailService.selectDetail(entity);
			rm.isSuccessMsg(list, "获取询价记录成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取询价记录失败");
		}
		return rm;
	}
	/**
	 * 根据物资编码获取详细信息
	 */
	@ResponseBody
	@RequestMapping(value = "getAccountSupplierByPurchasenum")
	public ResponseModel<List<AccountInquiryDetail>> getAccountSupplierByPurchasenum(AccountInquiryDetail accountInquiryDetail) {
		ResponseModel<List<AccountInquiryDetail>> rm = new ResponseModel<List<AccountInquiryDetail>>();
		try {
			List<AccountInquiryDetail> adDetails = accountInquiryDetailService.getAccountSupplierByPurchasenum(accountInquiryDetail);
			rm.isSuccessMsg(adDetails, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	/**
	 * 根据采购标题获取供应商列表信息
	 */
	@ResponseBody
	@RequestMapping(value = "getAccountSupplierByPurchasenumtitle")
	public ResponseModel<List<AccountInquiryDetail>> getAccountSupplierByPurchasenumtitle(HttpServletRequest req) {
		ResponseModel<List<AccountInquiryDetail>> rm = new ResponseModel<List<AccountInquiryDetail>>();
		try {
			String purchasenumtitle = req.getParameter("purchasenumtitle");
			List<AccountInquiryDetail> accountInquiryDetail = accountInquiryDetailService.getAccountSupplierByPurchasenumtitle(purchasenumtitle);
			rm.isSuccessMsg(accountInquiryDetail, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String title, String checker, String procInsId, PageUtil paging , String startTime, String endTime) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<AccountInquiry> list= accountInquiryService.selectView( title, checker, procInsId, paging, startTime, endTime );
			int total = accountInquiryService.selectViewCount( title, checker, procInsId, startTime, endTime );
			paging.setList( list );
			paging.setTotalCount(total);
			rm.setSuccessMessage("操作成功", paging);
		}catch( Exception e ){
			e.printStackTrace();
			rm.isErrorMsg("查询失败");
		}
		return rm;
	}
	
	/**
	 * 报价，结束
	 * 
	 * @param accountInquiryid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveEnd")
	public ResponseModel<String> saveEnd(String accountInquiryid) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			accountInquiryService.saveEnd(accountInquiryid);
			rm.isSuccessMsg("", "报价成功");
		} catch (Exception e) {
			rm.isErrorMsg("报价失败");
		}
		return rm;
	}
}
