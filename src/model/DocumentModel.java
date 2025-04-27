package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DocumentModel {
    private File current;
    private boolean hasFileChanges;
    private String content = "";

    public static void main(String[] args) {
        DocumentModel model = new DocumentModel(new File("C:/Users/User/Desktop/niggerman.txt"));
    }

    public DocumentModel(File file) {
        current = file;
        readCurrentFile();
    }

    private void print() {
        System.out.print(content);
    }

    private void readCurrentFile() {
        if (current == null) {
            System.out.println("Error when file reading!");
            return;
        }


        try(FileReader fileReader = new FileReader(current);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while (bufferedReader.ready()) {
                char[] chars = new char[1024];
                int n = bufferedReader.read(chars, 0, chars.length);

                if (n == -1)
                    return;
                else {

                    StringBuilder builder = new StringBuilder(chars.length);
                    for (int i = 0; i < n; i++)
                        builder.append(chars[i]);

                    content = content + builder;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
