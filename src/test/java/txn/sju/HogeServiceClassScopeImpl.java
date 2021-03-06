package txn.sju;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = JdbcSQLException.class)//★
public class HogeServiceClassScopeImpl implements HogeService {
	private Logger L = LoggerFactory.getLogger(HogeServiceClassScopeImpl.class);

	@Autowired
	DataSource dataSource;

	@Override
	public void doInsert(String[] sqls) throws Throwable {
		Connection c = dataSource.getConnection();
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		for (String sql : sqls)
			s.executeUpdate(sql);
	}

	@Override
	public int doSelect(String sql) throws Throwable {
		Connection c = dataSource.getConnection();
		System.out.println(c);
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			i++;
			L.info(String.format("★%d", rs.getInt(1)));
		}
		return i;
	}
}
