public class Main {

    // Array in dem die einzelnen Zeilen der Message gespeichert werden
    public static String[] messageArray = new String[0];

    // Speichere die Zeilen im Array
    public static void nextRow (String Row){

        String[] temp = new String[messageArray.length+1];
        for (int i = 0; i < messageArray.length; i++){
            temp[i] = messageArray[i];
        }
        temp[temp.length-1] = Row;
        messageArray = temp;
    }

    // Printe die einzelnen Zeilen mit dem Rand
    public static void printMessage(){

        for (int i = 0; i < messageArray.length; i++){
            if (i == 0){
                System.out.println("/ "+messageArray[i]+" \\");
            } else if (i == messageArray.length-1){
                System.out.println("\\ "+messageArray[i]+" /");
            } else {
                System.out.println("| "+messageArray[i]+" |");
            }
        }
    }

    public static void PrintTextbox(int width, String message) {

        // Die Message wird in einzelne Wörter zerlegt und
        // diese als Array gespeichert
        String[] words = message.split(" ");

        // Generiere den oberen Rand mit Länge width
        String topborder = " ";
        for (int i = 0; i < width; i++) {
            topborder += "_";
        }

        // Generiere den unteren Rand mit Länge width
        String bottomborder = " ";
        for (int i = 0; i < width; i++) {
            bottomborder += "-";
        }

        // Hilfsvariablen für die Schleife
        String Row = "";
        String rest = "";
        int i = 0;

        while (i < words.length) {

            //Prüfe ob ein Rest existiert
            if (rest.length() > 0) {

                //Falls der Rest in die Zeile passt füge ihn hinzu und gehe zum nächsten Wort
                if ((rest.length() <= width-2)) {
                    Row += rest;
                    rest = "";
                    i++;

                // Falls der Rest zu lang ist, splitte ihn, speicher den Rest,
                // printe die volle Zeile, leere sie danach und gehe zurück an den Anfang der Schleife
                } else if (rest.length() > width-2) {
                    Row = rest.substring(0, width-2);
                    rest = rest.substring(width-2);

                    nextRow(Row);

                    Row = "";
                    continue;
                }
            }

            // Wenn i >= words.length gibt es noch einen Rest der geprintet werden muss
            if ( i >= words.length) {
                break;
            }

            // Prüfe ob das Wort zu lang für eine Zeile ist
            if (words[i].length() > width-2){

                // Wenn schon etwas in der Zeile steht fülle sie auf und speicher sie
                if (Row.length() > 0){
                    while (Row.length() < width-2) {
                        Row += " ";
                    }
                    nextRow(Row);
                    Row = "";
                }

                // Wenn noch nichts in der Zeile steht speicher den ersten Teil
                // des Wortes in der Zeile und speicher den Rest
                else {
                    Row = words[i].substring(0, width-2);
                    nextRow(Row);
                    Row = "";
                    rest = words[i].substring(width-2);
                }
                continue;
            }

            // Wenn die Zeile + das nächste Wort micht die Textboxbreite überschreiten
            // wird das nächste Wort der Zeile hinzugefügt. Falls nicht wird die Zeie
            // gefüllt und gespeichert
            if ((Row.length() + words[i].length()) <= width-2){
                if (Row.equals("")){
                    Row += words[i];
                } else {
                    Row += " " + words[i];
                }
                i++;
            } else {
                while (Row.length() < width-2){
                    Row += " ";
                }
                nextRow(Row);
                Row = "";
            }
        }

        // Auffüllen damit die Breite passt
        while (Row.length() < width-2){
            Row += " ";
        }
        nextRow(Row);

        // Printe die Message
        System.out.println(topborder);
        printMessage();
        System.out.println(bottomborder);
    }

    public static void PrintCow (String eyes, boolean tongue, String offset){
        // Normalerweise wird keine Zunge gesprintet
        String tongue2 = " ";

        // Wenn tongue == true, wird die Zunge gesprintet
        if (tongue){
            tongue2 = "U";
        }

        // Printe die Kuh
        System.out.printf(  "%s    \\   ^__^\n" +
                            "%s     \\  (%s)\\_______\n" +
                            "%s        (__)\\       )\\/\\\n" +
                            "%s         %s  ||----w |\n" +
                            "%s            ||     ||", offset, offset,eyes,offset,offset, tongue2,offset);
    }

    public static void main(String[] args) {

        //Standardvariablen
        String eyes = "oo";
        String message = "Du hast keine Nachricht als Kommandozeilenparameter angegeben";
        boolean tongue = false;
        boolean tongueflip = false;
        int textboxwidth = 40;

        // Verändere die Variablen bezüglich der Kommandozeilenparameter
        if (args.length != 0){
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-b")){
                    eyes = "==";
                }
                if (args[i].equals("-d")){
                    eyes = "XX";
                    tongue = true;
                }
                if (args[i].equals("-g")){
                    eyes = "$$";
                }
                if (args[i].equals("-p")){
                    eyes = "@@";
                }
                if (args[i].equals("-s")){
                    eyes = "**";
                    tongue = true;
                }
                if (args[i].equals("-t")){
                    eyes = "--";
                }
                if (args[i].equals("-w")){
                    eyes = "OO";
                }
                if (args[i].equals("-y")){
                    eyes = "..";
                }
                if (args[i].equals("-e")){
                    eyes = args[i+1];
                }
                if (args[i].equals("-T")){
                    tongueflip = true;
                }
                if (args[i].equals("-W")){
                    textboxwidth = Integer.parseInt(args[i+1]);
                }
                if ((args[i].length()>2) && !(args[i-1].equals("-e"))){
                    message = args[i];
                }
            }
        }

        if (tongueflip){
            tongue = !tongue;
        }

        // Verschiebe die Position der Kuh je nach Breite der Sprechblase
        String offset = "";
        for (int i = 0; i < textboxwidth-3; i++) {
            offset += " ";
        }

        //Rufe die Methoden auf um Textbox und Kuh zu printen
        PrintTextbox(textboxwidth, message);
        PrintCow(eyes, tongue, offset);
    }
}
