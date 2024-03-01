package webSites;

public enum QuoteDescriptorEnum {

    QUOTETAG(1,"Quote", " | Quote Price: "), 
    QUOTEDATETAG(2, "Date", " | Quote date: "), 
    MARKETSTATUSTAG(3,"Market status", " | Market Status: ");
	
    private final Integer key;
    private final String tagName;
    private final String tagText;
    
    QuoteDescriptorEnum(Integer key, String tagName, String tagText) {
        this.key = key;
        this.tagName = tagName;
        this.tagText = tagText;
    }

    public Integer getKey() {
        return key;
    }
    public String getTagName() {
        return tagName;
    }
	
    public String getTagText() {
        return tagText;
    }
	
}
