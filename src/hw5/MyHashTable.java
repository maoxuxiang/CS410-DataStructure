package hw5;

public class MyHashTable<K, V> implements HashTable<K, V> {
    private int TABLE_SIZE = 2003;

    MyEntry<K, V>[] table;

    MyHashTable() {
          table = new MyEntry[TABLE_SIZE];
          for (int i = 0; i < TABLE_SIZE; i++)
                table[i] = null;
    }
    
    MyHashTable(int size) {
    	TABLE_SIZE = size;
        table = new MyEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
              table[i] = null;
  }
    
    private int hashVal(K key) {
    	int hashVal = 0;
    	 
		for(int i=0; i<((String)key).length(); i++) {
			hashVal = 37 * hashVal + ((String)key).charAt(i);
		}
		hashVal = hashVal % TABLE_SIZE;
		
		return hashVal;
    	
    }
    
	public MyEntry<K, V> getNextEntry(K key) {
		int hash;
		if (key instanceof Integer ) {
			hash = (Integer)key % TABLE_SIZE;
		}
		else {
			hash = hashVal(key);
		}
        if (table[hash] == null)
              return null;
        else {
              MyEntry<K, V> entry = table[hash];
              while (entry != null && entry.getKey() != key)
                    entry = entry.getNext();
              if (entry == null)
                    return null;
              else
                    return entry.getNext();
        }
  }
	
	public MyEntry<K, V> getEntry(K key) {
		int hash;
		if (key instanceof Integer ) {
			hash = (Integer)key % TABLE_SIZE;
		}
		else {
			hash = hashVal(key);
		}
        if (table[hash] == null)
              return null;
        else {
              MyEntry<K, V> entry = table[hash];
              while (entry != null && entry.getKey() != key)
                    entry = entry.getNext();
              if (entry == null)
                    return null;
              else
                    return entry;
        }
  }

	public V get(K key) {
		int hash;
		if (key instanceof Integer ) {
			hash = (Integer)key % TABLE_SIZE;
		}
		else {
			hash = hashVal(key);
		}
          if (table[hash] == null)
                return null;
          else {
                MyEntry<K, V> entry = table[hash];
                while (entry != null && entry.getKey() != key)
                      entry = entry.getNext();
                if (entry == null)
                      return null;
                else
                      return (V)entry.getValue();
          }
    }

    public void put(K key, V value) {
    	int hash;
		if (key instanceof Integer ) {
			hash = (Integer)key % TABLE_SIZE;
		}
		else {
			hash = hashVal(key);
		}
          if (table[hash] == null)
                table[hash] = new MyEntry<K, V>(key, value);
          else {
                MyEntry<K, V> entry = table[hash];
                while (entry.getNext() != null && entry.getKey() != key)
                      entry = entry.getNext();
                if (entry.getKey() == key)
                      entry.setValue(value);
                else
                      entry.setNext(new MyEntry<K, V>(key, value));
          }
    }

    public V remove(K key) {
    	int hash;
		if (key instanceof Integer ) {
			hash = (Integer)key % TABLE_SIZE;
		}
		else {
			hash = hashVal(key);
		}
          if (table[hash] != null) {
                MyEntry<K, V> prevEntry = null;
                MyEntry<K, V> entry = table[hash];
                while (entry.getNext() != null && entry.getKey() != key) {
                      prevEntry = entry;
                      entry = entry.getNext();
                }
                if (entry.getKey() == key) {
                      if (prevEntry == null) {
                    	  table[hash] = entry.getNext();
  
                      } 
                      else {
                    	  prevEntry.setNext(entry.getNext());

                      }
                      return (V)entry.getValue();     
                }
                else
                	return null;
          	  
          }
          else 
        	  return null;
    }

}

class MyEntry<K, V> {
    private K key;
    private V value;
    private MyEntry<K, V> next;

    MyEntry(K key, V value) {
          this.key = key;
          this.value = value;
          this.next = null;
    }

    public V getValue() {
          return value;
    }

    public void setValue(V value) {
          this.value = value;
    }

    public K getKey() {
          return key;
    }

    public MyEntry<K, V> getNext() {
          return next;
    }

    public void setNext(MyEntry<K, V> next) {
          this.next = next;
    }
}