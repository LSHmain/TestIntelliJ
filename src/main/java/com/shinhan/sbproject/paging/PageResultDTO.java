package com.shinhan.sbproject.paging;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.shinhan.sbproject.entity.BoardEntity;
import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.repositoryfinal.WebBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;


// PageResultDTO : 페이징한 정보를 client에 보내기 (Entity를 DTO로 변경하여 보낸다)
@Data
public class PageResultDTO<DTO, EN> {
	//data
	private List<DTO> dtoList;
	// 총페이지번호
	private int totalPage;
	// 현재페이지 번호
	private int page;
	// 목록사이즈
	private int size;
	// 시작페이지번호, 끝페이지번호
	private int start, end;
	// 이전, 다음
	private boolean prev, next;
	// 페이지번호 목록
	private List<Integer> pageList;

//	@Autowired
//	WebBoardRepository repo;


	// Page<Entity>객체들을 DTO객체로 변환해서 자료구조에 넣기 
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
//		List<WebBoardEntity> blist = repo.findAll();
		dtoList = result.stream().map( fn
//				b -> mapper.map(b, WebBoardDTO.class)
		).collect(Collectors.toList());
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());	 
	}
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		int tempEnd = (int)(Math.ceil(page/10.0))*10;  //1~10  start:1  end:10   11~20 
		//한화면에 10개의 페이지번호를 출력하기로 가정함  
		start = tempEnd - 9;    		
		end = totalPage > tempEnd?tempEnd:totalPage;
		end = totalPage < 10 ? totalPage : end;  //10개보다 작다면 page번호까지만 		
		prev = start > 1;  //전페이지기 있는가?
		next = totalPage > tempEnd;
		System.out.printf("tempEnd:%d, totalPage:%d start:%d end:%d \n" ,
				tempEnd, totalPage,start,end);

		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
}



