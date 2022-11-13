package spirit.db;

import java.io.PrintWriter;

import org.hsqldb.Server;

public class HSQLServer {
	private Server hsqlServer = new Server();
	private static HSQLServer instance;

	public static HSQLServer getInstance() {
		if (instance == null) {
			instance = new HSQLServer();
		}

		return instance;
	}

	private HSQLServer() {
		this.hsqlServer.setLogWriter((PrintWriter) null);
		this.hsqlServer.setSilent(true);
		this.hsqlServer.setDatabaseName(0, "SpiritDB");
		this.hsqlServer.setDatabasePath(0, "file:spiritdb");
	}

	public void startDatabase() {
		this.hsqlServer.start();
	}

	public void stopFatabase() {
		this.hsqlServer.stop();
	}
}