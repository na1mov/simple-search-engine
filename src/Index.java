package src;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Index {
    //private Map<Integer, Map<String, Integer>> index = new HashMap<>();
    private final Map<String, Map<Integer, Integer>> index = new HashMap<>();

    public Index(String dirName, int filesNum) throws IOException {
        for (int docId = 1; docId <= filesNum; docId++) {
            String fileName = dirName + "/" + docId + ".txt";
            try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    for (String word : line.split(" ")) {
                        if (!index.containsKey(word)) {
                            index.put(word, new HashMap<>());
                        }
                        Map<Integer, Integer> docToWordFreq = index.get(word);
                        docToWordFreq.putIfAbsent(docId, 1);
                        docToWordFreq.put(docId, docToWordFreq.get(docId) + 1);
                    }
                }
            }
        }
    }

    public List<Integer> search(String query) {
        String[] queryWords = query.split(" ");
        Map<Integer, Integer> serp = new HashMap<>();

        for (String queryWord : queryWords) {
            Map<Integer, Integer> wordIndex = index.get(queryWord);
            for (Map.Entry<Integer, Integer> docIdAndFreq : wordIndex.entrySet()) {
                int docId = docIdAndFreq.getKey();
                int wordFreq = docIdAndFreq.getValue();
                serp.putIfAbsent(docId, 1);
                serp.put(docId, serp.get(docId) + wordFreq);
            }
        }

        List<DocEntry> serpList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> docIdAndFreq : serp.entrySet()) {
            serpList.add(new DocEntry(docIdAndFreq.getKey(), docIdAndFreq.getValue()));
        }

        Collections.sort(serpList);
        List<Integer> ans = new ArrayList<>();
        for (DocEntry docEntry : serpList) {
            ans.add(docEntry.getDocId());
        }
        return ans;
    }
}
