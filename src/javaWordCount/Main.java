package javaWordCount;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main
{
  public static void main(String[] args) {
    String filePath = "/Library/WebServer/Documents/lambda/java-WordCount/declaration.txt";
    String declarationOfHumanRights = "";

    declarationOfHumanRights = readAllBytes(filePath);

    declarationOfHumanRights.replaceAll("[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]", "");
    declarationOfHumanRights = declarationOfHumanRights.toLowerCase();
    String[] words = declarationOfHumanRights.split(" ");
    HashMap<String, Integer> wBw = new HashMap<>();
    for(String w : words)
    {
      if(wBw.containsKey(w)) {
        wBw.put(w, wBw.get(w) + 1);
      } else {
        wBw.put(w, 1);
      }
    }
    for(String word : wBw.keySet())
    {
      System.out.println("key: " + word);
      System.out.println("Value: " + wBw.get(word));
    }
  }

  private static String readAllBytes(String filePath)
  {
    String content = "";

    try {
      content = new String( Files.readAllBytes(Paths.get(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
      //TODO: handle exception
    }
    return content;
  }
}