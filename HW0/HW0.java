/* EX1 */
public class ClassNameHere {
   public static void main(String[] args) {
      int N = 10;
      DrawTriangle(N);
   }
   public static void DrawTriangle(int rows){
      int cols = 1;
      while(rows-cols>=0){
         int x = cols;
         while(x>0){
            if(x==1){
               System.out.println('*');
            }else{
               System.out.print('*');
            }
            x -= 1;
         }
         cols += 1;
      }
   }
}


/* EX2 */
public class ClassNameHere {
   public static int max(int[] m) {
      int getmax = m[0];
      int i = 1;
      while(i<m.length){
         if(m[i]>getmax){
            getmax = m[i];
         }
         i += 1;
      }
      return getmax;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
      System.out.println(max(numbers));
   }
}


/* EX3 */
public class ClassNameHere {
   public static int forMax(int[] m) {
      int getmax = m[0];
      for(int i=1;i<m.length;i=i+1){
         if(m[i]>getmax){
            getmax = m[i];
         }
      }
      return getmax;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
      System.out.println(max(numbers));
   }
}
