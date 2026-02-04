import java.io.*;
import java.util.Scanner; 

class FileInputTest { 
   public static FileInputStream foo(String fileName) throws FileNotFoundException
   {
     System.out.println("foo: Started");
     FileInputStream fis = new FileInputStream(fileName);
     System.out.println("foo: Returned");
     return fis;
   } 

  public static void main(String args[])
    {      
      FileInputStream fis = null;
      Scanner in = new Scanner(System.in);
      while(true){
        try{
          String fileName = in.next();
          fis = foo(fileName);
          System.out.println("main: Started"); 
          break;
        }
        catch(FileNotFoundException e){
          System.out.println("파일이 존재하지 않음. 재입력 요망");
        }
      }

      System.out.println("main: Ended");
    }
 } 