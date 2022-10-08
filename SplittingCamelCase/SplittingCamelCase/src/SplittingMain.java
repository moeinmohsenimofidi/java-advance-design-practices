import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplittingMain {

    static boolean isBeginWithNumber(String character){
        return Character.isDigit(character.charAt(0));
    }
    static boolean isBeginWithUpper(String character){
        return Character.isUpperCase(character.charAt(0));
    }
    static boolean isBeginWithLower(String character){
        return Character.isLowerCase(character.charAt(0));
    }


    static void NumberStart(String firstCharacter) throws Exception {
        if(isBeginWithNumber(firstCharacter)){
            throw new Exception("Oops,Can't start with the number!!");
        }
    }

    static void IllegalCharacters(List<String> input) throws Exception {
        for(String character:input){
            if(!isBeginWithNumber(character) && !isBeginWithUpper(character) && !isBeginWithLower(character)){
                throw new Exception("Special characters are not allowed,only letters and numbers");
            }
        }
    }

    public static List<String> splittingCamelCaseStrings(String camelcase) throws Exception {

        List<String> splittingList = new ArrayList<>();

        NumberStart(camelcase);
        IllegalCharacters(Collections.singletonList(camelcase));


        if (camelcase != null && !camelcase.isEmpty()) {
            List<Integer> index = indexCheck(camelcase);

            for (int i = 0; i < index.size() - 1; ++i) {

                splittingList.add(FormatOfWord(camelcase.substring(index.get(i), index.get(i + 1))));
            }
        }
        return splittingList;
    }

    public static boolean checkFrontBack(String camelcase, int i) {

        return (!Character.isDigit(camelcase.charAt(i))
                && (camelcase.charAt(i) == Character.toUpperCase(camelcase.charAt(i))
                && (camelcase.charAt(i + 1) == Character.toLowerCase(camelcase.charAt(i + 1))
                || camelcase.charAt(i - 1) == Character.toLowerCase(camelcase.charAt(i - 1)))))
                || (Character.isDigit(camelcase.charAt(i)) && !Character.isDigit(camelcase.charAt(i - 1)));
    }

    public static String FormatOfWord(String word) {
        if (word.equals(word.toUpperCase())) {
            return word;
        }
        return word.toLowerCase();
    }

    public static List<Integer> indexCheck(String camelcase) {

        List<Integer> index = new ArrayList<>();

        index.add(0);

        for (int i = 1; i < camelcase.length() - 1; ++i) {

            if (checkFrontBack(camelcase, i))
                index.add(i);
        }

        index.add(camelcase.length());
        return index;
    }




}