package txn.sju;

public interface HogeService {
	void doInsert(String[] sqls) throws Throwable;
	int doSelect(String sql) throws Throwable;
}
