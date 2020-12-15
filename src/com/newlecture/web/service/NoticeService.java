package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.jdbc.JdbcNoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public class NoticeService {
	
	private NoticeDao noticeDao;
	public NoticeService() {
		noticeDao = new JdbcNoticeDao();
	}

	public List<Notice> getList(int page, int size, String field, String query) {
		
		int startIndex = 1+(page-1)*size;//1, 11, 21, 31, ...
		int endIndex = page*10;//10,20,30,40,50,60... 
		
		// [*제목][ 하하 ] [검색]
		
		return noticeDao.getList(startIndex, endIndex, field, query);
	}
	
	public int deleteAll(int[] ids) {
		
		// Dao를 사용하는 이유
		// 1. 협업
		// 2. 재사용
		// 3. 데이터(소스)를 숨기는 것
		
		
		// DELETE NOTICE WHERE ID IN (......);
		//noticeDao.deleteAll(ids);
		
		int result = 0;
		for(int i=0; i<ids.length; i++) {
			int id = ids[i];
			result += noticeDao.delete(id);
		}
		
		return result;
	}
	
	public int hitUp(int id) {
		
		//int result = noticeDao.hitUp(id);
		
		// SELECT * FROM NOTICE WHERE ID=?
		Notice notice  = noticeDao.get(id); 
		notice.setHit(notice.getHit()+1);
		// UPDATE NOTICE SET ... WHERE ID=?
		int result = noticeDao.update(notice);
		
		return result;
	}
	
	public List<NoticeView> getViewList(int page, int size) {
		
		int startIndex = 1+(page-1)*size;//1, 11, 21, 31, ...
		int endIndex = page*10;//10,20,30,40,50,60... 
				
		return noticeDao.getViewList(startIndex, endIndex);
	}
	
	public Notice get(int id) {
		
		return noticeDao.get(id);
	}

	public int insert(Notice notice) {
		return noticeDao.insert(notice);
	}


	public int update(Notice notice) {
		int result = 0;
		
		result = noticeDao.update(notice);
		
		return result;
	}
	
	public int delete(int id) {
		int result =0;
		
		result = noticeDao.delete(id);
		
		
		return result; 
	}

	public int getLastId() {
		Notice n = noticeDao.getLast();
		
		// 업데이트 -> 컬럼
		// 서비스에서는 공개값 업데이트/좋아요 업데이트/조회수 업데이트
		// get -> set -> update();
		return n.getId();
	}
	
}
