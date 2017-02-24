package project3;

public class ParseJSON {

	private String parsin;

	public ParseJSON(String parsin) {
		this.setParsin(parsin);
	}

	/**
	 * Cleans and splits string, and either returns null if null, or string of words to sort by
	 * @param parsin - JSON string to parse
	 * @return Returns cleaned string of first and last name or null if null
	 */
	public String parseNames(String parsin) {
		
		if(parsin != null) {
			parsin = parsin.replace("{", "");
			parsin = parsin.replace("}", "");
			String[] commas = parsin.split(",");
			String tmp = "";
			for(String a : commas) {
				String[] colon = a.split(" : ");
				tmp += colon[1];
			}
			return tmp;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Cleans and splits string, and either returns null if null, or string of letters to sort by
	 * @param parsin - JSON string to parse
	 * @return Returns cleaned string of letters or null if null
	 */
	public String parseLetters(String parsin) {
		
		if(parsin != null) {
			parsin = parsin.replace("{", "");
			parsin = parsin.replace("}", "");
			String[] commas = parsin.split(",");
			String tmp = "";
			for(String a : commas) {
				String[] colon = a.split(":");
				tmp += colon[0];
				tmp += colon[1];
			}
			return tmp;
		}
		else {
			return null;
		}
	}

	public String getParsin() {
		return parsin;
	}

	public void setParsin(String parsin) {
		this.parsin = parsin;
	}
}