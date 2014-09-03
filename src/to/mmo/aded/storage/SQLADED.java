package to.mmo.aded.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import to.mmo.aded.Codeset;
import to.mmo.aded.Entity;
import to.mmo.aded.Item;

public class SQLADED implements ADEDStorage, ADEDPersistency {

	public static enum Database {
		MYSQL, POSTGRES, SQLITE
	}

	private Connection connection;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rSet;
	private Database database;
	private String uri;
	private String user;
	private String password;

	public SQLADED() {

	}

	public SQLADED(Database db, String uri, String user, String password) {
		this();
		database = db;
		this.uri = uri;
		this.user = user;
		this.password = password;
		init();
	}

	public SQLADED(Database db, String uri) {
		this();
		database = db;
		this.uri = uri;
		init();
	}

	private void init() {
		try {
			switch (database) {
			case MYSQL:
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				break;
			case POSTGRES:
				Class.forName("org.postgresql.Driver").newInstance();
				break;
			case SQLITE:
				break;
			}
			if (user == null || password == null) {
				connection = DriverManager.getConnection(uri);
			} else {
				connection = DriverManager.getConnection(uri, user, password);
			}
			// connection is up, we're done here.
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initDB() {
		String adedscheme = "create table if not exists aded ("
				+ "id varchar(8)," + "name varchar(256)," + "primary key (id)"
				+ ");";
		String entityscheme = "create table if not exists entity ("
				+ "id varchar(8)," + "name varchar(8),"
				+ "ddversion varchar(8)," + "primary key (id)" + ");";
		String itemscheme = "create table if not exists item ("
				+ "id varchar(8)," + "identity varchar()," + "name varchar(),"
				+ "format varchar()," + "length int," + "resolution int,"
				+ "unit varchar()," + "codeset varchar()," + "minimum int,"
				+ "maximum int," + "ddversion varchar(8)," + "primary key (id)"
				+ ");";
		String entityitemsscheme = "create table if not exists entityitems ("
				+ "entityId varchar(8)," + "itemId varchar(8),"
				+ "type varchar(3),"
				+ "foreign key (entityId) references entity (id),"
				+ "foreign key (itemId) references item (id)"
				+ "primary key (entityId,itemId)" + ");";
		String codesetscheme = "create table if not exists codeset ("
				+ "id varchar(8)," + "name varchar(),"
				+ "ddversion varchar(8)," + "primary key (id)" + ");";
		String codesetelementscheme = "create table if not exists codesetelement ("
				+ "codesetId varchar(8),"
				+ "key varchar(),"
				+ "definition varchar(),"
				+ "primary key (codesetId,key),"
				+ "foreign key (codesetId) references codeset (id)" + ");";
		String aded_data_entities = "create table if not exists entitydata ("
				+ "id int," // need to be sequence!
				+ "entityId varchar(8)," + "timestamp datetime,"
				+ "primary key (id),"
				+ "foreign key (entityId) references entity (id)" + ");";
		String aded_data_items = "create table if not exists itemdata ("
				+ "id int," // need to be sequence!
				+ "dataEntityId int," + "itemId varchar(8),"
				+ "data varchar(256)," + "primary key (id),"
				+ "foreign key (dataEntityId) references entitydata (id),"
				+ "foreign key (itemId) references item (id)" + ");";
	}

	@Override
	public Entity getEntity(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem(String item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Codeset getCodeset(String codeset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Item> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Entity> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putADEDVersion(String version) {
		// TODO Auto-generated method stub

	}

	@Override
	public void putEntity(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void putItem(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void putCodeset(Codeset codeset) {
		// TODO Auto-generated method stub

	}
}
