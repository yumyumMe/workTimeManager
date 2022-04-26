package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.WorkForm;
import com.example.demo.reposiory.WorkTimeDao;

@Service
public class WorkTimeService {

	@Autowired
	WorkTimeDao workTimeDao;

	// 勤務時間表示(指定日)
	public List<Map<String, Object>> showWorkTime(String day) {

		List<Map<String, Object>> list = workTimeDao.showWorkTime(day);

		return list;

	}

	// 勤務時間表示(指定ID)
	public Map<String, Object> showIdWork(String id) {

		Map<String, Object> list = workTimeDao.showIdWork(id);

		return list;

	}

	// 勤務時間登録
	public int registWorkTime(WorkForm workForm) {

		int result = workTimeDao.registWorkTime(workForm);

		return result;

	}

	// 勤務時間更新
	public int updateWorkData(WorkForm workForm) {

		int result = workTimeDao.updateWorkData(workForm);

		return result;

	}

	// 勤務時間削除
	public int deleteWorkTime(int workId) {

		int result = workTimeDao.deleteWorkTime(workId);

		return result;

	}

}
