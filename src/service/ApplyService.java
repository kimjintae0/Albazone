package service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import domain.Apply;
import domain.Gonggo;
import domain.Resume;

import static utils.AlbaUtils.*;

@SuppressWarnings("unchecked")
public class ApplyService {

	// 싱글톤
	private static ApplyService applyService = new ApplyService();
	private ApplyService() {}
	public static ApplyService getInstance() {
		return applyService;
	}
	
	// 리줌, 공고 정보 가져오기
//	private UserService userService = UserService.getInstance();
//	private ResumeService resumeService = ResumeService.getInstance();

	
	
	// 지원 리스트 생성
	List<Apply> applyList = new ArrayList<>();
	
	// 초기화 블럭
	{
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/apply.ser"));
			applyList = (List<Apply>)ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Apply : 파일을 불러올 수 없습니다. 임시 데이터셋으로 진행합니다.");
			applyList.add(new Apply(1, 1));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
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
			if(a.getGonggoNo() == gonggoNo && a.getResumeNo() == resumeNo) {
				System.out.println("해당 이력서로 이미 지원한 공고입니다.");
				return;
			}
		}
		if(!nextConfirm("해당 공고에 지원하시겠습니까?")) {
			System.out.println("공고 지원 취소");
			return;
		}
		applyList.add(new Apply(gonggoNo, resumeNo)); // 로그인 유저의 이력서 중, 유저가 선택한 이력서의 번호 
		System.out.println("공고 지원 완료");
		save();
	}
	
	// 데이트 타입 포매터
	SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
	
	// 지원내역 조회 - 알바
	public void lookupUser() {
		int size = 0;
		for(Resume r : ResumeService.getInstance().resumeList) {
			if(r.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
				for(Apply a : applyList) {
					if(r.getResumeNo() == a.getResumeNo()) {
						for(Gonggo g : GonggoService.getInstance().gonggoList) {
							if(a.getGonggoNo() == g.getGonggoNo()) {
								System.out.println("지원 시간 : " + dateFormat.format(a.getApplyDate()));
								System.out.println("지원 상태 : " + (a.getApplySitu() == 0 ? "접수" : "읽음"));
								System.out.println(g.toString());
								size++;
							}
						}
					}
				}
			}
		}
		if(size == 0) {
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
			if(a.getGonggoNo() == input) {
				for(Resume r : ResumeService.getInstance().resumeList) {
					if(r.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
						if(a.getResumeNo() == r.getResumeNo()) {
							removeApply = a;
							check = true;
						}
					}
				}
			}
		}
		if(check = false) {
			System.out.println("지원 이력을 찾을 수 없습니다.");
			return;
		}
		applyList.remove(removeApply);
		System.out.println("지원이 취소되었습니다.(중복 지원시, 오래된 항목이 먼저 삭제됩니다.)");
		save();
	}
	
	// 내 공고에 지원한 내역 조회 - 사업자
	
	public void lookupUserOwner() {
		int size = 0;
		for(Gonggo g : GonggoService.getInstance().gonggoList) {
			if(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo() && g.state == true) {
				System.out.println(g.toString());
			}
		}
		int input = nextInt("지원내역을 확인하실 공고의 번호를 입력해주세요.");
		for(Gonggo g : GonggoService.getInstance().gonggoList) {
			if(!(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo() && g.state == true)) {
				input = 0;
			}
		}
		if(input == 0) {
			System.out.println("마감된 공고이거나 접근 권한이 없는 공고입니다.");
			return;
		}
		for(Apply a : applyList) {
			if(a.getGonggoNo() == input) {
				
				System.out.println("지원날짜"+ dateFormat.format(a.getApplyDate()));
				for(Resume r : ResumeService.getInstance().resumeList) {
					if(a.getResumeNo() == r.getResumeNo()) {
						System.out.println(r.toString());
						size++;
					}
				}
				a.setApplySitu(a.getApplySitu() + 1);
			}
		}
		if(size == 0) {
			System.out.println("지원자가 없습니다.");
		}
		save();
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
	
	
	
	
	
	
	
	
	
	

	
}
