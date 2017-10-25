import org.testng.annotations.Test;

public class Test_1 extends BaseTest{
   @Test
   public void testGoogle(){
       driver.get("http://www.google.com");
       System.out.println();
   }
}
