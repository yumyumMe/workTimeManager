package com.example.demo.reposiory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WorkForm;

@Repository
public class WorkTimeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// 勤務時間表示(引数： 日付)
	public List<Map<String, Object>> showWorkTime(String day) {

		String sql = "SELECT workId, startTime, endTime, workKbn, workDetail, other "
					 + "FROM workTime WHERE registDate = ? "
					 + "ORDER BY startTime";
		Object[] param = {day};
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);

		return list;

	}

	// 勤務時間表示(引数： 作業ID)
	public Map<String, Object> showIdWork(String id) {

		String sql = "SELECT startTime, endTime, workKbn, workDetail, other, registDate "
					 + "FROM workTime WHERE workId = ?";
		Object[] param = {id};
		Map<String, Object> list = jdbcTemplate.queryForMap(sql, param);

		return list;

	}

	// 勤務時間登録
	public int registWorkTime(WorkForm workForm) {

		String sql = "INSERT INTO workTime (workId, startTime, endTime, workKbn, workDetail, other, registDate) VALUES(?, ?, ?, ?, ?, ?, ?)";
		Object[] param = {workForm.getWorkId(), workForm.getStartTime(), workForm.getEndTime(), workForm.getWorkKbn(), workForm.getWorkDetail(), workForm.getOther(), workForm.getRegistDate()};
		int result = jdbcTemplate.update(sql, param);

		return result;

	}

	// 勤務時間更新
	public int updateWorkData(WorkForm workForm) {

		String sql = "UPDATE workTime SET startTime = ?, endTime = ?, workKbn = ?, workDetail = ?, other = ?, registDate = ? WHERE workId = ?";
		Object[] param = {workForm.getStartTime(), workForm.getEndTime(), workForm.getWorkKbn(), workForm.getWorkDetail(), workForm.getOther(), workForm.getRegistDate(), workForm.getWorkId()};
		int result = jdbcTemplate.update(sql, param);

		return result;

	}

	// 勤務時間削除
	public int deleteWorkTime(int workId) {

		String sql = "DELETE FROM workTime WHERE workId = ?";
		Object[] param = {workId};

		int result = jdbcTemplate.update(sql, param);

		return result;

	}

}
