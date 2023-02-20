package Lesson_2;

public class Main {
    public static void main(String[] args) {

        String[] testText = {"Добрый   день Как      дела Все хорошо ",
                null,
                "Здравствуйте   ",
                ""};

        for (String sentence : testText) {
            System.out.println(sentence + " <- До исправления");
            System.out.println(correction(sentence) + " <- После исправления\n");
        }
    }

    public static String correction(String text) {
        if (text == null) return "Sending \"null\" is incorrect!!!";

        StringBuilder replacedText = new StringBuilder();

        String[] listWords = text.split("\\s+");

        if (!listWords[0].equals("")) {
            String firstWord = listWords[0];
            replacedText.append(firstWord);

            for (int i = 1; i < listWords.length; i++) {
                char firstLetter = (char) listWords[i].codePointAt(0);
                if (Character.isUpperCase(firstLetter)) {
                    replacedText.append(".");
                }
                replacedText.append(" ");
                replacedText.append(listWords[i]);
            }
            replacedText.append(".");

        } else {
            return "Нужно одно или несколько слов для правильной работы!";
        }

        return replacedText.toString();
    }
}

