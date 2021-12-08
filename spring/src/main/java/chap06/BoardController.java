package chap06;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;//다형성 개념을 적용
	
	@GetMapping("/board/index.do")
	public String index(Model model, HttpServletRequest req, BoardVo vo) {// 커맨드 객체이므로 BoardVo vo를 매개변수로 먼저 받아야 selectList()안에 넣을 수 있다.
		int totCount = boardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수 // 정수와 정수의 연산값은 정수! 여기서는 게시글 수가 정해져서 10으로 표시했지만, 변수로 하면 더 편하다.
		if (totCount % 10 > 0) totPage++;//총 게시글 수가 10개로 나눴을 때, 나머지가 0보다 크면 총 페이지수를 +1 해야 한다.
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);//vo에 startIdx값이 담겨있어야만 xml에서 사용가능
		
//		String searchWord = req.getParameter("searchWord");//파라미터를 두 개(searchWord, searchType) 받고 있음 -> 그 안에는 null이 있다.
//		String searchType = req.getParameter("searchType");
		List<BoardVo> list = boardService.selectList(vo);//대입연산은 제일 마지막에 실행되므로, boardService.selectList()가 먼저 실행되고 나서 list에 대입된다.
		model.addAttribute("list", list);// 앞에 "list"는 문자열, 뒤의 list는 변수 => list라는 객체에 list라는 이름으로 담김
//		model.addAttribute("word", searchWord);//를 추가하면, index.jsp에서 value="<%=request.getParameter("word")%>" 또는 ${word}를 써도 된다.
		
		model.addAttribute("totPage", totPage);// index.jsp에 가져오려면 reqeust에 저장되어 있어야만 가져올 수 있다.
		
		return "board/index";
	}
	
	@RequestMapping("/board/write.do")
	public String write() {//쓰기페이지에 DB를 연동해서 가끔 값이 들어오거나 select할 때(카테고리, 말머리 등 관리해야 하는 부분 있는 경우)는 값이 필요할 때가 있다.
		return "board/write";
	}
	
	@PostMapping("/board/insert.do")//write.jsp에서 form을 POST방식으로 전송하므로, 매핑방식도 POST
	public String insert(BoardVo vo, HttpServletRequest req, MultipartFile file) {// parameter(form 값) 받는 방법으로, 커맨드 객체가 편리해서 많이 사용된다./ MultipartFile : 사용자가 업로드한 file(단, 파일명이 동일해야 한다)을 자동으로 객체에 받아준다.
		// 파일 저장(실제 폴더에 저장)
		if (!file.isEmpty()) {//비어있지 않으면 = 사용자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();// 사용자가 업로드한 파일이름(원본)
				file.transferTo(new File(path+filename)); // 경로에 파일을 저장
				vo.setFilename(filename);// vo에 set을 해줘야만 insert할 때 SQL에 파일명이 들어가서 DB에 나온다.
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		int r = boardService.insert(vo);
		System.out.println("r: "+r);
		
		// alert : 정상적으로 등록되었습니다. 라고 띄우고
		// 경로 이동 : index.do로 이동
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
		return "/include/result";
	}
	
	@GetMapping("/board/detail.do")
	public String detail(Model model, @RequestParam int boardno) {//한행 한행 값을 가져온다.
		model.addAttribute("data", boardService.selectOne(boardno));//request에 date라는 이름으로 set이 된다.
		return "board/detail";
	}
	
	@GetMapping("/board/detail2.do")
	public String detail2(Model model, @RequestParam int boardno) {//한행 한행 값을 가져온다.
		model.addAttribute("data", boardService.selectOne2(boardno));//request에 date라는 이름으로 set이 된다.
		return "board/detail2";
	}
	
	@GetMapping("/board/edit.do")
	public String edit(Model model, @RequestParam int boardno) {//한행 한행 값을 가져와야 하므로 detail과 동일하다.
		model.addAttribute("data", boardService.selectOne(boardno));//boardno로 조회한 행의 결과가 BoardVo 객체에 담겨서
		return "board/edit";
	}
	
	@PostMapping("/board/update.do")
	public String update(Model model, BoardVo vo) {//메세지, url을 담으려면 Model을 써서 값을 담아주는게 좋다. + 커맨드 객체 이용(여러 값이 들어오므로)
		int r = boardService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "detail.do?boardno="+vo.getBoardno());//성공했을 때 상세페이지로 이동 => 해당 되는 boardno의 상세페이지로 이동되어야 하므로!
		} else {// 수정페이지에서 파라미터값을 보면 boardno=0 으로 나오는데, 왜? edit.jsp에 boardno 값이 없기 때문에 boardVo 필드에 선언된 boardno의 값 0 으로 나온다.
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?boardno="+vo.getBoardno());//실패했을 때 수정페이지로 이동
		}
		return "include/result";
	}
	
	@GetMapping("/board/delete.do")
	public String delete(Model model, BoardVo vo) {
		int r = boardService.delete(vo);
		if ( r > 0 ) {//삭제하기 - 확인을 눌러야만 BoardController로 넘어오기 때문에, 삭제된 후(시점이 다르다)에 결과를 출력한다.
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");//성공했을 때 목록페이지로 이동
			// 게시판 페이지가 여러 개일 때, 보던 페이지가 3페이지면 해당 페이지를 가도록 코딩을 추가해야 한다.
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "detail.do?boardno="+vo.getBoardno());//실패했을 때 상세페이지로 이동
		}
		return "include/result";
	}
}
