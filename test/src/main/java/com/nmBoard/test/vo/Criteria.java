package com.nmBoard.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Criteria {
	
	/** 현재 페이지 번호 */
	private int page;
	
	/** 페이지당 출력할 게시글 개수 */
	private int recordSize;
	
	/** 화면 하단에 출력할 페이지 사이즈 : 5로 지정하면 1~5까지의 페이지 */
    private int pageSize;  
    
    private Pagination pagination;
    
    private String keyword;       // 검색 키워드
	
    /** 객체가 생성되는 시점에 기본값으로 현재 페이지 번호는 1로, 페이지당 출력할 데이터 개수 5와 하단에 출력할 페이지 개수를 10으로 초기화 */
    public Criteria() {
		this.page = 1;
		this.recordSize = 10;
		this.pageSize = 5;
	}
    
    public int getOffset() {
        return (page - 1) * recordSize;
        
    }
    
}
