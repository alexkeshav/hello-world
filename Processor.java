package keshavProj.wordProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Processor {

    public void parseFile() {
        String fileName = "C:\\Users\\Keshav\\kd.txt";
        File file = new File(fileName);
        FileInputStream fis;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);

            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                // process the line
                System.out.println(line.toString());
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

static class TopOccurrence {
    private final PriorityQueue<WordCount> minHeap;
    private final int maxSize;
    public TopOccurrence(int maxSize) {
        this.maxSize = maxSize;
        this.minHeap = new PriorityQueue<WordCount>(Comparator.comparingInt(wc -> wc.count));
//This constructs a min heap (when order of elements is natural i.e. ascending order).
//We are using Natural order for integers (wc.count)
//In order to create a max-heap, we just need to provide reversed comparator i.e. that sorts in descending order,as shown below
//this.minHeap = new PriorityQueue<WordCount>(Comparator.comparingInt((WordCount wc) -> wc.count).reversed());
    }
    public void add(WordCount data) {
        if (minHeap.size() < maxSize) { // size() is Big O(1)
            minHeap.offer(data); // Big O(log(k)) where k is the number of top occurrences required
        } else if (minHeap.peek().count < data.count) { // peek() is Big O(1)
            minHeap.poll(); // Big O(log(k))
            minHeap.offer(data); // Big O(log(k))
        }
    }
    @Override
    public String toString() {
        return "TopOccurrence{" + "minHeap=" + minHeap + ", maxSize=" + maxSize + '}';
    }
}
static class WordCount {
    protected final String word;
    protected final int count;
    WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }
    @Override
    public String toString() {
        return "{" + "word='" + word + '\'' + ", count=" + count + '}'+"\r\n";
    }
}
    }
