public class app {
    public static void main(String[] args) throws Exception {

        // Get File
        String filePath = Utils.getInputFilePath(args);
        String encodedContent = FileManager.readFromPath(filePath);

        // Process Content
        long start = System.currentTimeMillis();
        String key = KeyManager.findKey(encodedContent);
        String decodedContent = Decoder.decode(encodedContent, key);
        long finish = System.currentTimeMillis();

        // Save Output
        Utils.getOutputFilePath(args).ifPresent(path -> FileManager.writeOnPath(path, decodedContent));

        // Final Results
        System.out.println("################### ");
        System.out.println("| Processing time | " + (finish - start) + " (ms)");
        System.out.println("| Key info        | " + (key) + " (" + (key.length()) + ")");
        System.out.println("| Encoded sample  | " + encodedContent.substring(0, 100));
        System.out.println("| Decoded sample  | " + decodedContent.substring(0, 100));
        System.out.println("################### ");

    }

}
