package avoid.using.reentrant.locks;

import static java.lang.System.out;

public class TestIdentityHash {
	
	public static class SimpleData{
		private String str;
		public SimpleData(String str){
			this.str = str;
		}
		
	}
	private static final String NEW_LINE = System.getProperty("line.separator");

	   /**
	    * Print the overridden and identity hash codes of the provided object.
	    *
	    * @param object Object whose overridden and identity hash codes are to be
	    *    printed to standard output.
	    */
	   public static void printHashCodes(final Object object)
	   {
	      out.println(
	           NEW_LINE + "====== "
	         + String.valueOf(object) + "/"
	         + (object != null ? object.getClass().getName() : "null")
	         + " ======");
	      out.println(
	           "Overridden hashCode: "
	         + (object != null ? object.hashCode() : "N/A"));
	      out.println("Identity   hashCode: " + System.identityHashCode(object));
	   }

	   /**
	    * Main executable function.
	    *
	    * @param arguments Command-line arguments; none expected.
	    */
	   public static void main(final String[] arguments)
	   {
	      final Integer int1 = 1;
	      final int int2 = 1;
	      final Long long1 = 1L;
	      final long long2 = 1L;
	      final String str = "SomeString";
	      final String nullStr = null;
	      final SimpleData simpleData = new SimpleData("AnotherString");

	      printHashCodes(int1);
	      printHashCodes(int2);
	      printHashCodes(long1);
	      printHashCodes(long2);
	      printHashCodes(str);
	      printHashCodes(nullStr);
	      printHashCodes(simpleData);
	   }
}
