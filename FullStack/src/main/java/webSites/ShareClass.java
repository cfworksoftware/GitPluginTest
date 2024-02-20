package webSites;

public class ShareClass {

	private String currency;
	private String shareName;
	private String shareURL;
//	private InvestmentWebsiteCSSSelectorEnum investmentWebsite;
	
	ShareClass(InvestmentWebsitesEnum name/*, InvestmentWebsiteCSSSelectorEnum cssSelector*/){
		shareName = name.getShareName();
		shareURL = name.getShareURL();
		currency = name.getCurrency();
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public String getShareName() {
		return shareName;
	}
	
	public String getShareURL() {
		return shareURL;
	}
	
	public String getWebsiteElementName(InvestmentWebsiteCSSSelectorEnum component) {
		return component.getElementName();
	}
	
	public String getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum component) {
		return component.getCSSSelector();
	}
	
	public String getDatabaseTableName(DatabaseTableNamesEnum tableName) {
		return tableName.getDatabaseTableName();
	}
	
	
}
