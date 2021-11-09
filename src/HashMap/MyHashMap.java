package HashMap;


import java.util.*;

public class MyHashMap implements MyMap {


    private static class HashMapEntry implements MyMap.Entry {
        private String key;
        private String value;
        private int hashCode;
        private HashMapEntry next;


        private HashMapEntry(String key, String value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof HashMapEntry)) return false;
            HashMapEntry that = (HashMapEntry) o;
            return hashCode == that.hashCode && getKey().equals(that.getKey()) && getValue().equals(that.getValue());
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            result = 31 * result + hashCode;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }


        @Override
        public void setValue(String value) {
            this.value = value;
        }


        @Override
        public Object getNext() {
            return next;
        }


        @Override
        public String toString() {
            return key + "=" + value;
        }
    }


    private int size = 0;
    private HashMapEntry[] table = new HashMapEntry[16];
    private double loadFactor = 0.75;
    private double threshold = loadFactor * table.length;


    @Override
    public void clear() {
        size = 0;
        table = new HashMapEntry[16];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String put(String key, String value) {
        String result = putInternal(key, value);
        if (result == null) {
            size++;
        }
        if (size > threshold) {
            resize();
        }
        return result;
    }

    private String putInternal(String key, String value) {
        HashMapEntry newEntry = new HashMapEntry(key, value, key.hashCode());
        int position = newEntry.hashCode % table.length;
        if (table[position] != null) {
            HashMapEntry tmp = table[position];
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    String oldValue = tmp.value;
                    tmp.value = value;
                    return oldValue;
                }
                tmp = tmp.next;
            }
            newEntry.next = table[position];
        }
        table[position] = newEntry;
        return null;
    }

    private void resize() {
        Entry[] arr = toArray();
        table = new HashMapEntry[table.length * 2];
        threshold = loadFactor * table.length;
        for (Entry entry : arr) {
            putInternal(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }


    @Override
    public String get(String key) {
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                if (table[i].getKey().equals(key)) {
                    return table[i].getValue();
                }
            }
        }
        return null;
    }


    private void removeNext(Entry next) {
        if (next != null) {
            if (next.getNext() != null)
                removeNext(next);

            next = null;
        }

    }

    @Override
    public String remove(String key) {
        int position = key.hashCode() % table.length;
        Entry temp = table[position];
        removeNext(table[position]);
        table[position] = null;
        size--;
        return (String) temp.getValue();
    }

    @Override
    public Entry[] toArray() {
        HashMapEntry[] result = new HashMapEntry[size];
        int index = 0;
        for (HashMapEntry tmp : table) {
            while (tmp != null) {
                result[index] = tmp;
                tmp = tmp.next;
                index++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
