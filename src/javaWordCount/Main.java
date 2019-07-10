package javaWordCount;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
    // for(String word : wBw.keySet())
    // {
    //   System.out.println("key: " + word);
    //   System.out.println("Value: " + wBw.get(word));
    // }

    ArrayList<HashMap.Entry<String, Integer>> sortedWords = new ArrayList<>();
    sortedWords.addAll(wBw.entrySet());
    Collections.sort(sortedWords, new Comparator<HashMap.Entry<String, Integer>>(){
      public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2)
      {
        return o2.getValue() - o1.getValue();
      }
    });

    // for (HashMap.Entry<String, Integer> d : sortedWords)
		// {
		// 	System.out.println("key: " + d.getKey() + " value: " + d.getValue());
    // }
    
    for(int i = 0; i < 49; i++)
    {
      Map.Entry<String,Integer> entry = new AbstractMap.SimpleEntry<String, Integer>(sortedWords.get(i));
      System.out.println("'" + entry.getKey() + "'" + " was found " + entry.getValue() + " times.");
    }

    // Array -> Collection -> ArrayList -> List

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