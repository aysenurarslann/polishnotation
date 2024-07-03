package polishnotation;
import java.util.*;// 'util' paketinden tüm sınıfları içe aktarır. bu kodda 'Stack' ve 'scanner' sınıfları için kullandık


public class PolishNotation { //ana sınıf
	static boolean isOperator(String ch) // bu metod, bir stringin operator olup olmadığını kontrol eder.
	//"+","-","*","/" operatörlerinden birine eşitse 'true' döner. aksi takdirde 'false'.
	  {
	      if(ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/"))
	      return true;
	      
	      return false;
	  }    
	    
	  static int evaluatePrefix(String exp[])// Bu metod, prefix notasyonunu kullanarak ifadeyi değerlendirir.
	  // Bir dizi olarak alınan ifadeyi traverse eder, operatörleri ve operandları değerlendirir ve hesaplar.
	  {
	      // exp dizisinin uzunluğunu al
	      int n= exp.length;
	      // sonucu değerlendirmek için yığın
	      Stack<Integer> st = new Stack<>();// 'Stack' sınıfından 'st' nesnesini oluşturur.
	      // Stack, veri yapısı olarak son giren, ilk çıkan(LIFO) prensibine göre çalışır.
	      
	      for(int i=n-1;i>=0;i--)//Bu döngü ifade dizisini tersten okur.
	      {
	          // check if each value in array is an operator or not.
	          if(isOperator(exp[i])) // mevcut elemanın bir operatör olup olmadığını kontrol eder
	          {
	              int first = st.pop();
	              int second = st.pop();
	              //Operatörse, 'int first=st.pop();' ve 'int second=st.pop();' ile stackten iki önceki değerleri çıkarır.
	              
	              
	             //Bu blokta operatörlere göre işlem yapar ve sonucu tekrar stacke ekler(st.push(temp))
	              if(exp[i].equals("+"))
	              {
	                  int temp = first + second;
	                  st.push(temp);
	              }
	              else if(exp[i].equals("-"))
	              {
	                  int temp = first - second;
	                  st.push(temp);
	              }
	              if(exp[i].equals("*"))
	              {
	                  int temp = first * second;
	                  st.push(temp);
	              }
	              if(exp[i].equals("/"))
	              {
	                  int temp = first / second;
	                  st.push(temp);
	              }
	              
	          }
	          else
	          {
	              
	        	  //Operatör değilse, bu durumda eleman bir operandtır ve doğrudan stack'e eklenir
	              st.push(Integer.parseInt(exp[i]));
	          }
	      }
	      //Döngü bittiğinde stackte kalan tek değer sonucu temsil eder ve 'return st.pop()' ile döndürülür. Bu bizim sonucumuzdur
	      return st.pop();
	  }
	    
	  public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);// Kullanıcıdan giriş almak için bir 'Scanner' nesnesi oluşturur

	        System.out.println("Lütfen bir önek ifade girin:");
	        String exp = scanner.nextLine();//kullanıcıdan alınan ifade bir stringe atanır

	        // we split the operators and operands on basis of space to avoid confusion with double digit numbers
	        // and easy traversal.
	        String exp_arr[] = exp.split(" ");// Girilen ifade boşluklara bölünerek bir diziye atanır. Bu, ifade içindeki sayıları ve operatörleri ayırmak için kullanılır

	        System.out.println("The Polish Notation is : " + exp);

	        int result = evaluatePrefix(exp_arr);

	        System.out.println("The Result after Evaluation is : " + result);
	    }
}
