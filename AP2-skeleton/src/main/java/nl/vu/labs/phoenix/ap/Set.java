package nl.vu.labs.phoenix.ap;

import java.util.NoSuchElementException;

public class Set<T extends Comparable<T>> implements SetInterface<T> {

	private ListInterface<T> list;

	public Set() {
		list = new LinkedList<T>();
	}

	@Override
	public boolean add(T t) {
		if (this.containsElement(t) == true) {
			return false;
		}
		list.insert(t);
		return true;
	}

	@Override
	public T get() {
		if (list.isEmpty()) {
			throw new NoSuchElementException(); 
		} else {
			list.goToFirst();
			return list.retrieve();
		}
	}

	@Override
	public boolean remove(T t) {
		if (list.isEmpty()) {
			throw new NoSuchElementException(); 
		} else {
			if (list.find(t)) {
				list.remove();
				return true;
			} else return false;
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public SetInterface<T> copy() {
		SetInterface<T> result = new Set<T>();
		T t;
		if (this.isEmpty()) {
			result.init();
		} else {

			this.list.goToFirst();
			t = this.list.retrieve();
			if(!result.containsElement(t)) {
				result.add(t);
			}
			while (this.list.goToNext()) {
				t = this.list.retrieve();
				if(!result.containsElement(t)) {
					result.add(t);
				}
			}

		}
		return result;
	}

	@Override
	public SetInterface<T> union(SetInterface<T> set) {
		if (set.isEmpty()) {
			return this.copy();
		}
		SetInterface<T> result = set.copy();
		if (this.isEmpty()) {
			return result;
		}
		this.list.goToFirst();
		if (!result.containsElement(this.list.retrieve())){
			result.add(this.list.retrieve());
		}

		while (this.list.goToNext()) {
			if (!result.containsElement(this.list.retrieve())){
				result.add(this.list.retrieve());
			}
		}
		return result;
	}

	@Override
	public SetInterface<T> intersection(SetInterface<T> set) {
		SetInterface<T> result = new Set<T>();
		if (this.isEmpty() || set.isEmpty()) {
			return result;
		}
		this.list.goToFirst();

		if (set.containsElement(this.list.retrieve())){
			result.add(this.list.retrieve());
		}

		while (this.list.goToNext()) {
			if (set.containsElement(this.list.retrieve())){
				result.add(this.list.retrieve());
			}
		}
		return result;
	}

	@Override
	public SetInterface<T> difference(SetInterface<T> set) {
		SetInterface<T> result = new Set<T>();

		if (set.isEmpty()) {
			return this.copy();
		} 
		
		if (this.isEmpty()) {
			return result;
		}
		
		this.list.goToFirst();

		if (set.containsElement(this.list.retrieve()) == false){
			result.add(this.list.retrieve());
		}

		while (this.list.goToNext()) {
			if(set.containsElement(this.list.retrieve()) == false){
				result.add(this.list.retrieve());
			}
		}
		return result;
	}

	@Override
	public SetInterface<T> symdiff(SetInterface<T> set) {
		SetInterface<T> result = set.copy();
		SetInterface<T> intersection = this.intersection(set);
 		
		if (this.isEmpty() && set.isEmpty()){
			return new Set<T>();
		} else if (set.isEmpty()) {
			return this.copy();
		} else if (this.isEmpty()) {
			return result;
		}
 		
		this.list.goToFirst();
		if (result.containsElement(this.list.retrieve())){
			result.remove(this.list.retrieve());
		} else {
			result.add(this.list.retrieve());
		}

		while (this.list.goToNext()) {
			if (result.containsElement(this.list.retrieve())){
				result.remove(this.list.retrieve());
			} else {
				result.add(this.list.retrieve());
			}
		}
		
		return result;
	}

	@Override
	public boolean containsElement(T t) {
		return list.find(t);
	}

	@Override
	public void init() {
		list.init();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
