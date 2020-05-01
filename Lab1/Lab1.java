public class LeapYear {
   public static boolean isLeapYear(int year) {
      if((year % 4 == 0 && year % 100 !=0)||year % 400 == 0){
         return true;
      }else{
         return false;
      }
   }
   
   public static void checkLeapYear(int year){
      if(isLeapYear(year)){
         System.out.printf("%d is a leap year.\n", year);
      }else{
         System.out.printf("%d is not a leap year.\n", year);
      }
   }
   
   public static void main(String[] args) {
      int[] y = {2000,1900,2004,2003,2100};
      for (int i = 0; i < y.length; i++) {
         checkLeapYear(y[i]);
      }
   }
}
