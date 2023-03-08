package com.nmBoard.test.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

	private int totalRecordCount; // 전체 게시글 개수
	private int totalPageCount; // 전체 페이지 수 :테이블에 1,000개의 레코드가 있고, 페이지당 출력할 데이터 개수가 10개라고 가정했을 때 (1,000 / 10)의 결과인 100

	private int startPage; // 첫 페이지 번호 : 페이지 하단에 출력할 페이지 수(pageSize)가 10이고,현재 페이지 번호(page)가 5라고 가정했을 때 1을 의미 (페이지번호 15라면, 11)
	private int endPage; // 끝 페이지 번호 : 페이지 하단에 출력할 페이지 수(pageSize)가 10이고, 현재 페이지 번호(page)가 5라고 가정했을 때 10을 의미 (페이지번호 15라면, 20)
	private int limitStart; // LIMIT 시작 위치, mysqldml limit 구문에 사용되는 멤버 변수 limit #{시작위치} #{limitStart를 기준으로 조회할 데이터의 개수 지정}

	private boolean existPrevPage; // 이전 페이지 존재 여부 : 현재 첫 페이지(startPage)가 1이 아니라면, 이전 페이지는 무조건적으로 존재
	private boolean existNextPage; // 다음 페이지 존재 여부

	private Criteria cri;

	public Pagination(int totalRecordCount, Criteria cri) {
		if (totalRecordCount > 0) {
			this.totalRecordCount = totalRecordCount;
			this.calculation(cri);
		}
	}

	private void calculation(Criteria cri) {

		// 전체 페이지 수 계산
		totalPageCount = ((totalRecordCount - 1) / cri.getRecordSize()) + 1;

		// 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
		if (cri.getPage() > totalPageCount) {
			cri.setPage(totalPageCount);
		}

		// 첫 페이지 번호 계산
		startPage = ((cri.getPage() - 1) / cri.getPageSize()) * cri.getPageSize() + 1;

		// 끝 페이지 번호 계산
		endPage = startPage + cri.getPageSize() - 1;

		// 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		// LIMIT 시작 위치 계산
		// 현재 페이지 번호가 1이고, 페이지당 출력할 데이터 개수가 10이라고 가정했을 때 (1 - 1) * 10 = 0이라는 결과가 나오게 되고, LIMIT 0, 10으로 쿼리가 실행
		// 다른 예로, 페이지 번호가 5라면, LIMIT 40, 10으로 쿼리가 실행
		limitStart = (cri.getPage() - 1) * cri.getRecordSize();

		// 이전 페이지 존재 여부 확인
		existPrevPage = startPage != 1;

		// 다음 페이지 존재 여부 확인
		// 페이지당 출력할 데이터 개수가 10개, 끝 페이지 번호가 10이라고 가정했을 때 (10 * 10) = 100 => 만약, 전체 데이터 개수가 105개라면, 다음 페이지 존재 여부는 true
		existNextPage = (endPage * cri.getRecordSize()) < totalRecordCount;
	}
}
