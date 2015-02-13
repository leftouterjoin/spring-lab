package txn.aop;

import java.util.ArrayList;
import java.util.List;

public class ZDelegateSample {
	public void test1() {
		CList<String> l = new CList<String>();
		l.count(new Func<String, Boolean>() {
			@Override
			public Boolean invoke(String t) {
				return t.equals("");
			}

		});
	}
}

@SuppressWarnings("hiding")
interface Func<T, R> {
	R invoke(T t);
}

class CList<T> extends ArrayList<T> {
	private List<T> list = new ArrayList<T>();

	public Integer count(final Func<T, Boolean> delegate) {
		int count = 0;
		for (T i : list)
			if (delegate.invoke(i))
				count++;
		return count;
	}

	public Boolean exists(final Func<T, Boolean> delegate) {
		for (T i : list)
			if (delegate.invoke(i))
				return true;
		return false;
	}

	public boolean add(T t) {
		return list.add(t);
	}
}