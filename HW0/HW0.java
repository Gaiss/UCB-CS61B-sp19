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
