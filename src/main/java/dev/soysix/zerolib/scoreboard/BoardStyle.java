package dev.soysix.zerolib.scoreboard;

public enum BoardStyle {
   MODERN(false, 1),
   KOHI(true, 15),
   VIPER(true, -1),
   TEAMSHQ(true, 0);

   private int start;
   private boolean descending;

   private BoardStyle(boolean descending, int start) {
      this.descending = descending;
      this.start = start;
   }

   public boolean isDescending() {
      return this.descending;
   }

   public int getStart() {
      return this.start;
   }
}
