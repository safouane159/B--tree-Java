package main.java.fr.miage.fsgbd;

import java.util.Objects;


public class keyPointer <KeyType, ValueType>{
	
    public keyPointer(ValueType value, KeyType key) {
		super();
		this.value = value;
		this.key = key;
	}
	public ValueType getValue() {
		return value;
	}
	public void setValue(ValueType value) {
		this.value = value;
	}
	public KeyType getKey() {
		return key;
	}
	public void setKey(KeyType key) {
		this.key = key;
	}
	private ValueType value;
	 private KeyType key;
	 
	 
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        keyPointer keyValue = (keyPointer) o;
	        return Objects.equals(key, keyValue.key);
	    }
	
	

}
