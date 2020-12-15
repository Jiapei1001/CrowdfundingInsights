package insights.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import insights.dal.AnalysisDao;
import insights.model.Analysis;


@WebServlet("/loananalysis")
public class LoanAnalysis  extends HttpServlet{
	private static final long serialVersionUID = -1283915797204721439L;
	protected AnalysisDao analysisDao;
	
	@Override
	public void init() throws ServletException {
		analysisDao = AnalysisDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Analysis> results = new ArrayList<>();

        try {
	        results = analysisDao.getCountriesWithSmallestLoanDeficitRatio();
	        if (results != null && results.size() > 0) {
		        // Show loan details
	            messages.put("success", "Analysis Successful");
	        } else {
	        	messages.put("success", "Analysis Unsuccessful");
	        }
	        req.setAttribute("results", results);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
      
        req.getRequestDispatcher("/LoanAnalysis.jsp").forward(req, resp);
    }
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Analysis> results = new ArrayList<>();

        try {
	        results = analysisDao.getCountriesWithSmallestLoanDeficitRatio();
	        if (results != null && results.size() > 0) {
		        // Show loan details
	            messages.put("success", "Analysis Successful");
	        } else {
	        	messages.put("success", "Analysis Unsuccessful");
	        }
	        req.setAttribute("results", results);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
      
        req.getRequestDispatcher("/LoanAnalysis.jsp").forward(req, resp);
    }

}
