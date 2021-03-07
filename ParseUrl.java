import java.util.*;

class   ParseUrl  {

    public static void checkUrl(String urlAddress, String phraseToReplace) {

        String urlStringModified = urlAddress.replace(phraseToReplace, "~");

        char[] newUrlStringToArray = urlStringModified.toCharArray();

        int firstIndex = 0;
        int lastIndex = 0;

        for (int i = 0; i < newUrlStringToArray.length; i++) {

            if (newUrlStringToArray[i] == '~') {
                firstIndex = i + 1;
            }
            if (newUrlStringToArray[i] == '&' && i > firstIndex && firstIndex != 0) {
                lastIndex = i;
                break;
            } else if (i > firstIndex && firstIndex != 0) {
                lastIndex = newUrlStringToArray.length;

            }
            if (newUrlStringToArray[firstIndex] == '&') {
                printIfNotFound();
                break;
            }
        }
        for (int i = firstIndex; i < lastIndex; i++) {
            System.out.print(newUrlStringToArray[i]);
        }
    }

    public static void printIfNotFound() {
        System.out.print("not found");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String urlAddress = scanner.nextLine();

        String regexExpression = "\\b.*?\\?|\\b=\\w&|\\b=\\d&|.\\w*&|\\b=.\\w+\\b"; // This regex gets rid of all stuff that host, cookie, name and the like
        String[] urlAddressSplitItems = urlAddress.split(regexExpression);

        for (int i = 1; i < urlAddressSplitItems.length; i++) {
            if (urlAddress.contains(urlAddressSplitItems[i])) {
                System.out.print(urlAddressSplitItems[i] + " : ");
                checkUrl(urlAddress, urlAddressSplitItems[i] + "=");
                System.out.println();
            }
        }

        if (!urlAddress.contains("pass=")) {
            System.out.print("");
        } else {
            System.out.print("password : ");
            checkUrl(urlAddress, "pass=");
            System.out.println();
        }
    }
}
