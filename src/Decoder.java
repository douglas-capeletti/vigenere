public class Decoder {

    public static String decode(String text, String key) {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int charDistance = text.charAt(i) - key.charAt(i % key.length());
            if (charDistance < 0) charDistance += 26;
            content.append(Character.toString(charDistance + 97));
        }
        return content.toString();
    }

}
