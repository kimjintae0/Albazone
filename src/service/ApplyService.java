package service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import domain.Apply;
import domain.Gonggo;

import static utils.AlbaUtils.*;

@SuppressWarnings("unchecked")
public class ApplyService {

	// 싱글톤
	private static ApplyService applyService = new ApplyService();
	private ApplyService() {}
	public static ApplyService getInstance() {
		return applyService;
	}
	
	
	// 지원 리스트 생성
	List<Apply> applyList = new ArrayList<>();
	
	// 초기화 블럭 - 파일 불러오기
	{
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/apply.ser"));
			applyList = (List<Apply>)ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Apply : 파일을 불러올 수 없습니다. 임시 데이터셋으로 진행합니다.");
			applyList.add(new Apply(1, 1, 2));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// ========================================= 메서드 ==============================================
	
	
	// 지원하기 - 알바
	public void apply() {
		int gonggoNo = GonggoService.getInstance().gonggoSelectUser();
		if(gonggoNo == 0) {
			System.out.println("접근 권한이 없는 공고입니다.");
			return;
		}
		ResumeService.getInstance().lookupUser();
		int resumeNo = ResumeService.getInstance().resumeSelect();
		if (resumeNo == 0) {
			System.out.println("존재하지 않는 이력서입니다.");
			return;
		}
		for(Apply a : applyList) {
			if(a.getGonggoNo() == gonggoNo && a.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
				System.out.println("이미 지원한 공고입니다.");
				return;
			}
		}
		if(!nextConfirm("해당 공고에 지원하시겠습니까?")) {
			System.out.println("공고 지원이 취소되었습니다.");
			return;
		}
		applyList.add(new Apply(gonggoNo, resumeNo, UserService.getInstance().getLoginUser().getUserNo())); // 로그인 유저의 이력서 중, 유저가 선택한 이력서의 번호 
		System.out.println("공고 지원 완료");
		save();
	}
	
	
	// 지원내역 조회 - 알바
	public void lookupUser() {
			boolean check = false;
			for(Apply a : applyList) {
				if(a.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
					System.out.println("지원 시간 : " + dateFormat(a.getApplyDate()) + "\n지원상태 : " + (a.getApplySitu() == 0 ? "접수" : "읽음"));
					System.out.println(GonggoService.getInstance().findGonggoBy(a.getGonggoNo()));
					check = true;
				}
			}
			if(!check) {
				System.out.println("지원내역이 없습니다.");
			}
	}
	
	
	
	// 지원 취소
	public void remove() {
		lookupUser();
		Apply removeApply = null;
		boolean check = false;
		int input = nextInt("지원을 취소하실 공고의 번호를 입력해주세요.");
		for(Apply a : applyList) {
			if(a.getUserNo() == UserService.getInstance().getLoginUser().getUserNo() && a.getGonggoNo() == input) {
				removeApply = a;
				check = true;
			}
		}
		if(!check) {
			System.out.println("지원 이력을 찾을 수 없습니다.");
			return;
		}
		applyList.remove(removeApply);
		System.out.println("지원이 취소되었습니다.)");
		save();
	}
	
	
	
	// 내 공고에 지원한 내역 조회 - 사업자
	public void lookupUserOwner() {
		for(Gonggo g : GonggoService.getInstance().gonggoList) {
			if(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo() && g.state == true) {
				System.out.println(g.toString());
			}
		}
		int input = GonggoService.getInstance().gonggoSelectOwner();
		if(input == 0) {
			System.out.println("마감된 공고이거나 접근 권한이 없는 공고입니다.");
			return;
		}
		for(Apply a : applyList) {
			if(a.getGonggoNo() == input) {
				System.out.println("지원날짜"+  dateFormat(a.getApplyDate()));
				ResumeService.getInstance().lookupOwner(a.getResumeNo());
				a.setApplySitu(a.getApplySitu() + 1);
			}
		}
		save();
	}
	
	
	
	// 회원탈퇴, 거주지 수정시 유저의 지원내역 전체삭제 - 유저 
	public void removeAllUser() {
		for(Apply a : applyList) {
			if (a.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
				applyList.remove(a);
			}
		}
		System.out.println("지원내역이 전체 삭제되었습니다.");
		save();
	}
	
	// 이력서 삭제시 지원내역 삭제 - resumeNo입력 후 해당 이력서로 지원한 내역 삭제
	public void removeByResumeNo(int resumeNo) {
		List<Apply> applys = new ArrayList<>();
		for(Apply a : applyList) {
			if(a.getResumeNo() == resumeNo) {
				applys.add(a);
			}
		}
		applyList.removeAll(applys);
		save();
	}
	
	
	
	// 공고 수정, 삭제 시 해당공고 지원내역 전체삭제 - 사업자
	public void removeAllOwner(int gonggoNo) {
				for(Apply a : applyList) {
					if(a.getGonggoNo() == gonggoNo) {
						applyList.remove(a);
					}
				}
		System.out.println("해당 공고에 지원한 지원내역이 전체 삭제되었습니다.");
		save();
	}
	
	
//		============================== 자체 유틸 ============================
	
		
		
		
		// 입력 resumeNo 출력 List<Apply>
		public List<Apply> findApplysByResume(int resumeNo) {
			List<Apply> applies = new ArrayList<>();
			for(Apply a : applyList) {
				if(a.getResumeNo() == resumeNo) {
					applies.add(a);
				}
			}
			return applies;
		}
		
		
		// 입력 gonggoNo 출력 List<Apply>
		public List<Apply> findApplysByGonggo(int gonggoNo){
			List<Apply> applies = new ArrayList<>();
			for(Apply a : applyList) {
				if(a.getGonggoNo() == gonggoNo) {
					applies.add(a);
				}
			}
			return applies;
		}
		
		// 파일로 저장하기
		private void save() {
			try {
				File file = new File("data");
				if(!file.exists()) {
					file.mkdirs();
				}
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file, "apply.ser")));
				oos.writeObject(applyList);
				oos.close();
			}
			catch (Exception e) {
				System.out.println("파일 접근 권한이 없습니다.");
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
//		============================= 주석처리 ==============================
		
//		// 로그인한 사용자가 이력서들
//		List<Resume> resumes = ResumeService.getInstance().findResumeBy(UserService.getInstance().getLoginUser().getUserNo());
//		// 해당 이력서들로 지원한 지원서들
//		List<Apply> applies = new ArrayList<>();
//		for(Resume r : resumes) {
//			applies.addAll(findApplysBy(r.getResumeNo()));
//		}
//		// 해당 지원서들에 해당되는 공고
//		List<Gonggo> gonggoes = new ArrayList<>();
//		for(Apply a : applies) {
//			System.out.println("지원 시간 :" + dateFormat.format(a.getApplyDate()) + "\n지원상태 : " + (a.getApplySitu() == 0 ? "접수" : "읽음")); 
//			gonggoes.addAll(GonggoService.getInstance().findGonggoBy(a.getGonggoNo()));
//			System.out.println(gonggoes.get(gonggoes.size()-1).toString());
//		}
		
		
	
	
	
	
	
	

	
}
