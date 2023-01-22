package src;

public class DocEntry implements Comparable<DocEntry> {
    private final int docId;
    private final int rate;

    public DocEntry(int docId, int rate) {
        this.docId = docId;
        this.rate = rate;
    }

    @Override
    public int compareTo(DocEntry o) {
        return o.rate - rate;
    }

    public int getDocId() {
        return docId;
    }

    public int getRate() {
        return rate;
    }
}
