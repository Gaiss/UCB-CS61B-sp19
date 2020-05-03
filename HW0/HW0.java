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
         while(x>1){
            System.out.print('*');
            x -= 1;
         }
         System.out.println('*');
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
      System.out.println(forMax(numbers));
   }
}


/* EX4 */
public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    for(int i = 0;i < a.length;i += 1){
      if(a[i] < 0){
        continue;
      }
      int sum = a[i];
      for(int j = 1;j <= n;j += 1){
        if(i + j == a.length){
          break;
        }
        sum += a[i + j];
      }
      a[i] = sum;
    }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}
