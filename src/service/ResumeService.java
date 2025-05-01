package service;


public class ResumeService {
	
	private static ResumeService resumeService = new ResumeService();
	private ResumeService() {}
	public static ResumeService getInstance() {
		return resumeService;
	
	
	}
	
}
