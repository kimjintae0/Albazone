package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import static utils.AlbaUtils.*;

import domain.AlbaUser;
import domain.Resume;


public class ResumeService {
	// 이력서 리스트 생성
	List<Resume> resumeList = new ArrayList<>();
	
	// 싱글톤
	private static ResumeService resumeService = new ResumeService();
	public static ResumeService getInstance() {
		System.out.println();
		return resumeService;
	}
	
	// 클래스 연동
	AlbazoneService albazoneService = AlbazoneService.getInstance();
	UserService userService = UserService.getInstance();
	ApplyService applyService = ApplyService.getInstance();
	GonggoService gonggoService = GonggoService.getInstance();
	
	// 초기화 블럭
		{ 
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream("data/resume.ser")); // 보조 입력 스트림 , 입력
				resumeList = (List<Resume>) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				System.out.println("resume : 파일을 불러올 수 없습니다. 임시 데이터셋으로 진행합니다.");
				resumeList.add(new Resume(2, 1, "김진태님의 이력서입니다.", "김진태", "010-1111-1111", "서울", "안녕하세요. 저는 김진태입니다."));

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	
	
	// 이력서 작성
	public void resister() {
		// 알바유저 번호, 이력서 번호, 제목, 알바이름, 알바 연락처, 알바지역, 자기소개
		String title = nextLine("이력서 제목을 작성해주세요.");
		String introduce = nextLine("자기소개를 작성해주세요.");
		// 이력서 번호 관리
		int num = resumeList.size() == 0 ? 1 : resumeList.get(resumeList.size() - 1).getResumeNo() + 1;
		resumeList.add(new Resume(UserService.getInstance().getLoginUser().getUserNo(), num, title, UserService.getInstance().getLoginUser().getName(), UserService.getInstance().getLoginUser().getTel(), UserService.getInstance().getLoginUser().getArea(), introduce));
		System.out.println("이력서 작성 완료");
		save();
	}
	
	// 이력서 조회 - 	알바
	public void lookupUser() {
		for(Resume resume : resumeList) {
			if(resume.getUserNo() == UserService.getInstance().getLoginUser().getUserNo() && UserService.getInstance().getLoginUser() instanceof AlbaUser) {
				System.out.println(resume.toString());
			}
		} 
	}
	
	// 이력서 회원정보 변경
	public void resumeSync() {
		for(Resume resume : resumeList) {
			if (resume.getUserNo() == UserService.getInstance().getLoginUser().getUserNo() && UserService.getInstance().getLoginUser() instanceof AlbaUser) {
				resume.setName(UserService.getInstance().getLoginUser().getName());
				resume.setArea(UserService.getInstance().getLoginUser().getArea());
				resume.setTel(UserService.getInstance().getLoginUser().getTel());
			}
		}
	}
	
	// 입력 : 이력서 번호, 출력 : 이력서 투스트링
	public void lookupOwner(int resumeNo) {
		for (int i = 0; i < resumeList.size(); i++) {
			if(resumeList.get(i).getResumeNo() == resumeNo) {
				System.out.println(resumeList.get(i).toString());
			}
		}
	}
	
	// 이력서 수정
	public void modify() {
		System.out.println("이력서 수정");
		Resume input = null;
		lookupUser();
		int select = resumeSelect();
		if (select == 0) {
			System.out.println("존재하지 않는 이력서입니다.");
			return;
		}
		for(Resume resume : resumeList) {
			if(resume.getResumeNo() == select) {
					input = resume;
			}
		}
		String title = nextLine("이력서 제목을 작성해주세요.");
		String introduce = nextLine("자기소개를 작성해주세요.");
		input.setTitle(title);
		input.setIntroduce(introduce);
		save();
	}
	
	
	// 이력서 삭제
	public void remove() {
		System.out.println("이력서 삭제");
		lookupUser();
		int select = resumeSelect();
		Resume removeResume = null;
		if (select == 0) {
			System.out.println("존재하지 않는 이력서입니다.");
			return;
		}
		for(Resume resume : resumeList) {
			if(resume.getResumeNo() == select) {
					removeResume = resume;
			}
		}
		ApplyService.getInstance().removeByResumeNo(removeResume.getResumeNo());
		resumeList.remove(removeResume);
		System.out.println("No." + removeResume.getResumeNo() + " 이력서가 삭제되었습니다.");
		save();
	}
	
	
	// 이력서 번호 선택
	public int resumeSelect() {
		int select = nextInt("이력서 번호를 입력해주세요.");	
		for(Resume resume : resumeList) {
			if(resume.getResumeNo() == select) {
				if(resume.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
					return select;
				}
			}
		}
		return 0;
	}
	
	// 회원번호를 입력하면 이력서들이 반환
	public List<Resume> findResumeBy(int no) {
		List<Resume> resumes = new ArrayList<>();
		for(Resume r : resumeList) {
			if(r.getUserNo() == no) {
				resumes.add(r);
			}
		}
		return resumes;
	}
	
	// 파일 저장 
		private void save() {
			try {
				File file = new File("data");
				if (!file.exists()) {
					file.mkdirs();
				}
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file, "resume.ser")));

				oos.writeObject(resumeList);
				oos.close();
			} catch (Exception e) {
				System.out.println("파일 접근 권한이 없습니다.");
				e.printStackTrace();

			}
		}
}