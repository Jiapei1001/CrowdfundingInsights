package insights.model;

public class Analysis {
	protected String country;
	protected double fundedToLoanAmountRatio;
	
	public Analysis(String country, double fundedToLoanAmountRatio) {
		this.country = country;
		this.fundedToLoanAmountRatio = fundedToLoanAmountRatio;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getFundedToLoanAmountRatio() {
		return fundedToLoanAmountRatio;
	}

	public void setFundedToLoanAmountRatio(double fundedToLoanAmountRatio) {
		this.fundedToLoanAmountRatio = fundedToLoanAmountRatio;
	}

}
