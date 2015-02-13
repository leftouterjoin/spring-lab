package txn.aop;

public interface FooService {
	void insert(String[] sqls) throws Throwable;
	int select(String sql) throws Throwable;
}
