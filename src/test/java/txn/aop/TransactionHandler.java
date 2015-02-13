package txn.aop;

public interface TransactionHandler {
	Object invoke(Func delegate) throws Throwable;
	public interface Func {
		Object invoke() throws Throwable;
	}
}
