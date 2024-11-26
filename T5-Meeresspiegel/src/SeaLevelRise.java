import java.util.Arrays;

public class SeaLevelRise {
  public static String heightProfile(String water, int anstieg) {
    if (!isValidLand(water))
      return "Error: Invalid land supplied";

    String output = "";
    char temp;

    for (int i = 0; i < water.length(); i++) {
      temp = (char) (water.charAt(i) - anstieg);

      if (temp <= '0') {
        output += '0';
      } else if (temp <= '9') {
        output += temp;
      }
    }
    return output;
  }

  /**
   * @param water    z.B. 001222234322222222234544322211100000121
   * @param seaLevel höhe des Grundwassers
   * @return (water - seaLevel) z.B. 000000012100000000012322100000000000000 für
   *         seaLevel 2
   */
  public static String groundwater(String water, int seaLevel) {
    if (!isValidLand(water))
      return "Error: Invalid land supplied";

    String result = "";

    for (char digitChar : water.toCharArray()) {
      int digit = Character.getNumericValue(digitChar);

      int adjustedWater = digit - seaLevel;

      if (adjustedWater < 0) {
        adjustedWater = 0;
      }
      result += adjustedWater;
    }

    return result;
  }

  /**
   * Prüft, ob {@code land} aus gültigen Zeichen besteht:
   * {@code '9' >= land[i] >= '0'}
   * 
   * @param land
   */
  public static boolean isValidLand(String land) {
    for (char c : land.toCharArray()) {
      if (c < '0')
        return false;
      if (c > '9')
        return false;
    }

    return true;
  }

  /**
   *
   * @param s
   * @return {{startIndex, endIndex}, ...}
   */
  public static int[][] splitOnWater(String s) {
    int splitCount = 0;
    boolean inWater = false;

    int startIndex = -1;
    int endIndex = -1;

    for (int i = 0; i < s.length(); i++) { // split on land, x*0, land
      if (inWater && s.charAt(i) != '0') {
        splitCount++;
        inWater = false;
      }

      if (!inWater && s.charAt(i) == '0') {
        inWater = true;
      }
    }

    int[][] splits = new int[splitCount][2];

    inWater = false;
    int arrIndex = 0;

    // splits = 0 land 0 or land 0 or 0 land

    for (int i = 0; i < s.length(); i++) { // split on land, x*0, land
      if (i == 0 && s.charAt(i) != '0') {
        startIndex = 0;
      }

      if (inWater && s.charAt(i) != '0') {
        inWater = false;
        startIndex = i;
      }

      else if (!inWater && s.charAt(i) == '0') {
        inWater = true;

        if (startIndex != -1) {
          endIndex = i - 1;
          splits[arrIndex++] = new int[] { startIndex, endIndex };
          startIndex = -1;
        }
      }

      if (i == s.length() - 1 && s.charAt(i) != '0') {
        endIndex = i;
        splits[arrIndex++] = new int[] { startIndex, endIndex };
      }
    }

    return splits;
  }

  public static String substr(String s, int startIndex, int endIndex) {
    String out = "";

    for (int i = startIndex; i <= endIndex; i++) {
      out += s.charAt(i);
    }

    return out;
  }

  public static boolean isWater(String land, int index) {
    if (index < 0)
      return false;
    if (index >= land.length())
      return false;
    if (land.charAt(index) != '0')
      return false;
    return true;
  }

  /**
   * <p>
   * {@code seaLevel} steigt von allen {@code 0}en aus an und überflutet
   * {@code land}.
   * </p>
   * <p>
   * Höhe {@code n} wird bei {@code seaLevel} {@code n} überflutet.
   * </p>
   *
   * @param land z.B. {@code 001222234322222222234544322211100000121}
   * @return Zeitpunkt der Überschwemmung für jedes {@code land}-Feld z.B.
   *         {@code 001222234444444444444544322211100000122}
   */
  public static String seawater(String land) {
    // TODO check if no water present at all

    if (!isValidLand(land))
      return "Error: Invalid land supplied";

    String output = "";

    int[][] splits = splitOnWater(land);

    for (int i = 0; i < splits.length; i++) {

      String tile = substr(land, splits[i][0], splits[i][1]);

      // System.out.print(Arrays.toString(splits[i]) + " ");
      // System.out.print(tile + "\n");

      int startIndex = splits[i][0];
      int endIndex = splits[i][1];

      boolean checkLeft = isWater(land, startIndex - 1);
      boolean checkRight = isWater(land, endIndex + 1);

      tile = checkFlooding(tile, checkLeft, checkRight);

      for (int j = output.length(); j <= endIndex; j++) {
        if (j < startIndex)
          output += "0";
        else
          output += tile.charAt(j - startIndex);
      }
    }

    for (int j = output.length(); j < land.length(); j++) {
      output += "0";
    }

    return output;
  }

  public static String checkFlooding(String land, boolean checkLeft, boolean checkRight) {

    // check only left

    if ((checkLeft) && (!checkRight)) {

      String substringleft = "";
      int currentheight = 0;
      int currentindex = 0;

      while (currentindex < land.length()) {

        if (currentheight + 48 < land.charAt(currentindex)) {
          currentheight += 1;
        } else if ((currentheight + 48) >= land.charAt(currentindex)) {
          substringleft += (char) (currentheight + 48);
          currentindex++;
        }
      }
      return substringleft;
    }

    // check only right

    if ((!checkLeft) && (checkRight)) {

      String substringright = "";
      int currentheight = 0;
      int currentindex = land.length() - 1;

      while (currentindex >= 0) {

        if (currentheight + 48 < land.charAt(currentindex)) {
          currentheight += 1;
        } else if ((currentheight + 48) >= land.charAt(currentindex)) {
          substringright = ((char) (currentheight + 48)) + substringright;
          currentindex--;
        }
      }
      return substringright;
    }

    if ((checkLeft) && (checkRight)) {
      String substringleft = "";
      String substringright = "";

      int currentheight = 0;

      int indexleft = 0;
      int indexright = land.length() - 1;
      boolean increaseheightleft = false;
      boolean increaseheightright = false;

      while ((indexright - indexleft) >= 0) {

        if (currentheight + '0' < land.charAt(indexleft)) {
          increaseheightleft = true;
        } else if ((currentheight + '0') >= land.charAt(indexleft)) {
          while ((currentheight + '0') >= land.charAt(indexleft) && (indexright - indexleft) > 0) {
            substringleft += (char) (currentheight + '0');
            indexleft++;
          }
        }

        if (currentheight + '0' < land.charAt(indexright)) {
          increaseheightright = true;
        } else if ((currentheight + '0') >= land.charAt(indexright)) {
          while ((currentheight + '0') >= land.charAt(indexright) && (indexright - indexleft) >= 0) {
            substringright = ((char) (currentheight + '0')) + substringright;
            indexright--;
          }
        }

        if (increaseheightleft && increaseheightright) {
          currentheight++;
        }
      }

      return (substringleft + substringright);
    }

    return null;
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Usage: java SeaLevelRise <mode> <landprofile=[01]*>");
      return;
    }

    String mode = args[0];
    String land = "";

    for (int i = 1; i < args.length; i++) {
      land += args[i];
    }

    // String land = "001222234322222222234544322211100000121";
    // int anstieg = 3;

    // System.out.println("Höhenprofil nach Anstieg um " + anstieg + " :" +
    // heightProfile(land, anstieg));
    if (mode.equals("groundwater")) {
      for (int i = 0; i < 10; i++) {
        System.out.println("Höhenprofil bei NN = " + i);
        System.out.println(groundwater(land, i));
      }
    } else if (mode.equals("seawater")) {
      System.out.println(seawater(land));
    } else {
      System.err.println("Mode not supported");
    }
  }
}