package DB;
import java.sql.*;

public class ForDatabase {
	private static Connection connectionConnection;

	public ForDatabase() {// �ڹ��캯���м���jdbc
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String dburl = "jdbc:mysql://localhost:3306/dataforurl?useUnicode=true&characterEncoding=GBK";// ��������ݿ���ʦlogininterface
//		try {
//			connectionConnection = DriverManager.getConnection(dburl, "root", "root");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		connectionConnection = DBConnection.getConnection();
	}

	public static ResultSet searchByType(String fileType, String content, int ageRange) {// ��������ǩ����ѯ������ID�ļ���
																							// ע��!ͨ����ҳʵ�֣�����û�û��ѡ����fileType
																							// ��contentĬ��Ϊ�գ�ageRenge��Ĭ��Ϊ-1

		ResultSet resultSet = null;

		String sqlString = "select recordID from myweb ";// where
														// fileType='"+fileType+"'
														// and
														// content='"+content+"'
														// and ageRange="
														// +ageRange;//������������ͬʱ����
		int flag = 0;// flag is a tag showing whether we need to add 'and' or
						// not to sqlString
		if (fileType != null) {
			sqlString += " where fileType='" + fileType + "'";
			flag = 1;
		}
		if (content != null) {
			if (flag == 0) {// ֮ǰ��û������
				sqlString += " where content='" + content + "'";
			} else {
				sqlString += " and content='" + content + "'";
			}
			flag = 1;
		}
		if (ageRange > 0) {// >0˵���û�ΪageRenge��ֵ
			if (flag == 0) {
				sqlString += " where ageRange=" + ageRange;
			} else {
				sqlString += " and ageRange=" + ageRange;
			}
		}
		try {
			Statement statement;
			statement = connectionConnection.createStatement();
			resultSet = statement.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSet;
	}

	public static ResultSet serarchByKey(String keyWord)// ���ؼ���������������������ֻҪ�а����ؼ��ֵļ��ɣ�����ID�ļ���
	{
		ResultSet resultSet = null;
		String sqlString = "select recordID from myweb where tagname like '%" + keyWord + "%' or fileType like '%"
				+ keyWord + "%' or content like '%" + keyWord + "%'";
		Statement statement;
		try {
			statement = connectionConnection.createStatement();
			resultSet = statement.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSet;
	}

	public static boolean isFree(int id) {// ����id���ж��Ƿ��������

		ResultSet resultSet = null;
		String sqlString = "select isFree from myweb where recordID=" + id;
		Statement statement;
		try {
			statement = connectionConnection.createStatement();
			resultSet = statement.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultSet.next();
			int isfree = resultSet.getInt(1);
			if (isfree == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static String getURL(int id) {// ����id������URL
		ResultSet resultSet = null;
		String sqlString = "select URL from myweb where recordID=" + id;
		Statement statement;
		String URL = null;
		try {
			statement = connectionConnection.createStatement();
			resultSet = statement.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultSet.next();
			URL = resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return URL;

	}

	public static String getTagName(int id) {
		ResultSet resultSet = null;
		String sqlString = "select tagname from myweb where recordID=" + id;
		Statement statement;
		String tagnameString = null;
		try {
			statement = connectionConnection.createStatement();
			resultSet = statement.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultSet.next();
			tagnameString = resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tagnameString;

	}
}
