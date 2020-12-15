package insights.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import insights.model.Partners;

public class PartnersDao {
	protected ConnectionManager connectionManager;
	private static PartnersDao instance = null;
	
	protected PartnersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static PartnersDao getInstance() {
		if(instance == null) {
			instance = new PartnersDao();
		}
		return instance;
	}
	
	/**
	 * Returns top 10 partners who have funded maximum number of loans for the given region
	 * @param region - the region in which we want to find partners who have historically funded loans in
	 * @return - the top 10 partners who have funded maximum number of loans for the given region
	 * @throws SQLException
	 */
	public List<Integer> findPartnersFundingMaximumLoansForRegion(String region) throws SQLException {
		List<Integer> partnerResults = new ArrayList<>();
		String selectPartners = 
				"SELECT COUNT(*) AS COUNT,Partners.PartnerID " +
				"FROM( " +
					"SELECT LoanID,PartnerID,Loans.Region " +
					"FROM Loans INNER JOIN Regions " +
					"ON Loans.Region = Regions.Region " +
					"WHERE Loans.Region=? " +
					"LIMIT 1000000) AS LOAN_REGION " +
				"INNER JOIN Partners " +
						"ON LOAN_REGION.PartnerID = Partners.PartnerID " +
				"GROUP BY Partners.PartnerID " +
				"ORDER BY COUNT DESC " +
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPartners);
			selectStmt.setString(1, region);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int partnerID = results.getInt("Partners.PartnerId");
				partnerResults.add(partnerID);
			}
			return partnerResults;
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
	}
	
	/**
	 * Returns top 10 partners who have funded maximum number of loans for the given sector
	 * @param sector - the sector in which we want to find partners who have historically funded loans in
	 * @return - the top 10 partners who have funded maximum number of loans for the given sector
	 * @throws SQLException
	 */
	public List<Integer> findPartnersFundingMaximumLoansForSector(String sector) throws SQLException {
		List<Integer> partnerResults = new ArrayList<>();
		String selectPartners = 
				"SELECT COUNT(*) AS COUNT,Partners.PartnerID " +
				"FROM " +
					"(SELECT LoanID,PartnerID,LoanSectors " +
					"FROM Loans INNER JOIN LoanActivities " +
					"ON Loans.LoanActivities = LoanActivities.LoanActivities " +
					"WHERE LoanSectors=? " +
					"LIMIT 1000000) AS LOANS_SECTOR " +
				"INNER JOIN Partners " +
						"ON Partners.PartnerID = LOANS_SECTOR.PartnerID " +
				"GROUP BY Partners.PartnerID " +
				"ORDER BY COUNT DESC " +
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPartners);
			selectStmt.setString(1, sector);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int partnerID = results.getInt("Partners.PartnerId");
				partnerResults.add(partnerID);
			}
			return partnerResults;
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
	}

	/**
	 * Save the Partners instance by storing it in MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Partners create(Partners partner) throws SQLException {
		String insertPartner = "INSERT INTO Partners(PartnerID,PartnerName,PartnerSector) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPartner);
			insertStmt.setInt(1, partner.getPartnerId());
			insertStmt.setString(2, partner.getPartnerName());
			insertStmt.setString(3, partner.getSector().name().replaceAll("_", " "));
			insertStmt.executeUpdate();
			return partner;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	/**
	 * Get the Partners record by fetching it from MySQL instance.
	 * This runs a SELECT statement and returns a single Partners instance.
	 */
	public Partners getPartnerByPartnerId(int partnerId) throws SQLException {
		String selectPartner = "SELECT PartnerID,PartnerName,PartnerSector FROM Partners WHERE PartnerID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPartner);
			selectStmt.setInt(1, partnerId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultPartnerId = results.getInt("PartnerID");
				String partnerName = results.getString("PartnerName");
				Partners.PartnerSector partnerSector = Partners.PartnerSector.valueOf(
						results.getString("PartnerSector").replaceAll(" ", "_"));
				Partners partner = new Partners(resultPartnerId, partnerName, partnerSector);
				return partner;
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
		return null;
	}
	
	/**
	 * Get the Partners record by fetching it from MySQL instance.
	 * This runs a SELECT statement and returns a single Partners instance.
	 */
	public List<Partners> getAllPartners() throws SQLException {
		List<Partners> partners = new ArrayList<Partners>();
		String selectPartners = "SELECT * FROM Partners;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPartners);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String partnerId = results.getString("PartnerID");
				String partnerName = results.getString("PartnerName");
				String sector = results.getString("PartnerSector");
				Partners partner = new Partners(Integer.valueOf(partnerId), partnerName, Partners.PartnerSector.valueOf(sector.replaceAll(" ", "_")));
				partners.add(partner);
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
		return partners;
	}
	
	/**
	 * Update the PartnerName of the Partners instance.
	 * This runs a UPDATE statement.
	 */
	public Partners updatePartnerName(Partners partner, String newPartnerName) throws SQLException {
		String updatePartner = "UPDATE Partners SET PartnerName=? WHERE PartnerID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePartner);
			updateStmt.setString(1, newPartnerName);
			updateStmt.setInt(2, partner.getPartnerId());
			updateStmt.executeUpdate();
			
			// Update the partner param before returning to the caller.
			partner.setPartnerName(newPartnerName);
			return partner;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the Partners instance.
	 * This runs a DELETE statement.
	 */
	public Partners delete(Partners partner) throws SQLException {
		String deletePartner = "DELETE FROM Partners WHERE PartnerID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePartner);
			deleteStmt.setInt(1, partner.getPartnerId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
