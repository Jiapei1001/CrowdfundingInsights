package insights.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import insights.model.*;

public class AnalysisDao {
	protected ConnectionManager connectionManager;
	private static AnalysisDao instance = null;
	
	protected AnalysisDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static AnalysisDao getInstance() {
		if(instance == null) {
			instance = new AnalysisDao();
		}
		return instance;
	}
	
	/**
	 * Get the first 1000 the Loans corresponding to the given Country Code
	 * @param countryCode
	 * @return - a list of the first 1000 loans corresponding to the given country code
	 * @throws SQLException
	 */
	public List<Analysis> getCountriesWithSmallestLoanDeficitRatio() throws SQLException {
		List<Analysis> ratios = new ArrayList<>();
		String selectResults =
			"SELECT countries.Country AS COUNTRY, (SUM(loans.FundedAmount) / SUM(loans.LoanAmount)) AS RATIO_FUNDED_TO_REQUESTED "
			+ "FROM loans INNER JOIN countries "
			+ "ON loans.CountryCode = countries.CountryCode "
			+ "GROUP BY countries.Country "
			+ "ORDER BY RATIO_FUNDED_TO_REQUESTED ASC "
			+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectResults);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultCountry = results.getString("Countries.Country");
				double ratio = results.getDouble("RATIO_FUNDED_TO_REQUESTED");
				Analysis r = new Analysis(resultCountry, ratio);
				ratios.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return ratios;
	}

}
