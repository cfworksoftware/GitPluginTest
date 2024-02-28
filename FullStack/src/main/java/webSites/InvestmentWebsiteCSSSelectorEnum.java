package webSites;

public enum InvestmentWebsiteCSSSelectorEnum {

    SHAREPRICEQUOTE(1,"Price", "h2.intraday__price > bg-quote.value"), 
    SHAREQUOTEDATETIME(2,"DateTime", "div.intraday__timestamp > span.timestamp__time > bg-quote[field = \"date\"]"), 
    MARKETCLOSED(3,"Closed", "h2.intraday__price > span.value"),
	MARKETAFTERHOURS(4,"AfterHours","h2.intraday__price > bg-quote[session=\"after\"]"),
	SHAREQUOTECURRENCY(5,"Currency","h2.intraday__price > sup.character"),
	INTRADAYCSSTAG(6,"Intraday","h2.intraday__price");
	
    private final Integer key;
    private final String elementName;
    private final String cssSelector;
    
    InvestmentWebsiteCSSSelectorEnum(Integer key, String elementName, String cssSelector) {
        this.key = key;
        this.elementName = elementName;
        this.cssSelector = cssSelector;
    }

    public Integer getKey() {
        return key;
    }
    public String getElementName() {
        return elementName;
    }
	
    public String getCSSSelector() {
        return cssSelector;
    }
	
}
