package txn.aop2;

public interface BarService {
	void foo(boolean isFail) throws Throwable;
	void bar(boolean isFail) throws Throwable;
}
