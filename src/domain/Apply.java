package domain;


public class Apply {

      // 회원(추상) 개인*사업주(구상)
      //가입, 수정, 탈퇴
      //1) 사업자 : 구현/ ID, Pw, 대표자명, 상호명, 연락처
   //2) 개인회원 : 구현/ ID, PW, 이름, 거주지, 연락처, 이력서

   private String ceoName; // 상호명
   private String companyName; // 상호명
   int number;// 연락처
   private String id; 
   private String pw;
   //


   public Apply(String ceoName, String companyName, int number, String id, String pw) {
      super();
      this.ceoName = ceoName;
      this.companyName = companyName;
      this.number = number;
      this.id = id;
      this.pw = pw;
   }

   @Override
   public String toString() {
      return "Apply [ceoName=" + ceoName + ", companyName=" + companyName + ", number=" + number + ", id=" + id
            + ", pw=" + pw + "]";
   }


   public String getCeoName() {
      return ceoName;
   }


   public void setCeoName(String ceoName) {
      this.ceoName = ceoName;
   }


   public String getCompanyName() {
      return companyName;
   }


   public void setCompanyName(String companyName) {
      this.companyName = companyName;
   }


   public int getNumber() {
      return number;
   }


   public void setNumber(int number) {
      this.number = number;
   }


   public String getId() {
      return id;
   }


   public void setId(String id) {
      this.id = id;
   }


   public String getPw() {
      return pw;
   }


   public void setPw(String pw) {
      this.pw = pw;
   }
}