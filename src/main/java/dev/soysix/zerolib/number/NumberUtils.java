package dev.soysix.zerolib.number;

public class NumberUtils {
   public static boolean checkInt(String s) {
      try {
         int var1 = Integer.parseInt(s);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }

   public static boolean checkDouble(String s) {
      try {
         double var1 = Double.parseDouble(s);
         return true;
      } catch (NumberFormatException var3) {
         return false;
      }
   }

   public static boolean checkLong(String s) {
      try {
         long var1 = Long.parseLong(s);
         return true;
      } catch (NumberFormatException var3) {
         return false;
      }
   }
}
