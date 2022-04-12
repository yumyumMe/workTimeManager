package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.workForm;
import com.example.demo.model.workModel;
import com.example.demo.service.workTimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/work")
public class workTimeManagerController {

	@Autowired
	workTimeService workTimeService;

	workModel workModel = new workModel();

	// 勤務時間初期表示
	@GetMapping("/index")
	public String showTodayWork(Model model, @ModelAttribute workForm workForm) {

		// 当日日付のレコードのみ取得
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(cl.getTime());

		List<Map<String, Object>> list = workTimeService.showWorkTime(today);

		// 作業区分変換
		for(Map<String, Object> val : list) {
			int workKbn = (Integer)val.get("workKbn");
			val.replace("workKbn", workModel.translateWorkKbn(workKbn));
		}

		model.addAttribute("list", list);

		return "/work/index";

	}

	// 勤務時間(指定日)表示
	@GetMapping("/date")
	@ResponseBody
	public String showDateWork(HttpServletRequest request) {

		String queryParam = request.getQueryString().substring(5);

		List<Map<String, Object>> list = workTimeService.showWorkTime(queryParam);

		return getJson(list);

	}

	// 勤務時間(指定ID)表示
	@GetMapping("/id")
	@ResponseBody
	public String showIdWork(HttpServletRequest request) {

		String queryParam = request.getQueryString().substring(3);

		Map<String, Object> list = workTimeService.showIdWork(queryParam);

		return getJson(list);

	}

	// 指定日の勤務時間一覧をJSON形式で返却
	private String getJson(List<Map<String, Object>> list){

        String jsonList = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try{

        	jsonList = objectMapper.writeValueAsString(list);

        } catch (JsonProcessingException e) {

            System.err.println(e);

        }

        return jsonList;

    }

	// 指定IDの勤務時間をJSON形式で返却
	private String getJson(Map<String, Object> list){

	    String jsonList = null;
	    ObjectMapper objectMapper = new ObjectMapper();

	    try{

	        jsonList = objectMapper.writeValueAsString(list);

	    } catch (JsonProcessingException e) {

	        System.err.println(e);

	    }

	    return jsonList;

	}

	// 勤務時間登録
	@PostMapping("/register")
	public String registWorkTime(@Valid @ModelAttribute workForm workForm,
			BindingResult bindingResult, RedirectAttributes attr) {

		if(bindingResult.hasErrors()) {

			return "/work/index";

		}else {

			workTimeService.registWorkTime(workForm);

			return "redirect:/work/index";

		}

	}

	// 労働内容更新
	@PostMapping("/update")
	@ResponseBody
	public String updateWorkData(@RequestBody workForm workForm) {

		workTimeService.updateWorkData(workForm);

		return "redirect:/work/index";

	}

	// 勤務時間削除
	@PostMapping("/delete")
	@ResponseBody
	public String deleteWorkTime(@RequestBody int workId, RedirectAttributes attr) {

		workTimeService.deleteWorkTime(workId);

		return "redirect:/work/index";

	}

}
