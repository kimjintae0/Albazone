package service;

import java.util.ArrayList;
import java.util.List;

import domain.Resume;

public class ResumeService {
	private static ResumeService resumeService = new ResumeService();
	private ResumeService() {}
	public static ResumeService getInstance() {
		System.out.println();
		return resumeService;
	}
	
	List<Resume> resumes = new ArrayList<>();
	
	
	
	
	
}




