package insights.servlet;

import insights.dal.*;
import insights.model.Partners;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Partner Recommender allows the user to search for a partner that may more likely fund a loan
 * given the loan sector
 */
@WebServlet("/partnerrecommender")
public class PartnerRecommender extends HttpServlet {	
	private static final long serialVersionUID = -888438509027158868L;
	protected PartnersDao partnersDao;
	
	@Override
	public void init() throws ServletException {
		partnersDao = PartnersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Partners> partners = new ArrayList<>();
        List<Integer> partnerIds = new ArrayList<>();
        
        // Retrieve and validate sector
        String sector = req.getParameter("sector");
        if (sector == null || sector == "" || sector == "None") {
            messages.put("success", "Please select a sector");
        } else {
        	// Retrieve partners
        	try {
    			partnerIds = partnersDao.findPartnersFundingMaximumLoansForSector(sector);
    			for (Integer id : partnerIds) {
    				Partners partner = partnersDao.getPartnerByPartnerId(id);
    				partners.add(partner);
    			}
    			if (partners.size() == 0) {
    		        messages.put("success", "No partners on record funding sector " + sector);
    			} else {
    			     messages.put("success", "Displaying Up To 10 Recommended Partners for sector " + sector);
    			}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        req.setAttribute("partners", partners);   
        req.getRequestDispatcher("/PartnerRecommender.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Integer> partnerIds = new ArrayList<>();
        List<Partners> partners = new ArrayList<>();
        
        // Retrieve and validate sector
        String sector = req.getParameter("sector");
        String region = req.getParameter("region");
        if ((sector == null || sector == "" || sector == "None")
        		&& (region ==  null || region.trim().isEmpty())) {
            messages.put("success", "Please select a sector or specify a region");
        } else {
        	// Retrieve Loan/s, and store as a message.
        	boolean recommendBySector = (sector != null && sector != "");
        	try {
        		if (recommendBySector == true) {
        			partnerIds = partnersDao.findPartnersFundingMaximumLoansForSector(sector);
           			if (partnerIds.size() == 0) {
        		        messages.put("success", "No partners on record funding sector " + sector);
        			} else {
        			     messages.put("success", "Displaying Up To 10 Recommended Partners for sector " + sector);
        			}
        		} else {
        			partnerIds = partnersDao.findPartnersFundingMaximumLoansForRegion(region);
           			if (partnerIds.size() == 0) {
        		        messages.put("success", "No partners on record region " + region);
        			} else {
        			     messages.put("success", "Displaying Up To 10 Recommended Partners for region " + region);
        			}
        		}
        		// Get partners by ID
    			for (Integer id : partnerIds) {
    				Partners partner = partnersDao.getPartnerByPartnerId(id);
    				partners.add(partner);
    			}
 
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        req.setAttribute("partners", partners);     
        req.getRequestDispatcher("/PartnerRecommender.jsp").forward(req, resp);
	}
}

